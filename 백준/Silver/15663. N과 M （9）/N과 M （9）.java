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
	static int[] numArr; // 자연수 배열, 중복 불가
	static int[] printArr; // 출력할 배열
	static boolean[] visitNum; // 자연수 방문 확인 배열
	static List<String> visitArr = new ArrayList<>(); // 중복 수열을 확인하기 위한 리스트

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numArr = new int[N];
		printArr = new int[M];
		visitNum = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			numArr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(numArr); // 자연수 배열 정렬

		dfs(0); // 모든 수열을 확인하기 위한 깊이 우선 탐색

		System.out.println(sb);

		br.close();
	}

	// 깊이 우선 탐색
	static void dfs(int depth) {
		if (depth == M) { // 출력할 배열의 크기에 도달했다면
			StringBuilder s = new StringBuilder();

			// 배열 문자열로 생성
			for (int i : printArr)
				s.append(i).append(' ');
			s.append('\n');

			String arr = s.toString();

			// 기존에 출력한 배열 중 새로운 배열과 같은 배열이 있는지 확인
			if (!visitArr.contains(arr)) {
				visitArr.add(arr); // 새로운 배열 추가
				sb.append(arr); // 출력
			}

			return;
		}

		for (int i = 0; i < N; i++) { // 0부터 N-1까지 반복문 사용
			if (visitNum[i]) // 방문했다면 탐색 넘어감
				continue;

			visitNum[i] = true; // 방문 확인
			printArr[depth] = numArr[i]; // 배열에 해당 자연수 추가
			dfs(depth + 1); // 다음 배열 탐색
			visitNum[i] = false; // 방문 취소
		}
	}
}