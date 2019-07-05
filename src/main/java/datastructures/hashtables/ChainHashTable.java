package datastructures.hashtables;

import java.math.BigInteger;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());
        int commands = Integer.parseInt(in.nextLine());

        ChainHashTable table = new ChainHashTable(count);

        for (int i = 0; i < commands; i++) {
            String[] s = in.nextLine().split(" ");

            if (s[0].equals("add")) {
                table.addString(s[1]);
            }
            else if (s[0].equals("del")) {
                table.delString(s[1]);
            }
            else if (s[0].equals("find")) {
                table.find(s[1]);
            }
            else if (s[0].equals("check")) {
                int index = Integer.parseInt(s[1]);
                table.check(index);
            }
        }
    }
}

class ChainHashTable {
    private long MAGIC_NUMBER = 1000000007;
    private int X = 263;
    private int SIZE;
    private Node[] table;

    public ChainHashTable(int size) {
        table = new Node[size];
        SIZE = size;
    }

    private int getHash(String s) {
        long result = 0;
        int ii = 0;
        for (char c : s.toCharArray()) {
            BigInteger i = BigInteger.valueOf((long) c);

            result += i.multiply(BigInteger.valueOf(X).pow(ii++)).mod(BigInteger.valueOf(MAGIC_NUMBER)).intValue();
            result = result % MAGIC_NUMBER;
        }
        result = (result % MAGIC_NUMBER) % SIZE;
        return (int)result;
    }

    public void addString(String s) {
        int hash = getHash(s);
        if (!find(s, hash)) {
            Node newNode = new Node(s);
            Node old = table[hash];
            if (old == null) table[hash] = newNode;
            else {
                newNode.next = old;
                table[hash] = newNode;
            }
        }
    }

    public void delString(String s) {
        int hash = getHash(s);
        Node old = table[hash];
        if (old == null) return;
        else while(!s.equals(old.data) && old.next != null) {
            old = old.next;
        }
        if (s.equals(old.data)) old.data = null;
    }

    public void find(String s) {
        int hash = getHash(s);
        System.out.println(find(s, hash) ? "yes" : "no");
    }

    private boolean find(String s, int cell) {
        Node old = table[cell];
        if (old == null) {
            return false;
        }
        else while(!s.equals(old.data) && old.next != null) {
            old = old.next;
        }
        if (s.equals(old.data)) return true;
        else return false;
    }

    public void check(int i) {
        Node old = table[i];
        if (old != null) {
            for (Node n = old;; n = n.next) {
                if (n.data != null) System.out.print(n.data + " ");
                if (n.next == null) break;
            }
        }
        System.out.println();
    }

    class Node {
        String data;
        Node next = null;

        public Node(String data) {
            this.data = data;
        }
    }
}
