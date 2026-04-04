package org.example.practice;

public class SortedSinglyLinkedList {

    private NodeList head;

   private static class NodeList {
        int data;
        NodeList next;

        NodeList(int data){
            this.data = data;
            this.next = null;
        }
    }

    static void main(String[] args) {

       NodeList n1 = new NodeList(1);
       NodeList n2 = new NodeList(1);

       NodeList n3 = new NodeList(2);

       NodeList n4 = new NodeList(3);
       NodeList n5 = new NodeList(4);
       NodeList n6 = new NodeList(4);



    }

    public void addAtEnd(NodeList newNode){
       NodeList current = head;
    }
}
