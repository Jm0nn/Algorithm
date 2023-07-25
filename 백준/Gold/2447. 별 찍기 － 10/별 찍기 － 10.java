import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static char[][] box;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		box = new char[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(box[i], '*');
		star(0, 0, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				sb.append(box[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	private static void star(int x, int y, int size) {
		if (size == 1) {
			return;
		} else {
			int smallsize = size / 3;
			int tmpx1 = x + smallsize;
			int tmpx2 = tmpx1 + smallsize;
			int tmpy1 = y + smallsize;
			int tmpy2 = tmpy1 + smallsize;
			for (int i = tmpx1; i < tmpx2; i++)
				for (int j = tmpy1; j < tmpy2; j++)
					box[i][j] = ' ';
			star(x, y, smallsize);
			star(tmpx1, y, smallsize);
			star(tmpx2, y, smallsize);
			star(x, tmpy1, smallsize);
			star(tmpx2, tmpy1, smallsize);
			star(x, tmpy2, smallsize);
			star(tmpx1, tmpy2, smallsize);
			star(tmpx2, tmpy2, smallsize);
		}
	}

}