package algo; /**
 * https://en.wikipedia.org/wiki/Merge_sort
 *
 * https://stanford.edu/~rezab/classes/cme323/S16/notes/Lecture04/cme323_lec4.pdf
 *
 * Class: Sorting algorithm
 * Data structure: Array
 * Worst-case performance: O(n log n)
 * Best-case performance: O(n log n) typical / O(n) natural variant
 * Average performance: O(n log n)
 * Worst-case space complexity: О(n) total with O(n) auxiliary, O(1) auxiliary with linked lists[1]
 */
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Mergesort {
    ForkJoinPool pool;

    /**
     * constructor
     * @param threadNum
     */
    public Mergesort(int threadNum) {
        this.pool = new ForkJoinPool(threadNum);
    }

    /**
     * sort function
     * @param list
     * @param threshold
     */
    public void sort(int[] list, int threshold) {
        MergesortTask mainTask = new MergesortTask(list,threshold);
        this.pool.invoke(mainTask);
    }

    public class MergesortTask extends RecursiveAction {
        private int[] list;
        private int threshold;

        public MergesortTask(int[] list, int threshold) {
            this.list = list;
            this.threshold = threshold;
        }

        /**
         * threshold determine if use sequentially sort
         */
        @Override
        public void compute() {
            if (list.length <= threshold) { Arrays.sort(list); }
            else {
                int[] left = new int[list.length / 2];
                System.arraycopy(list, 0, left, 0, list.length / 2);

                int restLength = list.length - list.length / 2;
                int[] right = new int[restLength];
                System.arraycopy(list, list.length / 2, right, 0, restLength);

                MergesortTask first  = new MergesortTask (left, threshold);
                MergesortTask second = new MergesortTask (right, threshold);
                invokeAll(first,second);

                merge(left, right, list);
            }
        }

        /**
         * merge function after compute
         * @param a
         * @param b
         * @param temp
         */
        public void merge(int[] a, int[] b, int[] temp) {
            int aIndex = 0; // Current index in a
            int bIndex = 0; // Current index in b
            int tempIndex = 0; // Current index in temp

            while (aIndex < a.length && bIndex < b.length) {
                if (a[aIndex] < b[bIndex]) { temp[tempIndex++] = a[aIndex++]; }
                else { temp[tempIndex++] = b[bIndex++]; }
            }
            while (aIndex < a.length) { temp[tempIndex++] = a[aIndex++]; }
            while (bIndex < b.length) { temp[tempIndex++] = b[bIndex++]; }
        }
    }

    public int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }

    public int[] getRandomArray(int length, int min, int max) {
        int[] array = new int[length];
        for (int i = 0;i < length;i++) {
            array[i] = getRandomNumberInts(min, max);
        }

        return array;
    }

    public static void main(String args[]) {
        //ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
        int[] input = new int[] {1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3};
        Mergesort a = new Mergesort(20);

        long start = System.nanoTime();
        a.sort(input,2);
        long end = System.nanoTime();

        System.out.println(Arrays.toString(input));
        System.out.println(end - start);
   }
}
