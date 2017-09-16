import java.util.*;

/**
 * По данной непустой строке ss длины не более 104104, состоящей из строчных букв латинского алфавита,
 * постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв kk,
 * встречающихся в строке, и размер получившейся закодированной строки.
 * В следующих kk строках запишите коды букв в формате "letter: code".
 * В последней строке выведите закодированную строку.
 */

public class HoffmanCode {

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);
        char[] arr = in.nextLine().toCharArray();

        TreeMap<Character, Integer> map = new TreeMap<>(); // здесь храним частоты символов
        Queue<Node> que = new PriorityQueue<>(); // сюда складываем узлы дерева
        TreeMap<Character, Node> codeMap = new TreeMap<>(); // здесь храним код каждого символа

        for (Character a : arr) {
            map.merge(a, 1, (k, v) -> k + v);
        }

        new Integer((int)(Math.random() * 10));

        //если все символы в строке одинаковые
        if (map.size() == 1) {
            System.out.println(1 + " " + arr.length);
            System.out.println(map.firstKey() + ": " + 0);
            for (int i = 0; i < arr.length; i++) {
                System.out.print("0");
            }
            return;
        }

        for (Map.Entry<Character, Integer> pair : map.entrySet()) {
            Node a = new LeafNode(pair.getKey(), pair.getValue());
            que.add(a);
            codeMap.put(pair.getKey(), a);
        }

        int totalSum = 0;

        while(que.size() > 1) {
            Node a = que.poll();
            Node b = que.poll();
            InnerNode c = new InnerNode(a, b);
            totalSum += c.sum;
            que.add(new InnerNode(a, b));
        }

        Node first = que.poll();
        first.makeCode("");

        StringBuilder codeString = new StringBuilder();
        for (char a : arr) {
            codeString.append(codeMap.get(a).code);
        }

        System.out.println(map.size() + " " + codeString.length());
        codeMap.forEach((k, v) -> System.out.println(k + ": " + v.code));

        System.out.println(codeString.toString());
    }
}

abstract class Node implements Comparable<Node> {
    int sum;
    String code;

    @Override
    public int compareTo(Node o) {
        return sum - o.sum;
    }

    @Override
    public String toString() {
        return sum + " " + code;
    }

    abstract void makeCode(String a);

}

class InnerNode extends Node {
    Node left;
    Node right;

    public InnerNode(Node a, Node b){
        sum = a.sum + b.sum;
        left = a;
        right = b;
    }

    @Override
    public void makeCode(String a) {
        code = a;
        left.makeCode(a + "0");
        right.makeCode(a + "1");
    }
}

class LeafNode extends Node {
    char symbol;

    public LeafNode(char a, int b){
        symbol = a;
        sum = b;
    }

    @Override
    void makeCode(String a) {
        code = a;
    }

    @Override
    public String toString() {
        return symbol +": " + sum;
    }
}
