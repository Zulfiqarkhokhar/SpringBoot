package org.example.practice;

public class FindMiddleNodeInSinglyLinkedList {

    private Node head;
    static void main(String[] args) {

        FindMiddleNodeInSinglyLinkedList sll = new FindMiddleNodeInSinglyLinkedList();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        sll.addAtBeginning(n1);
        sll.addAtBeginning(n2);
        sll.addAtBeginning(n3);
        sll.addAtBeginning(n4);

        System.out.println(sll.findMiddle());
        Node n5 = new Node(5);
        sll.addAtBeginning(n5);
        System.out.println(sll.findMiddle());

    }
    private static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
    // add at beginning
    public void addAtBeginning(Node newNode){
        newNode.next = head;
        head = newNode;
    }

    public int findMiddle(){
        Node slowNode = head;
        Node fastNode = head;
        while (fastNode != null && fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode.val;
    }
}
