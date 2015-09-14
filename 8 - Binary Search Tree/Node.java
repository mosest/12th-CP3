//Tara Moses
//Node Class for CP3 Assignment 7: Binary Search Tree
//March 5, 2014

public class Node {
	int value;
	Node left;
	Node right;
	Node parent;
	
	public Node(int n, Node l, Node r, Node p) {
		value=n;
		left=l;
		right=r;
		parent=p;
	}
	
	public Node(int n) {
		value=n;
		left=null;
		right=null;
		parent=null;
	}
	
	public int compareTo(Node newNode) {
		if (value>newNode.value) return -1;
		else if (value==newNode.value) return 0;
		else return 1;
	}
}
