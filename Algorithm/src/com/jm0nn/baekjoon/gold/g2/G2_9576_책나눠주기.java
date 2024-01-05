package com.jm0nn.baekjoon.gold.g2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 책을 나눠 줄 수 있는 최댓값을 구하는 문제
public class G2_9576_책나눠주기 {

	// 학생 클래스
	static class Student {
		int a; // 앞번호
		int b; // 뒷번호

		public Student(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 책의 수
			int m = Integer.parseInt(st.nextToken()); // 학생의 수
			boolean[] book = new boolean[n + 1]; // 책 배열, 빌려주면 true
			Student[] student = new Student[m]; // 학생 배열

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				student[i] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 학생 배열을 b의 오름차순, b가 같다면 a의 오름차순으로 정렬
			Arrays.sort(student, new Comparator<Student>() {

				@Override
				public int compare(Student o1, Student o2) {
					if (o1.b == o2.b)
						return o1.a - o2.a;
					return o1.b - o2.b;
				}

			});

			int answer = 0; // 정답
			for (int i = 0; i < m; i++) {
				// 현재 학생이 받고 싶은 책의 범위 내에서 책을 빌려줌
				for (int j = student[i].a; j <= student[i].b; j++) {
					// 책이 남아있다면 빌려줌
					if (!book[j]) {
						book[j] = true;
						answer++;
						break;
					}
				}
			}

			sb.append(answer).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

}
