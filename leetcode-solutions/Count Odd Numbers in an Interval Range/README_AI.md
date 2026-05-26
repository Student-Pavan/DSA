# 📌 Count Odd Numbers in an Interval Range

---

# 📝 Problem Statement

Given two non-negative integers `low` and `high`, return the count of odd numbers between `low` and `high` (inclusive).

### **Objective**
Count all odd integers in the closed interval `[low, high]`.

### **Input/Output Expectations**
- **Input:** Two integers `low` and `high` (0 ≤ `low` ≤ `high` ≤ 10^9)
- **Output:** An integer representing the count of odd numbers in the range.

### **Constraints**
- The input range can be very large (up to 10^9), so an efficient solution is required.
- Both `low` and `high` are non-negative integers.

---

# 💡 Intuition

The key observation here is that odd numbers are evenly distributed in any interval. Instead of iterating through every number in the range (which would be inefficient for large ranges), we can use a mathematical formula to compute the count directly.

The number of odd numbers between two integers can be determined by:
1. The total numbers in the range.
2. Whether the endpoints (`low` and `high`) are odd or even.

This allows us to derive the count in constant time, making the solution highly efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The naive approach involves iterating through every number in the range `[low, high]` and counting how many of them are odd. This is straightforward but inefficient for large ranges.

## 🔹 Algorithm
1. Initialize a counter to `0`.
2. Iterate from `low` to `high` (inclusive).
3. For each number, check if it is odd (i.e., `num % 2 != 0`).
4. If odd, increment the counter.
5. Return the counter.

## 🔹 Code
```java
class Solution {
    public int countOdds(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (i % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}
```

## 🔹 Dry Run
**Example:** `low = 3`, `high = 7`

| Step | Current Number | Is Odd? | Count |
|------|----------------|---------|-------|
| 1    | 3              | Yes     | 1     |
| 2    | 4              | No      | 1     |
| 3    | 5              | Yes     | 2     |
| 4    | 6              | No      | 2     |
| 5    | 7              | Yes     | 3     |

**Result:** `3` (Odd numbers: 3, 5, 7)

## 🔹 Complexity Analysis
| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach leverages mathematical properties to compute the count in constant time. The formula is derived as follows:
1. The total numbers in the range `[low, high]` is `high - low + 1`.
2. If both `low` and `high` are even, the count of odd numbers is `(total / 2)`.
3. If either `low` or `high` is odd, the count is `(total + 1) / 2`.

This avoids iteration and works in **O(1)** time.

## 🔹 Why This Works
- Odd numbers are uniformly distributed in any interval.
- The formula accounts for whether the range starts or ends with an odd number, adjusting the count accordingly.

## 🔹 Algorithm
1. Compute the total numbers in the range: `total = high - low + 1`.
2. If `total` is even, the count of odd numbers is `total / 2`.
3. If `total` is odd, the count is `(total + 1) / 2` if either `low` or `high` is odd, otherwise `total / 2`.

Alternatively, a simplified formula is:
```java
return (high + 1) / 2 - low / 2;
```

## 🔹 Code
```java
class Solution {
    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }
}
```

## 🔹 Detailed Dry Run
**Example 1:** `low = 3`, `high = 7`
- `(7 + 1) / 2 = 4`
- `3 / 2 = 1`
- `4 - 1 = 3` (Correct: 3, 5, 7)

**Example 2:** `low = 2`, `high = 5`
- `(5 + 1) / 2 = 3`
- `2 / 2 = 1`
- `3 - 1 = 2` (Correct: 3, 5)

**Example 3:** `low = 8`, `high = 10`
- `(10 + 1) / 2 = 5`
- `8 / 2 = 4`
- `5 - 4 = 1` (Correct: 9)

## 🔹 Complexity Analysis
| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(1)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases
| Case                     | Expected Output | Explanation                          |
|--------------------------|-----------------|--------------------------------------|
| `low = 0`, `high = 0`    | 0               | Only even number (0) in range.       |
| `low = 1`, `high = 1`    | 1               | Only odd number (1) in range.        |
| `low = 2`, `high = 2`    | 0               | Only even number (2) in range.       |
| `low = 1`, `high = 10^9` | 500,000,000     | Large range with half odd numbers.   |
| `low = 3`, `high = 3`    | 1               | Single odd number.                   |

---

# 📚 Key Takeaways
1. **Mathematical Insight:** Odd numbers are uniformly distributed, allowing constant-time computation.
2. **Efficiency:** The optimal solution avoids iteration, making it suitable for very large ranges.
3. **Formula Simplification:** `(high + 1) / 2 - low / 2` is a concise way to compute the count.
4. **Edge Case Handling:** The formula naturally handles all edge cases, including single-number ranges.

---

# 🚀 Interview Tips
1. **Follow-Up Questions:**
   - How would you handle negative numbers? (The formula still works.)
   - Can you extend this to count even numbers? (Yes, `total - countOdds`.)
2. **Common Pitfalls:**
   - Off-by-one errors in range calculation.
   - Forgetting to handle the inclusive range.
3. **Alternative Approaches:**
   - Using arithmetic progression (sum of odd numbers).
   - Iterative approach (for small ranges).

---

# ✅ Conclusion
The optimal solution leverages mathematical properties to compute the count of odd numbers in **O(1)** time, making it highly efficient for large ranges. The brute-force approach, while simple, is impractical for large inputs. The key takeaway is recognizing patterns in number distribution to derive efficient solutions.

This problem is a great example of how mathematical insights can optimize algorithmic performance.