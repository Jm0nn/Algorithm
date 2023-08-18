import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if ('A' <= c && c <= 'Z')
				c += 'a' - 'A';
			else
				c += 'A' - 'a';
			sb.append(c);
		}
		System.out.println(sb);
	}
}