import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node {
    AtomicMarkableReference<Node> next;
    int data;
    int key;

    public Node(int key) {
        this.key = key;
        this.data = key;
        this.next = new AtomicMarkableReference<Node>(null, false);
    }
}