# 📌 Subarray Sum Equals K

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return the **total number of subarrays** whose sum equals `k`.

A subarray is a contiguous non-empty sequence of elements within the array.

### Constraints:
- `1 <= nums.length <= 2 * 10^4`
- `-1000 <= nums[i] <= 1000`
- `-10^7 <= k <= 10^7`

### Examples:
**Example 1:**
```
Input: nums = [1,1,1], k = 2
Output: 2
Explanation: The subarrays [1,1] and [1,1] (both at indices 0-1 and 1-2) sum to 2.
```

**Example 2:**
```
Input: nums = [1,2,3], k = 3
Output: 2
Explanation: The subarrays [1,2] and [3] sum to 3.
```

---

# 💡 Intuition

The key insight is recognizing that the sum of a subarray from index `i` to `j` can be expressed as the difference between two prefix sums: `prefix[j] - prefix[i-1]`. If this difference equals `k`, then the subarray from `i` to `j` sums to `k`.

Instead of checking all possible subarrays (which is O(n²)), we can use a hash map to store the frequency of prefix sums encountered so far. This allows us to compute the number of valid subarrays in O(n) time by leveraging the relationship:
`prefix_sum - k = previous_prefix_sum`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and counting those whose sum equals `k`. For each starting index `i`, we iterate through all ending indices `j >= i`, compute the subarray sum, and increment the count if the sum equals `k`.

This approach is straightforward but inefficient due to its O(n²) time complexity.

---

## 🔹 Algorithm

1. Initialize a counter `count` to 0.
2. Iterate over each starting index `i` from `0` to `n-1`.
3. For each `i`, initialize a running sum `current_sum` to 0.
4. Iterate over each ending index `j` from `i` to `n-1`.
5. Add `nums[j]` to `current_sum`.
6. If `current_sum == k`, increment `count`.
7. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 2, 3]`, `k = 3`

| Step | i | j | currentSum | Action                     | count |
|------|---|---|------------|----------------------------|-------|
| 1    | 0 | 0 | 1          | currentSum != 3            | 0     |
| 2    | 0 | 1 | 1 + 2 = 3  | currentSum == 3 → count++  | 1     |
| 3    | 0 | 2 | 3 + 3 = 6  | currentSum != 3            | 1     |
| 4    | 1 | 1 | 2          | currentSum != 3            | 1     |
| 5    | 1 | 2 | 2 + 3 = 5  | currentSum != 3            | 1     |
| 6    | 2 | 2 | 3          | currentSum == 3 → count++  | 2     |

**Final Output:** `2`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n²)     |
| Space Complexity | O(1)      |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a hash map to store the frequency of prefix sums encountered during the traversal. The idea is to compute the prefix sum at each index and check if `prefix_sum - k` exists in the map. If it does, it means there are subarrays ending at the current index that sum to `k`.

This reduces the time complexity to O(n) and space complexity to O(n) due to the hash map.

---

## 🔹 Why This Works

- **Prefix Sum Insight:** The sum of a subarray from index `i` to `j` is `prefix[j] - prefix[i-1]`.
- **Hash Map Lookup:** If `prefix_sum - k` exists in the map, it means there are `map.get(prefix_sum - k)` subarrays ending at the current index that sum to `k`.
- **Efficiency:** The hash map allows O(1) average-time lookups, making the overall algorithm linear.

---

## 🔹 Algorithm

1. Initialize a hash map `prefixSumCount` with `{0: 1}` to account for subarrays starting from index 0.
2. Initialize `prefixSum = 0` and `count = 0`.
3. Iterate through each number in `nums`:
   - Add the number to `prefixSum`.
   - If `prefixSum - k` exists in `prefixSumCount`, increment `count` by `prefixSumCount.get(prefixSum - k)`.
   - Update `prefixSumCount` with the current `prefixSum`.
4. Return `count`.

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Base case: prefix sum 0 occurs once
        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 2, 3]`, `k = 3`

| Step | num | prefixSum | prefixSum - k | prefixSumCount (Map) | count |
|------|-----|-----------|---------------|----------------------|-------|
| 1    | 1   | 1         | 1 - 3 = -2    | {0:1}                | 0     |
|      |     |           |               | {0:1, 1:1}           |       |
| 2    | 2   | 1 + 2 = 3 | 3 - 3 = 0     | {0:1, 1:1}           | 1     |
|      |     |           |               | {0:1, 1:1, 3:1}      |       |
| 3    | 3   | 3 + 3 = 6 | 6 - 3 = 3     | {0:1, 1:1, 3:1}      | 2     |
|      |     |           |               | {0:1, 1:1, 3:1, 6:1} |       |

**Final Output:** `2`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n)      |
| Space Complexity | O(n)      |

---

# 🔍 Edge Cases

| Edge Case                     | Explanation                                                                 |
|-------------------------------|-----------------------------------------------------------------------------|
| Empty array                   | Return 0 (no subarrays).                                                    |
| Single element equal to `k`   | Return 1 (the subarray is the element itself).                              |
| All elements are zero         | Return `n*(n+1)/2` (all subarrays sum to 0).                                |
| Negative numbers              | The optimal approach handles negative numbers correctly.                   |
| `k = 0`                       | Count subarrays that sum to zero (e.g., `[1, -1, 1]` has 3 such subarrays).|
| Large input size              | The optimal approach handles `n = 2 * 10^4` efficiently.                   |

---

# 📚 Key Takeaways

1. **Prefix Sum Pattern:** Recognize that subarray sums can be expressed as differences of prefix sums.
2. **Hash Map Optimization:** Use a hash map to store prefix sum frequencies for O(1) lookups.
3. **Efficiency:** The optimal approach reduces time complexity from O(n²) to O(n).
4. **Edge Cases:** Always consider edge cases like empty arrays, single elements, and negative numbers.
5. **Initialization:** Initialize the hash map with `{0: 1}` to handle subarrays starting from index 0.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - Can you solve this problem if the array is circular?
   - How would you handle very large `k` values (e.g., `k = 10^18`)?
   - Can you optimize space further if the array contains only positive numbers?

2. **Common Pitfalls:**
   - Forgetting to initialize the hash map with `{0: 1}`.
   - Not handling negative numbers correctly (the brute force approach may miss some cases).
   - Overcomplicating the solution (the optimal approach is elegant and simple).

3. **Alternative Approaches:**
   - Sliding window (only works for non-negative numbers).
   - Brute force (for small inputs or interviews where optimization isn't required).

4. **Optimization Discussion:**
   - The optimal approach is a classic example of trading space for time.
   - The hash map allows us to avoid recomputing prefix sums repeatedly.

---

# ✅ Conclusion

The **optimal approach** using prefix sums and a hash map is the most efficient solution for this problem, achieving O(n) time and space complexity. It elegantly handles all edge cases, including negative numbers and large input sizes, making it the preferred choice for interviews and production code.

**Key Insight:** The relationship `prefix_sum - k = previous_prefix_sum` is the cornerstone of the solution, enabling us to count valid subarrays in linear time. This pattern is widely applicable in problems involving subarray sums or differences.