import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] input = br.readLine().toCharArray();
		int len = input.length;
		for (int i = len - 1; i >= 0; --i) {
			sb.append(input[i]);
		}
		System.out.println(sb);
	}
}