import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		Map<String, Node> child;

		Node() {
			child = new HashMap<>();
		}

		void add(List<String> list) {
			Node node = this;

			for (String s : list) {
				node.child.putIfAbsent(s, new Node());
				node = node.child.get(s);
			}
		}

		void print(Node node, int depth) {
			if (node.child.size() == 0)
				return;

			List<String> list = new ArrayList<>(node.child.keySet());
			Collections.sort(list);

			for (String s : list) {
				for (int i = 0; i < depth; ++i)
					sb.append("--");
				sb.append(s).append('\n');
				print(node.child.get(s), depth + 1);
			}
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Node trie = new Node();

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			List<String> list = new ArrayList<>();

			while (k-- > 0)
				list.add(st.nextToken());

			trie.add(list);
		}

		trie.print(trie, 0);

		System.out.println(sb);
	}

}