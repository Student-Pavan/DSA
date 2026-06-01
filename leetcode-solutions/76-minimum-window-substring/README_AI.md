# Minimum Window Substring

---

# 📝 Problem Statement

Given two strings `s` and `t`, return the minimum window in `s` which will contain all the characters in `t` (including duplicates). If there is no such window, return an empty string.

**Constraints:**
- `1 <= s.length, t.length <= 10^5`
- `s` and `t` consist of English letters.

---

# 💡 Intuition

The key insight is to use a sliding window approach to efficiently find the smallest substring containing all characters of `t`. We maintain a frequency map of characters in `t` and track how many characters we still need to match. As we expand the window, we adjust our requirements and when all requirements are met, we try to contract the window to find the smallest valid substring.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of `s` to see if they contain all characters of `t`. For each starting index, we expand the window until all characters of `t` are included, then we try to find the smallest such window.

## 🔹 Algorithm

1. Initialize the minimum length to a large value.
2. Iterate over all possible starting indices of the window.
3. For each starting index, expand the window to the right until all characters of `t` are included.
4. Once all characters are included, try to contract the window from the left to find the smallest valid window.
5. Update the minimum length and starting index if a smaller valid window is found.
6. Return the smallest valid window found or an empty string if no such window exists.

## 🔹 Code

```java
class Solution {
    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            int[] freq = new int[128];
            for (char ch : t.toCharArray()) {
                freq[ch]++;
            }

            int required = t.length();
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                if (freq[ch] > 0) {
                    required--;
                }
                freq[ch]--;

                if (required == 0) {
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        start = i;
                    }
                    break;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "ADOBECODEBANC"` and `t = "ABC"`.

| Iteration | Start Index | End Index | Current Window | Valid? | Min Length | Start |
|-----------|-------------|-----------|-----------------|--------|------------|-------|
| 1         | 0           | 0         | A               | No     | MAX_VALUE  | -     |
| 2         | 0           | 1         | AD              | No     | MAX_VALUE  | -     |
| 3         | 0           | 2         | ADO             | No     | MAX_VALUE  | -     |
| 4         | 0           | 3         | ADOB            | No     | MAX_VALUE  | -     |
| 5         | 0           | 4         | ADOBE           | No     | MAX_VALUE  | -     |
| 6         | 0           | 5         | ADOBEC          | No     | MAX_VALUE  | -     |
| 7         | 0           | 6         | ADOBECO         | No     | MAX_VALUE  | -     |
| 8         | 0           | 7         | ADOBECODE       | No     | MAX_VALUE  | -     |
| 9         | 0           | 8         | ADOBECODEB      | No     | MAX_VALUE  | -     |
| 10        | 0           | 9         | ADOBECODEBA     | No     | MAX_VALUE  | -     |
| 11        | 0           | 10        | ADOBECODEBAN    | No     | MAX_VALUE  | -     |
| 12        | 0           | 11        | ADOBECODEBANC   | Yes    | 12         | 0     |
| 13        | 1           | 1         | D               | No     | 12         | 0     |
| ...       | ...         | ...       | ...             | ...    | ...        | ...   |
| 18        | 7           | 9         | CODEBA          | No     | 12         | 0     |
| 19        | 7           | 10        | CODEBAN         | No     | 12         | 0     |
| 20        | 7           | 11        | CODEBANC        | Yes    | 5          | 7     |

The brute force approach finds the minimum window "BANC" with length 4 starting at index 9.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique with two pointers (left and right) to efficiently find the minimum window substring. We maintain a frequency map of characters in `t` and track how many characters we still need to match. As we expand the window, we adjust our requirements and when all requirements are met, we try to contract the window to find the smallest valid substring.

## 🔹 Why This Works

This approach works because it efficiently narrows down the search space by expanding and contracting the window dynamically. By maintaining a frequency map and tracking the number of characters we still need to match, we can ensure that we find the smallest valid window in linear time.

## 🔹 Algorithm

1. Initialize a frequency map for characters in `t`.
2. Initialize two pointers, `left` and `right`, to represent the current window.
3. Initialize `required` to the length of `t` and `minLen` to a large value.
4. Iterate over the string `s` with the `right` pointer:
   - Decrement the frequency of the current character.
   - If the frequency is still positive, decrement `required`.
   - While `required` is zero, it means all characters of `t` are included in the current window:
     - Update `minLen` and `start` if the current window is smaller.
     - Move the `left` pointer to the right to try to find a smaller valid window.
     - Increment the frequency of the character at the `left` pointer.
     - If the frequency is positive, increment `required`.
5. Return the smallest valid window found or an empty string if no such window exists.

## 🔹 Code

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] freq = new int[128];

        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        int left = 0;
        int required = t.length();

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (freq[ch] > 0) {
                required--;
            }

            freq[ch]--;

            while (required == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);

                freq[leftChar]++;

                if (freq[leftChar] > 0) {
                    required++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "ADOBECODEBANC"` and `t = "ABC"`.

| Step | Left | Right | Current Window | Required | Action | Min Length | Start |
|------|------|-------|-----------------|----------|--------|------------|-------|
| 1    | 0    | 0     | A               | 2        | -      | MAX_VALUE  | -     |
| 2    | 0    | 1     | AD              | 2        | -      | MAX_VALUE  | -     |
| 3    | 0    | 2     | ADO             | 2        | -      | MAX_VALUE  | -     |
| 4    | 0    | 3     | ADOB            | 2        | -      | MAX_VALUE  | -     |
| 5    | 0    | 4     | ADOBE           | 2        | -      | MAX_VALUE  | -     |
| 6    | 0    | 5     | ADOBEC          | 1        | -      | MAX_VALUE  | -     |
| 7    | 0    | 6     | ADOBECO         | 1        | -      | MAX_VALUE  | -     |
| 8    | 0    | 7     | ADOBECODE       | 1        | -      | MAX_VALUE  | -     |
| 9    | 0    | 8     | ADOBECODEB      | 0        | Update minLen=9, start=0 | 9 | 0 |
| 10   | 1    | 8     | DOBECODEB       | 1        | -      | 9          | 0     |
| 11   | 2    | 8     | OBECODEB        | 1        | -      | 9          | 0     |
| 12   | 3    | 8     | BECODEB         | 1        | -      | 9          | 0     |
| 13   | 4    | 8     | ECODEB          | 1        | -      | 9          | 0     |
| 14   | 5    | 8     | CODEB           | 1        | -      | 9          | 0     |
| 15   | 6    | 8     | ODEB            | 1        | -      | 9          | 0     |
| 16   | 7    | 8     | DEB             | 1        | -      | 9          | 0     |
| 17   | 8    | 8     | B               | 2        | -      | 9          | 0     |
| 18   | 8    | 9     | BA              | 2        | -      | 9          | 0     |
| 19   | 8    | 10    | BAN             | 1        | -      | 9          | 0     |
| 20   | 8    | 11    | BANC            | 0        | Update minLen=4, start=8 | 4 | 8 |

The optimal approach finds the minimum window "BANC" with length 4 starting at index 8.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Input:** If `s` or `t` is empty, return an empty string.
- **No Valid Window:** If `s` does not contain all characters of `t`, return an empty string.
- **Single Character:** If `t` is a single character, find the first occurrence of that character in `s`.
- **All Characters Same:** If all characters in `s` are the same, check if they match `t`.
- **Large Input:** Ensure the solution handles large input sizes efficiently.

---

# 📚 Key Takeaways

- **Sliding Window Technique:** This problem is a classic example of the sliding window technique, which is efficient for finding subarrays or substrings that meet certain conditions.
- **Frequency Map:** Maintaining a frequency map helps track the characters we need to match and efficiently update the window.
- **Dynamic Window Adjustment:** Adjusting the window dynamically by expanding and contracting helps find the smallest valid window efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss how to handle cases where `t` has duplicate characters.
- **Common Pitfalls:** Ensure that the frequency map is correctly updated and that the window is adjusted properly.
- **Alternative Approaches:** Consider using a hash map to track character frequencies and a queue to manage the window.

---

# ✅ Conclusion

The optimal sliding window approach efficiently finds the minimum window substring by dynamically adjusting the window and maintaining a frequency map. This approach ensures that we find the smallest valid window in linear time, making it suitable for large input sizes.