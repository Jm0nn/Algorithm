import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static class Node {
		int num;
		Node left;
		Node right;

		Node(int num) {
			this.num = num;
		}
	}

	static class Tree {
		Node root;

		void add(int n) {
			if (root == null)
				root = new Node(n);
			else
				add(root, n);
		}

		void add(Node node, int n) {
			int cur = node.num;

			if (cur > n) {
				if (node.left == null)
					node.left = new Node(n);
				else
					add(node.left, n);
			} else {
				if (node.right == null)
					node.right = new Node(n);
				else
					add(node.right, n);
			}
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Tree tree = new Tree();

		String input;
		while ((input = br.readLine()) != null)
			tree.add(Integer.parseInt(input));

		dfs(tree.root);

		System.out.println(sb);
	}

	static void dfs(Node node) {
		if (node == null)
			return;

		dfs(node.left);
		dfs(node.right);
		sb.append(node.num).append('\n');
	}

}