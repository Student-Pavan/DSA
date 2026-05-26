# 📌 Continuous Subarray Sum

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return `true` if `nums` has a **continuous subarray** of size **at least two** whose elements sum to a multiple of `k`, or `false` otherwise.

A **continuous subarray** is a subarray that appears consecutively in the array. A **multiple of `k`** means the sum is divisible by `k` (i.e., `sum % k == 0`).

### **Constraints:**
- `1 <= nums.length <= 10^5`
- `0 <= nums[i] <= 10^9`
- `0 <= k <= 10^9`

### **Examples:**
**Example 1:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] sums to 6, which is a multiple of 6.
```

**Example 2:**
```
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23,2,6,4,7] sums to 42, which is a multiple of 6.
```

**Example 3:**
```
Input: nums = [23,2,6,4,7], k = 13
Output: false
```

---

# 💡 Intuition

The key insight is recognizing that if the **cumulative sum up to index `i`** and the **cumulative sum up to index `j`** have the **same remainder when divided by `k`**, then the subarray from `i+1` to `j` must sum to a multiple of `k`.

This leverages the mathematical property:
- If `(sum_j - sum_i) % k == 0`, then `sum_j % k == sum_i % k`.

We can use a **hash map** to store the first occurrence of each remainder, allowing us to check for valid subarrays in **O(1)** time per element.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach checks all possible subarrays of size **at least two** and verifies if their sum is a multiple of `k`. This involves nested loops:
- Outer loop: Start index of the subarray.
- Inner loop: End index of the subarray (must be at least `start + 1`).

## 🔹 Algorithm
1. Iterate over all possible starting indices `i` from `0` to `n-2`.
2. For each `i`, iterate over all ending indices `j` from `i+1` to `n-1`.
3. Compute the sum of `nums[i..j]`.
4. If the sum is a multiple of `k`, return `true`.
5. If no such subarray is found, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) return true;
                } else if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [23, 2, 4, 6, 7]`, `k = 6`

| Step | i | j | Subarray | Sum | Sum % 6 | Result |
|------|---|---|----------|-----|---------|--------|
| 1    | 0 | 1 | [23, 2]  | 25  | 1       | false  |
| 2    | 0 | 2 | [23, 2, 4] | 29 | 5       | false  |
| 3    | 0 | 3 | [23, 2, 4, 6] | 35 | 5       | false  |
| 4    | 0 | 4 | [23, 2, 4, 6, 7] | 42 | 0       | **true** |
| ...  | ... | ... | ... | ... | ... | ... |

**Output:** `true` (subarray `[23, 2, 4, 6, 7]` sums to `42`, which is divisible by `6`).

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach
We use **prefix sums** and **modulo arithmetic** to optimize the solution. The idea is:
- Track cumulative sums and their remainders when divided by `k`.
- If the same remainder appears again, the subarray between the two indices sums to a multiple of `k`.
- Use a hash map to store the **first occurrence** of each remainder.

**Special Case:** If `k = 0`, we check for subarrays that sum to `0`.

## 🔹 Why This Works
- If `sum_j % k == sum_i % k`, then `(sum_j - sum_i) % k == 0`.
- The subarray `nums[i+1..j]` sums to a multiple of `k`.
- We ensure the subarray has **at least two elements** by checking that the first occurrence of the remainder is at least **two indices before** the current index.

## 🔹 Algorithm
1. Initialize a hash map `remainderMap` with `{0: -1}` (to handle subarrays starting from index `0`).
2. Initialize `prefixSum = 0`.
3. Iterate through `nums`:
   - Update `prefixSum += nums[i]`.
   - Compute `remainder = prefixSum % k`.
   - If `remainder` exists in `remainderMap`:
     - If the stored index is **at least two positions before** `i`, return `true`.
   - Else, store `remainder` with its index.
4. If no valid subarray is found, return `false`.

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1); // To handle subarrays starting from index 0
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = k == 0 ? prefixSum : prefixSum % k;

            if (remainderMap.containsKey(remainder)) {
                if (i - remainderMap.get(remainder) >= 2) {
                    return true;
                }
            } else {
                remainderMap.put(remainder, i);
            }
        }
        return false;
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `nums = [23, 2, 4, 6, 7]`, `k = 6`

| Iteration | nums[i] | prefixSum | remainder | remainderMap | Action |
|-----------|---------|-----------|-----------|--------------|--------|
| 0         | 23      | 23        | 5         | {0: -1, 5: 0} | Store remainder `5` at index `0` |
| 1         | 2       | 25        | 1         | {0: -1, 5: 0, 1: 1} | Store remainder `1` at index `1` |
| 2         | 4       | 29        | 5         | {0: -1, 5: 0, 1: 1} | Remainder `5` exists at index `0`. Check `i - 0 >= 2` → `2 >= 2` → **true** |

**Output:** `true` (subarray `[23, 2, 4]` sums to `29`, but `[2, 4]` sums to `6`, which is divisible by `6`).

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(min(n, k)) |

---

# 🔍 Edge Cases

| Edge Case | Explanation |
|-----------|-------------|
| `nums = [0, 0]`, `k = 0` | Subarray `[0, 0]` sums to `0` → **true** |
| `nums = [1, 0]`, `k = 2` | Subarray `[1, 0]` sums to `1` → **false** |
| `nums = [5, 0, 0]`, `k = 0` | Subarray `[0, 0]` sums to `0` → **true** |
| `nums = [23, 2, 4, 6, 7]`, `k = 13` | No subarray sums to a multiple of `13` → **false** |
| `nums = [1, 2, 3]`, `k = 5` | Subarray `[2, 3]` sums to `5` → **true** |

---

# 📚 Key Takeaways

- **Modulo Arithmetic** is crucial for optimizing subarray sum problems.
- **Prefix Sums + Hash Map** is a powerful pattern for subarray problems.
- **Edge Cases** like `k = 0` require special handling.
- The optimal solution reduces time complexity from **O(n²)** to **O(n)**.

---

# 🚀 Interview Tips

- **Follow-up:** How would you handle negative numbers in `nums`?
  - *Answer:* The optimal approach already handles negatives since `%` works correctly for negative numbers in Java.
- **Common Pitfall:** Forgetting to check subarray size ≥ 2.
- **Alternative Approach:** Sliding window is **not** applicable here because the problem involves **multiples**, not a fixed sum.
- **Optimization Insight:** The hash map stores the **first occurrence** of each remainder to maximize subarray length.

---

# ✅ Conclusion

The **optimal solution** leverages **prefix sums and modulo arithmetic** to achieve **O(n)** time complexity, making it suitable for large inputs. The **brute force approach** is intuitive but inefficient for large arrays. The key insight is recognizing that **repeating remainders** indicate valid subarrays, allowing us to use a hash map for constant-time lookups.

This problem is a great example of how **mathematical insights** can lead to **efficient algorithmic solutions**.