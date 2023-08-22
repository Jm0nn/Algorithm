import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 컨베이어 벨트의 윗쪽 칸 수
		int k = Integer.parseInt(st.nextToken()); // 종료 조건

		int[] cb = new int[n * 2 + 1]; // 컨베이어 벨트 배열
		boolean[] robot = new boolean[n]; // 로봇 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2 * n; i++)
			cb[i] = Integer.parseInt(st.nextToken());

		int stage = 0; // 현재 단계
		int broken = 0; // 내구도가 0인 칸의 개수

		while (true) {
			stage++;

			// 벨트 회전
			int[] tmp = new int[n * 2 + 1];
			for (int i = 2; i <= n * 2; i++)
				tmp[i] = cb[i - 1];
			tmp[1] = cb[n * 2];
			cb = tmp;

			// 로봇 벨트와 같이 회전
			for (int i = n - 1; i >= 1; i--) {
				if (!robot[i])
					continue;

				robot[i] = false;

				// 로봇이 N번 칸에 도달하면 내림
				if (i + 1 == n)
					continue;

				robot[i + 1] = true;
			}

			// 로봇 이동
			for (int i = n - 1; i >= 2; i--) {
				if (!robot[i])
					continue;

				// 이동하려는 칸의 내구도가 0이면 이동 안함
				// 한칸 앞에 로봇이 이미 있다면 이동 안함
				if (cb[i + 1] == 0 || (i < n - 1 && robot[i + 1]))
					continue;

				robot[i] = false;

				// 도달한 칸 내구도 감소
				if (--cb[i + 1] == 0)
					broken++;

				// 이동 후 N번 칸에 도달하면 내림
				if (i + 1 == n)
					continue;

				robot[i + 1] = true;
			}

			// 1번칸 내구도가 남아있으면 로봇 올림
			if (cb[1] > 0) {
				robot[1] = true;

				if (--cb[1] == 0)
					broken++;
			}

			// 내구도가 0인 칸이 k개 이상이면 종료
			if (broken >= k)
				break;
		}

		System.out.println(stage);
	}
}