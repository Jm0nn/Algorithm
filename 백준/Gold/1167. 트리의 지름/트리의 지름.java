import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int num;
		int weight;
		Node next;

		Node(int num, int weight, Node next) {
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
	}

	static int n, max, maxNum;
	static Node[] tree;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		tree = new Node[n + 1];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c;
			while ((c = Integer.parseInt(st.nextToken())) > 0) {
				int w = Integer.parseInt(st.nextToken());
				tree[p] = new Node(c, w, tree[p]);
			}
		}

		visit = new boolean[n + 1];
		visit[1] = true;
		dfs(1, 0);

		visit = new boolean[n + 1];
		visit[maxNum] = true;
		dfs(maxNum, 0);

		System.out.println(max);
	}

	static void dfs(int num, int weight) {
		if (max < weight) {
			max = weight;
			maxNum = num;
		}

		for (Node node = tree[num]; node != null; node = node.next) {
			if (visit[node.num])
				continue;

			visit[node.num] = true;
			dfs(node.num, weight + node.weight);
		}
	}

}