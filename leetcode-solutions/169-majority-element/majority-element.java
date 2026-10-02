class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        int ele = 0;
        int freq = 0;
        int count = 0;
        int ans = 0;
        for(int num : nums){
            if(freq == 0){
                ele = num;
            }

            if(ele == num){
                freq++;
            }

            if(ele != num){
                if (freq > count) {
                    count = freq;
                    ans = ele;
                }
                freq = 1;
                ele = num;
            }

        }
        if(freq > count){
            ans = ele;
        }
        return ans;
    }
}