import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열의 r행 c열을 몇번째로 탐색하는지 구하는 문제
public class Main {

	static int n, r, c, answer; // 배열 크기, 탐색할 행, 열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, n);

		bs(r, c, size);

		System.out.println(answer);
	}

	static void bs(int nr, int nc, int size) {
		if (size == 1)
			return;

		int half = size / 2;

		if (nr < half && nc < half) {
			bs(nr, nc, half);
		} else if (nr < half && nc >= half) {
			answer += half * half;
			bs(nr, nc - half, half);
		} else if (nr >= half && nc < half) {
			answer += half * half * 2;
			bs(nr - half, nc, half);
		} else if (nr >= half && nc >= half) {
			answer += half * half * 3;
			bs(nr - half, nc - half, half);
		}
	}
}