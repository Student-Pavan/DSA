class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        if(m * k > bloomDay.length){
            return -1;
        }
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i = 0 ; i < bloomDay.length ; i++){
            low = Math.min(low,bloomDay[i]);
            high = Math.max(high,bloomDay[i]);
        }
        int ans = -1;
        while(low <= high){
            int mid = low + (high - low) / 2;

            if(Canmake(bloomDay,m,k,mid)){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ans;

    }
    private boolean Canmake(int[] bloomDay, int m, int k, int day){
        int count = 0;
        int boquets = 0;

        for(int bloom : bloomDay){
            if(bloom <= day){
                count++;
                if(count == k){
                    boquets++;
                    count = 0;
                }   
            }
            else
                count = 0;
        }
        return boquets >= m;
    }
}