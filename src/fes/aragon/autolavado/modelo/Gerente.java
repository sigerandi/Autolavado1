package fes.aragon.autolavado.modelo;

public class Gerente {
	private String nombre;
	private String telefono;
	private String rfc;
	private String id;
	public Gerente() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Gerente [nombre=" + nombre + ", telefono=" + telefono + ", rfc=" + rfc + ", id=" + id + "]";
	}
	

}
