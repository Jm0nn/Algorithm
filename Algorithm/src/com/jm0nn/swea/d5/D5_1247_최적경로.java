package com.jm0nn.swea.d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 회사에서 출발해서 고객을 방문 후 집에 돌아가는 가장 짧은 경로를 구하는 문제
public class D5_1247_최적경로 {

	static int n, min; // 고객의 수, 경로의 최솟값
	static Pos company, home; // 회사, 집의 좌표
	static Pos[] customer; // 고객의 좌표

	// 좌표 클래스
	static class Pos implements Comparable<Pos> {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		// Next Permutation을 위한 정렬 기준
		@Override
		public int compareTo(Pos o) {
			return this.r != o.r ? this.r - o.r : this.c - o.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			n = Integer.parseInt(br.readLine());
			customer = new Pos[n];

			st = new StringTokenizer(br.readLine());
			company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			for (int i = 0; i < n; i++)
				customer[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			Arrays.sort(customer);

			min = Integer.MAX_VALUE;

			do {
				int sum = 0; // 경로의 합

				// 회사와 첫번째 고객의 거리
				sum += Math.abs(company.r - customer[0].r) + Math.abs(company.c - customer[0].c);

				// 각 고객 간의 거리
				for (int i = 1; i < n; i++)
					sum += Math.abs(customer[i].r - customer[i - 1].r) + Math.abs(customer[i].c - customer[i - 1].c);

				// 마지막 고객과 집의 거리
				sum += Math.abs(home.r - customer[n - 1].r) + Math.abs(home.c - customer[n - 1].c);

				// 최솟값 갱신
				if (min > sum)
					min = sum;
			} while (np());

			sb.append(min).append('\n');
		}

		System.out.println(sb);
	}

	// 고객 순열 구하기
	static boolean np() {
		int i = n - 1;

		while (i > 0 && customer[i - 1].compareTo(customer[i]) >= 0)
			i--;

		if (i == 0)
			return false;

		int j = n - 1;

		while (customer[i - 1].compareTo(customer[j]) >= 0)
			j--;

		swap(i - 1, j);

		int k = n - 1;

		while (i < k)
			swap(i++, k--);

		return true;
	}

	static void swap(int a, int b) {
		Pos tmp = customer[a];
		customer[a] = customer[b];
		customer[b] = tmp;
	}

}
