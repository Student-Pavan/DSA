class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxsum = 0;
        int maxsubarraysum = Integer.MIN_VALUE;
        int minsum = 0;
        int minsubarraysum = Integer.MAX_VALUE;

        int totalsum = 0;

        for(int i = 0; i < nums.length; i++){

            totalsum += nums[i];

            maxsum += nums[i];
            maxsubarraysum = Math.max(maxsum,maxsubarraysum);

            if(maxsum < 0)
                maxsum = 0;
            


            minsum += nums[i];
            minsubarraysum = Math.min(minsum, minsubarraysum);

            if(minsum > 0)
                minsum = 0;

            
           
        }

        if(maxsubarraysum < 0)
            return maxsubarraysum;

        
        return Math.max(maxsubarraysum, totalsum - minsubarraysum);
    }
}