import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static long[] p = new long[100];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		p();
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(p[n - 1]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static void p() {
		p[0] = 1;
		p[1] = 1;
		p[2] = 1;
		p[3] = 2;
		p[4] = 2;
		for (int i = 5; i < 100; i++) {
			p[i] = p[i - 1] + p[i - 5];
		}
	}

}