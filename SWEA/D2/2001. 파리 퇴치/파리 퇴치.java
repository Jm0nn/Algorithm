import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] fly = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					fly[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			for (int i = 0; i <= n - m; i++) {
				for (int j = 0; j <= n - m; j++) {
					int sum = 0;
					for (int k = i; k < i + m; k++) {
						for (int l = j; l < j + m; l++) {
							sum += fly[k][l];
						}
					}
					
					max = Math.max(max, sum);
				}
			}
			
			sb.append(max).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
}