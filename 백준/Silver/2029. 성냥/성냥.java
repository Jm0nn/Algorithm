import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = 10;
		char[][] matches = new char[L][L];

		int cnt = 0;

		for (int i = 0; i < L; ++i) {
			String input = br.readLine();
			for (int j = 0; j < L; ++j) {
				char c = input.charAt(j);
				matches[i][j] = input.charAt(j);
				if (c == '-' || c == '|') {
					++cnt;
				}
			}
		}

		int A = 24 - cnt / 2;
		int B = 0;

		for (int i = 0; i < L; ++i) {
			for (int j = 0; j < L; ++j) {
				if (matches[i][j] != '+') {
					continue;
				}

				loop:
				for (int s = 3; s <= 9; s += 3) {
					for (int k = i; k <= i + s; ++k) {
						for (int l = j; l <= j + s; ++l) {
							if (k != i && k != i + s && l != j && l != j + s) {
								continue;
							} else if (k >= L || l >= L) {
								break loop;
							}

							if (matches[k][l] == '.') {
								continue loop;
							}
						}
					}

					++B;
				}
			}
		}

		System.out.println(A + " " + B);
	}

}