package q6.ReentrantLock;

import q6.Increment;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PIncrement extends Increment {
    private static volatile int counter;
    private static Lock lock;

    private static void getReentrantIncrement() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    public static int parallelIncrement(int c, int numThreads) {
        if (numThreads >= 1) {
            counter= c;
            lock = new ReentrantLock();
            threadPool = Executors.newFixedThreadPool(numThreads);

            for (int i = 0;i < numThreads;i++) {
                threadPool.submit(new ReentrantThread(i + 1, numThreads));
            }
            threadPool.shutdown();
            while (!threadPool.isTerminated());
            while (counter < TOTAL_OPS);
            System.out.println("Reentrant Finished");
            return counter;
        } else {
            return -1;
        }
    }

    private static class ReentrantThread implements Runnable {
        private final int id;
        private final int numThreads;

        public ReentrantThread (int id, int numThreads) {
            this.id = id;
            this.numThreads = numThreads;
        }

        @Override
        public void run() {
            if (id == numThreads) {
                for (int i = 0;i < TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1);i++) {
                    getReentrantIncrement();
                }

            } else {
                for (int i = 0;i < TOTAL_OPS / numThreads;i++) {
                    getReentrantIncrement();
                }
            }
            System.out.println("Thread id: " + id + "; Thread end increment: " + counter);

        }
    }
}
