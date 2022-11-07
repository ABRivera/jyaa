package model;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value= {"donacion"})
@Entity
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donacion_id")
	private Donacion donacion;
	private String tipo;
	private String marca;
	private int peso_capacidad;
	private int unidades;
	@Temporal(TemporalType.DATE)
	private Date vencimiento;
	
	public Producto() {
	}

	
	public Donacion getDonacion() {
		return donacion;
	}


	public void setDonacion(Donacion donacion) {
		this.donacion = donacion;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getPeso_capacidad() {
		return peso_capacidad;
	}

	public void setPeso_capacidad(int peso_capacidad) {
		this.peso_capacidad = peso_capacidad;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	@Override
	public String toString() {
		return String.format( "%s[tipo=%s, marca=%s, cantidad=%s]", 
		        getClass().getSimpleName(), tipo, marca, unidades );
	}

	
}
