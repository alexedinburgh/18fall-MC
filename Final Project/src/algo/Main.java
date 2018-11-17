package algo;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    int threshold;

    public Main(int threshold) {
        this.threshold = threshold;
    }
    public long getQuickSortDuration(ArrayList<Integer> list, int threadNum) {
        QuickSort a = new QuickSort(threadNum, threshold);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long getBrickSortDuration(ArrayList<Integer> list, int threadNum) {
        BrickSort a = new BrickSort(threadNum);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long getOddEvenMergeSortDuration(ArrayList<Integer> list, int threadNum) {
        BrickSortV2 a = new BrickSortV2(threadNum, threshold);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long getMergeSortDuration(int[] list, int threadNum) {
        Mergesort a = new Mergesort(threadNum);
        long start = System.currentTimeMillis();
        a.sort(list, threshold);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long getBitonicSortDuration(ArrayList<Integer> list, int threadNum) {
        BitonicSort a = new BitonicSort(threadNum, threshold);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long getRadixSortDuration(ArrayList<Integer> list, int threadNum) {
        RadixSort a = new RadixSort(threadNum);
        long start = System.currentTimeMillis();
        a.sort(list);
        long end = System.currentTimeMillis();
        return end - start;
    }

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

    public ArrayList<Integer> arrayToArrayList(int[] array) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int item : array) {
            output.add(item);
        }
        return output;
    }

    List<List<Integer>> newArrayList(int m) {
        List<List<Integer>> temp = new ArrayList<>();
        for (int i = 0;i < m;++i) {
            temp.add(new ArrayList<>());
        }
        return temp;
    }

    public void writeToFile(List<List<Integer>> input, FileWriter fw) {
        try {
            String newLine = System.getProperty("line.separator");
            for (List<Integer> item : input) {
                fw.write(item.toString() + newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        int numThreadOptions = 4;
        int numPowerOptions = 15;
        Main mainObject = new Main(4);

        List<List<Integer>> quickSortRes = mainObject.newArrayList(numThreadOptions);
        List<List<Integer>> brickSortRes = mainObject.newArrayList(numThreadOptions);
        List<List<Integer>> oddEvenMergeSortRes = mainObject.newArrayList(numThreadOptions);
        List<List<Integer>> mergeSortRes = mainObject.newArrayList(numThreadOptions);
        List<List<Integer>> bitonicSortRes = mainObject.newArrayList(numThreadOptions);
        List<List<Integer>> radixSortRes = mainObject.newArrayList(numThreadOptions);

        for (int i = 0;i < numPowerOptions;++i) {
            int[] list = mainObject.getArray((int) Math.pow(2, i), -1000000, 1000000);

            for (int j = 0;j < numThreadOptions;++j) {
                int threadNum = (int) Math.pow(2, j);
                ArrayList<Integer> copy1 = mainObject.arrayToArrayList(list);
                ArrayList<Integer> copy2 = mainObject.arrayToArrayList(list);
                ArrayList<Integer> copy3 = mainObject.arrayToArrayList(list);
                ArrayList<Integer> copy4 = mainObject.arrayToArrayList(list);
                ArrayList<Integer> copy5 = mainObject.arrayToArrayList(list);
                int[] copy6 = Arrays.copyOf(list, list.length);

                quickSortRes.get(j).add((int) mainObject.getQuickSortDuration(copy1, threadNum));
                brickSortRes.get(j).add((int) mainObject.getBrickSortDuration(copy2, threadNum));
                oddEvenMergeSortRes.get(j).add((int) mainObject.getOddEvenMergeSortDuration(copy3, threadNum));
                mergeSortRes.get(j).add((int) mainObject.getMergeSortDuration(copy6, threadNum));
                bitonicSortRes.get(j).add((int) mainObject.getBitonicSortDuration(copy4, threadNum));
                radixSortRes.get(j).add((int) mainObject.getRadixSortDuration(copy5, threadNum));
            }
        }

        try {
            FileWriter fw = new FileWriter("result.txt");
            mainObject.writeToFile(quickSortRes, fw);
            mainObject.writeToFile(brickSortRes, fw);
            mainObject.writeToFile(oddEvenMergeSortRes, fw);
            mainObject.writeToFile(mergeSortRes, fw);
            mainObject.writeToFile(bitonicSortRes, fw);
            mainObject.writeToFile(radixSortRes, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
