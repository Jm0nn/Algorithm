import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = br.readLine();
		long ans = 0;
		int len = n.length();
		for (int i = 0; i < len; ++i)
			ans = (ans * 10 + (n.charAt(i) - '0')) % 20000303;
		System.out.print(ans);
	}
}