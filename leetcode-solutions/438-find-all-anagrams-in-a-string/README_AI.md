# 📌 Find All Anagrams in a String

---

# 📝 Problem Statement

Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

An **anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

**Constraints:**
- `1 <= s.length, p.length <= 3 * 10^4`
- `s` and `p` consist of lowercase English letters.

---

# 💡 Intuition

The key insight is recognizing that an anagram is simply a permutation of characters. For two strings to be anagrams, they must contain the same characters with the same frequencies, regardless of order. The optimal approach leverages a sliding window technique to efficiently track character frequencies as we traverse the string `s`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Iterate through each possible starting index in `s` where a window of length `p.length` can be formed.
2. For each window, check if it's an anagram of `p` by comparing character frequencies.
3. If it is, add the starting index to the result list.

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. For each starting index `i` from `0` to `s.length() - p.length()`:
   - Extract the substring `s[i..i+p.length()-1]`.
   - Create a frequency map for this substring.
   - Compare the frequency map with `p`'s frequency map.
   - If they match, add `i` to the result list.
3. Return the result list.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();

        if (sLength < pLength) {
            return result;
        }

        // Create frequency map for p
        HashMap<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i <= sLength - pLength; i++) {
            // Create frequency map for current window
            HashMap<Character, Integer> windowMap = new HashMap<>();
            for (int j = i; j < i + pLength; j++) {
                char c = s.charAt(j);
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            }

            // Compare frequency maps
            if (windowMap.equals(pMap)) {
                result.add(i);
            }
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "cbaebabacd"` and `p = "abc"`.

| Iteration | Window | Window Map | p Map | Match | Result |
|-----------|--------|------------|-------|-------|--------|
| 0         | "cba"  | {c:1, b:1, a:1} | {a:1, b:1, c:1} | Yes | [0] |
| 1         | "bab"  | {b:2, a:1} | {a:1, b:1, c:1} | No | [0] |
| 2         | "aba"  | {a:2, b:1} | {a:1, b:1, c:1} | No | [0] |
| 3         | "bab"  | {b:2, a:1} | {a:1, b:1, c:1} | No | [0] |
| 4         | "aba"  | {a:2, b:1} | {a:1, b:1, c:1} | No | [0] |
| 5         | "bac"  | {b:1, a:1, c:1} | {a:1, b:1, c:1} | Yes | [0, 6] |
| 6         | "acd"  | {a:1, c:1, d:1} | {a:1, b:1, c:1} | No | [0, 6] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O((n - m) * m) where n is the length of `s` and m is the length of `p` |
| Space Complexity | O(m) for storing the frequency maps |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique with two pointers to efficiently track character frequencies. We maintain a frequency map for the current window and adjust it as we slide the window through the string.

## 🔹 Why This Works

This approach is more efficient because it avoids recreating the frequency map for each window from scratch. Instead, it updates the frequency map by removing the leftmost character and adding the new rightmost character as the window slides.

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. Create a frequency map for `p`.
3. Initialize a frequency map for the current window.
4. Initialize two pointers, `left` and `right`, both starting at 0.
5. Initialize a counter `count` to track the number of characters that need to be matched.
6. Iterate through `s` with the `right` pointer:
   - If the current character is in `p`'s frequency map, decrement its count in the window's frequency map.
   - If the count reaches zero, increment the `count` variable.
   - When the window size equals `p.length()`, check if all characters are matched (`count == 0`). If so, add the starting index to the result.
   - Move the `left` pointer to the right, incrementing the count for the character leaving the window if it was in `p`'s frequency map.
7. Return the result list.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();

        if (sLength < pLength) {
            return result;
        }

        // Create frequency map for p
        HashMap<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        // Initialize frequency map for the current window
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < sLength; right++) {
            char c = s.charAt(right);
            if (pMap.containsKey(c)) {
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                if (windowMap.get(c).equals(pMap.get(c))) {
                    count++;
                }
            }

            // When the window size equals p.length(), check for anagram
            if (right - left + 1 == pLength) {
                if (count == pMap.size()) {
                    result.add(left);
                }

                // Move the left pointer to the right
                char leftChar = s.charAt(left);
                if (pMap.containsKey(leftChar)) {
                    if (windowMap.get(leftChar).equals(pMap.get(leftChar))) {
                        count--;
                    }
                    windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                }
                left++;
            }
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "cbaebabacd"` and `p = "abc"`.

| Step | Left | Right | Current Char | Window Map | Count | Action | Result |
|------|------|-------|--------------|-------------|-------|--------|--------|
| 1    | 0    | 0     | 'c'          | {c:1}       | 1     | -      | []     |
| 2    | 0    | 1     | 'b'          | {c:1, b:1}  | 2     | -      | []     |
| 3    | 0    | 2     | 'a'          | {c:1, b:1, a:1} | 3 | Add 0 | [0] |
| 4    | 1    | 3     | 'e'          | {b:1, a:1}  | 2     | -      | [0]    |
| 5    | 2    | 4     | 'b'          | {b:2, a:1}  | 1     | -      | [0]    |
| 6    | 3    | 5     | 'a'          | {b:1, a:2}  | 1     | -      | [0]    |
| 7    | 4    | 6     | 'b'          | {a:1, b:1}  | 2     | Add 6 | [0, 6] |
| 8    | 5    | 7     | 'a'          | {a:2, b:1}  | 1     | -      | [0, 6] |
| 9    | 6    | 8     | 'c'          | {a:1, b:1, c:1} | 2 | Add 6 | [0, 6] |
| 10   | 7    | 9     | 'd'          | {a:1, b:1}  | 2     | -      | [0, 6] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the length of `s` |
| Space Complexity | O(1) since the frequency maps are bounded by the size of the alphabet |

---

# 🔍 Edge Cases

- `s` is empty or shorter than `p`
- `p` is empty
- `s` and `p` are the same string
- `s` and `p` have all unique characters
- `s` and `p` have repeated characters
- `s` has multiple anagrams of `p`

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substring searches.
- Maintaining a frequency map and adjusting it as the window slides reduces time complexity.
- The optimal approach avoids unnecessary recomputation, making it more efficient than the brute force method.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask if the input strings can be modified or if additional data structures can be used.
- Consider the constraints and discuss the scalability of the solutions.

---

# ✅ Conclusion

The optimal sliding window approach is preferred because it efficiently tracks character frequencies with a single pass through the string, resulting in linear time complexity. This approach is particularly suitable for large input sizes, making it ideal for interview scenarios with tight constraints.