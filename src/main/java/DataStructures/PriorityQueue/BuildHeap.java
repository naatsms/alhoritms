package DataStructures.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildHeap {

    private int[] arr;
    private int size;
    private int swapCount = 0;
    private List<String> log = new ArrayList<>();

    public BuildHeap(int[] arr) {
        this.arr = arr;
        size = arr.length;
        build();
    }

    private void build() {
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void printLog() {
        System.out.println(swapCount);
        for (String s : log) {
            System.out.println(s);
        }
    }

    private void siftDown(int index) {
        while (index <= size/2 - 1) {
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;
            int minChild;
            if (rightIndex <= size - 1 && arr[rightIndex] <= arr[leftIndex]) minChild = rightIndex;
            else minChild = leftIndex;

            if (arr[index] > arr[minChild]) swap(index, minChild);
            index = minChild;
        }
    }

    private void swap(int first, int second) {
        log.add(first + " " + second);
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
        swapCount++;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }

        BuildHeap heap = new BuildHeap(arr);
        heap.printLog();
    }

}
