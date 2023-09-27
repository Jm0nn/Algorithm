import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 O(nlogn)
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] LIS = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		LIS[0] = arr[0];
		int lengthOfLIS = 1;

		for (int i = 1; i < n; i++) {
			int key = arr[i];

			// 만약 key가 LIS의 마지막 값보다 클 경우 추가
			if (LIS[lengthOfLIS - 1] < key) {
				LIS[lengthOfLIS++] = key;
			} else {
				// Lower Bound 이분탐색 진행
				int lo = 0;
				int hi = lengthOfLIS - 1;
				while (lo < hi) {
					int mid = (lo + hi) / 2;

					if (LIS[mid] < key)
						lo = mid + 1;
					else
						hi = mid;
				}

				// lo가 LIS에서 대치 될 원소의 위치가 될 것이고 해당 위치에 key값으로 원소를 바꿔준다
				LIS[lo] = key;
			}
		}

		System.out.println(lengthOfLIS);
	}

}