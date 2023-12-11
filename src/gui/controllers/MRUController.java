package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MRUController {

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
    private void handleCalculate(ActionEvent event) {
        boolean calculatedS0 = false;
        boolean calculatedS = false;
        boolean calculatedV0 = false;
        boolean calculatedV = false;
        boolean calculatedT = false;
        boolean calculatedA = false;
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
            
            if (hasV) {            
                velocityFinalField.setText(String.valueOf(v));
                calculatedV = true;
            }
            if (hasA) {            
                acel.setText(String.valueOf(a));
                calculatedA = true;
            }
            if (hasV0) {            
            	velocityInitialField.setText(String.valueOf(v0));
            	calculatedV0 = true;
            }
            if (hasS0) {            
            	positionInitialField.setText(String.valueOf(s0));
            	calculatedS0 = true;
            }
            if (hasS) {            
            	positionFinalField.setText(String.valueOf(s));
            	calculatedS = true;
            }
            if (hasT && calculatedT) {            
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
            
            if (!calculatedS0 && hasS && hasV && hasV0 && hasT && !hasS0) {
                result = s - ((v + v0) * t) / 2;
                resultLabel.setText("Posição Inicial (s0): " + result + " metros");
                s0 = result; calculatedS0 = true;
                hasS0 = true;
            }
            
            if (!calculatedS0 && hasS && hasV && hasV0 && hasT && !hasS0 && !hasA) {
                result = s - ((v + v0) * t) / 2;
                resultLabel.setText("Posição Inicial (s0): " + result + " metros");
                s0 = result; calculatedS0 = true;
                hasS0 = true;
            }
            
         // Verifique se há informações suficientes para calcular
            if ((hasV && hasV0 && hasT) || (hasS && hasS0 && hasV0) || (hasS && hasS0 && hasV)) {
                System.out.println("Possível Calcular");
            } else {
                System.out.println("Não é possível!");
            }

            if (!calculatedS && hasV && hasV0 && hasT) {
                result = s0 + ((v + v0) * t) / 2;
                resultLabel.setText("Posição Final (s): " + result + " metros");
                System.out.println("Agora tem Posicao Final");
                s = result; hasS = true; calculatedS = true;
                System.out.println(result);
            }

            if (!calculatedV0 && hasS && hasV && hasT) {
                result = (2 * s - v * t) / t;
                resultLabel.setText("Velocidade Inicial (v0): " + result + " m/s");
                System.out.println("Agora tem Velocidade Inicial");
                v0 = result; hasV0 = true;  calculatedV0 = true;
                System.out.println(result);
            }

            if (!calculatedA && hasS && hasV && hasV0 && !hasS0 && hasA) {
                double discriminant = Math.pow(v0, 2) - 4 * (0.5 * a) * (s0 - s);

                if (discriminant >= 0) {
                    double root1 = (-v0 + Math.sqrt(discriminant)) / (a);
                    double root2 = (-v0 - Math.sqrt(discriminant)) / (a);

                    // Display only the positive root if it exists
                    if (root1 >= 0) {
                        resultLabel.setText("Tempo (t): " + root1 + " segundos");
                        System.out.println("Root 1: " + root1);
                        calculatedT = true;
                        a = root1; hasT = true;
                    } else if (root2 >= 0) {
                        resultLabel.setText("Tempo (t): " + root2 + " segundos");
                        System.out.println("Root 2: " + root2);
                        calculatedT = true;
                        a = root2; hasT = true;
                    } else {
                        resultLabel.setText("Erro: Raízes imaginárias, sem solução real.");
                        System.out.println("Erro: Raízes imaginárias, sem solução real.");
                    }
                } else {
                    resultLabel.setText("Erro: Raízes imaginárias, sem solução real.");
                    System.out.println("Erro: Raízes imaginárias, sem solução real.");
                }
                
            }
            
            if (!calculatedS && hasS0 && hasV0 && hasA && hasT && hasS) {
                result = s0 + v0 * t + 0.5 * a * Math.pow(t, 2);
                resultLabel.setText("Posição Final (s): " + result + " metros");
                System.out.println("Agora tem Posicao Final");
                s = result; hasS = true; calculatedS = true;
                System.out.println(result);
            }

            if (!calculatedV && hasV0 && hasA && hasS) {
                result = Math.sqrt(Math.pow(v0, 2) + 2 * a * (s - s0));
                resultLabel.setText("Velocidade Final (v): " + result + " m/s");
                System.out.println("Agora tem Velocidade Final");
                v = result; hasV = true; calculatedV = true;
                System.out.println(result);
            }

            if (!calculatedV && hasV0 && hasA && hasS && hasT && !hasV) {
                result = v0 + a * t;
                resultLabel.setText("Velocidade Final (v): " + result + " m/s");
                System.out.println("Agora tem Velocidade Final");
                v = result; hasV = true; calculatedV = true;
                System.out.println(result);
            }
 
         // Verifique se há informações suficientes para calcular a aceleração
            if (!calculatedA && hasV && hasV0 && hasT && !hasA) {
                result = (v - v0) / t;
                resultLabel.setText("Aceleração (a): " + result + " m/s^2");
                System.out.println("Agora tem Aceleração");
                a = result; hasA = true; calculatedA = true;
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
            // Handle the exception, e.g., show an error message or log it
            resultLabel.setText("Erro: Insira valores válidos em todos os campos.");
            e.printStackTrace();
        }
    }
}


