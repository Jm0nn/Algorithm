package com.jm0nn.programmers.lv3.N으로표현;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, 12)); // 4
        System.out.println(solution.solution(2, 11)); // 3
        System.out.println(solution.solution(5, 11111)); // 6 (55555 / 5)
        System.out.println(solution.solution(4, 31)); // 5 ((4 + 4) * 4 - (4 / 4))
    }
}
