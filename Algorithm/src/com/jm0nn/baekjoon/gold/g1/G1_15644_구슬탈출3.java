package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//보드를 기울여 구멍에 구슬을 넣는 문제
public class G1_15644_구슬탈출3 {
	
	// 상하좌우 이동 방향 (U, R, D, L)
	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static final String[] moveString = { "U", "R", "D", "L" };
	
	static int N, M, cnt; // 보드의 크기, 이동 횟수
	static char[][] board; // 보드 정보
	static boolean[][][][] visit; // 방문 정보
	static String finalMove; // 최종 이동 내역
	
	// 빨간 구슬, 파란 구슬의 좌표와 이동 횟수, 이동 내역을 나타내는 클래스
	static class Marble {
		int rx, ry, bx, by, cnt;
		String move;
		
		public Marble() {}

		public Marble(int rx, int ry, int bx, int by, int cnt, String move) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
			this.move = move;
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
		
		Marble start = new Marble(0, 0, 0, 0, 0, ""); // 시작 구슬
		
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
		
		if (bfs()) { // 넓이 우선 탐색으로 10회 이내 성공한다면
			System.out.println(cnt); // 1 출력
			System.out.println(finalMove); // 이동 내역 출력
		}
		else // 10회 이내로 성공하지 못한다면
			System.out.println(-1); // 0 출력
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static boolean bfs() {
		
		while (!queue.isEmpty()) {
			Marble cur = queue.poll();
			
			cnt = cur.cnt + 1; // 카운트 증가
			
			if (cnt > 10)	// 카운트가 10을 초과하면 실패로 간주하고 false 리턴
				return false;
			
			for (int d = 0; d < 4; d++) { // 사방으로 기울임
				int nrx = cur.rx; // 새로운 구슬들의 좌표
				int nry = cur.ry;
				int nbx = cur.bx;
				int nby = cur.by;
				String nmove = cur.move + moveString[d]; // 이동 내역 갱신
				
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
				
				// 빨간 구슬이 구멍과 만나면
				if (board[nrx + deltas[d][0]][nry + deltas[d][1]] == 'O') {
					finalMove = nmove; // 이동 내역 저장
					return true; // true 반환
				}
				
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
					queue.add(new Marble(nrx, nry, nbx, nby, cnt, nmove));
				}
			}
			
		}
		
		return false; // 빨간 구슬이 구멍에 들어가지 못했으므로 false 리턴
	}
}
