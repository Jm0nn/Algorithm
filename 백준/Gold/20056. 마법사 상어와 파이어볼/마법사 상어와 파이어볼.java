import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 마법사 상어가 파이어볼을 시전했을 때 파이어볼 질량의 합을 구하는 문제
public class Main {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	static int N; // 필드의 크기
	static Queue<Fireball> fireball = new ArrayDeque<>(); // 파이어볼 큐
	static PriorityQueue<Fireball> tmp = new PriorityQueue<>(); // 임시 파이어볼 우선순위 큐
	static List<Fireball> merged; // 합쳐진 파이어볼 리스트

	// 파이어볼 클래스
	static class Fireball implements Comparable<Fireball> {
		int r; // 행
		int c; // 열
		int m; // 질량
		int s; // 속력
		int d; // 방향

		boolean isMerged; // 합쳐졌는지 확인

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;

			this.isMerged = false;
		}

		// 좌표에 따라 우선순위 정렬
		@Override
		public int compareTo(Fireball o) {
			return this.r != o.r ? this.r - o.r : this.c - o.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 파이어볼의 개수
		int K = Integer.parseInt(st.nextToken()); // 파이어볼의 이동 횟수

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			fireball.offer(new Fireball(r, c, m, s, d));
		}

		while (K-- > 0)
			spell(); // 파이어볼 이동

		int sum = 0; // 파이어볼 질량의 합
		for (Fireball fb : fireball)
			sum += fb.m;

		System.out.println(sum);

		br.close();
	}

	// 파이어볼 이동
	static void spell() {
		while (!fireball.isEmpty()) {
			Fireball fb = fireball.poll(); // 파이어볼 큐에서 꺼냄
			
			// 이동
			for (int j = 0; j < fb.s; j++) {
				fb.r = (fb.r + deltas[fb.d][0] + N) % N;
				fb.c = (fb.c + deltas[fb.d][1] + N) % N;
			}

			if (fb.r == 0)
				fb.r = N;
			if (fb.c == 0)
				fb.c = N;
			
			tmp.offer(fb); // 이동 완료되면 임시 우선순위 큐에 넣음
		}
		
		while (!tmp.isEmpty()) {
			merged = new ArrayList<>();
			
			Fireball fb = tmp.peek(); // 우선순위 큐의 가장 위 요소
			
			// 현재 파이어볼 좌표
			int r = fb.r;
			int c = fb.c;
			
			while (!tmp.isEmpty()) {
				Fireball f = tmp.peek();
				
				// 새로 확인한 좌표가 처음 본 좌표와 같다면 꺼내서 합쳐진 리스트에 넣음
				if (f.r == r && f.c == c)
					merged.add(tmp.poll());
				else // 좌표가 다른 파이어볼이 나오면 반복문 탈출
					break;
			}

			// 합쳐진 파이어볼이 없다면 넘어감
			if (merged.size() == 1) {
				fireball.offer(fb);
				continue;
			}

			int m = 0; // 합쳐진 질량
			int s = 0; // 합쳐진 속력
			boolean isOdd = true; // 방향이 모두 홀수인지
			boolean isEven = true; // 방향이 모두 짝수인지

			for (int j = 0; j < merged.size(); j++) {
				merged.get(j).isMerged = true; // 합쳐짐 확인
				m += merged.get(j).m;
				s += merged.get(j).s;

				if (merged.get(j).d % 2 == 0)
					isOdd = false;
				else
					isEven = false;
			}

			m /= 5; // 나누어진 파이어볼의 질량

			if (m == 0) // 질량이 0이면 소멸됨
				continue;

			s /= merged.size(); // 나누어진 파이어볼의 속력

			if (isOdd || isEven) // 합쳐진 파이어볼의 방향이 모두 홀수이거나 모두 짝수일 때 +로 나누어짐
				for (int d = 0; d < 8; d += 2)
					fireball.offer(new Fireball(r, c, m, s, d));
			else // 합쳐진 파이어볼의 방향의 홀짝이 같지 않으면 x로 나누어짐
				for (int d = 1; d < 8; d += 2)
					fireball.offer(new Fireball(r, c, m, s, d));
		}
	}

}