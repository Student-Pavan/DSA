# Valid Palindrome

---

# 📝 Problem Statement

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Constraints:**
- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

---

# 💡 Intuition

The key insight is that we need to compare characters from both ends of the string while ignoring non-alphanumeric characters. This approach ensures we only make one pass through the string, making it efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Create a new string containing only alphanumeric characters from the original string.
2. Convert all characters in this new string to lowercase.
3. Compare the new string with its reverse to check if it's a palindrome.

---

## 🔹 Algorithm

1. Initialize an empty string `cleaned`.
2. Iterate through each character in the input string `s`:
   - If the character is alphanumeric, convert it to lowercase and append it to `cleaned`.
3. Compare `cleaned` with its reverse to determine if it's a palindrome.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the code with the input string `s = "A man, a plan, a canal: Panama"`.

1. **Cleaning the string:**
   - Original string: "A man, a plan, a canal: Panama"
   - Remove non-alphanumeric characters: "AmanaplanacanalPanama"
   - Convert to lowercase: "amanaplanacanalpanama"

2. **Reversing the cleaned string:**
   - Reversed string: "amanaplanacanalpanama"

3. **Comparison:**
   - Compare "amanaplanacanalpanama" with its reverse "amanaplanacanalpanama"
   - They are equal, so return `true`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Instead of creating a new string, we can use two pointers to compare characters from both ends of the string. This approach avoids the extra space used for creating a new string.

---

## 🔹 Why This Works

By using two pointers, we can compare characters in place, skipping non-alphanumeric characters as we go. This method is more efficient in terms of space complexity.

---

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the string.
2. While `left` is less than `right`:
   - Skip non-alphanumeric characters from the left.
   - Skip non-alphanumeric characters from the right.
   - Compare the characters at `left` and `right` (case-insensitive).
   - If they are not equal, return `false`.
   - Move `left` forward and `right` backward.
3. If the loop completes without mismatches, return `true`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
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

## 🔹 Detailed Dry Run

Let's dry run the code with the input string `s = "A man, a plan, a canal: Panama"`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 21 | Skip non-alphanumeric | left=0, right=21 |
| 2 | 0 | 21 | Compare 'A' and 'a' | left=1, right=20 |
| 3 | 1 | 20 | Compare 'm' and 'm' | left=2, right=19 |
| 4 | 2 | 19 | Compare 'a' and 'n' | left=3, right=18 |
| 5 | 3 | 18 | Compare 'n' and 'a' | left=4, right=17 |
| 6 | 4 | 17 | Compare ',' and 'a' | Skip left |
| 7 | 5 | 17 | Compare ' ' and 'a' | Skip left |
| 8 | 6 | 17 | Compare 'a' and 'a' | left=7, right=16 |
| 9 | 7 | 16 | Compare ' ' and 'n' | Skip left |
| 10 | 8 | 16 | Compare 'p' and 'n' | left=9, right=15 |
| 11 | 9 | 15 | Compare 'l' and 'a' | left=10, right=14 |
| 12 | 10 | 14 | Compare 'a' and 'n' | left=11, right=13 |
| 13 | 11 | 13 | Compare 'n' and 'a' | left=12, right=12 |
| 14 | 12 | 12 | Compare ':' and 'a' | Skip left |
| 15 | 13 | 12 | Compare ' ' and 'a' | Skip left |
| 16 | 14 | 12 | Compare 'P' and 'a' | left=15, right=11 |
| 17 | 15 | 11 | Compare 'a' and 'n' | left=16, right=10 |
| 18 | 16 | 10 | Compare 'n' and 'a' | left=17, right=9 |
| 19 | 17 | 9 | Compare 'a' and 'a' | left=18, right=8 |
| 20 | 18 | 8 | Compare 'm' and 'a' | left=19, right=7 |
| 21 | 19 | 7 | Compare 'a' and 'a' | left=20, right=6 |
| 22 | 20 | 6 | Compare 'a' and 'a' | left=21, right=5 |
| 23 | 21 | 5 | Compare ' ' and 'a' | Skip right |
| 24 | 21 | 4 | Compare ' ' and 'a' | Skip right |
| 25 | 21 | 3 | Compare ' ' and 'a' | Skip right |
| 26 | 21 | 2 | Compare ' ' and 'a' | Skip right |
| 27 | 21 | 1 | Compare ' ' and 'a' | Skip right |
| 28 | 21 | 0 | Compare ' ' and 'A' | Skip right |
| 29 | 21 | -1 | Terminate loop | Return true |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty string: `""` → `true`
- Single character: `"a"` → `true`
- All non-alphanumeric characters: `" ,.! "` → `true`
- Mixed case and non-alphanumeric characters: `"A man, a plan, a canal: Panama"` → `true`
- Non-palindrome: `"race a car"` → `false`

---

# 📚 Key Takeaways

- Two-pointer technique is efficient for palindrome checks.
- Skipping non-alphanumeric characters in place saves space.
- Case-insensitive comparison is crucial for accurate palindrome checks.

---

# 🚀 Interview Tips

- Discuss time and space complexity trade-offs.
- Mention alternative approaches like using a stack.
- Ask about edge cases and constraints.
- Explain why the two-pointer approach is optimal.

---

# ✅ Conclusion

The optimal approach using two pointers is preferred because it operates in O(n) time with O(1) space complexity, making it efficient for large input sizes. The key insight is to compare characters from both ends while skipping non-alphanumeric characters, ensuring an optimal solution.