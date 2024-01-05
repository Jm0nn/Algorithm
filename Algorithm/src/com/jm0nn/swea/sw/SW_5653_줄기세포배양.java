package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_5653_줄기세포배양 {

	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n, m, k;
	static boolean[][] isCell;
	static List<Cell> cells;

	static class Cell {
		int r, c;
		int vital;
		int time;

		Cell(int r, int c, int vital) {
			this.r = r;
			this.c = c;
			this.vital = vital;

			this.time = -1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			isCell = new boolean[1001][1001];
			cells = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num > 0) {
						cells.add(new Cell(i, j, num));
						isCell[i][j] = true;
					}
				}
			}

			for (Cell cell : cells)
				cell.time = 0;

			cells.sort((o1, o2) -> o2.vital - o1.vital);

			while (k-- > 0) {
				// 번식 단계
				int cellCnt = cells.size();
				for (int i = 0; i < cellCnt; i++) {
					if (cells.get(i).time != cells.get(i).vital)
						continue;

					for (int d = 0; d < 4; d++) {
						int nr = cells.get(i).r + deltas[d][0];
						int nc = cells.get(i).c + deltas[d][1];

						if (nr < 0)
							nr += 1001;
						else if (nr > 1000)
							nr -= 1001;

						if (nc < 0)
							nc += 1001;
						else if (nc > 1000)
							nc -= 1001;

						if (isCell[nr][nc])
							continue;

						isCell[nr][nc] = true;
						cells.add(new Cell(nr, nc, cells.get(i).vital));
					}
				}

				cells.sort((o1, o2) -> o2.vital - o1.vital);

				// 시간 흐름 단계
				for (int i = cells.size() - 1; i >= 0; i--) {
					cells.get(i).time++;

					if (cells.get(i).time == cells.get(i).vital * 2)
						cells.remove(i);
				}
			}

			sb.append(cells.size()).append('\n');
		}

		System.out.println(sb);
	}

}
