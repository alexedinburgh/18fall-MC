package q5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Xiaocheng
 */

public class Frequency {

    private static ExecutorService threadPool;

    static class helper implements Callable {
        int target;
        List<Integer> targetList;

        public helper (int target, List<Integer> targetList) {
            this.target = target;
            this.targetList = targetList;
        }

        @Override
        public Object call() throws Exception {
            int counter = 0;
            for (int i : targetList) {
                if (target == i) {
                    counter++;
                }
            }

            return counter;
        }
    }
    /**
     *
     * @param x - target that will be computed frequency in the program
     * @param A - target array
     * @param numThreads - number of how many threads need
     * @return
     */
    public static int parallelFreq(int x, int[] A, int numThreads) throws ExecutionException, InterruptedException {
        // your implementation goes here.
        int frequency = 0;
        try {
            threadPool = Executors.newFixedThreadPool(numThreads);
            List<List<Integer>> target = splitArray(A, numThreads);
            List<Future<Integer>> res = new ArrayList<>();

            for (List<Integer> sublist : target) {
                Future<Integer> temp = threadPool.submit(new helper(x, sublist));
                res.add(temp);
            }

            for (Future<Integer> temp : res) {
                frequency += temp.get();
            }
            threadPool.shutdown();

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        return frequency;
    }

    /**
     *
     * @param A - target array
     * @param numThreads - numbers of threads need
     * @return
     */
    private static List<List<Integer>> splitArray (int[] A, int numThreads) {
        List<List<Integer>> res = new ArrayList<>();
        int size = A.length <= numThreads ? 1 : A.length / numThreads;

        int begin = 0;
        for (int i = 0; i < Math.min(numThreads, A.length);i++) {
            List<Integer> temp = new ArrayList<>();
            if (i == numThreads - 1) {
                for (int j = begin;j < A.length;j++) {
                    temp.add(A[j]);
                }
            } else {
                for (int j = begin;j < begin + size;j++) {
                    temp.add(A[j]);
                }
            }
            res.add(temp);
            begin += size;

        }

        return res;
    }

    public static void main (String[] args) throws ExecutionException, InterruptedException {
        int[] array = new int[] {1,2,3,4,5,6,7,6,5,4,3,2,3,4,5,2,3,4,5,6};
        System.out.println(parallelFreq(4, array, 2));
        System.out.println(parallelFreq(4, array, 200));
        System.out.println(parallelFreq(4, array, 0));
    }
}
