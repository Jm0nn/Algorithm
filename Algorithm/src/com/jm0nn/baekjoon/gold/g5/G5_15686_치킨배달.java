package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 도시 내 치킨집의 수가 제한될 때 각 집의 치킨 거리의 합의 최솟값을 구하는 문제 (맨해튼 거리)
public class G5_15686_치킨배달 {

	static int n, m; // 도시의 크기, 치킨집의 최대 개수
	static int min = Integer.MAX_VALUE; // 치킨 거리의 최솟값
	static List<Pos> hList = new ArrayList<>(); // 집 리스트
	static List<Pos> cList = new ArrayList<>(); // 치킨집 리스트

	// 좌표 클래스
	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) // 집을 리스트에 추가
					hList.add(new Pos(i, j));
				else if (num == 2) // 치킨집을 리스트에 추가
					cList.add(new Pos(i, j));
			}
		}

		// 집의 최소 치킨 거리 배열
		int[] dist = new int[hList.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);

		recur(0, 0, dist); // 재귀로 최소 치킨 거리 계산

		System.out.println(min);

		br.close();
	}

	// cnt: 현재 선택된 치킨집의 수, next: 탐색 시작할 치킨집 인덱스, dist: 치킨 거리 배열
	static void recur(int cnt, int next, int[] dist) {

		// 선택된 치킨집의 수가 최대에 도달하면 도시의 치킨 거리 계산
		if (cnt == m) {
			int sum = 0;

			for (int i : dist)
				sum += i;

			if (min > sum)
				min = sum;

			return;
		}

		for (int i = next; i < cList.size(); i++) {
			int[] tmp = dist.clone(); // 치킨 거리 배열 복사

			getDist(cList.get(i), tmp); // 치킨 거리 계산
			recur(cnt + 1, i + 1, tmp); // 다음 치킨집 탐색
		}
	}

	// 치킨 거리 계산, c: 치킨집 좌표, dist: 치킨 거리 배열
	static void getDist(Pos c, int[] dist) {

		// 치킨집의 좌표
		int x = c.x;
		int y = c.y;

		// 모든 집을 돌면서 탐색
		for (int i = 0; i < hList.size(); i++) {
			// 현재 집과 치킨집의 치킨 거리
			int d = Math.abs(x - hList.get(i).x) + Math.abs(y - hList.get(i).y);
			// 기존값과 비교하여 작은 값으로 갱신
			dist[i] = Math.min(dist[i], d);
		}
	}
}
