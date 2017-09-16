package DataStructures.PriorityQueue;

import java.util.*;

public class Analyzer {

    private Map<Integer, Node> sets = new HashMap<>();

    public void MakeSet(int id) {
        sets.put(id, new Node(id));
    }

    public Node find(int id) {
        Node n = sets.get(id);
        List<Node> temp = new ArrayList<>();
        while (n.parent != n) {
            temp.add(n);
            n = n.parent;
        }
        for (Node node : temp) node.parent = n;
        return n;
    }

    public void union(int fir, int sec) {
        Node first = find(fir);
        Node second = find(sec);

        if (first.id != second.id) {
            second.parent = first;
        }
    }

    public boolean isDiff(int f, int s) {
        Node first = find(f);
        Node second = find(s);
        if (first != second) return true;
        return false;
    }

    public static void main(String[] args) {
        Analyzer an = new Analyzer();
        Scanner in = new Scanner(System.in);
        int vars = in.nextInt();
        int unions = in.nextInt();
        int un = in.nextInt();

        for (int i = 1; i <= vars; i++) {
            an.MakeSet(i);
        }

        for (int i = 0; i < unions; i++) {
            an.union(in.nextInt(), in.nextInt());
        }

        for (int i = 0; i < un; i++) {
            if (!an.isDiff(in.nextInt(), in.nextInt())) {
                System.out.println(0);
                System.exit(0);
            }
        }
        System.out.println(1);
    }

    class Node {
        int id;
        Node parent;

        public Node(int id) {
            this.id = id;
            parent = this;
        }

    }
}
