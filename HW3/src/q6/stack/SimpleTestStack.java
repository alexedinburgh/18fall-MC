package q6.stack;
import org.junit.Assert;
import org.junit.Test;
import q6.stack.EmptyStack;
import q6.stack.LockFreeStack;
import q6.stack.MyStack;

public class SimpleTestStack {

    @Test
    public void testLockFreeStack() {
        LockFreeStack stack = new LockFreeStack();
        threadPush(stack);
        threadPop(stack);
        threadEmptyCheck(stack);
    }

    private void threadPush(MyStack stack) {
        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new PushThread(0, 1000, stack));
        threads[1] = new Thread(new PushThread(1500, 2500, stack));
        threads[2] = new Thread(new PushThread(1000, 2000, stack));
        threads[1].start(); threads[0].start(); threads[2].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadPop(MyStack stack) {

        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new PopThread(0, 1000, stack));
        threads[1] = new Thread(new PopThread(1500, 2500, stack));
        threads[2] = new Thread(new PopThread(1000, 2000, stack));
        threads[1].start(); threads[0].start(); threads[2].start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void threadEmptyCheck(MyStack stack) {
        Thread thread = new Thread(new FinalCheckThread(stack));
    }

    private class PushThread implements Runnable {

        int begin;
        int end;
        MyStack stack;

        PushThread(int begin, int end, MyStack stack) {
            this.begin = begin;
            this.end = end;
            this.stack = stack;
        }

        @Override
        public void run() {
            for (int i = begin; i <= end; ++i) {
                stack.push(i);
            }
        }
    }

    private class PopThread implements Runnable {

        int begin;
        int end;
        MyStack stack;

        PopThread(int begin, int end, MyStack stack) {
            this.begin = begin;
            this.end = end;
            this.stack = stack;
        }

        @Override
        public void run() {
            for (int i = begin; i <= end; ++i) {
                try {
                    stack.pop();
                } catch (EmptyStack emptyStack) {
                    Assert.fail("pop from an empty stack!");
                }
            }
        }
    }

    private class FinalCheckThread implements Runnable {

        MyStack stack;

        FinalCheckThread(MyStack stack) {
            this.stack = stack;
        }

        @Override
        public void run() {
            try {
                stack.pop();
                Assert.assertTrue(false);
            } catch (EmptyStack emptyStack) {
                Assert.assertTrue(true);
            }
        }
    }

}
