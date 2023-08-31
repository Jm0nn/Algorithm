import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] queue = new int[n];

		int head = 0, tail = 0;

		StringBuilder sb = new StringBuilder();

		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				queue[tail++] = Integer.parseInt(st.nextToken());
				continue;

			case "pop":
				sb.append(head != tail ? queue[head++] : -1);
				break;

			case "size":
				sb.append(tail - head);
				break;

			case "empty":
				sb.append(head == tail ? 1 : 0);
				break;

			case "front":
				sb.append(head != tail ? queue[head] : -1);
				break;

			case "back":
				sb.append(head != tail ? queue[tail - 1] : -1);
				break;
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

}