# 📌 Max Consecutive Ones

---

# 📝 Problem Statement

Given a binary array `nums`, return the maximum number of consecutive `1`'s in the array.

### **Objective**
Find the longest sequence of consecutive `1`s in the given binary array.

### **Input**
- A binary array `nums` where each element is either `0` or `1`.
- `1 <= nums.length <= 10^5`
- `nums[i]` is either `0` or `1`.

### **Output**
- An integer representing the maximum number of consecutive `1`s.

### **Constraints**
- The array can be empty (though constraints say `1 <= nums.length`).
- The array may contain only `0`s or only `1`s.
- The solution must be efficient due to large input size.

---

# 💡 Intuition

The problem requires tracking sequences of consecutive `1`s. The key insight is that we can traverse the array once while maintaining a running count of consecutive `1`s. Whenever we encounter a `0`, we reset the count. The maximum count encountered during the traversal is our answer.

This problem is a classic example of **sliding window** or **linear scan** pattern, where we maintain state during iteration to avoid unnecessary nested loops.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every possible subarray of `1`s. For each element, if it is a `1`, we count how many consecutive `1`s follow it. We keep track of the maximum count encountered.

This approach is inefficient because it uses nested loops: one to iterate through each element and another to count consecutive `1`s starting from that element.

## 🔹 Algorithm

1. Initialize `maxCount = 0`.
2. Iterate through each element in the array:
   - If the current element is `1`, initialize `currentCount = 1`.
   - While the next element is also `1`, increment `currentCount` and move to the next element.
   - Update `maxCount = max(maxCount, currentCount)`.
3. Return `maxCount`.

## 🔹 Code

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int currentCount = 1;
                while (i + 1 < nums.length && nums[i + 1] == 1) {
                    currentCount++;
                    i++;
                }
                maxCount = Math.max(maxCount, currentCount);
            }
        }
        return maxCount;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [1, 1, 0, 1, 1, 1]`

| Step | Index | Current Element | Action                     | `currentCount` | `maxCount` |
|------|-------|-----------------|----------------------------|----------------|------------|
| 1    | 0     | 1               | Start counting from index 0 | 1              | 1          |
| 2    | 1     | 1               | Increment count            | 2              | 2          |
| 3    | 2     | 0               | Reset count                | 0              | 2          |
| 4    | 3     | 1               | Start counting from index 3 | 1              | 2          |
| 5    | 4     | 1               | Increment count            | 2              | 2          |
| 6    | 5     | 1               | Increment count            | 3              | 3          |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves a **single pass** through the array while maintaining a running count of consecutive `1`s. Whenever a `0` is encountered, the count is reset to `0`. The maximum count encountered during the traversal is the answer.

This approach leverages the fact that we only need to track the current streak of `1`s and update the maximum streak whenever a longer one is found.

## 🔹 Why This Works

- **Efficiency:** We traverse the array only once, making the time complexity linear.
- **Simplicity:** The logic is straightforward and easy to implement.
- **Correctness:** By resetting the count on `0`, we ensure that only consecutive `1`s are counted.

## 🔹 Algorithm

1. Initialize `maxCount = 0` and `currentCount = 0`.
2. Iterate through each element in the array:
   - If the current element is `1`, increment `currentCount`.
   - Else, reset `currentCount` to `0`.
   - Update `maxCount = max(maxCount, currentCount)`.
3. Return `maxCount`.

## 🔹 Code

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0;
            }
        }
        return maxCount;
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 1, 0, 1, 1, 1]`

| Step | Current Element | `currentCount` | `maxCount` | Action                     |
|------|-----------------|----------------|------------|----------------------------|
| 1    | 1               | 1              | 1          | Increment count            |
| 2    | 1               | 2              | 2          | Increment count            |
| 3    | 0               | 0              | 2          | Reset count                |
| 4    | 1               | 1              | 2          | Increment count            |
| 5    | 1               | 2              | 2          | Increment count            |
| 6    | 1               | 3              | 3          | Increment count            |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `nums = []`                   | `0`             | Empty array has no elements.                 |
| `nums = [0]`                  | `0`             | No `1`s present.                             |
| `nums = [1]`                  | `1`             | Single `1`.                                  |
| `nums = [0, 0, 0]`            | `0`             | All `0`s.                                    |
| `nums = [1, 1, 1]`            | `3`             | All `1`s.                                    |
| `nums = [1, 0, 1, 1, 0, 1]`   | `2`             | Multiple streaks of `1`s.                    |
| `nums = [1, 1, 0, 1, 1, 1]`   | `3`             | Longest streak at the end.                   |

---

# 📚 Key Takeaways

1. **Linear Scan:** The optimal solution leverages a single pass through the array, making it efficient.
2. **State Maintenance:** Tracking `currentCount` and `maxCount` allows us to solve the problem in constant space.
3. **Sliding Window Insight:** This problem is a simplified version of the sliding window technique, where the window size is determined by consecutive `1`s.
4. **Edge Case Handling:** Always consider edge cases like empty arrays or arrays with all `0`s or `1`s.

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - What if the array contains `-1` or other values? (Clarify constraints.)
   - How would you modify the solution to return the indices of the longest streak?
   - Can you solve this problem using a two-pointer approach?

2. **Common Pitfalls:**
   - Forgetting to reset `currentCount` on encountering a `0`.
   - Using nested loops unnecessarily (brute force approach).
   - Not handling edge cases like empty arrays or all `0`s.

3. **Alternative Approaches:**
   - **String Conversion:** Convert the array to a string and split on `0` to find the longest substring of `1`s. (Less efficient due to string operations.)
   - **Prefix Sum:** Use prefix sums to count `1`s, but this is overcomplicating for this problem.

4. **Optimization Discussion:**
   - The optimal solution is already O(n) time and O(1) space, which is the best possible for this problem.
   - Discuss trade-offs between readability and performance (e.g., brute force vs. optimal).

---

# ✅ Conclusion

The **optimal approach** is preferred because it efficiently solves the problem in **O(n) time and O(1) space**, making it suitable for large input sizes. The key insight is to maintain a running count of consecutive `1`s and reset it on encountering a `0`, while keeping track of the maximum count observed.

This problem is a great example of how a simple linear scan can outperform brute force methods, reinforcing the importance of **algorithmic efficiency** in technical interviews.