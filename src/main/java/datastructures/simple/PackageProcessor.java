package datastructures.simple;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class PackageProcessor {
    protected int time = 0;
    protected Queue<Package> que;
    protected List<Integer> log = new LinkedList<Integer>();

    public PackageProcessor(int size) {
        que = new ArrayBlockingQueue<>(size);
    }

    public void recievePackage(Package pack) {
        if (que.offer(pack)) {
            if (pack.arrival > time) {
                time = pack.arrival;
                log.add(time);
                time += pack.duration;
                pack.finishProcessing = time;
            } else {
                log.add(time);
                time += pack.duration;
                pack.finishProcessing = time;
            }
        } else {
            Package off = que.peek();
            if (pack.arrival >= off.finishProcessing) {
                que.poll();
                que.offer(pack);
                if (pack.arrival > time) {
                    time = pack.arrival;
                    log.add(time);
                    time += pack.duration;
                    pack.finishProcessing = time;
                }
                else {
                    log.add(time);
                    time += pack.duration;
                    pack.finishProcessing = time;
                }
            }
            else log.add(-1);
        }
    }

    public List<Integer> processPackages(Package[] packs) {
        for (Package pack : packs) {
            recievePackage(pack);
        }
        return log;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PackageProcessor processor = new PackageProcessor(in.nextInt());
        int count = in.nextInt();
        Package[] packs = new Package[count];

        for (int i = 0; i < count; i++) {
            packs[i] = new Package(in.nextInt(), in.nextInt());
        }

        List<Integer> result = processor.processPackages(packs);

        for (Integer i : result) {
            System.out.println(i);
        }
    }

    public static class Package {
        int arrival;
        int duration;
        int finishProcessing;

        public Package(int arrival, int duration) {
            this.arrival = arrival;
            this.duration = duration;
        }
    }
}
