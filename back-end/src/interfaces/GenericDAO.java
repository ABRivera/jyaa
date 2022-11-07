package interfaces;
import java.io.Serializable;
public interface GenericDAO<T>{
	public T update(T entity);
	public T create(T entity);
	public void delete(T entity);
	public T getById(Serializable id);
}
