package shu.silence;

import java.util.Scanner;

public class exercise1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int num;
        System.out.println("请输入3个1-100的整数");
        while (count < 3) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("此次输入并非整数,请重新输入");
            }
            num = scanner.nextInt();
            if (num < 1 || num > 100) {
                System.out.println("这个整数不在范围内，请重新输入");
            } else {
                count++;
                System.out.print("第" + count + "次输入正确，输入的值为" + num);
                if (count != 3) System.out.println("，请继续输入");
            }
        }
    }
}
