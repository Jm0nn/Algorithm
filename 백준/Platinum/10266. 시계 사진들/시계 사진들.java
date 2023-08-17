import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 시계가 같은 시각을 나타내는지 맞추는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 시계 바늘의 수
		int[] clock1 = new int[n]; // 시계1
		int[] clock2 = new int[n]; // 시계2

		StringTokenizer st = new StringTokenizer(br.readLine());
		clock1[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < n; i++)
			clock1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			clock2[i] = Integer.parseInt(st.nextToken());

		// 시계 배열 오름차순 정렬
		Arrays.sort(clock1);
		Arrays.sort(clock2);

		// 시계 바늘 간의 차를 입력할 StringBuilder
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		for (int i = 0; i < n - 1; i++) {
			sb1.append(clock1[i + 1] - clock1[i]).append(' ');
			sb2.append(clock2[i + 1] - clock2[i]).append(' ');
		}

		sb1.append((360000 + clock1[0]) - clock1[n - 1]).append(' ');
		sb2.append((360000 + clock2[0]) - clock2[n - 1]).append(' ');

		String s1 = sb1.append(sb1.toString()).toString();
		String s2 = sb2.toString();

		boolean possible = false; // 같은 시계인지 여부

		// KMP 알고리즘
		int n1 = s1.length();
		int n2 = s2.length();
		int[] table = new int[n2];
		int idx = 0;
		for (int i = 1; i < n2; i++) {
			while (idx > 0 && s2.charAt(i) != s2.charAt(idx))
				idx = table[idx - 1];

			if (s2.charAt(i) == s2.charAt(idx)) {
				idx++;
				table[i] = idx;
			}
		}

		int begin = 0;
		int matched = 0;
		while (begin <= n1 - n2) {
			if (matched < n2 && s1.charAt(begin + matched) == s2.charAt(matched)) {
				matched++;
				if (matched == n2) {
					possible = true;
					break;
				}
			} else {
				if (matched == 0)
					begin++;
				else {
					begin += matched - table[matched - 1];
					matched = table[matched - 1];
				}
			}
		}

		if (possible)
			System.out.println("possible");
		else
			System.out.println("impossible");
	}
}