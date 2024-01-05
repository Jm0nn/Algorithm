package com.jm0nn.baekjoon.gold.g5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 상어 초등학교 교실에서 학생들이 규칙에 따라 자리에 앉을 때 만족도를 구하는 문제
public class G5_21608_상어초등학교 {

	// 좌표 이동
	static final int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	static int n; // 교실의 크기
	static Student[] student; // 학생 배열
	static Student[][] room; // 교실 배열

	// 학생 클래스
	static class Student {
		int num; // 번호
		List<Integer> like; // 좋아하는 학생 리스트

		public Student(int num) {
			this.num = num;
			like = new ArrayList<>();
		}
	}

	// 자리 클래스
	static class Seat implements Comparable<Seat> {
		int r; // 행
		int c; // 열
		int likeCnt; // 주변 좋아하는 학생 수
		int emptyCnt; // 주변 비어있는 자리 수

		public Seat(int r, int c, int likeCnt, int emptyCnt) {
			this.r = r;
			this.c = c;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}

		// 주변에 좋아하는 학생이 많을수록, 주변에 비어있는 자리가 많을수록, 행이 작을수록, 열이 작을수록 우선순위 높음
		@Override
		public int compareTo(Seat o) {
			return this.likeCnt != o.likeCnt ? o.likeCnt - this.likeCnt
					: this.emptyCnt != o.emptyCnt ? o.emptyCnt - this.emptyCnt
							: this.r != o.r ? this.r - o.r : this.c - o.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		int stdSize = n * n; // 학생의 수
		student = new Student[stdSize];
		room = new Student[n][n];

		for (int i = 0; i < stdSize; i++) {
			st = new StringTokenizer(br.readLine());

			// 학생의 번호를 입력받아 학생 생성
			student[i] = new Student(Integer.parseInt(st.nextToken()));

			// 학생이 좋아하는 학생 입력
			for (int j = 0; j < 4; j++)
				student[i].like.add(Integer.parseInt(st.nextToken()));

			seating(student[i]); // 자리 배치
		}

		System.out.println(satisfy()); // 만족도의 총 합 출력

		br.close();
	}

	// 자리 배치
	static void seating(Student s) {
		// 학생이 앉을 수 있는 자리를 넣을 우선순위 큐
		PriorityQueue<Seat> seat = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 자리가 비어있지 않다면 다음 자리 탐색
				if (room[i][j] != null)
					continue;

				int likeCnt = 0; // 주변 좋아하는 학생 수
				int emptyCnt = 0; // 주변 비어있는 자리 수

				for (int d = 0; d < 4; d++) {
					// 주변 좌표
					int ni = i + deltas[d][0];
					int nj = j + deltas[d][1];

					// 범위를 벗어나면 다음 좌표 탐색
					if (0 > ni || ni >= n || 0 > nj || nj >= n)
						continue;

					if (room[ni][nj] == null)
						emptyCnt++; // 주변이 비어있다면 증가
					else if (s.like.contains(room[ni][nj].num))
						likeCnt++; // 주변에 좋아하는 학생이 앉아있다면 증가
				}

				// 현재 자리 정보 우선순위 큐에 넣음
				seat.offer(new Seat(i, j, likeCnt, emptyCnt));
			}
		}

		// 우선순위에 따라 자리 배치
		Seat choice = seat.poll();
		room[choice.r][choice.c] = s;
	}

	// 만족도 총 합 계산
	static int satisfy() {
		int total = 0; // 만족도 총 합

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = 0; // 주변 좋아하는 학생 수

				for (int d = 0; d < 4; d++) {
					// 주변 좌표
					int ni = i + deltas[d][0];
					int nj = j + deltas[d][1];

					// 범위를 벗어나면 다음 좌표 탐색
					if (0 > ni || ni >= n || 0 > nj || nj >= n)
						continue;

					// 해당 좌표에 좋아하는 학생이 있다면 cnt 증가
					if (room[i][j].like.contains(room[ni][nj].num))
						cnt++;
				}

				// 만족도 더함
				if (cnt > 0)
					total += Math.pow(10, cnt - 1);
			}
		}

		return total;
	}

}
