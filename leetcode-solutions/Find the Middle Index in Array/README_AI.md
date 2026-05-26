# 📌 Find the Middle Index in Array

---

# 📝 Problem Statement

Given a **0-indexed** integer array `nums`, find the **leftmost middle index** (i.e., the smallest index amongst all possible middle indices).

A **middle index** is defined as an index where the sum of all elements to the **left** of the index is equal to the sum of all elements to the **right** of the index.

If no such index exists, return `-1`.

### Constraints:
- `1 <= nums.length <= 10⁴`
- `-1000 <= nums[i] <= 1000`

### Examples:
**Example 1:**
```
Input: nums = [2,3,-1,8,4]
Output: 3
Explanation: The sum of the numbers to the left of index 3 (nums[3] = 8) is 2 + 3 + (-1) = 4, and the sum of the numbers to the right of index 3 is 4. Both sums are equal.
```

**Example 2:**
```
Input: nums = [1,-1,4]
Output: 2
Explanation: The sum of the numbers to the left of index 2 (nums[2] = 4) is 1 + (-1) = 0, and the sum of the numbers to the right of index 2 is 0.
```

**Example 3:**
```
Input: nums = [2,5]
Output: -1
Explanation: There is no middle index.
```

---

# 💡 Intuition

The key insight is recognizing that the **total sum** of the array can be leveraged to avoid recalculating left and right sums repeatedly.

- Let `totalSum` be the sum of all elements in `nums`.
- For any index `i`, the **left sum** is the sum of elements from `0` to `i-1`.
- The **right sum** can be derived as `totalSum - leftSum - nums[i]`.
- If `leftSum == rightSum`, then `i` is a middle index.

This insight allows us to compute the solution in **O(n)** time with **O(1)** space by maintaining a running left sum.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Iterating through each index `i` in the array.
2. For each `i`, compute the **left sum** (sum of elements before `i`).
3. Compute the **right sum** (sum of elements after `i`).
4. If `leftSum == rightSum`, return `i`.
5. If no such index is found, return `-1`.

This approach recalculates sums for every index, leading to **O(n²)** time complexity.

---

## 🔹 Algorithm

1. Iterate through each index `i` from `0` to `n-1`.
2. Initialize `leftSum = 0`.
3. Compute `leftSum` by summing elements from `0` to `i-1`.
4. Compute `rightSum` by summing elements from `i+1` to `n-1`.
5. If `leftSum == rightSum`, return `i`.
6. If loop completes without finding a match, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int findMiddleIndex(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Calculate left sum
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            // Calculate right sum
            for (int j = i + 1; j < n; j++) {
                rightSum += nums[j];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [2, 3, -1, 8, 4]`

| Index (i) | Left Sum Calculation | Right Sum Calculation | Left Sum | Right Sum | Match? |
|-----------|----------------------|-----------------------|----------|-----------|--------|
| 0         | None (no left)       | 3 + (-1) + 8 + 4 = 14 | 0        | 14        | ❌     |
| 1         | 2                    | -1 + 8 + 4 = 11       | 2        | 11        | ❌     |
| 2         | 2 + 3 = 5            | 8 + 4 = 12            | 5        | 12        | ❌     |
| 3         | 2 + 3 + (-1) = 4     | 4                     | 4        | 4         | ✅     |
| 4         | 2 + 3 + (-1) + 8 = 12| None (no right)       | 12       | 0         | ❌     |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the **total sum** of the array to avoid recalculating sums repeatedly:
1. Compute the `totalSum` of the array.
2. Traverse the array while maintaining a running `leftSum`.
3. For each index `i`, compute `rightSum = totalSum - leftSum - nums[i]`.
4. If `leftSum == rightSum`, return `i`.
5. Update `leftSum += nums[i]` for the next iteration.
6. If no index satisfies the condition, return `-1`.

This reduces the time complexity to **O(n)** with **O(1)** space.

---

## 🔹 Why This Works

- The **total sum** is constant and computed once.
- The **right sum** can be derived from `totalSum - leftSum - nums[i]`.
- This avoids nested loops and recalculations, making the solution efficient.

---

## 🔹 Algorithm

1. Compute `totalSum` of the array.
2. Initialize `leftSum = 0`.
3. Iterate through each index `i`:
   - Compute `rightSum = totalSum - leftSum - nums[i]`.
   - If `leftSum == rightSum`, return `i`.
   - Update `leftSum += nums[i]`.
4. Return `-1` if no index is found.

---

## 🔹 Code

```java
class Solution {
    public int findMiddleIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [2, 3, -1, 8, 4]`

| Index (i) | nums[i] | leftSum (before) | rightSum = totalSum - leftSum - nums[i] | Match? | leftSum (after) |
|-----------|---------|------------------|-----------------------------------------|--------|-----------------|
| 0         | 2       | 0                | 17 - 0 - 2 = 15                         | ❌     | 0 + 2 = 2       |
| 1         | 3       | 2                | 17 - 2 - 3 = 12                         | ❌     | 2 + 3 = 5       |
| 2         | -1      | 5                | 17 - 5 - (-1) = 13                      | ❌     | 5 + (-1) = 4    |
| 3         | 8       | 4                | 17 - 4 - 8 = 5                          | ❌     | Wait! 4 == 5? ❌ |
|           |         |                  | Correction: 17 - 4 - 8 = **5**          | ❌     | 4 + 8 = 12      |
| **Correction:** | | | | | |
| 3         | 8       | 4                | 17 - 4 - 8 = **5**                      | ❌     | 4 + 8 = 12      |
| **Re-evaluate:** | | | | | |
| **Total Sum:** 2 + 3 + (-1) + 8 + 4 = **16** | | | | | |
| 0         | 2       | 0                | 16 - 0 - 2 = 14                         | ❌     | 2               |
| 1         | 3       | 2                | 16 - 2 - 3 = 11                         | ❌     | 5               |
| 2         | -1      | 5                | 16 - 5 - (-1) = 12                      | ❌     | 4               |
| 3         | 8       | 4                | 16 - 4 - 8 = **4**                      | ✅     | Return 3        |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

| Case                     | Expected Output | Explanation                          |
|--------------------------|-----------------|--------------------------------------|
| `nums = []`              | `-1`            | Empty array has no middle index.     |
| `nums = [1]`             | `0`             | Single element is trivially middle.  |
| `nums = [1, -1, 0]`      | `0`             | Left sum = 0, right sum = 0.         |
| `nums = [1, 2, 3, 4, 5]` | `-1`            | No middle index exists.              |
| `nums = [-1, -1, 0]`     | `2`             | Left sum = -2, right sum = 0? ❌ Wait! |
| **Correction:** | | |
| `nums = [-1, -1, 0]`     | `2`             | Left sum = -2, right sum = 0 → ❌ |
| **Actual:** | | |
| `nums = [-1, -1, 0]` → Total = -2 | | |
| i=0: left=0, right=-2-0-(-1)=-1 → ❌ | | |
| i=1: left=-1, right=-2-(-1)-(-1)=0 → ❌ | | |
| i=2: left=-2, right=-2-(-2)-0=0 → ❌ | | |
| **Output:** `-1` | | |

---

# 📚 Key Takeaways

- **Prefix Sum Insight:** The optimal solution leverages the total sum to avoid recalculating left/right sums.
- **Efficiency:** The optimal approach reduces time complexity from **O(n²)** to **O(n)**.
- **Space Optimization:** Uses **O(1)** space by maintaining a running left sum.
- **Edge Handling:** Always check for empty arrays and single-element cases.

---

# 🚀 Interview Tips

- **Follow-up:** What if the array contains very large numbers? (Hint: Use `long` to avoid overflow.)
- **Common Pitfall:** Forgetting to subtract `nums[i]` when computing `rightSum`.
- **Alternative Approach:** Could you solve this using prefix sums stored in an array? (Yes, but it uses **O(n)** space.)
- **Optimization Discussion:** Why is the optimal solution better? (Avoids nested loops and recalculations.)

---

# ✅ Conclusion

The **optimal solution** efficiently finds the middle index by leveraging the **total sum** of the array, reducing time complexity to **O(n)** with **O(1)** space. This approach is both **interview-ready** and **production-quality**, making it ideal for FAANG-level coding interviews.

The key insight is recognizing that the **right sum** can be derived from the **total sum** and **left sum**, eliminating the need for nested loops. This pattern is widely applicable in problems involving subarray sums.