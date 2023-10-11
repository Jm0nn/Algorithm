import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 카드에 적힌 수를 비교하여 나누어 떨어지게 하면 승리 나누어 떨어지면 패배
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 카드의 수

		int[] card = new int[n]; // 카드 배열
		boolean[] check = new boolean[1000001]; // 뽑힌 카드 확인 배열

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			card[i] = num;
			check[num] = true;
		}

		@SuppressWarnings("unchecked")
		List<Integer>[] win = new List[n];

		for (int i = 0; i < n; i++) {
			// card[i]를 이기는 수 배열
			win[i] = new ArrayList<>();

			// 에라토스테네스의 체
			for (int j = 1; j * j <= card[i]; j++) {
				// j가 card[i]의 약수
				if (card[i] % j == 0)
					win[i].add(j);

				// card[i]를 j로 나눈 수가 card[i]의 약수
				if (j * j != card[i] && card[i] % j == 0)
					win[i].add(card[i] / j);
			}
		}

		int[] ans = new int[1000001]; // 점수 배열

		for (int i = 0; i < n; i++) {
			// card[i]를 이기는 카드 배열
			for (int k : win[i]) {
				// 뽑힌 번호에 대해
				if (check[k]) {
					ans[k]++; // 이긴 카드 점수 증가
					ans[card[i]]--; // 진 카드 점수 감소
				}
			}
		}

		// 점수 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(ans[card[i]]).append(' ');
		System.out.println(sb);
	}

}