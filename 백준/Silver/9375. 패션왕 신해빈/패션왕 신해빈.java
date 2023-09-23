import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			List<String> keyList = new ArrayList<>();
			while (n-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String s = st.nextToken();
				if (map.containsKey(s)) {
					map.put(s, map.get(s) + 1);
				} else {
					keyList.add(s);
					map.put(s, 1);
				}
			}
			int ans = 1;
			for (String key : keyList) {
				ans *= map.get(key) + 1;
			}
			ans -= 1;
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

}