package shu.silence.exep1;

public class computePower {
    public static void compPower(double d1, double d2) {
        d1 = d1 * d1;
        d2 = d2 * d2;
        System.out.println("在静态方法中");
        System.out.println("两个参数的值分别为" + d1 + " " + d2);
        System.out.println();
    }

    public static void compPower(Parameter parameter) {
        parameter.a = parameter.a * parameter.a + parameter.b * parameter.b;
        System.out.println("在静态方法中");
        System.out.println("a的值为" + parameter.a);
        System.out.println();
    }
}
