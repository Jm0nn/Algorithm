package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임 {

	static int win, lose;
	static int[] card1, card2, compare;
	static boolean[] used, visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			card1 = new int[9];
			card2 = new int[9];
			compare = new int[9];
			used = new boolean[19];
			visit = new boolean[9];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				card1[i] = num;
				used[num] = true;
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (used[i])
					continue;

				card2[idx++] = i;
			}

			win = 0;
			lose = 0;

			do {
				int wpoint = 0;
				int lpoint = 0;

				for (int i = 0; i < 9; i++) {
					if (card1[i] > card2[i])
						wpoint += card1[i] + card2[i];
					else
						lpoint += card1[i] + card2[i];
				}

				if (wpoint > lpoint)
					win++;
				else
					lose++;
			} while (np());

//			permutation(0);

			sb.append(win).append(' ').append(lose).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void permutation(int cnt) {
		if (cnt == 9) {
			int wpoint = 0;
			int lpoint = 0;

			for (int i = 0; i < 9; i++) {
				if (card1[i] > compare[i])
					wpoint += card1[i] + compare[i];
				else
					lpoint += card1[i] + compare[i];
			}

			if (wpoint > lpoint)
				win++;
			else
				lose++;

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			compare[cnt] = card2[i];
			permutation(cnt + 1);
			visit[i] = false;
		}
	}

	static boolean np() {
		int top = 9;
		int i = top - 1;

		while (i > 0 && card2[i - 1] >= card2[i])
			i--;

		if (i == 0)
			return false;

		int j = top - 1;

		while (card2[i - 1] >= card2[j])
			j--;

		swap(i - 1, j);

		int k = top - 1;

		while (i < k)
			swap(i++, k--);

		return true;
	}

	static void swap(int a, int b) {
		int tmp = card2[a];
		card2[a] = card2[b];
		card2[b] = tmp;
	}

}
