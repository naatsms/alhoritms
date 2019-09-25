package divineconquer;

import java.lang.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Qsort3
{
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] arr = new int[] {5, 4, 3, 2, 1, 2, 3, 4, 5, 0, 0, 0, 1, -11, 12, 55, 3, 3, 12};
        qsort3(arr);

        for(int a: arr) {
            System.out.print(a + " ");
        }
    }

    public static void qsort3 (int[] arr) {
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

    public static void swap (int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
