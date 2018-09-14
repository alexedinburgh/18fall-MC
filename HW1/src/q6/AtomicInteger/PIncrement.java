package q6.AtomicInteger;

import q6.Increment;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PIncrement extends Increment {
    private static volatile AtomicInteger atomicInteger;

    /**
     * Main method to call
     * @param c
     * @param numThreads
     * @return
     */
    public static int parallelIncrement(int c, int numThreads) {
        // your implementation goes here
        if (numThreads >= 1) {
            threadPool = Executors.newFixedThreadPool(numThreads);
            atomicInteger = new AtomicInteger(c);
        }
        for (int i = 0; i < numThreads; i++) {
            if (i == numThreads - 1) {
                threadPool.submit(new AtomicThread(TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1), i + 1));
            } else {
                threadPool.submit(new AtomicThread(TOTAL_OPS / numThreads, i + 1));
            }
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated());
        while (atomicInteger.get() < TOTAL_OPS);
        System.out.println("PIncrement Finished");
        return atomicInteger.get();
    }

    /**
     * thread of atomic increment
     */
    private static class AtomicThread implements Runnable {
        private final int numOfOps;
        private final int id;

        public AtomicThread(int numOfOps, int id) {
            this.numOfOps = numOfOps;
            this.id = id;
        }

        @Override
        public void run() {
            int counter = 0;
            while (counter < numOfOps) {
                int temp = atomicInteger.get();
                if (atomicInteger.compareAndSet(temp, temp + 1)) {
                    counter++;
                }
            }
            System.out.println("Thread id: " + id + "; Thread end increment: " + atomicInteger.get());

        }
    }

    public static void main (String[] args) {
        for (int i = 1; i <= 8; i++) {
            long start = System.currentTimeMillis();
            parallelIncrement(0, i);
            long end = System.currentTimeMillis();
            System.out.println("Time spent is: " + (end - start) + " ms");
        }



    }


}
