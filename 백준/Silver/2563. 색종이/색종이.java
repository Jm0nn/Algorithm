import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] paper = new boolean[100][100];
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j = x; j < x + 10; j++)
				Arrays.fill(paper[j], y, y + 10, true);
		}
		int count = 0;
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				if (paper[i][j])
					count++;
		System.out.println(count);
		br.close();
	}

}