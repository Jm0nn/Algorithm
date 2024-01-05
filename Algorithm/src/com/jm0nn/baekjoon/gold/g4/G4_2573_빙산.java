package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 빙산이 녹으며 두 덩어리로 분리될 때까지 걸린 햇수를 구하는 문제
public class G4_2573_빙산 {
	
	// 상하좌우 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	
	static int N, M, mapSize, year, visitCount;	// 배열의 크기, 걸린 햇수, 방문 수
	static int[][] map;	// 지도 정보
	static boolean[][] visit;	// 방문 정보
	
	// 현재 위치 정보를 나타내는 클래스
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
		mapSize = N * M;	// 지도의 크기
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 0) {	// 빙산이 없는 영역
					visit[i][j] = true;	// 방문 확인
					visitCount++;	// 방문 수 증가
				}
				else
					map[i][j] = n;
			}
		}
		
		while (true) {
			if (bfs())	// 빙산의 덩어리 갯수를 구하기 위한 넓이 우선 탐색
				break;	// 빙산이 두 덩어리로 나눠질 경우 반복문 탈출
			
			if (!afterYear()) {	// 1년이 지난 후 빙산이 다 녹았으면
				year = 0;	// year에 0 반환 후
				break;	// 반복문 탈출
			}
		}
		
		System.out.println(year);
		
		br.close();
	}
	
	// 넓이 우선 탐색으로 빙산이 한 덩어리인지 아닌지 구하는 메서드
	static boolean bfs() {
		Queue<Pos> queue = new LinkedList<>();
		
		Loop: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {	// 빙산이 있는 영역
					visit[i][j] = true;	// 방문 확인
					visitCount++;	// 방문 수 증가
					queue.add(new Pos(i, j));
					break Loop;	// bfs 시작
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {	// 상하좌우 이동
				int nx = cur.x + deltas[d][0];	// 상하좌우 새로운 좌표
				int ny = cur.y + deltas[d][1];
				
				// 새로운 좌표가 배열을 벗어나지 않으며 방문하지 않았을 경우
				if (((0 <= nx && nx < N) && (0 <= ny && ny < M)) && !visit[nx][ny]) {
					visit[nx][ny] = true;	// 방문 확인
					visitCount++;	// 방문 수 증가
					queue.add(new Pos(nx, ny));	// 큐에 넣음
				}
			}
		}
		
		if (visitCount == mapSize)	// 빙산이 한 덩어리면 false 반환
			return false;
		else	// 한 덩어리가 아니면 true 반환
			return true;
	}
	
	// 1년이 지난 후 배열 정보 갱신
	static boolean afterYear() {
		int[][] melt = new int[N][M];	// 빙산 영역의 상하좌우 바다의 갯수를 나타내는 배열
		visitCount = 0;	// 방문 수 초기화
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {	// 빙산이 있는 영역
					for (int d = 0; d < 4; d++) {	// 상하좌우 이동
						int ni = i + deltas[d][0];	// 상하좌우 새로운 좌표
						int nj = j + deltas[d][1];
						
						// 새로운 좌표가 배열을 벗어나지 않고 바다인 경우
						if (((0 <= ni && ni < N) && (0 <= nj && nj < M)) && map[ni][nj] == 0)
							melt[i][j]++;	// 바다의 갯수 증가
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)	// 빙산의 높이에서 바다의 갯수를 뺌
					map[i][j] -= melt[i][j];
				
				if (map[i][j] > 0)	// 빙산이 남아있으면
					visit[i][j] = false;	// 방문하지 않음
				else {	// 빙산이 다 녹으면
					map[i][j] = 0;	// 높이 0
					visit[i][j] = true;	// 방문 확인
					visitCount++;	// 방문 수 증가
				}
			}
		}
		
		year++;	// 햇수 증가
		
		if (visitCount == mapSize)	// 빙산이 다 녹았으면 false 반환
			return false;
		else	// 아니면 true 반환
			return true;
	}
}
