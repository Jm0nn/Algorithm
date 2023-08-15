import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int answer = 0;
			int count = 0;

			String s = br.readLine();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'O')
					answer += ++count;
				else
					count = 0;
			}

			sb.append(answer).append('\n');
		}

		System.out.println(sb);
	}
}