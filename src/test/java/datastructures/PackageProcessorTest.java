package datastructures;

import datastructures.simple.PackageProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class PackageProcessorTest extends PackageProcessor {

    public PackageProcessorTest() {
        super(2);
    }

    @Test
    public void processPackages() throws Exception {

        Package[] arr1 = {new Package(0,0),
                        new Package(1, 0)};
        List<Integer> list1 = processPackages(arr1);
        Assert.assertEquals(Arrays.asList(0, 1), list1);

        que.clear();
        log.clear();
        time = 0;

        Package[] arr2 = {new Package(0,0),
                new Package(0, 0),
                new Package(0, 0),
                new Package(1, 0),
                new Package(1, 0),
                new Package(1, 1),
                new Package(1, 2),
                new Package(1, 3)};
        List<Integer> list2 = processPackages(arr2);
        Assert.assertEquals(Arrays.asList(0,0,0,1,1,1,2,-1), list2);

        que.clear();
        log.clear();
        time = 0;

        Package[] arr3 = {new Package(0,0),
                        new Package(0, 0),
                        new Package(0, 0),
                        new Package(1, 1),
                        new Package(1, 0),
                        new Package(1, 0),
                        new Package(1, 2),
                        new Package(1, 3)};
        List<Integer> list3 = processPackages(arr3);
        Assert.assertEquals(Arrays.asList(0,0,0,1,2,-1,-1,-1), list3);

        que.clear();
        log.clear();
        time = 0;

        Package[] arr4 = {new Package(0,2),
                new Package(0, 0),
                new Package(2, 0),
                new Package(3, 0),
                new Package(4, 0),
                new Package(5, 0)};
        List<Integer> list4 = processPackages(arr4);
        Assert.assertEquals(Arrays.asList(0,2,2,3,4,5), list4);

        que = new ArrayBlockingQueue<Package>(3);
        log.clear();
        time = 0;

        Package[] arr5 = {new Package(0,7),
                        new Package(0, 0),
                        new Package(2, 0),
                        new Package(3, 3),
                        new Package(4, 0),
                        new Package(5, 0)};
        List<Integer> list5 = processPackages(arr5);
        Assert.assertEquals(Arrays.asList(0,7,7,-1,-1,-1), list5);

        que = new ArrayBlockingQueue<Package>(1);
        log.clear();
        time = 0;

        Package[] arr6 = {new Package(999999,1),
                new Package(1000000, 0),
                new Package(1000000, 1),
                new Package(1000000, 0),
                new Package(1000000, 0)};
        List<Integer> list6 = processPackages(arr6);
        Assert.assertEquals(Arrays.asList(999999,1000000,1000000,-1,-1), list6);

        que = new ArrayBlockingQueue<Package>(1);
        log.clear();
        time = 0;

        Package[] arr7 = {new Package(0, 0),
                        new Package(0, 3),
                        new Package(1, 10),//
                        new Package(5, 5),
                        new Package(11, 0),
                        new Package(11, 1),
                        new Package(11, 2)
        };
        List<Integer> list7 = processPackages(arr7);
        Assert.assertEquals(Arrays.asList(0,0,-1,5,11,11,-1), list7);

        que = new ArrayBlockingQueue<Package>(1);
        log.clear();
        time = 0;

        Package[] arr8 = {
                new Package(15, 23),
                new Package(24, 44),
                new Package(39, 43),
                new Package(48, 15),
                new Package(56, 6),
                new Package(56, 8),
                new Package(56, 29),
                new Package(56, 28),
                new Package(56, 4),
                new Package(56, 17),
                new Package(68, 44),
                new Package(75, 22),
                new Package(75, 34),
                new Package(84, 46),
                new Package(84, 21),
                new Package(84, 25),
                new Package(97, 31),
                new Package(105, 34),
                new Package(105, 43),
                new Package(117, 17),
                new Package(129, 12),
                new Package(142, 47),
                new Package(144, 22),
                new Package(144, 18),
                new Package(152, 9)};
        List<Integer> list8 = processPackages(arr8);
        Assert.assertEquals(Arrays.asList(15, -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 84, -1, -1, -1, -1, -1, -1, -1, 142, -1, -1, -1), list8);

        que = new ArrayBlockingQueue<Package>(7);
        log.clear();
        time = 0;

        Package[] arr9 = {
                new Package(0, 21),
                new Package(10, 35),
                new Package(10, 12),
                new Package(21, 13),
                new Package(35, 11),
                new Package(35, 14),
                new Package(51, 49),
                new Package(59, 33),
                new Package(59, 43),
                new Package(67, 42),
                new Package(80, 14),
                new Package(93, 45),
                new Package(93, 38),
                new Package(100, 8),
                new Package(101, 31),
                new Package(108, 46),
                new Package(123, 22),
                new Package(127, 20),
                new Package(139, 7),
                new Package(142, 43),
                new Package(142, 12),
                new Package(142, 25),
                new Package(154, 25),
                new Package(154, 5),
                new Package(154, 42)};
        List<Integer> list9 = processPackages(arr9);
        Assert.assertEquals(Arrays.asList(0, 21, 56, 68, 81, 92, 106, 155, 188, -1, 231, 245, 290, -1, -1, 328, -1, -1, -1, -1, -1, -1, -1, -1, -1), list9);


    }

}