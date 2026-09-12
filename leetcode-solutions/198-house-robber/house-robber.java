class Solution {
    public int rob(int[] nums) {
        if(nums.length ==0)
            return 0;

        if(nums.length == 1)
            return nums[0];

        int case1 = canRob(nums,0,nums.length-1);
        int case2 = canRob(nums,1,nums.length-1);

        return Math.max(case1,case2);
    }

    private int canRob(int[] nums,int start, int end){
        int prev1 = 0,prev2 =0;

        for(int i = start; i <= end; i++){
            int rob = nums[i] + prev2;
            int curr = Math.max(rob,prev1);

            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}