package rest.resources;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import daos.*;
import io.jsonwebtoken.security.WeakKeyException;
import utils.*;
import model.JWTResponse;
import model.Usuario;
import model.SignInData;

import java.io.UnsupportedEncodingException;
import java.util.*;

import rest.resources.AuthService;
@Path("/login")
public class LoginResource {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private UsuarioDAO udao = FactoryDAO.getUsuarioDAO();
	private String mensaje;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AuthenticateUser(SignInData signin) {
	//verify user-pass in bd, if exist, creates token else forbidden
		if (udao.login(signin.getEmail(), signin.getPassword()) != null) {
			Usuario usuario = udao.findUserFromMail(signin.getEmail());
			if (usuario != null) {
				JWTResponse jwsResponse = new JWTResponse();
				jwsResponse.setType("Bearer");
			    long nowMillis = System.currentTimeMillis();
		        Date exp = new Date(nowMillis + 3600000);//1hs
		        try {
					jwsResponse.setAccess_token(AuthService.buildJWToken(usuario, exp));
				} catch (WeakKeyException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        jwsResponse.setExpires_in(Long.valueOf(6000));
				//response.header("Authorization", "Bearer "+jws)
				return Response
						.ok()
						.entity(jwsResponse)
						.build();
			} else {
				mensaje="No se encontro el usuario";
				return Response
						.status(Response.Status.NOT_FOUND)
						.entity(mensaje)
						.build();
			}
		} else {
			mensaje="eMail y/o password incorrectos";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
		
	}
	
}