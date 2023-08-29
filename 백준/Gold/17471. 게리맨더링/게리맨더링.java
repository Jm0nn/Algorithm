import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 선거구를 두 개로 나눠서 두 선거구의 인구 차이의 최솟값을 구하는 문제
public class Main {

	static int n, min; // 구역의 수, 인구 차이의 최솟값
	static int[] population; // 구역의 인구 수
	static boolean[] choiceA; // A 선거구에 선택된 구역
	static List<Integer>[] list; // 구역 인접 리스트

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		population = new int[n + 1];
		choiceA = new boolean[n + 1];
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

		group(1, 0);

		System.out.println(min != Integer.MAX_VALUE ? min : -1);
	}

	// 부분집합을 구하는 알고리즘을 통해 A 선거구의 구역을 정함
	// A 선거구에 들어가지 않은 구역들은 B 선거구
	static void group(int cnt, int count) {
		if (cnt == n + 1) {
			if (count == 0 || count == n)
				return;

			int a = -1;
			int b = -1;

			for (int i = 1; i <= n; i++) {
				if (choiceA[i] && a == -1)
					a = i;
				else if (!choiceA[i] && b == -1)
					b = i;
				else if (a != -1 && b != -1)
					break;
			}

			// 정해진 선거구 내의 구역들이 서로 인접해 있는지 확인
			if (bfs(a, b)) {
				int sumA = 0;
				int sumB = 0;

				for (int i = 1; i <= n; i++) {
					if (choiceA[i])
						sumA += population[i];
					else
						sumB += population[i];
				}

				int diff = Math.abs(sumA - sumB);

				if (min > diff)
					min = diff;
			}

			return;
		}

		choiceA[cnt] = true;
		group(cnt + 1, count + 1);
		choiceA[cnt] = false;
		group(cnt + 1, count);

	}

	// bfs를 통해 선거구 내 구역들의 인접 정보 확인
	static boolean bfs(int a, int b) {
		// A 선거구의 첫 번째 구역과 인접한 구역
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(a);
		visited[a] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i : list[cur]) {
				if (visited[i] || !choiceA[i])
					continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		// A 선거구의 구역들이 서로 인접해 있지 않다면 false
		for (int i = 1; i <= n; i++) {
			if (choiceA[i] != visited[i])
				return false;
		}

		// B 선거구의 첫 번째 구역과 인접한 구역
		visited = new boolean[n + 1];
		queue.offer(b);
		visited[b] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i : list[cur]) {
				if (visited[i] || choiceA[i])
					continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		// B 선거구의 구역들이 서로 인접해 있지 않다면 false
		for (int i = 1; i <= n; i++) {
			if (choiceA[i] == visited[i])
				return false;
		}

		return true; // 두 선거구 내 구역들이 서로 인접해 있으므로 true
	}

}