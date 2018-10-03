package q3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monkey {

    final Lock lock;
    final Condition notFull;
    volatile int maxNum, currentNum, currentDirection;
    volatile boolean king;

    public Monkey() {
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        maxNum = 3;
        currentNum = 0;
        currentDirection = 2;
        king = false;
    }

    public void ClimbRope(int direction) throws InterruptedException {
        lock.lock();
        try {
            if (direction == -1) {
                king = true;
                while (currentNum != 0) {
                    notFull.await();
                }
            } else {// 0,1 go into else
                while (currentNum == maxNum || (currentNum != 0 && currentDirection != direction) || king) {
                    notFull.await();
                }
            }
            currentDirection = direction;
            currentNum++;
        } finally {
            // TODO: handle finally clause
            lock.unlock();
        }
    }

    public void LeaveRope() {
        lock.lock();
        try {
            if (currentDirection == -1) {
                king = false;
            }
            currentNum--;
            notFull.signalAll();
        } finally {
            // TODO: handle finally clause
            lock.unlock();
        }
    }

    /**
     * Returns the number of monkeys on the rope currently for test purpose.
     *
     * @return the number of monkeys on the rope
     *
     * Positive Test Cases:
     * case 1: when normal monkey (0 and 1) is on the rope, this value should <= 3, >= 0
     * case 2: when Kong is on the rope, this value should be 1
     */
    public int getNumMonkeysOnRope() {
        return currentNum;
    }

//	public static void main(String[] args) {
//		Monkey monkey = new Monkey();
//		System.out.println(monkey.getNumMonkeysOnRope());
//	}
}