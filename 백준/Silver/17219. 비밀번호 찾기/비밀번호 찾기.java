import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		String key;
		String value;
		Node next;

		public Node(String key, String value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	static final int HASH = 5381;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Node[] hashMap = new Node[HASH];

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());

			String key = st.nextToken();
			String value = st.nextToken();

			int idx = getIndex(key);

			hashMap[idx] = new Node(key, value, hashMap[idx]);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m; ++i) {
			String key = br.readLine();
			int idx = getIndex(key);

			for (Node cur = hashMap[idx]; cur != null; cur = cur.next) {
				if (key.equals(cur.key)) {
					sb.append(cur.value).append('\n');
					break;
				}
			}
		}

		System.out.println(sb);
	}

	static int hashCode(String s) {
		int hash = HASH;
		int len = s.length();

		for (int i = 0; i < len; ++i) {
			hash = (hash << 5) + s.charAt(i);
		}

		if (hash < 0) {
			hash *= -1;
		}

		return hash;
	}

	static int getIndex(String s) {
		int hash = hashCode(s);
		return hash % HASH;
	}

}