package fes.aragon.autolavado.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Citas;
import fes.aragon.autolavado.modelo.Clientes;
import fes.aragon.autolavado.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CitaController extends BaseController implements Initializable {
	private Autolavado autolavado;
	private String mensajes = "";
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ChoiceBox<String> chcTipo;

    @FXML
    private CheckBox chkCera;

    @FXML
    private CheckBox chkInteriores;

    @FXML
    private CheckBox chkLavado;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtPlacas;

    @FXML
    void cancelarCita(ActionEvent event) {
    	this.cerrarVentana(btnCancelar);

    }

    @FXML
    void nuevaCita(ActionEvent event) {
    	/*Autolavado autolavado = Autolavado.getAutolavado();
    	Citas cita = new Citas();
    	cita.setModelo(this.txtModelo.getText());
    	cita.setModelo(this.txtModelo.getText());
    	cita.setPlacas(this.txtPlacas.getText());
    	cita.setTipo(this.chcTipo.getValue());
    	cita.setLavado(this.chkLavado.isSelected());
    	cita.setCera(this.chkCera.isSelected());
    	cita.setInteriores(this.chkInteriores.isSelected());
    	
    	autolavado.getCita().add(cita);*/
    	
    	Citas cita = new Citas();
    	if (this.verificar()) {
    	cita.setModelo(this.txtModelo.getText());
    	cita.setPlacas(this.txtPlacas.getText());
    	cita.setTipo(this.chcTipo.getValue());
    	cita.setLavado(this.chkLavado.isSelected());
    	cita.setCera(this.chkCera.isSelected());
    	cita.setInteriores(this.chkInteriores.isSelected());
    	autolavado.getCita().add(cita);
    	System.out.println(autolavado.getCita().get(autolavado.getCita().size()-1));
    	this.cerrarVentana(btnAceptar);
    	}else {
    		this.ventanaEmergente("Mensaje","Datos no correctos", mensajes);
    		this.mensajes="";}
    }
    	
    	

    
    @Override	
    public void initialize(URL arg0, ResourceBundle arg1) {
		this.chcTipo.getItems().addAll("Carro","Camioneta mediana","Camioneta grande");
		
		this.verificarEntrada(txtModelo, TipoError.NUMEROS);
		
		if (Clientes.getInstancia().isModificarAutolavado()) {
			this.autolavado = Clientes.getInstancia().getGrupoAutolavados().get(Clientes.getInstancia().getIndice());
			this.txtModelo.setText(autolavado.getModelo());
		    this.txtPlacas.setText(autolavado.getPlacas());
		}else {
			autolavado = Clientes.getInstancia().getGrupoAutolavados().get(Clientes.getInstancia().getGrupoAutolavados().size() - 1);
		}
		
		
	}

 
    	private boolean verificar() {
		// TODO Auto-generated method stub
		boolean valido = true;
		if ((this.txtModelo.getText() == null)
				|| (this.txtModelo.getText() != null && this.txtModelo.getText().isEmpty())) {
			this.mensajes += "El modelo no es valido, esta vacio\n";
			valido = false;
		}
		
		return valido;
	}

	
	
	
}
