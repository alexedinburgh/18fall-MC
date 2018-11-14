package test;

import algo.BitonicSort;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BitonicSortAbsTest extends AbsTest {

    @org.junit.Test
    public void sort() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 14), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BitonicSort a = new BitonicSort(2, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort2() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 9), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BitonicSort a = new BitonicSort(8, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort3() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 8), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BitonicSort a = new BitonicSort(8, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort4() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 7), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BitonicSort a = new BitonicSort(8, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }
}