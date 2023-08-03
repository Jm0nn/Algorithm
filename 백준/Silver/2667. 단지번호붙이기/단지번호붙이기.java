import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 지도 내 서로 연결된 집 끼리 같은 단지 번호를 붙이는 문제
public class Main {

	// 상하좌우 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int N; // 지도의 크기
	static int group = 1; // 총 단지수
	static int[][] map; // 지도 정보
	static boolean[][] visit; // 방문 정보

	// 좌표를 정의하는 클래스
	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';

				// 집이 없다면 방문한 것으로 간주
				if (map[i][j] == 0)
					visit[i][j] = true;
			}
		}

		// 맵을 돌면서 넓이 우선 탐색 적용
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				bfs(i, j);

		// 각 단지 내 집의 수에 대한 배열 생성
		int[] groupCount = new int[group];

		// 맵을 돌며 단지 번호의 집 카운트 증가
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				groupCount[map[i][j]]++;

		// 집이 없는 경우(단지 번호 0)를 제거한 배열 생성
		int[] ngroupCount = Arrays.copyOfRange(groupCount, 1, group);

		// 각 단지 내 집의 수 오름차순으로 정렬
		Arrays.sort(ngroupCount);

		// 총 단지 수 출력
		System.out.println(group - 1);

		// 각 단지 내 집의 수 출력
		for (int i = 0; i < ngroupCount.length; i++)
			if (ngroupCount[i] != 0)
				System.out.println(ngroupCount[i]);

		br.close();
	}

	// 넓이 우선 탐색
	static void bfs(int i, int j) {
		// 방문한 적 있는 칸이라면 탐색 하지 않고 종료
		if (visit[i][j])
			return;

		// 현재 위치 정보로 queue 생성
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(i, j));
		visit[i][j] = true;
		map[i][j] = group;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			for (int d = 0; d < 4; d++) {
				// 새로운 좌표 설정
				int nx = now.x + deltas[d][0];
				int ny = now.y + deltas[d][1];

				// 새로운 좌표가 맵을 벗어나거나 방문한 적 있다면 다음 탐색
				if (0 > nx || nx >= N || 0 > ny || ny >= N || visit[nx][ny])
					continue;

				// 방문 확인, 단지 번호 지정
				visit[nx][ny] = true;
				map[nx][ny] = group;
				queue.add(new Pos(nx, ny));

			}
		}

		// 한 단지 내 집을 다 돌았다면 단지 수 증가
		group++;
	}
}