public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double g = 6.67e-11;

	public Body(double xP, double yP, double xV,
				double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double dist;
		double xRes = this.xxPos - b.xxPos;
		double yRes = this.yyPos - b.yyPos;
		double tmp = Math.pow(xRes, 2) + Math.pow(yRes, 2);
		dist = Math.sqrt(tmp);
		return dist;
	}

	public double calcForceExertedBy(Body b){
		double force;
		double tmp = g * this.mass * b.mass;
		double dist =  this.calcDistance(b);
		force = tmp / Math.pow(dist, 2);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double forceX;
		double force = this.calcForceExertedBy(b);
		double dist = this.calcDistance(b);
		forceX = force * (b.xxPos - this.xxPos) / dist;
		return forceX;
	}

	public double calcForceExertedByY(Body b){
		double forceY;
		double force = this.calcForceExertedBy(b);
		double dist = this.calcDistance(b);
		forceY = force * (b.yyPos - this.yyPos) / dist;
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double netForceX = 0.0;
		for (Body body:allBodys){
			if (! this.equals(body)){
				double tmp = this.calcForceExertedByX(body);
				netForceX += tmp;
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double netForceY = 0.0;
		for (Body body:allBodys){
			if (! this.equals(body)){
				double tmp = this.calcForceExertedByY(body);
				netForceY += tmp;
			}
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}
}
