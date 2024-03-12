package com.jm0nn.baekjoon.silver.s2;

import java.io.*;
import java.util.*;

public class S2_1406_에디터 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();



		int m = Integer.parseInt(br.readLine());

		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			char cmd = st.nextToken().charAt(0);

			switch (cmd) {
				case 'L':

					break;

				case 'D':

					break;

				case 'B':

					break;

				case 'P':

					break;
			}
		}

		System.out.println(sb);
	}
}
