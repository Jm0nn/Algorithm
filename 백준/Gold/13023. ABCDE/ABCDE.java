import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static boolean right;
	static List<Integer> list;
	static List<Integer>[] friend;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		friend = new ArrayList[n];

		for (int i = 0; i < n; i++)
			friend[i] = new ArrayList<>();

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			friend[a].add(b);
			friend[b].add(a);
		}

		for (int i = 0; i < n; i++) {
			list = new ArrayList<>();
			dfs(1, i);
			if (right)
				break;
		}

		System.out.println(right ? 1 : 0);
	}

	static void dfs(int depth, int idx) {
		if (depth == 5) {
			right = true;
			return;
		}

		list.add(idx);

		for (int i : friend[idx]) {
			if (!list.contains(i))
				dfs(depth + 1, i);

			if (right)
				return;
		}

		list.remove(list.size() - 1);
	}

}