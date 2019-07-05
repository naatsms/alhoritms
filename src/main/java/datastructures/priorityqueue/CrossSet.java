package DataStructures.PriorityQueue;

import java.util.*;

/**
 * Created by Мишаня on 02.05.2017.
 */
public class CrossSet {

    int maxSize = 0;
    private Map<Integer, Table> tables = new HashMap<>();
    private List<Integer> log = new ArrayList<>();

    public void makeSet(int id, int size) {
        tables.put(id, new Table(id, size));
        if (size > maxSize) maxSize = size;
    }

    public void printLog(){
        for (Integer i : log) {
            System.out.println(i);
        }
    }

    public void union(int destId, int sourceId) {
        Table dest = find(destId);
        Table source = find(sourceId);
        if (dest.id != source.id) {
            source.parent = dest;
            dest.size += source.size;
            if (dest.size > maxSize) maxSize = dest.size;
        }
        log.add(maxSize);
    }

    public Table find(int id) {
        Table source = tables.get(id);
        List<Table> temp = new LinkedList<>();
        while(source.parent != source) {
            temp.add(source);
            source = source.parent;
        }
        for (Table t : temp) {
            t.parent = source;
        }
        return source;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CrossSet set = new CrossSet();
        int tables = in.nextInt();
        int actions = in.nextInt();

        for (int i = 1; i <= tables; i++) {
            set.makeSet(i, in.nextInt());
        }

        for (int i = 0; i < actions; i++) {
            set.union(in.nextInt(), in.nextInt());
        }

        set.printLog();
    }

    class Table {
        int id;
        int size;
        Table parent;

        public Table(int id, int size){
            this.id = id;
            this.size = size;
            parent = this;
        }
    }
}
