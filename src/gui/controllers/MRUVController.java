package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


// DESCARTADO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class MRUVController implements Initializable  {
	
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
    
    @FXML
    private void handleCalculate(ActionEvent event) {
    	   boolean calculatedS0 = false;
           boolean calculatedS = false;
           boolean calculatedV0 = false;
           boolean calculatedV = false;
           boolean calculatedT = false;
           boolean calculatedA = false;
           
           boolean convertedS0 = false;
           boolean convertedS = false;
           boolean convertedV0 = false;
           boolean convertedV = false;
           boolean convertedT = false;
           boolean convertedA = false;

        // Obtenha os valores dos campos de texto
        String v0Text = velocityInitialField.getText();
        String vText = velocityFinalField.getText();
        String s0Text = positionInitialField.getText();
        String sText = positionFinalField.getText();
        String tText = timeField.getText();
        String aText = acel.getText();

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
            
            if (hasV && !convertedV) { 
            	v = convertVelocity(v);
                velocityFinalField.setText(String.valueOf(v));
                calculatedV = true;
            }
            if (hasA && !convertedA) {
            	a = convertVelocity(a);
                acel.setText(String.valueOf(a));
                calculatedA = true;
            }
            if (hasV0 && !convertedV0) {
            	v0 = convertVelocity(v0);
            	velocityInitialField.setText(String.valueOf(v0));
            	calculatedV0 = true;
            }
            if (hasS0 && !convertedS0) {  
            	s0 = convertVelocity(s0);
            	positionInitialField.setText(String.valueOf(s0));
            	calculatedS0 = true;
            }
            if (hasS && !convertedS) {     
            	s = convertVelocity(s);
            	positionFinalField.setText(String.valueOf(s));
            	calculatedS = true;
            }
            if (hasT && calculatedT && !convertedT) {
            	t = convertVelocity(t);
            	timeField.setText(String.valueOf(t));
            	calculatedT = true;
            }
            // Mensagens de depuração
            System.out.println("hasV0: " + hasV0);
            System.out.println("hasV: " + hasV);
            System.out.println("hasS0: " + hasS0);
            System.out.println("hasS: " + hasS);
            System.out.println("hasT: " + hasT);
            System.out.println("hasA: " + hasA);
            
            double result;
            
            if ((hasV && hasV0 && hasT) || (hasS && hasS0 && hasV0) || (hasS && hasS0 && hasV)) {
                System.out.println("Possível Calcular");
            } else {
                System.out.println("Não é possível!");
            }
            
            if (!calculatedS0 && hasS && hasV && hasV0 && hasT && !hasS0) {
                result = s - v0 * t - 0.5 * a * Math.pow(t, 2);
                resultLabel.setText("Posição Inicial (s0): " + result + " metros");
                s0 = result;
                calculatedS0 = true;
                hasS0 = true;
            }

            if (!calculatedS && hasV && hasV0 && hasT) {
                result = s0 + v0 * t + 0.5 * a * Math.pow(t, 2);
                resultLabel.setText("Posição Final (s): " + result + " metros");
                s = result;
                hasS = true;
                calculatedS = true;
                System.out.println(result);
            }

            if (!calculatedV0 && hasS && hasV && hasT) {
                result = v0 + a * t;
                resultLabel.setText("Velocidade Inicial (v0): " + result + " m/s");
                v0 = result;
                hasV0 = true;
                calculatedV0 = true;
                System.out.println(result);
            }

            if (!calculatedA && hasS && hasV && hasV0 && hasT && hasS0) {
                result = (2 * (s - s0) - v0 * t) / Math.pow(t, 2);
                resultLabel.setText("Aceleração (a): " + result + " m/s^2");
                if (v0 > v) {
                a = result;
                a = a*(-1);
                }else {
                a = result;
                }
                hasA = true;
                calculatedA = true;
                System.out.println(result);
            }
            if (!calculatedT && hasS && hasS0 && hasV && hasV0 && hasA) {
                double discriminant = Math.pow(v0, 2) + 2 * a * (s0 - s);

                if (discriminant >= 0) {
                    double root1 = (-v0 + Math.sqrt(discriminant)) / a;
                    double root2 = (-v0 - Math.sqrt(discriminant)) / a;

                    // Display only the positive root if it exists
                    if (root1 >= 0) {
                        resultLabel.setText("Tempo (t): " + root1 + " segundos");
                        t = root1;
                        hasT = true;
                        calculatedT = true;
                        System.out.println("Root 1: " + root1);
                    } else if (root2 >= 0) {
                        resultLabel.setText("Tempo (t): " + root2 + " segundos");
                        t = root2;
                        hasT = true;
                        calculatedT = true;
                        System.out.println("Root 2: " + root2);
                    } else {
                        resultLabel.setText("Erro: Raízes imaginárias, sem solução real.");
                        System.out.println("Erro: Raízes imaginárias, sem solução real.");
                    }
                } else {
                    resultLabel.setText("Erro: Raízes imaginárias, sem solução real.");
                    System.out.println("Erro: Raízes imaginárias, sem solução real.");
                }
            }

            if (!calculatedV && hasV0 && hasA && hasS && hasS0 && hasT) {
                result = v0 + a * t;
                resultLabel.setText("Velocidade Final (v): " + result + " m/s");
                v = result;
                hasV = true;
                calculatedV = true;
                System.out.println(result);
            }
           
            
            if (hasV) {            
                velocityFinalField.setText(String.valueOf(v));
            }
            if (hasA) {            
                acel.setText(String.valueOf(a));
            }
            if (hasV0) {            
            	velocityInitialField.setText(String.valueOf(v0));
            }
            if (hasS0) {            
            	positionInitialField.setText(String.valueOf(s0));
            }
            if (hasS) {            
            	positionFinalField.setText(String.valueOf(s));
            }
            if (hasT && calculatedT) {            
            	timeField.setText(String.valueOf(t));
            }
                
                
        } catch (NumberFormatException e) {
            resultLabel.setText("Erro: Insira valores válidos em todos os campos.");
            e.printStackTrace();
        }
    }

    private double convertTime(double time) {
    	String selectedTimeUnit = timeUni.getValue();
    	double timeConversionFactor;
    	  // Convert time to seconds
        switch (selectedTimeUnit) {
            case "Second":
                timeConversionFactor = 1.0;
                break;
            case "Minute":
                timeConversionFactor = 60.0;
                break;
            case "Hour":
                timeConversionFactor = 3600.0;
                break;
            default:
                timeConversionFactor = 1.0;
                break;
        }
        return (time * timeConversionFactor);
    }
    private double convertSpace(double space) {
    	String selectedSpaceUnit = spaceUni.getValue();
    	double spaceConversionFactor;
    	 // Convert space to meters
        switch (selectedSpaceUnit) {
            case "m":
                spaceConversionFactor = 1.0;
                break;
            case "km":
                spaceConversionFactor = 1000.0;
                break;
            case "foot":
                spaceConversionFactor = 0.3048;
                break;
            case "yd":
                spaceConversionFactor = 0.9144;
                break;
            case "miles":
                spaceConversionFactor = 1609.34;
                break;
            default:
                spaceConversionFactor = 1.0;
                break;
        }
        
        return (space * spaceConversionFactor);
    }
    
    private double convertVelocity(double velo) {
        // Get the selected units from ComboBoxes
        String selectedVelocityUnit = velUni.getValue();
        // Define conversion factors for different units
        double velocityConversionFactor;
        // Convert velocity to m/s
        switch (selectedVelocityUnit) {
            case "m/s":
                velocityConversionFactor = 1.0;
                break;
            case "km/h":
                velocityConversionFactor = 0.277778; // 1 km/h = 0.277778 m/s
                break;
            case "foot/s":
                velocityConversionFactor = 0.3048; // 1 foot/s = 0.3048 m/s
                break;
            case "mph":
                velocityConversionFactor = 0.44704; // 1 mph = 0.44704 m/s
                break;
            default:
                velocityConversionFactor = 1.0;
                break;
        }
        return (velo * velocityConversionFactor);

    }
}



