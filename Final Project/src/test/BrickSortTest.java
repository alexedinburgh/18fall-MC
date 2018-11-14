package test;

import algo.BitonicSort;
import algo.BrickSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class BrickSortTest extends AbsTest{

    @org.junit.Test
    public void sort() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 14), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 14) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort1() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 13), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 13) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort2() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 12), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 12) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort3() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 11), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 11) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort4() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 10), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 10) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort5() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 9), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 9) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort6() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 8), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 8) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort7() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 7), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 7) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort8() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 6), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 6) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort9() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 5), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 5) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort10() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 4), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 4) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort11() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 3), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 3) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort12() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 2), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 2) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort13() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 1), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 1) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort14() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 0), -1000000, 1000000);
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSort a = new BrickSort(8);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, 0) + " in Milli-seconds is: " + (end - start));
        Assert.assertEquals(target.toString(), list.toString());
    }
}