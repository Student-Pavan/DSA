class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> ans = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String curr = words[i];
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }

        int wordLen = words[0].length();
        int totalWords = words.length;

        for (int start = 0; start < wordLen; start++) {

            int left = start;
            int count = 0;

            HashMap<String, Integer> window = new HashMap<>();

            for (int right = start; right + wordLen <= s.length(); right += wordLen) {

                String curr = s.substring(right, right + wordLen);

                if (map.containsKey(curr)) {

                    window.put(curr, window.getOrDefault(curr, 0) + 1);
                    count++;

                    while (window.get(curr) > map.get(curr)) {

                        String leftStr = s.substring(left, left + wordLen);

                        window.put(leftStr, window.get(leftStr) - 1);

                        left += wordLen;
                        count--;
                    }

                    if (count == totalWords) {

                        ans.add(left);

                        String leftStr = s.substring(left, left + wordLen);

                        window.put(leftStr, window.get(leftStr) - 1);

                        left += wordLen;
                        count--;
                    }

                } else {

                    window.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return ans;
    }
}