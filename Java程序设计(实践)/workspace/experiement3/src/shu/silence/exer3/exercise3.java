package shu.silence.exer3;

import java.util.Scanner;

public class exercise3 {
    public static void main(String[] args) {
        int[] num = new int[100];
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.println("请输入数据总数：");
        n = scanner.nextInt();
        System.out.println("请依次输入这" + n + "个数据");
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }

        // 以下使用选择排序
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < n; j++) {
                if (num[j] < num[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = num[i];
                num[i] = num[min];
                num[min] = tmp;
            }
        }

        System.out.println("排序后结果为");
        for (int i = 0; i < n; i++)
            System.out.print(num[i] + " ");

        // 以下为二分排序
        System.out.println("请输入一个数据，将使用二分查找这个数据是否在排序的数据中");
        int target = scanner.nextInt();
        int index = -1;
        int left = 0; // 设置左右边界
        int right = n - 1;
        while (left <= right) { // 设置循环终止条件
            int mid = left + (right - left) / 2;
            if (num[mid] == target) {
                index = mid;
                break; // 若查找到，则推出循环
            } else if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (index == -1)
            System.out.println("未查找到");
        else
            System.out.println("在序列中，索引号为" + index);
    }
}
