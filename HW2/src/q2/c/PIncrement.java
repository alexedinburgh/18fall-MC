package q2.c;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PIncrement {
    static int count;
    static AndersonLock andersonLock;
    static final int TOTAL_TIMES = 12000;

    public static int parallelIncrement(int c, int numThreads) {
        count = c;
        andersonLock = new AndersonLock(numThreads);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            Increment increment = new Increment(TOTAL_TIMES / numThreads);
            Thread thread = new Thread(increment);
            threads[i] = thread;
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static class AndersonLock {
        AtomicInteger tailSlot = new AtomicInteger(0);
        AtomicBoolean[] available;
        ThreadLocal<Integer> mySlot;
        int threadNum;

        public AndersonLock(int threadNum) {
            this.threadNum = threadNum;
            this.mySlot = new ThreadLocal<>();
            mySlot.set(0);

            available = new AtomicBoolean[threadNum];
            for (int i = 0;i < threadNum;i++) {
                available[i] = new AtomicBoolean(false);
            }
            try {
                available[0].set(true);;
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }

        public void lock() {
            mySlot.set(tailSlot.getAndIncrement() % threadNum);
            while(!available[mySlot.get()].get()) {};
        }

        public void unlock() {
            available[mySlot.get()].set(false);
            available[(mySlot.get() + 1) % threadNum].set(true);
        }
    }

    public static class Increment implements Runnable {
        int times;

        public Increment(int times) {
            this.times = times;
        }

        public void run() {
            for (int i = 0; i < times; i++) {
                andersonLock.lock();
                count++;
                andersonLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 4;i++) {
            long begin = System.currentTimeMillis();
            int t = parallelIncrement(0, (int)Math.pow(2, i));
            long end = System.currentTimeMillis();

            System.out.println("thread number: " + (int)Math.pow(2, i) + " value: " + t);
            System.out.println("time: " + (end - begin));
        }
    }
}