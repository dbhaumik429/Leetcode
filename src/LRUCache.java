import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Map<Integer, Node> data;
    int totalCapacity;
    int currCapacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        data = new HashMap<>(capacity);
        totalCapacity = capacity;
        currCapacity = 0;

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    public int get(int key) {
        if (!data.containsKey(key))
            return -1;

        Node dataNode = data.get(key);
        removeNode(dataNode);
        addNode(dataNode);

        return dataNode.val;
    }

    public void put(int key, int value) {
        if (data.containsKey(key)) {
            Node oldNode = data.get(key);
            removeNode(oldNode);
        }

        Node node = new Node(key, value);
        data.put(key, node);
        addNode(node);

        if (data.size() > totalCapacity) {
            Node nodeToDelete = tail.prev;
            removeNode(nodeToDelete);
            data.remove(nodeToDelete.key);
        }

    }

    private void removeNode(Node remove) {
        Node prevNode = remove.prev;
        Node nextNode = remove.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addNode(Node add) {
        Node nextNode = head.next;
        nextNode.prev = add;
        add.prev = head;
        add.next = nextNode;
        head.next = add;
    }

    //1,2
    //3,1
    //queue - FIFO
    //stack - LIFO
    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
