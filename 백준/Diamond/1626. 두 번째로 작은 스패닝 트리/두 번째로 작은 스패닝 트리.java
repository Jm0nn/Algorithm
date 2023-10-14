import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 무방향 그래프에서 최소 스패닝 트리보다는 크면서 가장 작은 스패닝 트리를 구하는 문제
public class Main {

	// Edge 클래스
	static class Edge implements Comparable<Edge> {
		int start, end;
		int cost;
		boolean mst;
		Edge next;

		Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		Edge(int end, int cost, Edge next) {
			this.end = end;
			this.cost = cost;
			this.next = next;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	// Kruskal
	static int V, E;
	static int[] parent;
	static Edge[] edgeList;

	// LCA
	static Edge[] adjList;
	static int[] depth;
	static int[][] LCAParent;
	static int[][] maxDist;
	static int[][] secondDist;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		adjList = new Edge[V + 1];
		for (int i = 1; i <= V; ++i)
			parent[i] = i;

		edgeList = new Edge[E];

		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(start, end, cost);
		}

		System.out.println(solution());
	}

	static int solution() {
		// 간선의 개수가 두 번째로 작은 스패닝 트리를 만들 수 없으면 -1 출력
		if (E < V)
			return -1;

		// Kruskal 알고리즘으로 MST 생성
		int cnt = 0;
		int totalCost = 0;
		Arrays.sort(edgeList);

		for (int i = 0; i < E; ++i) {
			if (cnt == V - 1)
				break;

			Edge cur = edgeList[i];
			int start = cur.start;
			int end = cur.end;
			int cost = cur.cost;

			// 서로 다른 그룹이어서 union이 된다면
			if (union(start, end)) {
				totalCost += cost;
				cur.mst = true; // MST 간선으로 기록
				++cnt;

				// MST를 이용해 LCA를 구해야 하므로 새로운 인접리스트에 추가
				adjList[start] = new Edge(end, cost, adjList[start]);
				adjList[end] = new Edge(start, cost, adjList[end]);
			}
		}

		// MST가 만들어지지 않았으면 -1 출력
		if (cnt != V - 1)
			return -1;

		// LCA
		K = 1;
		for (int i = 1; i <= V; i *= 2)
			++K;

		depth = new int[V + 1];
		LCAParent = new int[K + 1][V + 1];
		maxDist = new int[K + 1][V + 1];
		secondDist = new int[K + 1][V + 1];

		dfs(1, 1); // depth 확인

		// parent 채우기
		for (int i = 1; i <= K; ++i) {
			for (int j = 1; j <= V; ++j) {
				int parentNo = LCAParent[i - 1][j];

				// MST가 만들어진 번호로 LCA 구함
				if (parentNo > 0 && LCAParent[i - 1][parentNo] > 0) {
					// j가 조상으로 갈 때
					// 첫 번째, 두 번째 max
					int max = maxDist[i - 1][j];
					int second = secondDist[i - 1][j];

					// 부모의 첫 번째, 두 번째 max
					int parentMax = maxDist[i - 1][parentNo];
					int parentSecond = secondDist[i - 1][parentNo];

					// max가 더 크면 max로 갱신
					if (max > parentMax) {
						maxDist[i][j] = max;
						secondDist[i][j] = Math.max(parentMax, second);
					}

					// parentMax가 더 크면 parentMax로 갱신
					else if (max < parentMax) {
						maxDist[i][j] = parentMax;
						secondDist[i][j] = Math.max(max, parentSecond);
					}

					// 둘이 같으면 second끼리 비교
					else {
						maxDist[i][j] = max;
						secondDist[i][j] = Math.max(second, parentSecond);
					}

					LCAParent[i][j] = LCAParent[i - 1][parentNo];
				}
			}
		}

		// 모든 edge를 보면서 교체할 지 검토
		int res = Integer.MAX_VALUE;

		for (int i = 0; i < E; ++i) {
			Edge cur = edgeList[i];

			// MST에 해당하면 교체하지 않음
			if (cur.mst)
				continue;

			// LCA를 보면서 가능한 교체 값(max) 확인
			int max = lca(cur.start, cur.end, cur.cost);

			if (max == -1)
				continue;

			res = Math.min(res, cur.cost - max);
		}

		if (res == Integer.MAX_VALUE)
			return -1;

		return totalCost + res;
	}

	static int find(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parent[rootA] = rootB;
		return true;
	}

	static void dfs(int no, int cnt) {
		// depth 기록
		depth[no] = cnt;

		// 자식들의 depth 기록
		for (Edge edge = adjList[no]; edge != null; edge = edge.next) {
			int end = edge.end;
			int cost = edge.cost;

			// 미방문 노드
			if (depth[end] == 0) {
				dfs(end, cnt + 1);

				LCAParent[0][end] = no; // 첫 번째 부모 기록

				// maxDist 초기값 설정
				maxDist[0][end] = cost;
				secondDist[0][end] = -1;
			}
		}
	}

	// 최소공통조상
	static int lca(int a, int b, int cost) {
		// depth[a] >= depth[b]가 되도록 조정
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		// 불가능하다고 가정하고 시작
		int res = -1;

		// 더 깊은 a를 2^K 점프하여 depth를 맞추기
		for (int i = K; i >= 0; --i) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				res = compare(res, i, a, cost);

				// depth 전진
				a = LCAParent[i][a];
			}
		}

		// depth를 맞췄는데 같다면 종료
		if (a == b)
			return res;

		// a와 b는 같은 depth이므로 2^K 점프하여 공통부모 바로 아래까지 올리기
		for (int i = K; i >= 0; --i) {
			if (LCAParent[i][a] != LCAParent[i][b]) {
				// a ~ 최소공통조상까지 max가 있는지 확인
				res = compare(res, i, a, cost);

				// b ~ 최소공통조상까지 max가 있는지 확인
				res = compare(res, i, b, cost);

				a = LCAParent[i][a];
				b = LCAParent[i][b];
			}
		}

		// a ~ 바로 윗 조상 사이에 max인지 확인
		res = compare(res, 0, a, cost);

		// b ~ 바로 윗 조상 사이에 max인지 확인
		res = compare(res, 0, b, cost);

		return res;
	}

	static int compare(int res, int i, int no, int cost) {
		// 첫 번째로 큰 값을 현재의 cost로 대체가 가능하면
		if (maxDist[i][no] != cost)
			res = Math.max(res, maxDist[i][no]);

		// 첫 번째로 큰 값은 안되더라도 두 번째로 큰 값이 된다면
		else if (secondDist[i][no] != -1)
			res = Math.max(res, secondDist[i][no]);

		return res;
	}

}