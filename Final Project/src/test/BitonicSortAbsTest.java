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
        BitonicSort a = new BitonicSort(8, 4);
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