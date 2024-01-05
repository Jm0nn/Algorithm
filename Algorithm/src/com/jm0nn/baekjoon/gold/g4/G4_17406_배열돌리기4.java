package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열을 여러번 돌려서 배열 값의 최솟값을 구하는 문제
public class G4_17406_배열돌리기4 {

	// 좌표 이동용
	static final int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static int n, m, k; // 배열의 크기, 회전 횟수
	static int min = Integer.MAX_VALUE; // 배열 값의 최솟값
	static int[][] arr; // 배열
	static Rotation[] rotation; // 회전 방법 배열

	// 회전 방법 클래스
	static class Rotation implements Comparable<Rotation> {
		int idx; // 인덱스로 대소 비교
		int r;
		int c;
		int s;

		Rotation(int idx, int r, int c, int s) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public int compareTo(Rotation o) {
			return this.idx - o.idx;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][m + 1];
		rotation = new Rotation[k];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			rotation[i] = new Rotation(i, r, c, s);
		}

		do {
			// 임시 배열
			int[][] tmp = new int[n + 1][m + 1];

			// 임시 배열 복사
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= m; j++)
					tmp[i][j] = arr[i][j];

			// 임시 배열에 회전 연산 수행
			for (int i = 0; i < k; i++)
				rot(rotation[i], tmp);

			// 배열 값 계산
			for (int i = 1; i <= n; i++) {
				int sum = 0; // 해당 행 값의 합

				for (int j = 1; j <= m; j++)
					sum += tmp[i][j];

				// 행 값의 합이 기존 최솟값보다 작다면 최솟값 갱신
				if (min > sum)
					min = sum;
			}
		} while (np());

		System.out.println(min);

		br.close();
	}

	// Next Permutation
	static boolean np() {
		int i = k - 1;

		while (i > 0 && rotation[i - 1].compareTo(rotation[i]) >= 0)
			i--;

		if (i == 0)
			return false;

		int j = k - 1;

		while (rotation[i - 1].compareTo(rotation[j]) >= 0)
			j--;

		swap(i - 1, j);

		int l = k - 1;

		while (i < l)
			swap(i++, l--);

		return true;
	}

	static void swap(int a, int b) {
		Rotation tmp = rotation[a];
		rotation[a] = rotation[b];
		rotation[b] = tmp;
	}

	// 회전 연산
	static void rot(Rotation ro, int[][] p) {
		int r = ro.r;
		int c = ro.c;
		int s = ro.s;

		// 회전할 영역의 좌표
		int sr = r - s; // 가장 왼쪽 윗칸 행
		int sc = c - s; // 가장 왼쪽 윗칸 열
		int fr = r + s; // 가장 오른쪽 아랫칸 행
		int fc = c + s; // 가장 오른쪽 아랫칸 열

		// s번 회전
		for (int i = 0; i < s; i++) {
			int nr = sr + i; // 왼쪽 윗칸 행
			int nc = sc + i; // 왼쪽 윗칸 열
			int d = 0; // 좌표 이동 방향
			int cnt = (s - i) * 8; // 회전할 원소 수

			int tmp = p[nr][nc]; // 회전을 위한 임시 변수

			for (int j = 0; j < cnt; j++) {
				// 새로운 좌표
				int nnr = nr + deltas[d][0];
				int nnc = nc + deltas[d][1];

				// 새로운 좌표가 범위를 벗어나면 방향을 바꿔서 좌표 재설정
				if (sr + i > nnr || nnr > fr - i || sc + i > nnc || nnc > fc - i) {
					d = (d + 1) % 4;
					nnr = nr + deltas[d][0];
					nnc = nc + deltas[d][1];
				}

				// 기존 좌표 값에 새로운 좌표 값을 넣고 새로운 좌표로 이동
				p[nr][nc] = p[nnr][nnc];
				nr = nnr;
				nc = nnc;
			}

			p[nr][nc + 1] = tmp; // 마지막 좌표에 tmp 넣음
		}
	}

}
