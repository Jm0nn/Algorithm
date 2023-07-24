import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 목표 층으로 이동할 때 엘리베이터를 이용하는 횟수의 최솟값을 구하는 문제
public class Main {
	
	static int F, S, G, U, D;
	static boolean[] visit = new boolean[1_000_001];
	
	static class State {
		int pos;
		int cnt;
		
		public State(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int cnt = bfs();
		
		if (cnt == -1)
			System.out.println("use the stairs");
		else
			System.out.println(cnt);
		
		br.close();
	}
	
	static int bfs() {
		int[] move = { U, -D };
		
		Queue<State> queue = new LinkedList<>();
		queue.add(new State(S, 0));
		visit[S] = true;
		
		while (!queue.isEmpty()) {
			State cur = queue.poll();
			
			if (cur.pos == G)
				return cur.cnt;
			
			for (int i = 0; i < 2; i++) {
				int npos = cur.pos + move[i];
				int ncnt = cur.cnt + 1;
				
				if ((1 <= npos && npos <= F) && !visit[npos]) {
					visit[npos] = true;
					queue.add(new State(npos, ncnt));
				}
			}
		}
		
		return -1;
	}
}