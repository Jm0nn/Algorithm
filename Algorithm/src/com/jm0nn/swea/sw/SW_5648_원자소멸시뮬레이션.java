package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5648_원자소멸시뮬레이션 {

	static final int[][] deltas = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static int n;
	static Atom[][] field;
	static Atom[] atoms;

	static class Atom {
		int x, y;
		int d;
		int k;

		public Atom(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());

			field = new Atom[4001][4001];
			atoms = new Atom[n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				if (x < 0)
					x += 4001;
				if (y < 0)
					y += 4001;

				atoms[i] = new Atom(x, y, d, k);
				field[x][y] = new Atom(x, y, d, k);
			}

		}

		System.out.println(sb);
	}
}
