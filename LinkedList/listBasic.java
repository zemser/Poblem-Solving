
import java.util.*;


public class listBasic {
    public static void main(String args[]) {
        System.out.print("---------------Start-----------------");
        listBasic list = new listBasic();
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next  = new ListNode(3);
        head.next.next.next.next  = new ListNode(5);
     
        /* Test Desired fucntion */
        // list.nextLargerNodes(head);
        int x = list.getDecimalValue(head);
        System.out.print(x);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode();
        ListNode cur = ret;
        while(l1 != null && l2 !=null){
            if(l1.val < l2.val){
                cur.next =new ListNode(l1.val);
                l1=l1.next;
                
            }else{
                cur.next =new ListNode(l2.val);
                l2=l2.next;
            }
            cur = cur.next;
            
        }
        //we have finished one of the lists
        while(l1 != null){
            cur.next =new ListNode(l1.val);
            l1=l1.next;
            cur = cur.next;
        }
        while(l2 !=null){
            cur.next =new ListNode(l2.val);
            l2=l2.next;
            cur = cur.next;
        }
        return ret.next;
        
    }


    //Reverse a singly linked list.
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
    public ListNode reverseListRec(ListNode head) {
       if(head == null)
            return null;
        if(head.next == null)
            return head;
        ListNode rev = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
    
    
    // Given a non-empty, singly linked list with head node head, return a middle node of linked list.
    // If there are two middle nodes, return the second middle node.
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    // Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
    // Return the decimal value of the number in the linked list
    public int getDecimalValue(ListNode head) {
        ListNode cur = head;
        int ret = 0;
        while(cur != null){
            ret *= 2;
            ret += cur.val; //another option instead is to do (ret << 1) | cur.val;
            cur = cur.next;
        }
        return ret;
    }


    //Remove all elements from a linked list of integers that have value val.
    public ListNode removeElementsIterative(ListNode head, int val) {
        ListNode cur = head, prev = head; 
        while(cur !=null){
            if(cur.val == val){
                if(prev == head && cur == head){
                    head = head.next;
                    prev = head;
                    cur = head;
                    
                } else {
                    prev.next = cur.next;
                    cur = cur.next;
                }
                
            } else{
                prev = cur;
                cur = cur.next;
            }
           
        }
        return head;
    }
    public ListNode removeElementsRecursive(ListNode head, int val) {
        if(head == null) return null;
        
        if(head.val == val) return removeElementsRecursive(head.next, val);
        
        head.next = removeElementsRecursive(head.next, val);
        return head;
    }


    //Given a singly linked list, determine if it is a palindrome.
    //using addional space
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> s = new Stack<Integer>();
        ListNode cur = head;
        while(cur != null){
            s.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            int poped = s.pop();
            if(poped != cur.val)
                return false;
            cur = cur.next;
        }
        return true;
    }
    //without using addional space
     public boolean isPalindrome2(ListNode head) {
      /*
      the trick is to use 2 pointers - slow and fast:  get slow to the middle of the list by forwarding the fast double faster.
      then reverse the second half of the list and point the fast pointer to the start of the first half.
      now fastpointer points to the start of the half and slowpointer points to the start of the second half reversed list e.g 
      if we had 1->2->3->2->1 now we have 1->2->3<-2<-1
       */
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        slow = reverseListRec(slow);
        while(slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }


    //Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.
    public void deleteNode(ListNode node) {
        //we are guaranteed that the nodes isnt a tail node - if it was we can't solve this
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //Write a program to find the node at which the intersection of two singly linked lists begins.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
       ListNode pointer1;
       ListNode pointer2;
       int countA = countNodes(headA);
       int countB = countNodes(headB);
       if(countA > countB){
           pointer1 = headA;
           pointer2 = headB;
       } else {
           pointer1 = headB;
           pointer2 = headA;
       }
       for (int i = 0; i < Math.abs(countA - countB); i++) {
           pointer1 = pointer1.next;
       }
       while(pointer1 != null && pointer2 != null){
           if(pointer1.val == pointer2.val){
               return pointer1;
           }
           pointer1 = pointer1.next;
           pointer2 = pointer2.next;
       }
       return null;
    }
    public int countNodes(ListNode n){
        int count = 0;
        while(n != null){
            count++;
            n = n.next;
        }
        return count;
    }

    //Given head, the head of a linked list, determine if the linked list has a cycle in it. and if so find the start of the loop
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow)
                return true;
        }
        return false;
    }

    //Given head, the head of a linked list, determine if the linked list has a cycle in it. and if so find the start of the loop
    public ListNode findCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
               
        }
        return null;
    }


    //Sort a linked list using insertion sort.
    public ListNode insertionSortList(ListNode head) {
        if(head == null)
            return head;
        ListNode retHead = new ListNode(head.val);
        ListNode cur = head.next;
        while(cur != null){
           ListNode tmp = retHead;
           ListNode prev = null;
           ListNode node = new ListNode(cur.val);
           while(tmp != null){
               if(cur.val < tmp.val){
                    break;  
               }
               prev = tmp;
               tmp = tmp.next;
           }
           if(prev == null){
            node.next = retHead;
            retHead = node;
           } else{
            prev.next = node;
            node.next = tmp;
           }
           cur = cur.next;
        }
        return retHead;
    }
   


}