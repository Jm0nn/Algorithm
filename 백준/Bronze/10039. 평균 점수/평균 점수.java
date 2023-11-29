import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for (int i = 0; i < 5; ++i) {
			int n = Integer.parseInt(br.readLine());
			sum += Math.max(n, 40);
		}
		System.out.println(sum / 5);
	}
}