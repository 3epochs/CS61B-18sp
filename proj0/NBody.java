/**run our simulation*/
public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int firstElem = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName){
		Body[] planets = new Body[5];
		In in = new In(fileName);
		int totalNum = in.readInt();
		double radius = in.readDouble();
		for(int i = 0; i < 5; i ++){
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
}