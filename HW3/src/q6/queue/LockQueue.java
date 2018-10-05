package q6.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implement Lock-based and Lock-Free unbounded queues of
 * Integers. For the lock based implementation, use di  erent locks for enq and deq operations. For the
 * variable count use AtomicInteger. For the lock-free implementation, use Michael and Scott's algorithm
 * as explained in the class. The deq operation should return null if the queue is empty.
 */

/**
 * need to use two locks for enq and deq, but now only use one lock
 */
public class LockQueue implements MyQueue {
// you are free to add members
    private Node head;
    private Node tail;
    private ReentrantLock qLock;

    public LockQueue() {
        // implement your constructor here
        this.head = new Node(null);
        this.tail = head;
        this.qLock = new ReentrantLock();
    }

    public int size() {
        AtomicInteger counter = new AtomicInteger(0);
        Node node = head;
        while(node.next != null) {
            node = node.next;
            counter.getAndIncrement();
        }
        return counter.get();
    }

    /**
     * add new node into queue, keep FIFO
     * @param value
     * @return
     */

    public boolean enq(Integer value) {
        // implement your enq method here
        if (value == null) {
            return false;
        }
        qLock.lock();
        try {
            Node newNode = new Node(value);
            tail.next = newNode;
            tail = newNode;
            return true;
        } finally {
            qLock.unlock();
        }
    }

    public Integer deq() {
        Integer res;
        qLock.lock();
        try {
            while (this.size() == 0);//wait for nodes
            res = head.next.value;
            head = head.next;
        } finally {
            qLock.unlock();
        }
        // implement your deq method here
        return res;
    }

    protected class Node {
        public Integer value;
        public Node next;

        public Node(Integer x) {
            value = x;
            next = null;
        }
    }
}