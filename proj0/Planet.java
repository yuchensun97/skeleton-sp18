public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                    double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P) {
        this.xxPos = P.xxPos;
        this.yyPos = P.yyPos;
        this.xxVel = P.xxVel;
        this.yyVel = P.yyVel;
        this.mass = P.mass;
        this.imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P){
        double dx = P.xxPos - this.xxPos;
        double dy = P.yyPos - this.yyPos;
        double r = Math.sqrt(dx*dx + dy*dy);
        return r;
    }

    public double calcForceExertedBy(Planet P) {
        double G = 6.67e-11;
        double F = G * this.mass * P.mass / Math.pow(this.calcDistance(P),2);
        return F;
    }

    public double calcForceExertedByX(Planet P) {
        double dx = P.xxPos - this.xxPos;
        double Fx = this.calcForceExertedBy(P) * dx / this.calcDistance(P);
        return Fx;
    }

    public double calcForceExertedByY(Planet P) {
        double dy = P.yyPos - this.yyPos;
        double Fy = this.calcForceExertedBy(P) * dy / this.calcDistance(P);
        return Fy;      
    }

    public double calcNetForceExertedByX(Planet[] allPlanet) {
        double Fnetx = 0;
        for (int i = 0; i < allPlanet.length; i++) {
            if (!this.equals(allPlanet[i])) {
                Fnetx += this.calcForceExertedByX(allPlanet[i]);
            }
        }
        return Fnetx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet) {
        double Fnety = 0;
        for (int i = 0; i < allPlanet.length; i++) {
            if (!this.equals(allPlanet[i])) {
                Fnety += this.calcForceExertedByY(allPlanet[i]);
            }
        }
        return Fnety;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}