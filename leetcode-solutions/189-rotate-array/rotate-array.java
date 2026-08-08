class Solution {
    public void rotate(int[] nums, int k) {
        int temp[] = new int[nums.length];

        int index = 0;

        // k = k % nums.length; is used because k can be greater than the array length.
        k = k % nums.length;

        // Take last k elements first
        for (int i = nums.length - k; i < nums.length; i++) {
            temp[index++] = nums[i];
        }

        // Take remaining elements
        for (int i = 0; i < nums.length - k; i++) {
            temp[index++] = nums[i];
        }

        // Copy temp back to nums
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
}