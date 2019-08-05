import java.util.Scanner;

public class DistanceOfEditing {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] a = in.nextLine().toCharArray();
        char[] b = in.nextLine().toCharArray();
        in.close();

        System.out.println(editDist(a, b));
    }

    public static int editDist(char[] a, char[] b) {
        int[][] result = new int[a.length][b.length];
        for (int i = 0; i < b.length; i++) {
            result[0][i] = i;
        }
        for (int i = 0; i < a.length; i++) {
            result[i][0] = i;
        }

        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[i].length; j++) {
                result[i][j] = min(result[i-1][j] + 1, result[i][j-1] + 1, result[i-1][j-1] + diff(b[j-1], a[i-1]));
            }
        }
        return result[a.length-1][b.length-1];
    }

    public static int diff(char a, char b) {
        return a != b ? 1 : 0;
    }

    public static int min(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }

}
