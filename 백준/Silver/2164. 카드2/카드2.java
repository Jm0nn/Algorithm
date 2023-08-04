import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 카드 수
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 1; i <= n; i++)
			queue.offer(i); // n장의 카드 큐에 넣기

		while (queue.size() > 1) { // 카드의 수가 1이 될 때까지
			queue.poll(); // 제일 위 카드 제거
			queue.offer(queue.poll()); // 다음 카드 아래로 넣기
		}

		System.out.println(queue.poll()); // 남은 카드 출력

		br.close();
	}

}