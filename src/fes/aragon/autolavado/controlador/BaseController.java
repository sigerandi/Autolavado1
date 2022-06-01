package fes.aragon.autolavado.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.autolavado.modelo.Autolavado;
import fes.aragon.autolavado.modelo.Clientes;
import fes.aragon.autolavado.modelo.TipoError;

import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BaseController {
	protected boolean modeloValido=true;
	protected boolean telefonoValido=true;
	protected boolean rfcValido=true;
	protected boolean correoValido=true;
	
	protected boolean placasValido=true;
	
	protected boolean tipoValido=true;
	
	/* EXPRESIONES REGULARES
	* 0 palabras sin espacio
	* 1 solo números
	* 2 validar RFC
	* 3 validar correo
	* 4 validar placas
	* 5 validar teléfono
	*/

	private String[] expresiones={"(\\w+)",
	        "(\\d+)(\\.\\d{1,2})",
	        "(\\w){13}",
	        "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	        "(\\w){13}",
			"(\\d){10}"};
	public void nuevaVentana(String archivo) {
		 try {
	            Pane root=(Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/autolavado/fxml/"+archivo+".fxml"));
	            Scene scene =new Scene(root);
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
	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta = new Alert(AlertType.INFORMATION);
		//alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();                                                               
	}
	public void cerrarVentana(Button boton) {
	    Stage stage = (Stage) boton.getScene().getWindow();
	    stage.close();
	}
	public void verificarEntrada(TextField caja,TipoError error) {
	    caja.textProperty().addListener(evento-> {
	        String text = caja.getText();
	        if(text ==null) {
	            text="";
	        }
	        String patron = expresiones[error.ordinal()];
	        Pattern pt = Pattern.compile(patron);
	        Matcher match = pt.matcher(text);
	        caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"),!match.matches ());
	        if(error==TipoError.PALABRAS){
	            this.modeloValido=match.matches();
	        }else if(error==TipoError. NUMEROS) {
	        	this.placasValido=match.matches();
	        }else if(error==TipoError. RFC) {
	        	this.rfcValido=match.matches();
	        }else if(error==TipoError.CORREO) {
	        	this.correoValido=match.matches();
	        }else if(error==TipoError. TELEFONO) {
	        	this.telefonoValido=match.matches();
	        }
	    });
	}
	
	public void abrirArchivo(Button boton) throws IOException, ClassNotFoundException {
		Stage stage = (Stage) boton.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir archivo de datos");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos fes","*.fes"));
		File ruta =fileChooser.showOpenDialog(stage);
		if(ruta!=null) {
			FileInputStream fi=new FileInputStream(ruta);
			ObjectInputStream entrada = new ObjectInputStream(fi);
			ArrayList<AutolavadoArchivo> datos= (ArrayList<AutolavadoArchivo>)entrada.readObject();
			Clientes.getInstancia().getGrupoAutolavados().clear();
			for (AutolavadoArchivo autolavado : datos) {
				Autolavado objeto = new Autolavado();
				objeto.setNombre(autolavado.getNombre());
				objeto.setDireccion(autolavado.getDireccion());
				objeto.setCorreo(autolavado.getCorreo());
				objeto.setTelefono(autolavado.getTelefono());
				objeto.setGerente(autolavado.getGerente());
				objeto.setHabitaciones(FXCollections.observableArrayList(autolavado.getHabitaciones()));
				Clientes.getInstancia().getGrupoAutolavados().add(objeto);
			}
			fi.close();
			entrada.close();
			
		}
	}
	public void guardarArchivo(Button boton) throws IOException {
		Stage stage = (Stage) boton.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos fes", "*.fes"));
		fileChooser.setTitle("Guardar archivo de datos");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta =fileChooser.showSaveDialog(stage);
		if(ruta!=null) {
			FileOutputStream fo=new FileOutputStream(ruta);
			ObjectOutputStream salida=new ObjectOutputStream(fo);
			ArrayList<AutolavadoArchivo> clientes= new ArrayList<>();
			for (Autolavado autolavado : Autolavados.getInstancia().getGrupoHoteles()) {
				AutolavadoArchivo objeto = new AutolavadoArchivo();
				objeto.setNombre(autolavado.getNombre());
				objeto.setDireccion(autolavado.getDireccion());
				objeto.setCorreo(autolavado.getCorreo());
				objeto.setTelefono(autolavado.getTelefono());
				objeto.setGerente(autolavado.getGerente());
				objeto.setHabitaciones(new ArrayList<>(Autolavado.getAutolavado() ()));
				clientes.add(objeto);
			}
			salida.writeObject(clientes);
			salida.close();
			fo.close();
		}
	}
}
