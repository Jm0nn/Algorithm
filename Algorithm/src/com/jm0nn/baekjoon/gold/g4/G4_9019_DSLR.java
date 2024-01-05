package com.jm0nn.baekjoon.gold.g4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_9019_DSLR {

	static class DSLR {
		int num;
		String cmd;

		DSLR(int num, String cmd) {
			this.num = num;
			this.cmd = cmd;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			boolean[] visit = new boolean[10000];
			Queue<DSLR> queue = new ArrayDeque<>();

			visit[A] = true;
			queue.offer(new DSLR(A, ""));

			while (!queue.isEmpty()) {
				DSLR cur = queue.poll();
				int num = cur.num;
				String cmd = cur.cmd;

				if (num == B) {
					sb.append(cmd).append('\n');
					break;
				}

				int D = (num * 2) % 10000;

				if (!visit[D]) {
					visit[D] = true;
					queue.offer(new DSLR(D, cmd + "D"));
				}

				int S = num - 1 >= 0 ? num - 1 : 9999;

				if (!visit[S]) {
					visit[S] = true;
					queue.offer(new DSLR(S, cmd + "S"));
				}

				int L = (num * 10 + num / 1000) % 10000;

				if (!visit[L]) {
					visit[L] = true;
					queue.offer(new DSLR(L, cmd + "L"));
				}

				int R = (num / 10 + (num % 10) * 1000);

				if (!visit[R]) {
					visit[R] = true;
					queue.offer(new DSLR(R, cmd + "R"));
				}
			}
		}

		System.out.println(sb);
	}

}
