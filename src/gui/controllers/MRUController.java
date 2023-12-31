package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.MRUCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Controlador para a interface gráfica do usuário (GUI) relacionada ao Movimento Retilíneo Uniforme (MRU).
 * Este controlador lida com a entrada do usuário, chama a classe `MRUCalculator` para realizar cálculos e exibe os resultados na GUI.
 * As unidades de medida para tempo, espaço e velocidade são configuradas por meio de ComboBoxes.
 *
 * @author [jader louis]
 * @version dev-mode
 */
public class MRUController implements Initializable {
	 /**
     * Inicializa o controlador, preenchendo ComboBoxes com unidades de medida para tempo, espaço e velocidade.
     *
     * @param url             O local relativo ao objeto de código do controlador.
     * @param resourceBundle Os recursos a serem usados para localizar o objeto de código do controlador.
     */
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	   // Populate time units ComboBox
	   ObservableList<String> timeUnits = FXCollections.observableArrayList("Second", "Hour", "Minute");
	   timeUni.setItems(timeUnits);

	   // Populate velocity units ComboBox
	   ObservableList<String> velocityUnits = FXCollections.observableArrayList("m/s", "km/h", "foot/s", "mph");
	   velUni.setItems(velocityUnits);

	   // Populate space units ComboBox
	   ObservableList<String> spaceUnits = FXCollections.observableArrayList("m", "km", "foot", "yd", "miles");
	   spaceUni.setItems(spaceUnits);
	    }

    @FXML
    private TextField velocityInitialField;

    @FXML
    private TextField velocityFinalField;

    @FXML
    private TextField positionInitialField;

    @FXML
    private TextField positionFinalField;

    @FXML
    private TextField timeField;

    @FXML
    private Label resultLabel;
    
    @FXML
    private TextField acel;
    
    @FXML
    private ComboBox<String> timeUni;
    @FXML
    private ComboBox<String> velUni;
    @FXML
    private ComboBox<String> spaceUni;
    /**
     * Manipula o evento de clique no botão de cálculo, obtendo os valores dos campos de entrada, realizando cálculos e exibindo resultados.
     * Se as unidades de medida não forem selecionadas, exibe um alerta de erro.
     *
     * @param event O evento de clique no botão.
     */
    @FXML
    private void handleCalculate(ActionEvent event) {
        // Obtenha os valores dos campos de texto
        String v0Text = velocityInitialField.getText();
        String vText = velocityFinalField.getText();
        String s0Text = positionInitialField.getText();
        String sText = positionFinalField.getText();
        String tText = timeField.getText();
        String aText = acel.getText();

        String selectedSpaceUnit = spaceUni.getValue();
        String selectedVelocityUnit = velUni.getValue();
        String selectedTimeUnit = timeUni.getValue();
        if (selectedSpaceUnit == null || selectedVelocityUnit == null || selectedTimeUnit == null) {
            // Pelo menos uma das variáveis é nula, exibir alerta
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("ERRO 01!");
            alert.setContentText("Por favor, selecione todas as unidades de medida!.");
            alert.showAndWait();
        } else {

            // Verifique quais valores foram fornecidos pelo usuário
            boolean hasV0 = !v0Text.isEmpty();
            boolean hasV = !vText.isEmpty();
            boolean hasS0 = !s0Text.isEmpty();
            boolean hasS = !sText.isEmpty();
            boolean hasT = !tText.isEmpty();
            boolean hasA = !aText.isEmpty();

            try {
                // Converta as strings para valores double
                double v0 = hasV0 ? Double.parseDouble(v0Text) : 0.0;
                double v = hasV ? Double.parseDouble(vText) : 0.0;
                double s0 = hasS0 ? Double.parseDouble(s0Text) : 0.0;
                double s = hasS ? Double.parseDouble(sText) : 0.0;
                double t = hasT ? Double.parseDouble(tText) : 0.0;
                double a = hasA ? Double.parseDouble(aText) : 0.0;

                MRUCalculator mru = new MRUCalculator(v0, v, s0, 0.0, s, t, a, selectedTimeUnit, selectedVelocityUnit, selectedSpaceUnit);
                resultLabel.setText(mru.calculaDados());
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Calculado");
                alert.setHeaderText("Tudo Ocorreu Normalmente!");
                alert.setContentText("os resultados estão na caixa azul!");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                resultLabel.setText("Erro: Insira valores válidos em todos os campos.");
                e.printStackTrace();
            
        
    
    }
        }
    }
}

    
         
         
         




