package org.example.practice;

import org.example.linkedlist.SinglyLinkedList;
import org.w3c.dom.Node;

public class ReverseSinglyLinkedList {

    private Node head;

    static void main(String[] args) {

        ReverseSinglyLinkedList sll = new ReverseSinglyLinkedList();

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

        sll.traverseNodes();
        System.out.println("After Reverse");
        sll.head = sll.reverse();
        sll.traverseNodes();
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

    // reverse sll
    public Node reverse(){
        Node current = head;
        Node previous = null;
        Node next = null;
        while (current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;

    }
    //print list
    public void traverseNodes(){
        Node current = head;
        while (current != null){
            System.out.print(current.val+" --> ");
            current = current.next;
        }
        System.out.println("Null");
    }
}
