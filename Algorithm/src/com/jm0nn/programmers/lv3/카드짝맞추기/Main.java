package com.jm0nn.programmers.lv3.카드짝맞추기;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                        {1, 0, 0, 3},
                        {2, 0, 0, 0},
                        {0, 0, 0, 2},
                        {3, 0, 1, 0}},
                1, 0)); // 14
        System.out.println(solution.solution(new int[][]{
                        {3, 0, 0, 2},
                        {0, 0, 1, 0},
                        {0, 1, 0, 0},
                        {2, 0, 0, 3}},
                0, 1)); // 16
    }
}
