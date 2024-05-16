package shu.silence.exer2;

import java.util.Scanner;

public class exercise2 {
    public static void main(String[] args) {
        double l1, l2, l3;
        Scanner scanner = new Scanner(System.in);

        System.out.println("——————————三角形测试开始——————————");
        System.out.println("请输入三角形的l1边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l1 = scanner.nextDouble();

        System.out.println("请输入三角形的l2边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l2 = scanner.nextDouble();

        System.out.println("请输入三角形的l3边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l3 = scanner.nextDouble();

        Triangle triangle = new Triangle(l1, l2, l3);

        System.out.println("三角形的周长为：" + triangle.getPerimeter()); // 计算周长并输出
        System.out.println("接下来修改三角形的三边长：");

        System.out.println("请输入三角形的l1边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l1 = scanner.nextDouble();

        System.out.println("请输入三角形的l2边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l2 = scanner.nextDouble();

        System.out.println("请输入三角形的l3边长");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        l3 = scanner.nextDouble();

        triangle.setL1(l1); // 修改三边值，判断是否能构成三角形
        triangle.setL2(l2); // 修改三边值，判断是否能构成三角形
        triangle.setL3(l3); // 修改三边值，判断是否能构成三角形
        System.out.println("是否能构成三角形？" + "  " + triangle.isTriangle());


        System.out.println("——————————圆形测试开始——————————");
        System.out.println("请输入圆形的l3边长");
        double r;
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        r = scanner.nextDouble();
        Circle circle = new Circle(r);
        System.out.println("圆形的面积为：" + circle.getArea()); // 计算面积并输出


        System.out.println("——————————圆锥体测试开始——————————");
        System.out.println("请输入圆锥体的底面半径");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        r = scanner.nextDouble();

        System.out.println("请输入圆锥体的高");
        double height;
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        height = scanner.nextDouble();

        Cone cone = new Cone(new Circle(r), height);
        System.out.println("接下来修改圆锥体底面的半径：");
        System.out.println("请输入圆锥体的底面半径");
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.println("此次输入不合法,请重新输入");
        }
        r = scanner.nextDouble();
        cone.getBottom().setRadius(r); // 修改 Cone 底的半径
        System.out.println("圆锥体的体积为" + cone.getVolume());
    }
}
