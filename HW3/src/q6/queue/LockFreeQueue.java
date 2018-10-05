package q6.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeQueue implements MyQueue {
    // you are free to add members
    AtomicReference<Node> head;
    AtomicReference<Node> tail;

    public LockFreeQueue() {
        // implement your constructor here
        Node node = new Node(null);//Allocate a free node
        //node.next = new AtomicReference<>(null);//// Make it the only node in the linked list
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    /**
     * enqueue
     * @param value
     * @return
     */
    public boolean enq(Integer value) {
        // implement your enq method here
        if (value == null) {
            return false;
        }
        Node node = new Node(value);
        //node.next = new AtomicReference<>(null);
        while(true) {
            Node curTail = tail.get();
            Node tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    if (curTail.next.compareAndSet(null, node)) {
                        tail.compareAndSet(curTail, node);
                        return true;
                    }
                }
            }

        }
        //return false;
    }

    /**
     * dequeue
     * @return
     */

    public Integer deq() {
        // implement your deq method here
        while (true) {
            Node curHead = head.get();
            Node headNext = curHead.next.get();
            if (curHead == head.get()) {
                if (headNext != null) {
                    head.compareAndSet(curHead, headNext);
                    return headNext.value;
                } else {
                    return null;
                }
            }
        }

    }

    /**
     * structure node_t {value: data type, next: pointer_t}
     */
    protected class Node {
        public Integer value;
        public AtomicReference<Node> next;
        //public AtomicInteger counter;

        public Node(Integer x) {
            value = x;
            next = new AtomicReference<>(null);
            //counter = new AtomicInteger(0);
        }
    }

}