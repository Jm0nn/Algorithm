import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static class MaxHeap {

		int[] tree;
		int size;

		MaxHeap(int capacity) {
			tree = new int[capacity + 2];
		}

		private void swap(int i1, int i2) {
			int tmp = tree[i1];
			tree[i1] = tree[i2];
			tree[i2] = tmp;
		}

		public void offer(int x) {
			tree[++size] = x;

			int idx = size;

			while (idx >> 1 > 0 && tree[idx >> 1] < tree[idx]) {
				swap(idx >> 1, idx);
				idx >>= 1;
			}
		}

		public int poll() {
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

		public boolean isEmpty() {
			if (size == 0)
				return true;
			return false;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		MaxHeap mh = new MaxHeap(n);
		for (int i = 0; i < n; i++)
			mh.offer(Integer.parseInt(br.readLine()));
		while (!mh.isEmpty())
			sb.append(mh.poll()).append('\n');
		System.out.println(sb);
	}
}