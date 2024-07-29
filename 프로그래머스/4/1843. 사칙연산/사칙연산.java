class Solution {
    public int solution(String arr[]) {
        int len = arr.length;

        int[][] min = new int[len][len];
        int[][] max = new int[len][len];

        for (int i = 0; i < len; i += 2) {
            for (int j = 0; j < len; j += 2) {
                if (i == j) {
                    int num = Integer.parseInt(arr[i]);
                    min[i][j] = num;
                    max[i][j] = num;
                } else {
                    min[i][j] = Integer.MAX_VALUE;
                    max[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        for (int size = 3; size <= len; size += 2) {
            for (int k = 1; k <= len - 2; k += 2) {
                for (int i = Math.max(k - (size - 2), 0); i <= Math.min(k - 1, len - size); i += 2) {
                    int j = i + size - 1;

                    if (arr[k].equals("+")) {
                        min[i][j] = Math.min(min[i][j], min[i][k - 1] + min[k + 1][j]);
                        max[i][j] = Math.max(max[i][j], max[i][k - 1] + max[k + 1][j]);
                    } else {
                        min[i][j] = Math.min(min[i][j], min[i][k - 1] - max[k + 1][j]);
                        max[i][j] = Math.max(max[i][j], max[i][k - 1] - min[k + 1][j]);
                    }
                }
            }
        }

        return max[0][len - 1];
    }
}