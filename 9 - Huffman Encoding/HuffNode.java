//Tara Moses
//Class for CP3 Assignment 9: Huffman Encoding
//March 18, 2014

public class HuffNode implements Comparable<HuffNode> {
	int freq;
	char value;
	HuffNode parent;
	HuffNode left;
	HuffNode right;
	boolean isLeaf;
	String code;

	public HuffNode(int frequency, char nodeValue) {
		this.freq = frequency;
		this.value = nodeValue;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.code="";
		
		isLeaf=true; //a character we're trying to encode or decode!
	}
	
	public HuffNode(HuffNode leftChild, HuffNode rightChild, int frequency) {
		this.left = leftChild;
		this.right = rightChild;
		this.freq = frequency;
		this.value = '\0'; //control character :D
		this.parent = null;
		this.code="\0";
		
		isLeaf=false; //not a character we're trying to encode or decode :<
	}
	
	public int compareTo(HuffNode n) {
		if (this.freq < n.freq) return -1;
		else if (this.freq==n.freq) return 0;
		return 1;
	}
	
	public boolean equals(HuffNode other) {
		if (this.value!=other.value) return false;
		return true;
	}
}
