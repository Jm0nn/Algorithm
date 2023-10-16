import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		long x, y;

		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}

	static Point control;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Point> points = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points.add(new Point(x, y));
		}

		control = new Point(40001, 40001); // 기준점

		// y 좌표 값이 가장 작은 점, 그 중 x 좌표 값이 가장 작은 점을 기준점으로 설정
		for (int i = 0; i < n; ++i) {
			if (points.get(i).y < control.y)
				control = points.get(i);
			else if (points.get(i).y == control.y)
				if (points.get(i).x < control.x)
					control = points.get(i);
		}

		// 기준점과 나머지 점들이 CCW로 반시계 방향이 되도록 정렬
		points.sort(new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				long ccwR = ccw(control, p1, p2);

				if (ccwR > 0) // 반시계
					return -1;
				else if (ccwR < 0) // 시계
					return 1;
				else { // 일직선일 경우 거리가 증가하게 정렬
					long dist1 = dist(control, p1);
					long dist2 = dist(control, p2);

					if (dist1 > dist2)
						return 1;
					else
						return -1;
				}
			}

		});

		// Graham's scan 알고리즘
		Stack<Point> stack = new Stack<>();
		stack.add(control);

		for (int i = 1; i < n; ++i) {
			int size = stack.size();

			// 시계방향 또는 일직선이면 스택에서 제거
			while (size > 1 && ccw(stack.get(size - 2), stack.get(size - 1), points.get(i)) <= 0) {
				stack.pop();
				--size;
			}

			stack.add(points.get(i));
		}

		System.out.println(stack.size());
	}

	// CCW 알고리즘: 시계방향 음수, 일직선 0, 반시계방향 양수
	static long ccw(Point p1, Point p2, Point p3) {
		return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
	}

	// 두 점 사이의 거리의 제곱
	static long dist(Point p1, Point p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

}