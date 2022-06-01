package fes.aragon.autolavado.controlador;

import static javafx.scene.control.ButtonType.OK;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Clientes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InicioController extends BaseController implements Initializable{

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnSalir;
    
    @FXML
    private Button btnAbrir;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<Autolavado, String> clmModelo;

    @FXML
    private TableColumn<Autolavado, String> clmNombre;
    
    @FXML
    private TableColumn<Autolavado, String> clmPlacas;

    @FXML
    private TableColumn<Autolavado, String> clmRfc;

    @FXML
    private TableColumn<Autolavado, String> clmTelefono;

    @FXML
    private TableView<Autolavado> tblTabla;

    @FXML
    void eliminarAutolavado(ActionEvent event) {
    	int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
    	if (indice>=0) {
    		this.tblTabla.getItems().remove(indice);
    	}
    	else {
    		Alert alerta;
    		alerta=new Alert(AlertType.INFORMATION);
    		alerta.setTitle("Dialogo de aviso");
    		alerta.setHeaderText("Se necesita una fila");
    		alerta.setContentText("Por favor selecciona una fila, para eliminar");
    		Optional<ButtonType> resultado=alerta.showAndWait();
    		if(resultado.get ().equals (OK)) {
    		}
    	}
    }

    @FXML
    void modificarAutolavado(ActionEvent event) {
    	int indice=this.tblTabla.getSelectionModel().getSelectedIndex();
    	if(indice>=0 ) {
    		Clientes.getInstancia().setModificarAutolavado(true);
    		Clientes.getInstancia().setIndice(indice);
    		this.nuevaVentana("Autolavado");
    	}
    	else {
    		Alert alerta;
    		alerta=new Alert(AlertType.INFORMATION);
    		alerta.setTitle("Dialogo de aviso");
    		alerta.setHeaderText("Se necesita una fila");
    		alerta.setContentText("Por favor selecciona una fila, para la modificar");
    		Optional<ButtonType> resultado=alerta.showAndWait();
    		if(resultado.get ().equals (OK)) {
    			
    		}
    	}
    }

    @FXML
    void nuevoAutolavado(ActionEvent event) {
    	Clientes.getInstancia().setIndice(-1);
    	Clientes.getInstancia().setModificarAutolavado(false);
    	Clientes.getInstancia().setIndiceCitas(-1);
    	Clientes.getInstancia().getGrupoAutolavados().add(new Autolavado());
    	this.nuevaVentana("Autolavado");
    }

    @FXML
    void salir(ActionEvent event) {
    	Platform.exit();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.clmModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
		this.clmPlacas.setCellValueFactory(new PropertyValueFactory<>("Placas"));
		this.clmRfc.setCellValueFactory(new PropertyValueFactory<>("Rfc"));
		this.clmTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
		this.tblTabla.setItems(Clientes.getInstancia().getGrupoAutolavados());
	}
	
	
	public void nuevaVentana(String archivo){
    	try {
			Pane root=(Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/autolavado/fxml/"+archivo+".fxml"));
			Scene scene=new Scene(root);
			Stage escenario=new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@FXML
	void abrirArchivo(ActionEvent event) {
		try {
			this.abrirArchivo(btnAbrir);
			this.desabilitar(false);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
	}
    @FXML
    void guardarArchivo(ActionEvent event) {
    	try {
			this.guardarArchivo(btnGuardar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.ventanaEmergente("Mensaje", "Problema en el archivo", "Hay un problema en el archivo, consulta al programador");
			e.printStackTrace();
		}
    }
    private void desabilitar(boolean valor) {
    	this.btnGuardar.setDisable(valor);
    	this.btnModificar.setDisable(valor);
    	this.btnEliminar.setDisable(valor);
    }
}
