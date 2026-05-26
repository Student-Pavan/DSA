```markdown
# 📌 Subarrays with K Different Integers

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return the number of **good subarrays** of `nums`.

A **good subarray** is defined as a subarray that contains **exactly** `k` distinct integers.

### Constraints:
- `1 <= nums.length <= 2 * 10^4`
- `1 <= nums[i] <= nums.length`
- `1 <= k <= nums.length`

### Examples:
**Example 1:**
```
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays with exactly 2 distinct integers:
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
```

**Example 2:**
```
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays with exactly 3 distinct integers:
[1,2,1,3], [2,1,3], [1,3,4]
```

---

# 💡 Intuition

The key insight is recognizing that counting subarrays with **exactly** `k` distinct integers can be transformed into a problem of counting subarrays with **at most** `k` distinct integers and subtracting those with **at most** `k-1` distinct integers.

This transformation leverages the **sliding window** technique to efficiently compute the number of subarrays with a bounded number of distinct elements. The sliding window approach allows us to maintain a dynamic window of elements while tracking the count of distinct integers within it.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking **all possible subarrays** of `nums` and counting those that contain exactly `k` distinct integers.

1. Iterate over all possible starting indices of subarrays.
2. For each starting index, expand the subarray to include subsequent elements.
3. Track the number of distinct integers in the current subarray using a hash set.
4. If the number of distinct integers equals `k`, increment the count.

This approach is straightforward but inefficient due to its high time complexity.

---

## 🔹 Algorithm

1. Initialize `count = 0`.
2. For each starting index `i` from `0` to `n-1`:
   - Initialize a set `distinct` to track unique elements.
   - For each ending index `j` from `i` to `n-1`:
     - Add `nums[j]` to `distinct`.
     - If `distinct.size() == k`, increment `count`.
     - If `distinct.size() > k`, break (no need to expand further).
3. Return `count`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(nums[j]);
                if (distinct.size() == k) {
                    count++;
                } else if (distinct.size() > k) {
                    break;
                }
            }
        }

        return count;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 2, 1, 2, 3]`, `k = 2`

| Step | i | j | Current Subarray | Distinct Elements | Action                     | Count |
|------|---|---|------------------|-------------------|----------------------------|-------|
| 1    | 0 | 0 | [1]              | {1}               | distinct.size() < k        | 0     |
| 2    | 0 | 1 | [1, 2]           | {1, 2}            | distinct.size() == k       | 1     |
| 3    | 0 | 2 | [1, 2, 1]        | {1, 2}            | distinct.size() == k       | 2     |
| 4    | 0 | 3 | [1, 2, 1, 2]     | {1, 2}            | distinct.size() == k       | 3     |
| 5    | 0 | 4 | [1, 2, 1, 2, 3]  | {1, 2, 3}         | distinct.size() > k → break| 3     |
| 6    | 1 | 1 | [2]              | {2}               | distinct.size() < k        | 3     |
| 7    | 1 | 2 | [2, 1]           | {1, 2}            | distinct.size() == k       | 4     |
| 8    | 1 | 3 | [2, 1, 2]        | {1, 2}            | distinct.size() == k       | 5     |
| 9    | 1 | 4 | [2, 1, 2, 3]     | {1, 2, 3}         | distinct.size() > k → break| 5     |
| 10   | 2 | 2 | [1]              | {1}               | distinct.size() < k        | 5     |
| 11   | 2 | 3 | [1, 2]           | {1, 2}            | distinct.size() == k       | 6     |
| 12   | 2 | 4 | [1, 2, 3]        | {1, 2, 3}         | distinct.size() > k → break| 6     |
| 13   | 3 | 3 | [2]              | {2}               | distinct.size() < k        | 6     |
| 14   | 3 | 4 | [2, 3]           | {2, 3}            | distinct.size() == k       | 7     |
| 15   | 4 | 4 | [3]              | {3}               | distinct.size() < k        | 7     |

**Final Count:** `7`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(k)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the **sliding window** technique to count subarrays with **at most** `k` distinct integers and subtracts those with **at most** `k-1` distinct integers. This transforms the problem into two efficient passes over the array.

### Key Insight:
- `subarraysWithKDistinct(nums, k) = subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k-1)`

### Why This Works:
- The sliding window efficiently tracks the number of distinct elements in the current window.
- By adjusting the window size dynamically, we avoid recomputing distinct counts for every subarray.

---

## 🔹 Why This Works

The transformation works because:
- Subarrays with **exactly** `k` distinct integers are a subset of those with **at most** `k` distinct integers.
- By subtracting subarrays with **at most** `k-1` distinct integers, we isolate those with **exactly** `k` distinct integers.

This approach reduces the problem to two linear passes, each with O(n) time complexity.

---

## 🔹 Algorithm

1. Implement a helper function `subarraysWithAtMostKDistinct(nums, k)`:
   - Use a sliding window with `left` and `right` pointers.
   - Maintain a frequency map to track distinct elements in the current window.
   - Expand the window by moving `right` and adjust `left` to maintain at most `k` distinct elements.
   - For each valid window, count all subarrays ending at `right` as `(right - left + 1)`.
2. Compute `subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k-1)`.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

    private int subarraysWithAtMostKDistinct(int[] nums, int k) {
        int left = 0;
        int count = 0;
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            frequency.put(nums[right], frequency.getOrDefault(nums[right], 0) + 1);

            while (frequency.size() > k) {
                frequency.put(nums[left], frequency.get(nums[left]) - 1);
                if (frequency.get(nums[left]) == 0) {
                    frequency.remove(nums[left]);
                }
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 2, 1, 2, 3]`, `k = 2`

### Step 1: Compute `subarraysWithAtMostKDistinct(nums, 2)`

| right | nums[right] | frequency Map | left | frequency.size() | Action                     | Count (right - left + 1) | Total Count |
|-------|-------------|---------------|------|------------------|----------------------------|--------------------------|-------------|
| 0     | 1           | {1:1}         | 0    | 1                |                            | 1                        | 1           |
| 1     | 2           | {1:1, 2:1}    | 0    | 2                |                            | 2                        | 3           |
| 2     | 1           | {1:2, 2:1}    | 0    | 2                |                            | 3                        | 6           |
| 3     | 2           | {1:2, 2:2}    | 0    | 2                |                            | 4                        | 10          |
| 4     | 3           | {1:2, 2:2, 3:1}| 0    | 3    | Shrink window    | left=1, frequency={1:1,2:2,3:1} | 4 | 14          |

**Result:** `14`

### Step 2: Compute `subarraysWithAtMostKDistinct(nums, 1)`

| right | nums[right] | frequency Map | left | frequency.size() | Action                     | Count (right - left + 1) | Total Count |
|-------|-------------|---------------|------|------------------|----------------------------|--------------------------|-------------|
| 0     | 1           | {1:1}         | 0    | 1                |                            | 1                        | 1           |
| 1     | 2           | {1:1, 2:1}    | 0    | 2                | Shrink window              | left=1, frequency={2:1}  | 1 | 2           |
| 2     | 1           | {2:1, 1:1}    | 1    | 2                | Shrink window              | left=2, frequency={1:1}  | 1 | 3           |
| 3     | 2           | {1:1, 2:1}    | 2    | 2                | Shrink window              | left=3, frequency={2:1}  | 1 | 4           |
| 4     | 3           | {2:1, 3:1}    | 3    | 2                | Shrink window              | left=4, frequency={3:1}  | 1 | 5           |

**Result:** `5`

### Final Calculation:
`subarraysWithKDistinct(nums, 2) = 14 - 5 = 7`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(k)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `nums = []`, `k = 1`          | 0               | Empty array has no subarrays.                |
| `nums = [1]`, `k = 1`         | 1               | Single element array has one subarray.       |
| `nums = [1,1,1]`, `k = 1`     | 6               | All subarrays have exactly 1 distinct integer.|
| `nums = [1,2,3]`, `k = 3`     | 1               | Only the entire array has 3 distinct integers.|
| `nums = [1,2,1,2,1]`, `k = 0` | 0               | `k=0` is invalid per constraints.            |

---

# 📚 Key Takeaways

1. **Sliding Window Transformation**: Counting subarrays with **exactly** `k` distinct integers can be transformed into counting subarrays with **at most** `k` and `k-1` distinct integers.
2. **Efficiency**: The sliding window approach reduces time complexity from O(n²) to O(n).
3. **Frequency Tracking**: Using a hash map to track element frequencies is crucial for maintaining the window constraints.
4. **Subarray Counting**: The number of subarrays ending at `right` is `(right - left + 1)` when the window is valid.

---

# 🚀 Interview Tips

1. **Follow-up Questions**:
   - How would you handle `k = 0`?
   - Can you optimize space further?
   - What if the array contains negative numbers?

2. **Common Pitfalls**:
   - Forgetting to handle the case where `k = 1` separately.
   - Incorrectly counting subarrays when shrinking the window.
   - Not resetting the frequency map between passes.

3. **Alternative Approaches**:
   - Using a single pass with a more complex sliding window (less intuitive).
   - Prefix sums with hash maps (less efficient for this problem).

4. **Optimization Discussions**:
   - The optimal approach leverages mathematical transformation to avoid brute force.
   - Space can be optimized by using an array instead of a hash map if the range of `nums[i]` is known.

---

# ✅ Conclusion

The optimal solution efficiently counts subarrays with exactly `k` distinct integers by transforming the problem into two sliding window passes. This approach achieves linear time complexity and is both intuitive and interview-ready. The key insight—subtracting subarrays with at most `k-1` distinct integers from those with at most `k`—is a powerful pattern applicable to similar problems.

**Final Thought:** Always look for opportunities to transform a problem into a simpler or more efficient variant using mathematical insights.
```