public class linkedListImplementation{
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;

    public static void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
        return;
    }

    public static void addLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        return;
    }
    public static void printLL(){
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
    }

    public static int removeFirst(){
        if(head == null){
            return -1;
        }
        int val = head.data;
        if(head == tail){
            head = tail = null;
            return val;
        }
        head = head.next;
        return val;
    }
    public static int iterativeSearch(int key){
        Node temp = head;
        int i = 0;
        while(temp!=null){
            if(temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        return -1;
    }

    public static int recursiceSearch(int key,Node head){
        if(head == null){
            return -1;
        }
        if(head.data==key){
            return 0;
        }
        int idx = recursiceSearch(key, head.next);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }

    public static void reverseLinkedList(){
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
    //Find mid
    public static Node findMid(){
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static boolean isCycle(){
        //Floyd's cycle detection algorithm
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //Remove cycle from a linkedlist
    public static void removeCycle(){
        //detect cycle
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        slow = head;
        Node prev = null;
        while(slow!=fast){
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = null;
    }



    //getMin of linked list
    public static Node getMid(){
        Node slow = head;
        Node fast = head.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node mergeSort(Node head){
        if(head==null || head.next==null){
            return head;
        }
        //find mid
        Node mid = getMid();
        //call for left and right half
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);

        //merge both halfs
        return merge(newLeft,newRight);

    }
    public static Node merge(Node head1,Node head2){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while(head1 != null && head2 != null){
            if(head1.data<=head2.data){
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            }else{
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }
        }
        while(head1!=null){
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while(head2 != null){
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;           
        }
        return mergedLL.next;
    }
    public static void main(String[] args) {
        linkedListImplementation ll = new linkedListImplementation();
        // ll.addFirst(5);
        // ll.addFirst(4);
        // ll.addFirst(3);
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(6);


        // ll.printLL();
        // ll.removeFirst();
        // ll.removeFirst();
        // System.out.println();
        // ll.printLL();
        // System.out.println();
        // System.out.println(ll.iterativeSearch(4));
        // System.out.println();
        // System.out.println(recursiceSearch(3, head));
        // ll.reverseLinkedList();
        // System.out.println();
        // // ll.printLL(); 
        // System.out.println(ll.findMid().data);

        //For cycle detection
        // head = new Node(1);
        // head.next = new Node(2);
        // head.next.next = new Node(3);
        // head.next.next.next = new Node(4);
        // head.next.next.next.next = head;
        // System.out.println(isCycle());
        // removeCycle();
        // printLL();
    } 


}