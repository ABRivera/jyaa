package daos;
import java.io.Serializable;
import java.util.List;
import utils.EMF;
import javax.persistence.*;

import model.Usuario;
import interfaces.*;
public class UsuarioDAO extends GenericEntityDAO<Usuario> implements IUsuarioDAO {
	public UsuarioDAO() {
	}
	
	@Override
	protected Usuario findById(EntityManager em, Serializable id) {
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}
	
	public Usuario findUserFromMail(String email) {
		String query = "SELECT e FROM Usuario e WHERE email = :email";
		TypedQuery<Usuario> stmt = EMF.getEMF().createEntityManager().createQuery(query, Usuario.class);
		stmt.setParameter("email", email);
		try {
			Usuario result = stmt.getSingleResult(); 
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//findbymail
	
	public List<Usuario> getAll() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Usuario e");
		List<Usuario> result = (List<Usuario>)stmt.getResultList();
		return result;
	}
	
	public List<Usuario> getAllDonors() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Usuario e WHERE rol_id = 2");
		List<Usuario> result = (List<Usuario>)stmt.getResultList();
		return result;
	}
	
	public List<Usuario> getAllVolunteers() {
		Query stmt= EMF.getEMF().createEntityManager().createQuery("SELECT e FROM Usuario e WHERE rol_id = 1");
		List<Usuario> result = (List<Usuario>)stmt.getResultList();
		return result;
	}
	
	//Si la combinacion email-pass es valida, retorna un usuario. Sino retorna NULL
	public Usuario login(String email, String password) {
		String query = "SELECT e FROM Usuario e WHERE email = :email AND password = :password";
		TypedQuery<Usuario> stmt = EMF.getEMF().createEntityManager().createQuery(query, Usuario.class);
		stmt.setParameter("email", email);
		stmt.setParameter("password", password);
		try {
			Usuario result = stmt.getSingleResult(); 
			return result;
		} catch (NoResultException e) {
			return null;
		}		
	}
}
