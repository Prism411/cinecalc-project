package application;
	
import entities.MRUCalculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/gui/mainMenu.fxml"));
			Scene scene = new Scene(root, 1280, 720); // Definir a resolução desejada
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setScene(scene);
		    primaryStage.setResizable(false);
		    primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MRUCalculator mru = new MRUCalculator(0.0,0.0,0.0,0.0,0.0,0.0,0.0,"s","m/s","m");
		mru.setFinalVelocity(3.0);
		mru.setA(5.0);
		mru.setInitialVelocity(2.0);
		System.out.println(mru.calculaDados());
		launch(args);

		
	}
}
