package q5;

import java.util.concurrent.locks.ReentrantLock;

public class CoarseGrainedListSet implements ListSet {
    private ReentrantLock listLock;
    private Node head;

    public CoarseGrainedListSet() {
        // implement your constructor here
        this.listLock = new ReentrantLock();
        this.head = new Node(-1);
    }

    /**
     * add one node behind
     * @param value
     * @return
     */

    public boolean add(int value) {
        // implement your add method here
        listLock.lock();
        try {
            Node prev = head;
            Node cur = prev.next;
            while (cur != null && cur.value < value) {
                prev = cur;
                cur = cur.next;
            }

            if (cur != null && value == cur.value) {
                return false;
            } else {
                Node nextNode = new Node(value);
                prev.next = nextNode;
                nextNode.next = cur;
                return true;
            }

        } finally {
            listLock.unlock();
        }
    }

    /**
     * remove one node
     * @param value
     * @return
     */

    public boolean remove(int value) {
        // implement your remove method here
        listLock.lock();
        Node cur = head;
        Node prev = cur;

        while (cur != null) {
            if (cur.value == value) {
                prev.next = cur.next;
                listLock.unlock();

                return true;
            }

            prev = cur;
            cur = cur.next;
        }

        listLock.unlock();
        return false;
    }

    /**
     * check if this list contains this value
     * @param value
     * @return
     */

    public boolean contains(int value) {
        // implement your contains method here
        Node cur = head;
        listLock.lock();

        while (cur != null) {
            if (cur.value == value) {
                listLock.unlock();
                return true;
            }

            cur = cur.next;
        }

        listLock.unlock();
        return false;
    }

    protected class Node {
        public Integer value;
        public Node next;

        public Node(Integer x) {
            value = x;
            next = null;
        }
    }
    /**
     * return the string of list, if: 1 -> 2 -> 3, then return "1,2,3,"
     * check simpleTest for more info
     * @return
     */

    @Override
    public String toString() {
        Node dummy = head;
        Node cur = dummy.next;
        String sb = "";

        while (cur != null) {
            sb += cur.value;
            sb += ",";
            cur = cur.next;
        }

        return sb;
    }
}