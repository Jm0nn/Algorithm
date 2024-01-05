package com.jm0nn.swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전차를 조종하는 게임
public class D3_1873_상호의배틀필드 {

	// 전차의 방향 (0: 위, 1: 아래, 2: 왼쪽, 3: 오른쪽)
	static final int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int h, w, n; // 맵의 크기, 사용자 입력의 개수
	static String command; // 사용자 입력
	static char[][] field; // 맵
	static Tank tank; // 조종할 전차

	// 전차 클래스
	static class Tank {
		int r, c, d; // 좌표, 방향

		public Tank(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');

			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			field = new char[h][w];

			// 필드 입력
			for (int i = 0; i < h; i++) {
				String s = br.readLine();
				for (int j = 0; j < w; j++) {
					char c = s.charAt(j);

					// 전차를 입력 받을 경우
					if (c == '^' || c == 'v' || c == '<' || c == '>') {
						// 전차의 방향 구하기
						int d = 0;
						switch (c) {
						case '^':
							d = 0;
							break;
						case 'v':
							d = 1;
							break;
						case '<':
							d = 2;
							break;
						case '>':
							d = 3;
							break;
						}

						// 전차 생성
						tank = new Tank(i, j, d);

						// 전차의 위치는 평지로 설정
						c = '.';
					}

					field[i][j] = c;
				}
			}

			n = Integer.parseInt(br.readLine());
			command = br.readLine();

			for (int i = 0; i < command.length(); i++) {
				char c = command.charAt(i);
				if (c == 'S') { // 포탄 발사 명령어
					shoot(); // 발사
				} else { // 이동 명령어
					// 방향 바꿈
					switch (c) {
					case 'U':
						tank.d = 0;
						break;
					case 'D':
						tank.d = 1;
						break;
					case 'L':
						tank.d = 2;
						break;
					case 'R':
						tank.d = 3;
						break;
					}

					move(); // 이동
				}
			}

			// 출력 전 필드에 전차 배치
			switch (tank.d) {
			case 0:
				field[tank.r][tank.c] = '^';
				break;
			case 1:
				field[tank.r][tank.c] = 'v';
				break;
			case 2:
				field[tank.r][tank.c] = '<';
				break;
			case 3:
				field[tank.r][tank.c] = '>';
				break;
			}

			// 출력
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++)
					sb.append(field[i][j]);
				sb.append('\n');
			}
		}

		System.out.println(sb);
	}

	static void shoot() {
		// 포탄이 발사되는 좌표
		int r = tank.r + deltas[tank.d][0];
		int c = tank.c + deltas[tank.d][1];

		while (0 <= r && r < h && 0 <= c && c < w) {
			if (field[r][c] == '*') { // 포탄이 벽돌을 만나면
				field[r][c] = '.'; // 평지로 바꾼 후 멈춤
				break;
			} else if (field[r][c] == '#') { // 포탄이 강철을 만나면 멈춤
				break;
			}

			// 포탄 계속 이동
			r += deltas[tank.d][0];
			c += deltas[tank.d][1];
		}
	}

	static void move() {
		// 이동할 방향
		int r = tank.r + deltas[tank.d][0];
		int c = tank.c + deltas[tank.d][1];

		// 이동할 방향이 평지면 이동
		if (0 <= r && r < h && 0 <= c && c < w && field[r][c] == '.') {
			tank.r = r;
			tank.c = c;
		}
	}

}
