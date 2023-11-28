import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine());
		int t = l / 5 + (l % 5 > 0 ? 1 : 0);
		System.out.println(t);
	}
}