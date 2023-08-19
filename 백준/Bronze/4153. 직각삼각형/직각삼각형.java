import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = br.readLine();
			if (s.equals("0 0 0"))
				break;
			String[] split = s.split(" ");
			int[] arr = new int[3];
			for (int i = 0; i < 3; i++)
				arr[i] = Integer.parseInt(split[i]);
			Arrays.sort(arr);
			if (arr[0] * arr[0] + arr[1] * arr[1] == arr[2] * arr[2])
				sb.append("right\n");
			else
				sb.append("wrong\n");
		}
		System.out.println(sb);
	}
}