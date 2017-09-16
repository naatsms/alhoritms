/*
* Дано целое число 1 ≤ n ≤ 10-3 и массив A[1…n] натуральных чисел, не превосходящих 2⋅10-9.
* Выведите максимальное 1 ≤ k ≤ n, для которого найдётся подпоследовательность длины k,
* в которой каждый элемент делится на предыдущий.
*/

import java.util.Scanner;

public class LongestIncSubsequence {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        in.close();
        System.out.println(bottomUp(arr));
    }

    private static int bottomUp(int[] arr) {
        int[] table = new int[arr.length];
        for (int i = 0; i < table.length; i++) {
            table[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i] && arr[i] % arr[j] == 0) {
                    if (table[j] >= table[i]) {
                        table[i] = table[j] + 1;
                    }
                }
            }
        }
        int max = 0;
        for (int i : table) {
            if (i > max) max = i;
        }
        return max;
    }
}
