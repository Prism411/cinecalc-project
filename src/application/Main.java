package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Classe principal que inicia a aplicação JavaFX.
 * A aplicação carrega a interface gráfica do usuário (GUI) a partir do arquivo FXML e exibe a janela principal.
 * O tamanho da janela é definido como 1280x720 e a aplicação não é redimensionável.
 * 
 * @author [Jáder Louis, Nicolas Sales, Wyllgner França]
 * @version dev-mode
 */
public class Main extends Application {

    /**
     * Método principal que inicia a aplicação JavaFX.
     * 
     * @param primaryStage O estágio principal da aplicação.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/gui/mainMenu.fxml"));
            Scene scene = new Scene(root, 1280, 720); // Definir a resolução desejada
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal da aplicação.
     * 
     * @param args Os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
