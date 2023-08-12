import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 트럭에서 연료가 새는 도중 마을까지 가면서 주유소에 들르는 횟수를 구하는 문제
public class Main {

	// 주유소 클래스
	static class GasStation implements Comparable<GasStation> {
		int a; // 위치
		int b; // 충전할 수 있는 기름의 양

		public GasStation(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(GasStation o) {
			return this.a - o.a;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 주유소의 수

		// 주유소 거리에 따라 오름차순으로 정렬한 우선순위 큐
		PriorityQueue<GasStation> gs = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			gs.offer(new GasStation(a, b));
		}

		st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken()); // 마을의 위치
		int p = Integer.parseInt(st.nextToken()); // 현재 기름의 양

		// 연료를 내림차순으로 정렬한 우선순위 큐
		PriorityQueue<Integer> fuel = new PriorityQueue<>(Collections.reverseOrder());

		int cnt = 0; // 주유소에 도달한 횟수

		// 연료가 마을에 도달할 만큼 충분할 때까지 반복
		while (p < l) {
			// 연료가 다음 주유소에 도달할 만큼 충분하면 연료 큐에 추가
			while (!gs.isEmpty() && gs.peek().a <= p)
				fuel.offer(gs.poll().b);

			// 연료 큐가 비었다면 마을에 도달하지 못하므로 반복문 탈출
			if (fuel.isEmpty()) {
				cnt = -1;
				break;
			}

			cnt++; // 횟수 증가
			p += fuel.poll(); // 연료 증가, 큐에서 가장 큰 수가 더해짐
		}

		System.out.println(cnt);

		br.close();
	}

}