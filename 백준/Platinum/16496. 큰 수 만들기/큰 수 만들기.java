import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] num = new String[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i)
			num[i] = st.nextToken();

		Arrays.sort(num, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

		if (num[0].equals("0"))
			System.out.println(0);
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; ++i)
				sb.append(num[i]);
			System.out.println(sb);
		}
	}

}