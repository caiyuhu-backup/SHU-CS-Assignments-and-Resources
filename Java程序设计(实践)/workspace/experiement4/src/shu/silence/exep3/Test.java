package shu.silence.exep3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入这条线段两个点的信息");
        System.out.println("请输入第一个点坐标(x1, y1)");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        Point p1 = new Point(x1, y1);
        System.out.println("请输入第二个点坐标(x2, y2)");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        Point p2 = new Point(x2, y2);
        Line line = new Line(p1, p2);

        System.out.println("这条直线的长度为" + line.dest);
    }
}
