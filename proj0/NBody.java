public class NBody {
    
    public static double readRadius(String FileName){
        In in = new In(FileName);
        in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String FileName) {
        In in = new In(FileName);
        int num_planets = in.readInt();
        in.readDouble();
        Planet[] allPlanets = new Planet[num_planets];
        int i = 0;
        while (i < num_planets) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String imName = in.readString();
            Planet P = new Planet(xP, yP, xV, yV,m,imName);
            allPlanets[i] = P;
            i += 1;
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        /** initializing */
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        Planet[] allPlanets = readPlanets(filename);
        double Radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius,Radius);

        double t = 0;
        int num_planets = allPlanets.length;
        while (t <= T) {

            double[] xForces = new double[num_planets];
            double[] yForces = new double[num_planets];

            for (int j = 0; j < num_planets; j++) {
                double Fnetx = allPlanets[j].calcNetForceExertedByX(allPlanets);
                double Fnety = allPlanets[j].calcNetForceExertedByY(allPlanets);
                xForces[j] = Fnetx;
                yForces[j] = Fnety;
            }

            /** update each planet's status */
            for (int j = 0; j < num_planets; j++) {
                allPlanets[j].update(dt, xForces[j], yForces[j]);
            }

            /** Drawing the Background */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /** Drawing Planets */
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", num_planets);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < num_planets; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                        allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
    }
}