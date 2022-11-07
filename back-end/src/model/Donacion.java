package model;
import java.util.*;
import java.sql.Time;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"usuario", "en_recorrido"})
@Entity
public class Donacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorrido_id")
	private Recorrido en_recorrido;
	private String sucursal;
	//fecha a partir de la cual se puede retirar
	@Temporal(TemporalType.DATE)
	private Date fechaRetiro;
	private Time initHour;
	private Time endHour;
	private String address;
	private String coordenadas;
	private Boolean is_retired;
	private Long numeroRemito;
	//fecha en la que fue retirada
	@Temporal(TemporalType.DATE)
	private Date fechaRetirada;
	private Time horaRetiro;
	@OneToMany(mappedBy="donacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Producto> productos = new ArrayList<Producto>();

	public Donacion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Recorrido getEn_recorrido() {
		return en_recorrido;
	}

	public void setEn_recorrido(Recorrido en_recorrido) {
		this.en_recorrido = en_recorrido;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Time getInitHour() {
		return initHour;
	}

	public void setInitHour(Time initHour) {
		this.initHour = initHour;
	}

	public Time getEndHour() {
		return endHour;
	}

	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public Boolean getIs_retired() {
		return is_retired;
	}

	public void setIs_retired(Boolean is_retired) {
		this.is_retired = is_retired;
	}

	public Long getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(Long numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public Time getHoraRetiro() {
		return horaRetiro;
	}

	public void setHoraRetiro(Time horaRetiro) {
		this.horaRetiro = horaRetiro;
	}

	public Collection<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProduct (Producto producto) {
		this.productos.add(producto);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaRetirada() {
		return fechaRetirada;
	}

	public void setFechaRetirada(Date fechaRetirada) {
		this.fechaRetirada = fechaRetirada;
	}
	
	@Override
	public String toString() {
		return String.format( "%s[sucursal=%s, direccion=%s, fecha retiro=%s]", 
		        getClass().getSimpleName(), sucursal, address, fechaRetiro );
	}

}
