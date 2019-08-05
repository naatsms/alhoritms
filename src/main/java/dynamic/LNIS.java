import java.util.Scanner;

/*
Дано целое число 1 ≤ n ≤ 10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9.
Найдите наибольшую невозрастающую подпоследовательность в A.
В первой строке выведите её длину k, во второй — её индексы 1 ≤ i1 < i2 <…< ik ≤ n
(таким образом, A[i1] ≥ A[i2] ≥…≥A[in].
*/

public class LNIS {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        in.close();
        int[] result = bottomUp(arr);
        System.out.println(result.length);
        StringBuilder sb = new StringBuilder();
        for (int a : result) {
            sb.append(a);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static int[] bottomUp(int[] arr) {
        int[] table = new int[arr.length];
        int[] lasts = new int[arr.length + 1];
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            int low = 1;
            int high = length;
            while (low <= high) {
                int mid = (int)Math.ceil((low + high)/2);
                if(arr[lasts[mid]] >= arr[i]) low = mid + 1;
                else high = mid - 1;
            }
            int pos = low;
            table[i] = lasts[pos - 1];
            lasts[pos] = i;
            if (pos > length) length = pos;
        }

        int[] result = new int[length];
        int k = lasts[length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = k + 1;
            k = table[k];
        }
        return result;
    }

}
