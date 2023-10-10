import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] matrix;
	static boolean[] know;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		matrix = new int[n + 1][n + 1];

		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int[] knowPeople = new int[k];
		for (int i = 0; i < k; ++i)
			knowPeople[i] = Integer.parseInt(st.nextToken());

		@SuppressWarnings("unchecked")
		List<Integer>[] party = new List[m];
		for (int i = 0; i < m; ++i) {
			party[i] = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			while (p-- > 0) {
				party[i].add(Integer.parseInt(st.nextToken()));
				int size = party[i].size();

				for (int j = 0; j < size - 1; ++j) {
					int a = party[i].get(j);
					int b = party[i].get(size - 1);
					matrix[a][b] = 1;
					matrix[b][a] = 1;
				}
			}
		}

		know = new boolean[n + 1];
		for (int i = 0; i < k; ++i)
			dfs(knowPeople[i]);

		int cnt = 0;
		loop: for (int i = 0; i < m; ++i) {
			for (int num : party[i]) {
				if (know[num])
					continue loop;
			}
			++cnt;
		}

		System.out.println(cnt);
	}

	static void dfs(int num) {
		if (know[num])
			return;

		know[num] = true;

		for (int i = 1; i <= n; ++i) {
			if (matrix[num][i] == 1)
				dfs(i);
		}
	}

}