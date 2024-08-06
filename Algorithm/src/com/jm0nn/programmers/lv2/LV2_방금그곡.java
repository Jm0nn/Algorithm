package com.jm0nn.programmers.lv2;

import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        m = changeSharp(m);

        int len = musicinfos.length;
        Music[] musics = new Music[len];

        for (int i = 0; i < len; ++i) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");

            String start = st.nextToken();
            String end = st.nextToken();
            String title = st.nextToken();
            String notes = changeSharp(st.nextToken());

            musics[i] = new Music(start, end, title, notes);
        }

        Arrays.sort(musics);

        for (Music music : musics) {
            String notes = music.notes;
            int playMinute = music.playMinute;

            String tmp = notes;

            while (playMinute > notes.length()) notes = notes + tmp;

            if (playMinute < notes.length()) notes = notes.substring(0, playMinute);

            if (notes.contains(m)) return music.title;
        }

        return "(None)";
    }

    private String changeSharp(String notes) {
        return notes
                .replace("C#", "c")
                .replace("D#", "d")
                .replace("E#", "e")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");
    }
}

class Music implements Comparable<Music> {
    String title;
    String notes;
    int playMinute;

    Music(String start, String end, String title, String notes) {
        this.title = title;
        this.notes = notes;

        StringTokenizer s = new StringTokenizer(start, ":");
        StringTokenizer e = new StringTokenizer(end, ":");

        int startHour = Integer.parseInt(s.nextToken());
        int startMinute = Integer.parseInt(s.nextToken());
        int endHour = Integer.parseInt(e.nextToken());
        int endMinute = Integer.parseInt(e.nextToken());

        this.playMinute = (endHour - startHour) * 60 + (endMinute - startMinute);
    }

    @Override
    public int compareTo(Music o) {
        return o.playMinute - this.playMinute;
    }
}

public class LV2_방금그곡 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"})); // "HELLO"
        System.out.println(solution.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"})); // "FOO"
        System.out.println(solution.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"})); // "WORLD"
    }
}
