import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class StrikesDots {

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);
        int strikes = in.nextInt();
        int dots = in.nextInt();

        int[] starts = new int[strikes];
        int[] finishs = new int[strikes];

        for (int i = 0; i < strikes; i++) {
            starts[i] = in.nextInt();
            finishs[i] = in.nextInt();
        }

        qsort(starts);
        qsort(finishs);

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < dots; i++) {
            int num = in.nextInt();
            int a = searchFinishs(num, finishs);
            System.out.println(a);
            int b = searchStarts(num, starts);
            System.out.println(b);
            buf.append((strikes - a - b) + " ");
        }

        String answer = buf.toString();
        System.out.println(answer);

    }

    public static void qsort (int[] arr) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, arr.length - 1});

        while(!que.isEmpty()) {
            int start = que.peek()[0];
            int end = que.poll()[1];

            int leftCount = start + 1;
            int medianCount = 1;
            int median = (int) (Math.random() * (end - start + 1)) + start;

            swap(arr, start, median);

            for (int i = start + 1; i <= end; i++) {
                if (arr[i] == arr[start]) {
                    swap(arr, i, start + medianCount++);
                    if (arr[i] < arr[start]) swap(arr, i, leftCount);
                    leftCount++;
                    continue;
                }
                if (arr[i] < arr[start] && leftCount <= end) swap(arr, i, leftCount++);
            }

            for (int i = 0; i < medianCount; i++) {
                swap(arr, start + i, leftCount - 1 - i);
            }

            int lEnd = leftCount - medianCount - 1;
            int rEnd = leftCount;

            if (lEnd > start) que.add(new int[]{start, lEnd});
            if (rEnd < end) que.add(new int[]{rEnd, end});
        }
    }

    public static int searchFinishs(int k, int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int cur = 0;
        if (arr[last] < k) return arr.length;
        if (arr[0] > k) return 0;

        while (first <= last) {
            cur = first + ((last - first) / 2);
            if (k == arr[cur]) break;

            if (k < arr[cur]) last = cur - 1;
            else first = cur + 1;
        }
        while (arr[cur] >= k && cur > 0) cur -= 1;
        if (arr[cur] >= k) return cur;
        return cur + 1;
    }

    public static int searchStarts(int k, int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int cur = 0;
        if (arr[0] > k) return arr.length;
        if (arr[last] < k) return 0;

        while (first <= last) {
            cur = first + ((last - first) / 2);
            if (k == arr[cur]) break;

            if (k < arr[cur]) last = cur - 1;
            else first = cur + 1;
        }

        while (arr[cur] <= k && cur < arr.length - 1) cur += 1;
        if (arr[cur] <= k) return arr.length - (cur + 1);
        return arr.length - (cur);
    }

    public static void swap (int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
