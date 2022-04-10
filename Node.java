import java.util.concurrent.locks.ReentrantLock;

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