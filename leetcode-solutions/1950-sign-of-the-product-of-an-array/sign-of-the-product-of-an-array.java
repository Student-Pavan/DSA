class Solution {
    public int arraySign(int[] nums) {
        double prod = 1;

        for( int num : nums){
            prod *= (double)num;
        }
        if(prod > 0)
            return 1;
        if(prod < 0)
            return -1;
        else
            return 0;
        

    }
}