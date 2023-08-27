import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, cnt;
	static int[] student;
	static boolean[] visit; // 방문 배열
	static boolean[] finish; // 확인 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			student = new int[n + 1];
			visit = new boolean[n + 1];
			finish = new boolean[n + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++)
				student[i] = Integer.parseInt(st.nextToken());

			cnt = 0;

			for (int i = 1; i <= n; i++) {
				if (!visit[i])
					dfs(i);
			}

			sb.append(n - cnt).append('\n');
		}

		System.out.println(sb);
	}

	static void dfs(int cur) {
		visit[cur] = true;
		int next = student[cur];

		// dfs로 방문하지 않은 노드 계속 방문
		if (!visit[next])
			dfs(next);

		// dfs로 계속 방문을 해서 방문할 수 있는 모든 노드를 방문하면
		// 싸이클 내 노드 개수만큼 카운트 증가
		else if (!finish[next]) {
			cnt++;
			for (int i = next; i != cur; i = student[i])
				cnt++;
		}

		finish[cur] = true; // 확인 완료
	}

}