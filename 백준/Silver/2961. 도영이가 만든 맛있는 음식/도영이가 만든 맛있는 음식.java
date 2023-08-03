import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 요리의 재료를 적절이 섞어서 신맛과 쓴맛의 차이가 최소가 되는 요리를 찾는 문제
public class Main {

	static int n; // 재료의 개수
	static int min = Integer.MAX_VALUE; // 신맛과 쓴맛의 차이의 최솟값
	static int[][] ingredient; // 재료 배열
	static boolean[] isSelected; // 사용 확인 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		ingredient = new int[n][2];
		isSelected = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredient[i][0] = Integer.parseInt(st.nextToken()); // 재료의 신맛
			ingredient[i][1] = Integer.parseInt(st.nextToken()); // 재료의 쓴맛
		}

		set(0, 0, 1, 0); // 현재 탐색한 재료와 사용한 재료를 0으로 시작

		System.out.println(min);

		br.close();
	}

	// cnt: 현재 탐색한 재료의 개수, use: 현재 사용한 재료의 개수
	static void set(int cnt, int use, int sin, int ssn) {

		// 재료 탐색을 끝내면 신맛과 쓴맛의 차이를 구함
		if (cnt == n) {

			// 재료를 하나도 사용하지 않았으면 리턴
			if (use == 0)
				return;

			int diff = Math.abs(sin - ssn); // 신맛과 쓴맛의 차이

			min = Math.min(min, diff); // 기존 최솟값과 현재값 중 작은 값을 최솟값으로 갱신

			return;
		}

		isSelected[cnt] = true; // 재료 사용
		set(cnt + 1, use + 1, sin * ingredient[cnt][0], ssn + ingredient[cnt][1]); // 다음 재료 탐색
		isSelected[cnt] = false; // 재료 미사용
		set(cnt + 1, use, sin, ssn); // 다음 재료 탐색
	}
}