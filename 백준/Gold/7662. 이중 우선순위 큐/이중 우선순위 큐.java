import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			SortedMap<Integer, Integer> map = new TreeMap<>();

			int k = Integer.parseInt(br.readLine());
			while (k-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				switch (cmd) {
				case 'I':
					map.put(num, map.getOrDefault(num, 0) + 1);
					break;

				case 'D':
					if (map.isEmpty())
						break;

					int key = num == 1 ? map.lastKey() : map.firstKey();
					int cnt = map.get(key);

					if (cnt - 1 == 0)
						map.remove(key);
					else
						map.put(key, cnt - 1);
					break;
				}
			}

			if (map.isEmpty())
				sb.append("EMPTY\n");
			else
				sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
		}

		System.out.println(sb);
	}

}