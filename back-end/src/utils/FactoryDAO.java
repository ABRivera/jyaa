package utils;
import daos.*;
public class FactoryDAO {
	public FactoryDAO() {
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAO();
	}

	public static ComentarioDAO getComentarioDAO() {
		return new ComentarioDAO();
	}
	
	public static DonacionDAO getDonacionDAO() {
		return new DonacionDAO();
	}
	
	public static ProductoDAO getProductoDAO() {
		return new ProductoDAO();
	}
	
	public static RecorridoDAO getRecorridoDAO() {
		return new RecorridoDAO();
	}
	
	public static RolDAO getRolDAO() {
		return new RolDAO();
	}
}
