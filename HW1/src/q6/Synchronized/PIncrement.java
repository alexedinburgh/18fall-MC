package q6.Synchronized;
import q6.Increment;
import java.util.concurrent.Executors;

/**
 * Synchronized Construct
 */
public class PIncrement extends Increment {
    static volatile int counter;

    public static int parallelIncrement(int c, int numThreads) {
        if (numThreads >= 1) {
            counter = c;
            threadPool = Executors.newFixedThreadPool(numThreads);
        } else {
            return -1;
        }
        // your implementation goes here
        try {
            for (int i = 0;i < numThreads;i++) {
                threadPool.submit(new SyncThread(i + 1, numThreads));
            }
            threadPool.shutdown();
            while (!threadPool.isTerminated());
            while (counter < TOTAL_OPS);
            System.out.println("PIncrement Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counter;
    }

    private static synchronized void getIncrement () {
        counter++;
    }

    private static class SyncThread implements Runnable {
        private final int id;
        private final int numThreads;

        public SyncThread (int id, int numThreads) {
            this.id = id;
            this.numThreads = numThreads;
        }

        @Override
        public void run() {
            if (id == numThreads) {
                for (int i = 0;i < TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1);i++) {
                    getIncrement();
                }

            } else {
                for (int i = 0;i < TOTAL_OPS / numThreads;i++) {
                    getIncrement();
                }
            }
            System.out.println("Thread id: " + id + "; Thread end increment: " + counter);
        }
    }

    public static void main (String[] args) {
        for (int i = 1; i <= 8; i++) {
            long start = System.currentTimeMillis();
            parallelIncrement(0, i);
            long end = System.currentTimeMillis();
            System.out.println("Sync Time spent is: " + (end - start) + " ms");
        }



    }

}