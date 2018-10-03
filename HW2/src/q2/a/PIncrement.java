package q2.a;

import java.util.concurrent.TimeUnit;

public class PIncrement {
    static int count;
    static Fischer fischer;
    static final int TOTAL_TIMES = 1200;

    public static int parallelIncrement(int c, int numThreads) {
        fischer = new Fischer();
        count = c;

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            Increment increment = new Increment(TOTAL_TIMES / numThreads, i);
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

    public static class Fischer {
        long delta;
        volatile int turn;

        public Fischer() {
            this.delta = TimeUnit.MICROSECONDS.toMicros(10);
            this.turn = -1;
        }

        public void lock(int threadID) {
            while (true) {
                while (turn != -1);
                turn = threadID;

                try {
                    Thread.sleep(delta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (turn == threadID) {
                    return;
                }
            }
        }

        public void unlock(int threadID) {
            turn = -1;
        }
    }

    public static class Increment implements Runnable {
        int times;
        int threadID;

        public Increment(int times, int threadID) {
            this.times = times;
            this.threadID = threadID;
        }

        public void run() {
            for (int i = 0; i < times; i++) {
                fischer.lock(threadID);
                count++;
                fischer.unlock(threadID);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i <= 3;i++) {
            long begin = System.currentTimeMillis();
            int t = parallelIncrement(0, (int)Math.pow(2, i));
            long end = System.currentTimeMillis();

            System.out.println("thread number: " + (int)Math.pow(2, i) + " value: " + t);
            System.out.println("time: " + (end - begin));
        }
    }
}