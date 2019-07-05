package datastructures.simple;

import java.util.Scanner;

public class TreeHeight {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        in.close();

        System.out.println(findHeight(arr));

    }

    public static int findHeight(int... arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int height = siftUp(i, arr);
            if (height > result) result = height;
        }
        return result;
    }

    public static int siftUp(int index, int... arr) {
        if (arr[index] == -1) return 1;
        int height = 2;
        int parent = arr[index];
        while (arr[parent] != -1) {
            height++;
            parent = arr[parent];
        }
        return height;
    }

}
