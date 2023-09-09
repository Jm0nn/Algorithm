import java.io.BufferedReader;
import java.io.InputStreamReader;

// N개의 문자열을 적절히 쪼개서 나열할 때 만들수 있는 문자열 중 사전순으로 가장 앞서는 문자열을 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 문자열의 개수
		MinHeap mh = new MinHeap(n); // 최소힙

		for (int i = 0; i < n; i++)
			mh.offer(br.readLine());

		StringBuilder sb = new StringBuilder();

		// 힙이 빌 때까지 반복
		while (!mh.isEmpty()) {
			String s = mh.poll(); // 힙에서 문자열 꺼냄
			sb.append(s.charAt(0)); // 문자열의 첫 번째 문자 출력
			s = s.substring(1); // 문자열에서 첫 번째 문자 제거

			// 첫 번째 문자 제거 후 남은 문자열 힙에 다시 추가
			if (s.length() > 0)
				mh.offer(s);
		}

		System.out.println(sb);
	}

	// 최소힙
	static class MinHeap {

		String[] tree; // 트리 배열
		int capacity; // 힙의 최대 크기
		int size; // 힙에 들어있는 원소 수

		MinHeap(int capacity) {
			this.capacity = capacity;
			this.tree = new String[capacity + 1];
		}

		int compare(String s1, String s2) {
			int l1 = s1.length();
			int l2 = s2.length();

			int len = l1 < l2 ? l1 : l2;

			for (int i = 0; i < len; i++) {
				if (s1.charAt(i) == s2.charAt(i))
					continue;
				return s1.charAt(i) - s2.charAt(i);
			}

			return l1 == l2 ? 0 : l1 < l2 ? 1 : -1;
		}

		void swap(int i1, int i2) {
			String tmp = tree[i1];
			tree[i1] = tree[i2];
			tree[i2] = tmp;
		}

		void offer(String s) {
			tree[++size] = s;

			int idx = size;

			while (idx >> 1 > 0) {
				if (compare(tree[idx >> 1], tree[idx]) <= 0)
					break;

				swap(idx, (idx >>= 1));
			}
		}

		String poll() {
			int idx = 1;
			String res = tree[idx];
			tree[idx] = tree[size];
			tree[size--] = null;

			while ((idx <<= 1) <= size) {
				if ((idx + 1) <= size)
					idx = compare(tree[idx], tree[idx + 1]) < 0 ? idx : idx + 1;

				if (compare(tree[idx >> 1], tree[idx]) <= 0)
					break;

				swap(idx >> 1, idx);
			}

			return res;
		}

		boolean isEmpty() {
			if (size == 0)
				return true;
			return false;
		}
	}
}