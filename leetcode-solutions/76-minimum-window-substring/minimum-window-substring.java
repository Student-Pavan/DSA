class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int left = 0;
        int count = map.size();
        int minlen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                map.put(ch, map.getOrDefault(ch, 0) - 1);

                if (map.get(ch) == 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (right - left + 1 < minlen) {
                    minlen = right - left + 1;
                    start = left;
                }

                char leftchar = s.charAt(left);
                if (map.containsKey(leftchar)) {
                    map.put(leftchar, map.getOrDefault(leftchar, 0) + 1);

                    if (map.get(leftchar) == 1) {
                        count++;
                    }
                }
                left++;
            }
        }

        return minlen == Integer.MAX_VALUE ? "" : s.substring(start, start + minlen);
    }
}