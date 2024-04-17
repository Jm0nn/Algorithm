import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] people = new int[N][2];

			for (int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				people[i][0] = a;
				people[i][1] = b;
			}

			Arrays.sort(people, Comparator.comparingInt(o -> o[0]));

			int cnt = 0;
			int min = N + 1;

			for (int[] arr : people) {
				if (arr[1] < min) {
					++cnt;
					min = arr[1];
				}
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}
}