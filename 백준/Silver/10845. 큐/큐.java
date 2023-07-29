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

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 명령 입력

			switch (st.nextToken()) {
			case "push": // 큐에 정수 추가
				queue.add(Integer.parseInt(st.nextToken()));
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
				else { // 큐의 가장 뒷 요소를 보기 위해 큐를 배열의 형태로 바꾼 후 가장 마지막 요소를 출력
					Integer[] arr = new Integer[queue.size()];
					queue.toArray(arr);
					sb.append(arr[queue.size() - 1]).append('\n');
				}
				break;
			}
		}

		System.out.println(sb);

		br.close();
	}
}