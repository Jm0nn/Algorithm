import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	// 보석 클래스
	static class Jewel {
		int m; // 무게
		int v; // 가격

		public Jewel(int m, int v) {
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 보석의 개수
		int k = Integer.parseInt(st.nextToken()); // 가방의 개수
		Jewel[] jewel = new Jewel[n]; // 보석 배열
		int[] c = new int[k]; // 가방 배열

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < k; i++)
			c[i] = Integer.parseInt(br.readLine());

		// 보석 배열을 무게의 오름차순으로, 무게가 같다면 가격의 내림차순으로 정렬
		// 무게가 가볍고 가격이 높을수록 앞으로 정렬
		Arrays.sort(jewel, new Comparator<Jewel>() {

			@Override
			public int compare(Jewel o1, Jewel o2) {
				if (o1.m == o2.m)
					return o2.v - o1.v;

				return o1.m - o2.m;
			}

		});

		Arrays.sort(c); // 가방 배열을 오름차순으로 정렬

		// 가격의 내림차순으로 정렬하는 우선순위 큐
		PriorityQueue<Integer> vQueue = new PriorityQueue<>(Comparator.reverseOrder());
		long answer = 0; // 정답
		for (int i = 0, idx = 0; i < k; i++) {
			// 현재 가방에 들어갈 수 있는 보석을 모두 우선순위 큐에 넣음
			// 보석의 인덱스를 따로 계산하기 때문에 중복해서 큐에 들어가지 않음
			while (idx < n && jewel[idx].m <= c[i])
				vQueue.offer(jewel[idx++].v);

			// 우선순위 큐에서 요소를 하나 빼서 가방에 넣음
			if (!vQueue.isEmpty())
				answer += vQueue.poll();
		}

		System.out.println(answer);

		br.close();
	}

}