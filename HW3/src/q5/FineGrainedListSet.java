package q5;

import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedListSet implements ListSet {
    private Node head;

    public FineGrainedListSet() {
        this.head = new Node(-1);//less than 0
        head.next = new Node(Integer.MAX_VALUE);
    }

    /**
     * add one node at the end of list
     * @param value
     * @return
     */
    public boolean add(int value) {
        Node prev;
        head.listLock.lock();
        prev = head;

        try {
            Node cur = prev.next;
            cur.listLock.lock();

            try {
                while (cur.value < value) {//list is sorted
                    prev.listLock.unlock();
                    prev = cur;
                    cur = cur.next;
                    cur.listLock.lock();
                }
                if (cur.value == value) {
                    return false;
                }

                Node node = new Node(value);
                node.next = cur;
                prev.next = node;

                return true;
            } finally {
                cur.listLock.unlock();
            }
        } finally {
            prev.listLock.unlock();
        }
    }

    /**
     * remove any position
     * @param value
     * @return
     */

    public boolean remove(int value) {
        Node prev;
        Node cur;
        head.listLock.lock();
        prev = head;

        try {
            cur = prev.next;
            cur.listLock.lock();
            try {
                while (cur.value < value) {
                    prev.listLock.unlock();
                    prev = cur;
                    cur = cur.next;
                    cur.listLock.lock();
                }
                if (cur.value == value) {
                    prev.next = cur.next;

                    return true;
                }
                return false;
            } finally {
                cur.listLock.unlock();
            }
        } finally {
            prev.listLock.unlock();
        }
    }

    /**
     * to check if list contains this value
     * @param value
     * @return
     */

    public boolean contains(int value) {
        head.listLock.lock();
        Node cur = head;

        try {
            while (cur.value < value) {
                cur.listLock.unlock();
                cur = cur.next;
                cur.listLock.lock();
            }

            return cur.value == value;
        } finally {
            cur.listLock.unlock();
        }
    }

    protected class Node {
        public Integer value;
        public Node next;
        public ReentrantLock listLock;

        public Node(Integer x) {
            value = x;
            next = null;
            listLock = new ReentrantLock();
        }
    }

    /**
     * same as CGL
     * @return
     */

    public String toString() {
        Node dummy = head;
        Node cur = dummy.next;
        String sb = "";

        while (cur.next != null) {
            sb += cur.value;
            sb += ",";
            cur = cur.next;
        }

        return sb;
    }
}