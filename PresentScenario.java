public class PresentScenario {
    public static void main(String[] args) {
        ConcurrentLL ll = new ConcurrentLL();

        for (int i = 0; i < 10; i++)
            ll.add(i);

        System.out.println(ll);

        System.out.println(ll.contains(5));
        ll.remove(5);
        System.out.println(ll);
        System.out.println(ll.contains(5));
    }
}