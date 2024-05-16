package shu.silence.exep1;

public class computePowerTest {
    public static void main(String[] args) {
        double d1 = 0.3, d2 = 0.5;
        System.out.println("——————基本类型数据测试开始——————");
        System.out.println("原变量为" + d1 + " " + d2);
        computePower.compPower(d1, d2);
        System.out.println("在主函数中");
        System.out.println("两个参数的值分别为" + d1 + " " + d2);

        Parameter parameter = new Parameter(3.0, 5.0);
        System.out.println("——————引用类型数据测试开始——————");
        System.out.println("原变量为" + parameter.a + " " + parameter.b);
        computePower.compPower(parameter);
        System.out.println("在主函数中");
        System.out.println("a的值为" + parameter.a);
    }
}
