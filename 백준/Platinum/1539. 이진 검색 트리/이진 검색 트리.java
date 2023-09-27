import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		long sum = 0;

		TreeMap<Integer, Long> map = new TreeMap<>();

		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine());

			Entry<Integer, Long> floor = map.floorEntry(num);
			Entry<Integer, Long> ceiling = map.ceilingEntry(num);

			long h = -1;
			if (floor == null && ceiling == null) {
				h = 1;
			} else if (floor == null) {
				h = ceiling.getValue() + 1;
			} else if (ceiling == null) {
				h = floor.getValue() + 1;
			} else {
				long f = floor.getValue();
				long c = ceiling.getValue();

				if (f < c) {
					h = c + 1;
				} else {
					h = f + 1;
				}
			}

			map.put(num, h);
			sum += h;
		}

		System.out.println(sum);
	}

}