package com.company;

import javax.sound.sampled.Line;

public class LinkList {
     Node head; // head of list

     // Linked list Node.
     // This inner class is made static
     // so that main() can access it
     static class Node {

          int data;
          Node next;

          // Constructor
          Node(int d) {
               data = d;
               next = null;
          }

     }
     public static void printList(LinkList list)
     {
          Node currNode = list.head;

          System.out.print("LinkedList: ");

          // Traverse through the LinkedList
          while (currNode != null) {
               // Print the data at current node
               System.out.print(currNode.data + " ");

               // Go to next node
               currNode = currNode.next;
          }
     }
}
