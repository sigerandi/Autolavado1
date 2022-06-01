package fes.aragon.autolavado.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Autolavado {
	private Gerente gerente=new Gerente();
	private ObservableList <Citas> cita = FXCollections.observableArrayList();
	private String nombre;
	private String direccion;
	private String rfc;
	private String telefono;
	private String placas;
	private String correo;
	private String modelo;
	
	
	private static Autolavado instancia = new Autolavado();
	
	public Autolavado() {
		
	}
	public static Autolavado getAutolavado() {
		return instancia;
	}
	
	
	
	public ObservableList<Citas> getCita() {
		return cita;
	}
	public Gerente getGerente() {
		return gerente;
	}
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPlacas() {
		return placas;
	}
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@Override
	public String toString() {
		return "Autolavado [gerente=" + gerente + ", cita=" + cita + ", nombre=" + nombre
				+ ", direccion=" + direccion + ", rfc=" + rfc + ", telefono=" + telefono + ", placas=" + placas
				+ ", citas=" + cita + ", correo=" + correo + ", modelo=" + modelo + "]";
	}
	
}
