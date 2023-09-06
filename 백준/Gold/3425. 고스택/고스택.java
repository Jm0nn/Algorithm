import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static final long MAX_NUM = 1_000_000_000L;

	static class GoStack {
		long[] stack;
		int size;
		int top;

		GoStack(int size, int start) {
			this.size = size + 1;
			stack = new long[size + 1];
			stack[top++] = start;
		}

		void num(int x) {
			stack[top++] = x;
		}

		boolean pop() {
			if (--top < 0)
				return false;
			return true;
		}

		boolean inv() {
			if (top == 0)
				return false;
			stack[top - 1] *= -1;
			return true;
		}

		boolean dup() {
			if (top == 0)
				return false;
			stack[top] = stack[top++ - 1];
			return true;
		}

		boolean swp() {
			if (top < 2)
				return false;
			long tmp = stack[top - 1];
			stack[top - 1] = stack[top - 2];
			stack[top - 2] = tmp;
			return true;
		}

		boolean add() {
			if (top < 2)
				return false;
			stack[top - 2] += stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		boolean sub() {
			if (top < 2)
				return false;
			stack[top - 2] -= stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		boolean mul() {
			if (top < 2)
				return false;
			stack[top - 2] *= stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		boolean div() {
			if (top < 2 || stack[top - 1] == 0)
				return false;
			stack[top - 2] /= stack[--top];
			return true;
		}

		boolean mod() {
			if (top < 2 || stack[top - 1] == 0)
				return false;
			stack[top - 2] %= stack[--top];
			return true;
		}

		long result() {
			return stack[0];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		program: while (true) {
			List<String> cmdList = new ArrayList<>();

			String s = "";
			while (!(s = br.readLine()).equals("END")) {
				if (s.equals("QUIT"))
					break program;
				cmdList.add(s);
			}

			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				int v = Integer.parseInt(br.readLine());
				GoStack gs = new GoStack(cmdList.size(), v);

				boolean isValid = true;

				for (String cmd : cmdList) {
					st = new StringTokenizer(cmd);
					String cmd1 = st.nextToken();

					switch (cmd1) {
					case "NUM":
						gs.num(Integer.parseInt(st.nextToken()));
						break;

					case "POP":
						if (!gs.pop())
							isValid = false;
						break;

					case "INV":
						if (!gs.inv())
							isValid = false;
						break;

					case "DUP":
						if (!gs.dup())
							isValid = false;
						break;

					case "SWP":
						if (!gs.swp())
							isValid = false;
						break;

					case "ADD":
						if (!gs.add())
							isValid = false;
						break;

					case "SUB":
						if (!gs.sub())
							isValid = false;
						break;

					case "MUL":
						if (!gs.mul())
							isValid = false;
						break;

					case "DIV":
						if (!gs.div())
							isValid = false;
						break;

					case "MOD":
						if (!gs.mod())
							isValid = false;
						break;
					}

					if (!isValid)
						break;
				}

				if (!isValid || gs.top != 1)
					sb.append("ERROR\n");
				else
					sb.append(gs.result()).append('\n');
			}

			sb.append('\n');
			br.readLine();
		}

		System.out.println(sb);
	}

}