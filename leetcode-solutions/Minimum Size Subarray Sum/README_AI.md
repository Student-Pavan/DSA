# 📌 Minimum Size Subarray Sum

---

# 📝 Problem Statement

Given an array of **positive integers** `nums` and a **positive integer** `target`, return the **minimal length** of a **contiguous subarray** whose sum is **greater than or equal to** `target`. If no such subarray exists, return `0`.

### **Objective**
Find the smallest window (contiguous subarray) in `nums` that sums to at least `target`.

### **Input**
- `nums`: Array of positive integers (1 ≤ `nums.length` ≤ 10⁵)
- `target`: Positive integer (1 ≤ `target` ≤ 10⁹)

### **Output**
- Integer representing the minimal length of a valid subarray, or `0` if none exists.

### **Constraints**
- All elements in `nums` are **positive**.
- The solution must efficiently handle large input sizes (up to 10⁵ elements).

---

# 💡 Intuition

The problem requires finding the smallest contiguous subarray that meets or exceeds a given sum. The key insight is that we can use a **sliding window** technique to efficiently track subarrays without recalculating sums repeatedly.

- **Brute Force**: Check all possible subarrays, which is too slow for large inputs.
- **Optimal**: Use a sliding window to maintain a dynamic window that expands and contracts based on the current sum, ensuring we only traverse the array once.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach involves checking **every possible subarray** to find the smallest one that meets the sum condition. For each starting index, we expand the subarray to the right until the sum meets or exceeds `target`, then record the length.

### **Why It’s Inefficient**
- Time Complexity: O(n²) — For each of the `n` starting indices, we may traverse up to `n` elements.
- Space Complexity: O(1) — No additional space is used beyond a few variables.

---

## 🔹 Algorithm
1. Initialize `minLength` to `Integer.MAX_VALUE`.
2. Iterate over each starting index `i` in `nums`.
3. For each `i`, initialize `currentSum = 0` and `j = i`.
4. While `j < nums.length` and `currentSum < target`, add `nums[j]` to `currentSum` and increment `j`.
5. If `currentSum >= target`, update `minLength` with the smaller of its current value or `j - i`.
6. If no valid subarray is found, return `0`; otherwise, return `minLength`.

---

## 🔹 Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                if (currentSum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // No need to expand further for this i
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [2, 3, 1, 2, 4, 3]`, `target = 7`

| Step | i | j | currentSum | Action                     | minLength |
|------|---|---|------------|----------------------------|-----------|
| 1    | 0 | 0 | 2          | currentSum < 7             | ∞         |
| 2    | 0 | 1 | 5          | currentSum < 7             | ∞         |
| 3    | 0 | 2 | 6          | currentSum < 7             | ∞         |
| 4    | 0 | 3 | 8          | currentSum >= 7 → update   | 4         |
| 5    | 1 | 1 | 3          | currentSum < 7             | 4         |
| 6    | 1 | 2 | 4          | currentSum < 7             | 4         |
| 7    | 1 | 3 | 6          | currentSum < 7             | 4         |
| 8    | 1 | 4 | 10         | currentSum >= 7 → update   | 4         |
| 9    | 2 | 2 | 1          | currentSum < 7             | 4         |
| 10   | 2 | 3 | 3          | currentSum < 7             | 4         |
| 11   | 2 | 4 | 7          | currentSum >= 7 → update   | 3         |
| 12   | 3 | 3 | 2          | currentSum < 7             | 3         |
| 13   | 3 | 4 | 6          | currentSum < 7             | 3         |
| 14   | 3 | 5 | 9          | currentSum >= 7 → update   | 3         |
| 15   | 4 | 4 | 4          | currentSum < 7             | 3         |
| 16   | 4 | 5 | 7          | currentSum >= 7 → update   | 2         |
| 17   | 5 | 5 | 3          | currentSum < 7             | 2         |

**Result:** `2` (Subarray `[4, 3]`)

---

## 🔹 Complexity Analysis

| Complexity      | Value     |
|-----------------|-----------|
| Time Complexity | O(n²)     |
| Space Complexity| O(1)      |

---

# ⚡ Optimal Approach

## 🔹 Approach
The **sliding window** technique optimizes the solution by maintaining a dynamic window that expands and contracts based on the current sum. We use two pointers (`left` and `right`) to represent the window boundaries.

- **Expand**: Move `right` to include more elements until the sum meets or exceeds `target`.
- **Contract**: Move `left` to exclude elements from the left while maintaining the sum condition, updating the minimal length.

### **Why It Works**
- **Efficiency**: Each element is processed at most twice (once by `right`, once by `left`), resulting in O(n) time.
- **Correctness**: The window always represents a valid subarray, and we ensure the smallest valid window is recorded.

---

## 🔹 Why This Works
- **Positive Elements**: Since all elements are positive, the sum is strictly increasing as we expand the window. This guarantees that once the sum meets `target`, further expansion is unnecessary for the current `left`.
- **Dynamic Window**: By contracting from the left, we efficiently explore smaller subarrays without recalculating sums.

---

## 🔹 Algorithm
1. Initialize `left = 0`, `currentSum = 0`, and `minLength = Integer.MAX_VALUE`.
2. Iterate `right` from `0` to `n-1`:
   - Add `nums[right]` to `currentSum`.
   - While `currentSum >= target`:
     - Update `minLength` with `right - left + 1`.
     - Subtract `nums[left]` from `currentSum` and increment `left`.
3. Return `minLength` if found, otherwise `0`.

---

## 🔹 Code

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [2, 3, 1, 2, 4, 3]`, `target = 7`

| Step | right | left | currentSum | Action                     | minLength |
|------|-------|------|------------|----------------------------|-----------|
| 1    | 0     | 0    | 2          | currentSum < 7             | ∞         |
| 2    | 1     | 0    | 5          | currentSum < 7             | ∞         |
| 3    | 2     | 0    | 6          | currentSum < 7             | ∞         |
| 4    | 3     | 0    | 8          | currentSum >= 7 → update   | 4         |
| 5    | 3     | 1    | 6          | currentSum < 7 → break     | 4         |
| 6    | 4     | 1    | 10         | currentSum >= 7 → update   | 4         |
| 7    | 4     | 2    | 7          | currentSum >= 7 → update   | 3         |
| 8    | 4     | 3    | 6          | currentSum < 7 → break     | 3         |
| 9    | 5     | 3    | 9          | currentSum >= 7 → update   | 3         |
| 10   | 5     | 4    | 7          | currentSum >= 7 → update   | 2         |
| 11   | 5     | 5    | 3          | currentSum < 7 → break     | 2         |

**Result:** `2` (Subarray `[4, 3]`)

---

## 🔹 Complexity Analysis

| Complexity      | Value     |
|-----------------|-----------|
| Time Complexity | O(n)      |
| Space Complexity| O(1)      |

---

# 🔍 Edge Cases

| Case                     | Expected Output | Explanation                          |
|--------------------------|-----------------|--------------------------------------|
| `nums = [1, 4, 4]`, `target = 4` | 1 | Single element meets the target. |
| `nums = [1, 1, 1, 1]`, `target = 100` | 0 | No subarray meets the target. |
| `nums = [1, 2, 3, 4, 5]`, `target = 11` | 3 | Subarray `[3, 4, 5]` sums to 12. |
| `nums = [10, 2, 3]`, `target = 6` | 1 | Single element `10` exceeds target. |
| `nums = []`, `target = 1` | 0 | Empty array. |

---

# 📚 Key Takeaways

1. **Sliding Window Pattern**: Ideal for problems involving contiguous subarrays with sum/length constraints.
2. **Efficiency**: The optimal approach reduces time complexity from O(n²) to O(n).
3. **Positive Elements**: The sliding window works seamlessly when all elements are positive, ensuring the sum is monotonic.
4. **Dynamic Window**: Contracting the window from the left ensures we explore all possible minimal subarrays.

---

# 🚀 Interview Tips

- **Clarify Constraints**: Confirm that all elements are positive, as this affects the sliding window approach.
- **Follow-Up**: What if elements can be negative? (Answer: Sliding window may not work; consider prefix sums or other approaches.)
- **Optimization Insight**: Highlight the importance of the `while` loop for contracting the window.
- **Common Pitfall**: Forgetting to handle the case where no subarray meets the target (return `0`).

---

# ✅ Conclusion

The **sliding window** approach is the optimal solution for this problem, offering **O(n) time complexity** and **O(1) space complexity**. It efficiently narrows down the smallest subarray by dynamically adjusting the window boundaries, making it ideal for large input sizes. The brute force approach, while intuitive, is impractical for real-world constraints due to its quadratic time complexity. Mastering the sliding window pattern is crucial for solving similar problems in interviews and competitive programming.