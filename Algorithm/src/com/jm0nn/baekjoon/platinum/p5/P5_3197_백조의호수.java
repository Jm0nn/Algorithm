package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 백조의 호수에서 빙판이 녹아 백조들이 만날 수 있는 날을 구하는 문제
public class P5_3197_백조의호수 {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int r, c; // 호수의 크기
	static char[][] lake; // 호수 배열
	static Pos swan1, swan2; // 백조의 위치
	static Pos[][] parents; // 같은 물 그룹인지 확인하기 위한 parents 배열
	static Queue<Pos> waterQueue;

	// 위치 클래스
	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		boolean equals(Pos p) {
			if (this.r == p.r && this.c == p.c)
				return true;
			else
				return false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		lake = new char[r][c];
		parents = new Pos[r][c];
		waterQueue = new ArrayDeque<>();

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				char ch = s.charAt(j);

				// 백조의 위치 입력
				if (ch == 'L') {
					if (swan1 == null)
						swan1 = new Pos(i, j);
					else
						swan2 = new Pos(i, j);

					ch = '.';
				}

				lake[i][j] = ch;
				parents[i][j] = new Pos(i, j);

				int ni = i - 1;
				int nj = j - 1;

				if (lake[i][j] == '.') {
					if (ni >= 0 && lake[ni][j] == '.')
						union(i, j, ni, j);
					if (nj >= 0 && lake[i][nj] == '.')
						union(i, j, i, nj);

					if (ni >= 0 && lake[ni][j] == 'X')
						waterQueue.offer(new Pos(i, j));
					else if (nj >= 0 && lake[i][nj] == 'X')
						waterQueue.offer(new Pos(i, j));
				} else {
					if (ni >= 0 && lake[ni][j] == '.')
						waterQueue.offer(new Pos(ni, j));
					if (nj >= 0 && lake[i][nj] == '.')
						waterQueue.offer(new Pos(i, nj));
				}
			}
		}

		int day = 0; // 걸린 일 수

		// 두 백조가 만날 때까지 반복
		while (!canReach()) {
			day++;
			melt();
		}

		System.out.println(day);
	}

	// 두 백조가 만날 수 있는지
	static boolean canReach() {
		if (find(swan1).equals(find(swan2)))
			return true;
		return false;
	}

	// 빙판 녹음
	static void melt() {
		int size = waterQueue.size();

		while (size-- > 0) {
			Pos cur = waterQueue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (0 > nr || nr >= r || 0 > nc || nc >= c)
					continue;

				if (lake[nr][nc] == 'X') {
					lake[nr][nc] = '.';
					waterQueue.offer(new Pos(nr, nc));

					for (int nd = 0; nd < 4; nd++) {
						int nnr = nr + deltas[nd][0];
						int nnc = nc + deltas[nd][1];

						if (0 > nnr || nnr >= r || 0 > nnc || nnc >= c)
							continue;

						if (lake[nnr][nnc] == '.')
							union(nr, nc, nnr, nnc);
					}
				}
			}
		}
	}

	static Pos find(Pos p) {
		if (p.equals(parents[p.r][p.c]))
			return p;

		return parents[p.r][p.c] = find(parents[p.r][p.c]);
	}

	static Pos find(int r, int c) {
		int pr = parents[r][c].r;
		int pc = parents[r][c].c;

		if (r == pr && c == pc)
			return parents[r][c];

		return parents[r][c] = find(pr, pc);
	}

	static boolean union(int r1, int c1, int r2, int c2) {
		Pos root1 = find(r1, c1);
		Pos root2 = find(r2, c2);

		if (root1.equals(root2))
			return false;

		parents[root2.r][root2.c] = root1;
		return true;
	}

}
