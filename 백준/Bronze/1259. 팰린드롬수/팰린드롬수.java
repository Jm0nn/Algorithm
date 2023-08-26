import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		while (!(s = br.readLine()).equals("0")) {
			char[] num = s.toCharArray();
			boolean flag = true;
			for (int i = 0; i < num.length / 2; i++) {
				if (num[i] != num[num.length - i - 1]) {
					flag = false;
					break;
				}
			}
			sb.append(flag ? "yes\n" : "no\n");
		}
		System.out.println(sb);
	}
}