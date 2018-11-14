package test;

import algo.Mergesort;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class MergesortAbsTest extends AbsTest {

    @Test
    public void sort1() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 14), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 1);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
        System.out.println("Time for " + (int) Math.pow(2, 14) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort2() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 13), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 1);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
        System.out.println("Time for " + (int) Math.pow(2, 13) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort3() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 12), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
        System.out.println("Time for " + (int) Math.pow(2, 12) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort4() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 11), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
        System.out.println("Time for " + (int) Math.pow(2, 11) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort5() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 10), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 10) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort6() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 9), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 9) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort7() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 8), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 8) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort8() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 7), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 7) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort9() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 6), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 6) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort10() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 5), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 5) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort11() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 4), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 4) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort12() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 3), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 3) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort13() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 2), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 2) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort14() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 1), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 1) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }

    @Test
    public void sort15() throws ExecutionException, InterruptedException {
        Mergesort a = new Mergesort(4);
        int[] test = getArray((int) Math.pow(2, 0), -10000000, 10000000);
        int[] target = Arrays.copyOf(test, test.length);
        Arrays.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test, 10000);
        long end = System.currentTimeMillis();
        //System.out.println("Time in Milli-seconds is: " + (end - start));
        System.out.println("Time for " + (int) Math.pow(2, 0) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(Arrays.toString(target), Arrays.toString(test));
    }


}