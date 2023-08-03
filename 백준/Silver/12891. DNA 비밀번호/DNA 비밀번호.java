import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체 문자열에서 문자의 최소 개수를 만족하는 부분 문자열의 개수를 찾는 문제
public class Main {

	static int a, c, g, t; // 각 문자를 사용해야 할 최솟값
	static int[] charCount = new int[4]; // 만들어진 부분문자열에서 각 문자가 사용된 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		char[] dna = br.readLine().toCharArray();
		int[] dnaNum = new int[s];

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		for (int i = 0; i < s; i++) {
			// 각 문자를 숫자로 바꿔서 정리
			switch (dna[i]) {
			case 'A':
				dnaNum[i] = 0;
				break;
			case 'C':
				dnaNum[i] = 1;
				break;
			case 'G':
				dnaNum[i] = 2;
				break;
			case 'T':
				dnaNum[i] = 3;
				break;
			}
		}

		int count = 0; // 만족하는 부분문자열의 수

		// 문자열의 가장 앞쪽 부분문자열에서 각 문자가 사용된 수
		for (int i = 0; i < p; i++)
			charCount[dnaNum[i]]++;

		// 해당 문자열이 만족한다면 카운트 증가
		if (isValid())
			count++;

		// 부분문자열의 앞쪽, 뒷쪽
		int i = 0;
		int f = p;

		while (f < s) {
			// 부분문자열 이동
			charCount[dnaNum[i]]--;
			charCount[dnaNum[f]]++;

			if (isValid())
				count++;

			i++;
			f++;
		}

		System.out.println(count);

		br.close();
	}

	// 부분문자열이 조건을 만족하는지 확인하는 메서드
	static boolean isValid() {
		if (charCount[0] < a)
			return false;
		if (charCount[1] < c)
			return false;
		if (charCount[2] < g)
			return false;
		if (charCount[3] < t)
			return false;

		return true;
	}
}