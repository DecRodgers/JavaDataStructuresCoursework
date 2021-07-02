package controller;
import model.Product;

/* 
 * Modification of 'SortedLinkedList' Library Class
 */

public class SortedLinkedList {

    public class NotFoundException extends Exception {
    }

    public class NotUniqueException extends Exception {
    }
    
    
    protected class ListNode {
        protected Comparable object;
        protected ListNode next;
    }

    protected ListNode head;
    // set by find to allow remove to remove the found node
    private ListNode current;
    private ListNode previous;

    public String toString() {
        String listDetails = new String();
        if (this.head != null) {
            ListNode current = this.head;
            while (current != null) {            	
            	listDetails += current.object + "\n";
                current = current.next;
            }
        } else {
            listDetails += "### No Products to Display ###\n";
        }
        return listDetails;
    }

    public void insert(Comparable object) throws NotUniqueException {
        ListNode newNode = new ListNode();
        newNode.object = object;
        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode current = this.head;
            // require to explicitly set to null to avoid compilation error
            ListNode previous = null;
            Boolean insertionPositionFound = false;
            while (!insertionPositionFound) {
                if (object.compareTo(current.object) == 0) {
                    throw new NotUniqueException();
                }
                if (object.compareTo(current.object) < 0) {
                    insertionPositionFound = true;
                    newNode.next = current;
                    if (current == this.head) {
                        this.head = newNode;
                    } else {
                        previous.next = newNode;
                    }
                } else if (current.next == null) {
                    insertionPositionFound = true;
                    current.next = newNode;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public Comparable find(Comparable object) throws NotFoundException {
        if (this.head == null) {
            throw new NotFoundException();
        }
        Comparable foundObject = null;
        this.current = this.head;
        while (foundObject == null) {
            if (object.compareTo(this.current.object) == 0) {
                foundObject = this.current.object;
            } else if (object.compareTo(this.current.object) < 0) {
                throw new NotFoundException();
            } else if (this.current.next == null) {
                throw new NotFoundException();
            } else {
                this.previous = this.current;
                this.current = this.current.next;
            }
        }
        return foundObject;
    }

    public Comparable remove(Comparable object) throws NotFoundException {
        Comparable removedObject = this.find(object);
        if (this.current == this.head) {
            this.head = this.current.next;
        } else {
            this.previous.next = this.current.next;
        }
        this.current.object = null;
        this.current.next = null;
        return removedObject;
    }
    
    
    //Custom methods to use downcasted Product class method compareTo using ProductCode
    //rather than entering both name and code, and a number to find/remove    
    public Comparable findByProductCode(Comparable object) throws NotFoundException {
        if (this.head == null) {
            throw new NotFoundException();
        }
        Comparable foundObject = null;
        this.current = this.head;
        while (foundObject == null) {
            if (((String)object).compareToIgnoreCase(((Product)this.current.object).getProductCode()) == 0) {
                foundObject = this.current.object;
            } else if (((String)object).compareToIgnoreCase(((Product)this.current.object).getProductCode()) < 0) {
                throw new NotFoundException();
            } else if (this.current.next == null) {
                throw new NotFoundException();
            } else {
                this.previous = this.current;
                this.current = this.current.next;
            }
        }
        return foundObject;
    }
   
    public Comparable removeByProductCode(Comparable object) throws NotFoundException {
        Comparable removedObject = this.findByProductCode(object);
        if (this.current == this.head) {
            this.head = this.current.next;
        } else {
            this.previous.next = this.current.next;
        }
        this.current.object = null;
        this.current.next = null;
        return removedObject;
    }
    
    
}
