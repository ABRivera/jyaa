package daos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import interfaces.*;
import model.Rol;
import utils.EMF;
public class RolDAO extends GenericEntityDAO<Rol> implements IRolDAO {
	@Override
	protected Rol findById(EntityManager em, Serializable id) {
		Rol rol = em.find(Rol.class, id);
		return rol;
	}
	
	public List<Rol> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Rol e");
		List<Rol> result = (List<Rol>)stmt.getResultList();
		return result;
	}
}
