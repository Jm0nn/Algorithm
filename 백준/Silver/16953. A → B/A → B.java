import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int ans = -1;
		int cnt = 0;

		while (A < B) {
			if (B % 2 == 0)
				B /= 2;
			else if (B % 10 == 1)
				B /= 10;
			else
				break;

			++cnt;

			if (A == B)
				ans = ++cnt;
		}

		System.out.println(ans);
	}

}