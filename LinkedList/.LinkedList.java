class LinkedList {
    Node head; // head of the list

    static class Node {
        int data;
        Node next;

        // Constructor to create a new node
        // Next is by default initialized ass null
        Node(int d) {
        data = d; 
        }
    }
}
