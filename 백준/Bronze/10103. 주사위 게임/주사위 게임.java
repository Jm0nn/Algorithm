import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int a = 100;
		int b = 100;

		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());

			int da = Integer.parseInt(st.nextToken());
			int db = Integer.parseInt(st.nextToken());

			if (da > db)
				b -= da;
			else if (da < db)
				a -= db;
		}

		System.out.println(a);
		System.out.println(b);
	}
}