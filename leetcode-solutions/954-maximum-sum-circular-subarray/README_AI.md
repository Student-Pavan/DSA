# Maximum Sum Circular Subarray

---

# 📝 Problem Statement

Given a circular integer array `nums` of length `n`, return the maximum possible sum of a non-empty subarray of `nums`.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of `nums[i]` is `nums[(i + 1) % n]` and the previous element of `nums[i]` is `nums[(i - 1 + n) % n]`.

A subarray may only include each element of the fixed buffer `nums` at most once. Formally, for a subarray `nums[i], nums[i + 1], ..., nums[j]`, there does not exist `i <= k1, k2 <= j` with `k1 % n == k2 % n`.

**Constraints:**
- `n == nums.length`
- `1 <= n <= 3 * 10^4`
- `-3 * 10^4 <= nums[i] <= 3 * 10^4`

---

# 💡 Intuition

The key insight is that the maximum subarray sum in a circular array can be either:
1. The standard maximum subarray sum (non-circular case)
2. The total sum minus the minimum subarray sum (circular case)

This approach efficiently handles both scenarios in O(n) time with O(1) space complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach checks all possible subarrays, calculating their sums and keeping track of the maximum. This involves nested loops to consider every possible starting and ending index.

---

## 🔹 Algorithm

1. Initialize `maxSum` to negative infinity.
2. For each starting index `i` from 0 to n-1:
   - Initialize `currentSum` to 0.
   - For each ending index `j` from `i` to n-1:
     - Add `nums[j]` to `currentSum`.
     - Update `maxSum` if `currentSum` is greater.
3. Return `maxSum`.

---

## 🔹 Code

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
}
```

---

## 🔹 Dry Run

Let's dry run with `nums = [1, -2, 3, -2]`:

| i | j | currentSum | maxSum |
|---|---|------------|--------|
| 0 | 0 | 1          | 1      |
| 0 | 1 | -1         | 1      |
| 0 | 2 | 2          | 2      |
| 0 | 3 | 0          | 2      |
| 1 | 1 | -2         | 2      |
| 1 | 2 | 1          | 2      |
| 1 | 3 | -1         | 2      |
| 2 | 2 | 3          | 3      |
| 2 | 3 | 1          | 3      |
| 3 | 3 | -2         | 3      |

Final result: 3

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal solution uses Kadane's algorithm twice:
1. First to find the maximum subarray sum (non-circular case)
2. Second to find the minimum subarray sum (to calculate circular case sum)
3. Compare both results to get the maximum circular subarray sum

---

## 🔹 Why This Works

- The maximum circular subarray sum can be either:
  - The standard maximum subarray sum (non-circular)
  - The total sum minus the minimum subarray sum (circular)
- This approach efficiently handles both cases in a single pass through the array

---

## 🔹 Algorithm

1. Initialize `maxSum`, `minSum`, `totalSum` to 0.
2. Initialize `maxSubarraySum` to negative infinity and `minSubarraySum` to positive infinity.
3. For each number in `nums`:
   - Add to `totalSum`
   - Update `maxSum` and `maxSubarraySum` using Kadane's algorithm
   - Update `minSum` and `minSubarraySum` using modified Kadane's algorithm
4. If all numbers are negative, return `maxSubarraySum`
5. Otherwise, return the maximum between `maxSubarraySum` and `totalSum - minSubarraySum`

---

## 🔹 Code

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = 0;
        int maxSubarraySum = Integer.MIN_VALUE;
        int minSum = 0;
        int minSubarraySum = Integer.MAX_VALUE;
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;

            maxSum += num;
            maxSubarraySum = Math.max(maxSum, maxSubarraySum);
            if (maxSum < 0) {
                maxSum = 0;
            }

            minSum += num;
            minSubarraySum = Math.min(minSum, minSubarraySum);
            if (minSum > 0) {
                minSum = 0;
            }
        }

        if (maxSubarraySum < 0) {
            return maxSubarraySum;
        }

        return Math.max(maxSubarraySum, totalSum - minSubarraySum);
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run with `nums = [1, -2, 3, -2]`:

| Iteration | num | totalSum | maxSum | maxSubarraySum | minSum | minSubarraySum |
|-----------|-----|----------|--------|----------------|--------|----------------|
| 0         | 1   | 1        | 1      | 1              | 1      | 1              |
| 1         | -2  | -1       | -1     | 1              | -1     | -1             |
| 2         | 3   | 2        | 3      | 3              | 2      | -1             |
| 3         | -2  | 0        | 1      | 3              | 0      | -1             |

Final calculations:
- `maxSubarraySum` = 3
- `totalSum - minSubarraySum` = 0 - (-1) = 1
- Result = max(3, 1) = 3

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- All negative numbers: `[ -3, -2, -1 ]` → -1
- Single element: `[5]` → 5
- All positive numbers: `[1, 2, 3]` → 6
- Mixed positive and negative: `[5, -3, 5]` → 10
- Large array with circular maximum: `[3, -1, 2, -1]` → 4

---

# 📚 Key Takeaways

1. The circular maximum subarray sum can be found by considering both the standard maximum and the circular case
2. Kadane's algorithm is highly efficient for subarray sum problems
3. The optimal solution handles edge cases where all numbers are negative
4. The time complexity is linear, making it suitable for large input sizes

---

# 🚀 Interview Tips

1. Be prepared to explain why the optimal solution works
2. Practice explaining the two-pass approach with Kadane's algorithm
3. Consider asking about handling edge cases during the interview
4. Be ready to discuss alternative approaches like divide and conquer

---

# ✅ Conclusion

The optimal solution efficiently handles the circular maximum subarray problem by combining Kadane's algorithm with a clever observation about the relationship between maximum and minimum subarray sums. This approach provides an optimal O(n) time complexity solution with O(1) space complexity, making it ideal for large input sizes.