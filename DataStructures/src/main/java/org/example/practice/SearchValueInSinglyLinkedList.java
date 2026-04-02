package org.example.practice;

public class SearchValueInSinglyLinkedList {

    private Node head;

    static void main(String[] args) {

        SearchValueInSinglyLinkedList sll = new SearchValueInSinglyLinkedList();

        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        Node n5 = new Node(50);

        sll.addAtBeginning(n1);
        sll.addAtBeginning(n2);
        sll.addAtBeginning(n3);
        sll.addAtBeginning(n4);
        sll.addAtBeginning(n5);

        System.out.println(sll.searchValue(400));
        System.out.println(sll.searchValue(40));

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

    // search value in sll

    public boolean searchValue(int num){

        Node current = head;
        while (current != null){
            if(current.val == num){
                return true;
            }
            current = current.next;
        }

        return false;
    }

}
