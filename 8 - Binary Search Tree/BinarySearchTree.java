//Tara Moses
//Tree Class for CP3 Assignment 7: Binary Search Tree
//October 18, 2013

public class BinarySearchTree {
	Node root;
	int size;
	
	public BinarySearchTree() {
		root=null;
		size=0;
	}
	
	public void add(int value) {
		Node nodeToAdd=new Node(value);
		
		if (size==0) {
			root=nodeToAdd;
			size++;
		} else {
			helperAdd(nodeToAdd,root,null);
		}
		System.out.println("size="+size);
	}
	
	public void helperAdd(Node newNode, Node current, Node parent) {
		if (newNode.compareTo(current)==-1) {	//current.value<newnode.value so add to right
			if (current.right==null) {
				current.right=newNode;
				size++;
			} else helperAdd(newNode,current.right,current);
		} else if (newNode.compareTo(current)==1) {
			if (current.left==null) {
				current.left=newNode;
				size++;
			} else helperAdd(newNode,current.left,current);
		}
	}
	
	public void delete(int valueToDelete) {
		/*int count=0;
		currentNode=head;
		Node deleteNode;
		for (int i=0;i<size && currentNode.value!=valueToDelete;i++) 			//find node with valueToDelete
		{
			count++;
			current=current.connectsTo;
		}
		
		if (count==0) //node's at beginning
		{
			head=nodeAtPosition(count+1);
		}
		else //node's at the end or in the middle somewhere
		{
			nodeAtPosition(count-1).connectsTo=nodeAtPosition(count+1);
			nodeAtPosition(count).connectsTo=null;
		}
		size--;*/
	}
	
	public boolean search(int valueToSearchFor) {
		/*for (int i=0;i<size;i++)
		{
			if (nodeAtPosition(i).value==valueToSearchFor) return true;
		}*/
		return false;
	}
	
	public void print() {
		helperPrint(root);
	}
	
	public void helperPrint(Node current) {
		if (current!=null) {
			System.out.println("current is: "+current.value);
			helperPrint(current.left);
			helperPrint(current.right);
		}
	}
}
