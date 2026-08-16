class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m - 1;

        
        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (target >= matrix[mid][0] && target <= matrix[mid][n - 1]) {
                return binarySearch(matrix[mid], target);
            }
            else if (target < matrix[mid][0]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return false;
    }

    private boolean binarySearch(int[] row, int target) {

        int left = 0;
        int right = row.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (row[mid] == target) {
                return true;
            }
            else if (row[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return false;
    }
}