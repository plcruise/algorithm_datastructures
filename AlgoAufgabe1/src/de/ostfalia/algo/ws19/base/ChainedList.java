package de.ostfalia.algo.ws19.base;

public class ChainedList {
	private Node head;
	private int size = 0;
	private int operationCount;

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
		operationCount=0;

		while (n != null && n.getData().getKey() != key) {
			n = n.next;
			operationCount++;
		}
		if (n == null) {
			operationCount++;
			return null;
		}
		return n.getData();
	}

	public void insert(IMember memb) {
		operationCount = 0;
		new Node(memb);
		operationCount++;

	}

	public int getSizeOfSport(KindOfSport sport) {
		Node n = head;
		operationCount=0;

		int counter = 0;
		while (n != null) {
			operationCount++;
			if (n.getData().getKindOfSport().equals(sport)) {
				counter++;
			}
			n = n.getNext();
		}
		return counter;
	}

	public IMember search(String name, String firstName) {
		Node n = head;
		operationCount=0;

		while (n != null && !(n.getData().getName().equals(name) && n.getData().getFirstName().equals(firstName))) {
			n = n.next;
			operationCount++;

		}
		if (n == null) {
			operationCount++;
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

	public IMember[] searchDisciplineMembers(KindOfSport sport, int n) {
		IMember[] members = new Member[n];
		int i = 0;
		operationCount=0;

		Node m = head;

		while (i < n) {
			if (m.getData().getKindOfSport().equals(sport)) {
				members[i] = m.getData();
				i++;
			}
			operationCount++;
			m = m.getNext();
		}

		return members;
	}

	public IMember[] toArray() {
		IMember[] array = new Member[getSize()];
		int counter = 0;
		Node n = head;

		while (n != null) {
			array[counter] = n.getData();
			n = n.next;
		}
		return array;
	}

	public int getOperationsCount() {
		return operationCount;
	}

}
