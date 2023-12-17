import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger sum = new BigInteger(br.readLine());
		BigInteger dif = new BigInteger(br.readLine());
		System.out.println(sum.add(dif).divide(BigInteger.valueOf(2)));
		System.out.println(sum.subtract(dif).divide(BigInteger.valueOf(2)));
	}
}