package model;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value= {"usuario", "recorrido"})
@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorrido_id")
	private Recorrido recorrido;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String content;

	public Comentario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recorrido getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Recorrido recorrido) {
		this.recorrido = recorrido;
	}

}
