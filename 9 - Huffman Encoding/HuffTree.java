//Tara Moses
//Class for CP3 Assignment 9: Huffman Encoding
//March 18, 2014

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class HuffTree {
	PriorityQueue<HuffNode> queue;
	HuffNode head;
	List<HuffNode> nodes;

	public HuffTree(PriorityQueue<HuffNode> frequencyList) {
		this.queue = frequencyList;
		this.nodes=new ArrayList<HuffNode>();
		this.constructTree();
	}
	
	public void constructTree() {
		HuffNode first;
		HuffNode second;
		HuffNode newNode;
		int freq;
		
		while (queue.size()>1) {
			first=queue.poll();
			second=queue.poll();
			freq=first.freq+second.freq;
			
			newNode=new HuffNode(first, second, freq);
			first.parent=newNode;
			second.parent=newNode;
			queue.add(newNode);
		}
		head=queue.poll();
		printCodes(head, "");
	}
	
	public String getCodeOfChar(char c) {
		for (HuffNode node : nodes) {
			if (node.value==c) return node.code;
		}
		return "";
	}
	
	public void printCodes(HuffNode node, String code) {
		if (!node.isLeaf) {
			code+="0";
			printCodes(node.left, code);
			code=code.substring(0,code.length()-1)+"1";
			printCodes(node.right, code);
		} else {
			node.code=code;
			nodes.add(node);
		}
	}
}
