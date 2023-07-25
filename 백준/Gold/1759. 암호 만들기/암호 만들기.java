import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 주어진 알파벳으로 중복되는 조합 없이 오름차순으로 최소 한개의 모음과 최소 두개의 자음을 포함하여 암호를 만드는 문제
public class Main {

	static int L, C; // 암호의 길이, 문자의 갯수
	static char[] code; // 암호 배열
	static char[] alpha; // 문자 배열
	static boolean[] visit; // 방문 정보

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		code = new char[L + 1];
		alpha = new char[C];
		visit = new boolean[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			alpha[i] = st.nextToken().charAt(0);

		Arrays.sort(alpha); // 문자 배열 오름차순 정렬

		dfs(1); // 중복되지 않는 암호를 만들기 위한 깊이 우선 탐색

		System.out.println(sb);

		br.close();
	}

	// 깊이 우선 탐색
	static void dfs(int depth) {
		if (depth == L + 1) { // 암호의 길이에 도달했다면
			int vowels = 0;	// 암호 내 모음의 갯수
			for (int i = 1; i <= L; i++) {
				if ("aeiou".contains(String.valueOf(code[i])))
					vowels++;	// 암호 문자를 돌며 모음의 갯수 계산
			}

			if (vowels > 0 && L - vowels > 1) {	// 모음이 1개 이상이며 자음이 2개 이상인 경우
				for (int i = 1; i <= L; i++)	// 암호 출력 후 리턴
					sb.append(code[i]);
				sb.append('\n');
			}
			
			return;
		}

		for (int i = 0; i < C; i++) {
			if (code[depth - 1] < alpha[i] && !visit[i]) { // 암호가 오름차순이 되며 방문하지 않은 문자일 때
				visit[i] = true; // 방문 확인
				code[depth] = alpha[i]; // 암호에 문자 입력
				dfs(depth + 1); // 다음 암호 문자 탐색
				visit[i] = false; // 탐색 종료 후 방문 초기화
			}
		}
	}
}