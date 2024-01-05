package com.jm0nn.ds.hash;

public class HashTable {

	private class Node {
		String key;
		String value;
		Node next;

		Node(String key, String data, Node next) {
			this.key = key;
			this.value = data;
			this.next = next;
		}
	}

	Node[] table;
	int capacity; // capacity는 prime number가 좋음 (N ~ 2*N 사이에서 적절한 소수를 찾아 사용)
	int size;

	public HashTable(int capacity) {
		this.capacity = capacity;
		table = new Node[capacity];
	}

	private int hash(String str) {
		int hash = 5381; // Prime number (B형 reference)

		for (int i = 0; i < str.length(); i++)
			hash = ((hash << 5) + hash) + str.charAt(i);

		if (hash < 0)
			hash *= -1;

		return hash;
	}

	private int getIndex(String str) {
		return hash(str) % capacity;
	}

	private Node searchNode(int idx, String key) {
		for (Node cur = table[idx]; cur != null; cur = cur.next) {
			if (cur.key.equals(key))
				return cur;
		}
		return null;
	}

	public boolean add(String key, String data) {
		int idx = getIndex(key);
		Node searched = searchNode(idx, key);

		if (searched == null) {
			table[idx] = new Node(key, data, table[idx]);
			size++;
			return true;
		}

		return true;
	}

	public String get(String key) {
		int idx = getIndex(key);
		Node searched = searchNode(idx, key);

		if (searched == null)
			return null;

		return searched.value;
	}

}
