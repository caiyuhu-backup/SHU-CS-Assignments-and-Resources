package shu.silence.exer1;

public class exercise1 {
    public static boolean isPrime(int n) {
        int i = 2;
        for (; i < n; i++)
            if (n % i == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        for (int i = 101; i <= 200; i++)
            if (exercise1.isPrime(i)) System.out.println(i);
    }
}
