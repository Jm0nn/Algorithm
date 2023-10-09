import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int num;
		Node next;

		Node(int num) {
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node[] game = new Node[101];
		for (int i = 100; i > 0; --i)
			game[i] = new Node(i);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int sum = n + m;
		while (sum-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			game[a].next = game[b];
		}

		boolean[] visit = new boolean[101];
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] { 1, 0 });
		visit[1] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int num = cur[0];
			int cnt = cur[1];

			if (num == 100) {
				System.out.println(cnt);
				break;
			}

			for (int i = 1; i <= 6; ++i) {
				int next = num + i;

				if (next > 100)
					continue;

				Node tmp = game[next];
				while (tmp.next != null)
					tmp = tmp.next;

				if (visit[tmp.num])
					continue;

				queue.offer(new int[] { tmp.num, cnt + 1 });
				visit[tmp.num] = true;
			}
		}
	}

}