import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int min;
	static int[] comp = new int[3];
	static int[] cnt = new int[16];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			Arrays.fill(cnt, 0);

			int n = Integer.parseInt(br.readLine());
			int max = 0;

			st = new StringTokenizer(br.readLine());
			while (n-- > 0) {
				String mbti = st.nextToken();
				int idx = 0;

				for (int i = 0; i < 4; ++i) {
					char c = mbti.charAt(i);

					int bit = 0;

					switch (c) {
					case 'E':
					case 'S':
					case 'T':
					case 'J':
						bit = 0;
						break;

					case 'I':
					case 'N':
					case 'F':
					case 'P':
						bit = 1;
						break;
					}

					idx += bit << i;
				}

				if (max < ++cnt[idx])
					max = cnt[idx];
			}

			if (max >= 3) {
				sb.append(0).append('\n');
				continue;
			}

			min = Integer.MAX_VALUE;
			dfs(0, 0);

			sb.append(min).append('\n');
		}

		System.out.println(sb);
	}

	static void dfs(int depth, int start) {
		if (depth == 3) {
			int dist = 0;

			dist += cal(comp[0], comp[1]);
			dist += cal(comp[1], comp[2]);
			dist += cal(comp[2], comp[0]);

			if (min > dist)
				min = dist;

			return;
		}

		for (int i = start; i < 16; ++i) {
			if (cnt[i] == 0)
				continue;

			--cnt[i];
			comp[depth] = i;
			dfs(depth + 1, i);
			++cnt[i];
		}
	}

	static int cal(int a, int b) {
		int res = 0;

		for (int i = 0; i < 4; ++i) {
			if ((a & 1) != (b & 1))
				++res;

			a >>= 1;
			b >>= 1;
		}

		return res;
	}

}