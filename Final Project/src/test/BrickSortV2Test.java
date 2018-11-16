package test;

import algo.BrickSortV2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class BrickSortV2Test extends AbsTest {

    @org.junit.Test
    public void sort() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 14), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 14);
        Assert.assertEquals(target.toString(), list.toString());
    }

    private ArrayList<Integer> getIntegers(ArrayList<Integer> list, int i) {
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        BrickSortV2 a = new BrickSortV2(8, 4);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        System.out.println("Time for " + (int) Math.pow(2, i) + " in Milli-seconds is: " + (end - start));
        return target;
    }

    @org.junit.Test
    public void sort1() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 13), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 13);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort2() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 12), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 12);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort3() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 11), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 11);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort4() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 10), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 10);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort5() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 9), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 9);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort6() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 8), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 8);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort7() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 7), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 7);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort8() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 6), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 6);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort9() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 5), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 5);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort10() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 4), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 4);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort11() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 3), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 3);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort12() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 2), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 2);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort13() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 1), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 1);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @org.junit.Test
    public void sort14() {
        ArrayList<Integer> list = getArrayList((int) Math.pow(2, 0), -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(list, 0);
        Assert.assertEquals(target.toString(), list.toString());
    }
}