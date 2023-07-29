import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 정수를 저장하는 큐를 사용하는 문제
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 명령의 횟수
		Queue<Integer> queue = new LinkedList<>(); // 정수를 저장하는 큐
		int num = 0; // 큐에 마지막으로 추가 할 정수

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 명령 입력

			switch (st.nextToken()) {
			case "push": // 큐에 정수 추가
				num = Integer.parseInt(st.nextToken()); // 큐에 가장 마지막에 추가된 정수
				queue.add(num);
				break;

			case "pop": // 큐에서 정수 빼고 그 수 출력
				if (queue.isEmpty()) // 큐가 비어있다면
					sb.append(-1).append('\n'); // -1 출력
				else
					sb.append(queue.poll()).append('\n');
				break;

			case "size": // 큐의 크기 출력
				sb.append(queue.size()).append('\n');
				break;

			case "empty": // 큐가 비어있는지 확인
				if (queue.isEmpty()) // 비어있으면 1 출력
					sb.append(1).append('\n');
				else // 비어있지 않으면 0 출력
					sb.append(0).append('\n');
				break;

			case "front": // 큐의 가장 앞 정수 출력
				if (queue.isEmpty()) // 큐가 비어있다면
					sb.append(-1).append('\n'); // -1 출력
				else
					sb.append(queue.peek()).append('\n');
				break;

			case "back": // 큐의 가장 뒷 정수 출력
				if (queue.isEmpty()) // 큐가 비어있다면
					sb.append(-1).append('\n'); // -1 출력
				else { // 가장 마지막에 큐에 넣은 정수 출력
					sb.append(num).append('\n');
				}
				break;
			}
		}

		System.out.println(sb);

		br.close();
	}
}