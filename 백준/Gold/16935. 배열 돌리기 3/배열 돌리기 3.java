import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, r, cnt;
	static int[][] arr;
	static int[] cal;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		cal = new int[r];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++)
			cal[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < r; i++) {
			switch (cal[i]) {
			case 1:
				cal1();
				break;

			case 2:
				cal2();
				break;

			case 3:
				arr = cal3();
				break;

			case 4:
				arr = cal4();
				break;

			case 5:
				cal5();
				break;

			case 6:
				cal6();
				break;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void cal1() {
		for (int i = 0; i < arr.length / 2; i++) {
			int[] tmp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = tmp;
		}
	}

	static void cal2() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length / 2; j++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[i][arr[0].length - j - 1];
				arr[i][arr[0].length - j - 1] = tmp;
			}
		}
	}

	static int[][] cal3() {
		int[][] tmp = new int[arr[0].length][arr.length];

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				tmp[j][arr.length - i - 1] = arr[i][j];

		return tmp;
	}

	static int[][] cal4() {
		int[][] tmp = new int[arr[0].length][arr.length];

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				tmp[arr[0].length - j - 1][i] = arr[i][j];

		return tmp;
	}

	static void cal5() {
		int[][] tmp = new int[arr.length / 2][arr[0].length / 2];

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[i][j] = arr[i][j];
				arr[i][j] = arr[i + tmp.length][j];
				arr[i + tmp.length][j] = arr[i + tmp.length][j + tmp[0].length];
				arr[i + tmp.length][j + tmp[0].length] = arr[i][j + tmp[0].length];
				arr[i][j + tmp[0].length] = tmp[i][j];
			}
		}
	}

	static void cal6() {
		int[][] tmp = new int[arr.length / 2][arr[0].length / 2];

		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[i][j] = arr[i][j];
				arr[i][j] = arr[i][j + tmp[0].length];
				arr[i][j + tmp[0].length] = arr[i + tmp.length][j + tmp[0].length];
				arr[i + tmp.length][j + tmp[0].length] = arr[i + tmp.length][j];
				arr[i + tmp.length][j] = tmp[i][j];
			}
		}
	}

}