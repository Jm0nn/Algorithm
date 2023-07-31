import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 배추를 키우기 위해 배추흰지렁이가 몇 마리 필요한지 구하는 문제
public class Main {
	
	// 사방 탐색
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	
	static int M, N; // 밭의 크기
	static int[][] field; // 밭 배열
	
	// 현재 위치를 나타내는 클래스
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			field = new int[M][N];
			
			int cabbage = Integer.parseInt(st.nextToken()); // 배추가 심어진 갯수
			
			while (cabbage-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				// 배추가 심어진 곳은 1로 바꿈
				field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			// 넓이 우선 탐색으로 필요한 배추흰지렁이의 갯수 구함
			sb.append(bfs()).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static int bfs() {
		int count = 0; // 배추흰지렁이의 갯수
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (field[i][j] == 1) { // 배추가 심어진 곳
					count++; // 배추흰지렁이의 갯수 증가
					
					Queue<Pos> queue = new LinkedList<>();
					
					field[i][j] = 0; // 방문 확인 후 큐에 넣음
					queue.add(new Pos(i, j));
					
					while (!queue.isEmpty()) {
						Pos cur = queue.poll();
						
						for (int d = 0; d < 4; d++) { // 사방 탐색
							int nx = cur.x + deltas[d][0]; // 새로운 좌표
							int ny = cur.y + deltas[d][1];
							
							// 새로운 좌표가 밭을 벗어나지 않고 배추가 심어진 곳이라면
							if ((0 <= nx && nx < M) && (0 <= ny && ny < N) && field[nx][ny] == 1) {
								field[nx][ny] = 0; // 방문 확인 후 큐에 넣음
								queue.add(new Pos(nx, ny));
							}
						}
					}
				}
			}
		}
		
		return count; // 배추흰지렁이의 갯수 리턴
	}
}