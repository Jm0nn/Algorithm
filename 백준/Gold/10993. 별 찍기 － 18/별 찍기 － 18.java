import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] sizeX, sizeY; // N에 따라 만들어질 배열의 크기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		if (N == 1) { // N이 1일 때 별 하나만 출력
			sb.append('*');
		} else { // N이 2 이상일 때

			// 배열 크기 생성
			sizeX = new int[N];
			sizeY = new int[N];

			sizeX[0] = 1;
			sizeY[0] = 1;

			for (int i = 1; i < N; i++) { // 만들어질 배열의 크기
				sizeX[i] = sizeX[i - 1] * 2 + 1;
				sizeY[i] = sizeY[i - 1] * 2 + 3;
			}

			char[][] map = star(N); // 별을 찍기 위한 재귀 실행

			// 출력
			if (N % 2 == 1) { // N이 홀수일 때
				int blank = sizeY[N - 1] / 2; // 뒷쪽 출력 제거
				for (int i = 0; i < sizeX[N - 1]; i++) {
					for (int j = 0; j < sizeY[N - 1]; j++) {
						if (sizeY[N - 1] - j <= blank)
							break;
						sb.append(map[i][j]);
					}
					sb.append('\n');
					blank--;
				}
			} else { // N이 짝수일 때
				int blank = 0; // 뒷쪽 출력 제거
				for (int i = 0; i < sizeX[N - 1]; i++) {
					for (int j = 0; j < sizeY[N - 1]; j++) {
						if (sizeY[N - 1] - j <= blank)
							break;
						sb.append(map[i][j]);
					}
					sb.append('\n');
					blank++;
				}
			}
		}

		System.out.println(sb);

		br.close();
	}

	// 별 찍는 메서드
	static char[][] star(int n) {
		// n에서 만들어질 배열의 크기
		int x = sizeX[n - 1];
		int y = sizeY[n - 1];

		char[][] small = new char[x][y]; // 리턴할 배열

		for (int i = 0; i < x; i++) // 배열 전체를 빈 공간으로 채움 (char 기본값 '\0')
			Arrays.fill(small[i], ' ');

		if (n == 1) { // 기본 배열
			small[0][0] = '*';
		} else {
			int middle = y / 2; // 열의 중간지점

			// 가장 바깥쪽 삼각형 생성
			// n이 홀수일 때
			if (n % 2 == 1) {
				small[0][middle] = '*';

				int blank = 1;
				for (int i = 1; i < x; i++) {
					for (int j = 0; j < y; j++) {
						if (i == x - 1)
							small[i][j] = '*';
						else if (j == middle - blank || j == middle + blank)
							small[i][j] = '*';
					}

					blank++;
				}

				// n이 짝수일 때
			} else {
				small[x - 1][middle] = '*';

				int blank = 1;
				for (int i = x - 2; i >= 0; i--) {
					for (int j = 0; j < y; j++) {
						if (i == 0)
							small[i][j] = '*';
						else if (j == middle - blank || j == middle + blank)
							small[i][j] = '*';
					}

					blank++;
				}
			}

			// n - 1의 삼각형 생성
			char[][] tmp = star(n - 1);

			// n - 1의 배열 크기
			int tmpX = sizeX[n - 2];
			int tmpY = sizeY[n - 2];

			// 바깥쪽 삼각형 안에 n - 1 삼각형을 넣음
			// n이 홀수일 때
			if (n % 2 == 1) {
				for (int i = tmpX; i < x - 1; i++) {
					for (int j = (y - tmpY) / 2; j < (y - tmpY) / 2 + tmpY; j++) {
						small[i][j] = tmp[i - tmpX][j - ((y - tmpY) / 2)];
					}
				}

				// n이 짝수일 때
			} else {
				for (int i = 1; i <= tmpX; i++) {
					for (int j = (y - tmpY) / 2; j < (y - tmpY) / 2 + tmpY; j++) {
						small[i][j] = tmp[i - 1][j - ((y - tmpY) / 2)];
					}
				}
			}
		}

		return small; // 만들어진 별 모양 배열 리턴
	}
}