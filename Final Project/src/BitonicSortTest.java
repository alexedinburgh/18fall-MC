import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BitonicSortTest {

    @org.junit.Test
    public void sort() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
        List<Integer> target = new ArrayList<>(Arrays.asList(-3, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 5, 8, 12));
        BitonicSort a = new BitonicSort(8);
        a.sort(list);
        Assert.assertEquals(list, target);
    }
}