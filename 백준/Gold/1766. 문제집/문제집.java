import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static Node[] adjList;
	static int[] inDegree;

	static class Node {
		int vertex;
		Node link;

		Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adjList = new Node[n + 1];
		inDegree = new int[n + 1];

		int from, to;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}

		List<Integer> list = topologySort();

		StringBuilder sb = new StringBuilder();
		for (Integer vertex : list)
			sb.append(vertex).append(' ');

		System.out.println(sb);
	}

	static List<Integer> topologySort() {
		List<Integer> orderList = new ArrayList<>();

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= n; i++)
			if (inDegree[i] == 0)
				queue.offer(i);

		while (!queue.isEmpty()) {
			Integer curNode = queue.poll();
			orderList.add(curNode);

			for (Node temp = adjList[curNode]; temp != null; temp = temp.link)
				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);
		}

		return orderList;
	}
}