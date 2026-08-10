# Subarray Sum Equals K

---

# 📝 Problem Statement

Given an array of integers `nums` and an integer `k`, return the total number of subarrays whose sum equals to `k`.

A subarray is a contiguous non-empty sequence of elements within an array.

**Example 1:**

```text
Input: nums = [1,1,1], k = 2
Output: 2
```

**Example 2:**

```text
Input: nums = [1,2,3], k = 3
Output: 2
```

**Constraints:**

- `1 <= nums.length <= 2 * 10^4`
- `-1000 <= nums[i] <= 1000`
- `-10^7 <= k <= 10^7`

---

# 💡 Intuition

The key insight here is recognizing that the sum of a subarray can be expressed as the difference between two prefix sums. By maintaining a running prefix sum and using a hash map to store the frequency of prefix sums, we can efficiently count the number of subarrays that sum to `k`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and calculating their sums. For each starting index, we iterate through all possible ending indices and compute the sum of the subarray from the starting index to the ending index. If the sum equals `k`, we increment our count.

---

## 🔹 Algorithm

1. Initialize a count variable to 0.
2. Use a nested loop where the outer loop runs from the start of the array to the end.
3. The inner loop runs from the current starting index of the outer loop to the end of the array.
4. For each subarray defined by the starting and ending indices, calculate the sum.
5. If the sum equals `k`, increment the count.
6. Return the count after all subarrays have been checked.

---

## 🔹 Code

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
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

Let's dry run the algorithm with `nums = [1, 1, 1]` and `k = 2`.

| Start | End | Sum | Count |
|-------|-----|-----|-------|
| 0     | 0   | 1   | 0     |
| 0     | 1   | 2   | 1     |
| 0     | 2   | 3   | 1     |
| 1     | 1   | 1   | 1     |
| 1     | 2   | 2   | 2     |
| 2     | 2   | 1   | 2     |

**Final Count:** 2

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using a hash map to store the frequency of prefix sums encountered during the traversal of the array. This allows us to efficiently count the number of subarrays that sum to `k` in O(n) time.

---

## 🔹 Why This Works

By maintaining a running prefix sum and using a hash map to store the frequency of prefix sums, we can determine if the difference between the current prefix sum and `k` has been seen before. If it has, it means there are subarrays ending at the current index that sum to `k`.

---

## 🔹 Algorithm

1. Initialize a hash map to store prefix sums and their frequencies.
2. Initialize a variable to keep track of the running prefix sum.
3. Initialize a count variable to 0.
4. Iterate through the array, updating the running prefix sum.
5. For each prefix sum, check if `prefixSum - k` exists in the hash map. If it does, increment the count by the frequency of `prefixSum - k`.
6. Update the hash map with the current prefix sum.
7. Return the count after traversing the entire array.

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            if (prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum - k);
            }
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `nums = [1, 1, 1]` and `k = 2`.

| Iteration | Num | Prefix Sum | Prefix Sum - k | Count | Hash Map |
|-----------|-----|-------------|-----------------|-------|----------|
| 0         | 1   | 1           | -1              | 0     | {0:1, 1:1} |
| 1         | 1   | 2           | 0               | 1     | {0:1, 1:1, 2:1} |
| 2         | 1   | 3           | 1               | 2     | {0:1, 1:1, 2:1, 3:1} |

**Final Count:** 2

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Array:** If the input array is empty, the function should return 0.
- **Single Element:** If the array has only one element, the function should return 1 if the element equals `k`, otherwise 0.
- **All Elements Equal:** If all elements in the array are equal, the function should return the correct count of subarrays that sum to `k`.
- **Negative Numbers:** The function should correctly handle arrays with negative numbers.
- **Large Array:** The function should efficiently handle large arrays within the given constraints.

---

# 📚 Key Takeaways

- **Prefix Sum Technique:** The prefix sum technique is a powerful tool for solving subarray sum problems efficiently.
- **Hash Map Usage:** Using a hash map to store prefix sums and their frequencies allows for O(n) time complexity.
- **Efficiency:** The optimal approach significantly reduces the time complexity compared to the brute force method.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss how the solution can be extended to handle 2D arrays or other variations.
- **Common Pitfalls:** Be aware of edge cases and ensure the solution handles them correctly.
- **Alternative Approaches:** Consider using sliding window techniques if the array contains only positive numbers.

---

# ✅ Conclusion

The optimal approach using the prefix sum technique and hash map is highly efficient and suitable for solving the subarray sum problem in O(n) time. This approach is preferred in interviews due to its optimal time complexity and clarity.