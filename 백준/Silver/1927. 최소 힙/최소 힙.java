import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static class MinHeap {
		int[] tree;
		int size;

		MinHeap(int size) {
			tree = new int[size + 1];
		}

		void swap(int i1, int i2) {
			int tmp = tree[i1];
			tree[i1] = tree[i2];
			tree[i2] = tmp;
		}

		void offer(int x) {
			tree[++size] = x;

			int idx = size;

			while (idx >> 1 > 0 && tree[idx >> 1] > tree[idx]) {
				swap(idx >> 1, idx);
				idx >>= 1;
			}
		}

		int poll() {
			if (size == 0)
				return 0;

			int idx = 1;
			int res = tree[idx];
			tree[idx] = tree[size--];
			idx <<= 1;

			while (idx <= size) {
				idx = tree[idx] < tree[idx + 1] ? idx : idx + 1;

				if (tree[idx >> 1] <= tree[idx])
					break;

				swap(idx >> 1, idx);

				idx <<= 1;
			}

			return res;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		MinHeap mh = new MinHeap(n);

		while (n-- > 0) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0)
				sb.append(mh.poll()).append('\n');
			else
				mh.offer(x);
		}

		System.out.println(sb);
	}

}