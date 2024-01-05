package com.jm0nn.baekjoon.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q_Queueueue {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] hqueue = new int[n];
		int[] vqueue = new int[n];

		int hhead = 0, htail = 0, vhead = 0, vtail = 0;
		int middle = 0;

		for (int cmd = 0; cmd < n; ++cmd) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x;
			switch (st.nextToken()) {
			case "hpush":
				x = Integer.parseInt(st.nextToken());
				hqueue[htail++] = x;

				if (vtail - vhead == 0) {
					vqueue[vtail++] = x;
					middle = x;
				} else {
					middle = hqueue[(htail + hhead - 1) / 2];
					vqueue[(vtail + vhead - 1) / 2] = middle;
				}
				break;

			case "hpop":
				if (htail - hhead == 0)
					sb.append("-1\n");
				else {
					sb.append(hqueue[hhead++]).append('\n');

					if (htail - hhead == 0) {
						if (vtail - vhead == 1)
							vtail = vhead;
						else {
							for (int i = (vtail + vhead - 1) / 2 + 1; i < vtail; ++i)
								vqueue[i - 1] = vqueue[i];
							middle = vqueue[(--vtail + vhead - 1) / 2];
							hqueue[htail++] = middle;
						}
					} else {
						middle = hqueue[(htail + hhead - 1) / 2];
						vqueue[(vtail + vhead - 1) / 2] = middle;
					}
				}
				break;

			case "hfront":
				if (htail - hhead == 0)
					sb.append("-1\n");
				else
					sb.append(hqueue[hhead]).append('\n');
				break;

			case "hback":
				if (htail - hhead == 0)
					sb.append("-1\n");
				else
					sb.append(hqueue[htail - 1]).append('\n');
				break;

			case "hsize":
				sb.append(htail - hhead).append('\n');
				break;

			case "vpush":
				x = Integer.parseInt(st.nextToken());
				vqueue[vtail++] = x;

				if (htail - hhead == 0) {
					vqueue[htail++] = x;
					middle = x;
				} else {
					middle = vqueue[(vtail + vhead - 1) / 2];
					hqueue[(htail + hhead - 1) / 2] = middle;
				}
				break;

			case "vpop":
				if (vtail - vhead == 0)
					sb.append("-1\n");
				else {
					sb.append(vqueue[vhead++]).append('\n');

					if (vtail - vhead == 0) {
						if (htail - hhead == 1)
							htail = hhead;
						else {
							for (int i = (htail + hhead - 1) / 2 + 1; i < htail; ++i)
								hqueue[i - 1] = hqueue[i];
							middle = hqueue[(--htail + hhead - 1) / 2];
							vqueue[vtail++] = middle;
						}
					} else {
						middle = vqueue[(vtail + vhead - 1) / 2];
						hqueue[(htail + hhead - 1) / 2] = middle;
					}
				}
				break;

			case "vfront":
				if (vtail - vhead == 0)
					sb.append("-1\n");
				else
					sb.append(vqueue[vhead]).append('\n');
				break;

			case "vback":
				if (vtail - vhead == 0)
					sb.append("-1\n");
				else
					sb.append(vqueue[vtail - 1]).append('\n');
				break;

			case "vsize":
				sb.append(vtail - vhead).append('\n');
				break;

			case "size":
				if (htail - hhead + vtail - vhead == 0)
					sb.append("0\n");
				else
					sb.append(htail - hhead + vtail - vhead - 1).append('\n');
				break;

			case "empty":
				if (htail - hhead + vtail - vhead == 0)
					sb.append("1\n");
				else
					sb.append("0\n");
				break;

			case "middle":
				if (htail - hhead + vtail - vhead == 0)
					sb.append("-1\n");
				else
					sb.append(middle).append('\n');
				break;
			}
		}

		System.out.println(sb);
	}

}
