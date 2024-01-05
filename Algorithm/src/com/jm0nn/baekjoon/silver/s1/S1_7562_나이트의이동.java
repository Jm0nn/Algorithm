package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_7562_나이트의이동 {
	
	// 나이트가 이동하는 좌표
	static final int[][] deltas = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

	static int I; // 체스판의 크기
	static boolean[][] board; // 체스판 배열
	static int sx, sy, fx, fy; // 출발 좌표, 도착 좌표

	// 현재 위치를 나타내는 클래스
	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			I = Integer.parseInt(br.readLine());
			board = new boolean[I][I];

			StringTokenizer st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			fx = Integer.parseInt(st.nextToken());
			fy = Integer.parseInt(st.nextToken());
			
			sb.append(bfs()).append('\n'); // 최종 좌표까지 몇 번 이동하는지 구하는 메서드
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static int bfs() {
		int count = 0; // 이동 횟수
		
		Queue<Pos> queue = new LinkedList<>();
		
		board[sx][sy] = true; // 시작 좌표 방문 확인
		queue.add(new Pos(sx, sy, 0));
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			int ncnt = cur.cnt; // 현재 이동 횟수
			
			// 현재 좌표가 도착 좌표라면 현재 이동 횟수 저장 후 반복문 탈출
			if (cur.x == fx && cur.y == fy) {
				count = ncnt;
				break;
			}
			
			for (int d = 0; d < deltas.length; d++) {
				int nx = cur.x + deltas[d][0]; // 새로운 좌표
				int ny = cur.y + deltas[d][1];
				
				// 새로운 좌표가 체스판을 벗어나지 않고 방문하지 않았다면
				if ((0 <= nx && nx < I) && (0 <= ny && ny < I) && !board[nx][ny]) {
					board[nx][ny] = true; // 방문 확인 후 이동 횟수 증가하여 큐에 넣음
					queue.add(new Pos(nx, ny, ncnt + 1));
				}
			}
		}
		
		return count;
	}
}
