package fes.aragon.autolavado.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Citas;
import fes.aragon.autolavado.modelo.Clientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModificarCitasController extends BaseController implements Initializable {
	private Autolavado autolavado;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnNuevo;

    @FXML
    private TableColumn<Citas, String> clmModelo;

    @FXML
    private TableColumn<Citas, String> clmPlacas;

    @FXML
    private TableColumn<Citas, Boolean> clmServicio;

    @FXML
    private TableColumn<Citas, String> clmTipo;

    @FXML
    private TableView<Citas> tblCitas;

    @FXML
    void cancelarCita(ActionEvent event) {
    	this.cerrarVentana(btnCancelar);
    }

    @FXML
    void modificarCita(ActionEvent event) {
    	int indice=this.tblCitas.getSelectionModel().getSelectedIndex();
    	if(indice>=0) {
    		
    		Clientes.getInstancia().setIndiceCitas(indice);
    		this.nuevaVentana("Citas");
    	}
    	else {
    		this.ventanaEmergente("Informacion", "Seleccionar una fila", "Por favor");
    	}
    }

    @FXML
    void nuevaCita(ActionEvent event) {
    	Clientes.getInstancia().setIndiceCitas(-1);
    	this.nuevaVentana("Citas");
    }

  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.clmModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
		this.clmPlacas.setCellValueFactory (new PropertyValueFactory<>("Placas"));
		this.clmServicio.setCellValueFactory(new PropertyValueFactory<>("refrigerador"));
		this.clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		this.autolavado=Clientes.getInstancia().getGrupoAutolavados().get(
				Clientes.getInstancia().getIndice());
		this.tblCitas.setItems(autolavado.getCita());
		
	}
	}


