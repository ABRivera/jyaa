package daos;
import java.io.Serializable;
import java.util.List;
import utils.EMF;
import utils.FactoryDAO;

import javax.persistence.*;

import model.Comentario;
import model.Donacion;
import model.Recorrido;
import interfaces.*;
public class RecorridoDAO extends GenericEntityDAO<Recorrido> implements IRecorridoDAO{
	@Override
	protected Recorrido findById(EntityManager em, Serializable id) {
		Recorrido recorrido = em.find(Recorrido.class, id);
		return recorrido;
	}
	
	public List<Recorrido> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Recorrido e");
		List<Recorrido> result = (List<Recorrido>)stmt.getResultList();
		return result;
	}
	
	public List<Recorrido> getAllSortedByDate() {
		String query = "SELECT e FROM Recorrido e ORDER BY date DESC";
		TypedQuery<Recorrido> stmt = EMF.getEMF().createEntityManager().createQuery(query, Recorrido.class);
		List<Recorrido> result = (List<Recorrido>)stmt.getResultList();
		return result;
	}
	
	public List<Donacion> getRouteDonations(Long route_id) {
		DonacionDAO donacionDao = FactoryDAO.getDonacionDAO();
		List<Donacion> donations = donacionDao.getAllFromRoute(route_id);
		return donations;
	}
	
	public Recorrido addCommentToRoute(Long route_id, Comentario comentario) {
		ComentarioDAO comentarioDao = FactoryDAO.getComentarioDAO();
		Recorrido recorrido = this.getById(route_id);
		comentario.setRecorrido(recorrido);
		comentarioDao.create(comentario);
		return recorrido;
	}
	
	public List<Comentario> getRouteComments(Long route_id){
		String query = "SELECT e FROM Comentario e WHERE recorrido = :routeId";
		TypedQuery<Comentario> stmt= EMF.getEMF().createEntityManager().createQuery(query, Comentario.class);
		stmt.setParameter("routeId", route_id);
		List<Comentario> result = (List<Comentario>)stmt.getResultList();
		return result;
	}
	
	
}
