package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열 돌리는 문제
public class P5_17470_배열돌리기5 {

	static int n, m, r, tmpNum, isNM; // n, m: 배열 크기, r: 돌리는 횟수, tmpNum: swap할 때 임시 공간
	static int[] tmpArr; // swap할 때 임시 배열
	static int[][] arr, tmpArrNM, tmpArrMN; // arr: 연산을 적용할 배열, tmpArr2: swap할 때 임시 배열

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		tmpArrNM = new int[n][m];
		tmpArrMN = new int[m][n];
		isNM = 1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		while (r-- > 0) {
			switch (Integer.parseInt(st.nextToken())) {
			case 1: // 상하 반전
				for (int i = 0; i < n / 2; i++) {
					tmpArr = arr[i];
					arr[i] = arr[n - i - 1];
					arr[n - i - 1] = tmpArr;
				}
				break;

			case 2: // 좌우 반전
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m / 2; j++) {
						tmpNum = arr[i][j];
						arr[i][j] = arr[i][m - j - 1];
						arr[i][m - j - 1] = tmpNum;
					}
				}
				break;

			case 3: // 오른쪽 90도 회전

				if (isNM == 0) {
					for (int i = 0; i < n; i++)
						for (int j = 0; j < m; j++)
							tmpArrNM[j][n - i - 1] = arr[i][j];
					arr = tmpArrNM;
				} else {
					for (int i = 0; i < n; i++)
						for (int j = 0; j < m; j++)
							tmpArrMN[j][n - i - 1] = arr[i][j];
					arr = tmpArrMN;
				}

				tmpNum = n;
				n = m;
				m = tmpNum;
				isNM = 1 - isNM;

				break;

			case 4: // 왼쪽 90도 회전
				if (isNM == 0) {
					for (int i = 0; i < n; i++)
						for (int j = 0; j < m; j++)
							tmpArrNM[m - j - 1][i] = arr[i][j];
					arr = tmpArrNM;
				} else {
					for (int i = 0; i < n; i++)
						for (int j = 0; j < m; j++)
							tmpArrMN[m - j - 1][i] = arr[i][j];
					arr = tmpArrMN;
				}

				tmpNum = n;
				n = m;
				m = tmpNum;
				isNM = 1 - isNM;

				break;

			case 5: // 사분면 오른쪽으로 회전
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						tmpNum = arr[i][j];
						arr[i][j] = arr[i + n / 2][j];
						arr[i + n / 2][j] = arr[i + n / 2][j + m / 2];
						arr[i + n / 2][j + m / 2] = arr[i][j + m / 2];
						arr[i][j + m / 2] = tmpNum;
					}
				}
				break;

			case 6: // 사분면 왼쪽으로 회전
				for (int i = 0; i < n / 2; i++) {
					for (int j = 0; j < m / 2; j++) {
						tmpNum = arr[i][j];
						arr[i][j] = arr[i][j + m / 2];
						arr[i][j + m / 2] = arr[i + n / 2][j + m / 2];
						arr[i + n / 2][j + m / 2] = arr[i + n / 2][j];
						arr[i + n / 2][j] = tmpNum;
					}
				}
				break;
			}
		}

		// 최종 배열 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}

		System.out.println(sb);
	}

}
