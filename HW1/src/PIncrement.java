import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PIncrement {
    private static ExecutorService threadPool;
    private static final int TOTAL_OPS = 1200000;

    public static int parallelIncrement(int c, int numThreads) {
        // your implementation goes here
        return 0;
    }

    /**
     * Peterson Tournament Algorithm
     */
    private static class PTA {

    }

    /**
     * Atomic Increment
     */
    private static class AtomicIncrement {
        private static volatile AtomicInteger atomicInteger;

        /**
         *
         * @param c
         * @param numThreads
         */
        public AtomicIncrement (int c, int numThreads) {
            if (numThreads >= 1) {
                threadPool = Executors.newFixedThreadPool(numThreads);
                atomicInteger = new AtomicInteger(c);
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
                System.out.println("AtomicIncrement Finished");
            }
        }

        /**
         * thread of atomic increment
         */
        private static class AtomicThread implements Runnable {
            int numOfOps;
            int id;

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


    }

    /**
     * Synchronized Construct
     */
    private static class SyncIncrement {

    }

    public static void main(String[] args) {
        for (int i = 1;i <= 8;i++) {
            AtomicIncrement result = new AtomicIncrement(0, i);
        }
    }
}
