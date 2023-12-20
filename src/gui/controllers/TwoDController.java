package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Calc2D;
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

public class TwoDController implements Initializable {
	
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
	    
   @FXML
    private void handleCalculate() {
	   System.out.println("handleCalculate Ativado!");
	   String selectedSpaceUnit = spaceUni.getValue();
       String selectedVelocityUnit = velUni.getValue();
   		String selectedTimeUnit = timeUni.getValue();
   	if (selectedSpaceUnit == null || selectedVelocityUnit == null || selectedTimeUnit == null) {
   	    // Pelo menos uma das variáveis é nula, exibir alerta
   	    Alert alert = new Alert(AlertType.ERROR);
   	    alert.setTitle("Erro");
   	    alert.setHeaderText("ERRO 01!");
   	    alert.setContentText("Por favor, selecione todas as unidades de medida!.");
   	    alert.showAndWait();}else {
	    try {
	        // Read values from text fields
	        double v0 = parseTextField(velocityInitialField.getText(), 0.01);
	        double v = parseTextField(velocityFinalField.getText(), 0.01);
	        double initialVerticalVelocity = parseTextField(initialVerticalVelocityField.getText(), 0.01);
	        double finalVerticalVelocity = parseTextField(finalVerticalVelocityField.getText(), 0.01);
	        double g =  9.81;
	        double horizontalVelocity = parseTextField(HorizontalVelocity.getText(), 0.01);
	        double maxHeight = parseTextField(maxHeightField.getText(), 0.01);
	        double height = parseTextField(heightField.getText(), 0.01);
	        double alpha = parseTextField(alphaField.getText(), 0.01);
	        double range = parseTextField(rangeField.getText(), 0.01);
	        double t = parseTextField(timeField.getText(), 0.01);

	        Calc2D calc = new Calc2D(v, horizontalVelocity, initialVerticalVelocity, finalVerticalVelocity, v0, 9.81, t, height, maxHeight, alpha, range, selectedTimeUnit, selectedSpaceUnit, selectedVelocityUnit);
	        resultLabel.setText(calc.calculate());
	    } catch (NumberFormatException e) {
	        // Lide com exceção se a conversão falhar
	        System.out.println("Erro ao converter valores: " + e.getMessage());
	    }
   	    }
   	
	}
    /* 	  try {
        // Read values from text fields
        double v0 = Double.parseDouble(velocityInitialField.getText());
        double v = Double.parseDouble(velocityFinalField.getText());
        double s = Double.parseDouble(positionFinalField.getText());
        double s0 = Double.parseDouble(positionInitialField.getText());
        double alpha = Double.parseDouble(Alpha.getText());
        double g = Double.parseDouble(gravidade.getText());
        double t = Double.parseDouble(timeField.getText());
        double h = Double.parseDouble(altura.getText());
        double hMax = Double.parseDouble(alturaMaxima.getText());
        double range = Double.parseDouble(Range.getText());

        // Set values in the calculator
        calculator.setV0(v0);
        calculator.setAlpha(alpha);
        calculator.setG(g);
        calculator.setT(t);
        calculator.setH(h);


        // Perform calculations
        calculator.calculate();

        // Display or use the calculated values as needed
        double resultT = calculator.getT();
        double resultHmax = calculator.getHmax();
        double resultRange = calculator.getRange();
        
        velocityFinalField.setText(String.valueOf(calculator.getV()));
        velocityInitialField.setText(String.valueOf(calculator.getV0()));
        positionFinalField.setText(String.valueOf(calculator.getH()));

        
    	  } catch (NumberFormatException e) {
    	        System.err.println("Invalid input. Please enter numeric values.");
    	    } catch (NullPointerException e) {
    	        System.err.println("Calculation result is null. Check your input values.");
    	    }
    }
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
