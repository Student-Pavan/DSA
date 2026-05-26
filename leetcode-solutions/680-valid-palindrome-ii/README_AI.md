# 📌 Valid Palindrome II

---

# 📝 Problem Statement

Given a string `s`, return `true` if the string can be made a palindrome by removing **at most one** character. Otherwise, return `false`.

A palindrome is a string that reads the same backward as forward.

### Constraints:
- `1 <= s.length <= 10^5`
- `s` consists of lowercase English letters.

### Examples:
**Example 1:**
```
Input: s = "aba"
Output: true
Explanation: The string is already a palindrome.
```

**Example 2:**
```
Input: s = "abca"
Output: true
Explanation: Remove 'c' to get "aba", which is a palindrome.
```

**Example 3:**
```
Input: s = "abc"
Output: false
Explanation: No character can be removed to make a palindrome.
```

---

# 💡 Intuition

The key insight is that a string can be made a palindrome by removing **at most one** character if there exists at most one mismatch when comparing characters from both ends. If a mismatch is found, we can skip either the left or right character and check if the remaining substring forms a palindrome.

This problem leverages the **two-pointer technique** to efficiently check for palindromic properties while allowing a single deletion. The optimal approach avoids unnecessary recomputation by only checking the relevant substring after a mismatch.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings formed by removing each character one by one and verifying if any of them is a palindrome. This is inefficient because it requires O(n) palindrome checks, each taking O(n) time, resulting in O(n²) time complexity.

## 🔹 Algorithm

1. Iterate over each character in the string.
2. For each character, create a new string by removing that character.
3. Check if the new string is a palindrome.
4. If any such string is a palindrome, return `true`.
5. If none are found after all iterations, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            String candidate = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(candidate)) {
                return true;
            }
        }
        return isPalindrome(s);
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
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

## 🔹 Dry Run

**Input:** `s = "abca"`

| Step | Character Removed | Candidate String | Is Palindrome? | Result |
|------|-------------------|------------------|----------------|--------|
| 1    | 'a' (index 0)     | "bca"            | false          | -      |
| 2    | 'b' (index 1)     | "aca"            | true           | true   |

The algorithm returns `true` at step 2 since "aca" is a palindrome.

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(n)        |

**Explanation:**
- For each of the `n` characters, we create a substring of length `n-1` and check if it is a palindrome (O(n) per check).
- Total time: O(n * n) = O(n²).
- Space is O(n) due to substring creation.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the **two-pointer technique** with a single pass through the string. We start with pointers at both ends and move towards the center. If a mismatch is found, we check both possibilities: skipping the left character or skipping the right character, and verify if either results in a palindrome.

This approach avoids recomputing the entire palindrome check by only validating the relevant substring after a mismatch.

## 🔹 Why This Works

- If the string is already a palindrome, no deletion is needed.
- If a mismatch occurs, the string can still be a palindrome if either the left or right character is removed. We only need to check the substring formed by skipping one of them.
- This reduces the problem to at most two additional O(n) checks, resulting in O(n) time overall.

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the string.
2. While `left < right`:
   - If characters at `left` and `right` match, move both pointers inward.
   - If they don’t match, check if the substring formed by skipping `left` or `right` is a palindrome.
3. If either substring is a palindrome, return `true`.
4. If the loop completes without mismatches, return `true`.
5. Otherwise, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
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

## 🔹 Detailed Dry Run

**Input:** `s = "abca"`

| Step | Left | Right | Characters | Action                     | Result       |
|------|------|-------|------------|----------------------------|--------------|
| 1    | 0    | 3     | 'a' vs 'a' | Match, move pointers       | -            |
| 2    | 1    | 2     | 'b' vs 'c' | Mismatch                   | Check substrings |
| 2.1  | Check `isPalindrome(s, 2, 2)` (skip 'b') | "aca" | true       | Return `true`              |

The algorithm returns `true` after checking the substring "aca".

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

**Explanation:**
- The two-pointer traversal takes O(n) time.
- In the worst case, we perform two additional O(n) checks (after a mismatch), but this is still O(n) overall.
- No extra space is used beyond a few variables.

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `s = ""`                      | true            | Empty string is trivially a palindrome.      |
| `s = "a"`                     | true            | Single character is a palindrome.            |
| `s = "aa"`                    | true            | Already a palindrome.                        |
| `s = "ab"`                    | true            | Remove either 'a' or 'b' to get "a" or "b".  |
| `s = "abc"`                   | false           | No single deletion can form a palindrome.    |
| `s = "deeee"`                 | true            | Remove 'd' to get "eeee".                    |
| `s = "aabcaa"`                | true            | Remove 'b' or 'c' to get "aacaa" or "aabaa". |
| `s = "ebcbbececabbacecbbcbe"` | true            | Complex case with one valid deletion.        |

---

# 📚 Key Takeaways

1. **Two-Pointer Technique:** Efficiently checks palindromic properties by comparing characters from both ends.
2. **Early Termination:** The optimal solution avoids unnecessary checks by terminating early after a mismatch.
3. **Greedy Validation:** After a mismatch, only the relevant substring needs to be checked, not the entire string.
4. **Space Efficiency:** The optimal approach uses O(1) space, making it suitable for large inputs.
5. **Trade-off Awareness:** The brute force approach is intuitive but inefficient; the optimal solution leverages problem constraints for better performance.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Always confirm if the string can be empty or if it contains non-lowercase characters.
2. **Discuss Trade-offs:** Mention the brute force approach first to show problem understanding, then optimize.
3. **Follow-up Questions:**
   - Can you solve this with O(1) space?
   - What if you can remove up to `k` characters?
   - How would you handle uppercase/lowercase sensitivity?
4. **Common Pitfalls:**
   - Forgetting to check both substrings after a mismatch.
   - Using excessive space (e.g., creating substrings in the optimal approach).
   - Not handling edge cases like single-character strings.
5. **Alternative Approaches:**
   - Recursive solutions (though less efficient for large `n`).
   - Using a stack to validate palindromes (not optimal for this problem).

---

# ✅ Conclusion

The **optimal solution** leverages the two-pointer technique to efficiently validate whether a string can be made a palindrome with at most one deletion. By focusing on the relevant substring after a mismatch, it achieves **O(n) time and O(1) space**, making it suitable for large inputs.

The key insight is that **only one mismatch is allowed**, and after encountering it, we only need to check the two possible substrings formed by skipping either the left or right character. This approach minimizes recomputation and maximizes efficiency, making it ideal for technical interviews and production use.