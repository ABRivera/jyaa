package filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import utils.Segurizado;
import rest.resources.AuthService;//ver inyeccion de dependencia!

@Provider
@Segurizado
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
	
	private static final String BEARER_AUTH = "Bearer"; //o "Bearer "
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Obtiene el header Authorization del request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println("auth header: "+authorizationHeader);
        //Valida el header
        if (!isBearerAuthentication(authorizationHeader)) {
        	System.out.println("header no bearer, abort");
        	abortarConNoAutorizado(requestContext);
            return;
        }
        // Obtenemos token del header
        String token = authorizationHeader.substring(BEARER_AUTH.length()).trim();
        System.out.println("token: "+token);
        if (!validateToken(token)) {
        	System.out.println("invalid token, abort");
        	abortarConNoAutorizado(requestContext);
        	return;
        }

	}
	/*Chequea que el header no sea null y sea de tipo: "Bearer ". No sensible a mayúsculas*/
	private boolean isBearerAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(BEARER_AUTH.toLowerCase() + " ");
    }

    private void abortarConNoAutorizado(ContainerRequestContext requestContext) {
    	/*aborta la cadena de filtros con codigo 401 Unauthorized + Header WWW-Authenticate para indicar el Header AUTHORIZATION esperado*/
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                        		BEARER_AUTH + " realm= BALP")
                        .build());
    }

    private boolean validateToken(String token) {
    	try {
			return AuthService.validateToken(token);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

}
