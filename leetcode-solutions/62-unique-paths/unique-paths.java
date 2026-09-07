class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return countPaths(m - 1, n - 1,dp);
    }

    private int countPaths(int rows, int cols,int[][] dp) {
        if (rows == 0 || cols== 0)
            return 1;

        if(dp[rows][cols] != 0)
            return dp[rows][cols];

            
        dp[rows][cols] =  countPaths(rows, cols - 1,dp) + countPaths(rows - 1, cols,dp);

        return dp[rows][cols];
    }
}