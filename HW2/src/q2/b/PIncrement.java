package q2.b;

public class PIncrement {
    static int count;
    static int numOfThread;
    static FastMutex fastMutex;
    static final int TOTAL_TIMES = 12000;

    public static int parallelIncrement(int c, int numThreads) {
        count = c;
        numOfThread = numThreads;
        fastMutex = new FastMutex();

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

    public static class FastMutex {
        volatile int x;
        volatile int y;
        volatile boolean[] flag;

        public FastMutex() {
            this.x = -1;
            this.y = -1;
            this.flag = new boolean[numOfThread];
        }

        public void lock(int threadNum) {
            while (true) {
                flag[threadNum] = true;
                x = threadNum;
                if (y != -1) {
                    flag[threadNum] = false;
                    while (y != -1);
                    continue;
                } else {
                    y = threadNum;
                    if (x == threadNum) {
                        return;
                    } else {
                        flag[threadNum] = false;
                        for (int i = 0;i < numOfThread;i++) {
                            while (flag[i] != false);
                        }
                        if (y == threadNum) return;
                        else {
                            while (y != -1);
                            continue;
                        }
                    }
                }
            }
        }

        public void unlock(int threadNum) {
            y = -1;
            flag[threadNum] = false;
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
                fastMutex.lock(threadID);
                count++;
                fastMutex.unlock(threadID);
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