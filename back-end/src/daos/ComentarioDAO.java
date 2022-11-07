package daos;
import java.io.Serializable;
import interfaces.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Comentario;
import utils.EMF;
public class ComentarioDAO extends GenericEntityDAO<Comentario> implements IComentarioDAO {
	@Override
	protected Comentario findById(EntityManager em, Serializable id) {
		Comentario comentario = em.find(Comentario.class, id);
		return comentario;
	}
	
	public List<Comentario> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Comentario e");
		List<Comentario> result = (List<Comentario>)stmt.getResultList();
		return result;
	}
}
