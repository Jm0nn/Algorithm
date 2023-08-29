import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] rgb = new int[n][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				rgb[i][j] = Integer.parseInt(st.nextToken());

			if (i == 0)
				continue;

			rgb[i][0] += Math.min(rgb[i - 1][1], rgb[i - 1][2]);
			rgb[i][1] += Math.min(rgb[i - 1][0], rgb[i - 1][2]);
			rgb[i][2] += Math.min(rgb[i - 1][0], rgb[i - 1][1]);
		}

		System.out.println(Math.min(rgb[n - 1][0], Math.min(rgb[n - 1][1], rgb[n - 1][2])));
	}
}