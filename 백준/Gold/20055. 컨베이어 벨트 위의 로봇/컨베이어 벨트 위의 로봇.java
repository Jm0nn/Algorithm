import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 컨베이어 벨트에 로봇을 올리고 내릴 때 벨트의 내구도가 0이 된 개수가 일정 이상이 될 때를 구하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 컨베이어 벨트의 윗쪽 칸 수
		int k = Integer.parseInt(st.nextToken()); // 종료 조건

		int[] cb = new int[n * 2 + 1]; // 컨베이어 벨트 배열
		boolean[] robot = new boolean[n * 2 + 1]; // 로봇 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2 * n; i++)
			cb[i] = Integer.parseInt(st.nextToken());

		int stage = 0; // 현재 단계
		int broken = 0; // 내구도가 0인 칸의 개수
		int start = 1; // 로봇 올리는 칸
		int end = n; // 로봇 내리는 칸

		while (true) {
			stage++;

			// 벨트 회전
			if (--start == 0)
				start = n * 2;
			if (--end == 0)
				end = n * 2;

			// 로봇 벨트와 같이 회전 및 이동
			if (start < end) {
				for (int i = end; i >= start; i--) {
					if (!robot[i])
						continue;

					// 현재 위치가 로봇 내리는 칸이면 내림
					if (i == end) {
						robot[i] = false;
						continue;
					}

					int next = i + 1; // 로봇 이동할 칸

					// 이동할 칸 내구도가 1 이상이거나 로봇이 없는 칸이면 이동
					if (cb[next] == 0 || robot[next])
						continue;

					robot[i] = false;

					// 이동 후 내구도 감소
					if (--cb[next] == 0)
						broken++;

					// 이동한 곳이 로봇 내리는 칸이면 내림
					if (next != end)
						robot[next] = true;
				}
			} else {
				for (int i = end; i >= 1; i--) {
					if (!robot[i])
						continue;

					if (i == end) {
						robot[i] = false;
						continue;
					}

					int next = i + 1;

					if (cb[next] == 0 || robot[next])
						continue;

					robot[i] = false;

					if (--cb[next] == 0)
						broken++;

					if (next != end)
						robot[next] = true;
				}

				for (int i = n * 2; i >= start; i--) {
					if (!robot[i])
						continue;

					int next = i + 1;
					if (next == n * 2 + 1)
						next = 1;

					if (cb[next] == 0 || robot[next])
						continue;

					robot[i] = false;

					if (--cb[next] == 0)
						broken++;

					if (next != end)
						robot[next] = true;
				}
			}

			// 로봇 올리는 칸 내구도가 남아있으면 로봇 올림
			if (cb[start] > 0) {
				robot[start] = true;

				// 올린 후 내구도 감소
				if (--cb[start] == 0)
					broken++;
			}

			// 내구도가 0인 칸이 k개 이상이면 종료
			if (broken >= k)
				break;
		}

		System.out.println(stage);
	}
}