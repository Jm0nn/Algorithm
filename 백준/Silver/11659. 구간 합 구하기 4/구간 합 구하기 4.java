import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수 N개 중 i번째 수부터 j번째 수까지 합을 구하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구하는 횟수
		int[] arr = new int[N + 1]; // 수 배열 (1번 인덱스부터 사용)

		st = new StringTokenizer(br.readLine());
		arr[1] = Integer.parseInt(st.nextToken()); // 1번 인덱스
		for (int i = 2; i <= N; i++) // 2번 인덱스부터는 현재 입력받은 수에 이전 인덱스 값을 더해서 넣음
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			sb.append(arr[j] - arr[i - 1]).append('\n'); // j번째 누적합에서 i-1번째 누적합을 빼서 출력
		}

		System.out.println(sb);

		br.close();
	}
}