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
		Node n = head;

		while (n != null && n.getData().getKey() != key) {
			n = n.next;
		}
		if (n == null) {
			return null;
		}
		return n.getData();
	}

	public void insert(IMember memb) {
		new Node(memb);

	}

	public int getSizeOfSport(KindOfSport sport) {
		Node n = head;
		int counter = 0;
		while (n != null) {
			if (n.getData().getKindOfSport().equals(sport)) {
				counter++;
			}
			n = n.getNext();
		}
		return counter;
	}

	public IMember search(String name, String firstName) {
		Node n = head;

		while (n != null && !(n.getData().getName().equals(name) && n.getData().getFirstName().equals(firstName))) {
			n = n.next;
		}
		if (n == null) {
			return null;
		}
		return n.getData();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
