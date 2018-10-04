package q5;

import java.util.concurrent.atomic.AtomicReference;


public class LockFreeListSet implements ListSet {
    // you are free to add members
    AtomicReference<Node> head;

    public LockFreeListSet() {
        // implement your constructor here
        head = new AtomicReference<>(new Node(-1));
    }

    /**
     * add one element in the end of list
     * @param value
     * @return
     */
    public boolean add(int value) {
        // implement your add method here
        Node newNode = new Node(value);
        while (true) {
            Node prev = head.get();
            Node cur = head.get().next.get();
            while (cur != null && cur.value <= value) {
                if (cur.value == value) {
                    return false;
                }
                prev = cur;
                cur = cur.next.get();
            }

            if (prev.value == value) {
                return false;
            }
            return prev.next.compareAndSet(cur, newNode) && newNode.next.compareAndSet(null, cur);
        }
    }

    /**
     * remove cur node and prev's node point to cur.next
     * @param value
     * @return
     */
    public boolean remove(int value) {
        while (true) {
            Node prev = head.get();
            Node cur = head.get().next.get();

            while (cur != null && cur.value < value) {
                if (cur.value > value) {
                    return false;
                }
                prev = cur;
                cur = cur.next.get();
            }

            if ((prev != null && cur != null && cur.value == value)) {
                return prev.next.compareAndSet(cur, cur.next.get());
            }
        }
    }

    /**
     *
     * @param value
     * @return
     */

    public boolean contains(int value) {
        // implement your contains method here
        while (true) {
            Node cur = head.get();

            while (cur != null && cur.value <= value) {
                if (cur.value == value) {
                    return true;

                }
                cur = cur.next.get();
            }

            return false;
        }
    }

    protected class Node {
        public Integer value;
        public AtomicReference<Node> next;

        public Node(Integer x) {
            value = x;
            next = new AtomicReference<>(null);
        }
    }

    /**
     * stringbuilder to save space
     * @return
     */


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head.get();
        while (node.next.get() != null) {
            sb.append(node.next.get().value).append(",");
            node = node.next.get();
        }

        return sb.toString();
    }
}