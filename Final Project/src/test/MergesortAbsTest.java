package test;

import algo.Mergesort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class MergesortAbsTest extends AbsTest {

    private void getIntegers(int[] list, int i, int threadNum) {
        Mergesort a = new Mergesort(threadNum);
        long start = System.currentTimeMillis();
        a.sort(list, 4);
        long end = System.currentTimeMillis();
        System.out.println("Thread number: " + threadNum +". Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
    }

    @Test
    public void sort() {
        for (int i = 0;i < 15;++i) {
            int[] list = getArray((int) Math.pow(2, i), -1000000, 1000000);
            int[] target = Arrays.copyOf(list, list.length);
            Arrays.sort(target);
            for (int j = 0;j < 4;++j) {
                int[] copy = Arrays.copyOf(list, list.length);
                int threadNum = (int) Math.pow(2, j);
                getIntegers(copy, i, threadNum);
                Assert.assertEquals(Arrays.toString(target), Arrays.toString(copy));
            }
        }
    }
}