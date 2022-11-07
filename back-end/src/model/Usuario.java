package model;
import java.sql.Time;
import javax.persistence.*;
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String name;
	private String domicilio;
	private String telefono;
	private String empresa;
	private Time horaContactoIni;
	private Time horaContactoFin;
	@ManyToOne
	private Rol rol;
	//private Collection<Donacion> donaciones = new ArrayList<Donacion>();
	//private Collection<Recorrido> recorridos = new ArrayList<Recorrido>();

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getHoraContactoIni() {
		return horaContactoIni;
	}

	public void setHoraContactoIni(Time horaContactoIni) {
		this.horaContactoIni = horaContactoIni;
	}

	public Time getHoraContactoFin() {
		return horaContactoFin;
	}

	public void setHoraContactoFin(Time horaContactoFin) {
		this.horaContactoFin = horaContactoFin;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	

/*	public Collection<Donacion> getDonaciones() {
		return donaciones;
	}

	public void setDonaciones(Collection<Donacion> donaciones) {
		this.donaciones = donaciones;
	}

	public Collection<Recorrido> getRecorridos() {
		return recorridos;
	}

	public void setRecorridos(Collection<Recorrido> recorridos) {
		this.recorridos = recorridos;
	}
*/

}
