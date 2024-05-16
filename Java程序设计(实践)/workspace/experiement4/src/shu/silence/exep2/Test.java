package shu.silence.exep2;

public class Test {
    public static void main(String[] args) {
        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();

        MyClass.setX(22.22);
        myClass1.setY(2);
        System.out.println("第一个对象的x为 " + MyClass.getX() + " y为 " + myClass1.getY());

        MyClass.setX(11.11);
        myClass2.setY(5);
        System.out.println("第二个对象的x为 " + MyClass.getX() + " y为 " + myClass2.getY());

        System.out.println("此时第一个对象的x被修改，现在类变量x为" + MyClass.getX());
    }
}
