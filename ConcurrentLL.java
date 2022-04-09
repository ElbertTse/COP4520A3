import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLL {
    // Optimisic list
    public class Node {
        ReentrantLock reentrantLock;
        Node next;
        int data;
        int key;

        public Node(int key) {
            this.reentrantLock = new ReentrantLock();
            this.key = key;
            this.data = key;
            next = null;
        }
    }

    private Node head;

    public ConcurrentLL() {
        this.head = new Node(Integer.MIN_VALUE);
    }

    public boolean validate(Node pred, Node cur) {
        Node node = this.head;

        while (node.key <= pred.key) {
            if (node == pred) {
                return pred.next == cur;
            }

            node = node.next;
        }
        return false;
    }

    public boolean contains(int target) {
        while (true) {
            Node pred = this.head, cur = this.head.next;

            while (cur != null && cur.key < target) {
                pred = cur;
                cur = cur.next;
            }

            if (pred != null)
                pred.reentrantLock.lock();

            if (cur != null)
                cur.reentrantLock.lock();

            try {
                if (cur != null && validate(pred, cur)) {
                    return cur.key == target;
                } else {
                    return false;
                }
            } finally {
                if (pred != null)
                    pred.reentrantLock.unlock();

                if (cur != null)
                    cur.reentrantLock.unlock();
            }
        }
    }

    public boolean add(int item) {
        if (contains(item)) {
            return false;
        }

        while (true) {
            Node pred = this.head, cur = this.head.next;

            // Move to where we should add
            while (cur != null && cur.key < item) {
                pred = cur;
                cur = cur.next;
            }

            if (pred != null)
                pred.reentrantLock.lock();

            if (cur != null)
                cur.reentrantLock.lock();

            try {
                if (validate(pred, cur)) {
                    Node newItem = new Node(item);
                    newItem.next = cur;
                    pred.next = newItem;
                    return true;
                }
            } finally {
                if (pred != null)
                    pred.reentrantLock.unlock();

                if (cur != null)
                    cur.reentrantLock.unlock();
            }
        }
    }

    public boolean remove(int target) {
        if (!contains(target)) {
            return false;
        }

        while (true) {
            Node pred = this.head, cur = this.head.next;

            while (cur != null && cur.key < target) {
                pred = cur;
                cur = cur.next;
            }

            if (pred != null)
                pred.reentrantLock.lock();

            if (cur != null)
                cur.reentrantLock.lock();

            try {
                if (validate(pred, cur)) {
                    // Double check that cur's key is the target
                    if (cur.key == target) {
                        pred.next = cur.next;
                        return true;
                    } else {
                        return false;
                    }
                }
            } finally {
                if (pred != null)
                    pred.reentrantLock.unlock();

                if (cur != null)
                    cur.reentrantLock.unlock();
            }
        }
    }

    public int dequeue() throws NullPointerException {
        int retval = Integer.MIN_VALUE;
        Node pred = this.head, cur = this.head.next;
        // if (cur == null)
        // throw new NullPointerException();

        // retval = cur.data;

        // pred.next = cur.next;

        // return retval;

        if (pred != null)
                pred.reentrantLock.lock();

            if (cur != null)
                cur.reentrantLock.lock();

        try {
            if (validate(pred, cur)) {
                retval = cur.data;
                pred.next = cur.next;
            }
        } finally {
            if (pred != null)
            pred.reentrantLock.unlock();

            if (cur != null)
                cur.reentrantLock.unlock();
        }

        return retval;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this.head.next;

        while (cur != null) {
            sb.append(cur.data + " ");
            cur = cur.next;
        }

        return sb.toString();
    }
}
