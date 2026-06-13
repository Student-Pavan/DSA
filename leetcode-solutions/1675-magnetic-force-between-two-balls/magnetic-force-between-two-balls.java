import java.util.*;

class Solution {
    public int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int n = position.length - 1;
        int low = 1;
        int high = position[n] - position[0]; 
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(position, m, mid)) {
                ans = mid;
                low = mid + 1;   // try bigger distance go right 
            } else {
                high = mid - 1;  // reduce distance 
            }
        }
        return ans;
    }

    private boolean canPlace(int[] position, int m, int mid) {
        int last = position[0];
        int count = 1;

        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= mid) {
                count++;
                last = position[i];
            }
        }
        return count >= m;
    }
}