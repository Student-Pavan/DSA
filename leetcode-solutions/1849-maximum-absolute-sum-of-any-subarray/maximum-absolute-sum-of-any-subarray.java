class Solution {
    public int maxAbsoluteSum(int[] nums) {

        int maxsubarraysum = Integer.MIN_VALUE;
        int minsubarraysum = Integer.MAX_VALUE;

        int maxsum = 0;
        int minsum = 0;

        int total = 0;

        for(int i = 0; i < nums.length; i++){

                maxsum += nums[i];

                maxsubarraysum =Math.max(maxsum,maxsubarraysum);

                if(maxsum < 0){
                    maxsum = 0;
                }
                minsum += nums[i];

                minsubarraysum =Math.min(minsum,minsubarraysum);

                if(minsum > 0){
                   minsum = 0;
                }

        }
        return Math.max(maxsubarraysum, Math.abs(minsubarraysum));
    }
}