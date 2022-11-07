package rest.resources;
import javax.persistence.RollbackException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import daos.*;
import utils.*;
import model.Donacion;
import model.Recorrido;
import rest.resources.DonacionResource;
import java.util.*;
import utils.Segurizado;

@Path("/recorridos")
public class RecorridoResource {

	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;

	private RecorridoDAO rdao = FactoryDAO.getRecorridoDAO();
	private String message;
	
	
	// URI: /rest/recorridos/
	//retorna todos los recorridos(realizados y no), ordenados por fecha descendiente (mas reciente primero)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recorrido> getRecorridosSortedByDate(){
		return rdao.getAllSortedByDate();
	}
	
	//get by date
	//MAPAS -GUARDAR COORDINADAS-
	

	// URI: /rest/recorridos/23
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Long id){
		Recorrido rec = rdao.getById(id);
		if (rec != null){
			return Response
						.ok()
						.entity(rec)
						.build();
		} else {
			message="El recorrido solicitado no se encontr�";
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
	public Response crear(Recorrido recorrido){
		if(existe(recorrido)){
			//falta validar los datos
			rdao.create(recorrido);
			return Response.status(Response.Status.CREATED).build();
			//deberia devolver el recurso creado
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
	
	private Boolean existe(Recorrido recorrido){//DUMMY CHECK. NEEDS TO BE IMPLEMENTED
		if (recorrido != null){
			return true;
		} else {
			return false;
		}
	}
	

	//recorridos/{id}
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Segurizado
	public Response editar(Recorrido recorrido, @PathParam("id") Long id){
		Recorrido aux = rdao.getById(id);
		if (aux != null){
			rdao.update(recorrido);
			return Response.ok().entity(recorrido).build();
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
		Recorrido aux = rdao.getById(id);
		if (aux != null){
		//probablemente deba quitarse la clave en las donaciones primero
			rdao.delete(aux);
			return Response.noContent().build();
		} else {
			message = "El recorrido no existe";
			return Response.status(Response.Status.NOT_FOUND)
							.entity(message)
							.build();
		}
	}
	
}
