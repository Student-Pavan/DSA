```markdown
# 📌 Longest Palindromic Substring

---

# 📝 Problem Statement

Given a string `s`, return the longest palindromic substring in `s`.

A **palindrome** is a string that reads the same backward as forward (e.g., "madam" or "racecar").

### **Objective**
Find the longest contiguous substring that is a palindrome.

### **Input**
- A string `s` of length `1 <= s.length <= 1000`
- The string consists of lowercase and/or uppercase English letters.

### **Output**
- The longest palindromic substring in `s`.

### **Constraints**
- The solution must efficiently handle the upper constraint (`s.length = 1000`).
- The solution should return **any one** of the longest palindromic substrings if multiple exist.

---

# 💡 Intuition

A palindrome reads the same forwards and backwards. The key insight is that palindromes can be of **even** or **odd** length:
- **Odd-length palindromes** (e.g., "aba") have a central character.
- **Even-length palindromes** (e.g., "abba") have no central character but two mirrored halves.

The brute-force approach checks all possible substrings, but this is inefficient for longer strings. The optimal approach leverages **expansion around centers** to avoid redundant checks, significantly improving performance.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute-force approach generates all possible substrings of `s` and checks if each is a palindrome. The longest valid palindrome is returned.

### **Steps:**
1. Iterate over all possible starting indices `i` of substrings.
2. For each `i`, iterate over all possible ending indices `j` (where `j >= i`).
3. Check if the substring `s[i..j]` is a palindrome.
4. Track the longest palindrome found.

### **Palindrome Check:**
A helper function `isPalindrome(s, left, right)` checks if `s[left..right]` is a palindrome by comparing characters from both ends towards the center.

---

## 🔹 Algorithm

1. Initialize `start = 0` and `maxLength = 1` (since a single character is always a palindrome).
2. For each `i` from `0` to `n-1`:
   - For each `j` from `i` to `n-1`:
     - If `s[i..j]` is a palindrome and `j - i + 1 > maxLength`:
       - Update `maxLength = j - i + 1`.
       - Update `start = i`.
3. Return `s.substring(start, start + maxLength)`.

---

## 🔹 Code

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, maxLength = 1;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j) && (j - i + 1) > maxLength) {
                    start = i;
                    maxLength = j - i + 1;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

---

## 🔹 Dry Run

**Input:** `s = "babad"`

| Step | i | j | Substring | isPalindrome? | maxLength | start |
|------|---|---|-----------|---------------|-----------|-------|
| 1    | 0 | 0 | "b"       | Yes           | 1         | 0     |
| 2    | 0 | 1 | "ba"      | No            | 1         | 0     |
| 3    | 0 | 2 | "bab"     | Yes           | 3         | 0     |
| 4    | 0 | 3 | "baba"    | No            | 3         | 0     |
| 5    | 0 | 4 | "babad"   | No            | 3         | 0     |
| 6    | 1 | 1 | "a"       | Yes           | 3         | 0     |
| 7    | 1 | 2 | "ab"      | No            | 3         | 0     |
| 8    | 1 | 3 | "aba"     | Yes           | 3         | 0     |
| 9    | 1 | 4 | "abad"    | No            | 3         | 0     |
| 10   | 2 | 2 | "b"       | Yes           | 3         | 0     |
| 11   | 2 | 3 | "ba"      | No            | 3         | 0     |
| 12   | 2 | 4 | "bad"     | No            | 3         | 0     |
| 13   | 3 | 3 | "a"       | Yes           | 3         | 0     |
| 14   | 3 | 4 | "ad"      | No            | 3         | 0     |
| 15   | 4 | 4 | "d"       | Yes           | 3         | 0     |

**Output:** `"bab"` (or `"aba"`)

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n³)       |
| Space Complexity | O(1)        |

**Explanation:**
- **Time:** O(n²) substrings, each checked in O(n) time → O(n³).
- **Space:** O(1) auxiliary space (no extra data structures).

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses **expansion around centers** to avoid redundant checks. Instead of checking all substrings, we treat each character (and each pair of characters) as the center of a potential palindrome and expand outward to find the longest palindrome.

### **Key Insight:**
- A palindrome mirrors around its center.
- For odd-length palindromes, the center is a single character (e.g., "aba" → center is 'b').
- For even-length palindromes, the center is between two characters (e.g., "abba" → center is between the two 'b's).

### **Steps:**
1. Iterate over each character in `s`.
2. For each character, expand around its center for odd-length palindromes.
3. For each pair of characters, expand around their center for even-length palindromes.
4. Track the longest palindrome found.

---

## 🔹 Why This Works

- **Efficiency:** Each expansion takes O(n) time, and we perform O(n) expansions → O(n²) total time.
- **Correctness:** Every possible palindrome is checked by considering all centers.
- **Optimization:** Avoids the O(n³) brute-force approach by eliminating redundant checks.

---

## 🔹 Algorithm

1. Initialize `start = 0` and `maxLength = 1`.
2. For each `i` from `0` to `n-1`:
   - Expand around `i` for odd-length palindromes.
   - Expand around `i` and `i+1` for even-length palindromes.
   - Update `start` and `maxLength` if a longer palindrome is found.
3. Return `s.substring(start, start + maxLength)`.

---

## 🔹 Code

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, maxLength = 1;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Odd-length palindrome
            int len1 = expandAroundCenter(s, i, i);
            // Even-length palindrome
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > maxLength) {
                maxLength = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = "babad"`

| i | Odd-Length Expansion | Even-Length Expansion | maxLength | start |
|---|----------------------|-----------------------|-----------|-------|
| 0 | "b" (len=1)          | "ba" (len=0)          | 1         | 0     |
| 1 | "aba" (len=3)        | "ab" (len=0)          | 3         | 0     |
| 2 | "b" (len=1)          | "ba" (len=0)          | 3         | 0     |
| 3 | "a" (len=1)          | "ad" (len=0)          | 3         | 0     |
| 4 | "d" (len=1)          | -                     | 3         | 0     |

**Output:** `"bab"` (or `"aba"`)

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

**Explanation:**
- **Time:** Each of the `n` centers is expanded in O(n) time → O(n²).
- **Space:** O(1) auxiliary space.

---

# 🔍 Edge Cases

| Edge Case               | Expected Output       |
|-------------------------|-----------------------|
| `s = ""`                | `""`                  |
| `s = "a"`               | `"a"`                 |
| `s = "ac"`              | `"a"` or `"c"`        |
| `s = "bb"`              | `"bb"`                |
| `s = "abcba"`           | `"abcba"`             |
| `s = "a".repeat(1000)`  | `s` (entire string)   |
| `s = "abacdfgdcaba"`    | `"aba"` or `"cdc"`    |

---

# 📚 Key Takeaways

1. **Brute Force vs. Optimal:** The brute-force approach is intuitive but inefficient (O(n³)). The optimal approach reduces this to O(n²) by leveraging palindrome symmetry.
2. **Center Expansion:** Treating each character (and pair) as a center avoids redundant checks.
3. **Odd/Even Palindromes:** Both cases must be handled separately.
4. **Interview Insight:** Always consider edge cases (empty string, single character, all identical characters).

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - Can you solve this in O(n) time? (Manacher’s Algorithm)
   - How would you handle Unicode characters?
   - Can you modify the solution to return all longest palindromic substrings?

2. **Common Pitfalls:**
   - Forgetting to handle even-length palindromes.
   - Off-by-one errors in substring extraction.
   - Not considering edge cases (e.g., empty string).

3. **Alternative Approaches:**
   - **Dynamic Programming:** O(n²) time and space (not covered here).
   - **Manacher’s Algorithm:** O(n) time (advanced, rarely expected in interviews).

4. **Optimization Discussion:**
   - The optimal approach is already efficient for most interview settings.
   - For very large strings (n > 10⁴), Manacher’s Algorithm is preferred.

---

# ✅ Conclusion

The **optimal approach** (expansion around centers) is the preferred solution due to its **O(n²) time complexity** and **O(1) space complexity**. It efficiently checks all possible palindromes by leveraging symmetry, avoiding the redundancy of the brute-force method. The key insight is recognizing that every palindrome has a center, and expanding around it ensures we find the longest palindrome without unnecessary checks.

**Most Important Insight:**
- **Palindromes are symmetric around their center.** This symmetry allows us to expand outward from each center, reducing the problem to a linear scan with linear expansions.

**Key Learning Outcome:**
- **Optimization often comes from recognizing patterns** (e.g., symmetry in palindromes) and leveraging them to avoid redundant work.
```