package daos;
import java.io.Serializable;
import javax.persistence.*;
import utils.EMF;
import interfaces.*;
public abstract class GenericEntityDAO<T> implements GenericDAO<T>{
	protected Class<T> persistentClass;
	
	public GenericEntityDAO() {
	}

	public T update(T entity) {
		EntityManager em= EMF.getEMF().createEntityManager();
		EntityTransaction etx= em.getTransaction();
		etx.begin();
		entity = em.merge(entity);
		etx.commit();
		em.close();
		return entity;
	}
	
	public T create(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		}
		catch (RuntimeException e) {
			if ( tx != null && tx.isActive() ) tx.rollback();
				throw e; // System.out.println("Exception: "+e.getMessage());
			}
		finally {
			em.close();
		}
		return entity;
	}
	
	public void delete(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.contains(entity) ? entity : em.merge(entity));
			//em.remove(entity);
			tx.commit();
		}
		catch (RuntimeException e) {
			if ( tx != null && tx.isActive() ) tx.rollback();
			throw e;
		}
		finally {
			em.close();
		}
	}
	
	public T getById(Serializable id) {
		EntityManager em = EMF.getEMF().createEntityManager();
		return this.findById(em, id);
	}
	
	protected abstract T findById(EntityManager em, Serializable id);
}
