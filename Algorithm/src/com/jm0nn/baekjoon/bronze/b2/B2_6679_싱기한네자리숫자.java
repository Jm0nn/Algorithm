package com.jm0nn.baekjoon.bronze.b2;

public class B2_6679_싱기한네자리숫자 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

		for (int i = 1000; i < 10000; ++i) {
			int num10 = i;
			int num12 = i;
			int num16 = i;

			int sum10 = 0;
			int sum12 = 0;
			int sum16 = 0;

			while (num10 > 0) {
				sum10 += num10 % 10;
				num10 /= 10;
			}

			while (num12 > 0) {
				sum12 += num12 % 12;
				num12 /= 12;
			}

			while (num16 > 0) {
				sum16 += num16 % 16;
				num16 /= 16;
			}

			if (sum10 == sum12 && sum12 == sum16)
				sb.append(i).append('\n');
		}

		System.out.println(sb);
	}

}
