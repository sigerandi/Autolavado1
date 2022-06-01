 package fes.aragon.autolavado.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Clientes;
import fes.aragon.autolavado.modelo.Gerente;

import fes.aragon.autolavado.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GerenteController extends BaseController implements Initializable  {
	
	private Autolavado autolavado;
	private String mensajes="";
	
    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRfc;

    @FXML
    private TextField txtTelefono;

    @FXML
    void cancelarGerente(ActionEvent event) {
    	this.cerrarVentana(btnCancelar);

    }

    @FXML
    void nuevoGerente(ActionEvent event) {
    	//Autolavado autolavado = Autolavado.getAutolavado();
    	//Gerente gerente = new Gerente();
    	if(this.verificar()) {
    	autolavado.getGerente().setNombre(this.txtNombre.getText());
    	autolavado.getGerente().setTelefono(this.txtTelefono.getText());
    	autolavado.getGerente().setRfc(this.txtRfc.getText());
    	autolavado.getGerente().setId(this.txtId.getText());
    	//autolavado.setGerente(gerente);
    	System.out.println(autolavado.getGerente());
    	this.cerrarVentana(btnAceptar);
    } else {
		this.ventanaEmergente("Mensaje","Datos no correctos", mensajes);
		this.mensajes="";	
		}
    }
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		this.verificarEntrada(txtRfc, TipoError.RFC);
		//this.verificarEntrada(txtId, TipoError.Id);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		
		if (Clientes.getInstancia().isModificarAutolavado()) {
			this.autolavado = Clientes.getInstancia().getGrupoAutolavados().get(Clientes.getInstancia().getIndice());
			this.txtNombre.setText(autolavado.getGerente().getNombre());
			
			this.txtRfc.setText(autolavado.getGerente().getRfc());
			this.txtId.setText(autolavado.getGerente().getId());
			this.txtTelefono.setText(autolavado.getGerente().getTelefono());
		} else {
			autolavado = Clientes.getInstancia().getGrupoAutolavados().get(Clientes.getInstancia().getGrupoAutolavados().size() - 1);
		}
	}

	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre del gerente no es valido, es vacio\n";
			valido = false;
		}
		
		if ((this.txtRfc.getText() == null) || (this.txtRfc != null && this.txtRfc.getText().isEmpty())) {
			this.mensajes += "El RFC del gerente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtRfc.getText() == null) || (this.txtRfc != null && !this.txtRfc.getText().isEmpty())) {
			if (!this.rfcValido) {
				this.mensajes += "El RFC del Gerente no es valido, minimo 13, maximo 13 caracteres\n";
				valido = false;
			}
		}
		if ((this.txtId.getText() == null) || (this.txtId != null && this.txtId.getText().isEmpty())) {
			this.mensajes += "El correo del gerente no es valido, es vacio\n";
			valido = false;
		}
		
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El teléfono del gerente no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && !this.txtTelefono.getText().isEmpty())) {
			if (!this.telefonoValido) {
				this.mensajes += "El teléfono del gerente no es valido, minimo=10, maximo=10 números \n";
				valido = false;
			}
		}
		return valido;
	}

}
