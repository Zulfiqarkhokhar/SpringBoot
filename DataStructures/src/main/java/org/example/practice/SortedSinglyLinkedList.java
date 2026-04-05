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

       SortedSinglyLinkedList sll = new SortedSinglyLinkedList();

       sll.addAtEnd(n1);
       sll.addAtEnd(n2);
       sll.addAtEnd(n3);
       sll.addAtEnd(n4);
       sll.addAtEnd(n5);
       sll.addAtEnd(n6);

       sll.printList();

       sll.removeDuplicate();

       sll.printList();


    }

    public void addAtEnd(NodeList newNode){
        if(head == null){
            head = newNode;
            return;
        }
       NodeList current = head;
       while(current.next != null){
           current = current.next;
       }
       current.next = newNode;
    }

    public void printList(){
       NodeList current = head;
       while (current != null){
           System.out.print(current.data+" --> ");
           current = current.next;
       }
        System.out.println("null");
    }

    public void removeDuplicate(){
       NodeList current = head;
       while (current != null && current.next != null){
           if(current.data == current.next.data){
               current.next = current.next.next;
           }
           current = current.next;
       }
    }
}
