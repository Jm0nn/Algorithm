import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 별 모양의 높이
		char[][] map = new char[N][N * 2]; // 별 모양 배열
		
		map = star(N); // 별을 찍기 위한 재귀 실행
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N * 2; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static char[][] star(int n) {
		char[][] small = new char[n][n * 2]; // 리턴할 배열
		for (int i = 0; i < n; i++) // 배열 전체를 빈 공간으로 채움 (char 기본값 '\0')
			Arrays.fill(small[i], ' ');
		
		if (n == 3) { // 기본 배열
			small = new char[][] {
					{ ' ', ' ', '*', ' ', ' ', ' ' },
					{ ' ', '*', ' ', '*', ' ', ' ' },
					{ '*', '*', '*', '*', '*', ' ' } };
		} else {
			int m = n / 2; // 높이의 절반
			char[][] tmp = star(m); // 별 모양의 상부를 임시 배열에 저장
			for (int i = 0; i < m; i++) { // 상부를 세 부분으로 나눠 리턴할 배열에 저장
				for (int j = 0; j < n; j++) {
					small[i][m + j] = tmp[i][j];
					small[m + i][j] = tmp[i][j];
					small[m + i][m * 2 + j] = tmp[i][j];
				}
			}
		}
		
		return small; // 만들어진 별 모양 배열 리턴
	}
}