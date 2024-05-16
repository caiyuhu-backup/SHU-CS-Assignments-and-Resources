package shu.silence.exer2;

public class Triangle {
    private double l1, l2, l3; // 三个边长
    private double perimeter; // 周长
    private boolean isTriangle; // 判断三条边能否构成一个三角形

    public boolean isTriangle() {
        if (l1 + l2 <= l3 || l1 + l3 <= l2 || l3 + l2 <= l1) {
            return false;
        }
        return true;
    }

    public Triangle(double l1, double l2, double l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.perimeter = this.l1 + this.l2 + this.l3;
        isTriangle = this.isTriangle();
        if (isTriangle)
            System.out.println("三角形构造成功");
        else System.out.println("三角形构造失败");
    }

    public Triangle() {
    }

    public double getPerimeter() {
        if (!isTriangle) {
            System.out.println("不能构成三角形");
            return 0;
        }
        return perimeter;
    }

    public void setL1(double l1) {
        this.l1 = l1;
        isTriangle = this.isTriangle();
    }

    public void setL2(double l2) {
        this.l2 = l2;
        isTriangle = this.isTriangle();
    }

    public void setL3(double l3) {
        this.l3 = l3;
        isTriangle = this.isTriangle();
    }
}
