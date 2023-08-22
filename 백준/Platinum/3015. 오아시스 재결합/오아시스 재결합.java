import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 일렬로 사람이 서 있을 때 두 사람 사이에 둘 중 한명보다 키가 큰 사람이 없는 쌍의 수를 구하는 문제
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 사람 수

		Stack<long[]> stack = new Stack<>();

		long cnt = 0; // 쌍의 수

		while (n-- > 0) {
			long i = Long.parseLong(br.readLine());
			long[] input = { i, 1 }; // 사람의 키, 해당 사람의 수

			// 스택을 키의 내림차순으로 유지해야 함 (같은 키 없이)
			// 스택의 peek이 내 키보다 작거나 같으면 꺼내서 확인
			while (!stack.isEmpty() && stack.peek()[0] <= i) {
				long[] pop = stack.pop(); // 스택에서 꺼냄
				cnt += pop[1]; // 꺼낸 키의 사람 수만큼 카운트 증가

				if (pop[0] == i) // 스택에서 꺼낸 사람이 키가 똑같다면
					input[1] += pop[1]; // 사람 수 더함
			}

			if (!stack.isEmpty())
				cnt++; // 스택에 사람이 남아있다면 카운트 증가

			stack.push(input); // 스택에 입력받은 키 push
		}

		System.out.println(cnt);
	}

}