package q6.stack;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack implements MyStack {
// you are free to add members
    AtomicReference<Node> top;

    public LockFreeStack() {
        // implement your constructor here
        top = new AtomicReference<>(null);
    }

    /**
     * when value not legal, return false
     * @param value
     * @return
     */
    public boolean push(Integer value) {
        // implement your push method here
        if (value == null) { return false; }

        Node node = new Node(value);
        while (true) {
            Node oldTop = top.get();
            node.next = oldTop;
            if (top.compareAndSet(oldTop, node)) { return true; }
        }
    }

    /**
     * when no nodes, throw exception
     * @return
     * @throws EmptyStack
     */
    public Integer pop() throws EmptyStack {
        // implement your pop method here
        while (true) {
            Node oldTop = top.get();
            if (oldTop == null) { throw new EmptyStack(); }
            int value = oldTop.value;
            Node newTop = oldTop.next;
            if (top.compareAndSet(oldTop, newTop)) { return value; }
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