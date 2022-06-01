package fes.aragon.autolavado.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Clientes {
	private static Clientes instancia = new Clientes();
	
	private ObservableList<Autolavado> grupoAutolavados = FXCollections.observableArrayList();
	private boolean modificarAutolavado = false;
	private int indice = -1;
	private int indiceCitas = -1;
	
	private Clientes() {
		/*
		Autolavado a = new Autolavado(); 
		a.setNombre("Oscar Joaquin Villa Garcia");
		a.setTelefono("55 3475 1741");
		a.setPlacas("775 ZFH");
		a.setModelo("Lincoln MKZ");
		a.setRfc("VIGO000929V19");
		
		Gerente g = new Gerente();
		g.setNombre("Erandi Soublett Martínez");
		g.setTelefono("5511438797");
		g.setRfc("15621532");
		g.setId("Nose xdd");
		a.setGerente(g);
		
		Citas c = new Citas();
		c.setModelo("SEpa x2");
		c.setPlacas("225d5d5fe");
		c.setTipo("Carro");
		c.setLavado(true);
		c.setInteriores(true);
		c.setCera(false);
		a.getCita().add(c);
		
		this.grupoAutolavados.add(a);
		*/
	
		
		
	}
	public static Clientes getInstancia() {
		return instancia;
	}
	public boolean isModificarAutolavado() {
		return modificarAutolavado;
	}
	public void setModificarAutolavado(boolean modificarAutolavado) {
		this.modificarAutolavado = modificarAutolavado;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getIndiceCitas() {
		return indiceCitas;
	}
	public void setIndiceCitas(int indiceCitas) {
		this.indiceCitas = indiceCitas;
	}
	public ObservableList<Autolavado> getGrupoAutolavados() {
		return grupoAutolavados;
	}
	
	public void setGrupoAutolavados(ObservableList<Autolavado> grupoAutolavados) {
		this.grupoAutolavados = grupoAutolavados;
	

}}
