package shu.silence;

public class HelloJava {

    public static void main(String[] args) {
//        System.out.println("HelloJava");
        int sum = 0;
        for (int i = 0; i <= 20; i = i + 2) {
            System.out.print(i + " ");
            sum += i;
        }
        System.out.println("\n" + sum);
        Integer a = 10;
        System.out.println(a);
    }
}
