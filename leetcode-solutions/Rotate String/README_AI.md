# 📌 Rotate String

---

# 📝 Problem Statement

Given two strings `s` and `goal`, return `true` if and only if `s` can become `goal` after some number of **shifts** on `s`.

A **shift** on `s` consists of moving the leftmost character of `s` to the rightmost position.

**Example 1:**
```
Input: s = "abcde", goal = "cdeab"
Output: true
Explanation: After two shifts, "abcde" becomes "cdeab".
```

**Example 2:**
```
Input: s = "abcde", goal = "abced"
Output: false
```

**Constraints:**
- `1 <= s.length, goal.length <= 100`
- `s` and `goal` consist of lowercase English letters.

---

# 💡 Intuition

The key insight is recognizing that if `s` can be rotated to form `goal`, then `goal` must be a **substring** of `s + s`. For example, if `s = "abcde"`, then `s + s = "abcdeabcde"`. Any rotation of `s` will appear as a contiguous substring in this doubled string.

This observation allows us to avoid checking all possible rotations explicitly, reducing the problem to a simple substring check.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible rotations of `s` and checking if any of them match `goal`. This is done by repeatedly moving the first character to the end of the string and comparing the result with `goal`.

## 🔹 Algorithm

1. If the lengths of `s` and `goal` are different, return `false`.
2. Generate all possible rotations of `s` by:
   - Moving the first character to the end of the string.
   - Comparing the rotated string with `goal`.
3. If any rotation matches `goal`, return `true`.
4. If no rotation matches after all possibilities, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        String rotated = s;
        for (int i = 0; i < s.length(); i++) {
            if (rotated.equals(goal)) {
                return true;
            }
            rotated = rotated.substring(1) + rotated.charAt(0);
        }
        return false;
    }
}
```

## 🔹 Dry Run

**Input:** `s = "abcde"`, `goal = "cdeab"`

| Step | Rotated String | Comparison with Goal | Result |
|------|----------------|----------------------|--------|
| 0    | "abcde"        | "cdeab" ≠ "abcde"    | false  |
| 1    | "bcdea"        | "cdeab" ≠ "bcdea"    | false  |
| 2    | "cdeab"        | "cdeab" == "cdeab"   | true   |

**Output:** `true`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n²)               |
| Space Complexity | O(n)                |

**Explanation:**
- Time: Each rotation involves creating a new substring (O(n)), and we perform this up to `n` times.
- Space: We store the rotated string, which requires O(n) space.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the insight that `goal` must be a substring of `s + s` if `s` can be rotated to form `goal`. This reduces the problem to a single substring check, which can be done efficiently using built-in methods.

## 🔹 Why This Works

If `s` can be rotated to form `goal`, then `goal` must appear as a contiguous substring in `s + s`. For example:
- `s = "abcde"`, `s + s = "abcdeabcde"`
- `goal = "cdeab"` appears in `s + s` as a substring.

This approach avoids generating all rotations explicitly, significantly improving efficiency.

## 🔹 Algorithm

1. If the lengths of `s` and `goal` are different, return `false`.
2. Concatenate `s` with itself to form `s + s`.
3. Check if `goal` is a substring of `s + s`.
4. Return `true` if it is, otherwise `false`.

## 🔹 Code

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        return (s + s).contains(goal);
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `s = "abcde"`, `goal = "cdeab"`

1. Check lengths: `s.length() == goal.length()` → `5 == 5` → proceed.
2. Concatenate `s + s`: `"abcdeabcde"`.
3. Check if `"cdeab"` is a substring of `"abcdeabcde"` → `true`.

**Output:** `true`

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(n)                |
| Space Complexity | O(n)                |

**Explanation:**
- Time: The `contains` method in Java (using `String.indexOf`) runs in O(n) time for typical implementations.
- Space: Concatenating `s + s` requires O(n) space.

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `s = ""`, `goal = ""`         | `true`          | Empty strings are trivially equal.           |
| `s = "a"`, `goal = "a"`       | `true`          | Single character, no rotation needed.        |
| `s = "aa"`, `goal = "aa"`     | `true`          | All rotations result in the same string.     |
| `s = "abc"`, `goal = "bca"`   | `true`          | Valid rotation.                              |
| `s = "abc"`, `goal = "bac"`   | `false`         | Not a valid rotation.                        |
| `s = "abc"`, `goal = "abcd"`  | `false`         | Different lengths.                           |

---

# 📚 Key Takeaways

1. **Pattern Recognition:** The problem reduces to checking if `goal` is a substring of `s + s`. This is a common pattern in rotation problems.
2. **Efficiency:** The optimal solution avoids brute-force checks by leveraging substring properties.
3. **Edge Cases:** Always handle empty strings, single characters, and length mismatches.
4. **String Manipulation:** Understanding how string concatenation and substring checks work is crucial.

---

# 🚀 Interview Tips

1. **Follow-up Question:** What if rotations are allowed in both directions (left and right)? How would your solution change?
   - *Answer:* The same approach works, as `s + s` covers all possible rotations in either direction.

2. **Common Pitfall:** Forgetting to check if the lengths of `s` and `goal` are equal. This is a quick early exit to avoid unnecessary computations.

3. **Alternative Approach:** You could also use the Knuth-Morris-Pratt (KMP) algorithm for substring matching to achieve O(n) time with O(1) space (if modified), but this is overkill for typical constraints.

4. **Optimization Discussion:** The optimal solution is already very efficient, but if space is a concern, you could implement a custom substring check without concatenation (e.g., using a rolling hash).

---

# ✅ Conclusion

The **optimal approach** is highly efficient and elegant, leveraging the insight that `goal` must be a substring of `s + s`. This reduces the problem to a simple substring check, avoiding the O(n²) time complexity of the brute-force method. The solution is both **interview-friendly** and **production-ready**, making it ideal for technical discussions and coding interviews.

**Key Insight:** Rotation problems often involve clever string manipulations or pattern recognition. Always look for opportunities to reduce the problem to a simpler form, such as a substring check.