class Solution {
    public int smallestEqual(int[] nums) {
        

        for(int i = 0 ; i < nums.length; i++){

            int ans = i % 10;

            if(ans == nums[i]){
                return i;
            }
        }
        return -1;
    }
}