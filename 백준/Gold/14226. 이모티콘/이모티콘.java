import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// s개의 이모티콘을 화면에 만드는 데 걸리는 시간의 최솟값을 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int s = Integer.parseInt(br.readLine()); // 원하는 길이

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[s + 1][s + 1]; // 방문 배열 (현재 길이, 복사한 길이)

		q.offer(new int[] { 1, 0, 0 }); // 길이, 걸린 시간, 복사한 길이
		visit[1][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			// 원하는 길이에 도달하면 걸린 시간 출력
			if (cur[0] == s) {
				System.out.println(cur[1]);
				break;
			}

			// 이모티콘 복사해서 클립보드에 저장
			if (cur[0] != cur[2] && !visit[cur[0]][cur[0]]) {
				q.offer(new int[] { cur[0], cur[1] + 1, cur[0] });
				visit[cur[0]][cur[0]] = true;
			}

			// 클립보드에 있는 이모티콘 붙여넣기
			if (cur[2] != 0 && cur[0] + cur[2] <= s && !visit[cur[0] + cur[2]][cur[2]]) {
				q.offer(new int[] { cur[0] + cur[2], cur[1] + 1, cur[2] });
				visit[cur[0] + cur[2]][cur[2]] = true;
			}

			// 이모티콘 하나 삭제
			if (cur[0] - 1 >= 0 && !visit[cur[0] - 1][cur[2]]) {
				q.offer(new int[] { cur[0] - 1, cur[1] + 1, cur[2] });
				visit[cur[0] - 1][cur[2]] = true;
			}
		}
	}

}