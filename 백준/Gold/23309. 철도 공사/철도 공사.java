import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2호선에 역을 추가, 삭제하는 프로그램
public class Main {

	static final int MAX_STATION = 1_000_001; // 최대 역의 수

	// 현재 역을 배열의 인덱스, 다음 역과 이전 역을 배열의 요소로 설정
	static int[] prev = new int[MAX_STATION]; // 다음 역 배열
	static int[] next = new int[MAX_STATION]; // 이전 역 배열

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, first, idx, i, j, result;
	static String cmd;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 최초 역의 수
		m = Integer.parseInt(st.nextToken()); // 명령어 수

		// 최초 N개의 역 추가
		st = new StringTokenizer(br.readLine());
		first = Integer.parseInt(st.nextToken()); // 최초 역의 고유 번호

		// 최초 역은 이전 역과 다음 역이 자기 자신
		prev[first] = first;
		next[first] = first;

		for (int i = 1; i < n; i++) { // 나머지 역 추가
			idx = Integer.parseInt(st.nextToken());
			insertNext(first, idx);
			first = idx;
		}

		// 명령어 실행
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			switch (cmd) {
			case "BN": // i번 역 다음에 j번 역 추가
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(insertNext(i, j)).append('\n');
				break;

			case "BP": // i번 역 이전에 j번 역 추가
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(insertPrev(i, j)).append('\n');
				break;

			case "CN": // i번 역 다음 역 제거
				i = Integer.parseInt(st.nextToken());
				sb.append(deleteNext(i)).append('\n');
				break;

			case "CP": // i번 역 이전 역 제거
				i = Integer.parseInt(st.nextToken());
				sb.append(deletePrev(i)).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}

	// i역의 다음역에 j역을 설립
	static int insertNext(int i, int j) {
		result = next[i]; // i역의 다음 역 고유 번호 출력

		// 사이에 j역 끼워넣기
		next[i] = j;
		prev[result] = j;
		next[j] = result;
		prev[j] = i;

		return result;
	}

	// i역의 이전역에 j역을 설립
	static int insertPrev(int i, int j) {
		result = prev[i]; // i역의 이전 역 고유 번호 출력

		// 사이에 j역 끼워넣기
		next[result] = j;
		prev[i] = j;
		next[j] = i;
		prev[j] = result;

		return result;
	}

	// i역의 다음역 폐쇄
	static int deleteNext(int i) {
		result = next[i]; // i역의 다음 역 고유 번호 출력

		// i역의 다음 역 제거
		next[prev[result]] = next[result];
		prev[next[result]] = prev[result];

		return result;
	}

	// i역의 다음역 폐쇄
	static int deletePrev(int i) {
		result = prev[i]; // i역의 이전 역 고유 번호 출력

		// i역의 이전 역 제거
		next[prev[result]] = next[result];
		prev[next[result]] = prev[result];

		return result;
	}

}
