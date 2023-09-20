import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 규칙에 따라 말을 움직이며 게임이 종료되는 턴 수를 구하는 문제
class Main {

	// 말 클래스, 링크드 리스트로 위아래로 쌓인 말 탐색
	static class Piece {
		int num; // 말 번호
		int dir; // 말 이동 방향

		Piece up; // 위에 쌓인 말
		Piece down; // 아래에 쌓인 말

		Piece(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}

	// 위치 클래스
	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 체스판 색
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;

	static final int MAX_TURN = 1000; // 최대 턴 수
	static final int MAX_PIECE_STACK = 3; // 말이 최대로 쌓일 수 있는 수

	// 위치 이동 (1: 우, 2: 좌, 3: 상, 4: 하)
	static final int[][] DELTAS = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static int n, k; // 체스판 크기, 말의 개수
	static int curNum, turnCnt; // 현재 말 번호, 현재 턴 수
	static boolean gameOver; // 게임 종료 여부

	static int[][] boardColor; // 체스판 색 배열
	static int[][] pieceCnt; // 체스판에 쌓인 말의 수 배열
	static Piece[][] board; // 체스판에 쌓인 말 배열
	static Pos[] pieces; // 말의 위치 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 체스판 크기, 말의 개수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 배열 크기 지정
		boardColor = new int[n + 1][n + 1];
		pieceCnt = new int[n + 1][n + 1];
		board = new Piece[n + 1][n + 1];
		pieces = new Pos[k];

		// 체스판 색 입력
		for (int i = 1; i <= n; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; ++j) {
				boardColor[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 말 정보 입력
		for (int i = 0; i < k; ++i) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			++pieceCnt[x][y];
			board[x][y] = new Piece(i, dir);
			pieces[i] = new Pos(x, y);
		}

		br.close();

		curNum = 0;
		turnCnt = 1;

		// 턴이 1000을 넘어가면 종료
		while (turnCnt <= MAX_TURN) {
			// 현재 말의 위치
			Pos curPos = pieces[curNum];
			int x = curPos.x;
			int y = curPos.y;

			// 현재 말 탐색
			Piece curPiece = board[x][y];
			while (curPiece.num != curNum) {
				curPiece = curPiece.up;
			}

			int dir = curPiece.dir; // 현재 말의 방향

			// 해당 방향으로 위치 이동
			int nx = x + DELTAS[dir][0];
			int ny = y + DELTAS[dir][1];

			if (isValidPos(nx, ny)) {
				// 이동한 위치가 유효하면 말 이동
				move(curPos, curPiece, nx, ny);
			} else {
				// 이동한 위치가 유효하지 않으므로 방향 바꿈
				dir = back(dir);
				curPiece.dir = dir;

				// 바꾼 방향으로 위치 이동
				nx = x + DELTAS[dir][0];
				ny = y + DELTAS[dir][1];

				// 이동한 위치가 유효하면 말 이동
				if (isValidPos(nx, ny)) {
					move(curPos, curPiece, nx, ny);
				}
			}

			// 게임 종료 여부
			if (gameOver) {
				break;
			}

			curNum = ++curNum % k; // 다음 말

			// 전체 말을 한 번씩 이동하면 턴 수 증가
			if (curNum == 0) {
				++turnCnt;
			}
		}

		// 턴 수가 1000을 넘어가는지 확인, 넘어가지 않으면 턴 수를 출력, 넘어가면 -1 출력
		System.out.println(turnCnt <= MAX_TURN ? turnCnt : -1);
	}

	// 유효한 위치인지 확인하는 메서드
	static boolean isValidPos(int x, int y) {
		// 위치가 체스판을 벗어나거나 해당 위치가 파란색이면 유효하지 않음
		if (1 > x || x > n || 1 > y || y > n || boardColor[x][y] == BLUE) {
			return false;
		}
		return true;
	}

	// 말의 방향을 바꾸는 메서드
	static int back(int dir) {
		switch (dir) {
		case 1:
		case 2:
			// 좌우 전환
			return 3 - dir;
		case 3:
		case 4:
			// 상하 전환
			return 7 - dir;
		}
		return -1;
	}

	// 말이 이동하는 메서드
	static void move(Pos curPos, Piece curPiece, int nx, int ny) {
		int cnt = 0; // 이동하는 말의 수

		// 이동하는 말의 현재 좌표
		int x = curPos.x;
		int y = curPos.y;

		// 현재 말과 윗쪽 말 전부의 좌표를 이동
		for (Piece tmpPiece = curPiece; tmpPiece != null; tmpPiece = tmpPiece.up) {
			int num = tmpPiece.num;
			Pos tmpPos = pieces[num];
			tmpPos.x = nx;
			tmpPos.y = ny;
			++cnt;
		}

		// 쌓인 말의 개수 계산
		pieceCnt[x][y] -= cnt;
		pieceCnt[nx][ny] += cnt;

		// 말이 4개 이상 쌓였다면 게임 종료
		if (pieceCnt[nx][ny] > MAX_PIECE_STACK) {
			gameOver = true;
			return;
		}

		// 이동할 말을 아랫쪽 말과 분리
		if (curPiece.down == null) {
			board[x][y] = null;
		} else {
			curPiece.down.up = null;
			curPiece.down = null;
		}

		Piece bottom = curPiece; // 이동할 말

		// 이동한 위치가 빨간색이라면 쌓인 말들의 위아래를 뒤집음
		if (boardColor[nx][ny] == RED) {
			while (true) {
				swapUpDown(bottom);
				if (bottom.down == null) {
					break;
				}
				bottom = bottom.down;
			}
		}

		Piece top = board[nx][ny]; // 이동할 위치에 기존에 놓여 있는 말

		// 기존에 놓여 있는 말들의 가장 위에 말을 쌓음
		if (top == null) {
			board[nx][ny] = bottom;
		} else {
			while (top.up != null) {
				top = top.up;
			}

			top.up = bottom;
			bottom.down = top;
		}
	}

	// 말의 위아래를 뒤집는 메서드
	static void swapUpDown(Piece piece) {
		Piece tmp = piece.down;
		piece.down = piece.up;
		piece.up = tmp;
	}

}