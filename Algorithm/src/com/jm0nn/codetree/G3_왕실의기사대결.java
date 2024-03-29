package com.jm0nn.codetree;

import java.io.*;
import java.util.*;

public class G3_왕실의기사대결 {

	private static class Knight {

		int r, c; // 기사의 위치
		int h, w; // 기사의 크기
		int k, l; // 초기 체력, 남은 체력

		public Knight(int r, int c, int h, int w, int k) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.l = k;
		}

	}

	private static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private static int L, N, Q; // 체스판 크기, 기사의 수, 명령의 수
	private static int cur; // 명령 받은 기사
	private static int[][] board; // 체스판
	private static int[][] knightPos; // 기사의 위치
	private static Knight[] knights; // 기사 정보

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		board = new int[L + 1][L + 1];
		knightPos = new int[L + 1][L + 1];
		knights = new Knight[N + 1];

		for (int i = 1; i <= L; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= L; ++j) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			knights[i] = new Knight(r, c, h, w, k);

			for (int x = r; x < r + h; ++x) {
				for (int y = c; y < c + w; ++y) {
					knightPos[x][y] = i;
				}
			}
		}

		for (int k = 0; k < Q; ++k) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			// 기사가 명령대로 이동할 수 있는지 확인
			if (moveChk(i, d)) {
				cur = i; // 명령 받은 기사 기록
				move(i, d); // 기사 이동
			}
		}

		int dmg = 0; // 살아 있는 기사들이 받은 대미지
		for (int i = 1; i <= N; ++i) {
			Knight cur = knights[i];
			if (cur.l > 0) {
				dmg += cur.k - cur.l;
			}
		}
		System.out.println(dmg);
	}

	private static boolean moveChk(int i, int d) {
		// 0번 기사(빈 공간)
		if (i == 0) {
			return true;
		}

		int[][] arr = getArr(i, d); // 이동할 위치 좌표 배열

		for (int[] a : arr) {
			int r = a[0];
			int c = a[1];

			// 이동할 위치가 체스판을 벗어나거나 벽이라면 이동할 수 없음
			if (0 >= r || r > L) {
				return false;
			} else if (0 >= c || c > L) {
				return false;
			} else if (board[r][c] == 2) {
				return false;
			}
		}

		boolean[] flag = new boolean[N + 1]; // 기사 번호 확인 배열
		flag[i] = true;
		for (int[] a : arr) {
			int r = a[0];
			int c = a[1];
			int num = knightPos[r][c];

			// 이미 확인한 기사라면 넘어감
			if (flag[num]) {
				continue;
			}

			flag[num] = true;

			// 이동할 위치의 기사가 이동할 수 있는지 확인
			if (!moveChk(num, d)) {
				return false;
			}
		}

		return true;
	}

	private static void move(int i, int d) {
		// 빈 공간
		if (i == 0) {
			return;
		}

		int[][] arr = getArr(i, d);

		// 이동할 위치에 있는 기사부터 이동
		for (int[] a : arr) {
			int r = a[0];
			int c = a[1];
			int num = knightPos[r][c];

			move(num, d);
		}

		Knight knight = knights[i];

		int r = knight.r;
		int c = knight.c;

		for (int j = r; j < r + knight.h; ++j) {
			for (int k = c; k < c + knight.w; ++k) {
				knightPos[j][k] = 0;
			}
		}

		r += deltas[d][0];
		c += deltas[d][1];

		for (int j = r; j < r + knight.h; ++j) {
			for (int k = c; k < c + knight.w; ++k) {
				knightPos[j][k] = 0;
			}
		}

		knight.r = r;
		knight.c = c;

		for (int j = r; j < r + knight.h; ++j) {
			for (int k = c; k < c + knight.w; ++k) {
				knightPos[j][k] = i;
			}
		}

		// 명령을 받은 기사라면 그대로 리턴
		if (cur == i) {
			return;
		}

		boolean dead = false; // 기사가 죽었는지 확인

		for (int j = r; j < r + knight.h; ++j) {
			for (int k = c; k < c + knight.w; ++k) {
				if (board[j][k] == 1) {
					--knight.l;

					if (knight.l <= 0) {
						dead = true;
					}
				}
			}
		}

		// 기사가 죽었다면 배열에서 제거
		if (dead) {
			for (int j = r; j < r + knight.h; ++j) {
				for (int k = c; k < c + knight.w; ++k) {
					knightPos[j][k] = 0;
				}
			}
		}
	}

	private static int[][] getArr(int i, int d) {
		Knight knight = knights[i];

		int l;
		int[][] arr;
		if (d == 0) {
			l = knight.w;
			arr = new int[l][2];
			for (int j = 0; j < l; ++j) {
				arr[j][0] = knight.r - 1;
				arr[j][1] = knight.c + j;
			}
		} else if (d == 1) {
			l = knight.h;
			arr = new int[l][2];
			for (int j = 0; j < l; ++j) {
				arr[j][0] = knight.r + j;
				arr[j][1] = knight.c + knight.w;
			}
		} else if (d == 2) {
			l = knight.w;
			arr = new int[l][2];
			for (int j = 0; j < l; ++j) {
				arr[j][0] = knight.r + knight.h;
				arr[j][1] = knight.c + j;
			}
		} else {
			l = knight.h;
			arr = new int[l][2];
			for (int j = 0; j < l; ++j) {
				arr[j][0] = knight.r + j;
				arr[j][1] = knight.c - 1;
			}
		}

		return arr;
	}

}
