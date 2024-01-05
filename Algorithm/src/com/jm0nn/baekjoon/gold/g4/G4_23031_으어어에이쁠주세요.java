package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G4_23031_으어어에이쁠주세요 {

	static final int[][] deltas = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static final int[][] light = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };

	static int n;
	static int x, y, dir;
	static char[][] floor;
	static int[][][] zombie;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		floor = new char[n][n];
		zombie = new int[n][n][2];

		String move = br.readLine();

		for (int i = 0; i < n; ++i) {
			String input = br.readLine();
			for (int j = 0; j < n; ++j) {
				char c = input.charAt(j);

				if (c == 'Z') {
					++zombie[i][j][1];
					c = 'O';
				}

				floor[i][j] = c;
			}
		}

		boolean faint = false;

		int len = move.length();
		for (int i = 0; i < len; ++i) {
			char cmd = move.charAt(i);

			if (cmd == 'F') {
				if (moveAhri()) {
					faint = true;
					break;
				}
			} else if (cmd == 'L') {
				dir = (dir + 3) % 4;
			} else if (cmd == 'R') {
				dir = (dir + 1) % 4;
			}

			if (moveZombie()) {
				faint = true;
				break;
			}
		}

		System.out.println(faint ? "Aaaaaah!" : "Phew...");
	}

	static boolean moveAhri() {
		int nx = x + deltas[dir][0];
		int ny = y + deltas[dir][1];

		if (0 > nx || nx >= n || 0 > ny || ny >= n)
			return false;

		x = nx;
		y = ny;

		if (floor[x][y] == 'O') {
			if (zombie[x][y][0] > 0 || zombie[x][y][1] > 0)
				return true;
		} else if (floor[x][y] == 'S') {
			floor[x][y] = 'L';

			for (int l = 0; l < 8; ++l) {
				nx = x + light[l][0];
				ny = y + light[l][1];

				if (0 > nx || nx >= n || 0 > ny || ny >= n)
					continue;

				if (floor[nx][ny] == 'O')
					floor[nx][ny] = 'L';
			}
		}

		return false;
	}

	static boolean moveZombie() {
		int[][][] tmp = new int[n][n][2];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (zombie[i][j][0] > 0) {
					int ni = i - 1;

					if (ni < 0)
						++tmp[i][j][1];
					else
						++tmp[ni][j][0];
				}

				if (zombie[i][j][1] > 0) {
					int ni = i + 1;

					if (ni >= n)
						++tmp[i][j][0];
					else
						++tmp[ni][j][1];
				}
			}
		}

		zombie = tmp;

		if (floor[x][y] == 'O') {
			if (zombie[x][y][0] > 0 || zombie[x][y][1] > 0)
				return true;
		}

		return false;
	}

}
