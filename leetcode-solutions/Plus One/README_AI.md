# 📌 Plus One

---

# 📝 Problem Statement

You are given a large integer represented as an integer array `digits`, where each `digits[i]` is the `i-th` digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading zero.

**Objective:**
Increment the large integer by one and return the resulting array of digits.

**Input:**
- `digits`: An integer array representing a non-negative integer.
- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`
- The array does not contain any leading zeros.

**Output:**
- An integer array representing the incremented integer.

**Constraints:**
- The input is guaranteed to be a valid non-negative integer without leading zeros.
- The result may require an extra digit (e.g., `999` becomes `1000`).

---

# 💡 Intuition

The problem requires incrementing a number represented as an array of digits by one. The key insight is that adding one to a number affects the least significant digit (rightmost) first. If this digit is less than 9, we can simply increment it and return the array. However, if it is 9, it rolls over to 0, and we must carry over the increment to the next more significant digit (left). This process continues until we encounter a digit that is not 9 or reach the most significant digit.

The challenge lies in handling the carry-over efficiently, especially when all digits are 9 (e.g., `999` becomes `1000`), which requires an additional digit at the beginning of the array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves converting the entire array into a number, incrementing it by one, and then converting it back into an array. However, this approach is impractical for very large numbers (e.g., 100 digits) due to the limitations of primitive data types in Java (e.g., `int` or `long` cannot store such large numbers). Thus, this approach is not feasible for the given constraints but serves as a conceptual starting point.

---

## 🔹 Algorithm

1. Convert the array of digits into a string.
2. Parse the string into a `BigInteger` (to handle very large numbers).
3. Increment the `BigInteger` by one.
4. Convert the result back into a string.
5. Split the string into individual characters and convert them back into an integer array.

---

## 🔹 Code

```java
import java.math.BigInteger;

class Solution {
    public int[] plusOne(int[] digits) {
        // Convert the array to a string
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        String numStr = sb.toString();

        // Parse the string into a BigInteger and increment by one
        BigInteger num = new BigInteger(numStr);
        num = num.add(BigInteger.ONE);

        // Convert the result back to a string
        String resultStr = num.toString();

        // Convert the string back to an array of digits
        int[] result = new int[resultStr.length()];
        for (int i = 0; i < resultStr.length(); i++) {
            result[i] = resultStr.charAt(i) - '0';
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

**Input:** `digits = [1, 2, 3]`

| Step | Action | State |
|------|--------|-------|
| 1 | Convert array to string | `"123"` |
| 2 | Parse string to `BigInteger` | `123` |
| 3 | Increment by one | `124` |
| 4 | Convert back to string | `"124"` |
| 5 | Convert string to array | `[1, 2, 4]` |

**Output:** `[1, 2, 4]`

**Input:** `digits = [9, 9, 9]`

| Step | Action | State |
|------|--------|-------|
| 1 | Convert array to string | `"999"` |
| 2 | Parse string to `BigInteger` | `999` |
| 3 | Increment by one | `1000` |
| 4 | Convert back to string | `"1000"` |
| 5 | Convert string to array | `[1, 0, 0, 0]` |

**Output:** `[1, 0, 0, 0]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

**Explanation:**
- Converting the array to a string and back takes O(n) time, where `n` is the number of digits.
- `BigInteger` operations are O(n) for numbers with `n` digits.
- The space complexity is O(n) due to the storage of the string and `BigInteger`.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach avoids converting the entire array into a number and instead works directly with the array. We start from the least significant digit (end of the array) and move leftwards, handling the carry-over as we go. If a digit is less than 9, we simply increment it and return the array. If it is 9, we set it to 0 and continue the process. If we reach the most significant digit and it is 9, we set it to 0 and prepend a 1 to the array.

---

## 🔹 Why This Works

This approach efficiently handles the carry-over without converting the entire array into a number. It leverages the fact that the carry-over only affects digits to the left, allowing us to terminate early if no further carry-over is needed. This reduces the time complexity to O(n) in the worst case (e.g., `999` becomes `1000`), but often much less (e.g., `123` becomes `124` in O(1) time).

---

## 🔹 Algorithm

1. Start from the last digit (least significant digit) and move leftwards.
2. If the current digit is less than 9, increment it by one and return the array.
3. If the current digit is 9, set it to 0 and continue to the next digit to the left.
4. If all digits are 9, set all digits to 0 and prepend a 1 to the array.

---

## 🔹 Code

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // If all digits were 9, we need to add a new digit at the beginning
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `digits = [1, 2, 3]`

| Iteration | Current Digit | Action | Array State |
|-----------|---------------|--------|-------------|
| 1 | 3 | Increment to 4 | `[1, 2, 4]` |
| Return | - | - | `[1, 2, 4]` |

**Output:** `[1, 2, 4]`

**Input:** `digits = [9, 9, 9]`

| Iteration | Current Digit | Action | Array State |
|-----------|---------------|--------|-------------|
| 1 | 9 | Set to 0 | `[9, 9, 0]` |
| 2 | 9 | Set to 0 | `[9, 0, 0]` |
| 3 | 9 | Set to 0 | `[0, 0, 0]` |
| All digits processed | - | Prepend 1 | `[1, 0, 0, 0]` |

**Output:** `[1, 0, 0, 0]`

**Input:** `digits = [1, 9, 9]`

| Iteration | Current Digit | Action | Array State |
|-----------|---------------|--------|-------------|
| 1 | 9 | Set to 0 | `[1, 9, 0]` |
| 2 | 9 | Set to 0 | `[1, 0, 0]` |
| 3 | 1 | Increment to 2 | `[2, 0, 0]` |
| Return | - | - | `[2, 0, 0]` |

**Output:** `[2, 0, 0]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) (only in the worst case) |

**Explanation:**
- The time complexity is O(n) in the worst case (e.g., `999`), where we traverse the entire array.
- The space complexity is O(1) for most cases, but O(n) in the worst case (e.g., `999`), where we need to allocate a new array of size `n + 1`.

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output | Explanation |
|-----------|-------|-----------------|-------------|
| Single digit | `[0]` | `[1]` | Incrementing 0 gives 1. |
| Single digit 9 | `[9]` | `[1, 0]` | Incrementing 9 rolls over to 10. |
| All digits 9 | `[9, 9, 9]` | `[1, 0, 0, 0]` | All digits roll over, requiring an extra digit. |
| No carry-over | `[1, 2, 3]` | `[1, 2, 4]` | Only the last digit is incremented. |
| Carry-over to middle digit | `[1, 9, 9]` | `[2, 0, 0]` | Carry-over affects the second digit. |
| Large array | `[1, 0, 0, ..., 0]` (100 digits) | `[1, 0, 0, ..., 1]` | Only the last digit is incremented. |

---

# 📚 Key Takeaways

1. **Carry-over Handling:** The key challenge is efficiently handling the carry-over when incrementing digits, especially when all digits are 9.
2. **Early Termination:** The optimal solution leverages early termination to avoid unnecessary iterations once the carry-over is resolved.
3. **In-Place vs. New Array:** The optimal solution modifies the array in-place when possible but allocates a new array when necessary (e.g., `999` to `1000`).
4. **Time Complexity:** The optimal solution achieves O(n) time complexity, which is optimal for this problem.
5. **Space Complexity:** The space complexity is O(1) for most cases but O(n) in the worst case (all digits are 9).

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Ask if the input can have leading zeros (though the problem states it does not).
2. **Discuss Edge Cases:** Mention edge cases like `[0]`, `[9]`, and `[9, 9, 9]` to demonstrate thoroughness.
3. **Optimization Insight:** Highlight the importance of early termination in the optimal solution.
4. **Follow-Up Questions:**
   - How would you handle the problem if the input could have leading zeros?
   - How would you extend this solution to add two large numbers represented as arrays?
5. **Alternative Approaches:** Discuss the brute force approach (converting to a number) and why it is impractical for large inputs.
6. **Time-Space Tradeoff:** Emphasize the tradeoff between time and space in the optimal solution (O(n) time, O(1) or O(n) space).

---

# ✅ Conclusion

The **Plus One** problem is a classic example of handling carry-over in digit manipulation. The brute force approach, while conceptually simple, is impractical for large inputs due to the limitations of primitive data types. The optimal approach efficiently handles the carry-over in-place, terminating early when possible and only allocating additional space when necessary (e.g., `999` to `1000`).

**Key Insight:**
The optimal solution works from right to left, handling the carry-over digit by digit. This approach minimizes both time and space complexity, making it ideal for large inputs.

**Final Takeaway:**
Always consider edge cases and strive for solutions that minimize unnecessary computations. The optimal solution here demonstrates how a simple loop with early termination can outperform more complex approaches.