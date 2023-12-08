import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] a = new int[3];
		int[] c = new int[3];

		StringTokenizer stA = new StringTokenizer(br.readLine());
		StringTokenizer stC = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; ++i) {
			a[i] = Integer.parseInt(stA.nextToken());
			c[i] = Integer.parseInt(stC.nextToken());
		}

		System.out.printf("%d %d %d", c[0] - a[2], c[1] / a[1], c[2] - a[0]);
	}
}