import java.util.Arrays;
import java.util.Scanner;

class InversionCount {

    static long count = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int arrLength = in.nextInt();
        int[] arr = new int[arrLength];

        for (int i = 0; i < arrLength; i++) {
            arr[i] = in.nextInt();
        }

        int[] arr2 = mergeSort(arr);

        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }

        System.out.println(count);

    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr;
        int tim = arr.length >> 1;
        return merge(mergeSort(Arrays.copyOfRange(arr, 0, tim)), mergeSort(Arrays.copyOfRange(arr, tim, arr.length)));
    }

    public static int[] merge(int[] arrj, int[] arrk) {

        int[] arrf = new int[arrj.length + arrk.length];
        int j = 0;
        int k = 0;
        int i = 0;

        for (; j < arrj.length && k < arrk.length; i++) {
            if (arrj[j] > arrk[k]) {
                arrf[i] = arrk[k];
                count += arrj.length - j;
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
