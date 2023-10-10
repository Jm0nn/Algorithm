import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] sol = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = -1;
		int mid = Integer.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			int num = Integer.parseInt(st.nextToken());
			sol[i] = num;
			if (mid > Math.abs(num)) {
				mid = Math.abs(num);
				idx = i;
			}
		}

		int left = idx - 1;
		int right = idx;

		if (idx == 0) {
			left = idx;
			right = idx + 1;
		}

		int min = Integer.MAX_VALUE;
		int low = 0;
		int high = 0;

		while (left >= 0 && right < n) {
			int sum = sol[left] + sol[right];
			int abs = Math.abs(sum);

			if (min > abs) {
				min = abs;
				low = sol[left];
				high = sol[right];
			}

			if (sum == 0)
				break;

			if (sum > 0)
				--left;
			else
				++right;
		}

		System.out.println(low + " " + high);
	}

}