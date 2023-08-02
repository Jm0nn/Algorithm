import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// N개의 자연수 중에서 M개를 고른 수열을 구하는 문제
public class Main {

	static int N, M; // 출력할 자연수의 갯수, 배열의 크기
	static int[] num = new int[10001]; // 자연수가 입력된 개수를 저장하는 배열
	static int[] numArr; // 자연수 배열
	static int[] printArr; // 출력할 배열

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		printArr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[Integer.parseInt(st.nextToken())]++;

		dfs(0); // 모든 수열을 확인하기 위한 깊이 우선 탐색

		System.out.println(sb);

		br.close();
	}

	// 깊이 우선 탐색
	static void dfs(int depth) {
		if (depth == M) { // 출력할 배열의 크기에 도달했다면
			for (int i : printArr) // 배열 출력
				sb.append(i).append(' ');
			sb.append('\n');
			return;
		}

		for (int i = 1; i < 10001; i++) { // 1부터 10000까지 수 중 입력받은 수 탐색
			if (num[i] < 1) // 입력받지 않은 수라면 탐색 넘어감
				continue;

			num[i]--; // 방문 확인
			printArr[depth] = i; // 배열에 해당 자연수 추가
			dfs(depth + 1); // 다음 배열 탐색
			num[i]++; // 방문 취소
		}
	}
}