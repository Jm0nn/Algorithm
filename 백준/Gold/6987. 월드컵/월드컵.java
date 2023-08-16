import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 월드컵 조별 예선 경기에서 결과가 가능한지 구하는 문제
public class Main {

	static int[][][] score = new int[4][6][3]; // 승무패 배열: 4조, 6팀, 승무패 횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					score[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}

			if (recur(0, i, 0, 1))
				sb.append(1); // 가능한 결과라면 1 출력
			else
				sb.append(0); // 불가능한 결과라면 0 출력
			sb.append(' ');
		}

		System.out.println(sb);
	}

	// cnt: 경기 횟수, group: 그룹 번호, s1, s2: 경기할 두 팀
	static boolean recur(int cnt, int group, int s1, int s2) {
		if (cnt == 15) { // 15회 경기를 했을 때
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (score[group][i][j] != 0)
						return false; // 결과가 입력과 맞지 않으면 false
				}
			}

			return true; // 결과가 입력과 맞으면 true
		}

		for (int i = 0; i < 3; i++) { // 두 팀의 승무패
			// 각 팀의 승무패 결과 -1
			score[group][s1][i] -= 1;
			score[group][s2][2 - i] -= 1;

			// 결과가 음수가 되면 넘어감
			if (score[group][s1][i] >= 0 && score[group][s2][2 - i] >= 0) {
				if (s2 == 5) {
					if (recur(cnt + 1, group, s1 + 1, s1 + 2))
						return true;
				} else {
					if (recur(cnt + 1, group, s1, s2 + 1))
						return true;
				}
			}

			// 각 팀의 승무패 결과 원복
			score[group][s1][i] += 1;
			score[group][s2][2 - i] += 1;
		}

		return false;
	}

}