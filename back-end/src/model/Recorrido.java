package model;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Recorrido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date date;
	@OneToMany (mappedBy="en_recorrido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Donacion> donaciones = new ArrayList<Donacion>();
	@OneToMany (mappedBy="recorrido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Comentario> comentarios = new ArrayList<Comentario>();

	public Recorrido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void addComment (Comentario comentario) {
		this.comentarios.add(comentario);
	}
	
	public void addDonacion (Donacion donacion) {
		this.donaciones.add(donacion);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collection<Donacion> getDonaciones() {
		return donaciones;
	}

	public void setDonaciones(Collection<Donacion> donaciones) {
		this.donaciones = donaciones;
	}

	public Collection<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
}
