import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static boolean[] broken = new boolean[10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int cnt = Integer.parseInt(br.readLine());

		if (cnt > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (cnt-- > 0)
				broken[Integer.parseInt(st.nextToken())] = true;
		}

		System.out.println(find());
	}

	static int find() {
		int cnt = 0;
		int num1 = n;
		int num2 = n;

		if (Math.abs(n - 100) < 3)
			return Math.abs(n - 100);

		int min = Integer.MAX_VALUE;
		int more = 3;
		boolean count = false;

		while (true) {
			if (num1 == 100 || num2 == 100) {
				if (min > cnt)
					min = cnt;
				count = true;
			} else if (num2 >= 0 && available(num2)) {
				int tmp = cnt + Integer.toString(num2).length();
				if (min > tmp)
					min = tmp;
				count = true;
			} else if (available(num1)) {
				int tmp = cnt + Integer.toString(num1).length();
				if (min > tmp)
					min = tmp;
				count = true;
			}

			if (count) {
				if (--more == 0)
					return min;
			}

			++num1;
			--num2;
			++cnt;
		}
	}

	static boolean available(int num) {
		if (num == 0 && broken[0])
			return false;

		while (num > 0) {
			int i = num % 10;

			if (broken[i])
				return false;

			num /= 10;
		}

		return true;
	}

}