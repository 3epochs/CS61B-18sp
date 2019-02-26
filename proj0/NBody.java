/**run our simulation*/
public class NBody{
	private static String imgToDraw = "images/starfield.jpg";

	private static double readRadius(String fileName){
		In in = new In(fileName);
		int firstElem = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	private static Body[] readBodies(String fileName){
		In in = new In(fileName);
		int totalNum = in.readInt();
		Body[] planets = new Body[totalNum];
		double radius = in.readDouble();
		for(int i = 0; i < totalNum; i ++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String  imgFileName = in.readString();
			planets[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);		
		}
		return planets;
	}

	public  static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);

		// draw the background
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0, imgToDraw);

		// draw planets
		for (int i = 0; i < planets.length; i ++) {
			planets[i].draw();
		}


		double time = 0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		while (time < T) {
			for (int i = 0; i < planets.length; i ++ ){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0 ,imgToDraw);
			for (int i = 0; i < planets.length; i ++) {
				planets[i].update(dt, xForces[i], yForces[i]);
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

	}
}