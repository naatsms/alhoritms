import java.util.Scanner;

/**
 * У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x: заменить x на 2x, 3x или x+1.
 * По данному целому числу N определите минимальное число операций k, необходимое, чтобы получить n из 1.
 * Выведите k и последовательность промежуточных чисел.
 */

public class Calc {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[] prev = new int[number + 1];
        int[] k = new int[number + 1];
        int key = findK(number, prev, k);
        System.out.println(key);

        int[] result = new int[key+1];
        for (int i = number; i > 0 && key >= 0; ) {
            result[key] = i;
            i = prev[i];
            key--;
        }

        for (int i : result) {
            System.out.print(i + " ");
        }


    }

    public static int findK(int n, int[] prev, int[] k) {
        if (n == 1) return 0;
        for (int i = 2; i <= n; i++) {
            if (i == 2 || i == 3) {
                k[i] = 1;
                prev[i] = 1;
                continue;
            }
            int a = Integer.MAX_VALUE;
            int b = Integer.MAX_VALUE;
            int c = k[i-1];
            if (i % 2 == 0) {
                a = k[i/2];
            }
            if (i % 3 == 0) {
                b = k[i/3];
            }
            int min = min(a, b, c);
            if (min == a) prev[i] = i/2;
            else if (min == c) prev[i] = i-1;
            else if (min == b) prev[i] = i/3;
            k[i] = min + 1;
        }
        return k[n];
    }

    public static int min(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }


}
