import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static class MaxHeap {
		int[] tree;
		int size;

		MaxHeap(int size) {
			tree = new int[size + 2];
		}

		void offer(int x) {
			tree[++size] = x;

			int idx = size;

			while (idx / 2 > 0 && tree[idx / 2] < tree[idx]) {
				int tmp = tree[idx / 2];
				tree[idx / 2] = tree[idx];
				tree[idx] = tmp;

				idx /= 2;
			}
		}

		int poll() {
			if (size == 0)
				return 0;

			int idx = 1;
			int res = tree[idx];
			tree[idx] = tree[size--];

			while (idx * 2 <= size) {
				int tmpidx = tree[idx * 2] > tree[idx * 2 + 1] ? idx * 2 : idx * 2 + 1;

				if (tree[idx] >= tree[tmpidx])
					break;

				int tmp = tree[idx];
				tree[idx] = tree[tmpidx];
				tree[tmpidx] = tmp;

				idx = tmpidx;
			}

			return res;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		MaxHeap mh = new MaxHeap(n);

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