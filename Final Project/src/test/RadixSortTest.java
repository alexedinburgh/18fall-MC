package test;

import algo.RadixSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RadixSortTest extends AbsTest{

    @Test
    public void sort() {
        RadixSort r = new RadixSort(1);
        ArrayList<Integer> list = getArrayList(16384, -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(r, list);
        Assert.assertEquals(target.toString(), list.toString());
    }

    private ArrayList<Integer> getIntegers(RadixSort r, ArrayList<Integer> list) {
        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        long start = System.currentTimeMillis();
        r.sort(list);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return target;
    }

    @Test
    public void sort1() {
        RadixSort r = new RadixSort(2);
        ArrayList<Integer> list = getArrayList(16384, -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(r, list);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @Test
    public void sort2() {
        RadixSort r = new RadixSort(4);
        ArrayList<Integer> list = getArrayList(16384, -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(r, list);
        Assert.assertEquals(target.toString(), list.toString());
    }

    @Test
    public void sort3() {
        RadixSort r = new RadixSort(8);
        ArrayList<Integer> list = getArrayList(16384, -1000000, 1000000);
        ArrayList<Integer> target = getIntegers(r, list);
        Assert.assertEquals(target.toString(), list.toString());
    }
}