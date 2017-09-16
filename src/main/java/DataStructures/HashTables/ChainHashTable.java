package DataStructures.HashTables;

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

public class ChainHashTable {
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
            int i = (int) c;
            result += i * Math.pow(X, ii++);
        }
        result = (result % MAGIC_NUMBER) % SIZE;
        return (int)result;
    }

    public void addString(String s) {
        int hash = getHash(s);
        Node old = table[hash];
        if (old == null) table[hash] = new Node(s);
        else {
            if (s.equals(old.data)) return;
            while (old.next != null) {
                old = old.next;
                if (s.equals(old.data)) return;
            }
            old.next = new Node(s);
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
        Node old = table[hash];
        if (old == null) System.out.println("no");
        else while(!s.equals(old.data) && old.next != null) {
            old = old.next;
        }
        if (old != null && s.equals(old.data)) System.out.println("yes");
        else System.out.println("no");
    }

    public void check(int i) {
        Node old = table[i];
        if (old == null) System.out.println();
        else {
            for (Node n = old; n.next != null; n = n.next) {
                if (n.data == null) continue;
                else System.out.println(n.data + " ");
            }
        }
    }

    class Node {
        String data;
        Node next = null;

        public Node(String data) {
            this.data = data;
        }
    }
}
