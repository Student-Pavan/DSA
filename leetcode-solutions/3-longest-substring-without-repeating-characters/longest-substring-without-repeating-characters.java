class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet();

        int left = 0, maxlen = 0;

        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);

            if (set.contains(curr)) {
                while (set.contains(curr)) {
                    set.remove(s.charAt(left));
                    left++;
                }
            }
            set.add(curr);

            maxlen = Math.max(maxlen, right - left + 1);
      
        }
        return maxlen;

    }
}