import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] in, post;
	static int[] index = new int[100_001];

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		in = new int[n];
		post = new int[n];

		StringTokenizer sti = new StringTokenizer(br.readLine());
		StringTokenizer stp = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			index[in[i] = Integer.parseInt(sti.nextToken())] = i;
			post[i] = Integer.parseInt(stp.nextToken());
		}

		pre(0, 0, n - 1);

		System.out.println(sb);
	}

	static void pre(int is, int ps, int pe) {
		if (ps > pe) {
			return;
		}

		int root = index[post[pe]];
		int size = root - is;

		sb.append(in[root]).append(' ');

		pre(is, ps, ps + size - 1);
		pre(root + 1, ps + size, pe - 1);
	}

}