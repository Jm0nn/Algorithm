package com.jm0nn.swea.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 스마트폰이 이동하며 충전할 때, 최적의 충전량을 구하는 문제
public class SW_5644_무선충전 {

	// 이동 좌표 (0: 제자리, 1: 상, 2: 우, 3: 하, 4: 좌)
	static final int[][] deltas = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	static int m, a, sum; // 이동 시간, C의 개수, 충전량의 합
	static int[] moveA, moveB; // A, B의 이동 방향
	static Pos A, B; // A, B의 위치
	static BC[] bc; // BC의 정보

	// 위치 클래스
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// BC(Battery Charger) 클래스
	static class BC extends Pos implements Comparable<BC> {
		int c; // 충전 범위
		int p; // 충전량

		public BC(int x, int y, int c, int p) {
			super(x, y);
			this.c = c;
			this.p = p;
		}

		// 충전량의 내림차순으로 정렬
		@Override
		public int compareTo(BC o) {
			return o.p - this.p;
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
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());

			// A와 B의 초기 위치
			A = new Pos(1, 1);
			B = new Pos(10, 10);

			// 초기 위치부터 충전
			moveA = new int[m + 1];
			moveB = new int[m + 1];

			bc = new BC[a];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++)
				moveA[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++)
				moveB[i] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new BC(x, y, c, p);
			}

			Arrays.sort(bc); // BC 리스트를 충전량의 내림차순으로 정렬

			sum = 0; // 충전량의 합 초기화

			for (int i = 0; i <= m; i++) {
				// 1초 후 위치 이동
				A.x += deltas[moveA[i]][0];
				A.y += deltas[moveA[i]][1];
				B.x += deltas[moveB[i]][0];
				B.y += deltas[moveB[i]][1];

				charge(); // 이동한 위치에서 충전
			}

			sb.append(sum).append('\n');
		}

		System.out.println(sb);
	}

	static void charge() {
		// BC와의 연결 가능 여부
		boolean[] cA = new boolean[a];
		boolean[] cB = new boolean[a];

		// 연결한 BC의 인덱스
		int iA = -1;
		int iB = -1;

		// 연결한 BC의 충전량
		int pA = 0;
		int pB = 0;

		for (int i = 0; i < a; i++) {
			// BC와의 거리 계산
			int disA = Math.abs(A.x - bc[i].x) + Math.abs(A.y - bc[i].y);
			int disB = Math.abs(B.x - bc[i].x) + Math.abs(B.y - bc[i].y);

			// 거리가 충전 범위 내인지 확인
			if (disA <= bc[i].c)
				cA[i] = true;

			if (disB <= bc[i].c)
				cB[i] = true;
		}

		// A가 충전 가능한 BC 확인
		for (int i = 0; i < a; i++) {
			if (cA[i]) {
				iA = i;
				pA = bc[i].p;
				break;
			}
		}

		// B가 충전 가능한 BC 확인
		for (int i = 0; i < a; i++) {
			if (cB[i]) {
				iB = i;
				pB = bc[i].p;
				break;
			}
		}

		// 두 BC가 같으면 재확인
		if (iA == iB) {
			// 둘의 BC가 달라질 수 있는지 확인
			for (int i = iA + 1; i < a; i++) {
				if (cA[i]) {
					iA = i;
					pA = bc[i].p;
					break;
				}

				if (cB[i]) {
					iB = i;
					pB = bc[i].p;
					break;
				}
			}
		}

		if (iA == iB) // 두 BC가 같으면
			sum += pA; // 충전량은 각각 절반
		else // 두 BC가 다르면
			sum += pA + pB; // 따로 충전
	}

}
