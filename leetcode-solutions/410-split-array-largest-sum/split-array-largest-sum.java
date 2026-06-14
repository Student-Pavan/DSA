class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;
        for (int page : nums) {
            low = Math.max(low, page);
            high += page;
        }
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canSplit(int[] nums, int k, int max) {
        int sum = 0;
        int group = 1;
        for (int page : nums) {
            if (sum + page <= max) {
                sum += page;
            } else {
                group++;
                sum = page;
            }

        }

        return group <= k;
    }
}
