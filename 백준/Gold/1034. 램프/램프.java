import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 테이블 위 램프에서 열 전체의 상태를 한번에 바꿀 때 행 전체가 켜져 있는 최댓값을 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 행의 개수
		int m = Integer.parseInt(st.nextToken()); // 열의 개수
		String[] table = new String[n]; // 테이블을 String으로 받음

		for (int i = 0; i < n; i++)
			table[i] = br.readLine();

		int k = Integer.parseInt(br.readLine()); // 스위치 켜고 끈 횟수

		int oddEven = k % 2; // k의 홀짝 판별

		// 스위치 열의 최댓값이 50이므로 k가 50을 넘어가면 50으로 고정
		if (k > 50)
			k = 50;

		int max = 0; // 답

		for (int i = 0; i < n; i++) { // 각 행마다 판별

			// 현재 행의 0의 개수
			int zeroCnt = 0;
			for (int j = 0; j < m; j++)
				if (table[i].charAt(j) == '0')
					zeroCnt++;

			// 현재 행의 0의 개수가 k의 홀짝과 다르거나
			// 0의 개수가 k보다 크면 행이 켜질 수 없음
			if (zeroCnt % 2 != oddEven || zeroCnt > k)
				continue;

			// 현재 행과 같은 패턴의 행 개수
			int pattern = 0;
			for (int j = 0; j < n; j++)
				if (table[i].equals(table[j]))
					pattern++;

			// 기존 최댓값과 비교
			if (pattern > max)
				max = pattern;
		}

		System.out.println(max);

		br.close();
	}
}