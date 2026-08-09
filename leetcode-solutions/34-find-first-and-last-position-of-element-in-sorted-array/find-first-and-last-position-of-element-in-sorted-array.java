class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0)
            return new int[] {-1,-1};

        int first = findFirstOccurence(nums,target);
        int last = findLastOccurrence(nums,target);

        return new int[] {first,last};
        
             
    }

    private int findFirstOccurence(int[] nums, int target){

        int first = -1;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) /2;

            if(nums[mid] == target){
                first = mid;
                right = mid-1;
            }

            else if(nums[mid] < target){
                left = mid + 1;
            }
            else
                right = mid - 1;

        }
        return first;

    }
    private int findLastOccurrence(int[] nums, int target){

        int last = -1;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left) /2;

            if(nums[mid] == target){
                last = mid;
                left = mid+1;
            }

            else if(nums[mid] < target){
                left = mid + 1;
            }
            else
                right = mid - 1;

        }
        return last;

    }
}                                                                          