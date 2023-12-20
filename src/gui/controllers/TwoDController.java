package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import entities.Calc2D;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para a interface gráfica de uma aplicação de cálculos bidimensionais.
 *
 * @version dev-mode
 * @since 2023-12-20
 * @author [Jáder Louis, Nicolas Sales, Wyllgner França]
 */
public class TwoDController implements Initializable {

    @FXML
    private TextField velocityInitialField;

    @FXML
    private TextField velocityFinalField;

    @FXML
    private TextField initialVerticalVelocityField;

    @FXML
    private TextField finalVerticalVelocityField;

    @FXML
    private TextField HorizontalVelocity;

    @FXML
    private TextField maxHeightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField alphaField;

    @FXML
    private TextField rangeField;

    @FXML
    private TextField timeField;

    @FXML
    private Label resultLabel;

    @FXML
    private Button calcButton;

    @FXML
    private ComboBox<String> timeUni;

    @FXML
    private ComboBox<String> velUni;

    @FXML
    private ComboBox<String> spaceUni;

    /**
     * Inicializa o controlador, preenchendo ComboBoxes com unidades de medida para tempo, espaço e velocidade.
     *
     * @param url             O local relativo ao objeto de código do controlador.
     * @param resourceBundle Os recursos a serem usados para localizar o objeto de código do controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> timeUnits = FXCollections.observableArrayList("Second", "Hour", "Minute");
        timeUni.setItems(timeUnits);

        ObservableList<String> velocityUnits = FXCollections.observableArrayList("m/s", "km/h", "foot/s", "mph");
        velUni.setItems(velocityUnits);

        ObservableList<String> spaceUnits = FXCollections.observableArrayList("m", "km", "foot", "yd", "miles");
        spaceUni.setItems(spaceUnits);
    }

    /**
     * Manipula o evento de clique no botão de cálculo.
     * Realiza a leitura dos campos de texto, converte os valores, e realiza os cálculos bidimensionais.
     * Exibe o resultado no rótulo de resultado.
     */
    @FXML
    private void handleCalculate() {
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
            try {
                // Leitura dos valores
                double v0 = parseTextField(velocityInitialField.getText(), 0.01);
                double v = parseTextField(velocityFinalField.getText(), 0.01);
                double initialVerticalVelocity = parseTextField(initialVerticalVelocityField.getText(), 0.01);
                double finalVerticalVelocity = parseTextField(finalVerticalVelocityField.getText(), 0.01);
                double horizontalVelocity = parseTextField(HorizontalVelocity.getText(), 0.01);
                double maxHeight = parseTextField(maxHeightField.getText(), 0.01);
                double height = parseTextField(heightField.getText(), 0.01);
                double alpha = parseTextField(alphaField.getText(), 0.01);
                double range = parseTextField(rangeField.getText(), 0.01);
                double t = parseTextField(timeField.getText(), 0.01);

                // Criação e utilização do objeto de cálculos bidimensionais
                Calc2D calc = new Calc2D(v, horizontalVelocity, initialVerticalVelocity, finalVerticalVelocity, v0, 9.81, t, height, maxHeight, alpha, range, selectedTimeUnit, selectedSpaceUnit, selectedVelocityUnit);
                resultLabel.setText(calc.calculate());
            } catch (NumberFormatException e) {
                // Lida com exceção se a conversão falhar
                System.out.println("Erro ao converter valores: " + e.getMessage());
            }
        }
    }

    /**
     * Converte o texto de um campo de texto em um valor numérico.
     * Se a conversão falhar, retorna um valor padrão.
     *
     * @param text          O texto a ser convertido.
     * @param defaultValue  O valor padrão a ser retornado em caso de falha na conversão.
     * @return              O valor numérico convertido ou o valor padrão se a conversão falhar.
     */
    private double parseTextField(String text, double defaultValue) {
        if (text == null || text.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor: " + text);
            return defaultValue;
        }
    }
}
