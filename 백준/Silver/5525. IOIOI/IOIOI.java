import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String t = br.readLine();

		StringBuilder pb = new StringBuilder("I");
		while (n-- > 0)
			pb.append("OI");

		String p = pb.toString();
		int len = p.length();

		int[] table = new int[len];

		for (int i = 1, j = 0; i < len; ++i) {
			while (j > 0 && p.charAt(i) != p.charAt(j))
				j = table[j - 1];

			if (p.charAt(i) == p.charAt(j))
				table[i] = ++j;
		}

		int cnt = 0;

		for (int i = 0, j = 0; i < m; ++i) {
			while (j > 0 && t.charAt(i) != p.charAt(j))
				j = table[j - 1];

			if (t.charAt(i) == p.charAt(j)) {
				if (j == len - 1) {
					++cnt;
					j = table[j];
				} else {
					++j;
				}
			}
		}

		System.out.println(cnt);
	}

}