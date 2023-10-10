import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다각형의 꼭짓점의 좌표가 주어질 때 다각형의 면적을 구하는 문제
// 신발끈 정리(위키 검색)
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		long[] x = new long[n + 1];
		long[] y = new long[n + 1];

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Long.parseLong(st.nextToken());
			y[i] = Long.parseLong(st.nextToken());
		}

		x[n] = x[0];
		y[n] = y[0];

		long sum1 = 0;
		long sum2 = 0;

		for (int i = 0; i < n; ++i) {
			sum1 += x[i] * y[i + 1];
			sum2 += y[i] * x[i + 1];
		}

		System.out.println(String.format("%.1f", Math.abs(sum1 - sum2) / 2.0));
	}

}