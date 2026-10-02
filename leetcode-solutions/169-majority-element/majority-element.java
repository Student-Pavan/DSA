class Solution {
    public int majorityElement(int[] nums) {
        int freq = 0;
        int candidate  = 0;

        for(int num: nums){
            if(freq == 0){
                candidate = num;
            }

            if(candidate == num){
                freq++;
            }

            else{
                freq--;
            }
        }
        int count = 0;

        for(int num : nums){
            if(num == candidate){
                count++;
            }
        }
        return (count > nums.length/2) ? candidate : -1;
    }
}