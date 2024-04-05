import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new ArrayList<>();
		String input = br.readLine();
		int len = input.length();
		for (int i = 0; i < len; ++i) {
			list.add(input.substring(i));
		}
		list.sort(Comparator.naturalOrder());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; ++i) {
			sb.append(list.get(i)).append('\n');
		}
		System.out.println(sb);
	}
}