import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		final String vowel = "aeiou";
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			if (vowel.contains(String.valueOf(s.charAt(i)))) {
				++cnt;
			}
		}
		System.out.println(cnt);
	}
}