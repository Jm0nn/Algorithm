import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static long[] histogram;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		histogram = new long[n];

		for (int i = 0; i < n; ++i)
			histogram[i] = Long.parseLong(br.readLine());

		System.out.println(getArea(0, n - 1));
	}

	static long getArea(int lo, int hi) {
		if (lo == hi)
			return histogram[lo];

		int mid = (lo + hi) / 2;

		return Math.max(getMidArea(lo, hi, mid), Math.max(getArea(lo, mid), getArea(mid + 1, hi)));
	}

	static long getMidArea(int lo, int hi, int mid) {
		int left = mid;
		int right = mid;

		long height = histogram[mid];
		long maxArea = height;

		while (lo < left && right < hi) {
			if (histogram[left - 1] < histogram[right + 1])
				height = Math.min(height, histogram[++right]);
			else
				height = Math.min(height, histogram[--left]);

			maxArea = Math.max(maxArea, height * (right - left + 1));
		}

		while (right < hi) {
			height = Math.min(height, histogram[++right]);

			maxArea = Math.max(maxArea, height * (right - left + 1));
		}

		while (lo < left) {
			height = Math.min(height, histogram[--left]);

			maxArea = Math.max(maxArea, height * (right - left + 1));
		}

		return maxArea;
	}

}