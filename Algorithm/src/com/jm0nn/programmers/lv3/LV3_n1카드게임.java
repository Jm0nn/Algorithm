package com.jm0nn.programmers.lv3;

import java.util.*;

public class LV3_n1카드게임 {

    public static void main(String[] args) {
        LV3_n1카드게임 lv3 = new LV3_n1카드게임();
        System.out.println(lv3.solution(4,
                new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4}));
        System.out.println(lv3.solution(3,
                new int[]{1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12}));
        System.out.println(lv3.solution(2,
                new int[]{5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7}));
        System.out.println(lv3.solution(10,
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}));
    }

    public int solution(int coin, int[] cards) {
        int answer = 0;

        int n = cards.length;
        int count = 0;

        // TreeSet으로 카드 관리
        // contains() 메서드 사용 시 O(log(n))의 시간복잡도로 탐색 가능
        TreeSet<Integer> hand = new TreeSet<>();
        TreeSet<Integer> get = new TreeSet<>();

        while (count < n / 3) {
            hand.add(cards[count++]);
        }

        while (true) {
            ++answer;

            if (count >= n) {
                break;
            }

            get.add(cards[count++]);
            get.add(cards[count++]);

            boolean next = false;

            for (int card : hand) {
                int another = n + 1 - card;

                if (hand.contains(another)) {
                    hand.remove(card);
                    hand.remove(another);
                    next = true;
                    break;
                }

                if (get.contains(another) && coin > 0) {
                    --coin;
                    hand.remove(card);
                    get.remove(another);
                    next = true;
                    break;
                }
            }

            if (!next) {
                for (int card : get) {
                    int another = n + 1 - card;

                    if (get.contains(another) && coin > 1) {
                        coin -= 2;
                        get.remove(card);
                        get.remove(another);
                        next = true;
                        break;
                    }
                }
            }

            if (!next) {
                break;
            }
        }

        return answer;
    }

}
