import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열을 구하는 문제
public class Main {
	
	static int N, M;	// 출력할 자연수의 갯수, 배열의 크기
	static int[] numArr;	// 자연수 배열
	static int[] printArr;	// 출력할 배열
	static boolean[] visit;	// 방문 확인
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		printArr = new int[M];
		numArr = new int[N];
		visit = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			numArr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(numArr);	// 자연수 배열 오름차순 정렬
		
		dfs(0);	// 모든 수열을 확인하기 위한 깊이 우선 탐색
		
		System.out.println(sb);
		
		br.close();
	}
	
	// 깊이 우선 탐색
	static void dfs(int depth) {
		if (depth == M) {	// 출력할 배열의 크기에 도달했다면
			for (int i : printArr)	// 배열 출력
				sb.append(i).append(' ');
			sb.append('\n');
			return;	// 메서드 반환
		}
		
		for (int i = 0; i < N; i++) {	// 사전 순으로 출력하기 위해 1부터 N까지 반복문 사용
			if (!visit[i]) {	// 해당 자연수에 방문하지 않았을 경우
				visit[i] = true;	// 방문 확인
				printArr[depth] = numArr[i];	// 배열에 해당 자연수 추가
				dfs(depth + 1);	// 다음 배열 탐색
				visit[i] = false;	// 다음 배열 탐색 종료 후 다시 미방문 처리
			}
		}
	}
}