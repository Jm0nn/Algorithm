import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가스를 파이프라인을 통해 운반할 때 중간에 비어있는 파이프라인 블록을 찾는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 행
		int c = Integer.parseInt(st.nextToken()); // 열

		String[][] map = new String[r + 1][c + 1]; // 파이프라인 맵

		for (int i = 1; i <= r; i++) {
			String s = br.readLine();
			for (int j = 1; j <= c; j++)
				map[i][j] = String.valueOf(s.charAt(j - 1));
		}

		br.close();

		String roadInfo = "|-+1234MZ"; // 맵 파이프라인 정보

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				// 파이프라인이 없는 지점 탐색
				if (roadInfo.contains(map[i][j]))
					continue;

				// 상하좌우 정보
				String up = "";
				String right = "";
				String down = "";
				String left = "";

				int upr = i - 1;
				int upc = j;
				if (upr > 0)
					up = map[upr][upc];

				int rightr = i;
				int rightc = j + 1;
				if (rightc <= c)
					right = map[rightr][rightc];

				int downr = i + 1;
				int downc = j;
				if (downr <= r)
					down = map[downr][downc];

				int leftr = i;
				int leftc = j - 1;
				if (leftc > 0)
					left = map[leftr][leftc];

				// 상하좌우 연결 정보
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

				// 연결 필요한 지점 정보 출력
				if (isUp && isRight && isDown && isLeft) {
					System.out.println(i + " " + j + " +");
					return;
				} else if (isUp && isDown) {
					System.out.println(i + " " + j + " |");
					return;
				} else if (isRight && isLeft) {
					System.out.println(i + " " + j + " -");
					return;
				} else if (isRight && isDown) {
					System.out.println(i + " " + j + " 1");
					return;
				} else if (isUp && isRight) {
					System.out.println(i + " " + j + " 2");
					return;
				} else if (isUp && isLeft) {
					System.out.println(i + " " + j + " 3");
					return;
				} else if (isDown && isLeft) {
					System.out.println(i + " " + j + " 4");
					return;
				}
			}
		}
	}
}