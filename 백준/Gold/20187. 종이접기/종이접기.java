import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int k, h, count; // 종이를 접는 횟수, 최초 뚫린 구멍의 위치
	static char[] origami; // 종이 접기 배열

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력 버퍼
	static StringBuilder sb = new StringBuilder(); // 출력할 StringBuilder
	static StringTokenizer st; // 한줄에 공백으로 나눠진 입력 요소를 분리할 StringTokenizer

	public static void main(String[] args) throws Exception {

		k = Integer.parseInt(br.readLine());
		count = k * 2; // 종이 접기 횟수
		int size = (int) Math.pow(2, k); // 종이 크기
		origami = new char[count]; // 종이 접는 방법 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < origami.length; i++)
			origami[i] = st.nextToken().charAt(0);

		h = Integer.parseInt(br.readLine()); // 처음 뚫린 구멍 위치

		int[][] result = ori(0, origami[0]); // 결과 배열

		// 결과 배열 출력
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				sb.append(result[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);
	}

	// 종이접기, count: 펼쳐진 횟수, dir: 접힌 방향
	static int[][] ori(int cnt, char dir) {

		int[][] tmp; // 이전단계 배열
		if (cnt == count - 1) { // 최소 크기일 경우
			tmp = new int[1][1];
			tmp[0][0] = h;
		} else { // 최소 크기가 아닐 경우
			tmp = ori(cnt + 1, origami[cnt + 1]); // 재귀로 이전 단계 넘겨받음
		}
		int rSize = tmp.length; // 넘겨받은 종이의 행 크기
		int cSize = tmp[0].length; // 넘겨받은 종이의 열 크기

		int[][] result; // 현재 단계의 종이
		// 접힌 방향에 따라 크기 달라짐
		if (dir == 'U' || dir == 'D')
			result = new int[rSize * 2][cSize];
		else
			result = new int[rSize][cSize * 2];

		switch (dir) {
		case 'U': // 위로 접혔을 때
			// 종이의 윗쪽 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i][j] = tmp[i][j];
				}
			}
			// 종이의 아랫쪽 거울상으로 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i + rSize][j] = (tmp[rSize - i - 1][j] + 2) % 4;
				}
			}
			break;

		case 'D': // 아래로 접혔을 때
			// 종이의 아랫쪽 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i + rSize][j] = tmp[i][j];
				}
			}
			// 종이의 윗쪽 거울상으로 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i][j] = (tmp[rSize - i - 1][j] + 2) % 4;
				}
			}
			break;

		case 'L': // 왼쪽으로 접혔을 때
			// 종이의 왼쪽 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i][j] = tmp[i][j];
				}
			}
			// 종이의 오른쪽 거울상으로 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					if (tmp[i][cSize - j - 1] == 0 || tmp[i][cSize - j - 1] == 2)
						result[i][j + cSize] = tmp[i][cSize - j - 1] + 1;
					else
						result[i][j + cSize] = tmp[i][cSize - j - 1] - 1;
				}
			}
			break;

		case 'R': // 오른쪽으로 접혔을 때
			// 종이의 오른쪽 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					result[i][j + cSize] = tmp[i][j];
				}
			}
			// 종이의 왼쪽 거울상으로 복사
			for (int i = 0; i < rSize; i++) {
				for (int j = 0; j < cSize; j++) {
					if (tmp[i][cSize - j - 1] == 0 || tmp[i][cSize - j - 1] == 2)
						result[i][j] = tmp[i][cSize - j - 1] + 1;
					else
						result[i][j] = tmp[i][cSize - j - 1] - 1;
				}
			}
			break;
		}

		return result;
	}

}