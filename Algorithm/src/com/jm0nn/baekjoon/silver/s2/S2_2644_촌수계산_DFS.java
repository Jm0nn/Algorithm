package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부모 자식 간의 관계 내에서 주어진 두 사람의 촌수를 계산하는 문제
public class S2_2644_촌수계산_DFS {
	
	static int N, line;	// 사람 수, 관계 수, 첫 번째 사람, 
	static int p, target;	// 첫 번째 사람, 첫 번째 사람과의 촌수를 구할 타겟
	static int cnt;	// 촌 수
	static int[][] graph;	// 관계 정보
	static boolean[] visit;	// 방문 정보
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];
		visit = new boolean[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		
		line = Integer.parseInt(br.readLine());
		for (int i = 0; i < line; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = 1;	// 부모 관계라면 관계 정보 추가
		}
		
		dfs(p, target);	// 깊이 우선 탐색으로 두 사람의 촌수 계산
		
		if (visit[target])	// 타겟에 방문했다면 촌수 출력
			System.out.println(cnt);
		else	// 타겟에 방문하지 못했다면 관계가 없으므로 -1 출력
			System.out.println(-1);
		
		br.close();
	}
	
	// 깊이 우선 탐색
	static void dfs(int p, int target) {
		visit[p] = true;	// 방문 확인
		
		if (visit[target])	// 타겟에 방문했다면 탐색 종료
			return;
		
		for (int i = 1; i <= N; i++) {
			// 관계가 있고, 방문한 적 없는 사람일 때
			if (graph[p][i] == 1 && !visit[i]) {
				cnt++;	// 촌수 1 증가 및 탐색 계속
				dfs(i, target);
				
				if (visit[target])	// 타겟에 방문했다면 탐색 종료
					return;
				
				cnt--;	// 잘못된 탐색이라면 촌수 1 감소
			}
		}
	}
}
