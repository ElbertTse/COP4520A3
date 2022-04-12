// Elbert Tse, COP 4520, Spring 2022

import java.util.concurrent.atomic.AtomicMarkableReference;

public class ConcurrentLL {
    // Optimisic list
    private Node head;

    public ConcurrentLL() {
        this.head = new Node(Integer.MIN_VALUE);
    }

    public boolean isEmpty() {
        boolean[] marked = {false};
        Node node = this.head.next.get(marked);

        return node == null && !marked[0];
    }

    public boolean contains(int target) {
        boolean[] marked = {false};
        Node cur = this.head;

        while (cur.key < target) {
            cur = cur.next.getReference();

            if (cur == null) {
                return false;
            }

            // Suppressing since we really just want to update the marked array
            // Not using suc
            @SuppressWarnings("unused")
            Node suc = cur.next.get(marked);
        }

        return (cur.key == target) && !marked[0];
    }

    public boolean add(int item) {
        while (true) {
            Window window = find(head, item);
            Node pred = window.pred, cur = window.cur;

            if (cur != null && cur.key == item) {
                return false;
            } else {
                Node node = new Node(item);
                node.next = new AtomicMarkableReference<Node>(cur, false);

                if (pred.next.compareAndSet(cur, node, false, false)) {
                    return true;
                }
            }
        }
    }

    public boolean remove(int target) {
        boolean snip;

        while (true) {
            Window window = find(this.head, target);
            Node pred = window.pred, cur = window.cur;

            if (cur != null && cur.key != target) {
                return false;
            } else {
                Node suc = cur.next.getReference();

                // Mark cur as logically deleted
                snip = cur.next.compareAndSet(suc, suc, false, true);

                if (!snip) {
                    continue;
                }
                // Try to remove cur once
                pred.next.compareAndSet(cur, suc, false, false);

                return true;
            }
        }
    }

    public int dequeue() throws NullPointerException {
        int retval = Integer.MIN_VALUE;
        boolean[] marked = {false};
        Node pred = this.head, cur = this.head.next.get(marked);

        if (!marked[0] && cur != null) {
            Node suc = cur.next.getReference();
            retval = cur.data;

            // Now remove cur
            // Locigcal delete
            cur.next.compareAndSet(suc, suc, false, true);

            // Physical delete
            pred.next.compareAndSet(cur, suc, false, false);
        }

        return retval;
    }

    public Window find(Node head, int target) {
        Node pred = null, cur = null, suc = null;
        boolean[] marked = {false};
        boolean snip;

        retry: while (true) {
            pred = head;
            cur = pred.next.getReference();

            // Move to window
            while (true) {
                if (cur == null) {
                    return new Window(pred, cur);
                }
                else {
                    suc = cur.next.get(marked);
                }
                // Found a marked node, physically remove it
                while (marked[0]) {
                    snip = pred.next.compareAndSet(cur, suc, false, false);

                    if (!snip) {
                        continue retry;
                    }
                    cur = suc;

                    if (cur == null) {
                        // Cur is null so retry
                        continue retry;
                    }

                    suc = cur.next.get(marked);
                }

                if (cur.key >= target) {
                    return new Window(pred, cur);
                }

                pred = cur;
                cur = suc;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = this.head;

        while (cur != null) {
            sb.append(cur.data + " -> ");
            cur = cur.next.getReference();
        }

        return sb.toString();
    }
}
