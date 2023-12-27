import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int ur = Integer.parseInt(st.nextToken());
		int tr = Integer.parseInt(st.nextToken());
		int uo = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());

		System.out.println(56 * ur + 24 * tr + 14 * uo + 6 * to);
	}
}