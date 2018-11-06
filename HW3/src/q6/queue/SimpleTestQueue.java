package q6.queue;
import org.junit.Assert;
import org.junit.Test;
import q6.queue.LockFreeQueue;
import q6.queue.LockQueue;
import q6.queue.MyQueue;

public class SimpleTestQueue {

    @Test
    public void testLockBasedQueue() {
        LockQueue queue = new LockQueue();
        threadEnqueue(queue);
        threadDequeue(queue);
        threadEmptyCheck(queue);
    }

    @Test
    public void testLockFreeQueue() {
        LockFreeQueue queue = new LockFreeQueue();
        threadEnqueue(queue);
        threadDequeue(queue);
        threadEmptyCheck(queue);
    }

    private void threadEnqueue(MyQueue queue) {
        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new EnqueueThread(0, 1000, queue));
        threads[1] = new Thread(new EnqueueThread(1500, 2500, queue));
        threads[2] = new Thread(new EnqueueThread(1000, 2000, queue));
        threads[1].start(); threads[0].start(); threads[2].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadDequeue(MyQueue queue) {

        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new DequeueThread(0, 1000, queue));
        threads[1] = new Thread(new DequeueThread(1500, 2500, queue));
        threads[2] = new Thread(new DequeueThread(1000, 2000, queue));
        threads[1].start(); threads[0].start(); threads[2].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadEmptyCheck(MyQueue queue) {
        Thread thread = new Thread(new FinalCheckThread(queue));
        thread.start();
    }

    private class EnqueueThread implements Runnable {

        int begin;
        int end;
        MyQueue queue;

        EnqueueThread(int begin, int end, MyQueue queue) {
            this.begin = begin;
            this.end = end;
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = begin; i <= end; ++i) {
                queue.enq(i);
            }
        }
    }

    private class DequeueThread implements Runnable {

        int begin;
        int end;
        MyQueue queue;

        DequeueThread(int begin, int end, MyQueue queue) {
            this.begin = begin;
            this.end = end;
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = begin; i <= end; ++i) {
                Assert.assertNotNull(queue.deq());
            }
        }
    }

    private class FinalCheckThread implements Runnable {

        MyQueue queue;

        FinalCheckThread(MyQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Assert.assertNull(queue.deq());
        }
    }

}
