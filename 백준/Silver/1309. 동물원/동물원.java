import java.util.Scanner;

// 우리에 사자를 가둘 때 사자가 가로, 세로로 붙지 않도록 배치하는 경우의 수를 구하는 문제
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 우리의 크기

		int[][] dp = new int[n][3]; // dp 배열
		dp[0][0] = 1; // (n, 0) 어느 쪽에도 사자가 없을 때
		dp[0][1] = 1; // (n, 1) 왼쪽에 사자가 있을 때
		dp[0][2] = 1; // (n, 2) 오른쪽에 사자가 있을 때

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (j != k || j == 0) // 세로로 같은 사자가 있지 않은 경우에 이전의 dp값을 더해줌
						dp[i][k] = (dp[i][k] + dp[i - 1][j]) % 9901;
				}
			}
		}

		System.out.println((dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]) % 9901);
	}
}