import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 도미노를 손으로 최소 몇번 넘어뜨려야 모든 도미노를 넘어뜨릴 수 있는지 구하는 문제
public class Main {

	static int n, m; // 정점의 개수, 간선의 개수
	static int size, num; // scc 그룹의 개수, 정점 번호
	static List<Integer>[] graph, result; // 간선 리스트, scc 그룹 리스트
	static int[] order, sccArr; // 할당된 정점 번호, scc 그룹 번호
	static boolean[] finish; // scc가 확정된 정점 판별
	static Stack<Integer> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			graph = new ArrayList[n + 1];
			result = new ArrayList[n + 1];
			order = new int[n + 1];
			sccArr = new int[n + 1];
			finish = new boolean[n + 1];
			stack = new Stack<>();
			size = 0;
			num = 0;

			for (int i = 0; i <= n; i++) {
				graph[i] = new ArrayList<>();
				result[i] = new ArrayList<>();
			}

			// 단방향 인접리스트 구현 (방향, 역방향 그래프)
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph[from].add(to);
			}

			// 방향 그래프에 대해 dfs 수행
			for (int i = 1; i <= n; i++) {
				if (order[i] == 0)
					scc(i);
			}

			boolean[] inDegree = new boolean[size]; // scc그룹의 inDegree

			for (int i = 1; i <= n; i++) {
				int s = graph[i].size();

				for (int j = 0; j < s; j++) {
					int idx = graph[i].get(j);

					// 서로 연결되어있는 scc 그룹 inDegree true
					if (sccArr[idx] != sccArr[i])
						inDegree[sccArr[idx]] = true;
				}
			}

			int cnt = 0;
			for (int i = 0; i < size; i++) {
				// 연결된 scc 그룹들의 루트 그룹 개수 카운트
				if (!inDegree[i])
					cnt++;
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}

	// 각 정점에 대해 dfs 수행
	static int scc(int idx) {
		order[idx] = ++num; // 노드마다 고유한 scc 번호 할당
		stack.add(idx); // 스택에 자기 자신 삽입
		int root = order[idx]; // 자신의 dfs, 자식들의 결과나 order중 가장 작은 번호를 result에 저장

		for (int i = 0; i < graph[idx].size(); i++) {
			int tmp = graph[idx].get(i);
			// 아직 방문하지 않은 이웃
			if (order[tmp] == 0)
				root = Math.min(root, scc(tmp));
			// 방문은 했으나 아직 scc로 추출되지 않은 이웃
			else if (!finish[tmp])
				root = Math.min(root, order[tmp]);
		}

		// 부모노드가 자기 자신일 경우
		// 자신과 자신의 자손들이 도달 가능한 제일 높은 정점이 자신일 경우 scc 추출
		if (root == order[idx]) {
			// 스택에서 scc 그룹 추출
			while (true) {
				int top = stack.pop();
				result[size].add(top);
				sccArr[top] = size;
				finish[top] = true;
				if (top == idx)
					break;
			}

			size++; // scc 그룹 개수 증가
		}

		return root; // 자신의 부모값을 반환
	}
}