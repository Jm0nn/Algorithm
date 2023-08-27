import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 상어들이 서로 잡아먹을 때 상어 수의 최솟값을 구하는 문제
public class Main {

	// 상어 클래스
	static class Shark {
		int size; // 크기
		int speed; // 속도
		int intel; // 지능

		Shark(int size, int speed, int intel) {
			this.size = size;
			this.speed = speed;
			this.intel = intel;
		}

		// 상어가 다른 상어를 먹을 수 있는지
		// 크기, 속도, 지능 모두가 다른 상어보다 크거가 같다면 해당 상어를 먹을 수 있음
		boolean eatable(Shark o) {
			if (this.size >= o.size && this.speed >= o.speed && this.intel >= o.intel)
				return true;
			return false;
		}
	}

	static int n; // 상어 수
	static Shark[] shark; // 상어 배열
	static List<Integer>[] edge; // 먹을 수 있는 상어 리스트
	static int[] match; // 어떤 상어가 나를 먹었는지
	static boolean[] eaten; // 먹혔는지 여부

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		shark = new Shark[n];
		edge = new List[n];
		match = new int[n];

		Arrays.fill(match, -1); // 아직 먹히지 않은 상어는 -1
		for (int i = 0; i < n; i++)
			edge[i] = new ArrayList<>();

		// 상어 입력
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int size = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int intel = Integer.parseInt(st.nextToken());

			shark[i] = new Shark(size, speed, intel);

			// 이전에 입력받은 상어들과 관계 생성
			for (int j = 0; j < i; j++) {

				// 서로 먹을 수 있다면 먹는 방향을 하나만 고정
				if (shark[i].eatable(shark[j]) && shark[j].eatable(shark[i]))
					edge[i].add(j);

				// 어느 한 쪽만 먹을 수 있다면 해당 방향으로 설정
				else if (shark[i].eatable(shark[j]))
					edge[i].add(j);
				else if (shark[j].eatable(shark[i]))
					edge[j].add(i);
			}
		}

		int cnt = 0; // 먹힌 상어 수의 최댓값

		// 최대 두 마리 먹을 수 있음
		for (int eat = 0; eat < 2; eat++) {
			for (int i = 0; i < n; i++) {
				eaten = new boolean[n];

				if (dfs(i))
					cnt++;
			}
		}

		// 남은 상어 수의 최솟값 출력
		System.out.println(n - cnt);
	}

	// 이분 매칭으로 상어 매칭
	static boolean dfs(int from) {

		// 현재 상어가 먹을 수 있는 상어들을 탐색
		for (int i = 0; i < edge[from].size(); i++) {
			int to = edge[from].get(i);

			// 이미 먹혔다면 넘어감
			if (eaten[to])
				continue;

			eaten[to] = true; // 냠냠

			// to가 누구에게 먹히는지 재정립
			if (match[to] == -1 || dfs(match[to])) {
				match[to] = from;
				return true; // 먹었다면 true
			}
		}

		return false; // 아무도 못먹었다면 false
	}

}