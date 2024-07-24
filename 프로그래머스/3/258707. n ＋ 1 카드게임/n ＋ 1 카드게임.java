import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;

        int n = cards.length;
        int count = 0;

        boolean[] used = new boolean[n + 1];
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

            int card1 = cards[count++];
            int card2 = cards[count++];

            get.add(card1);
            get.add(card2);

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