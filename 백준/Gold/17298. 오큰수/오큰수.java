import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 수열의 각 원소 A_i에 대해서 A_i의 오른쪽에 있으면서 A_i보다 큰 수 중에서 가장 왼쪽에 있는 수를 구하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 수열의 원소의 개수
		int[] NGE = new int[N]; // 오큰수 배열
		IStack istack = new IStack(N); // 인덱스를 저장할 수 있는 스택

		Arrays.fill(NGE, -1); // NGE(i)가 없으면 -1

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken()); // i번째 수

			// 스택 내의 수가 현재 입력받은 수보다 작으면
			// 스택에서 꺼내서 해당 인덱스에 현재 입력받은 수를 NGE 배열에 저장함
			// 스택에 있는 수는 내림차순으로 저장됨
			while (!istack.isEmpty()) {
				// 스택의 top에 있는 수가 현재 입력받은 수보다 크거나 같으면 break
				if (istack.peek()[0] >= num)
					break;

				NGE[istack.pop()[1]] = num; // 스택에서 꺼낸 수의 인덱스에 현재 수를 넣음
			}

			istack.push(num, i); // 현재 수 스택에 push
		}

		// NGE(i) 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append(NGE[i]).append(' ');

		System.out.println(sb);
	}

	// 인덱스를 저장할 수 있는 스택
	static class IStack {
		int[][] stack;
		int top;

		IStack(int capacity) {
			stack = new int[capacity][2];
		}

		void push(int num, int idx) {
			stack[top][0] = num;
			stack[top++][1] = idx;
		}

		int[] peek() {
			return stack[top - 1];
		}

		int[] pop() {
			return stack[--top];
		}

		boolean isEmpty() {
			if (top == 0)
				return true;
			return false;
		}
	}
}