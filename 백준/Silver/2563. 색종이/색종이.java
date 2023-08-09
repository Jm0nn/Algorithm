import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 색종이가 붙은 영역의 넓이를 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 색종이를 붙일 배열, 붙어있으면 true, 그렇지 않으면 false
		boolean[][] paper = new boolean[100][100];

		int n = Integer.parseInt(br.readLine()); // 종이의 개수

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 색종이의 왼쪽 아래 좌표
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 색종이가 붙은 영역 true로 설정
			for (int j = x; j < x + 10; j++)
				Arrays.fill(paper[j], y, y + 10, true);
		}

		int count = 0; // 색종이가 붙은 영역의 넓이

		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				if (paper[i][j])
					count++;

		System.out.println(count);

		br.close();
	}

}