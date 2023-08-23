import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, min;
	static int[] population;
	static boolean[] visit;
	static List<Integer>[] list;
	static List<Integer> groupA, groupB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		population = new int[n + 1];
		visit = new boolean[n + 1];
		list = new ArrayList[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());

			while (m-- > 0) {
				int k = Integer.parseInt(st.nextToken());
				list[i].add(k);
				list[k].add(i);
			}
		}

		min = Integer.MAX_VALUE;

		group(1);

		if (min == Integer.MAX_VALUE)
			min = -1;

		System.out.println(min);
	}

	static void group(int cnt) {
		if (cnt == n + 1) {
			groupA = new ArrayList<>();
			groupB = new ArrayList<>();

			for (int i = 1; i <= n; i++) {
				if (visit[i])
					groupA.add(i);
				else
					groupB.add(i);
			}

			if (groupA.size() == 0 || groupB.size() == 0)
				return;

			if (bfs()) {
				int sumA = 0;
				int sumB = 0;

				for (int i : groupA)
					sumA += population[i];

				for (int i : groupB)
					sumB += population[i];

				int diff = Math.abs(sumA - sumB);

				if (min > diff)
					min = diff;
			}

			return;
		}

		visit[cnt] = true;
		group(cnt + 1);
		visit[cnt] = false;
		group(cnt + 1);

	}

	static boolean bfs() {
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(groupA.get(0));
		visited[groupA.get(0)] = true;

		while (!queue.isEmpty()) {
			Integer cur = queue.poll();

			for (int i : list[cur]) {
				if (visited[i] || !visit[i])
					continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		for (int i = 1; i <= n; i++) {
			if (visit[i] != visited[i])
				return false;
		}

		visited = new boolean[n + 1];
		queue.offer(groupB.get(0));
		visited[groupB.get(0)] = true;

		while (!queue.isEmpty()) {
			Integer cur = queue.poll();

			for (int i : list[cur]) {
				if (visited[i] || visit[i])
					continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		for (int i = 1; i <= n; i++) {
			if (!visit[i] != visited[i])
				return false;
		}

		return true;
	}

}