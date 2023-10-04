import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();

		int n = T.length;
		int m = P.length;

		int[] table = new int[m];

		for (int i = 1, j = 0; i < m; ++i) {
			while (j > 0 && P[i] != P[j])
				j = table[j - 1];

			if (P[i] == P[j])
				table[i] = ++j;
		}

		List<Integer> list = new ArrayList<>();

		for (int i = 0, j = 0; i < n; ++i) {
			while (j > 0 && T[i] != P[j])
				j = table[j - 1];

			if (T[i] == P[j]) {
				if (j == m - 1) {
					list.add(i - j + 1);
					j = table[j];
				} else {
					++j;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append('\n');
		for (int i : list)
			sb.append(i).append(' ');
		System.out.println(sb);
	}

}