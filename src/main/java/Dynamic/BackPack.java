/*
* Первая строка входа содержит целые числа a, b — вместимость рюкзака и число золотых слитков.
* Следующая строка содержит b целых чисел, задающих веса слитков.
* Найдите максимальный вес золота, который можно унести в рюкзаке.
*/

import java.util.Scanner;

public class BackPack {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int weight = in.nextInt();
        int count = in.nextInt();
        int[] items = new int[count];
        for (int i = 0; i < count; i++) {
            items[i] = in.nextInt();
        }
        in.close();

        System.out.println(maxWeight(weight, items));

    }

    public static int maxWeight(int weight, int[] items) {
        int[][] result = new int[items.length + 1][weight+1];
        for (int i = 1; i <= items.length; i++) {
            for (int j = 1; j <= weight; j++) {
                result[i][j] = result[i-1][j];
                if (items[i - 1] < j) {
                    result[i][j] = Math.max(result[i][j], result[i-1][j - items[i - 1]] + items[i - 1]);
                }
            }
        }
        return result[items.length][weight];
    }
}
