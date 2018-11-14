package test;

import algo.Mergesort;
import algo.QuickSort;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class QuickSortTest extends AbsTest {

    @Test
    public void sort1() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 14), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 14);
        Assert.assertEquals(target.toString(), test.toString());
    }

    private ArrayList<Integer> getIntegers(QuickSort a, ArrayList<Integer> test, int i) {
        ArrayList<Integer> target = new ArrayList<>(test);
        //Arrays.sort(target);
        Collections.sort(target);
        long start = System.currentTimeMillis();
        a.sort(test);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
        System.out.println("Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
        return target;
    }

    @Test
    public void sort2() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 13), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 13);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort3() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 12), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 12);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort4() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 11), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 11);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort5() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 10), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 10);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort6() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 9), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 9);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort7() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 8), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 8);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort8() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 7), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 7);
        Assert.assertEquals(target.toString(), test.toString());
    }

    @Test
    public void sort9() throws ExecutionException, InterruptedException {
        QuickSort a = new QuickSort(4);
        ArrayList<Integer> test = getArrayList((int) Math.pow(2, 6), -10000000, 10000000);
        ArrayList<Integer> target = getIntegers(a, test, 6);
        Assert.assertEquals(target.toString(), test.toString());
    }
}