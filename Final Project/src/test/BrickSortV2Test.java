package test;

import algo.BrickSortV2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;


public class BrickSortV2Test extends AbsTest {

    private void getIntegers(ArrayList<Integer> list, int i, int threadNum) {
        BrickSortV2 a = new BrickSortV2(threadNum, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Thread number: " + threadNum +". Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
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