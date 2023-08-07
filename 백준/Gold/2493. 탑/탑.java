import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑에서 레이저를 발사해서 어느 탑에서 수신하는지 알아내는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine()); // 탑의 수
		int[] tower = new int[n + 1]; // 탑 배열, 인덱스 1부터 시작

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			tower[i] = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>(); // 탑 높이 스택
		Stack<Integer> index = new Stack<>(); // 인덱스 스택
		int[] result = new int[n + 1]; // 결과를 출력할 배열

		// 가장 뒷쪽 탑부터 비교
		for (int i = n; i > 0; i--) {
			int height = tower[i]; // 현재 탑의 높이

			// 스택 안의 높이와 현재 높이를 비교하기 때문에 스택이 차 있을때만 비교
			// 스택 안의 높이가 현재 높이보다 작아거나 같아야 레이저가 현재 탑에 도달
			while (!stack.isEmpty() && height >= stack.peek()) {
				// 결과 배열에 현재 탑의 인덱스 저장 후 스택에서 값을 꺼냄
				result[index.pop()] = i;
				stack.pop();
			}

			// 스택에 현재 탑의 높이와 인덱스 넣어서 앞쪽의 탑과 비교
			stack.push(height);
			index.push(i);
		}

		// 결과 출력
		for (int i = 1; i <= n; i++)
			sb.append(result[i]).append(' ');

		System.out.println(sb);

		br.close();
	}
}