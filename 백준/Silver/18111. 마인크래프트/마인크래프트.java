import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		int max = 0;
		int min = 257;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int h = Integer.parseInt(st.nextToken());
				map[i][j] = h;
				if (max < h)
					max = h;
				if (min > h)
					min = h;
			}
		}

		int time = Integer.MAX_VALUE;
		int h = 0;

		for (int i = max; i >= min; i--) {
			int t = 0;
			int block = b;

			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					int amount = map[j][k] - i;

					if (map[j][k] > i) {
						block += amount;
						t += amount * 2;
					} else if (map[j][k] < i) {
						block += amount;
						t -= amount;
					}
				}
			}

			if (block >= 0 && time > t) {
				h = i;
				time = t;
			}
		}

		System.out.println(time + " " + h);
	}

}