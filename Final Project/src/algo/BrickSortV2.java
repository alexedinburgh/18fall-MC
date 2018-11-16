package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class BrickSortV2{
    ForkJoinPool pool;
    int threshold;

    public BrickSortV2(int threadNum, int threshold) {
        pool = new ForkJoinPool(threadNum);
        this.threshold = threshold;
    }

    public void compareAndSwap(ArrayList<Integer> input, int i, int j) {
        if(input.get(i) > input.get(j)) {
            Collections.swap(input, i, j);
        }
    }

    public void brickMerge(ArrayList<Integer> input, int begin, int length) {
        if (length > 1) {
            pool.invoke(new BrickMergeTask(input, begin, length, 1));
        }
    }

    public void brickSort(ArrayList<Integer> input, int begin, int length) {
        try {
            if (length > 1) {
                pool.invoke(new BrickSortTask(input, begin, length));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void sort(List<Integer> input) {
        brickSort((ArrayList<Integer>) input, 0, input.size());
        pool.shutdown();
    }

    public class SwapTask implements Callable<Void> {
        ArrayList<Integer> input;
        int i, j;

        public SwapTask(ArrayList<Integer> input, int i, int j) {
            this.input = input;
            this.i = i;
            this.j = j;
        }

        @Override
        public Void call() {
            if(input.get(i) > input.get(j)) {
                Collections.swap(input, i, j);
            }
            return null;
        }
    }

    public class BrickMergeTask extends RecursiveAction {

        private static final long serialVersionUID = 1L;

        ArrayList<Integer> input;
        int begin, length, distance;

        public BrickMergeTask(ArrayList<Integer> input, int begin, int length, int distance) {
            this.input = input;
            this.begin = begin;
            this.length = length;
            this.distance = distance;
        }

        @Override
        protected void compute() {
            int newDistance = distance * 2;
            if (newDistance < length) {

                BrickMergeTask left = new BrickMergeTask(input, begin, length, newDistance);
                left.fork();

                BrickMergeTask right = new BrickMergeTask(input, begin + distance,length, newDistance);

                right.compute();
                left.join();

                //ArrayList<Callable<Void>> temp = new ArrayList<Callable<Void>>();
                for (int i = begin + distance;i + distance < begin + length;i += newDistance) {
                    compareAndSwap(input, i, i + distance);
                    //temp.add(new SwapTask(input, i, i + distance));
//                    if(isUp == (input.get(i) > input.get(i + halfLength))) {
//                        Collections.swap(input, i, i + halfLength);
//                    }
                }
                //pool.invokeAll(temp);
            } else {
                compareAndSwap(input, begin, begin + distance);
            }
        }
    }

    public class BrickSortTask extends RecursiveAction {

        private static final long serialVersionUID = 1L;

        ArrayList<Integer> input;
        int begin, length;

        public BrickSortTask(ArrayList<Integer> input, int begin, int length) {
            this.input = input;
            this.begin = begin;
            this.length = length;
        }

        @Override
        protected void compute() {
            if (length > 1 && length <= threshold) {
                Collections.sort(input.subList(begin, begin + length));
            } else if (length > 1){
                int halfLength = length / 2;
                BrickSortTask left = new BrickSortTask(input, begin, halfLength);
                left.fork();

                BrickSortTask right = new BrickSortTask(input, begin + halfLength, halfLength);
                right.compute();
                left.join();

                brickMerge(input, begin, length);
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,3,2,4,0,3,-1,-2, 5, 12, 2, 8, 0, 1, -1, -3));
        BrickSortV2 brickSort = new BrickSortV2(8, 2);

        long start = System.nanoTime();
        brickSort.sort(input);
        long end = System.nanoTime();

        System.out.println(Arrays.toString(input.toArray()));
        System.out.println(end - start);
    }
}
