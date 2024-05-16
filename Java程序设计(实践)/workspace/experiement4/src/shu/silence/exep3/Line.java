package shu.silence.exep3;

public class Line {
    private Point p1, p2;
    double dest;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        dest = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public Point getP1() {
        return p1;
    }

    public Line() {
    }

    public void setP1(Point p1) {
        this.p1 = p1;
        dest = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));

    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        dest = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
