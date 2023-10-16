import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		double C = Double.parseDouble(br.readLine());

		int maxSF = (int) (C / 0.99);

		if (maxSF > 2)
			maxSF = 2;

		int n = Integer.parseInt(br.readLine());

		int[] solve = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			solve[i] = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = 0;

		int day = 0;
		int maxSolve = 0;
		int zeroCnt = 0;

		while (right < n) {
			int tmpSolve = solve[right++];

			if (tmpSolve > 0) {
				int tmpday = right - left;

				if (day < tmpday)
					day = tmpday;

				if (maxSolve < tmpSolve)
					maxSolve = tmpSolve;
			} else {
				if (++zeroCnt <= maxSF) {
					int tmpday = right - left;

					if (day < tmpday)
						day = tmpday;
				} else {
					while (zeroCnt > maxSF) {
						if (solve[left++] == 0)
							--zeroCnt;
					}
				}
			}
		}

		System.out.println(day);
		System.out.println(maxSolve);
	}

}