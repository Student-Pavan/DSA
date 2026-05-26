# 📌 Reverse String

---

# 📝 Problem Statement

Write a function that reverses a string **in-place**. The input string is given as an array of characters `s`.

**Objective:**
Reverse the array of characters without using extra space (modify the input array directly).

**Input:**
- `s`: A character array representing the string to be reversed.

**Output:**
- The input array `s` should be modified in-place to represent the reversed string.

**Constraints:**
- `1 <= s.length <= 10^5`
- `s[i]` is a printable ASCII character.

---

# 💡 Intuition

Reversing a string in-place requires swapping characters symmetrically from both ends towards the center. The key insight is that we can achieve this efficiently using a **two-pointer technique**, where one pointer starts at the beginning and the other at the end of the array. By swapping the characters at these pointers and moving them towards the center, we can reverse the array in linear time without using additional space.

This approach is optimal because it minimizes the number of operations and avoids the need for extra memory, making it both time and space efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach to reverse a string involves creating a new array and copying characters from the original array in reverse order. However, this violates the in-place constraint and uses extra space. While this approach is straightforward, it is not optimal for the given problem constraints.

---

## 🔹 Algorithm

1. Create a new character array of the same length as the input.
2. Iterate over the original array from the last element to the first.
3. Copy each character from the original array to the new array in reverse order.
4. Copy the reversed array back to the original array.

---

## 🔹 Code

```java
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        char[] reversed = new char[n];

        for (int i = 0; i < n; i++) {
            reversed[i] = s[n - 1 - i];
        }

        for (int i = 0; i < n; i++) {
            s[i] = reversed[i];
        }
    }
}
```

---

## 🔹 Dry Run

**Input:** `s = ['h', 'e', 'l', 'l', 'o']`

| Step | Original Array | Reversed Array (Temp) | Action |
|------|----------------|-----------------------|--------|
| 1    | ['h', 'e', 'l', 'l', 'o'] | [] | Initialize reversed array |
| 2    | ['h', 'e', 'l', 'l', 'o'] | ['o'] | Copy 'o' from s[4] to reversed[0] |
| 3    | ['h', 'e', 'l', 'l', 'o'] | ['o', 'l'] | Copy 'l' from s[3] to reversed[1] |
| 4    | ['h', 'e', 'l', 'l', 'o'] | ['o', 'l', 'l'] | Copy 'l' from s[2] to reversed[2] |
| 5    | ['h', 'e', 'l', 'l', 'o'] | ['o', 'l', 'l', 'e'] | Copy 'e' from s[1] to reversed[3] |
| 6    | ['h', 'e', 'l', 'l', 'o'] | ['o', 'l', 'l', 'e', 'h'] | Copy 'h' from s[0] to reversed[4] |
| 7    | ['o', 'l', 'l', 'e', 'h'] | ['o', 'l', 'l', 'e', 'h'] | Copy reversed array back to s |

**Final Output:** `['o', 'l', 'l', 'e', 'h']`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the **two-pointer technique** to reverse the array in-place. We initialize two pointers, `left` at the start of the array and `right` at the end. We swap the characters at these pointers and move the `left` pointer forward and the `right` pointer backward until they meet or cross each other. This approach ensures that we reverse the array in linear time without using extra space.

---

## 🔹 Why This Works

- **In-Place Modification:** The two-pointer technique modifies the original array directly, adhering to the in-place constraint.
- **Efficiency:** Each character is swapped exactly once, resulting in a time complexity of O(n).
- **Space Optimization:** No additional data structures are used, resulting in O(1) space complexity.

---

## 🔹 Algorithm

1. Initialize two pointers: `left = 0` and `right = s.length - 1`.
2. While `left < right`:
   - Swap `s[left]` and `s[right]`.
   - Increment `left` by 1.
   - Decrement `right` by 1.
3. The array is now reversed in-place.

---

## 🔹 Code

```java
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `s = ['h', 'e', 'l', 'l', 'o']`

| Step | Left | Right | Swap | Array State |
|------|------|-------|------|-------------|
| 1    | 0    | 4     | 'h' ↔ 'o' | ['o', 'e', 'l', 'l', 'h'] |
| 2    | 1    | 3     | 'e' ↔ 'l' | ['o', 'l', 'l', 'e', 'h'] |
| 3    | 2    | 2     | - | Loop terminates |

**Final Output:** `['o', 'l', 'l', 'e', 'h']`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** `s = []` → No operation needed.
- **Single Character:** `s = ['a']` → Array remains unchanged.
- **All Identical Characters:** `s = ['a', 'a', 'a']` → Array remains unchanged after reversal.
- **Even Length:** `s = ['a', 'b', 'c', 'd']` → Reversed to `['d', 'c', 'b', 'a']`.
- **Odd Length:** `s = ['a', 'b', 'c']` → Reversed to `['c', 'b', 'a']`.
- **Large Input:** `s.length = 10^5` → Ensure O(n) time and O(1) space.

---

# 📚 Key Takeaways

1. **Two-Pointer Technique:** A powerful tool for in-place array manipulations, especially for reversing or partitioning arrays.
2. **In-Place Operations:** Essential for optimizing space complexity in constrained environments.
3. **Time-Space Tradeoff:** The brute force approach trades space for simplicity, while the optimal approach prioritizes efficiency.
4. **Edge Case Handling:** Always consider edge cases to ensure robustness in real-world applications.

---

# 🚀 Interview Tips

- **Clarify Constraints:** Confirm whether in-place modification is required or if extra space is allowed.
- **Discuss Tradeoffs:** Explain the pros and cons of brute force vs. optimal approaches.
- **Follow-Up Questions:**
  - How would you reverse a string if extra space were allowed?
  - Can you reverse a string recursively? What are the tradeoffs?
  - How would you handle Unicode characters or multi-byte encodings?
- **Common Pitfalls:**
  - Forgetting to handle edge cases (e.g., empty array or single character).
  - Using extra space when in-place modification is required.
  - Off-by-one errors in pointer manipulation.

---

# ✅ Conclusion

The optimal solution for reversing a string in-place leverages the **two-pointer technique**, achieving O(n) time complexity and O(1) space complexity. This approach is efficient, elegant, and adheres to the problem constraints, making it the preferred choice for interviews and production environments. The key takeaway is the importance of in-place operations and the versatility of the two-pointer technique in solving array manipulation problems.