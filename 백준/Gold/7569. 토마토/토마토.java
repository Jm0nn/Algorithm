import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상자 내 토마토가 모두 썩는 최소 일수를 구하는 문제
public class Main {
	
	// 상하좌우앞뒤 이동
	static final int[][] deltas = { { -1, 0, 0 }, { 0, -1, 0 }, { 0, 0, -1 },
			{ 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
	
	static int M, N, H, maxDay, cnt;	// 상자의 크기, 걸린 일수, 방문 수
	static Tomato[][][] box;	// 박스 정보
	
	static Queue<Tomato> queue = new LinkedList<>();
	
	// 토마토 정보를 나타내는 클래스
	static class Tomato {
		int state;	// 토마토의 상태
		int x, y, z;	// 토마토의 위치
		int day;	// 익는데 걸린 시간
		boolean visit;	// 방문 정보
		
		public Tomato(int state, int x, int y, int z) {
			this.state = state;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new Tomato[M][N][H];
		
		for (int z = 0; z < H; z++) {
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					int state = Integer.parseInt(st.nextToken());
					box[x][y][z] = new Tomato(state, x, y, z);
					
					if (state == -1 || state == 1) {	// 토마토가 없거나 익은 상태라면
						box[x][y][z].visit = true;	// 방문 확인
						cnt++;	// 방문 수 증가
					}
				}
			}
		}
		
		for (int z = 0; z < H; z++) {
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (box[x][y][z].state == 1)	// 익은 토마토를 큐에 추가
						queue.add(box[x][y][z]);
				}
			}
		}
		
		bfs();	// 익은 최소 일수를 구하기 위한 넓이 우선 탐색
		
		if (cnt == M * N * H)	// 상자 전체를 방문했는지 확인 후 걸린 일수 출력
			System.out.println(maxDay);
		else	// 방문하지 않은 상자가 있으면 -1 출력
			System.out.println(-1);
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static void bfs() {
		while (!queue.isEmpty()) {
			Tomato cur = queue.poll();
			
			for (int d = 0; d < 6; d++) {	// 상하좌우앞뒤 이동하며 탐색
				// 새로운 좌표 및 걸린 일수 정보
				int nx = cur.x + deltas[d][0];
				int ny = cur.y + deltas[d][1];
				int nz = cur.z + deltas[d][2];
				int nday = cur.day;
				
				// 상자 범위 안에 있거나 방문하지 않은 경우
				if ((0 <= nx && nx < M) && (0 <= ny && ny < N) && (0 <= nz && nz < H) && !box[nx][ny][nz].visit) {
					box[nx][ny][nz].visit = true;	// 방문 확인
					cnt++;	// 방문 수 증가
					box[nx][ny][nz].day = nday + 1;	// 방문 일수 증가
					maxDay = nday + 1;	// 걸린 일수 갱신
					queue.add(box[nx][ny][nz]);
				}
			}
		}
	}
}