package de.ostfalia.algo.ws19.base;

public class Node {
 private static Node head = null;
 private Node next;
 private IMember data;
 
 
 public Node (Member member) {
	 next = head;
	 head = this;
	 data = member;
 }
}
