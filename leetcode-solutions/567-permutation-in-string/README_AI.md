# 📌 567. Permutation in String

---

# 📝 Problem Statement

Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.

In other words, return `true` if one of `s1`'s permutations is the substring of `s2`.

**Constraints:**
- `1 <= s1.length, s2.length <= 10^4`
- `s1` and `s2` consist of lowercase English letters.

---

# 💡 Intuition

The key insight is that a permutation of `s1` in `s2` means that the frequency of characters in any substring of `s2` with length equal to `s1` must match the frequency of characters in `s1`.

We can use a sliding window approach to efficiently check all possible substrings of `s2` with length equal to `s1` without recalculating character frequencies from scratch for each substring.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Generate all possible substrings of `s2` with length equal to `s1`.
2. For each substring, check if its character frequency matches `s1`'s character frequency.
3. If any substring matches, return `true`.
4. If no matches are found after checking all substrings, return `false`.

## 🔹 Algorithm

1. Initialize an array `freq1` of size 26 to store the frequency of characters in `s1`.
2. Populate `freq1` by iterating through `s1`.
3. Iterate through all possible starting indices of substrings in `s2`:
   - For each starting index, extract the substring of length equal to `s1`.
   - Initialize an array `freq2` of size 26 to store the frequency of characters in the current substring.
   - Populate `freq2` by iterating through the current substring.
   - Compare `freq1` and `freq2`. If they match, return `true`.
4. If no matches are found, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freq1 = new int[26];
        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            String substring = s2.substring(i, i + s1.length());
            int[] freq2 = new int[26];
            for (char c : substring.toCharArray()) {
                freq2[c - 'a']++;
            }
            boolean isMatch = true;
            for (int j = 0; j < 26; j++) {
                if (freq1[j] != freq2[j]) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                return true;
            }
        }
        return false;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s1 = "ab"` and `s2 = "eidbaoo"`.

1. Initialize `freq1`:
   - `freq1['a' - 'a'] = 1`
   - `freq1['b' - 'a'] = 1`

2. Iterate through all possible starting indices of substrings in `s2`:
   - Starting index 0: substring = "eid"
     - `freq2['e' - 'a'] = 1`
     - `freq2['i' - 'a'] = 1`
     - `freq2['d' - 'a'] = 1`
     - Compare `freq1` and `freq2`: No match.
   - Starting index 1: substring = "idb"
     - `freq2['i' - 'a'] = 1`
     - `freq2['d' - 'a'] = 1`
     - `freq2['b' - 'a'] = 1`
     - Compare `freq1` and `freq2`: No match.
   - Starting index 2: substring = "dba"
     - `freq2['d' - 'a'] = 1`
     - `freq2['b' - 'a'] = 1`
     - `freq2['a' - 'a'] = 1`
     - Compare `freq1` and `freq2`: Match found.
     - Return `true`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n * m) where n is the length of `s2` and m is the length of `s1` |
| Space Complexity | O(1) since the frequency arrays are of fixed size |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use a sliding window to efficiently check all possible substrings of `s2` with length equal to `s1`.
2. Maintain a frequency array for the current window and update it as the window slides.
3. Compare the frequency array of the current window with the frequency array of `s1` to check for a match.

## 🔹 Why This Works

This approach is optimal because it avoids recalculating the frequency of characters for each substring from scratch. Instead, it updates the frequency array incrementally as the window slides, reducing the time complexity significantly.

## 🔹 Algorithm

1. Initialize two frequency arrays `freq1` and `freq2` of size 26.
2. Populate `freq1` by iterating through `s1`.
3. Initialize a sliding window with `left` and `right` pointers at the start of `s2`.
4. Iterate through `s2` with the `right` pointer:
   - Increment the frequency of the current character in `freq2`.
   - If the window size exceeds `s1.length()`, decrement the frequency of the character at the `left` pointer and move the `left` pointer forward.
   - If the window size equals `s1.length()`, compare `freq1` and `freq2`. If they match, return `true`.
5. If no matches are found after iterating through `s2`, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            freq2[s2.charAt(right) - 'a']++;

            if (right - left + 1 > s1.length()) {
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }

            if (right - left + 1 == s1.length()) {
                boolean isMatch = true;
                for (int i = 0; i < 26; i++) {
                    if (freq1[i] != freq2[i]) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s1 = "ab"` and `s2 = "eidbaoo"`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 0 | Increment `freq2['e' - 'a']` | `freq2['e' - 'a'] = 1` |
| 2 | 0 | 1 | Increment `freq2['i' - 'a']` | `freq2['i' - 'a'] = 1` |
| 3 | 0 | 2 | Increment `freq2['d' - 'a']` | `freq2['d' - 'a'] = 1` |
| 4 | 0 | 3 | Increment `freq2['b' - 'a']` | `freq2['b' - 'a'] = 1` |
| 5 | 0 | 4 | Increment `freq2['a' - 'a']` | `freq2['a' - 'a'] = 1` |
| 6 | 1 | 4 | Decrement `freq2['e' - 'a']` and move `left` | `freq2['e' - 'a'] = 0` |
| 7 | 1 | 5 | Increment `freq2['o' - 'a']` | `freq2['o' - 'a'] = 1` |
| 8 | 2 | 5 | Decrement `freq2['i' - 'a']` and move `left` | `freq2['i' - 'a'] = 0` |
| 9 | 2 | 6 | Increment `freq2['o' - 'a']` | `freq2['o' - 'a'] = 2` |
| 10 | 3 | 6 | Decrement `freq2['d' - 'a']` and move `left` | `freq2['d' - 'a'] = 0` |
| 11 | 3 | 6 | Compare `freq1` and `freq2` | Match found. Return `true`. |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) where n is the length of `s2` |
| Space Complexity | O(1) since the frequency arrays are of fixed size |

---

# 🔍 Edge Cases

- `s1` is an empty string.
- `s2` is an empty string.
- `s1` is longer than `s2`.
- `s1` and `s2` are the same string.
- `s1` and `s2` have the same characters but different frequencies.
- `s1` and `s2` have all unique characters.

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substring searches.
- Maintaining a frequency array and updating it incrementally can significantly reduce time complexity.
- Understanding the problem constraints is crucial for optimizing the solution.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Mention that the optimal approach is suitable for large input sizes.
- Be prepared to explain the sliding window technique in detail.

---

# ✅ Conclusion

The optimal approach using the sliding window technique is preferred for its efficiency, especially with large input sizes. The key insight is recognizing that a permutation of `s1` in `s2` can be found by comparing character frequencies in sliding windows of `s2`. This approach ensures optimal performance with a time complexity of O(n).