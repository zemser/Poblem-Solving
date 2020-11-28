import java.util.*;

public class LinkedListMed {
    public static void main(String args[]) {
        System.out.print("---------------Start-----------------");
        LinkedListMed list = new LinkedListMed();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next  = new ListNode(3);
        head.next.next.next.next  = new ListNode(5);
     
        /* Test Desired fucntion */
         list.oddEvenList(head);
        
    }


     /*We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
    Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
    Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).*/    
    public int[] nextLargerNodes(ListNode head) {
        if(head == null){
            return new int[0];
        }
        //count elemnts and create the array
        ListNode cur = head;
        int size = 0;
        while(cur != null){
            size++;
            cur = cur.next;
        }
        int [] ret = new int[size];
        Stack<Integer> stack = new Stack<Integer>();
        //traverse the list from the end and by keeping the elements in an array check if there is a bigger elemnt in the stack for each index - in the end add the cur elemnt to the stack
        head = reverseList(head);
        int index = size - 1;
        while(head != null){
            int curVal = head.val;
            while(!stack.isEmpty()){
                if(curVal < stack.peek()){
                    ret[index--] = stack.peek();
                    break; 
                }
                stack.pop();
            }
            if(stack.isEmpty()){
                ret[index--] = 0; 
            }
            stack.push(curVal);
            head = head.next;
        }
        return ret;
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while(cur != null){
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }



    /*Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
      You should try to do it in place. The program should run in O(1) space complexity and O(n   odes) time complexity.*/
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode oddHead = new ListNode(0);
        ListNode evenHead = new ListNode(0);
        ListNode oddP = oddHead;
        ListNode evenP = evenHead;
        ListNode cur = head;
        int i = 1;
        while(cur != null){
            if(i == 1){
                oddP.next = cur;
                oddP = oddP.next;
            }else{
                evenP.next = cur;
                evenP = evenP.next;
            }
            i ^= 1;
            cur = cur.next;

        }
        oddP.next = evenHead.next;
        evenP.next = null;
        return oddHead.next;
        

    }


    /*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. 
    Add the two numbers and return the sum as a linked list. */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode();
        ListNode cur = ret;
        int curry = 0;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + curry;
            cur.next = new ListNode(sum  % 10);
            curry = sum/10;
            l1 = l1.next; 
            l2 = l2.next;
            cur = cur.next;
        }
        while(l1 != null){
            int sum = l1.val + curry;
            cur.next = new ListNode(sum%10);
            curry = sum / 10;
            l1 = l1.next;
            cur = cur.next;
        }
        while(l2 != null){
            int sum = l2.val + curry;
            cur.next = new ListNode(sum%10);
            curry = sum / 10;
            l2 = l2.next;
            cur = cur.next;
        }
        if(curry > 0)
            cur.next = new ListNode(1);
        return ret.next;
    }
























    
    class Pair{
        int index;
        Node list;
        public Pair(int index, Node list){
            this.index = index;
            this.list = list;
        }
    }

    // public Node flatten(Node head) {
    //     Node ret = new Node();
    //     ArrayList<Pair> multi = new ArrayList<Pair>();
    //     Node cur = head;
    //     int start = 0;
    //     while(cur != null){
    //         if(cur.val = null){

    //         }
    //     }
    //     return ret;
   // }
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}


