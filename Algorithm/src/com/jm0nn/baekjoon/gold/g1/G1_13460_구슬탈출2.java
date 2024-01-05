package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Arrays;
import java.util.StringTokenizer;

// 보드를 기울여 구멍에 구슬을 넣는 문제
public class G1_13460_구슬탈출2 {

	static final int MAX_TRIAL = 10;	// 보드를 움직일 수 있는 최대 횟수

	static final int[] moveX = { -1, 0, 1, 0 };	// X축으로 움직이는 방향
	static final int[] moveY = { 0, -1, 0, 1 };	// Y축으로 움직이는 방향

	static int N, M;	// 보드의 크기
	static char[][] board;	// 보드 정보
	static boolean[][][][] visit;	// 구슬의 위치에 따른 방문 체크

	static int irx, iry, ibx, iby;	// 빨간 구슬, 파란 구슬의 초기 좌표
	static int count;	// 보드를 기울인 횟수

	// 구슬의 정보
	static class Marble {
		int rx;	// 빨간 구슬의 현재 X축 좌표
		int ry;	// 빨간 구슬의 현재 Y축 좌표
		int bx;	// 파란 구슬의 현재 X축 좌표
		int by;	// 파란 구슬의 현재 Y축 좌표
		int count;	// 구슬이 움직인 횟수 (보드를 기울인 횟수)

		public Marble(int rx, int ry, int bx, int by, int count) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visit = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);

				if (c == 'R') {	// 빨간 구슬의 초기 좌표
					irx = i;
					iry = j;
					c = '.';	// 보드에는 빈 칸으로 입력
				} else if (c == 'B') {	// 파란 구슬의 초기 좌표
					ibx = i;
					iby = j;
					c = '.';	// 보드에는 빈 칸으로 입력
				}

				board[i][j] = c;	// 보드 정보 입력
			}
		}

		bfs();	// 최솟값 계산을 위한 너비 우선 탐색

		// 10회 이동 이하로 빨간 구슬만 빼지 못할 경우
		if (count == 0 || count > MAX_TRIAL)
			count = -1;

		System.out.println(count);

		br.close();
	}

	// 너비 우선 탐색
	static void bfs() {
		Queue<Marble> queue = new LinkedList<>();
		queue.add(new Marble(irx, iry, ibx, iby, 0));

		while (!queue.isEmpty()) {
			Marble now = queue.poll();

			if (now.count > MAX_TRIAL) {	// 10번을 초과해서 움직였을 때
				return;	// 실패로 간주하고 함수 종료
			}

			for (int i = 0; i < 4; i++) {	// 상하좌우로 기울이는 동작
				int nrx = now.rx;
				int nry = now.ry;
				int nbx = now.bx;
				int nby = now.by;
				int ncount = now.count;

				// 파란 구슬이 벽을 만날 때까지 기울이기
				while (board[nbx + moveX[i]][nby + moveY[i]] != '#') {
					nbx += moveX[i];
					nby += moveY[i];

					// 구멍을 만나면 기울이기 중단
					if (board[nbx][nby] == 'O')
						break;
				}

				// 빨간 구슬이 벽을 만날 때까지 기울이기
				while (board[nrx + moveX[i]][nry + moveY[i]] != '#') {
					nrx += moveX[i];
					nry += moveY[i];

					// 구멍을 만나면 기울이기 중단
					if (board[nrx][nry] == 'O')
						break;
				}

				// 파란 구슬이 구멍에 들어가면 해당 방향으로 기울이기 중단
				if (board[nbx][nby] == 'O')
					continue;

				// 빨간 구슬만 구멍에 들어가면 최솟값 반환 후 함수 종료
				if (board[nrx][nry] == 'O') {
					count = now.count + 1;
					return;
				}

				// 움직임이 끝난 후 빨간 구슬과 파란 구슬의 좌표가 같을 때
				if (nrx == nbx && nry == nby) {
					// 각 구슬이 움직인 거리 계산
					int red = Math.abs(nrx - now.rx) + Math.abs(nry - now.ry);
					int blue = Math.abs(nbx - now.bx) + Math.abs(nby - now.by);

					if (red > blue) {	// 빨간 구슬이 뒤에 있는 경우
						nrx -= moveX[i];
						nry -= moveY[i];
					} else {	// 파란 구슬이 뒤에 있는 경우
						nbx -= moveX[i];
						nby -= moveY[i];
					}
				}

				// 빨간 구슬과 파란 구슬의 좌표가 방문한 적이 없던 좌표일 때
				if (!visit[nrx][nry][nbx][nby]) {
					visit[nrx][nry][nbx][nby] = true;	// 방문 체크 후 큐에 추가
					queue.add(new Marble(nrx, nry, nbx, nby, ncount + 1));
				}
			}
		}
	}

//	static final int MAX_TRIAL = 10;
//	
//	static int N;
//	static int M;
//	static boolean goalRed;
//	static boolean goalBlue;
//	static int count;
//	static int minCount = 11;
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		char[][] board = new char[N][M];
//		
//		for (int i = 0; i < N; i++) {
//			String s = br.readLine();
//			for (int j = 0; j < M; j++)
//				board[i][j] = s.charAt(j);
//		}
//		
//		move(board);
//		
//		if (minCount > MAX_TRIAL)
//			System.out.println(-1);
//		else
//			System.out.println(minCount);
//		
//		br.close();
//	}
//	
//	static void move(char[][] b) {
//		if (count > MAX_TRIAL) {
//			goalRed = false;
//			goalBlue = false;
//			return;
//		}
//		
//		if (goalRed && !goalBlue) {
//			minCount = Math.min(minCount, count);
//			goalRed = false;
//			return;
//		} else if (goalBlue) {
//			goalRed = false;
//			goalBlue = false;
//			return;
//		}
//		
//		count++;
//		
//		for (int i = 0; i < 4; i++) {
//			switch (i) {
//			case 0:
//				move(tiltUp(b));
//				break;
//			case 1:
//				move(tiltDown(b));
//				break;
//			case 2:
//				move(tiltLeft(b));
//				break;
//			case 3:
//				move(tiltRight(b));
//				break;
//			}
//		}
//		
//		count--;
//	}
//	
//	static char[][] tiltUp(char[][] b) {
//		
//		char[][] board = copyBoard(b);
//		
//		for (int i = 2; i < N - 1; i++) {
//			for (int j = 1; j < M - 1; j++) {
//				if (board[i][j] == 'R' || board[i][j] == 'B') {
//					int ni = i;
//					
//					while (board[ni - 1][j] == '.') {
//						board[ni - 1][j] = board[ni][j];
//						board[ni--][j] = '.';
//					}
//					
//					if (board[ni - 1][j] == 'O') {
//						if (board[ni][j] == 'R')
//							goalRed = true;
//						else
//							goalBlue = true;
//						
//						board[ni][j] = '.';
//					}
//				}
//			}
//		}
//		
//		if (!isSame(board, b))
//			goalBlue = true;
//		
//		return board;
//	}
//	
//	static char[][] tiltDown(char[][] b) {
//		
//		char[][] board = copyBoard(b);
//		
//		for (int i = N - 3; i > 0; i--) {
//			for (int j = 1; j < M - 1; j++) {
//				if (board[i][j] == 'R' || board[i][j] == 'B') {
//					int ni = i;
//					
//					while (board[ni + 1][j] == '.') {
//						board[ni + 1][j] = board[ni][j];
//						board[ni++][j] = '.';
//					}
//					
//					if (board[ni + 1][j] == 'O') {
//						if (board[ni][j] == 'R')
//							goalRed = true;
//						else
//							goalBlue = true;
//						
//						board[ni][j] = '.';
//					}
//				}
//			}
//		}
//		
//		if (!isSame(board, b))
//			goalBlue = true;
//		
//		return board;
//	}
//	
//	static char[][] tiltLeft(char[][] b) {
//		
//		char[][] board = copyBoard(b);
//		
//		for (int j = 2; j < M - 1; j++) {
//			for (int i = 1; i < N - 2; i++) {
//				if (board[i][j] == 'R' || board[i][j] == 'B') {
//					int nj = j;
//					
//					while (board[i][nj - 1] == '.') {
//						board[i][nj - 1] = board[i][nj];
//						board[i][nj--] = '.';
//					}
//					
//					if (board[i][nj - 1] == 'O') {
//						if (board[i][nj] == 'R')
//							goalRed = true;
//						else
//							goalBlue = true;
//						
//						board[i][nj] = '.';
//					}
//				}
//			}
//		}
//		
//		if (!isSame(board, b))
//			goalBlue = true;
//		
//		return board;
//	}
//	
//	static char[][] tiltRight(char[][] b) {
//		
//		char[][] board = copyBoard(b);
//		
//		for (int j = M - 3; j > 0; j--) {
//			for (int i = 1; i < N - 1; i++) {
//				if (board[i][j] == 'R' || board[i][j] == 'B') {
//					int nj = j;
//					
//					while (board[i][nj + 1] == '.') {
//						board[i][nj + 1] = board[i][nj];
//						board[i][nj++] = '.';
//					}
//					
//					if (board[i][nj + 1] == 'O') {
//						if (board[i][nj] == 'R')
//							goalRed = true;
//						else
//							goalBlue = true;
//						
//						board[i][nj] = '.';
//					}
//				}
//			}
//		}
//		
//		if (!isSame(board, b))
//			goalBlue = true;
//		
//		return board;
//	}
//	
//	static char[][] copyBoard(char[][] b) {
//		char[][] copy = new char[N][M];
//		
//		for (int i = 0; i < N; i++)
//			copy[i] = Arrays.copyOf(b[i], M);
//		
//		return copy;
//	}
//	
//	static boolean isSame(char[][] b, char[][] c) {
//		boolean stayR = false;
//		boolean stayB = false;
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				if (b[i][j] == 'R' && c[i][j] == 'R')
//					stayR = true;
//				else if (b[i][j] == 'B' && c[i][j] == 'B')
//					stayB = true;
//			}
//		}
//		
//		if (stayR && stayB)
//			return false;
//		else
//			return true;
//	}
}
