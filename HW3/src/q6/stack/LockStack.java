
package q6.stack;

import java.util.concurrent.locks.ReentrantLock;

public class LockStack implements MyStack {
// you are free to add members
    private Node top;
    private ReentrantLock lock;


    /**
     *
     */
    public LockStack() {
        // implement your constructor here
        top = null;
        lock = new ReentrantLock();
    }

    /**
     *
     * @param value
     * @return
     */

    public boolean push(Integer value) {
        // implement your push method here
        if (value == null) {
            return false;
        }
        lock.lock();
        Node node = new Node(value);
        node.next = top;
        top = node;
        lock.unlock();
        return true;
    }

    /**
     *
     * @return
     * @throws EmptyStack
     */
    public Integer pop() throws EmptyStack {
        // implement your pop method here
        if (top == null) {
            throw new EmptyStack();
        } else {
            lock.lock();
            Node node = top;
            top = top.next;
            lock.unlock();
            return node.value;
        }
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