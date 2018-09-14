package q6.Tournament;
import q6.Increment;
import java.util.concurrent.Executors;

public class PIncrement extends Increment {
    private static volatile int counter;
    private static TournamentLock lock;

    public static int parallelIncrement(int c, int numThreads) {
        if (numThreads >= 1) {
            counter = c;
            lock = new TournamentLock(numThreads);
            threadPool = Executors.newFixedThreadPool(numThreads);
        } else {
            return -1;
        }

        try {
            for (int i = 0;i < numThreads;i++) {
                threadPool.submit(new TournamentThread(i, numThreads));
            }
            threadPool.shutdown();
            while (!threadPool.isTerminated());
            while (counter < TOTAL_OPS);
            System.out.println("Tournament Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counter;
    }

    private static void getIncrement (int pid) {
        lock.lock(pid);
        counter++;
        lock.unlock(pid);
    }

    private static class TournamentThread implements Runnable {
        private final int id;
        private final int numThreads;

        public TournamentThread (int id, int numThreads) {
            this.id = id;
            this.numThreads = numThreads;
        }

        @Override
        public void run() {
            if (id == numThreads - 1) {
                for (int i = 0;i < TOTAL_OPS - (TOTAL_OPS / numThreads) * (numThreads - 1);i++) {
                    getIncrement(id);
                }

            } else {
                for (int i = 0;i < TOTAL_OPS / numThreads;i++) {
                    getIncrement(id);
                }
            }
            System.out.println("Thread id: " + id + "; Thread end increment: " + counter);
        }
    }

    public static void main (String[] args) {
        for (int i = 1;i < 9;i++) {
            long start = System.currentTimeMillis();
            parallelIncrement(0, i);
            long end = System.currentTimeMillis();
            System.out.println("Tournament Time spent is: " + (end - start) + " ms");
        }
    }

}