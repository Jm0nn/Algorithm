package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class G4_2239_스도쿠 {

	static boolean finish;
	static int[][] sudoku = new int[9][9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; ++i) {
			String input = br.readLine();
			for (int j = 0; j < 9; ++j)
				sudoku[i][j] = input.charAt(j) - '0';
		}

		solve(0, 0);
	}

	static void solve(int x, int y) {
		if (x == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; ++i) {
				for (int j = 0; j < 9; ++j)
					sb.append(sudoku[i][j]);
				sb.append('\n');
			}
			System.out.println(sb);
			finish = true;
			return;
		}

		if (y == 9) {
			solve(x + 1, 0);
			return;
		}

		if (sudoku[x][y] > 0) {
			solve(x, y + 1);
			return;
		}

		for (int i = 1; i <= 9; ++i) {
			sudoku[x][y] = i;
			if (isValid(x, y))
				solve(x, y + 1);
			if (finish)
				return;
		}
		sudoku[x][y] = 0;
	}

	static boolean isValid(int x, int y) {
		int val = sudoku[x][y];

		for (int i = 0; i < 9; ++i) {
			if (i != y && sudoku[x][i] == val)
				return false;

			if (i != x && sudoku[i][y] == val)
				return false;
		}

		int gridx = (x / 3) * 3;
		int gridy = (y / 3) * 3;

		for (int i = gridx; i < gridx + 3; ++i)
			for (int j = gridy; j < gridy + 3; ++j)
				if (i != x && j != y && sudoku[i][j] == val)
					return false;

		return true;
	}

}
