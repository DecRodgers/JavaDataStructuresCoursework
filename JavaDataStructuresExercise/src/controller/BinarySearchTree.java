package controller;

import model.Pet;
import model.Product;

/* 
 * Modification of 'BinarySearchTree' Library Class
 */

public class BinarySearchTree {

    public class NotUniqueException extends Exception{}
    
    public class NotFoundException extends Exception {}
    
    private class BinarySearchTreeNode {
        private Pet petObject;
        private SortedLinkedList productList = new SortedLinkedList();
        private BinarySearchTreeNode left;
        private BinarySearchTreeNode right;
    }
    
    private BinarySearchTreeNode root;
    private BinarySearchTreeNode current;
    private BinarySearchTreeNode parent;

    public String toString(){
        String treeDetails = new String();
        if(this.isEmpty()) {
            treeDetails+="### No Pets to Display ###";
        } else if (!this.isEmpty()) {            
        	treeDetails += "### All Pets ###\n";
        	treeDetails += this.getInOrder(this.root);
        }
        return treeDetails;
    }

    public boolean isEmpty() {
  	   return this.root == null;
    }
    
    private String getInOrder(BinarySearchTreeNode current) {
        String inOrderDetails = new String();        
        if (current != null) {        	
            inOrderDetails += this.getInOrder(current.left);            
            inOrderDetails += current.petObject + "\n";            
            inOrderDetails += this.getInOrder(current.right);            
        }
        return inOrderDetails;
    }

    public void insert(Pet object) throws NotUniqueException {
        BinarySearchTreeNode newNode = new BinarySearchTreeNode();
        newNode.petObject = object;        
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.insert(newNode,this.root);
        }
    }

    private void insert(BinarySearchTreeNode newNode,BinarySearchTreeNode current) throws NotUniqueException{
        if (newNode.petObject.compareTo(current.petObject) == 0)
            throw new NotUniqueException();
        if (newNode.petObject.compareTo(current.petObject) < 0) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                this.insert(newNode,current.left);
            }
        } else if (current.right == null) {
            current.right = newNode;
        } else {
            this.insert(newNode,current.right);
        }
    }

    public Comparable find(Comparable object) throws NotFoundException {
        return this.find(object,this.root);
    }

    private Comparable find(Comparable object, BinarySearchTreeNode current) throws NotFoundException{
        Comparable foundObject;
        if (current != null) {
            if (object.compareTo(current.petObject) == 0) {
                this.current=current;
                foundObject = current.petObject;
            } else{
                this.parent=current;
                if (object.compareTo(current.petObject) < 0) {
                    foundObject = this.find(object,current.left);
                } else {
                    foundObject = this.find(object,current.right);
                }
            }
        } else{
            throw new NotFoundException();
        }
        return foundObject;
    }
    
    public Comparable remove(Comparable object) throws NotFoundException {        
        Comparable removedObject=this.find(object);
        if (this.current.left == null && this.current.right == null) {
            this.replaceNode(null);
        } else if (this.current.left != null && this.current.right == null) {
            this.replaceNode(this.current.left);
            this.current.left = null;
        } else if (this.current.left == null && this.current.right != null) {
            this.replaceNode(this.current.right);
            this.current.right = null;
        } else {
            this.replaceWithNextLargest(this.current, this.current, this.current.right);
        }
        return removedObject;
    }

    private void replaceNode(BinarySearchTreeNode replacement) {        
        if (this.current == this.root) // removing root
        {
            this.root = replacement;
        } else if (this.current == this.parent.left) {
            this.parent.left = replacement;
        } else {
            this.parent.right = replacement;
        }
        this.current.petObject = null;
    }

    private void replaceWithNextLargest(BinarySearchTreeNode nodeForDeletion, BinarySearchTreeNode parent, BinarySearchTreeNode current) {
        if (current.left == null) {
            nodeForDeletion.petObject = current.petObject;
            if (parent == nodeForDeletion) {
                parent.right = current.right;
            } else {
                parent.left = current.right;
            }
            current.petObject = null;
            current.right = null;
        } else {
            this.replaceWithNextLargest(nodeForDeletion, current, current.left);
        }
    }
    
    
    //Custom Methods    
    public BinarySearchTreeNode findNode(Comparable object) throws NotFoundException {
        return this.findNode(object,this.root);
    }

    private BinarySearchTreeNode findNode(Comparable object, BinarySearchTreeNode current) throws NotFoundException{
    	BinarySearchTreeNode foundNode;
        if (current != null) {
            if (object.compareTo(current.petObject) == 0) {
                this.current=current;
                foundNode = current;
            } else{
                this.parent=current;
                if (object.compareTo(current.petObject) < 0) {
                	foundNode = this.findNode(object,current.left);
                } else {
                	foundNode = this.findNode(object,current.right);
                }
            }
        } else{
            throw new NotFoundException();
        }
        return foundNode;
    }
    
    public void insertProduct(Comparable petName, Product newProduct) throws NotUniqueException, NotFoundException {    	
    	try {
    		BinarySearchTreeNode petNode = ((BinarySearchTree)this).findNode(petName);
    		((SortedLinkedList)petNode.productList).insert(newProduct);	        
	    }catch (SortedLinkedList.NotUniqueException e) {
	        throw new NotUniqueException(); 
	    }  	
    }
    
    
    public Comparable findProductByCode (Comparable petName, String productCode) throws NotFoundException {
    	Comparable foundProduct = null;
    	try {
    		BinarySearchTreeNode petNode = ((BinarySearchTree)this).findNode(petName);
    		foundProduct = ((SortedLinkedList)petNode.productList).findByProductCode(productCode); 		
            
        } catch (SortedLinkedList.NotFoundException e) {
        	throw new NotFoundException();            
        }
    	return foundProduct;
    }
    
    
    
	public Comparable removeProductByCode(Comparable petName, String productCode) throws NotFoundException {	    	    
		Comparable removedProduct = null;
		try {
			BinarySearchTreeNode petNode = ((BinarySearchTree)this).findNode(petName);
			removedProduct = ((SortedLinkedList)petNode.productList).removeByProductCode(productCode);            
        } catch (SortedLinkedList.NotFoundException e) {
            throw new NotFoundException();
        }
		return removedProduct;
	}	
	
	public String displayAllProducts() {
		return displayAllProducts(this.root);
	}
    
    private String displayAllProducts(BinarySearchTreeNode current) {
    	String fullList = new String();
        if (current != null) {
        	fullList += this.displayAllProducts(current.left);
        	fullList += "\n"+current.petObject+" Product List:\n";
        	fullList += current.productList;
        	fullList += this.displayAllProducts(current.right);
        }
        return fullList;
    }
    
    public String displayProductsByPet(Comparable petName) throws NotFoundException {
    	String petProductList = new String();
		BinarySearchTreeNode petNode = ((BinarySearchTree)this).findNode(petName);			
		petProductList += "Product list : "+petNode.petObject+"\n";
		petProductList += petNode.productList;        
    	return petProductList;
    }     

}
