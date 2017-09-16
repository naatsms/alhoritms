import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Sorter {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new Sorter().go();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void go() {
        int[] arr = new int[3000000];
        for (int i = 0; i < 10; i++ ) {
            arr[i] = (int) (Math.random() * 100000);
        }
        mergeSort(arr);
    }

    public static void stupid(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                int temp = arr[i - 1];
                arr[i - 1] = arr[i];
                arr[i] = temp;
                i = 0;
            }
        }
    }

    public static void insertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j - 1] = temp;
                    j--;
            }
        }
    }

    public static void bubble (int[] arr) {

        int count = 0;

        for (int j = arr.length; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    count++;
                }
            }
            if (count == 0) break;
            else count = 0;
        }
    }

    public static void shaker (int[] arr) {

        for (int j = arr.length, k = 0; j > k; j--, k++) {
            for (int i = 0; i < j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            for (int i = j - 1; i > k; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void evenodd (int[] arr) {
        int count = 0;
        while (true){
            for (int i = 0; i <= arr.length - 2; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    count++;
                }
            }
            for (int i = 1; i <= arr.length - 2; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    count++;
                }
            }
            if (count == 0) break;
            else count = 0;
        }
    }

    public static void comb (int[] arr) {
        float lf = 1.247f;
        int ef = (int) (arr.length / lf);
        for (; ef > 0; ef = (int)(ef/lf) ) {
            for (int i = 0; i < arr.length - ef; i++) {
                if (arr[i] > arr[i + ef]) {
                    int temp = arr[i];
                    arr[i] = arr[i + ef];
                    arr[i + ef] = temp;
                }
            }
        }
        bubble(arr);
    }

    public static void quick (int[] arr, int start, int end) {

        int a = start;
        int z = end;
        int base = arr[end];

        do {
            while (arr[a] < base) a++;
            while (arr[z] > base) z--;

            if (a <= z) {
                int temp = arr[a];
                arr[a] = arr[z];
                arr[z] = temp;
                a++;
                z--;
            }
        } while (a < z);

        if (z > start) quick(arr, start, z);
        if (end > a) quick(arr, a, end);
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

            Qsort3.swap(arr, start, median);

            for (int i = start + 1; i <= end; i++) {
                if (arr[i] == arr[start]) {
                    Qsort3.swap(arr, i, start + medianCount++);
                    if (arr[i] < arr[start]) Qsort3.swap(arr, i, leftCount);
                    leftCount++;
                    continue;
                }
                if (arr[i] < arr[start] && leftCount <= end) Qsort3.swap(arr, i, leftCount++);
            }

            for (int i = 0; i < medianCount; i++) {
                Qsort3.swap(arr, start + i, leftCount - 1 - i);
            }

            int lEnd = leftCount - medianCount - 1;
            int rEnd = leftCount;

            if (lEnd > start) que.add(new int[]{start, lEnd});
            if (rEnd < end) que.add(new int[]{rEnd, end});
        }
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr;
        int tim = arr.length >> 1;
        return merge(mergeSort(Arrays.copyOfRange(arr, 0, tim)), mergeSort(Arrays.copyOfRange(arr, tim, arr.length)));
    }

    public static int[] merge(int[] arrj, int[] arrk) {

        int[] arrf = new int[arrj.length + arrk.length];
        int j = 0, k = 0, i = 0;

        for (; j < arrj.length && k < arrk.length; i++) {
            if (arrj[j] > arrk[k]) {
                arrf[i] = arrk[k];
                k++;
            } else {
                arrf[i] = arrj[j];
                j++;
            }
        }

        if (j < arrj.length) {
            for (; i < arrf.length; i++, j++) {
                arrf[i] = arrj[j];
            }
        } else if (k < arrk.length) {
            for (; i < arrf.length; i++, k++) {
                arrf[i] = arrk[k];
            }
        }
        return arrf;
    }

}
