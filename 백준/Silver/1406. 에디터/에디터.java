import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		char c;
		Node prev, next;

		Node(char c) {
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Node head = new Node('\0');
		Node cur = head;

		String input = br.readLine();
		int len = input.length();

		for (int i = 0; i < len; ++i) {
			Node node = new Node(input.charAt(i));
			node.prev = cur;
			cur.next = node;
			cur = node;
		}

		int m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String cmd = st.nextToken();

			switch (cmd) {
				case "L":
					if (cur.c != '\0') {
						cur = cur.prev;
					}
					break;

				case "D":
					if (cur.next != null) {
						cur = cur.next;
					}
					break;

				case "B":
					if (cur.c != '\0') {
						cur = cur.prev;
						cur.next = cur.next.next;
						if (cur.next != null) {
							cur.next.prev = cur;
						}
					}
					break;

				case "P":
					char c = st.nextToken().charAt(0);
					Node node = new Node(c);
					if (cur.next != null) {
						node.next = cur.next;
						cur.next.prev = node;
					}
					node.prev = cur;
					cur.next = node;
					cur = node;
					break;
			}
		}

		for (Node node = head.next; node != null; node = node.next) {
			sb.append(node.c);
		}

		System.out.println(sb);
	}
}