import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, d, k, c;
	static int[] sushi;
	static int[] choice;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int[] sushi = new int[n];
		int[] conti = new int[k];
		choice = new int[d + 1];

		int cnt = 0;

		for (int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
			if (i < k) {
				conti[i] = sushi[i];
				if (choice[conti[i]] == 0)
					cnt++;
				choice[conti[i]]++;
			}
		}

		int sidx = k;
		int cidx = 0;
		int max = 0;

		for (int i = 0; i < n; i++) {
			if (choice[c] == 0)
				cnt++;

			if (max < cnt)
				max = cnt;

			if (choice[c] == 0)
				cnt--;

			if (--choice[conti[cidx]] == 0)
				cnt--;

			conti[cidx] = sushi[sidx];

			if (choice[conti[cidx]]++ == 0)
				cnt++;

			if (++sidx == n)
				sidx = 0;
			if (++cidx == k)
				cidx = 0;
		}

		System.out.println(max);
	}

}