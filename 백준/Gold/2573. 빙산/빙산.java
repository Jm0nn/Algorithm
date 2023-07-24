import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	
	static int N, M, mapSize, year, visitCount;
	static int[][] map;
	static boolean[][] visit;
	
	static class Pos {
		int x, y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mapSize = N * M;
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 0) {
					visit[i][j] = true;
					visitCount++;
				}
				else
					map[i][j] = n;
			}
		}
		
		while (true) {
			if (bfs())
				break;
			
			if (!afterYear()) {	// 1년이 지난 후 빙산이 다 녹았으면
				year = 0;	// year에 0 반환 후
				break;	// 탈출
			}
		}
		
		System.out.println(year);
		
		br.close();
	}
	
	static boolean bfs() {
		Queue<Pos> queue = new LinkedList<>();
		
		Loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					visit[i][j] = true;
					visitCount++;
					queue.add(new Pos(i, j));
					break Loop;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + deltas[d][0];
				int ny = cur.y + deltas[d][1];
				
				if (((0 <= nx && nx < N) && (0 <= ny && ny < M)) && !visit[nx][ny]) {
					visit[nx][ny] = true;
					visitCount++;
					queue.add(new Pos(nx, ny));
				}
			}
		}
		
		if (visitCount == mapSize)	// 한 덩어리면 false 반환
			return false;
		else	// 한 덩어리가 아니면 true 반환
			return true;
	}
	
	static boolean afterYear() {
		int[][] melt = new int[N][M];
		visitCount = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++) {
						int ni = i + deltas[d][0];
						int nj = j + deltas[d][1];
						
						if (((0 <= ni && ni < N) && (0 <= nj && nj < M)) && map[ni][nj] == 0)
							melt[i][j]++;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					map[i][j] -= melt[i][j];
				
				if (map[i][j] > 0)
					visit[i][j] = false;
				else {
					map[i][j] = 0;
					visit[i][j] = true;
					visitCount++;
				}
			}
		}
		
		year++;
		
		if (visitCount == mapSize)	// 빙산이 다 녹았으면 false 반환
			return false;
		else	// 아니면 true 반환
			return true;
	}
}