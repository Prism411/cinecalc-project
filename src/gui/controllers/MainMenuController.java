package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

	@FXML
	private Button mruButton;

	@FXML
	private Button twoDButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Adicionando manipuladores de eventos para os botões
        mruButton.setOnAction(this::openMRUWindow);
        twoDButton.setOnAction(this::openTwoDWindow);
    }

    // Método para abrir a janela correspondente ao Movimento Retilíneo Uniforme (MRU)
    private void openMRUWindow(ActionEvent event) {
        openNewWindow("/gui/MRU.fxml", "Movimento Retilíneo Uniforme (MRU)");
    }

    
    // Método para abrir a janela correspondente ao Movimento em Duas Dimensões (2D)
    private void openTwoDWindow(ActionEvent event) {
        openNewWindow("/gui/TwoD.fxml", "2D (Movimento em Duas Dimensões)");
    }

    // Método genérico para abrir novas janelas
    private void openNewWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
