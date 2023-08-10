import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// 직교 다각형을 x축으로 잘라 위쪽 영역에 나타나는 봉우리들의 상태를 구하는 문제
public class Main {

	static class Point implements Comparable<Point> {
		int x;
		char c;

		Point(int x) {
			this.x = x;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		List<Point> list = new ArrayList<>(); // x축 좌표 리스트

		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		// 처음 입력된 좌표
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		boolean flag = false; // 봉우리가 아래를 향하는지

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			// 다음 입력된 좌표
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());

			// 좌표가 x축을 이동했다면 x좌표 list에 추가
			if ((y > 0 && ny < 0) || (y < 0 && ny > 0)) {

				// 처음 입력되는 좌표의 선분이 아래를 향하면 봉우리가 아래를 향함
				if (list.isEmpty() && (y > 0 && ny < 0))
					flag = true;

				list.add(new Point(x));
			}

			// 현재 좌표 갱신
			x = nx;
			y = ny;
		}

		// 리스트 요소 개수가 홀수라면 마지막 좌표 추가
		if (list.size() % 2 == 1)
			list.add(new Point(x));

		// 봉우리가 아래를 향한다면 위를 향하게 바꿈
		if (flag) {
			list.add(list.get(0));
			list.remove(0);
		}

		for (int i = 0; i < list.size(); i += 2) {
			int f = list.get(i).x;
			int b = list.get(i + 1).x;

			if (f < b) {
				list.get(i).c = '(';
				list.get(i + 1).c = ')';
			} else {
				list.get(i).c = ')';
				list.get(i + 1).c = '(';
			}
		}

		Collections.sort(list); // 리스트 오름차순 정렬

		Stack<Character> stack = new Stack<>(); // 봉우리 계산할 스택

		int a = 0; // 다른 봉우리에 의해 포함되지 않는 봉우리 개수
		int b = 0; // 다른 봉우리를 포함하지 않는 봉우리 개수
		boolean isb = false; // 다른 봉우리를 포함하지 않는지?

		for (int i = 0; i < list.size(); i++) {
			if (stack.isEmpty()) { // 스택이 비어있다면
				stack.push(list.get(i).c); // 스택에 추가
				isb = true; // 현재 봉우리는 다른 봉우리를 포함하지 않음
				continue; // 다음 좌표 탐색
			}

			if (list.get(i).c == ')') { // 현재 리스트의 좌표가 봉우리의 뒤라면
				stack.pop(); // 스택에서 제거

				// 다른 봉우리를 포함하지 않으면 증가
				if (isb) {
					b++;

					// 팝 후 연속으로 팝이 되면 해당 봉우리는 직전의 봉우리를 포함하고 있음
					isb = false;
				}

				// 스택 pop 후 스택이 비어있다면 다른 봉우리에 포함되지 않으므로 증가
				if (stack.isEmpty())
					a++;

			} else { // 현재 리스트의 좌표가 봉우리의 앞이라면
				stack.push(list.get(i).c); // 현재 좌표 스택에 추가
				isb = true; // 다른 봉우리를 포함하지 않음
			}

		}

		sb.append(a).append(' ').append(b);

		System.out.println(sb);

		br.close();
	}

}