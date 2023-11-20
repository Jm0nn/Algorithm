import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			String input = br.readLine();
			char c = input.charAt(0);
			if ('a' <= c && c <= 'z')
				sb.append((char) (c - 'a' + 'A')).append(input.substring(1)).append('\n');
			else
				sb.append(input).append('\n');
		}
		System.out.print(sb);
	}
}