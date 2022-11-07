package utils;
import javax.persistence.*;
public class EMF {
	
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("caridadUP");

	public EMF() {
	}
	
	public static EntityManagerFactory getEMF() {
		return emf;
	}
}
