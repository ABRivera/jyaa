package daos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Donacion;
import utils.EMF;
import interfaces.*;

public class DonacionDAO extends GenericEntityDAO<Donacion> implements IDonacionDAO{
	@Override
	protected Donacion findById(EntityManager em, Serializable id) {
		Donacion donacion = em.find(Donacion.class, id);
		return donacion;
	}
	
	public List<Donacion> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Donacion e");
		List<Donacion> result = (List<Donacion>)stmt.getResultList();
		return result;
	}
	
	public List<Donacion> getAllUnretired() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Donacion e WHERE is_retired = false");
		List<Donacion> result = (List<Donacion>)stmt.getResultList();
		return result;
	}
	
	public List<Donacion> getAllWithoutRouteAsigned() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Donacion e WHERE en_recorrido IS NULL");
		List<Donacion> result = (List<Donacion>)stmt.getResultList();
		return result;
	}
	
	public List<Donacion> getAllFromRoute(Long route_id) {
		String query = "SELECT e FROM Donacion e WHERE en_recorrido = :routeId";
		TypedQuery<Donacion> stmt= EMF.getEMF().createEntityManager().createQuery(query, Donacion.class);
		stmt.setParameter("routeId", route_id);
		List<Donacion> result = (List<Donacion>)stmt.getResultList();
		return result;
	}

	
	public List<Donacion> getAllByUser(Long user_id, Boolean retired) {
		String query = "SELECT e FROM Donacion e WHERE usuario_id = :user_id AND is_retired = :retired";
		TypedQuery<Donacion> stmt = EMF.getEMF().createEntityManager().createQuery(query, Donacion.class);
		stmt.setParameter("user_id", user_id);
		stmt.setParameter("retired", retired);
		List<Donacion> result = (List<Donacion>)stmt.getResultList();
		return result;
	}
}
