import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 그래프를 SCC(Strongly Connected Component)로 나누는 문제
public class Main {

	static int v, e; // 노드의 개수, 간선의 개수
	static int size, num; // scc 그룹의 개수, scc 그룹 번호
	static List<Integer>[] graph, result; // 간선 리스트, scc 그룹 리스트
	static int[] order; // 할당된 scc 그룹 번호
	static boolean[] finish; // scc가 확정된 정점 판별
	static Stack<Integer> stack;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		graph = new ArrayList[v + 1];
		result = new ArrayList[v + 1];
		order = new int[v + 1];
		finish = new boolean[v + 1];
		stack = new Stack<>();

		for (int i = 1; i <= v; i++) {
			graph[i] = new ArrayList<>();
			result[i] = new ArrayList<>();
		}

		// 단방향 인접리스트 구현 (방향, 역방향 그래프)
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from].add(to);
		}

		// 방향 그래프에 대해 dfs 수행
		for (int i = 1; i <= v; i++) {
			if (order[i] == 0)
				scc(i);
		}

		// scc 그룹 개수
		sb.append(size).append('\n');

		for (int i = 1; i <= v; i++) {
			int s = result[i].size();

			// scc 그룹에 대하여 첫번째 항의 오름차순 출력
			if (s > 0) {
				for (int j = 0; j < s; j++)
					sb.append(result[i].get(j)).append(' ');
				sb.append("-1\n");
			}
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
			List<Integer> tmpArr = new ArrayList<>(); // scc 그룹을 저장할 리스트

			// 스택에서 scc 그룹 추출
			while (true) {
				int top = stack.pop();
				tmpArr.add(top);
				finish[top] = true;
				if (top == idx)
					break;
			}

			Collections.sort(tmpArr); // 리스트 오름차순 정렬
			int min = Collections.min(tmpArr); // 리스트의 최솟값
			result[min] = tmpArr; // 오름차순 출력을 위해 리스트의 최솟값에 scc 그룹 리스트 전달
			size++; // scc 그룹 개수 증가
		}

		return root; // 자신의 부모값을 반환
	}

}