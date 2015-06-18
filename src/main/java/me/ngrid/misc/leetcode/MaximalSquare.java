package me.ngrid.misc.leetcode;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 4.

 * @see <a href="https://leetcode.com/problems/maximal-square/">l33tc0d3</a>
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0)
            return 0;
        int pow = 1;
        for(int i = 0; i < matrix.length; i++) {
            for(int k = 0; k < matrix[0].length; k++) {
                while(dfs(matrix, i, k, pow)) {
                    pow++;
                }
            }
        }
        --pow;
        return pow * pow;
    }

    private boolean dfs(char[][] m, int x, int y, int pow) {
        for(int i = x; i < x + pow; i++) {
            for(int k = y; k < y + pow; k++) {
                if(i == m.length || k == m[0].length
                        || m[i][k] == '0')
                    return false;
            }
        }

        return true;
    }
}
