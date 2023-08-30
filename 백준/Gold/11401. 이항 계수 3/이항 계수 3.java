import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	final static long mod = 1_000_000_007L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		long num = factorial(n);
		long denom = factorial(k) * factorial(n - k) % mod;
		long answer = num * pow(denom, mod - 2) % mod;
		System.out.println(answer);
		br.close();
	}
	
	static long factorial(long n) {
		long fac = 1L;
		while (n > 1)
			fac = (fac * n--) % mod;
		return fac;
	}
	
	static long pow(long n, long exp) {
		if (exp == 1)
			return n % mod;
		else {
			long tmp = pow(n, exp / 2);
			if (exp % 2 == 1)
				return (tmp * tmp % mod) * n % mod;
			else
				return tmp * tmp % mod;
		}
	}

}