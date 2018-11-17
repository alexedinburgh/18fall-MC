package test;

import algo.Mergesort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class MergesortAbsTest extends AbsTest {

    private void getIntegers(int[] list, int i, int threadNum) {
        int[] target = Arrays.copyOf(list, list.length);
        Arrays.sort(target);
        Mergesort a = new Mergesort(8);
        long start = System.currentTimeMillis();
        a.sort(list, 4);
        long end = System.currentTimeMillis();
        System.out.println("Thread number: " + threadNum +". Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(list));
    }

    @Test
    public void sort() {
        for (int i = 0;i < 4;++i) {
            int threadNum = (int) Math.pow(2, i);
            for (int j = 0;j < 15;++j) {
                int[] list = getArray((int) Math.pow(2, j), -1000000, 1000000);
                getIntegers(list, j, threadNum);
            }
        }
    }
}