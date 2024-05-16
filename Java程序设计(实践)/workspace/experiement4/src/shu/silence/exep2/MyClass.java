package shu.silence.exep2;

public class MyClass {
    private static double x;
    private int y;

    public MyClass(int y) {
        this.y = y;
    }

    public MyClass() {
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        MyClass.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
