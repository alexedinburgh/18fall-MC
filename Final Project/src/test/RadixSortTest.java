package test;

import algo.RadixSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class RadixSortTest extends AbsTest{

    private ArrayList<Integer> getIntegers(ArrayList<Integer> list, int i, int threadNum) {
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        RadixSort a = new RadixSort(threadNum);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Thread number: " + threadNum +". Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
        return target;
    }

    @Test
    public void sort() {
        for (int i = 0;i < 15;++i) {
            ArrayList<Integer> list = getArrayList((int) Math.pow(2, i), -1000000, 1000000);
            ArrayList<Integer> target = new ArrayList<>(list);
            Collections.sort(target);
            for (int j = 0;j < 4;++j) {
                int threadNum = (int) Math.pow(2, j);
                getIntegers(list, i, threadNum);
                Assert.assertEquals(target.toString(), list.toString());
            }
        }
    }
}