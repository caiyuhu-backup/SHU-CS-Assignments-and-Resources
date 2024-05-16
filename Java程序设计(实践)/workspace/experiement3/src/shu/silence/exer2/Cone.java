package shu.silence.exer2;

public class Cone {
    private Circle bottom;
    private double height;

    public double getVolume() {
        return this.bottom.getArea() * this.height / 3;

    }

    public Circle getBottom() {
        return bottom;
    }

    public Cone(Circle bottom, double height) {
        this.bottom = bottom;
        this.height = height;
        System.out.println("圆锥体构造成功");
    }

    public Cone() {
    }
}
