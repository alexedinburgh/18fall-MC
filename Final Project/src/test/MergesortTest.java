package test;

import algo.Mergesort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class MergesortTest {

    @Test
    public void sort() throws ExecutionException, InterruptedException {
//        int[] list = new int[]{1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3};
//        int[] target = new int[]{-3, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 8, 12};

        Mergesort a = new Mergesort(8);
        int[] test = a.getRandomArray(10000000, -100000, 100000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.nanoTime();
        a.sort(test, 1000);
        long end = System.nanoTime();
        //System.out.println(Arrays.toString(input));
        System.out.println(end - start);
//        System.out.println(Arrays.toString(test));
//        System.out.println(Arrays.toString(target));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }
}