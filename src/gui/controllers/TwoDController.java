package gui.controllers;

import entities.Calc2D;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TwoDController {

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
    private TextField acel;

    @FXML
    private TextField gravidade;

    @FXML
    private TextField altura;

    @FXML
    private TextField alturaMaxima;

    @FXML
    private TextField Range;

    @FXML
    private TextField Alpha;

    @FXML
    private Button calcButton;
    
    private Calc2D calculator = new Calc2D();

   @FXML
    private void handleCalculate() {
	   System.out.println("handleCalculate Ativado!");
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
}