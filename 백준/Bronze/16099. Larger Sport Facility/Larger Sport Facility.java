import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			BigInteger wy = new BigInteger(st.nextToken());
			BigInteger hy = new BigInteger(st.nextToken());
			BigInteger wk = new BigInteger(st.nextToken());
			BigInteger hk = new BigInteger(st.nextToken());

			BigInteger y = wy.multiply(hy);
			BigInteger k = wk.multiply(hk);

			if (y.compareTo(k) > 0) sb.append("TelecomParisTech\n");
			else if (y.compareTo(k) < 0) sb.append("Eurecom\n");
			else sb.append("Tie\n");
		}

		System.out.println(sb);
	}
}