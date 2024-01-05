package com.jm0nn.swea.d2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_14510_나무높이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			sb.append('#').append(tc).append(' ');
			
			int n = Integer.parseInt(br.readLine());
			
			int[] tree = new int[n];
			
			boolean same = true;
			
			st = new StringTokenizer(br.readLine());
			tree[0] = Integer.parseInt(st.nextToken());
			int max = tree[0];
			for (int i = 1; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				if (max < tree[i])
					max = tree[i];
				if (tree[i] != tree[0])
					same = false;
			}
			
			if (same) {
				sb.append(0).append('\n');
				continue;
			}
			
			int day = 1;
			
			while (true) {
				boolean finish = false;
				
				if (day % 2 == 1) {
					int min = Integer.MAX_VALUE;
					int idx = 0;
					boolean water = false;
					int cnt = 0;
					for (int i = 0; i < n; i++) {
						if (!water && tree[i] == max - 1) {
							tree[i]++;
							water = true;
						}
						
						if (min > tree[i]) {
							min = tree[i];
							idx = i;
						}
						
						if (tree[i] < max)
							cnt++;
					}
					
					if (!water && cnt > 1)
						tree[idx]++;
					
					if (cnt == 0)
						finish = true;
				} else {
					int min = Integer.MAX_VALUE;
					int idx = 0;
					int cnt = 0;
					for (int i = 0; i < n; i++) {
						if (min > tree[i]) {
							min = tree[i];
							idx = i;
						}
						
						if (tree[i] < max)
							cnt++;
					}
					
					if (tree[idx] <= max - 2) {
						tree[idx] += 2;
						if (tree[idx] == max)
							cnt--;
					}
					
					if (cnt == 0)
						finish = true;
				}
				
				if (finish)
					break;
				
				day++;
			}
			
			sb.append(day).append('\n');
		}
		
		System.out.println(sb);
	}

}
