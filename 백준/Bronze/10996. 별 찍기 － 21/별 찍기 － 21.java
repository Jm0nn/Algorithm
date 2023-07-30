import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 별 모양 층수

		for (int i = 0; i < N * 2; i++) {
			for (int j = 0; j < N; j++) {
				if (i % 2 == 0) { // 짝수 행일 때
					if (j % 2 == 0) // 짝수 열일 때
						sb.append('*'); // 별 출력
					else // 홀수 열일 때
						sb.append(' '); // 빈 공간 출력
				} else { // 홀수 행일 때
					if (j % 2 == 0) // 짝수 열일 때
						sb.append(' '); // 빈 공간 출력
					else // 홀수 열일 때
						sb.append('*'); // 별 출력
				}
			}

			sb.append('\n');
		}

		System.out.println(sb);

		br.close();
	}
}