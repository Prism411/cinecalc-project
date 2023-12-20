package entities;

public class Calc2D {

	private double V, Vx, Vy0, Vy, V0, g, t, h, hmax, alpha, range; 
	boolean bool_V=false, bool_Vx=false, bool_Vy0=false, bool_Vy=false, bool_V0=false, bool_g=false, bool_t=false, bool_h=false, bool_hmax=false, bool_alpha=false, bool_range=false;
	
	public Calc2D() {
	}
	
	 /**
     * Construtor da classe {@code Calc2D} que permite a inicialização dos atributos com valores específicos.
     *
     * @param v     Velocidade resultante
     * @param vx    Velocidade inicial no eixo x
     * @param vy0   Velocidade inicial no eixo y
     * @param vy    Velocidade final no eixo y
     * @param v0    Velocidade inicial resultante
     * @param g     Aceleração devido à gravidade
     * @param t     Tempo
     * @param h     Altura
     * @param hmax  Altura máxima
     * @param alpha Ângulo de lançamento
     * @param range Alcance
     * @param time  Unidade de tempo
     * @param space Unidade de espaço
     * @param velo  Unidade de velocidade
     */
	public Calc2D(double v, double vx, double vy0, double vy, double v0, double g, double t, double h, double hmax,
			double alpha, double range,String time, String Space, String velo) {
		this.V = convertVelo(v,velo);
		this.Vx = convertVelo(vx,velo);
		this.Vy0 =  convertVelo(vy0,velo);
		this.Vy = convertVelo(vy,velo);
		this.V0 = convertVelo(v0,velo);
		this.g = g;
		this.t = convertTime(t,time);
		this.h = convertSpace(h,Space);
		this.hmax = convertSpace(h,Space);
		this.alpha = alpha;
		this.range = range;
	}

	/**
	 * Calcula várias grandezas relacionadas ao movimento bidimensional e gera uma string com os resultados.
	 * <p>
	 * Este método utiliza valores pré-definidos ou calculados para as variáveis relacionadas ao movimento bidimensional,
	 * tais como velocidades iniciais e finais, tempo de voo, altura máxima, alcance, etc.
	 * A aceleração devida à gravidade é considerada como 9.81 m/s².
	 * Os resultados dos cálculos são armazenados internamente nos atributos da classe e retornados como uma string.
	 * </p>
	 *
	 * @return Uma string contendo os resultados dos cálculos.
	 * @throws IllegalStateException Se ocorrer um erro ao calcular os dados, uma exceção será lançada com uma mensagem de erro.
	 * @see #horizontal_velocity_component(double, double)
	 * @see #vertical_velocity_component(double, double, double, double)
	 * @see #time_of_flight(double, double)
	 * @see #max_height(double, double)
	 * @see #range_of_the_projectile(double, double, double)
	 * @see #range_of_the_projectile(double, double, double, double)
	 * @see #velocity(double, double)
	 */
	public String calculate() throws IllegalStateException {
	 // Aceleração devida à gravidade
    g = 9.81;

    // Inicialização do contador
    int cont = 0;

    // StringBuilder para armazenar os resultados dos cálculos
    StringBuilder resultado = new StringBuilder();

    // Loop para realizar cálculos até atingir o número máximo de iterações (8)
    while (cont != 8) {
        // Cálculo da velocidade inicial no eixo x se necessário
        if (V0 != 0.01 && alpha != 0.01 && Vx == 0.01 && !bool_Vx) {
            this.setVx(this.horizontal_velocity_component(V0, alpha));
            bool_Vx = true;
            resultado.append("Velocidade Inicial X: ").append(Vx).append("m/s \n");
        }

        // Cálculo da velocidade inicial no eixo y se necessário
        if (V0 != 0.01 && alpha != 0.01 && t != 0.01 && Vy == 0.01 && !bool_Vy) {
            this.setVy(this.vertical_velocity_component(V0, alpha, g, t));
            bool_Vy = true;
            resultado.append("Velocidade Inicial Y: ").append(Vy).append("m/s \n");
        }

        // Cálculo do tempo de voo se necessário
        if (Vy0 != 0.01 && t == 0.01 && !bool_t) {
            this.setT(this.time_of_flight(Vy0, g));
            bool_t = true;
            resultado.append("Tempo de Voo: ").append(t).append("s \n");
        }

        // Cálculo da altura máxima se necessário
        if (Vy0 != 0.01 && hmax == 0.01 && !bool_hmax) {
            this.setHmax(this.max_height(Vy0, g));
            bool_hmax = true;
            resultado.append("Altura Máxima: ").append(hmax).append("m \n");
        }

        // Cálculo do alcance se necessário
        if (Vy0 != 0.01 && Vx != 0.01 && range == 0.01 && !bool_range) {
            this.setRange(this.range_of_the_projectile(Vx, Vy0, g));
            bool_range = true;
            resultado.append("Alcance: ").append(range).append("m \n");
        }

        // Cálculo do tempo de voo com altura específica se necessário
        if (Vy0 != 0.01 && h != 0.01 && t == 0.01 && !bool_t) {
            this.setT(this.time_of_flight(Vy0, g, h));
            bool_t = true;
            resultado.append("Tempo de Voo: ").append(t).append("s \n");
        }

        // Cálculo da altura máxima com altura específica se necessário
        if (Vy0 != 0.01 && h != 0.01 && hmax == 0.01 && !bool_hmax) {
            this.setHmax(this.max_height(h, Vy0, g));
            bool_hmax = true;
            resultado.append("Altura Máxima: ").append(hmax).append("m \n");
        }

        // Cálculo do alcance com altura específica se necessário
        if (Vx != 0.01 && Vy0 != 0.01 && h != 0.01 && range == 0.01 && !bool_range) {
            this.setRange(this.range_of_the_projectile(Vx, Vy0, g, h));
            bool_range = true;
            resultado.append("Alcance: ").append(range).append("m \n");
        }

        // Cálculo da velocidade resultante se necessário
        if (Vx != 0.01 && Vy != 0.01 && V == 0.01 && !bool_V) {
            this.setV(this.velocity(Vx, Vy));
            bool_V = true;
            resultado.append("Velocidade Resultante: ").append(V).append("m/s \n");
        }

        // Incremento do contador
        cont++;
    }

    // Retorna a string com os resultados dos cálculos
    return resultado.toString();}

	 // Outros métodos da classe omitidos para simplificação

    // Getters e setters da classe omitidos para simplificação

    // Métodos privados de conversão de unidades omitidos para simplificação
	
	double horizontal_velocity_component(double V0, double alpha) {
		return V0 * Math.cos(alpha);
	}
	
	double vertical_velocity_component(double V0, double alpha, double g, double t) {
		return V0 * Math.sin(alpha) - g * t;
	}
	
	double time_of_flight(double Vy0, double g) {
		return (2 * Vy0) / g;
	}
	
	double range_of_the_projectile(double Vx, double Vy0, double g) {
		return (2 * Vx * Vy0) / g;
	}
	
	double max_height(double Vy0, double g) {
		return Math.pow(Vy0, 2) / (2 * g);			
	}
	
	// launching the object from some elevation (initial height h > 0)
	
	double time_of_flight(double Vy0, double g, double h) {
		return (Vy0 + Math.sqrt((Math.pow(Vy0, 2) + 2 * g * h))) / g;
	}
	
	double range_of_the_projectile(double Vx, double Vy0, double g, double h) {
		return (Vx * (Vy0 + Math.sqrt(Math.pow(Vy0, 2) + 2 * g * h))) / g;
	}
	
	double max_height(double h, double Vy0, double g) {
		return (h + Math.pow(Vy0, 2)) / (2 * g);
	}
	
	// calculate velocity
	
	double velocity(double Vx, double Vy) {
		return (Math.sqrt(Math.pow(Vx, 2) + Math.pow(Vy, 2)));
	}
	
	// getters e setters

	public double getV() {
		return V;
	}

	public void setV(double v) {
		V = v;
		this.bool_V = true;
	}
		
	public double getVx() {
		return Vx;
	}

	public void setVx(double vx) {
		Vx = vx;
		this.bool_Vx = true;
	}

	public double getVy0() {
		return Vy0;
	}

	public void setVy0(double vy0) {
		Vy0 = vy0;
		this.bool_Vy0 = true;
	}

	public double getV0() {
		return V0;
	}

	public void setV0(double v0) {
		V0 = v0;
		this.bool_V0 = true;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
		this.bool_g = true;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
		this.bool_t = true;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
		this.bool_h = true;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
		this.bool_alpha = true;
	}

	public double getVy() {
		return Vy;
	}

	public void setVy(double vy) {
		Vy = vy;
		this.bool_Vy = true;
	}

	public double getHmax() {
		return hmax;
	}

	public void setHmax(double hmax) {
		this.hmax = hmax;
		this.bool_hmax = true;
	}

	public double getRange() {
		return range;	
	}

	public void setRange(double range) {
		this.range = range;
		this.bool_range = true;
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

	private double convertVelo(double velo, String unidade) {
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