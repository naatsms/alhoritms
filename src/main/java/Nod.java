import java.util.Scanner;

class Nod {
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(nod(a, b));

    }

    private static int nod(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a >= b) return nod(a % b, b);
        else return nod(a, b % a);
    }
}