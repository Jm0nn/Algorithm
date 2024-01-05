package com.jm0nn.baekjoon.gold.g3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_14786_AxBsinxC {

	static final double ERROR = 0.000000001;

	static int A, B, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		double left = 0;
		double right = C * 2;

		double mid = (left + right) / 2;

		while (Math.abs(right - left) >= ERROR) {
			if (f(mid) > 0)
				right = mid;
			else
				left = mid;

			mid = (left + right) / 2;
		}

		System.out.println(String.format("%.6f", mid));
	}

	static double f(double x) {
		return A * x + B * Math.sin(x) - C;
	}

}
