package com.jm0nn.baekjoon.silver.s3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1번 컴퓨터가 바이러스에 걸렸을 때, 추가로 바이러스에 걸리게 되는 컴퓨터의 수를 구하는 문제
public class S3_2606_바이러스 {
	
	static int N, line, count;	// 컴퓨터의 수, 연결된 수, 감염된 컴퓨터 수
	static int[][] graph;	// 컴퓨터 연결 정보
	static boolean[] infested;	// 감염 정보
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		line = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];
		infested = new boolean[N + 1];
		
		for (int i = 0; i < line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = 1;	// 컴퓨터가 연결되어 있으면 1, 그렇지 않으면 0
		}
		
		bfs();	// 넓이 우선 탐색
		
		for (int i = 2; i <= N; i++) {
			if (infested[i])	// 감염되어 있다면 카운트 증가
				count++;
		}
		
		System.out.println(count);
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);	// 1번 컴퓨터에서 출발
		infested[1] = true;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			
			for (int i = 2; i <= N; i++) {
				// 컴퓨터가 연결되어 있고, 아직 감염되지 않은 컴퓨터라면
				if (graph[n][i] == 1 && !infested[i]) {
					infested[i] = true;	// 감염 확인
					queue.add(i);
				}
			}
		}
	}
}
