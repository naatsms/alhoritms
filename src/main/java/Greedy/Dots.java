import java.util.*;

/**
 * По данным n отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержит хотя бы одну из точек.
 * В первой строке дано число 1 ≤ n ≤ 100 отрезков.
 * Каждая из последующих n строк содержит по два числа 0 ≤ l ≤ r ≤ 10^9, задающих начало и конец отрезка.
 * Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.
 */

public class Dots {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt();
        Strike[] arr = new Strike[cnt];
        LinkedList<Integer> dots = new LinkedList<>();

        for (int i = 0; i < cnt; i++) {
            arr[i] = new Strike(in.nextInt(), in.nextInt());
        }
        Arrays.sort(arr);

        dots.addLast(arr[0].bDot);

        for(Strike strike : arr) {
            if (dots.getLast() < strike.aDot) dots.addLast(strike.bDot);
        }

        System.out.println(dots.size());
        for(Integer i : dots) {
            System.out.println(i);
        }

    }
}

class Strike implements Comparable<Strike>{
    int aDot;
    int bDot;

    Strike (int a, int b) {
        aDot = a;
        bDot = b;
    }

    @Override
    public int compareTo(Strike b) {
        if (bDot == b.bDot) return 0;
        return bDot > b.bDot ? 1 : -1;
    }
}
