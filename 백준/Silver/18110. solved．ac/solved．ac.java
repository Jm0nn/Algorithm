import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] opinion = new int[n];

		for (int i = 0; i < n; i++)
			opinion[i] = Integer.parseInt(br.readLine());

		Arrays.sort(opinion);

		int cut = (int) Math.round(n * 0.15);
		int sum = 0;

		for (int i = cut; i < n - cut; ++i)
			sum += opinion[i];

		int ans = (int) Math.round((sum / (n - (cut * 2.0))));

		System.out.println(ans);
	}

}