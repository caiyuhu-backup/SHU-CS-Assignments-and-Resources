package shu.silence.exer2;

public class Circle {
    private double radius;
    private double area;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return area;
    }

    public Circle(double radius) {
        this.radius = radius;
        this.area = this.radius * this.radius * Math.PI;

    }

    public Circle() {
    }
}
