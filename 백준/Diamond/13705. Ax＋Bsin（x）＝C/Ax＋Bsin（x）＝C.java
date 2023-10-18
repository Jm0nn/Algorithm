import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {

	static final BigDecimal ERROR = new BigDecimal("0.0000000000000000000000001");

	static final BigDecimal NEG = BigDecimal.valueOf(-1);
	static final BigDecimal ZERO = BigDecimal.ZERO;
	static final BigDecimal ONE = BigDecimal.ONE;
	static final BigDecimal TWO = BigDecimal.valueOf(2);
	static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");

	static BigDecimal A, B, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = new BigDecimal(st.nextToken());
		B = new BigDecimal(st.nextToken());
		C = new BigDecimal(st.nextToken());

		BigDecimal left = (C.subtract(B)).divide(A, 100, BigDecimal.ROUND_HALF_UP);
		BigDecimal right = (C.add(B)).divide(A, 100, BigDecimal.ROUND_HALF_UP);

		BigDecimal mid = null;

		while (right.subtract(left).abs().compareTo(ERROR) >= 0) {
			mid = (left.add(right)).divide(TWO);

			if (f(mid).compareTo(ZERO) > 0)
				right = mid;
			else
				left = mid;
		}

		System.out.println(String.format("%.6f", mid));
	}

	static BigDecimal f(BigDecimal x) {
		return ((x.multiply(A)).add(sin(x).multiply(B))).subtract(C);
	}

	static BigDecimal sin(BigDecimal x) {
		x = x.remainder(PI.multiply(TWO));

		BigDecimal sum = new BigDecimal(x.toString());
		BigDecimal pow = new BigDecimal(x.toString());
		BigDecimal fact = ONE;
		BigDecimal x2 = x.multiply(x).multiply(NEG);

		for (int i = 1; i <= 50; ++i) {
			BigDecimal tmp = new BigDecimal(i).multiply(TWO);
			fact = fact.multiply(tmp.multiply(tmp.add(ONE)));
			pow = pow.multiply(x2);
			BigDecimal tmp2 = pow.divide(fact, 100, BigDecimal.ROUND_HALF_UP);
			sum = sum.add(tmp2);
		}

		return sum;
	}
}