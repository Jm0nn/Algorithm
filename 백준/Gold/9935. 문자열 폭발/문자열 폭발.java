import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String bomb = br.readLine();

		int len = str.length();
		StrStack ss = new StrStack(len, bomb);

		for (int i = 0; i < len; i++)
			ss.push(str.charAt(i));

		System.out.println(ss.toString());
	}

	static class StrStack {
		int initial_capacity;
		int top;
		char[] stack;
		char[] bomb;
		int len;

		StrStack(int initial_capacity, String bomb) {
			this.initial_capacity = initial_capacity;
			this.bomb = bomb.toCharArray();

			len = bomb.length();
			stack = new char[initial_capacity];
		}

		void push(char c) {
			stack[top++] = c;

			if (top < len)
				return;

			if (c == bomb[len - 1]) {
				boolean delete = true;
				for (int i = 0; i < len; i++) {
					if (stack[top - len + i] != bomb[i]) {
						delete = false;
						break;
					}
				}

				if (delete) {
					top -= len;
					return;
				}
			}
		}

		@Override
		public String toString() {
			if (top == 0)
				return "FRULA";
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < top; i++)
				sb.append(stack[i]);
			return sb.toString();
		}
	}
}