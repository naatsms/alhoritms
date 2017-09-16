import java.util.Scanner;

/**
 * В первой строке даны целое число 1 ≤ n ≤ 10^5 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
 * в порядке возрастания, во второй — целое число 1 ≤ k ≤ 10^5 и k натуральных чисел b1,…,bk, не превышающих 10^9.
 * Для каждого i от 1 до k необходимо вывести индекс 1 ≤ j ≤ n, для которого A[j]=bi, или −1, если такого j нет.
 */
public class BinarySearch {
    private int[] arr;
    private int length;

    public static void main (String[] args) {
        long start = System.currentTimeMillis();
        new BinarySearch().go();
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

    void go(){
        Scanner in = new Scanner(System.in);

        length = in.nextInt();
        arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = in.nextInt();
        }

        int count = in.nextInt();
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < count; j++) {
            str.append(binarySearch(in.nextInt()));
            str.append(' ');
        }
        System.out.print(str.toString());
    }

    private int binarySearch(int k) {
        int first = 0;
        int last = length - 1;
        int cur = length / 2;

        while (first <= last) {
            if (k == arr[cur]) return cur + 1;

            if (k < arr[cur]) last = cur - 1;
            else if (k > arr[cur]) first = cur + 1;
            cur = first + ((last - first) / 2);
        }
        return -1;
    }
}
