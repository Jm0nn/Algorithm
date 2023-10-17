import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int no;
		Node next;

		Node(int no, Node next) {
			this.no = no;
			this.next = next;
		}
	}

	static int n;
	static boolean[] visit;
	static Node[] tree;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		visit = new boolean[n + 1];
		tree = new Node[n + 1];
		dp = new int[n + 1][2];

		for (int i = 1; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			tree[u] = new Node(v, tree[u]);
			tree[v] = new Node(u, tree[v]);
		}

		dfs(1);

		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	static void dfs(int num) {
		visit[num] = true;
		dp[num][0] = 0; // 얼리어답터 아님
		dp[num][1] = 1; // 얼리어답터

		for (Node node = tree[num]; node != null; node = node.next) {
			int next = node.no;

			if (visit[next])
				continue;

			dfs(next);
			dp[num][0] += dp[next][1]; // 내가 얼리어답터가 아니면 자식은 반드시 얼리어답터
			dp[num][1] += Math.min(dp[next][0], dp[next][1]); // 내가 얼리어답터라면 자식이 무엇이든 상관 없음
		}
	}

}