package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 영역이 물에 잠겼을 때 안전한 영역의 최대 갯수를 구하는 문제
public class S1_2468_안전영역 {
	
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };	// 상하좌우 이동
	
	static int N, maxH, maxCount;	// 배열의 크기, 영역의 최대 높이, 영역의 최대 갯수
	static int[][] map;	// 영역 정보
	static boolean[][] visit;	// 방문 정보
	
	static class Pos {	// 현재 위치 정보에 대한 클래스
		int x, y;	// 현재 위치 좌표
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int t = Integer.parseInt(st.nextToken());
				map[i][j] = t;
				maxH = Math.max(maxH, t);	// 영역의 최대 높이를 구함
			}
		}
		
		for (int i = 0; i < maxH; i++)	// 물의 높이가 0에서부터 (최대 높이 - 1) 일때까지 넓이 우선 탐색
			maxCount = Math.max(maxCount, bfs(i));	// 기존 영역의 최대 갯수와 새로운 영역의 갯수를 비교하여 최댓값을 넘김
		
		System.out.println(maxCount);
		
		br.close();
	}
	
	// 물의 높이가 h일 때 영역의 갯수를 반환하는 넓이 우선 탐색 메서드
	static int bfs(int h) {
		// 방문 정보 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] - h > 0)	// 영역이 물보다 높이 있을 경우 방문 하지 않음
					visit[i][j] = false;
				else	// 영역이 물과 높이가 같거나 낮을 경우 방문 한 것으로 간주
					visit[i][j] = true;
			}
		}
		
		Queue<Pos> queue = new LinkedList<>();
		
		int count = 0;	// 영역의 갯수
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {	// 영역 전체를 돌며 방문하지 않은 좌표를 큐에 넣음
					queue.add(new Pos(i, j));
					visit[i][j] = true;
					
					while (!queue.isEmpty()) {
						Pos cur = queue.poll();
						
						for (int d = 0; d < 4; d++) {	// 상하좌우 새로운 좌표 설정
							int nx = cur.x + deltas[d][0];
							int ny = cur.y + deltas[d][1];
							
							// 새로운 좌표가 범위를 벗어나지 않고 방문하지 않은 경우
							if (((0 <= nx && nx < N) && (0 <= ny && ny < N)) && !visit[nx][ny]) {
								visit[nx][ny] = true;	// 방문 확인 후 큐에 넣음
								queue.add(new Pos(nx, ny));
							}
						}
					}
					
					count++;	// 영역 한 개를 다 돌면 영역의 갯수 증가
				}
			}
		}
		
		return count;	// 영역의 갯수를 반환
	}
}
