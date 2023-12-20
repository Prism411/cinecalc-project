package entities;

public class MRUCalculator {

    private Double initialVelocity;
    private Double finalVelocity;
    private Double initialPos;
    private Double deltaPos;
    private Double finalPos;
    private Double t;
    private Double a;
    
    private String unidadeTempo;
    private String unidadeVelocidade;
    private String unidadeEspaco;



	public MRUCalculator(Double initialVelocity, Double finalVelocity, Double initialPos, Double deltaPos,
			Double finalPos, Double t, Double a, String unidadeTempo, String unidadeVelocidade, String unidadeEspaco) {
		super();
		this.initialVelocity = initialVelocity;
		this.initialVelocity = convertvelocityUnits(initialVelocity,unidadeVelocidade);
		this.finalVelocity = finalVelocity;
		this.finalVelocity = convertvelocityUnits(finalVelocity,unidadeVelocidade);
		this.initialPos = initialPos;
		this.initialPos = convertSpace(initialPos,unidadeEspaco);
		this.deltaPos = deltaPos;
		this.finalPos = finalPos;
		this.finalPos = convertSpace(finalPos,unidadeEspaco);
		this.t = t;
		this.t = convertTime(t,unidadeTempo);
		this.a = a;
		this.a = convertvelocityUnits(a,unidadeVelocidade);
		this.unidadeTempo = unidadeTempo;
		this.unidadeVelocidade = unidadeVelocidade;
		this.unidadeEspaco = unidadeEspaco;
	}

	// Método para calcular o deslocamento (deltaPos) usando a equação do MRU
    public Double calcularDeltaPos() {
        deltaPos = (initialVelocity - finalVelocity);
        return deltaPos;
    }
    
    public Double calcularDeltaPosAlt() {
        deltaPos = initialVelocity * t;
        return deltaPos;
    }

    // Método para calcular a velocidade final usando a equação do MRU
    public Double calcularVelocidadeFinal() {
        finalVelocity = initialVelocity;
        return finalVelocity;
    }

    // Método para calcular a velocidade inicial usando a equação do MRU
    public Double calcularVelocidadeInicial() {
        initialVelocity = finalVelocity;
        return initialVelocity;
    }

    // Método para calcular o tempo (t) usando a equação do MRU
    public Double calcularTempo() {
        t = deltaPos / initialVelocity;
        return t;
    }

    // Método para calcular a aceleração (a) usando a equação do MRU
    public Double calcularAceleracao() {
        a = 0.0;
        return a;
    }

    // Método para calcular a posição final usando a equação do MRU
    public Double calcularPosicaoFinal() {
        finalPos = initialPos + initialVelocity * t;
        return finalPos;
    }


    // Método para calcular a posição inicial usando a equação do MRU
    public Double calcularPosicaoInicial() {
        initialPos = finalPos - initialVelocity * t;
        return initialPos;
    }
    
    public String calculaDados() {
        StringBuilder resultado = new StringBuilder();
        boolean initVelo = false, finalVelo = false, initPos = false, finPos = false, aCheck = false, tCheck = false, deltaCheck=false;
        // Verificar e calcular variáveis ausentes
        int cont = 0;

        while (cont < 8) {
            try {
            	
            	if (deltaPos == 0 && initialVelocity != 0 && t != 0) {
            		calcularDeltaPosAlt();
            		deltaCheck = true;
            		resultado.append("Deslocamento: ").append(deltaPos).append("\n");}
            		
            	if (deltaPos == 0 && initialVelocity != 0 && finalVelocity != 0 && !deltaCheck) {
            		calcularDeltaPos();
            		deltaCheck = true;
            		resultado.append("Deslocamento: ").append(deltaPos).append("\n");
            	}
                if (initialVelocity == 0 && finalVelocity != 0 && !initVelo) {
                    calcularVelocidadeInicial();
                    initVelo = true;
                    resultado.append("Inicial Velocity calculada: ").append(initialVelocity).append("\n");
                }

                if (finalVelocity == 0 && initialVelocity != 0 && !finalVelo) {
                    calcularVelocidadeFinal();
                    finalVelo = true;
                    resultado.append("Velocidade Final: ").append(finalVelocity).append("m/s \n");
                }

                if (initialPos == 0 && initialVelocity != 0 && finalPos != 0 && t != 0 && !initPos) {
                    calcularPosicaoInicial();
                    initPos = true;
                    resultado.append("Posicao Inicial: ").append(initialPos).append("m \n");
                }

                if (finalPos == 0 && initialPos != 0 && initialVelocity != 0 && t != 0 && !finPos) {
                    calcularPosicaoFinal();
                    finPos = true;
                    resultado.append("Posicao Final: ").append(finalPos).append("m \n");
                }

                if (t == 0 && finalVelocity != 0 && initialVelocity != 0 && a != 0 && !tCheck) {
                    calcularTempo();
                    tCheck = true;
                    resultado.append("Tempo: ").append(t).append("s \n");
                }

                if (a == 0 && finalVelocity != 0 && initialVelocity != 0 && t != 0 && !aCheck) {
                    calcularAceleracao();
                    aCheck = true;
                    resultado.append("Aceleracao: ").append(a).append("\n");
                }
            } catch (IllegalStateException e) {
                // Lidar com a exceção, por exemplo, imprimir uma mensagem de erro
                resultado.append("Erro ao calcular dados: ").append(e.getMessage()).append("\n");
            }

            cont++;
        }

        return resultado.toString();
    }

public Double getInitialVelocity() {
	return initialVelocity;
}
public void setInitialVelocity(Double initialVelocity) {
	this.initialVelocity = initialVelocity;
}
public Double getFinalVelocity() {
	return finalVelocity;
}
public void setFinalVelocity(Double finalVelocity) {
	this.finalVelocity = finalVelocity;
}
public Double getInitialPos() {
	return initialPos;
}
public void setInitialPos(Double initialPos) {
	this.initialPos = initialPos;
}
public Double getDeltaPos() {
	return deltaPos;
}
public void setDeltaPos(Double deltaPos) {
	this.deltaPos = deltaPos;
}
public Double getFinalPos() {
	return finalPos;
}
public void setFinalPos(Double finalPos) {
	this.finalPos = finalPos;
}
public Double getT() {
	return t;
}
public void setT(Double t) {
	this.t = t;
}
public Double getA() {
	return a;
}
public void setA(Double a) {
	this.a = a;
}

public String getUnidadeTempo() {
	return unidadeTempo;
}

public void setUnidadeTempo(String unidadeTempo) {
	this.unidadeTempo = unidadeTempo;
}

public String getUnidadeVelocidade() {
	return unidadeVelocidade;
}

public void setUnidadeVelocidade(String unidadeVelocidade) {
	this.unidadeVelocidade = unidadeVelocidade;
}

public String getUnidadeEspaco() {
	return unidadeEspaco;
}

public void setUnidadeEspaco(String unidadeEspaco) {
	this.unidadeEspaco = unidadeEspaco;
}

private double convertTime(double time, String unidade) {
	double timeConversionFactor;
	  // Convert time to seconds
    switch (unidade) {
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
private double convertSpace(double space, String unidade) {
	double spaceConversionFactor;
	 // Convert space to meters
    switch (unidade) {
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

private double convertvelocityUnits(double velo, String unidade) {
    double velocityConversionFactor;
    // Convert velocity to m/s
    switch (unidade) {
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
   
   
   

