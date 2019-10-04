package de.ostfalia.algo.ws19.base;

public class Node {
 private static Node last = null;
 private Node next;
 private IMember data;
 
 
 public Node (Member member) {
	 next = last;
	 last = this;
	 data = member;
 }
}
