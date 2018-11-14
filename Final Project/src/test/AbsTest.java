package test;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbsTest {

    public int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

    public int[] getArray(int length, int min, int max) {
        int[] array = new int[length];
        for (int i = 0;i < length;i++) {
            array[i] = getRandomNumberInts(min, max);
        }

        return array;
    }

    public ArrayList<Integer> getArrayList(int length, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0;i < length;i++) {
            list.add(getRandomNumberInts(min, max));
        }
        return list;
    }
}
