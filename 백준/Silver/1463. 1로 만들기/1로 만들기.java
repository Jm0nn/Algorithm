public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println(recur(nextInt(), 0));
	}

	static int recur(int n, int rem) {
		if (n < 2)
			return rem;
		return Math.min(recur(n / 2, rem + n % 2 + 1), recur(n / 3, rem + n % 3 + 1));
	}

	static int nextInt() throws Exception {
		int res = 0;
		int tmp;
		while ((tmp = System.in.read()) >= 48)
			res = res * 10 + (tmp - 48);
		return res;
	}
}