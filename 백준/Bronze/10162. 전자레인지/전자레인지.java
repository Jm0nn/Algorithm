import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int t; // 요리 시간
	static int a, b, c; // 각 버튼을 누른 횟수

	// BFS를 위한 클래스
	static class Algo {
		int t; // 현재 걸린 시간

		// 현재 각 버튼을 누른 횟수
		int aCnt;
		int bCnt;
		int cCnt;

		public Algo(int t, int aCnt, int bCnt, int cCnt) {
			this.t = t;
			this.aCnt = aCnt;
			this.bCnt = bCnt;
			this.cCnt = cCnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(br.readLine());

		if (bfs()) // BFS를 이용해 탐색 성공 시 각 버튼을 누른 횟수 출력
			sb.append(a).append(' ').append(b).append(' ').append(c);
		else // 탐색 실패 시 -1 출력
			sb.append(-1);

		System.out.println(sb);
	}

	static boolean bfs() {
		Queue<Algo> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[t + 1]; // 방문 배열

		// 초기값 큐에 입력 후 방문 확인
		queue.offer(new Algo(0, 0, 0, 0));
		visited[0] = true;

		while (!queue.isEmpty()) {
			Algo cur = queue.poll();

			// 큐에서 꺼낸 시간이 목표 시간이라면 각 버튼을 누른 횟수 저장 후 true 반환
			if (cur.t == t) {
				a = cur.aCnt;
				b = cur.bCnt;
				c = cur.cCnt;
				return true;
			}

			// 버튼 3개
			for (int i = 0; i < 3; i++) {
				int nt = cur.t; // 현재 시간

				switch (i) {
				case 0: // A버튼 (5분)
					nt += 300; // 시간 300초 추가

					if (nt > t || visited[nt])
						continue; // 시간이 t를 넘어가거나 방문한 시간이라면 넘어감

					// 현재 시간과 버튼 누른 횟수 큐에 넣음
					queue.offer(new Algo(nt, cur.aCnt + 1, cur.bCnt, cur.cCnt));
					visited[nt] = true;
					break;

				case 1: // B버튼 (1분)
					nt += 60; // 시간 60초 추가

					if (nt > t || visited[nt])
						continue; // 시간이 t를 넘어가거나 방문한 시간이라면 넘어감

					// 현재 시간과 버튼 누른 횟수 큐에 넣음
					queue.offer(new Algo(nt, cur.aCnt, cur.bCnt + 1, cur.cCnt));
					visited[nt] = true;
					break;

				case 2: // C버튼 (10초)
					nt += 10; // 시간 10초 추가

					if (nt > t || visited[nt])
						continue; // 시간이 t를 넘어가거나 방문한 시간이라면 넘어감

					// 현재 시간과 버튼 누른 횟수 큐에 넣음
					queue.offer(new Algo(nt, cur.aCnt, cur.bCnt, cur.cCnt + 1));
					visited[nt] = true;
					break;
				}
			}
		}

		return false; // 탐색 실패 시 false 반환
	}
}