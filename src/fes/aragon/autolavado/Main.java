package fes.aragon.autolavado;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/fes/aragon/autolavado/fxml/Inicio.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/fes/aragon/autolavado/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void setRoom(String fxml) {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
