import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MAX_STATION = 1_000_001;
	static int[] prev = new int[MAX_STATION];
	static int[] next = new int[MAX_STATION];

	static class Station {
		int no;
		Station prev;
		Station next;

		Station(int no, Station prev, Station next) {
			this.no = no;
			this.prev = prev;
			this.next = next;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, first, idx, i, j, result;
	static String cmd;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		first = Integer.parseInt(st.nextToken());
		prev[first] = first;
		next[first] = first;

		for (int i = 1; i < n; i++) {
			idx = Integer.parseInt(st.nextToken());
			insertNext(first, idx);
			first = idx;
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			switch (cmd) {
			case "BN":
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(insertNext(i, j)).append('\n');
				break;

			case "BP":
				i = Integer.parseInt(st.nextToken());
				j = Integer.parseInt(st.nextToken());
				sb.append(insertPrev(i, j)).append('\n');
				break;

			case "CN":
				i = Integer.parseInt(st.nextToken());
				sb.append(deleteNext(i)).append('\n');
				break;

			case "CP":
				i = Integer.parseInt(st.nextToken());
				sb.append(deletePrev(i)).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}

	static int insertNext(int i, int j) {
		result = next[i];

		next[i] = j;
		prev[result] = j;

		next[j] = result;
		prev[j] = i;

		return result;
	}

	static int insertPrev(int i, int j) {
		result = prev[i];

		next[result] = j;
		prev[i] = j;

		next[j] = i;
		prev[j] = result;

		return result;
	}

	static int deleteNext(int i) {
		result = next[i];

		next[prev[result]] = next[result];
		prev[next[result]] = prev[result];

		return result;
	}

	static int deletePrev(int i) {
		result = prev[i];

		next[prev[result]] = next[result];
		prev[next[result]] = prev[result];

		return result;
	}

}