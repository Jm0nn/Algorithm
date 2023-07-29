import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = N * 2 - 1;
		int mid = M / 2; // 모래시계 중간점

		int blankCount = 0; // 빈 공간 카운트
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M - blankCount; j++) {
				if (j < blankCount) // 앞열에서는 빈 공간 출력
					sb.append(' ');
				else // 뒷열에서 별 출력
					sb.append('*');
			}
			sb.append('\n');

			// 한 행 출력이 끝난 후
			if (i < mid) // 모래시계의 윗부분에서는 빈 공간 증가
				blankCount++;
			else // 모래시계의 아랫부분에서는 빈 공간 감소
				blankCount--;
		}
		
		System.out.println(sb);
		
		br.close();
	}
}