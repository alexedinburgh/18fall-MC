package test;

import algo.BitonicSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class BitonicSortAbsTest extends AbsTest {

    private ArrayList<Integer> getIntegers(ArrayList<Integer> list, int i, int threadNum) {
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BitonicSort a = new BitonicSort(threadNum, 4);
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
                ArrayList<Integer> copy = new ArrayList<>(list);
                int threadNum = (int) Math.pow(2, j);
                getIntegers(copy, i, threadNum);
                Assert.assertEquals(target.toString(), copy.toString());
            }
        }
    }
}