package com.jm0nn.baekjoon.silver.s2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 부모 자식 간의 관계 내에서 주어진 두 사람의 촌수를 계산하는 문제
public class S2_2644_촌수계산_BFS {
	
	static int N, line, p, target, cnt;	// 사람 수, 관계 수, 첫 번째 사람, 첫 번째 사람과의 촌수를 구할 타겟, 촌수
	static int[][] graph;	// 관계 정보
	static boolean[] visit;	// 방문 정보
	
	// 사람 정보
	static class Person {
		int num;	// 사람 번호
		int idx;	// 촌 수
		
		public Person(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}
	
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
		
		System.out.println(bfs());	// 넓이 우선 탐색으로 두 사람의 촌수 계산
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static int bfs() {
		Queue<Person> queue = new LinkedList<>();
		queue.add(new Person(p, cnt));
		visit[p] = true;	// 방문 확인
		
		while (!queue.isEmpty()) {
			Person cur = queue.poll();
			int num = cur.num;	// 현재 사람 번호
			int idx = cur.idx;	// 현재 사람 촌수
			
			for (int i = 1; i <= N; i++) {
				// 관계가 있으며 방문한 적 없는 사람이면
				if (graph[num][i] == 1 && !visit[i]) {
					queue.add(new Person(i, idx + 1));	// 큐에 사람 정보 추가
					visit[i] = true;	// 방문 확인
					
					if (visit[target])	// 타겟에 방문했다면
						return idx + 1;	// 촌수 반환
				}
			}
		}
		
		return -1;	// 타겟에 방문하지 못했다면 -1 반환
	}
}
