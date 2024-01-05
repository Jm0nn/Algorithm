package com.jm0nn.ds.heap;

public class MaxHeap {

	int[] tree;
	int capacity;
	int size;

	MaxHeap(int capacity) {
		this.capacity = capacity;
		tree = new int[capacity + 2];
	}

	private void swap(int i1, int i2) {
		int tmp = tree[i1];
		tree[i1] = tree[i2];
		tree[i2] = tmp;
	}

	public boolean offer(int x) {
		if (size == capacity)
			return false;

		tree[++size] = x;

		int idx = size;

		while (idx >> 1 > 0 && tree[idx >> 1] < tree[idx]) {
			swap(idx >> 1, idx);
			idx >>= 1;
		}

		return true;
	}

	public int poll() {
		if (size == 0)
			return 0;

		int idx = 1;
		int res = tree[idx];
		tree[idx] = tree[size];
		tree[size--] = 0;

		while ((idx <<= 1) <= size) {
			if (idx + 1 <= size)
				idx = tree[idx] > tree[idx + 1] ? idx : idx + 1;

			if (tree[idx >> 1] >= tree[idx])
				break;

			swap(idx >> 1, idx);
		}

		return res;
	}

}
