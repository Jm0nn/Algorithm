import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] score = new int[N + 1];

			for (int i = 1; i <= N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				score[a] = b;
			}

			int cnt = 0;
			int min = N + 1;

			for (int i = 1; i <= N; ++i) {
				if (score[i] < min) {
					++cnt;
					min = score[i];
				}
			}

			sb.append(cnt).append('\n');
		}

		System.out.println(sb);
	}
}