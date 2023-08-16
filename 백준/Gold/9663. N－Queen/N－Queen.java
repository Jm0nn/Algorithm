import java.io.BufferedReader;
import java.io.InputStreamReader;

// NxN 체스판에서 N개의 Queen을 서로 공격할 수 없게 놓는 방법의 수를 구하는 문제
public class Main {

	static int n, cnt; // Queen의 개수, 경우의 수
	static int[] queen; // Queen의 배치 배열
	static boolean[] visit; // 해당 index의 행에 Queen이 놓이면 true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		queen = new int[n];
		visit = new boolean[n];

		putQueen(0); // 백트래킹으로 Queen의 배치 경우의 수를 계산

		System.out.println(cnt);
	}

	static void putQueen(int depth) {
		if (depth == n) { // Queen을 모두 배치 했다면
			cnt++; // 경우의 수 증가
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visit[i]) // 현재 행에 이미 Queen이 놓여있으면 다음 탐색
				continue;

			queen[depth] = i;
			if (isValid(depth)) { // 현재 위치가 유효한 위치인지 확인
				visit[i] = true;
				putQueen(depth + 1); // 다음 행 배치
				visit[i] = false;
			}
		}

	}

	static boolean isValid(int col) {
		// 첫번째 행부터 현재 행까지 유효성 검사
		for (int i = 0; i < col; i++)
			// 대각선에 Queen이 놓이면 false
			if (col - i == Math.abs(queen[col] - queen[i]))
				return false;

		return true; // 유효하면 true
	}
}