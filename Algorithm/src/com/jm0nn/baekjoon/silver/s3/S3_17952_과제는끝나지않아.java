package com.jm0nn.baekjoon.silver.s3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class S3_17952_과제는끝나지않아 {

	static int n, point; // 이번 분기의 기간, 이번 분기 업무 평가 점수
	static Stack<Task> stop = new Stack<>(); // 진행 중 잠시 멈춘 업무 스택
	static Task current; // 현재 진행중인 업무

	// 업무 클래스
	static class Task {
		int task; // 업무가 있으면 1, 없으면 0
		int a; // 업무를 완성하면 얻을 수 있는 점수
		int t; // 업무에 걸리는 시간

		public Task(int task, int a, int t) {
			this.task = task;
			this.a = a;
			this.t = t;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) { // n 시간 동안 진행
			st = new StringTokenizer(br.readLine());

			Task cur; // 이번 시간에 받은 업무

			int task = Integer.parseInt(st.nextToken()); // 업무가 있는지

			if (task == 0) {
				cur = new Task(0, 0, 0); // 해당 시간 업무가 없으면 0 입력
			} else {
				int a = Integer.parseInt(st.nextToken()); // 업무의 만점
				int t = Integer.parseInt(st.nextToken()); // 업무에 걸리는 시간

				cur = new Task(task, a, t); // 업무가 있으면 정보 입력
			}

			if (cur.task == 0) { // 이번 시간에 업무를 받지 않았다면
				if (current != null)
					current.t--; // 현재 진행중인 업무 시간 감소
			} else { // 이번 시간에 업무를 받았다면
				if (current != null) // 현재 진행중인 업무가 있을 때 현재 업무를 리스트에 넣음
					stop.push(current);

				current = cur; // 이번에 받은 업무로 넘어감
				current.t--; // 업무 시간 감소
			}

			if (current != null && current.t == 0) { // 현재 진행중인 업무가 완료됐다면
				point += current.a; // 업무 평가 점수 더함

				if (!stop.isEmpty()) { // 이전에 진행중이던 업무가 있다면
					current = stop.pop(); // 해당 업무로 전환
				} else { // 이전에 진행중이던 업무가 없다면
					current = null; // 현재 진행중인 업무 제거
				}
			}
		}

		System.out.println(point); // 업무 평가 점수 출력
	}
}
