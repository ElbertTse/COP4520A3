public class ConcurrentLL {
    public class Node{
        Node next;
        int data;
        int key;

        public Node(int key) {
            this.key = key;
            this.data = key;
            next = null;
        }
    }

    private Node head;

    public ConcurrentLL(){
        this.head = new Node(Integer.MIN_VALUE);
    }

    public boolean contains(int target) {
        Node cur = this.head.next;

        while (cur != null && cur.key <= target) {
            if (cur.key == target) {
                return true;
            }

            cur = cur.next;
        }

        return false;
    }

    public boolean add(int item) {
        if (contains(item)) {
            return false;
        }

        Node pred = this.head, cur = this.head.next;

        // Move to where we should add
        while (cur != null && cur.key < item) {
            pred = cur;
            cur = cur.next;
        }

        Node toBeAdded = new Node(item);
        toBeAdded.next = cur;
        pred.next = toBeAdded;

        return true;
    }

    public boolean remove(int target) {
        if (!contains(target)) {
            return false;
        }
        
        Node pred = this.head, cur = this.head.next;

        // Move to where the target is

        while (cur != null && cur.key < target) {
            pred = cur;
            cur = cur.next;
        }

        pred.next = cur.next;
        
        return true;
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
