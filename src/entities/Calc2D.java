package entities;

public class Calc2D {

	private double V, Vx, Vy0, Vy, V0, g, t, h, hmax, alpha, range; 
	boolean bool_V, bool_Vx, bool_Vy0, bool_Vy, bool_V0, bool_g, bool_t, bool_h, bool_hmax, bool_alpha, bool_range;
	
	public Calc2D() {
		
	}
	
	public void calculate() {
		
		if (bool_V0 && bool_alpha && !bool_Vx) {
			this.setVx(this.horizontal_velocity_component(V0, alpha));
		}
		
		if (bool_V0 && bool_alpha && bool_g && bool_t && !bool_Vy) {
			this.setVy(this.vertical_velocity_component(V0, alpha, g, t));
		}
		
		if (bool_Vy0 && bool_g && !bool_t) {
			this.setT(this.time_of_flight(Vy0, g));
		}
		
		if (bool_Vy0 && bool_g && !bool_hmax) {
			this.setHmax(this.max_height(Vy0, g));
		}
		
		if (bool_Vy0 && bool_Vx && bool_g && !bool_range) {
			this.setRange(this.range_of_the_projectile(Vx, Vy0, g));
		}
		
		if (bool_Vy0 && bool_g && bool_h && !bool_t) {
			this.setT(this.time_of_flight(Vy0, g, h));
		}
		
		if (bool_Vy0 && bool_g && bool_h && !bool_hmax) {
			this.setHmax(this.max_height(h, Vy0, g));
		}
		
		if (bool_Vx && bool_Vy0 && bool_g && bool_h && !bool_range) {
			this.setRange(this.range_of_the_projectile(Vx, Vy0, g, h));
		}
		
		if (bool_Vx && bool_Vy && !bool_V) {
			this.setV(this.velocity(Vx, Vy));
		}
	}
	
	// launching the object from the ground (initial height h = 0)
	
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
}