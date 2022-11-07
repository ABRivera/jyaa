package rest.resources;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import daos.*;
import utils.*;
import model.Usuario;
import java.util.*;
import utils.Segurizado;

@Path("/usuarios")
public class UsuariosResource {

	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private UsuarioDAO udao = FactoryDAO.getUsuarioDAO();
	private String mensaje;
	
	// URI: /rest/usuarios
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Segurizado
	public List<Usuario> getUsuarios(){
		return udao.getAll();
	}
	
	// URI: /rest/usuarios/voluntarios
	@GET
	@Path("/voluntarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Segurizado
	public List<Usuario> getVolunteers(){
		return udao.getAllVolunteers();
	}
	
	// URI: /rest/usuarios/donantes
	@GET
	@Path("/donantes")
	@Produces(MediaType.APPLICATION_JSON)
	@Segurizado
	public List<Usuario> getDonors(){
		return udao.getAllDonors();
	}
	
	 // URI: /rest/usuarios/{id}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response encontrar(@PathParam("id") Long id){
		Usuario usu = udao.getById(id);
		if (usu != null){
			return Response
						.ok()
						.entity(usu)
						.build();
		} else {
			mensaje="No se encontr� el usuario";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response crear(Usuario usuario){
		if(existe(usuario)){
			//falta validar los datos del usuario
			udao.create(usuario);
			return Response.status(Response.Status.CREATED).build();
			//deberia devolver el recurso creado
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	private Boolean existe(Usuario usuario){//DUMMY CHECK. NEEDS TO BE IMPLEMENTED
		if (usuario != null){
			return true;
		} else {
			return false;
		}
	}
	
	//deberia ser usuarios/{id}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response editar(Usuario usuario){
		Usuario aux = udao.getById(usuario.getId());
		if (aux != null){
			udao.update(usuario);
			return Response.ok().entity(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
	                                        .entity("[]").build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	@Segurizado
	public Response borrar(@PathParam("id") Long id){
		Usuario aux = udao.getById(id);
		if (aux != null){
			udao.delete(aux);
			return Response.noContent().build();
		} else {
			mensaje = "El usuario no existe";
			return Response.status(Response.Status.NOT_FOUND)
							.entity(mensaje)
							.build();
		}
	}

}
