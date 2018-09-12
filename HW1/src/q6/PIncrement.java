package q6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
//    private static class AtomicIncrement {
//        private static volatile AtomicInteger atomicInteger;
//
//        /**
//         *
//         * @param c
//         * @param numThreads
//         */
//        public AtomicIncrement (int c, int numThreads) {
//            if (numThreads >= 1) {
//                threadPool = Executors.newFixedThreadPool(numThreads);
//                atomicInteger = new AtomicInteger(c);
//                for (int i = 0; i < numThreads; i++) {
//                    if (i == numThreads - 1) {
//                        threadPool.submit(new AtomicThread(TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1), i + 1));
//                    } else {
//                        threadPool.submit(new AtomicThread(TOTAL_OPS / numThreads, i + 1));
//                    }
//                }
//                threadPool.shutdown();
//                while (!threadPool.isTerminated());
//                while (atomicInteger.get() < TOTAL_OPS);
//                System.out.println("AtomicIncrement Finished");
//            }
//        }
//
//        /**
//         * thread of atomic increment
//         */
//        private static class AtomicThread implements Runnable {
//            private final int numOfOps;
//            private final int id;
//
//            public AtomicThread(int numOfOps, int id) {
//                this.numOfOps = numOfOps;
//                this.id = id;
//            }
//
//            @Override
//            public void run() {
//                int counter = 0;
//                while (counter < numOfOps) {
//                    int temp = atomicInteger.get();
//                    if (atomicInteger.compareAndSet(temp, temp + 1)) {
//                        counter++;
//                    }
//                }
//                System.out.println("Thread id: " + id + "; Thread end increment: " + atomicInteger.get());
//
//            }
//        }
//
//
//    }

    /**
     * Synchronized Construct
     */
//    private static class SyncIncrement {
//        private static volatile int c;
//
//        public SyncIncrement (int c, int numThreads) {
//            if (numThreads >= 1) {
//                this.c = c;
//                threadPool = Executors.newFixedThreadPool(numThreads);
//            }
//            for (int i = 0;i < numThreads;i++) {
//                threadPool.submit(new SyncThread (i + 1, numThreads));
//            }
//            threadPool.shutdown();
//            while (!threadPool.isTerminated());
//            while (this.c < TOTAL_OPS);
//            System.out.println("SyncIncrement Finished");
//        }
//
//        private static synchronized void getIncrement () {
//            c++;
//        }
//
//        private static class SyncThread implements Runnable {
//            private final int id;
//            private final int numThreads;
//
//            public SyncThread (int id, int numThreads) {
//                this.id = id;
//                this.numThreads = numThreads;
//            }
//
//            @Override
//            public void run() {
//                if (id == numThreads) {
//                    for (int i = 0;i < TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1);i++) {
//                        getIncrement();
//                    }
//
//                } else {
//                    for (int i = 0;i < TOTAL_OPS / numThreads;i++) {
//                        getIncrement();
//                    }
//                }
//                System.out.println("Thread id: " + id + "; Thread end increment: " + c);
//            }
//        }
//
//    }

    /**
     * Reentrant Increment
     */
//    private static class ReentrantIncrement {
//        private static volatile int c;
//        private static Lock lock;
//
//        public ReentrantIncrement (int c, int numThreads) {
//            this.c = c;
//            this.lock = new ReentrantLock();
//            threadPool = Executors.newFixedThreadPool(numThreads);
//
//            for (int i = 0;i < numThreads;i++) {
//                threadPool.submit(new ReentrantThread(i + 1, numThreads));
//            }
//            threadPool.shutdown();
//            while (!threadPool.isTerminated());
//            while (this.c < TOTAL_OPS);
//            System.out.println("Reentrant Finished");
//        }
//
//        private static void getReentrantIncrement() {
//            lock.lock();
//            c++;
//            lock.unlock();
//        }
//
//        private static class ReentrantThread implements Runnable {
//            private final int id;
//            private final int numThreads;
//
//            public ReentrantThread (int id, int numThreads) {
//                this.id = id;
//                this.numThreads = numThreads;
//            }
//
//            @Override
//            public void run() {
//                if (id == numThreads) {
//                    for (int i = 0;i < TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1);i++) {
//                        getReentrantIncrement();
//                    }
//
//                } else {
//                    for (int i = 0;i < TOTAL_OPS / numThreads;i++) {
//                        getReentrantIncrement();
//                    }
//                }
//                System.out.println("Thread id: " + id + "; Thread end increment: " + c);
//
//            }
//        }
//    }

//    public static void main(String[] args) {
//        for (int i = 1;i <= 8;i++) {
//            //AtomicIncrement result = new AtomicIncrement(0, i);
//            //SyncIncrement res = new SyncIncrement(0, i);
//            ReentrantIncrement res = new ReentrantIncrement(0, i);
//        }
//    }
}
