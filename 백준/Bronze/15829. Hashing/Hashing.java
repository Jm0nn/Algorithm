import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int r = 31;
		final int M = 1234567891;
		int H = 0;
		int L = Integer.parseInt(br.readLine());
		String s = br.readLine();
		for (int i = 0; i < L; i++)
			H += ((s.charAt(i) - 'a' + 1) * (int) Math.pow(r, i)) % M;
		System.out.println(H);
	}
}