package com.jm0nn.programmers.lv3.단속카메라;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{
                {-20, -15},
                {-14, -5},
                {-18, -13},
                {-5, -3}
        })); // 2

        System.out.println(solution.solution(new int[][]{
                {-10, 10},
                {-8, 8},
                {-1, 1}
        })); // 1
    }
}
