package de.ostfalia.algo.ws19.base;

public class ChainedList {
	private Node head = null;
	
	private class Node {
		 
		 private Node next;
		 private IMember data;
		 
		 
		 public Node (Member member) {
			 next = head;
			 head = this;
			 data = member;
		 }
		}


}
