import java.util.LinkedList;
import java.util.Scanner;

/**
 * По данному числу 1 ≤ n ≤ 10^9 найдите максимальное число k, для которого n можно представить как сумму k различных натуральных
 * слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
 */


class Terms {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int number = in.nextInt();
        term(number);

    }

    public static void term(int a) {
        LinkedList<Integer> arr = new LinkedList<Integer>();
        int sum = 1;
        arr.addLast(1);

        for (int i = 2; i <= a; i++) {
            if (a == sum) break;
            if (sum + i > a) {
                arr.addLast(arr.pollLast() + (a - sum));
                System.out.println(arr.getLast());
                break;
            } else {
                sum += i;
                arr.addLast(i);
            }
        }

        System.out.println(arr.size());
        arr.forEach(v -> System.out.print(v + " "));
    }
}
