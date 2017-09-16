import java.util.Arrays;
import java.util.Scanner;

/**
 * Первая строка входа содержит число операций 1 ≤ n ≤ 10^5.
 * Каждая из последующих nn строк задают операцию одного из следующих двух типов:
 * Insert xx, где 0 ≤ x ≤ 10^9 — целое число;
 * ExtractMax.
 * Первая операция добавляет число xx в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 */

public class BinaryHeap <T> {
    private final static int DEFAILT_SIZE = 8;
    private final static int DEFAILT_CONTAINER = 8;

    private Object[] arr;
    private int currentSize;

    public BinaryHeap() {
        arr = new Object[DEFAILT_CONTAINER];
        currentSize = DEFAILT_SIZE;
    }

    public void go(){

    }

    public void insert(T next) {
        sizeCheck();
        arr[currentSize] = next;
        siftUp(currentSize);
    }

    private void sizeCheck() {
        if (currentSize + 1 == arr.length) {
            arr = Arrays.copyOf(arr, arr.length + currentSize);
            currentSize++;
            for (int i = currentSize + 1; i < arr.length; i++) {
                arr[i] = null;
            }
        } else currentSize++;
    }

    //сделать проверку, есть ли в объекте компаратор. Если есть - использовать компаратор, если нет, приводить объекты к Comparable и сравнивать так.
    @SuppressWarnings("unchecked")
    private void siftUp(int n) {
        while(n != 1) {
            Comparable<T> key = (Comparable<T>) arr[n/2];
            if (key.compareTo((T)arr[n]) > 0) {
                Object temp = arr[n];
                arr[n] = arr[n / 2];
                arr[n / 2] = temp;
            }
            n = n / 2;
        }
    }

    @SuppressWarnings("unchecked")
    public T extract() {
        T out = (T) arr[1];
        arr[1] = arr[currentSize];
        arr[currentSize--] = Integer.MIN_VALUE;
       // siftDown();
        return out;
    }
    @SuppressWarnings("unchecked")
//    private void siftDown() {
//        int n = 1;
//        while (n*2 <= currentSize) {
//            if (arr[2 * n] < arr[2 * n + 1]) {
//                Object temp = arr[n];
//                arr[n] = arr[2 * n + 1];
//                arr[2 * n + 1] = temp;
//                n = 2 * n + 1;
//            } else {
//                Object temp = arr[n];
//                arr[n] = arr[2 * n];
//                arr[2 * n] = temp;
//                n = 2 * n;
//            }
//        }
//    }

    public static void main (String[] args) {

        BinaryHeap<Integer> heap = new BinaryHeap<>();

        Scanner in = new Scanner(System.in);
        int cnt = in.nextInt();
        for (int i = 0; i < cnt; i++) {
            String command = in.next();
            if (command.equals("Insert")) {
                heap.insert(in.nextInt());
            }
            if (command.equals("ExtractMax")) System.out.println(heap.extract());
        }



    }
}
