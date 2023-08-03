import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int n, num;
//	static boolean[] isNotPrime;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		num = (int) Math.pow(10, n - 1);
		
//		isNotPrime = new boolean[num * 10];
//		makePrime();

		for (int i = 2; i < 10; i++) {
			if (isPrime(i))
				recur(i);
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
//	static void makePrime() {
//		isNotPrime[0] = true;
//		isNotPrime[1] = true;
//		
//		for (int i = 2; i * i < isNotPrime.length; i++) {
//			if (isNotPrime[i])
//				continue;
//			
//			for (int j = i * i; j < isNotPrime.length; j += i)
//				isNotPrime[j] = true;
//		}
//	}
	
	static void recur(int k) {
		if (num < k && k < num * 10) {
			sb.append(k).append('\n');
			return;
		}
		
		int tmp = k * 10;
		
		for (int i = tmp + 1; i < tmp + 10; i += 2) {
			if (isPrime(i))
				recur(i);
		}
	}
	
	static boolean isPrime(int number) {
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0)
				return false;
		}
		
		return true;
	}
	
}