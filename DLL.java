package dsfinal;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.PrintWriter;
public class DLL {
    public class Node {
        String value;
        Node next;
        Node prev;
        public Node (String val, Node n, Node p) {
            value = val;
            next = n;
            prev = p;
        }
        public Node (String val) {
            // call other constructor
            this(val, null, null);
        }
    }
    
    Node head;
    Node last;
    
    public DLL() {
        head = null;
        last = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            // There is an element at p
            count++;
            p = p.next;
        }
        return count;
    }
    
    public void add(String v) {
        if (isEmpty()) {
            head = new Node(v, null, null);
            last = head;

        } else {
            // Add to end of existing list
            last.next = new Node(v, null, null);
            last = last.next;
        }
    }
    
    public void add(int index, String v) {
        // Check if the index is valid
        if (index < 0 || index > size()) {
        throw new IndexOutOfBoundsException();
        }
        // If the index is at the beginning of the list
        if (index == 0) {
            // Insert a new node at the head
            Node p = head;
            head = new Node(v, p, null);
            // Update the previous reference of the next node
            if (p != null) {
                p.prev = head;
            }
            // If this is the first node being added, update the last reference
            if (last == null) {
                last = head;
            }
            return;
        }
        // Traverse to the node just before the insertion point
        Node pred = head;
        for (int k = 1; k < index; k++) {
            pred = pred.next;
        }
        // Get the successor node
        Node succ = pred.next;
        // Create a new node to be inserted between pred and succ
        Node middle = new Node(v, succ, pred);
        // Update references of pred and succ to include the new node
        pred.next = middle;
        if (succ == null) {
            // If succ is null, update the last reference
            last = middle;
        } else {
            // Update the previous reference of the successor node
            succ.prev = middle;
        }  
    }
    
    public void printAt(int index) {
        Node ref = head; 
        if (ref == null) {
            System.out.println("List is empty");
            return;
        }
        for (int i = 0; i < index; i++) {
            ref = ref.next;
        }
        System.out.println(ref.value);  
        
    }
    
    public int indexOf(String value) {
        Node ref = head;
        int index = 0;
        while (ref != null) {
            if (ref.value.equalsIgnoreCase(value)) {
                break;
            }
            index++;
            ref = ref.next;
        }
        return index;
    }
    //generate question
    public String quest (int x) {
        Node ref = head; 
        if (ref == null) {
            System.out.println("List is empty");
            
        }
        for (int i = 0; i < x; i++) {
            ref = ref.next;
        }
        return ref.value;
        
    }
    
    
}
