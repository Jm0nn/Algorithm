import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static int r, c;
	static String[][] map;
	static Pos target;
	static String roadInfo = "|-+1234MZ";

	static class Pos {
		int r, c;
		String road;

		public Pos(int r, int c, String road) {
			this.r = r;
			this.c = c;
			this.road = road;
		}

		@Override
		public String toString() {
			return (r + 1) + " " + (c + 1) + " " + road;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new String[r][c];

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++)
				map[i][j] = String.valueOf(s.charAt(j));
		}

		loop: for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (roadInfo.contains(map[i][j]))
					continue;

				String up = ".";
				String right = ".";
				String down = ".";
				String left = ".";

				int upr = i + deltas[0][0];
				int upc = j + deltas[0][1];

				if (upr >= 0)
					up = map[upr][upc];

				int rightr = i + deltas[1][0];
				int rightc = j + deltas[1][1];

				if (rightc < c)
					right = map[rightr][rightc];

				int downr = i + deltas[2][0];
				int downc = j + deltas[2][1];

				if (downr < r)
					down = map[downr][downc];

				int leftr = i + deltas[3][0];
				int leftc = j + deltas[3][1];

				if (leftc >= 0)
					left = map[leftr][leftc];

				boolean isUp = false;
				boolean isRight = false;
				boolean isDown = false;
				boolean isLeft = false;

				if (up.equals("|") || up.equals("+") || up.equals("1") || up.equals("4"))
					isUp = true;

				if (right.equals("-") || right.equals("+") || right.equals("3") || right.equals("4"))
					isRight = true;

				if (down.equals("|") || down.equals("+") || down.equals("2") || down.equals("3"))
					isDown = true;

				if (left.equals("-") || left.equals("+") || left.equals("1") || left.equals("2"))
					isLeft = true;

				if (isUp && isRight && isDown && isLeft) {
					target = new Pos(i, j, "+");
					break loop;
				} else if (isUp && isDown) {
					target = new Pos(i, j, "|");
					break loop;
				} else if (isRight && isLeft) {
					target = new Pos(i, j, "-");
					break loop;
				} else if (isRight && isDown) {
					target = new Pos(i, j, "1");
					break loop;
				} else if (isUp && isRight) {
					target = new Pos(i, j, "2");
					break loop;
				} else if (isUp && isLeft) {
					target = new Pos(i, j, "3");
					break loop;
				} else if (isDown && isLeft) {
					target = new Pos(i, j, "4");
					break loop;
				}
			}
		}

		System.out.println(target);
	}

}