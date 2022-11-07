package daos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Producto;
import utils.EMF;
import interfaces.*;
public class ProductoDAO extends GenericEntityDAO<Producto> implements IProductoDAO {
	@Override
	protected Producto findById(EntityManager em, Serializable id) {
		Producto producto = em.find(Producto.class, id);
		return producto;
	}
	
	public List<Producto> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Producto e");
		List<Producto> result = (List<Producto>)stmt.getResultList();
		return result;
	}
}
