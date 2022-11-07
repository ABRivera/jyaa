package rest.resources;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import daos.*;
import utils.*;
import model.Donacion;
import java.util.*;
import utils.Segurizado;

@Path("/donaciones")
public class DonacionResource {

	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private DonacionDAO dondao = FactoryDAO.getDonacionDAO();
	private String message;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Donacion> getDonaciones(){
		return dondao.getAllUnretired();
	}
	
	// URI: /rest/donaciones/user/2
	@GET
	@Path("/user/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Donacion> getDonacionesByUser(@PathParam("user_id") Long userId){
		//agregar QueryParam para indicar si retiradas o pendientes
		return dondao.getAllByUser(userId, false);
	}
	
	// URI: /rest/donaciones/23
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Long id){
		Donacion don = dondao.getById(id);
		if (don != null){
			return Response
						.ok()
						.entity(don)
						.build();
		} else {
			message="La donaci�n solicitada no se encontr�";
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(message)
					.build();
		}
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response crear(Donacion donacion){
		if(existe(donacion)){
			//falta validar los datos
			dondao.create(donacion);
			return Response.status(Response.Status.CREATED).build();
			//deberia devolver el recurso creado
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	private Boolean existe(Donacion donacion){//DUMMY CHECK. NEEDS TO BE IMPLEMENTED
		if (donacion != null){
			return true;
		} else {
			return false;
		}
	} 

	//Actualizacion parcial �esta bien usar PUT emn vez de PATCH? �Update general o espec�fico(remito, fecha, hora)?
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response editar(Donacion donacion){
		Donacion aux = dondao.getById(donacion.getId());
		if (aux != null){
			dondao.update(donacion);
			return Response.ok().entity(donacion).build();
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
		Donacion aux = dondao.getById(id);
		if (aux != null){
			dondao.delete(aux);
			return Response.noContent().build();
		} else {
			message = "La donacion no existe";
			return Response.status(Response.Status.NOT_FOUND)
							.entity(message)
							.build();
		}
	}

}
