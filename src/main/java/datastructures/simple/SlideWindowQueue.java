package DataStructures.SimpleStructures;

import java.util.EmptyStackException;
import java.util.Scanner;

/*
*  Максимум в скользящем окне
*  Наивный способ решить данную задачу — честно просканировать каждое окно и найти в нём максимум.
*  Время работы такого алгоритма — O(nm). Ваша задача — реализовать алгоритм со временем работы O(n).
*/

public class SlideWindowQueue {

    private MaxStack leftStack = new MaxStack();
    private MaxStack rightStack = new MaxStack();

    private int windowSize;
    private int currentSize;

    public SlideWindowQueue(int size) {
        windowSize = size;
        currentSize = 0;
    }

    public int insertAndGetMax(int i) {
        if (currentSize < windowSize) {
            leftStack.push(i);
            currentSize++;
            return leftStack.max();
        }
        else {
            if (rightStack.isEmpty()) transfer();
            rightStack.pop();
            leftStack.push(i);
            if (rightStack.isEmpty()) return leftStack.max();
            else return Math.max(rightStack.max(), leftStack.max());
        }
    }

    public boolean offer(int i) {
        if (currentSize < windowSize) {
            leftStack.push(i);
            currentSize++;
            return true;
        }
        else return false;
    }

    public int poll() {
        if (currentSize == 0) throw new EmptyStackException();
        if (rightStack.isEmpty()) transfer();
        int ret = rightStack.pop();
        currentSize--;
        return ret;
    }

    public int max() {
        if (isEmpty()) throw new EmptyStackException();
        if (rightStack.isEmpty()) return leftStack.max();
        if (leftStack.isEmpty()) return rightStack.max();
        return Math.max(rightStack.max(), leftStack.max());
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void transfer() {
        while(!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] arr = new int[count];

        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }

        int window = in.nextInt();

        SlideWindowQueue que = new SlideWindowQueue(window);

        for (int i = 0; i < count; i++) {
            if (i < count - 1) que.insertAndGetMax(arr[i]);
            else System.out.println(que.insertAndGetMax(arr[i]));
        }
    }
}
