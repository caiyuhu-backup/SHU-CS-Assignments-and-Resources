package shu.silence;

import java.util.Random;
import java.util.Scanner;

public class exercise3 {
    public static void main(String[] args) {
        System.out.println("请输入剪刀、石头或布 剪刀（1）石头（2）布（3）");
        Scanner scanner = new Scanner(System.in);
        int myChoice = scanner.nextInt();
        int random = new Random().nextInt(3) + 1; // 生成[1,4)区间的整数 即1 2 3
        switch (random) {
            case 1: {
                System.out.println("电脑出剪刀");
                switch (myChoice) {
                    case 1:
                        System.out.println("平局");
                    case 2:
                        System.out.println("你赢了");
                    case 3:
                        System.out.println("你输了");
                }
            }
            case 2: {
                System.out.println("电脑出石头");
                switch (myChoice) {
                    case 1:
                        System.out.println("你输了");
                    case 2:
                        System.out.println("平局");
                    case 3:
                        System.out.println("你赢了");
                }
            }
            case 3: {
                System.out.println("电脑出布");
                switch (myChoice) {
                    case 1:
                        System.out.println("你赢了");
                    case 2:
                        System.out.println("你输了");
                    case 3:
                        System.out.println("平局");

                }
            }
        }
    }
}
