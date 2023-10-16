import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 수빈이가 동생을 만나는데 걸리는 시간 및 이동 방법을 구하는 문제
public class Main {

	static final int MAX_VALUE = 100_001; // 좌표 한계

	static int N, K; // 수빈이와 동생의 좌표
	static int[] visit = new int[MAX_VALUE]; // 방문 정보
	static int[] parent = new int[MAX_VALUE]; // 직전 방문 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();

		// 출력을 위한 스택
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		int idx = K;

		// 방문한 역순으로 스택에 추가
		while (idx != N) {
			stack.push(parent[idx]);
			idx = parent[idx];
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(visit[K] - 1).append('\n');
		while (!stack.isEmpty())
			sb.append(stack.pop()).append(' ');
		System.out.println(sb);
	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		visit[N] = 1; // 방문 확인 및 시간 정보 입력

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 동생을 만난 경우 걸린 시간 반환
			if (cur == K)
				return;

			// 이동 방법 세가지
			for (int i = 0; i < 3; ++i) {
				int next = cur; // 현재 좌표

				switch (i) {
				case 0: // 한 칸 뒤로 이동
					--next;
					break;
				case 1: // 한 칸 앞으로 이동
					++next;
					break;
				case 2: // x2의 위치로 순간이동
					next *= 2;
					break;
				}

				// 배열을 벗어나지 않고 방문하지 않은 경우
				if ((0 <= next && next < MAX_VALUE) && visit[next] == 0) {
					visit[next] = visit[cur] + 1; // 방문 확인
					parent[next] = cur; // 방문 정보 입력
					queue.add(next);
				}
			}
		}
	}
}