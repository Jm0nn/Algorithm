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

            String name = st.nextToken();
            String notes = changeSharp(st.nextToken());

            musics[i] = new Music(name, notes, start, end);
        }

        Arrays.sort(musics);

        for (Music music : musics) {
            String notes = music.notes;
            int notesLen = notes.length();

            if (music.playMinute < notesLen) {
                notes = notes.substring(0, music.playMinute);
            } else {
                int idx = 0;

                while (music.playMinute > notes.length()) {
                    notes = notes + notes.charAt(idx++);
                    if (idx == notesLen) idx = 0;
                }
            }

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

    Music(String title, String notes, String start, String end) {
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