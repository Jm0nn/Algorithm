import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int RED = 0;
	static final int GREEN = 1;
	static final int BLUE = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] rgb = new int[n + 1][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				rgb[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			rgb[i][RED] += Math.min(rgb[i - 1][GREEN], rgb[i - 1][BLUE]);
			rgb[i][GREEN] += Math.min(rgb[i - 1][RED], rgb[i - 1][BLUE]);
			rgb[i][BLUE] += Math.min(rgb[i - 1][GREEN], rgb[i - 1][RED]);
		}

		System.out.println(Math.min(rgb[n][RED], Math.min(rgb[n][GREEN], rgb[n][BLUE])));
	}
}