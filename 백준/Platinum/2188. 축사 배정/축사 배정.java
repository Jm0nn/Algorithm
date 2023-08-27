import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 최대한 많은 수의 소가 원하는 축사에 들어가게 하는 문제
public class Main {

	static int n, m; // 소의 수, 축사의 수
	static List<Integer>[] edge; // 소가 들어가고 싶은 축사 리스트
	static int[] match; // 해당 축사에 들어간 소
	static boolean[] visit; // 방문 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		edge = new List[n];
		match = new int[m + 1];

		Arrays.fill(match, -1);
		for (int i = 0; i < n; i++)
			edge[i] = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());

			for (int j = 0; j < s; j++)
				edge[i].add(Integer.parseInt(st.nextToken()));
		}

		int cnt = 0; // 원하는 축사에 들어간 소의 최댓값
		for (int i = 0; i < n; i++) {
			visit = new boolean[m + 1];

			if (dfs(i))
				cnt++;
		}

		System.out.println(cnt);
	}

	// 이분 매칭
	static boolean dfs(int from) {
		for (int i = 0; i < edge[from].size(); i++) {
			int to = edge[from].get(i);

			if (visit[to])
				continue;

			visit[to] = true;

			if (match[to] == -1 || dfs(match[to])) {
				match[to] = from;
				return true;
			}
		}

		return false;
	}
}