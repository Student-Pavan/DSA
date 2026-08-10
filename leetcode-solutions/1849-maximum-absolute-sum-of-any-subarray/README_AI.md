# Maximum Absolute Sum of Any Subarray

---

# 📝 Problem Statement

You are given an integer array `nums`. The absolute sum of a subarray is the absolute value of the sum of all the elements in that subarray.

A subarray is a contiguous sequence of elements within an array. For example, in the array `[1, -2, 3, -4]`, `[1, -2]` is a subarray while `[1, 3]` is not.

Return the maximum absolute sum of any subarray of the given array.

**Constraints:**
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`

---

# 💡 Intuition

The key insight here is that the maximum absolute sum of any subarray can be either the maximum subarray sum or the absolute value of the minimum subarray sum. This is because a subarray with a large negative sum can have a large absolute value when its sum is negated.

To find both the maximum and minimum subarray sums efficiently, we can use Kadane's algorithm, which runs in O(n) time with O(1) space complexity. The algorithm works by iterating through the array while maintaining the current maximum and minimum subarray sums. If the current sum becomes negative or positive respectively, we reset it to zero since a negative sum would only decrease the absolute value of the sum.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays of the given array, calculating their sums, and keeping track of the maximum absolute sum encountered. This approach has a time complexity of O(n²) and a space complexity of O(1).

---

## 🔹 Algorithm

1. Initialize `max_abs_sum` to 0.
2. Iterate through the array with the outer loop running from the start to the end of the array.
3. For each element in the outer loop, initialize `current_sum` to 0.
4. Iterate through the array with the inner loop running from the current element of the outer loop to the end of the array.
5. For each element in the inner loop, add the element to `current_sum`.
6. Update `max_abs_sum` with the maximum of its current value and the absolute value of `current_sum`.
7. After the inner loop completes, return `max_abs_sum`.

---

## 🔹 Code

```java
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int max_abs_sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int current_sum = 0;
            for (int j = i; j < nums.length; j++) {
                current_sum += nums[j];
                max_abs_sum = Math.max(max_abs_sum, Math.abs(current_sum));
            }
        }
        return max_abs_sum;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the input array `[1, -2, 3, -4]`.

| Outer Loop Index (i) | Inner Loop Index (j) | Current Sum | Max Absolute Sum |
|----------------------|----------------------|-------------|-------------------|
| 0                    | 0                    | 1           | 1                 |
| 0                    | 1                    | -1          | 1                 |
| 0                    | 2                    | 2           | 2                 |
| 0                    | 3                    | -2          | 2                 |
| 1                    | 1                    | -2          | 2                 |
| 1                    | 2                    | 1           | 2                 |
| 1                    | 3                    | -3          | 3                 |
| 2                    | 2                    | 3           | 3                 |
| 2                    | 3                    | -1          | 3                 |
| 3                    | 3                    | -4          | 4                 |

The maximum absolute sum of any subarray in this case is 4.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using Kadane's algorithm to find both the maximum and minimum subarray sums in a single pass through the array. The maximum absolute sum of any subarray is then the maximum of the maximum subarray sum and the absolute value of the minimum subarray sum.

---

## 🔹 Why This Works

Kadane's algorithm efficiently finds the maximum and minimum subarray sums by maintaining running sums and resetting them when they become negative or positive respectively. This approach ensures that we only traverse the array once, resulting in an O(n) time complexity with O(1) space complexity.

---

## 🔹 Algorithm

1. Initialize `max_subarray_sum` to the smallest possible integer value and `min_subarray_sum` to the largest possible integer value.
2. Initialize `current_max_sum` and `current_min_sum` to 0.
3. Iterate through the array:
   - Add the current element to `current_max_sum` and update `max_subarray_sum` with the maximum of its current value and `current_max_sum`.
   - If `current_max_sum` is negative, reset it to 0.
   - Add the current element to `current_min_sum` and update `min_subarray_sum` with the minimum of its current value and `current_min_sum`.
   - If `current_min_sum` is positive, reset it to 0.
4. Return the maximum of `max_subarray_sum` and the absolute value of `min_subarray_sum`.

---

## 🔹 Code

```java
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int max_subarray_sum = Integer.MIN_VALUE;
        int min_subarray_sum = Integer.MAX_VALUE;
        int current_max_sum = 0;
        int current_min_sum = 0;

        for (int num : nums) {
            current_max_sum += num;
            max_subarray_sum = Math.max(max_subarray_sum, current_max_sum);
            if (current_max_sum < 0) {
                current_max_sum = 0;
            }

            current_min_sum += num;
            min_subarray_sum = Math.min(min_subarray_sum, current_min_sum);
            if (current_min_sum > 0) {
                current_min_sum = 0;
            }
        }

        return Math.max(max_subarray_sum, Math.abs(min_subarray_sum));
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input array `[1, -2, 3, -4]`.

| Index | Current Element | Current Max Sum | Max Subarray Sum | Current Min Sum | Min Subarray Sum |
|-------|------------------|-----------------|-------------------|-----------------|-------------------|
| 0     | 1                | 1               | 1                 | 1               | 1                 |
| 1     | -2               | -1              | 1                 | -1              | -1                |
| 2     | 3                | 2               | 2                 | 2               | -1                |
| 3     | -4               | -2              | 2                 | -2              | -3                |

The maximum subarray sum is 2, and the minimum subarray sum is -3. The maximum absolute sum of any subarray is the maximum of 2 and 3, which is 3.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Element Array**: The maximum absolute sum is the absolute value of the single element.
- **All Positive Elements**: The maximum absolute sum is the sum of all elements.
- **All Negative Elements**: The maximum absolute sum is the absolute value of the sum of all elements.
- **Mixed Positive and Negative Elements**: The maximum absolute sum can be either the maximum subarray sum or the absolute value of the minimum subarray sum.

---

# 📚 Key Takeaways

- Kadane's algorithm is efficient for finding maximum and minimum subarray sums.
- The maximum absolute sum of any subarray can be derived from the maximum and minimum subarray sums.
- The optimal approach reduces the time complexity from O(n²) to O(n) by leveraging Kadane's algorithm.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle very large arrays or arrays with specific patterns.
- **Common Pitfalls**: Ensure that the algorithm correctly handles negative numbers and resets the running sums appropriately.
- **Alternative Approaches**: Consider using prefix sums or divide and conquer techniques, though they may not be as efficient.

---

# ✅ Conclusion

The optimal approach using Kadane's algorithm efficiently finds the maximum absolute sum of any subarray by leveraging the maximum and minimum subarray sums. This approach ensures optimal performance with a linear time complexity and constant space complexity, making it suitable for large input sizes.