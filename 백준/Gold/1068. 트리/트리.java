import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int parent;
		int[] child;
		int childCnt;

		Node(int size) {
			child = new int[size];
		}

		void addChild(int x) {
			child[childCnt++] = x;
		}
	}

	static int n, root, rmv, cnt;
	static Node[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		tree = new Node[n];

		for (int i = 0; i < n; i++)
			tree[i] = new Node(n);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int p = Integer.parseInt(st.nextToken());
			tree[i].parent = p;

			if (p == -1)
				root = i;
			else
				tree[p].addChild(i);
		}

		rmv = Integer.parseInt(br.readLine());

		if (rmv == root)
			System.out.println(0);
		else {
			int p = tree[rmv].parent;
			int idx = -1;
			for (int i = 0; i < tree[p].childCnt; i++) {
				if (tree[p].child[i] == rmv) {
					idx = i;
					break;
				}
			}

			tree[p].child[idx] = tree[p].child[--tree[p].childCnt];
			dfs(root);
			System.out.println(cnt);
		}
	}

	static void dfs(int num) {
		if (tree[num].childCnt == 0) {
			cnt++;
			return;
		}

		for (int i = 0; i < tree[num].childCnt; i++) {
			dfs(tree[num].child[i]);
		}
	}

}