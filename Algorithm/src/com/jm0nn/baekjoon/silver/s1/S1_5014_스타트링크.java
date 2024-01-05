package com.jm0nn.baekjoon.silver.s1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 목표 층으로 이동할 때 엘리베이터를 이용하는 횟수의 최솟값을 구하는 문제
public class S1_5014_스타트링크 {
	
	
	static int F, S, G, U, D;	// 전체 층 수, 현재 위치, 목표 위치, 위로 이동 거리, 아래로 이동 거리
	static boolean[] visit = new boolean[1_000_001];	// 방문 정보
	
	static class State {	// 현재 위치 정보를 나타내는 클래스
		int pos;	// 현재 위치
		int cnt;	// 현재 이동 횟수
		
		public State(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int cnt = bfs();	// 최소 이동 횟수를 구하기 위한 넓이 우선 탐색
		
		if (cnt == -1)	// 목표 위치에 도착하지 못하면 문구 출력
			System.out.println("use the stairs");
		else	// 이동 횟수 출력
			System.out.println(cnt);
		
		br.close();
	}
	
	// 넓이 우선 탐색
	static int bfs() {
		int[] move = { U, -D };	// 이동 거리 배열
		
		Queue<State> queue = new LinkedList<>();
		queue.add(new State(S, 0));
		visit[S] = true;
		
		while (!queue.isEmpty()) {
			State cur = queue.poll();
			
			if (cur.pos == G)	// 현재 위치가 목표 위치라면
				return cur.cnt;	// 이동 횟수 반환
			
			for (int i = 0; i < 2; i++) {	// 위 아래로 이동
				int npos = cur.pos + move[i];	// 현재 위치 갱신
				int ncnt = cur.cnt + 1;	// 이동 횟수 추가
				
//				if (npos == G)	// 이건 왜 안됨??
//					return ncnt;	// S랑 G랑 같으면 이거 안됨 개빡침ㅡㅡ
				
				// 범위 밖으로 벗어나지 않고 방문하지 않은 경우
				if ((1 <= npos && npos <= F) && !visit[npos]) {
					visit[npos] = true;	// 방문 확인 및 큐에 추가
					queue.add(new State(npos, ncnt));
				}
			}
		}
		
		return -1;	// 목표 위치에 도달하지 못하면 -1 반환
	}
}
