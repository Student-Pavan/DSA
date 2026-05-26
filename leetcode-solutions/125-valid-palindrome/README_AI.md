# Valid Palindrome

---

# 📝 Problem Statement

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

**Constraints:**

- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters.

---

# 💡 Intuition

The key insight is that we need to compare characters from both ends of the string while ignoring non-alphanumeric characters. This approach efficiently checks for palindrome properties without modifying the original string.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Create a new string by filtering out all non-alphanumeric characters and converting to lowercase.
2. Compare the filtered string with its reverse.

## 🔹 Algorithm

1. Initialize an empty string `filtered`.
2. Iterate through each character in the input string:
   - If the character is alphanumeric, convert to lowercase and append to `filtered`.
3. Compare `filtered` with its reverse.

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(String s) {
        String filtered = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(filtered).reverse().toString();
        return filtered.equals(reversed);
    }
}
```

## 🔹 Dry Run

Let's dry run with `s = "A man, a plan, a canal: Panama"`:

| Step | Action | Result |
|---|---|---|
| 1 | Filter non-alphanumeric and convert to lowercase | "amanaplanacanalpanama" |
| 2 | Reverse the filtered string | "amanaplanacanalpanama" |
| 3 | Compare filtered and reversed strings | true |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use two pointers starting from both ends of the string, moving towards the center while skipping non-alphanumeric characters.

## 🔹 Why This Works

This approach avoids creating a new string, saving space. It directly compares characters from both ends, which is more efficient.

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the string.
2. While `left` < `right`:
   - Skip non-alphanumeric characters from the left.
   - Skip non-alphanumeric characters from the right.
   - Compare characters at `left` and `right` (case-insensitive).
   - If they don't match, return false.
   - Move both pointers towards the center.
3. If the loop completes, return true.

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

## 🔹 Detailed Dry Run

Let's dry run with `s = "A man, a plan, a canal: Panama"`:

| Step | Left | Right | Action | Result |
|---|---|---|---|---|
| 1 | 0 | 21 | Skip non-alphanumeric | left=0, right=21 |
| 2 | 0 | 21 | Compare 'a' and 'a' | Match |
| 3 | 1 | 20 | Skip non-alphanumeric | left=1, right=20 |
| 4 | 1 | 20 | Compare 'm' and 'm' | Match |
| 5 | 2 | 19 | Skip non-alphanumeric | left=2, right=19 |
| 6 | 2 | 19 | Compare 'a' and 'a' | Match |
| ... | ... | ... | ... | ... |
| 10 | 10 | 10 | left >= right | Loop ends |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty string: `""` → `true`
- Single character: `"a"` → `true`
- All non-alphanumeric: `".,;'`" → `true`
- Mixed case: `"RaceCar"` → `true`
- With spaces: `"A man, a plan, a canal: Panama"` → `true`
- Non-palindrome: `"hello"` → `false`

---

# 📚 Key Takeaways

- Two-pointer technique is efficient for string manipulation problems.
- In-place comparison avoids extra space usage.
- Character classification methods (`isLetterOrDigit`) simplify filtering.
- Case-insensitive comparison requires proper conversion.

---

# 🚀 Interview Tips

- Ask if the string is guaranteed to be ASCII or might contain Unicode.
- Discuss time/space tradeoffs between brute force and optimal approaches.
- Mention that this problem tests string manipulation and edge case handling.
- Consider asking if the solution should be case-sensitive.

---

# ✅ Conclusion

The optimal approach is preferred because it operates in O(n) time with O(1) space, making it more efficient for large inputs. The key insight is using two pointers to skip non-alphanumeric characters while comparing from both ends.