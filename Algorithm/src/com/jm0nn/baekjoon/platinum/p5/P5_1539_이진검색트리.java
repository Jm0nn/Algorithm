package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class P5_1539_이진검색트리 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		long sum = 0;

		TreeMap<Integer, Integer> map = new TreeMap<>();

		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine());

			Entry<Integer, Integer> floor = map.floorEntry(num);
			Entry<Integer, Integer> ceiling = map.ceilingEntry(num);

			int h = -1;
			if (floor == null && ceiling == null) {
				h = 1;
			} else if (floor == null) {
				h = ceiling.getValue() + 1;
			} else if (ceiling == null) {
				h = floor.getValue() + 1;
			} else {
				int f = floor.getValue();
				int c = ceiling.getValue();

				if (f < c) {
					h = c + 1;
				} else {
					h = f + 1;
				}
			}

			map.put(num, h);
			sum += h;
		}

		System.out.println(sum);
	}

}
