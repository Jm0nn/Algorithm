package com.jm0nn.baekjoon.platinum.p5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

// 앨범정리 프로그램
public class P5_20541_앨범정리 {

	static Album head = new Album("album", null); // 최상위 앨범
	static Album current = head; // 현재 앨범, 프로그램 시작시 최상위 앨범이 선택됨

	static int delAlbum, delPhoto; // 삭제한 앨범, 사진의 갯수

	// 앨범 클래스(트리 구조)
	public static class Album {
		String name; // 앨범 이름
		Album parent; // 상위 앨범
		SortedMap<String, Album> children; // 하위 앨범 (key: 앨범 이름, value: 앨범 정보)
		SortedSet<String> photos; // 앨범에 속한 사진

		// 앨범 생성 시 앨범 이름과 상위 앨범을 반드시 추가해야 함
		public Album(String name, Album parent) {
			this.name = name;
			this.parent = parent;

			children = new TreeMap<>();
			photos = new TreeSet<>();
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 명령어 갯수

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			command(st.nextToken(), st.nextToken()); // 명령어를 받아 프로그램 실행
		}

		System.out.println(sb);

		br.close();
	}

	// 명령어 실행 메서드
	static void command(String cmd, String s) {

		switch (cmd) {
		case "mkalb": // 앨범을 생성하는 명령어
			mkalb(s);
			break;

		case "rmalb": // 앨범을 제거하는 명령어
			rmalb(s);
			break;

		case "insert": // 사진을 삽입하는 명령어
			insert(s);
			break;

		case "delete": // 사진을 삭제하는 명령어
			delete(s);
			break;

		case "ca": // 앨범 이동하는 명령어
			ca(s);
			break;
		}
	}

	// 앨범을 생성하는 메서드
	static void mkalb(String s) {

		// 현재 앨범에 같은 이름의 앨범이 있다면 앨범을 생성하지 않고 문구 출력 후 리턴
		if (current.children.containsKey(s)) {
			sb.append("duplicated album name\n");
			return;
		}

		// 현재 앨범의 하위 앨범으로 생성
		current.children.put(s, new Album(s, current));
	}

	// 앨범을 삭제하는 메서드
	static void rmalb(String s) {

		// 삭제한 앨범과 사진의 갯수 초기화
		delAlbum = 0;
		delPhoto = 0;

		switch (s) {
		case "-1": // 현재 앨범 내에서 이름이 사전순으로 가장 빠른 앨범 삭제
			if (!current.children.isEmpty()) {
				String name = current.children.firstKey(); // 가장 빠른 앨범 이름
				deleteAlbum(current.children.get(name)); // 해당 앨범 삭제
				current.children.remove(name);
				delAlbum++; // 삭제한 앨범의 갯수 증가
			}
			break;

		case "0": // 현재 앨범에 속해 있는 모든 앨범 삭제
			for (String name : current.children.keySet()) // 하위 앨범 이름으로 순회
				deleteAlbum(current.children.get(name)); // 하위 앨범 삭제

			delAlbum += current.children.size(); // 삭제한 앨범의 갯수 더함
			current.children.clear(); // 현재 앨범의 하위 앨범 초기화
			break;

		case "1": // 현재 앨범 내에서 이름이 사전순으로 가장 늦은 앨범 삭제
			if (!current.children.isEmpty()) {
				String name = current.children.lastKey(); // 가장 늦은 앨범 이름
				deleteAlbum(current.children.get(name)); // 해당 앨범 삭제
				current.children.remove(name);
				delAlbum++; // 삭제한 앨범의 갯수 증가
			}
			break;

		default: // 해당 이름을 가진 앨범 삭제
			// 현재 앨범에 해당 이름의 앨범이 있다면 삭제
			if (current.children.containsKey(s)) {
				deleteAlbum(current.children.get(s));
				current.children.remove(s);
				delAlbum++; // 삭제한 앨범의 갯수 증가
				break;
			}
		}

		sb.append(delAlbum).append(' ').append(delPhoto).append('\n');
	}

	// 해당 앨범의 모든 하위 앨범과 사진을 삭제하는 메서드
	static void deleteAlbum(Album now) {

		for (String name : now.children.keySet()) // 하위 앨범 이름으로 순회
			deleteAlbum(now.children.get(name)); // 하위 앨범을 삭제

		delAlbum += now.children.size(); // 해당 앨범의 삭제한 앨범 갯수 더함
		delPhoto += now.photos.size(); // 해당 앨범의 삭제한 사진 갯수 더함
	}

	// 사진을 삽입하는 메서드
	static void insert(String s) {

		// 현재 앨범에 같은 이름의 사진이 있다면 사진을 삽입하지 않고 문구 출력 후 리턴
		if (current.photos.contains(s)) {
			sb.append("duplicated photo name\n");
			return;
		}

		// 현재 앨범에 사진 삽입
		current.photos.add(s);
	}

	// 사진을 삭제하는 메서드
	static void delete(String s) {

		delPhoto = 0; // 삭제한 사진의 갯수 초기화

		switch (s) {
		case "-1": // 현재 앨범에서 사전순으로 가장 빠른 사진 삭제
			// 앨범이 비어있지 않다면 사진 삭제
			if (!current.photos.isEmpty()) {
				current.photos.remove(current.photos.first());
				delPhoto++;
			}
			break;

		case "0": // 현재 앨범의 모든 사진 삭제
			delPhoto = current.photos.size();
			current.photos.clear(); // 사진의 갯수를 저장하고 모든 사진 삭제
			break;

		case "1": // 현재 앨범에서 사전순으로 가장 늦은 사진 삭제
			// 앨범이 비어있지 않다면 사진 삭제
			if (!current.photos.isEmpty()) {
				current.photos.remove(current.photos.last());
				delPhoto++;
			}
			break;

		default: // 해당 이름을 가진 사진 삭제
			// 현재 앨범에 해당 이름의 사진이 있다면 삭제
			if (current.photos.remove(s))
				delPhoto++;
		}

		// 삭제된 사진의 갯수 출력
		sb.append(delPhoto).append('\n');
	}

	// 앨범을 이동하는 메서드
	static void ca(String s) {

		switch (s) {
		case "..": // 현재 앨범의 상위 앨범으로 이동
			// 현재 앨범의 상위 앨범이 존재하면 이동
			if (current.parent != null)
				current = current.parent;
			break;

		case "/": // 최상위 앨범으로 이동
			current = head;
			break;

		default: // 해당 이름을 가진 앨범으로 이동
			// 현재 앨범에 해당 이름의 앨범이 있다면 그 앨범으로 이동
			if (current.children.containsKey(s))
				current = current.children.get(s);
		}

		// 현재 앨범의 이름 출력
		sb.append(current.name).append('\n');
	}
}
