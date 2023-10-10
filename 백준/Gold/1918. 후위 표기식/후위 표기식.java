import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 주어진 수식을 후위 표기식으로 고치는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Character> stack = new Stack<>();

		String input = br.readLine();
		int len = input.length();

		for (int i = 0; i < len; ++i) {
			char c = input.charAt(i); // 현재 입력된 문자

			switch (c) {
			// 괄호 앞부분
			case '(':
				stack.push(c); // 스택에 저장
				break;

			// 괄호 뒷부분
			case ')':
				// 괄호 앞부분이 나올 때까지 스택에서 꺼내서 출력
				while (!stack.isEmpty() && stack.peek() != '(')
					sb.append(stack.pop());

				// 괄호 앞부분 스택에서 제거
				if (!stack.isEmpty())
					stack.pop();
				break;

			// 연산자
			case '+':
			case '-':
			case '*':
			case '/':
				// 현재 입력된 연산자와 스택의 가장 윗쪽 문자를 비교
				// 스택의 연산자가 우선순위가 높으면 꺼내서 출력
				while (!stack.isEmpty() && priority(stack.peek()) >= priority(c))
					sb.append(stack.pop());

				// 입력된 연산자 스택에 저장
				stack.push(c);
				break;

			// 알파벳
			default:
				sb.append(c); // 그대로 출력
			}
		}

		// 스택에 남아있는 연산자 모두 출력
		while (!stack.isEmpty())
			sb.append(stack.pop());

		System.out.println(sb);
	}

	// 연산자 우선순위, 높을수록 우선순위 높음
	static int priority(char c) {
		// 곱하기, 나누기 연산자는 우선순위가 가장 높음
		if (c == '*' || c == '/')
			return 2;

		// 더하기, 빼기 연산자
		if (c == '+' || c == '-')
			return 1;

		// 괄호 앞부분은 출력하면 안되므로 우선순위가 가장 낮음
		return 0;
	}

}