package fes.aragon.autolavado.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Clientes;
import fes.aragon.autolavado.modelo.TipoError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AutolavadoController extends BaseController implements Initializable {
	private Autolavado autolavado;
	private String mensajes="";
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnCita;

	@FXML
	private Button btnGerente;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtModelo;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPlacas;

	@FXML
	private TextField txtRfc;

	@FXML
	private TextField txtTelefono;

	@FXML
	void cancelarAutolavado(ActionEvent event) {
		this.cerrarVentana(btnCancelar);

	}

	@FXML
	void nuevaCita(ActionEvent event) {
		this.nuevaVentana("Citas");;

	}

	@FXML
	void nuevoGerente(ActionEvent event) {
		this.nuevaVentana("Gerente");

	}

	@FXML
	void nuevoAutolavado(ActionEvent event) {
		if (this.verificar()) {
			autolavado.setNombre(this.txtNombre.getText());
			autolavado.setDireccion(this.txtDireccion.getText());
			autolavado.setCorreo(this.txtCorreo.getText());
			autolavado.setRfc(this.txtRfc.getText());
			autolavado.setTelefono(this.txtTelefono.getText());
			autolavado.setPlacas(this.txtPlacas.getText());
			autolavado.setModelo(this.txtModelo.getText());
			if(Clientes.getInstancia().isModificarAutolavado()){
				    Clientes.getInstancia().getGrupoAutolavados().set(Clientes.getInstancia().getIndice(),autolavado);
				    Clientes.getInstancia().setIndice(-1);
				    Clientes.getInstancia().setModificarAutolavado(false);
				    Clientes.getInstancia().setIndiceCitas(-1);				
				
			} else {
				Clientes.getInstancia().getGrupoAutolavados()
						.set(Clientes.getInstancia().getGrupoAutolavados().size() - 1, autolavado);
			}

			
			this.cerrarVentana(btnAceptar);
		}else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", mensajes);
			}
		}
		


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.verificarEntrada(txtCorreo,TipoError.CORREO);
		this.verificarEntrada(txtTelefono, TipoError.TELEFONO);
		
			if(Clientes.getInstancia().isModificarAutolavado()){
		        this.autolavado=Clientes.getInstancia().getGrupoAutolavados().get(
		                Clientes.getInstancia().getIndice());
		        this.txtNombre.setText(autolavado.getNombre());
		        this.txtDireccion.setText(autolavado.getDireccion());
		        this.txtCorreo.setText(autolavado.getCorreo());
		        this.txtTelefono.setText(autolavado.getTelefono());
		}else {
			autolavado=Clientes.getInstancia().getGrupoAutolavados().get(
					Clientes.getInstancia().getGrupoAutolavados().size()-1);
			}
		}
	
	

	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtDireccion.getText() == null)
				|| (this.txtDireccion != null && this.txtDireccion.getText().isEmpty())) {
			this.mensajes += "La dirección presentada no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && this.txtCorreo.getText().isEmpty())) {
			this.mensajes += "El correo presentado no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCorreo.getText() == null) || (this.txtCorreo != null && !this.txtCorreo.getText().isEmpty())) {
			if (!this.correoValido) {
				this.mensajes += "El correo presentado no es valido, esta mal estructurado\n";
				valido = false;
			}
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && this.txtTelefono.getText().isEmpty())) {
			this.mensajes += "El teléfono presentado no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtTelefono.getText() == null)
				|| (this.txtTelefono != null && !this.txtTelefono.getText().isEmpty())) {
			if (!this.telefonoValido) {
				this.mensajes += "El teléfono presentado no es valido, minimo=10, maximo=10 números \n";
				valido = false;
			}
		}
		return valido;
	}

}
