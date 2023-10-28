import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int sum = S + K + H;

		if (sum >= 100) {
			System.out.println("OK");
		} else {
			int min = Math.min(S, Math.min(K, H));

			if (min == S) {
				System.out.println("Soongsil");
			} else if (min == K) {
				System.out.println("Korea");
			} else if (min == H) {
				System.out.println("Hanyang");
			}
		}
	}

}