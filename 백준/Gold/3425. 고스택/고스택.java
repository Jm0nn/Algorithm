import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 고스택 프로그램을 사용하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		program: while (true) {
			List<String> cmdList = new ArrayList<>(); // 커맨드 리스트

			String s; // 커맨드 입력
			// END가 나올 때 까지 입력
			while ((s = br.readLine()).charAt(0) != 'E') {
				// QUIT이 입력되면 프로그램 종료
				if (s.charAt(0) == 'Q')
					break program;
				cmdList.add(s); // 입력받은 커맨드 리스트에 저장
			}

			// 연산을 수행할 횟수
			int n = Integer.parseInt(br.readLine());
			while (n-- > 0) {
				int v = Integer.parseInt(br.readLine()); // 연산이 이뤄질 수
				GoStack gs = new GoStack(cmdList.size() + 1, v); // 고스택
				boolean isNotError = true; // 에러 여부

				// 커맨드 리스트에 저장된 커맨드를 순서대로 실행
				for (String cmd : cmdList) {
					// 에러 발생 시 프로그램 종료
					if (!(isNotError = gs.command(cmd)))
						break;
				}

				// 에러가 발생했거나 고스택에 남은 수가 한 개가 아니라면 에러 출력
				// 프로그램이 정상적으로 종료되어 수가 한 개가 남았다면 해당 수 출력
				if (!isNotError || gs.top != 1)
					sb.append("ERROR\n");
				else
					sb.append(gs.result()).append('\n');
			}

			sb.append('\n');
			br.readLine(); // 공백 입력
		}

		System.out.println(sb);
	}

	// 고스택 클래스
	static class GoStack {
		final long MAX_NUM = 1_000_000_000L; // 고스택 내 수의 최댓값

		long[] stack; // 스택 배열
		int top; // 스택 내 수의 개수 (스택의 가장 위에 저장된 수의 인덱스 + 1)

		// 처음에 스택에 start를 넣고 시작
		GoStack(int size, int start) {
			stack = new long[size];
			stack[top++] = start;
		}

		// x를 스택의 가장 위에 저장
		void num(int x) {
			stack[top++] = x;
		}

		// 스택 가장 위의 수를 제거
		// 스택에 수가 없으면 false
		boolean pop() {
			if (--top < 0)
				return false;
			return true;
		}

		// 스택 가장 위의 수의 부호를 바꿈
		// 스택에 수가 없으면 false
		boolean inv() {
			if (top == 0)
				return false;
			stack[top - 1] *= -1;
			return true;
		}

		// 스택의 가장 위의 수를 하나 더 저장
		// 스택에 수가 없으면 false
		boolean dup() {
			if (top == 0)
				return false;
			stack[top] = stack[top++ - 1];
			return true;
		}

		// 스택의 가장 위 두 수의 위치를 바꿈
		// 바꿀 수가 없으면 false
		boolean swp() {
			if (top < 2)
				return false;
			long tmp = stack[top - 1];
			stack[top - 1] = stack[top - 2];
			stack[top - 2] = tmp;
			return true;
		}

		// 스택의 가장 위 두 수를 꺼내서 합을 저장
		// 꺼낼 수가 없거나 저장한 수의 절댓값이 최댓값을 넘으면 false
		boolean add() {
			if (top < 2)
				return false;
			stack[top - 2] += stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		// 스택의 가장 위 두 수를 꺼내서 차를 저장
		// 꺼낼 수가 없거나 저장한 수의 절댓값이 최댓값을 넘으면 false
		boolean sub() {
			if (top < 2)
				return false;
			stack[top - 2] -= stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		// 스택의 가장 위 두 수를 꺼내서 곱을 저장
		// 꺼낼 수가 없거나 저장한 수의 절댓값이 최댓값을 넘으면 false
		boolean mul() {
			if (top < 2)
				return false;
			stack[top - 2] *= stack[--top];
			if (Math.abs(stack[top - 1]) > MAX_NUM)
				return false;
			return true;
		}

		// 스택의 가장 위 두 수를 꺼내서 나눗셈의 몫을 저장
		// 꺼낼 수가 없거나 나누는 수가 0이면 false
		boolean div() {
			if (top < 2 || stack[top - 1] == 0)
				return false;
			stack[top - 2] /= stack[--top];
			return true;
		}

		// 스택의 가장 위 두 수를 꺼내서 나눗셈의 나머지를 저장
		// 꺼낼 수가 없거나 나누는 수가 0이면 false
		boolean mod() {
			if (top < 2 || stack[top - 1] == 0)
				return false;
			stack[top - 2] %= stack[--top];
			return true;
		}

		// 커맨드 실행
		boolean command(String cmd) {
			switch (cmd) {
			case "POP":
				return pop();
			case "INV":
				return inv();
			case "DUP":
				return dup();
			case "SWP":
				return swp();
			case "ADD":
				return add();
			case "SUB":
				return sub();
			case "MUL":
				return mul();
			case "DIV":
				return div();
			case "MOD":
				return mod();
			}
			num(Integer.parseInt(cmd.substring(4)));
			return true;
		}

		// 스택에 남은 수를 출력
		long result() {
			return stack[0];
		}
	}
}