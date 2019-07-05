package datastructures.priorityqueue;

import java.util.*;

public class TaskProcessor {
    private Queue<Processor> que;
    private List<String> log = new LinkedList<>();

    public TaskProcessor(int count) {
        que = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            que.offer(new Processor(i));
        }
    }

    public void addTask(long time) {
        Processor worker = que.poll();
        worker.assignTask(time);
        que.offer(worker);
    }

    public void printLog() {
        for (String s : log) {
            System.out.println(s);
        }
    }

    class Processor implements Comparable<Processor> {
        private final int index;
        private int time = 0;

        public Processor(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Processor o) {
            return (time != o.time) ? time - o.time : index - o.index;
        }

        public void assignTask(long time) {
            TaskProcessor.this.log.add(index + " " + this.time);
            this.time += time;
        }
    }
}
