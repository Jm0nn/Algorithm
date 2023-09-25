public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder("n e\n- -----------\n0 1\n1 2\n2 2.5\n");
		double e = 2.5;
		double fac = 2.0;
		for (int i = 3; i < 10; ++i) {
			fac *= i;
			e += (1 / fac);
			sb.append(String.format("%d %.9f\n", i, e));
		}
		System.out.println(sb);
	}
}