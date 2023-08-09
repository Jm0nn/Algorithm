import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열 돌리는 문제
public class Main {

	static int n, m, r, tmpNum; // n, m: 배열 크기, r: 돌리는 횟수, tmpNum: swap할 때 임시 공간
	static int[] tmpArr1; // swap할 때 임시 배열
	static int[][] arr, tmpArr2; // arr: 연산을 적용할 배열, tmpArr2: swap할 때 임시 배열

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1: // 상하 반전
				cal1();
				break;

			case 2: // 좌우 반전
				cal2();
				break;

			case 3: // 오른쪽 90도 회전
				cal3();
				break;

			case 4: // 왼쪽 90도 회전
				cal4();
				break;

			case 5: // 사분면 오른쪽으로 회전
				cal5();
				break;

			case 6: // 사분면 왼쪽으로 회전
				cal6();
				break;
			}
		}

		// 최종 배열 출력
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	// 상하 반전
	static void cal1() {
		for (int i = 0; i < arr.length / 2; i++) {
			tmpArr1 = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = tmpArr1;
		}
	}

	// 좌우 반전
	static void cal2() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				tmpNum = arr[i][j];
				arr[i][j] = arr[i][arr[0].length - j - 1];
				arr[i][arr[0].length - j - 1] = tmpNum;
			}
		}
	}

	// 오른쪽으로 90도 회전
	static void cal3() {
		tmpArr2 = new int[arr[0].length][arr.length];

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				tmpArr2[j][arr.length - i - 1] = arr[i][j];

		arr = tmpArr2;
	}

	// 왼쪽으로 90도 회전
	static void cal4() {
		tmpArr2 = new int[arr[0].length][arr.length];

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				tmpArr2[arr[0].length - j - 1][i] = arr[i][j];

		arr = tmpArr2;
	}

	// 사분면 오른쪽으로 회전
	static void cal5() {
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				tmpNum = arr[i][j];
				arr[i][j] = arr[i + arr.length / 2][j];
				arr[i + arr.length / 2][j] = arr[i + arr.length / 2][j + arr[0].length / 2];
				arr[i + arr.length / 2][j + arr[0].length / 2] = arr[i][j + arr[0].length / 2];
				arr[i][j + arr[0].length / 2] = tmpNum;
			}
		}
	}

	// 사분면 왼쪽으로 회전
	static void cal6() {
		for (int i = 0; i < arr.length / 2; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				tmpNum = arr[i][j];
				arr[i][j] = arr[i][j + arr[0].length / 2];
				arr[i][j + arr[0].length / 2] = arr[i + arr.length / 2][j + arr[0].length / 2];
				arr[i + arr.length / 2][j + arr[0].length / 2] = arr[i + arr.length / 2][j];
				arr[i + arr.length / 2][j] = tmpNum;
			}
		}
	}

}