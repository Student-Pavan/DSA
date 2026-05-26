```markdown
# 📌 2552. Maximum Sum of Distinct Subarrays With Length K

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, find the **maximum sum** of any **contiguous subarray** of length `k` where **all elements are distinct**.

### **Objective**
Return the maximum sum of such a subarray. If no such subarray exists, return `0`.

### **Input**
- `nums`: An array of integers (1 ≤ `nums.length` ≤ 10<sup>5</sup>)
- `k`: An integer (1 ≤ `k` ≤ `nums.length`)

### **Output**
- An integer representing the maximum sum of a distinct subarray of length `k`.

### **Constraints**
- `-10<sup>4</sup> ≤ nums[i] ≤ 10<sup>4</sup>`
- The solution must efficiently handle large input sizes.

---

# 💡 Intuition

The problem requires finding a sliding window of fixed size `k` where all elements are unique. The key insight is that we can use a **sliding window** approach combined with a **frequency map** to track element uniqueness efficiently.

- **Brute Force**: Check every possible subarray of length `k`, verify uniqueness, and compute the sum.
- **Optimal**: Use a sliding window with a hash map to maintain element frequencies, allowing us to update the window in constant time when sliding.

The optimization comes from avoiding recomputation by reusing the previous window's sum and adjusting for the new element.

---

# 🐌 Brute Force Approach

## 🔹 Approach
1. Iterate over all possible subarrays of length `k`.
2. For each subarray, check if all elements are distinct.
3. If they are, compute the sum and keep track of the maximum sum encountered.

This approach is straightforward but inefficient due to repeated computations.

---

## 🔹 Algorithm
1. Initialize `maxSum = 0`.
2. For each starting index `i` from `0` to `n - k`:
   - Extract the subarray `nums[i..i+k-1]`.
   - Check if all elements in the subarray are distinct.
   - If distinct, compute the sum and update `maxSum` if this sum is larger.
3. Return `maxSum`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {
            Set<Integer> seen = new HashSet<>();
            long currentSum = 0;
            boolean isDistinct = true;

            for (int j = i; j < i + k; j++) {
                if (seen.contains(nums[j])) {
                    isDistinct = false;
                    break;
                }
                seen.add(nums[j]);
                currentSum += nums[j];
            }

            if (isDistinct && currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 5, 4, 2, 9, 9, 9]`, `k = 3`

| Iteration | Subarray | Elements | Distinct? | Sum | maxSum |
|-----------|----------|----------|-----------|-----|--------|
| 0         | [1,5,4]  | 1,5,4    | Yes       | 10  | 10     |
| 1         | [5,4,2]  | 5,4,2    | Yes       | 11  | 11     |
| 2         | [4,2,9]  | 4,2,9    | Yes       | 15  | 15     |
| 3         | [2,9,9]  | 2,9,9    | No        | -   | 15     |
| 4         | [9,9,9]  | 9,9,9    | No        | -   | 15     |

**Final Answer:** `15`

---

## 🔹 Complexity Analysis

| Complexity      | Value               |
|-----------------|---------------------|
| Time Complexity | O(n * k)            |
| Space Complexity| O(k)                |

---

# ⚡ Optimal Approach

## 🔹 Approach
Use a **sliding window** with a **hash map** to track element frequencies. Maintain a running sum of the current window and adjust it as the window slides. If duplicates are found, shrink the window from the left until all elements are distinct again.

---

## 🔹 Why This Works
- **Efficiency**: The sliding window allows us to process each element exactly twice (once when added, once when removed), resulting in O(n) time.
- **Correctness**: The frequency map ensures we only consider windows with distinct elements, and the running sum avoids recomputation.

---

## 🔹 Algorithm
1. Initialize `maxSum = 0`, `currentSum = 0`, and a frequency map `freq`.
2. Use two pointers, `left` and `right`, to represent the window.
3. Expand the window by moving `right`:
   - Add `nums[right]` to `currentSum` and update its frequency.
   - If the window size exceeds `k`, move `left` to shrink the window:
     - Subtract `nums[left]` from `currentSum` and decrement its frequency.
     - If frequency drops to `0`, remove the element from the map.
4. If the window size is `k` and all elements are distinct, update `maxSum`.
5. Return `maxSum`.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long currentSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int n = nums.length;

        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            while (freq.size() < right - left + 1 || right - left + 1 > k) {
                currentSum -= nums[left];
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }

            if (right - left + 1 == k && freq.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 5, 4, 2, 9, 9, 9]`, `k = 3`

| right | nums[right] | currentSum | freq (Map)          | left | Window Size | Action                     | maxSum |
|-------|-------------|------------|---------------------|------|-------------|----------------------------|--------|
| 0     | 1           | 1          | {1:1}               | 0    | 1           | Expand                     | 0      |
| 1     | 5           | 6          | {1:1, 5:1}          | 0    | 2           | Expand                     | 0      |
| 2     | 4           | 10         | {1:1, 5:1, 4:1}     | 0    | 3           | Valid window, update maxSum| 10     |
| 3     | 2           | 11         | {5:1, 4:1, 2:1}     | 1    | 3           | Shrink left (remove 1)     | 11     |
| 4     | 9           | 15         | {4:1, 2:1, 9:1}     | 2    | 3           | Valid window, update maxSum| 15     |
| 5     | 9           | 20         | {2:1, 9:2}          | 3    | 3           | Duplicate, shrink left     | 15     |
| 6     | 9           | 18         | {9:2}               | 5    | 2           | Shrink left (remove 2,9)   | 15     |

**Final Answer:** `15`

---

## 🔹 Complexity Analysis

| Complexity      | Value               |
|-----------------|---------------------|
| Time Complexity | O(n)                |
| Space Complexity| O(k)                |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                  |
|-------------------------------|-----------------|----------------------------------------------|
| `nums = [1, 2, 3, 4]`, `k = 2`| 7               | All subarrays are distinct.                  |
| `nums = [1, 1, 1, 1]`, `k = 2`| 0               | No distinct subarray of length `k`.          |
| `nums = [5, -3, 5, 2]`, `k = 3`| 4              | Negative numbers and duplicates.             |
| `nums = [1]`, `k = 1`         | 1               | Single element.                              |
| `nums = []`, `k = 1`          | 0               | Empty array.                                 |

---

# 📚 Key Takeaways

1. **Sliding Window**: Ideal for fixed-size subarray problems.
2. **Frequency Map**: Efficiently tracks element uniqueness.
3. **Running Sum**: Avoids recomputation, improving efficiency.
4. **Edge Handling**: Always consider empty inputs, duplicates, and negative values.

---

# 🚀 Interview Tips

- **Clarify Constraints**: Ask about input size and value ranges.
- **Optimization Insight**: Discuss why O(n) is better than O(n*k).
- **Follow-Up**: What if `k` is larger than the array size? (Return 0)
- **Alternative**: Could sorting help? (No, order matters for subarrays.)
- **Pitfall**: Forgetting to handle negative numbers in sums.

---

# ✅ Conclusion

The **optimal sliding window approach** efficiently solves the problem in **O(n) time** and **O(k) space**, making it suitable for large inputs. The key insight is maintaining a running sum and a frequency map to ensure element uniqueness while sliding the window. This approach is both **interview-friendly** and **production-ready**, demonstrating strong algorithmic thinking and optimization skills.
```