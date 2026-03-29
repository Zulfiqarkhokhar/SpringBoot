package org.example.linkedlist;

// Singly LinkedList
// It is data structure used for storing collection of nodes and has following properties
// It contains sequence of nodes
// A node has data and reference to next node in a list
// First node is the head node
// Last node has the data and points to null

public class SinglyLinkedList {

    private ListNode head;

    private static class ListNode{
        private int data;
        private ListNode next;

        public ListNode(int data){
            this.data = data;
            this.next = null;
        }
    }

    static void main(String[] args) {

        SinglyLinkedList sll = new SinglyLinkedList();
        sll.head = new ListNode(10);
        ListNode second = new ListNode(20);
        ListNode third = new ListNode(30);
        ListNode fourth = new ListNode(40);
        ListNode fifth = new ListNode(50);
        ListNode sixth = new ListNode(60);

        //attaching nodes
        sll.head.next = second;
        second.next = third;
        third.next = fourth;

        System.out.println("Traversing Nodes");
        sll.traverseNodes();

        System.out.println("Finding Length of Nodes");
        System.out.println("Length of LinkedList: "+sll.findLength());

        System.out.println("Adding node at beginning");
        sll.addAtBeginning(fifth);
        sll.traverseNodes();

        System.out.println("Adding node at end");
        sll.addAtEnd(sixth);
        sll.traverseNodes();

        System.out.println("Adding node at given position");
        sll.insetAtPosition(70,1);
        sll.traverseNodes();
        System.out.println("Adding node at given position");
        sll.insetAtPosition(80,1);
        sll.traverseNodes();

        System.out.println("Adding node at given position");
        sll.insetAtPosition(90,3);
        sll.traverseNodes();
        System.out.println("Adding node at given position");
        sll.insetAtPosition(100,7);
        sll.traverseNodes();
        System.out.println("Adding node at given position");
        sll.insetAtPosition(110,11);
        sll.traverseNodes();
        System.out.println("deleting first node");
        sll.deleteFirst();
        sll.traverseNodes();
        System.out.println("deleting last node");
        sll.deleteFromEnd();
        sll.traverseNodes();
        System.out.println("deleting last node");
        sll.deleteFromEnd();
        sll.traverseNodes();

        System.out.println("deleting at given position node");
        sll.deleteAtPosition(4);
        sll.traverseNodes();

    }

    //print list
    public void traverseNodes(){
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.data+" --> ");
            cur = cur.next;
        }
        System.out.println("Null");
    }

    // length of Singly LinkedList
    public int findLength(){
        int count=0;
        ListNode current = head;
        while (current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    // add node at beginning
    public void addAtBeginning(ListNode newNode){
        newNode.next = head;
        head = newNode;
    }

    // add node to end of list
    public void addAtEnd(ListNode newNode){

        if(head == null){
            head = newNode;
            return;
        }

        ListNode current = head;
        while (current.next !=null){
            current = current.next;
        }
        current.next = newNode;
    }

    // insert node at given position
    public void insetAtPosition(int val,int position){
        ListNode newNode = new ListNode(val);
        if (position == 1){
            newNode.next = head;
            head = newNode;
        }
        else {
            ListNode current = head;
            int count =1;
            while (current != null){
                current = current.next;
                count++;
                if (count == position-1){
                    ListNode temp = current.next;
                    current.next = newNode;
                    newNode.next = temp;
                }
            }
        }
    }

    // delete first node

    public void deleteFirst(){
        if (head == null){
            return;
        }
        head = head.next;

        // if you want to return deleted node
//        ListNode temp = head;
//        head = head.next;
//        temp.next = null;
//        return; temp;
    }

    // delete node from end
    public void deleteFromEnd(){
        if (head == null || head.next == null){
            return;
        }
        // one logic
//        ListNode current = head;
//        while (current.next.next != null){
//            current = current.next;
//        }
//        current.next = null;

        // second logi

        ListNode previous = null;
        ListNode current = head;

        while (current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;

    }

    // delete at given position

    public void deleteAtPosition(int position){
        if (position == 1){
            head = head.next;
        }
        else{
            ListNode previous = head;
            int count = 1;
            while(count<position-1){
                previous = previous.next;
                count++;
            }
            ListNode current = previous.next;
            previous.next = current.next;
        }
    }

}
