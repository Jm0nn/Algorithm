import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int t = h * 3600 + m * 60 + s + Integer.parseInt(br.readLine());

		int nh = t / 3600 % 24;
		int nm = (t % 3600) / 60;
		int ns = t % 60;

		System.out.printf("%d %d %d", nh, nm, ns);
	}
}