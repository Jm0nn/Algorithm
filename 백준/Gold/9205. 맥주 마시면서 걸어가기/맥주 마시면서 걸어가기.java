import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 집에서 페스티벌장까지 맥주를 마시면서 도착할 수 있는지 구하는 문제
public class Main {
	
	static int n;	// 편의점의 갯수
	static Pos[] point;	// 집, 편의점, 페스티벌 장소 좌표 지정 배열
	static boolean[] visit;	// 방문 확인
	
	static StringBuilder sb = new StringBuilder();
	
	static class Pos {	// 각 지점의 좌표를 나타내는 클래스
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());	// 테스트 케이스의 갯수
		
		while (t-- > 0) {	// 테스트 케이스가 0이 될 때 까지 반복
			n = Integer.parseInt(br.readLine());
			point = new Pos[n + 2];	// 집, 편의점, 페스티벌 장소 모두 포함한 배열 생성
			visit = new boolean[n + 2];
			
			// 집의 좌표를 인덱스 0에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			point[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			// 편의점의 좌표를 인덱스 1부터 n까지 저장
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				point[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 페스티벌 장소의 좌표를 인덱스 n+1에 저장
			st = new StringTokenizer(br.readLine());
			point[n + 1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			/*
			 * 페스티벌 장소에 맥주가 떨어지지 않을 때까지 도착할 수 있는지
			 * 확인하기 위해 넓이 우선 탐색
			 */
			bfs();
		}
		
		System.out.println(sb);	// 모든 테스트 케이스 결과 한번에 출력
		
		br.close();
	}
	
	static void bfs() {
		Pos home = point[0];	// 집의 좌표
		Pos festival = point[n + 1];	// 페스티벌 장소의 좌표
		
		Queue<Pos> queue = new LinkedList<>();
		queue.add(home);	// 집의 좌표 큐에 저장
		visit[0] = true;	// 집 방문 확인
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			if (distance(cur, festival) == 0) {	// 현재 위치가 페스티벌 장소라면
				sb.append("happy").append('\n');	// 해피 출력 후 리턴
				return;
			}
			
			for (int i = 1; i < n + 2; i++) {	// 전체 좌표를 탐색
				/*
				 * 맥주의 최대 갯수 20개, 맥주 한개당 50을 갈 수 있으므로 갈 수 있는 최대 거리는 1000
				 * 현재 위치와 새로운 위치의 거리가 1000 이하이고 방문하지 않았을 때
				 */
				if (distance(cur, point[i]) <= 1000 && !visit[i]) {
					visit[i] = true;	// 방문 확인
					queue.add(point[i]);	// 큐에 저장
				}
			}
		}
		
		sb.append("sad").append('\n');	// 페스티벌 장소에 도달하지 못하면 sad 출력 후 리턴
	}
	
	static int distance(Pos p1, Pos p2) {	// 두 지점의 거리를 구하는 메서드
		// x좌표의 차이와 y좌표의 차이의 합을 리턴(맨해튼 거리)
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}