import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 높이가 1, 길이가 L인 경사로를 이용하여 지나갈 수 있는 길을 만들 수 있는 개수를 구하는 문제
public class Main {

	static int n, l, cnt; // 지도의 크기, 경사로의 길이, 지나갈 수 있는 길의 개수
	static int[] road; // 선택한 길 정보
	static int[][] field; // 지도 정보

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		road = new int[n];
		field = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				field[i][j] = Integer.parseInt(st.nextToken());
		}

		cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				road[j] = field[i][j];
			getRoad();
		}

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++)
				road[i] = field[i][j];
			getRoad();
		}

		System.out.println(cnt);
	}

	// 선택한 길이 지나갈 수 있는 길인지 확인
	static void getRoad() {
		int max = road[0]; // 길의 최대 높이
		int min = road[0]; // 길의 최소 높이

		for (int i = 1; i < n; i++) {
			if (Math.abs(road[i] - road[i - 1]) > 1)
				return; // 높이차가 2 이상이면 불가능한 길

			if (max < road[i])
				max = road[i];
			if (min > road[i])
				min = road[i];
		}

		// 높이가 일정하면 가능한 길
		if (max == min) {
			cnt++;
			return;
		}

		int h = min; // 현재 높이

		while (h < max) {
			int start = 0; // 현재 높이 구간의 시작 지점
			int end = 0; // 현재 높이 구간의 끝 지점

			while (start < n) {
				// 현재 높이 구간 설정
				if (road[start] != h) {
					start++;
					end++;
					continue;
				}

				for (int i = start + 1; i < n; i++) {
					if (road[i] == h)
						end = i;
					else
						break;
				}

				// 현재 높이 구간의 길이가 경사로를 깔 수 있는 길이인지 확인
				if (start - 1 >= 0 && road[start - 1] == h + 1) {
					if (end - start + 1 < l)
						return;
				}

				if (end + 1 < n && road[end + 1] == h + 1) {
					if (end - start + 1 < l)
						return;
				}

				if (start - 1 >= 0 && end + 1 < n && road[start - 1] == h + 1 && road[end + 1] == h + 1) {
					if (end - start + 1 < l * 2)
						return;
				}

				start = ++end;
			}

			h++;
		}

		cnt++;
	}
}