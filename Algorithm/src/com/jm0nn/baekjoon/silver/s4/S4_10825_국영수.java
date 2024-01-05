package com.jm0nn.baekjoon.silver.s4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_10825_국영수 {

	static class Heap {
		Student[] stu;
		int size;

		Heap(int capacity) {
			stu = new Student[capacity + 1];
		}

		private void swap(int i1, int i2) {
			Student tmp = stu[i1];
			stu[i1] = stu[i2];
			stu[i2] = tmp;
		}

		public void offer(Student s) {
			stu[++size] = s;

			int idx = size;

			while (idx >> 1 > 0 && stu[idx >> 1].compareTo(stu[idx]) > 0) {
				swap(idx >> 1, idx);
				idx >>= 1;
			}
		}

		public String poll() {
			int idx = 1;
			Student res = stu[idx];
			stu[idx] = stu[size];
			stu[size--] = null;

			while ((idx <<= 1) <= size) {
				if (stu[idx + 1] != null)
					idx = stu[idx].compareTo(stu[idx + 1]) < 0 ? idx : idx + 1;

				if (stu[idx >> 1].compareTo(stu[idx]) <= 0)
					break;

				swap(idx >> 1, idx);
			}

			return res.name;
		}

		public boolean isEmpty() {
			if (size == 0)
				return true;
			return false;
		}
	}

	static class Student implements Comparable<Student> {
		String name;
		int kor, eng, mat;

		Student(String name, int kor, int eng, int mat) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
		}

		@Override
		public int compareTo(Student o) {
			return this.kor != o.kor ? o.kor - this.kor
					: this.eng != o.eng ? this.eng - o.eng
							: this.mat != o.mat ? o.mat - this.mat : this.name.compareTo(o.name);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Heap heap = new Heap(n);

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int mat = Integer.parseInt(st.nextToken());
			heap.offer(new Student(name, kor, eng, mat));
		}

		StringBuilder sb = new StringBuilder();
		while (!heap.isEmpty())
			sb.append(heap.poll()).append('\n');

		System.out.println(sb);
	}

}
