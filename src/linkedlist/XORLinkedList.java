package linkedlist;

class XORNode {
    int data;
    XORNode npx; // XOR of previous and next node addresses

    XORNode(int data) {
        this.data = data;
        this.npx = null;
    }
}

public class XORLinkedList {
    private XORNode head = null;

    public static void main(String[] args) {
        XORLinkedList list = new XORLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.printList();

        // Get elements before the 3rd node
        list.getElementsBeforeK(3);
    }

    // XOR operation for nodes
    private XORNode xor(XORNode a, XORNode b) {
        return a == null ? b : (b == null ? a : (XORNode) ((long) System.identityHashCode(a) ^ (long) System.identityHashCode(b)));
    }

    // Add a node to the XOR linked list
    public void add(int data) {
        XORNode newNode = new XORNode(data);
        if (head == null) {
            head = newNode; // First node
        } else {
            XORNode curr = head;
            XORNode prev = null;

            // Traverse to the last node
            while (curr != null) {
                XORNode next = xor(prev, curr.npx);
                if (next == null) break; // Found the last node
                prev = curr;
                curr = next;
            }

            // Update links for the new node and the last node
            curr.npx = xor(prev, newNode);
            newNode.npx = curr;
        }
    }

    // Print the list forward
    public void printList() {
        XORNode curr = head;
        XORNode prev = null;
        XORNode next;

        System.out.print("Linked List: ");
        while (curr != null) {
            System.out.print(curr.data + " ");
            next = xor(prev, curr.npx);
            prev = curr;
            curr = next;
        }
        System.out.println();
    }

    // Get elements before the kth node
    public void getElementsBeforeK(int k) {
        XORNode curr = head;
        XORNode prev = null;
        XORNode next;
        int count = 1;

        // Traverse to the kth node
        while (curr != null && count < k) {
            next = xor(prev, curr.npx);
            prev = curr;
            curr = next;
            count++;
        }

        if (curr == null || count < k) {
            System.out.println("k is out of range.");
            return;
        }

        // Traverse backward to collect elements before kth
        System.out.print("Elements before k: ");
        while (prev != null) {
            System.out.print(prev.data + " ");
            next = xor(curr, prev.npx); // Calculate the previous node
            curr = prev;
            prev = next;
        }
        System.out.println();
    }
}

