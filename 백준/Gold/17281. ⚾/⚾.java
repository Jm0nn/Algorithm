import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 타자들이 이닝 당 칠 수 있는 결과가 정해졌을 때 얻을 수 있는 점수의 최댓값을 구하는 문제
public class Main {

	static int n, max; // 이닝 수, 점수의 최댓값
	static int[] order; // 타석에 서는 순서
	static int[][] team; // 타자가 이닝 당 칠 수 있는 결과
	static boolean[] visit; // 방문 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		team = new int[n][9];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				team[i][j] = Integer.parseInt(st.nextToken());
		}

		order = new int[9];
		visit = new boolean[9];

		dfs(0);

		System.out.println(max);
	}

	static void dfs(int depth) {
		if (depth == 3) { // 4번타자
			order[depth] = 0; // 1번 선수
			dfs(depth + 1);
		}

		if (depth == 9) { // 순서가 결정됨
			int player = 0; // 현재 타석에 올라간 타자
			int point = 0; // 현재 순서로 얻을 수 있는 점수

			for (int t = 0; t < n; t++) { // n 이닝 동안 진행
				int out = 0; // 아웃 수
				// 루에 선수가 있는지
				boolean roo1 = false;
				boolean roo2 = false;
				boolean roo3 = false;

				while (out < 3) { // 아웃이 3개면 다음 이닝 진행
					switch (team[t][order[player]]) { // 현재 타자가 친 공
					case 0: // 아웃
						out++;
						break;

					case 1: // 안타 (1루타)
						if (roo3) {
							point++;
							roo3 = false;
						}
						if (roo2) {
							roo3 = true;
							roo2 = false;
						}
						if (roo1) {
							roo2 = true;
							roo1 = false;
						}
						roo1 = true;
						break;

					case 2: // 2루타
						if (roo3) {
							point++;
							roo3 = false;
						}
						if (roo2) {
							point++;
							roo2 = false;
						}
						if (roo1) {
							roo3 = true;
							roo1 = false;
						}
						roo2 = true;
						break;

					case 3: // 3루타
						if (roo3) {
							point++;
							roo3 = false;
						}
						if (roo2) {
							point++;
							roo2 = false;
						}
						if (roo1) {
							point++;
							roo1 = false;
						}
						roo3 = true;
						break;

					case 4: // 홈런
						if (roo3) {
							point++;
							roo3 = false;
						}
						if (roo2) {
							point++;
							roo2 = false;
						}
						if (roo1) {
							point++;
							roo1 = false;
						}
						point++;
						break;
					}

					// 다음 타자
					if (++player == 9)
						player = 0;
				}
			}

			// 최댓값 갱신
			if (max < point)
				max = point;
		}

		// 순열 계산 (1번 선수는 무조건 4번 타자)
		for (int i = 1; i < 9; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			order[depth] = i;
			dfs(depth + 1);
			visit[i] = false;
		}
	}

}