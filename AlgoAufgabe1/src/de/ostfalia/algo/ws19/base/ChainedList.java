package de.ostfalia.algo.ws19.base;

public class ChainedList {
	private Node head;
	private int size = 0;

	private class Node {

		private Node next;
		private IMember data;

		public Node(IMember member) {
			next = head;
			head = this;
			data = member;
			size++;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public IMember getData() {
			return data;
		}

		public void setData(IMember data) {
			this.data = data;
		}

	}

	public ChainedList() {
		head = null;
	}

	public IMember search(long key) {

	}

	public void insert(IMember memb) {
		Node n = new Node(memb);

	}

	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
