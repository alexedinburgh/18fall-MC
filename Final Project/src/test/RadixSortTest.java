package test;

import algo.RadixSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RadixSortTest {

    @Test
    public void sort() {

        RadixSort r = new RadixSort(1);
        ArrayList<Integer> list = getRandomArray(16384, -1000, 1000);

        ArrayList<Integer> target = new ArrayList<>(list);
        Collections.sort(target);
        long start = System.currentTimeMillis();
        r.sort(list);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Assert.assertEquals(target.toString(), list.toString());
    }


    static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

    static ArrayList<Integer> getRandomArray(int length, int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < length;i++) {
            list.add(getRandomNumberInts(min, max));
        }

        return (ArrayList<Integer>) list;
    }
}