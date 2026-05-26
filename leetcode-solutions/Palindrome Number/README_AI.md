# 📌 Palindrome Number

---

# 📝 Problem Statement

Determine whether an integer is a palindrome. A palindrome number reads the same backward as forward.

**Objective:**
Return `true` if the given integer is a palindrome, otherwise return `false`.

**Input:**
- An integer `x` (where `-2³¹ <= x <= 2³¹ - 1`)

**Output:**
- A boolean value indicating whether `x` is a palindrome.

**Constraints:**
- Negative numbers cannot be palindromes (e.g., `-121` is not a palindrome because the negative sign does not mirror).
- The solution should avoid converting the integer to a string if possible (though this is allowed in brute force).

---

# 💡 Intuition

A palindrome number remains unchanged when its digits are reversed. The key insight is to compare the original number with its reversed version. If they match, the number is a palindrome.

However, reversing the entire number may be inefficient for very large numbers. Instead, we can reverse only half of the digits and compare it with the other half, which optimizes both time and space complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The naive approach involves converting the integer to a string, then checking if the string reads the same forwards and backwards. This leverages the built-in string reversal functionality but may not be the most efficient due to the overhead of string conversion.

---

## 🔹 Algorithm

1. Convert the integer `x` to a string.
2. Reverse the string.
3. Compare the original string with the reversed string.
4. Return `true` if they are identical, otherwise return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = Integer.toString(x);
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}
```

---

## 🔹 Dry Run

**Example:** `x = 121`

| Step | Original String | Reversed String | Comparison | Result |
|------|------------------|------------------|------------|--------|
| 1    | "121"            | "121"            | "121" == "121" | `true` |

**Example:** `x = -121`

| Step | Original String | Reversed String | Comparison | Result |
|------|------------------|------------------|------------|--------|
| 1    | "-121"           | "121-"           | "-121" != "121-" | `false` |

**Example:** `x = 10`

| Step | Original String | Reversed String | Comparison | Result |
|------|------------------|------------------|------------|--------|
| 1    | "10"             | "01"             | "10" != "01" | `false` |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

- **Time:** Converting the integer to a string and reversing it both take O(n) time, where `n` is the number of digits in `x`.
- **Space:** Storing the string and its reversed version requires O(n) additional space.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach avoids converting the integer to a string and instead reverses only half of the digits. This reduces both time and space complexity.

**Key Insight:**
- For a number to be a palindrome, the first half of the digits must match the reverse of the second half.
- We can reverse the second half of the number and compare it with the first half.
- If the number has an odd number of digits, we can ignore the middle digit by dividing the reversed half by 10.

---

## 🔹 Why This Works

- **Efficiency:** Reversing only half of the digits reduces the number of operations by half.
- **Space:** No additional space is used beyond a few variables to store the reversed half.
- **Edge Handling:** Negative numbers are immediately identified as non-palindromes. Numbers ending with `0` (except `0` itself) are also non-palindromes because they cannot start with `0`.

---

## 🔹 Algorithm

1. Handle edge cases:
   - If `x` is negative, return `false`.
   - If `x` ends with `0` and is not `0` itself, return `false`.
2. Initialize a variable `reversedHalf` to `0`.
3. While `x` is greater than `reversedHalf`:
   - Append the last digit of `x` to `reversedHalf`.
   - Remove the last digit from `x`.
4. After the loop, check if `x` equals `reversedHalf` (for even digits) or `x` equals `reversedHalf / 10` (for odd digits).
5. Return `true` if either condition is met, otherwise return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `x = 1221`

| Step | x   | reversedHalf | Action                     | Condition Check       |
|------|-----|--------------|----------------------------|-----------------------|
| 1    | 1221| 0            | reversedHalf = 0*10 + 1    | 1221 > 0              |
| 2    | 122 | 1            | reversedHalf = 1*10 + 2    | 122 > 1               |
| 3    | 12  | 12           | Loop ends (12 > 12 is false)| x == reversedHalf → `true` |

**Example:** `x = 12321`

| Step | x   | reversedHalf | Action                     | Condition Check       |
|------|-----|--------------|----------------------------|-----------------------|
| 1    | 12321| 0           | reversedHalf = 0*10 + 1    | 12321 > 0             |
| 2    | 1232 | 1           | reversedHalf = 1*10 + 2    | 1232 > 1              |
| 3    | 123  | 12          | reversedHalf = 12*10 + 3   | 123 > 12              |
| 4    | 12   | 123         | Loop ends (12 > 123 is false)| x == reversedHalf/10 → 12 == 12 → `true` |

**Example:** `x = 10`

| Step | x   | reversedHalf | Action                     | Condition Check       |
|------|-----|--------------|----------------------------|-----------------------|
| 1    | 10  | -            | Edge case (x % 10 == 0)    | Return `false`        |

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(log n)    |
| Space Complexity | O(1)        |

- **Time:** The loop runs for half the number of digits in `x`, which is O(log n) (base 10).
- **Space:** Only a constant amount of extra space is used.

---

# 🔍 Edge Cases

| Edge Case               | Expected Output | Explanation                                  |
|-------------------------|-----------------|----------------------------------------------|
| `x = 0`                 | `true`          | Single-digit number is a palindrome.         |
| `x = 5`                 | `true`          | Single-digit number is a palindrome.         |
| `x = -121`              | `false`         | Negative numbers cannot be palindromes.      |
| `x = 10`                | `false`         | Numbers ending with `0` (except `0`) are not palindromes. |
| `x = 12321`             | `true`          | Odd-length palindrome.                       |
| `x = 1221`              | `true`          | Even-length palindrome.                      |
| `x = 1234567899`        | `false`         | Large non-palindrome number.                 |
| `x = Integer.MAX_VALUE` | `false`         | Largest 32-bit integer is not a palindrome.  |

---

# 📚 Key Takeaways

1. **String Conversion vs. Mathematical Reversal:**
   - Converting to a string is intuitive but less efficient.
   - Mathematical reversal (optimal approach) is faster and uses constant space.

2. **Half-Reversal Optimization:**
   - Reversing only half of the digits reduces time complexity from O(n) to O(log n).

3. **Edge Case Handling:**
   - Negative numbers and numbers ending with `0` (except `0`) are non-palindromes.

4. **Space Efficiency:**
   - The optimal approach avoids additional data structures, making it space-efficient.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - How would you handle very large numbers (e.g., 64-bit integers)?
   - Can you solve this without any extra space (even for the brute force approach)?
   - What if the input is a string instead of an integer?

2. **Common Pitfalls:**
   - Forgetting to handle negative numbers.
   - Not considering numbers ending with `0`.
   - Using extra space unnecessarily (e.g., storing the entire reversed number).

3. **Alternative Approaches:**
   - Recursive reversal (though less efficient due to stack space).
   - Using a stack to reverse digits (similar to string reversal but with integers).

4. **Optimization Discussion:**
   - The optimal approach is preferred in interviews due to its efficiency and elegance.
   - Always discuss trade-offs between time and space complexity.

---

# ✅ Conclusion

The **optimal approach** is the clear winner for this problem due to its **O(log n) time complexity** and **O(1) space complexity**. It efficiently checks for palindromes by reversing only half of the digits, avoiding unnecessary computations and extra space usage.

**Key Insight:**
- Reversing half of the digits is sufficient to determine if a number is a palindrome.
- Edge cases (negative numbers, numbers ending with `0`) must be handled explicitly.

This problem is a great example of how **mathematical insights** can lead to **optimal solutions** in algorithmic challenges.