import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//보드를 기울여 구멍에 구슬을 넣는 문제
public class Main {
	
	// 상하좌우 이동 방향
	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	
	static int N, M, count; // 보드의 크기, 이동 횟수
	static char[][] board; // 보드 정보
	static boolean[][][][] visit; // 방문 정보
	
	// 빨간 구슬, 파란 구슬의 좌표, 이동 횟수를 나타내는 클래스
	static class Marble {
		int rx, ry, bx, by, cnt;
		
		public Marble() {}

		public Marble(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	
	static Queue<Marble> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visit = new boolean[N][M][N][M];
		
		Marble start = new Marble(); // 시작 구슬
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				
				if (c == 'R') { // 보드의 좌표가 빨간 구슬이라면
					start.rx = i; // 빨간 구슬 좌표 입력
					start.ry = j;
					c = '.'; // 보드에는 빈 칸으로 표시
				} else if (c == 'B') { // 보드의 좌표가 파란 구슬이라면
					start.bx = i; // 파란 구슬 좌표 입력
					start.by = j;
					c = '.'; // 보드에는 빈 칸으로 표시
				}
				
				board[i][j] = c;
			}
		}
		
		visit[start.rx][start.ry][start.bx][start.by] = true; // 시작 지점 방문 확인
		queue.add(start); // 시작 구슬 큐에 넣기
		
		if (bfs()) // 넓이 우선 탐색으로 빨간 구슬을 구멍에 넣는다면
			System.out.println(count); // 이동 횟수 출력
		else // 넣지 못한다면
			System.out.println(-1); // -1 출력
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static boolean bfs() {
		
		while (!queue.isEmpty()) {
			Marble cur = queue.poll();
			
			count = cur.cnt + 1; // 현재 이동 횟수
			
			for (int d = 0; d < 4; d++) { // 사방으로 기울임
				int nrx = cur.rx; // 새로운 구슬들의 좌표
				int nry = cur.ry;
				int nbx = cur.bx;
				int nby = cur.by;
				
				// 빈 칸이 나오는 동안 빨간 구슬 움직임
				while (board[nrx + deltas[d][0]][nry + deltas[d][1]] == '.') {
					nrx += deltas[d][0];
					nry += deltas[d][1];
				}
				
				// 빈 칸이 나오는 동안 파란 구슬 움직임
				while (board[nbx + deltas[d][0]][nby + deltas[d][1]] == '.') {
					nbx += deltas[d][0];
					nby += deltas[d][1];
				}
				
				// 파란 구슬이 구멍과 만나면 해당 시도 넘어감
				if (board[nbx + deltas[d][0]][nby + deltas[d][1]] == 'O')
					continue;
				
				// 빨간 구슬이 구멍과 만나면 true를 리턴
				if (board[nrx + deltas[d][0]][nry + deltas[d][1]] == 'O')
					return true;
				
				// 빨간 구슬과 파란 구슬의 좌표가 같다면
				if (nrx == nbx && nry == nby) {
					// 각 구슬의 이동 거리 계산
					int dr = Math.abs(cur.rx - nrx) + Math.abs(cur.ry - nry);
					int db = Math.abs(cur.bx - nbx) + Math.abs(cur.by - nby);
					
					if (dr > db) { // 빨간 구슬이 더 많이 이동했다면 한 칸 뒤로 이동
						nrx -= deltas[d][0];
						nry -= deltas[d][1];
					} else { // 파란 구슬이 더 많이 이동했다면 한 칸 뒤로 이동
						nbx -= deltas[d][0];
						nby -= deltas[d][1];
					}
				}
				
				if (!visit[nrx][nry][nbx][nby]) { // 해당 좌표에 방문하지 않았다면
					visit[nrx][nry][nbx][nby] = true; // 방문 확인 후 큐에 넣음
					queue.add(new Marble(nrx, nry, nbx, nby, count));
				}
			}
			
		}
		
		return false; // 탐색을 마치도록 빨간 구슬이 구멍에 들어가지 못했으므로 false 리턴
	}
}