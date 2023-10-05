import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[] prime = new boolean[5_000_001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		getPrime();

		long teamA = 0, teamB = 0;
		boolean[] called = new boolean[5_000_001];

		int n = Integer.parseInt(br.readLine());
		Queue<Integer> callA = new PriorityQueue<>((o1, o2) -> o2 - o1);
		Queue<Integer> callB = new PriorityQueue<>((o1, o2) -> o2 - o1);

		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (called[a]) {
				teamA -= 1000;
			} else {
				if (!prime[a]) {
					called[a] = true;
					callA.offer(a);
				} else {
					if (callB.size() < 3) {
						teamB += 1000;
					} else {
						int tmp1 = callB.poll();
						int tmp2 = callB.poll();

						teamB += callB.peek();

						callB.offer(tmp1);
						callB.offer(tmp2);
					}
				}
			}

			if (called[b]) {
				teamB -= 1000;
			} else {
				if (!prime[b]) {
					called[b] = true;
					callB.offer(b);
				} else {
					if (callA.size() < 3) {
						teamA += 1000;
					} else {
						int tmp1 = callA.poll();
						int tmp2 = callA.poll();

						teamA += callA.peek();

						callA.offer(tmp1);
						callA.offer(tmp2);
					}
				}
			}
		}

		if (teamA > teamB)
			System.out.println("소수의 신 갓대웅");
		else if (teamA < teamB)
			System.out.println("소수 마스터 갓규성");
		else
			System.out.println("우열을 가릴 수 없음");
	}

	static void getPrime() {
		int n = 5000000;

		prime[0] = prime[1] = true;

		for (int i = 2; i * i <= n; ++i) {
			if (!prime[i]) {
				for (int j = i * i; j <= n; j += i)
					prime[j] = true;
			}
		}
	}

}