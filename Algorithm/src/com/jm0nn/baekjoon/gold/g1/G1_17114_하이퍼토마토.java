package com.jm0nn.baekjoon.gold.g1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 11차원 토마토 창고의 토마토들이 모두 익는 최소 일수를 구하는 문제
public class G1_17114_하이퍼토마토 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 창고 크기
		int tsize = 1;
		int[] size = new int[11];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 11; i++) {
			size[i] = Integer.parseInt(st.nextToken());
			tsize *= size[i];
		}

		// 방문 배열
		boolean[][][][][][][][][][][] visited = new boolean[size[0]][size[1]][size[2]][size[3]][size[4]][size[5]][size[6]][size[7]][size[8]][size[9]][size[10]];

		int day = 0; // 걸린 일수
		Queue<int[]> queue = new ArrayDeque<>(tsize); // bfs 큐

		for (int w = 0; w < size[10]; w++) {
			for (int v = 0; v < size[9]; v++) {
				for (int u = 0; u < size[8]; u++) {
					for (int t = 0; t < size[7]; t++) {
						for (int s = 0; s < size[6]; s++) {
							for (int r = 0; r < size[5]; r++) {
								for (int q = 0; q < size[4]; q++) {
									for (int p = 0; p < size[3]; p++) {
										for (int o = 0; o < size[2]; o++) {
											for (int n = 0; n < size[1]; n++) {
												st = new StringTokenizer(br.readLine());
												for (int m = 0; m < size[0]; m++) {
													char c = st.nextToken().charAt(0);
													if (c == '0')
														continue;
													// 방문 처리 및 토마토 감소
													visited[m][n][o][p][q][r][s][t][u][v][w] = true;
													tsize--;
													// 토마토가 이미 익었으면 큐에 좌표 offer
													if (c == '1')
														queue.offer(new int[] { m, n, o, p, q, r, s, t, u, v, w, 0 });
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// bfs로 최소 일수 계산
		while (!queue.isEmpty()) {
			int[] idx = queue.poll();

			// 걸린 일수
			if (idx[11] > day)
				day = idx[11];

			for (int i = 0; i < 11; i++) {
				for (int d = -1; d < 2; d += 2) {
					// 좌표 이동
					idx[i] += d;

					// 좌표 유효 확인 및 방문 확인
					if (0 <= idx[i] && idx[i] < size[i]
							&& !visited[idx[0]][idx[1]][idx[2]][idx[3]][idx[4]][idx[5]][idx[6]][idx[7]][idx[8]][idx[9]][idx[10]]) {
						// 방문 확인, 토마토 감소, 큐에 offer
						visited[idx[0]][idx[1]][idx[2]][idx[3]][idx[4]][idx[5]][idx[6]][idx[7]][idx[8]][idx[9]][idx[10]] = true;
						tsize--;
						queue.offer(new int[] { idx[0], idx[1], idx[2], idx[3], idx[4], idx[5], idx[6], idx[7], idx[8],
								idx[9], idx[10], idx[11] + 1 });
					}

					// 좌표 복귀
					idx[i] -= d;
				}
			}
		}

		// 창고의 크기와 익은 토마토의 개수가 맞지 않으면 토마토가 모두 익지 못함
		System.out.println(tsize == 0 ? day : -1);
	}
}
