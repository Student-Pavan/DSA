class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        long ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int right = 0 ; right < nums.length; right++){
            sum += nums[right];

            map.put(nums[right],map.getOrDefault(nums[right] , 0) + 1);


            if( right >= k){
                sum -= nums[right - k];

                map.put(nums[right - k],map.getOrDefault(nums[right - k] , 0) - 1);

                if(map.get(nums[right - k]) == 0)
                    map.remove(nums[right - k]);
                
                
            }

            if( right >= k - 1 && map.size() == k)
                ans = Math.max(ans,sum);
        }
        
        return ans;
    }
}
