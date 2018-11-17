package test;

import algo.BrickSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


public class BrickSortTest extends AbsTest{

    private ArrayList<Integer> getIntegers(ArrayList<Integer> list, int i, int threadNum) {
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Thread number: " + threadNum +". Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
        return target;
    }

    @Test
    public void sort() {
        for (int i = 0;i < 4;++i) {
            int threadNum = (int) Math.pow(2, i);
            for (int j = 0;j < 15;++j) {
                ArrayList<Integer> list = getArrayList((int) Math.pow(2, j), -1000000, 1000000);
                ArrayList<Integer> target = getIntegers(list, j, threadNum);
                Assert.assertEquals(target.toString(), list.toString());
            }
        }
    }
}