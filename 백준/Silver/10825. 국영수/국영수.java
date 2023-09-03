import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

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
		PriorityQueue<Student> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int kor = Integer.parseInt(st.nextToken());
			int eng = Integer.parseInt(st.nextToken());
			int mat = Integer.parseInt(st.nextToken());
			pq.offer(new Student(name, kor, eng, mat));
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty())
			sb.append(pq.poll().name).append('\n');

		System.out.println(sb);
	}

}