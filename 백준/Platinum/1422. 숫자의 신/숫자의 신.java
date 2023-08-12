import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// K개의 수에서 N개의 수를 뽑아 이어붙여 가장 큰 수를 만드는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 입력받을 수의 개수
		int N = Integer.parseInt(st.nextToken()); // 사용해야 할 수의 개수
		String[] num = new String[K]; // 입력받은 수 배열
		int max = 0; // 입력받은 수의 최댓값

		for (int i = 0; i < K; i++) {
			num[i] = br.readLine();
			max = Math.max(max, Integer.parseInt(num[i]));
		}

		// 두 수를 이어붙여서 더 큰 수가 앞으로 오게 정렬
		Arrays.sort(num, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

		StringBuilder sb = new StringBuilder();
		boolean maxUsed = false; // 최댓값이 중복으로 들어왔을 때 사용할 flag

		for (String s : num) {
			sb.append(s);
			// 현재 수가 최댓값이고, 처음 들어왔다면
			if (max == Integer.parseInt(s) && !maxUsed) {
				// N이 K보다 클 경우, 최댓값을 N - K 만큼 사용
				for (int i = K; i < N; i++) {
					sb.append(s);
					maxUsed = true;
				}
			}
		}

		System.out.println(sb);

		br.close();
	}
}