class Solution {
    public int arraySign(int[] nums) {
        double count = 0;

        for( int num : nums){
            if(num < 0){
                count++;
            }
            if(num == 0)
                return 0;
        }
        if(count % 2 == 0)
            return 1;
        if(count % 2 != 0)
            return -1;
        else
            return 0;
        
        

    }
}