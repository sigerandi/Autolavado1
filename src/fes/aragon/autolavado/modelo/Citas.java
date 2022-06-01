package fes.aragon.autolavado.modelo;

public class Citas {
	private String modelo;
	private String placas;
	private String tipo;
	private boolean lavado;
	private boolean cera;
	private boolean interiores;
	
	public Citas() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlacas() {
		return placas;
	}
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isLavado() {
		return lavado;
	}
	public void setLavado(boolean lavado) {
		this.lavado = lavado;
	}
	public boolean isCera() {
		return cera;
	}
	public void setCera(boolean cera) {
		this.cera = cera;
	}
	public boolean isInteriores() {
		return interiores;
	}
	public void setInteriores(boolean interiores) {
		this.interiores = interiores;
	}
	@Override
	public String toString() {
		return "Citas [modelo=" + modelo + ", placas=" + placas + ", tipo=" + tipo + ", lavado=" + lavado + ", cera="
				+ cera + ", interiores=" + interiores + "]";
	}
		
	
}
