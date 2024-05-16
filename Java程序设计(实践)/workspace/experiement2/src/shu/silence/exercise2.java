package shu.silence;

public class exercise2 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                count++;
                System.out.println("第" + count + "能被三整除的数字为 " + i);
                if (count == 3)
                    break;
            }
        }
    }
}
