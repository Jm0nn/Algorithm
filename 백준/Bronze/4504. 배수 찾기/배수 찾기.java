import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String yes = " is a multiple of ";
		String no = " is NOT a multiple of ";
		int n = Integer.parseInt(br.readLine());
		int m;
		while ((m = Integer.parseInt(br.readLine())) != 0) {
			sb.append(m);
			sb.append(m % n == 0 ? yes : no);
			sb.append(n).append(".\n");
		}
		System.out.print(sb);
	}
}