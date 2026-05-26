# 📌 Subarray Product Less Than K

---

# 📝 Problem Statement

Given an array of positive integers `nums` and an integer `k`, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than `k`.

### **Objective**
Count all contiguous subarrays whose product is less than `k`.

### **Input**
- `nums`: An array of positive integers (1 ≤ `nums.length` ≤ 3 × 10⁴)
- `k`: An integer (0 ≤ `k` ≤ 10⁶)

### **Output**
- An integer representing the count of valid subarrays.

### **Constraints**
- All elements in `nums` are **positive integers**.
- The product of any subarray will not overflow a 32-bit integer.

### **Examples**
**Example 1:**
```
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays with product < 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
```

**Example 2:**
```
Input: nums = [1, 2, 3], k = 0
Output: 0
Explanation: No subarray has product < 0 (since all elements are positive).
```

---

# 💡 Intuition

The problem requires counting all contiguous subarrays where the product of elements is less than `k`. A brute-force approach would check every possible subarray, but this leads to O(n²) time complexity, which is inefficient for large inputs.

The key insight is to recognize that this problem can be optimized using a **sliding window** technique. The sliding window helps maintain a window of elements whose product is less than `k`. As we expand the window to the right, we shrink it from the left if the product exceeds or equals `k`. This ensures we efficiently count all valid subarrays in O(n) time.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute-force approach involves checking every possible contiguous subarray and counting those whose product is less than `k`. This is done by using two nested loops:
1. The outer loop picks the starting index of the subarray.
2. The inner loop expands the subarray to the right, calculating the product at each step.

## 🔹 Algorithm
1. Initialize a counter `count` to 0.
2. Iterate over each starting index `i` from 0 to `n-1`.
3. For each `i`, initialize `product` to 1.
4. Iterate over each ending index `j` from `i` to `n-1`.
5. Multiply `product` by `nums[j]`.
6. If `product < k`, increment `count`.
7. If `product >= k`, break the inner loop (since further elements will only increase the product).
8. Return `count`.

## 🔹 Code

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = i; j < n; j++) {
                product *= nums[j];
                if (product < k) {
                    count++;
                } else {
                    break; // Further elements will only increase the product
                }
            }
        }

        return count;
    }
}
```

## 🔹 Dry Run

**Input:** `nums = [10, 5, 2, 6]`, `k = 100`

| Step | i | j | product | Action                     | count |
|------|---|---|---------|----------------------------|-------|
| 1    | 0 | 0 | 10      | 10 < 100 → count++         | 1     |
| 2    | 0 | 1 | 50      | 50 < 100 → count++         | 2     |
| 3    | 0 | 2 | 100     | 100 >= 100 → break         | 2     |
| 4    | 1 | 1 | 5       | 5 < 100 → count++          | 3     |
| 5    | 1 | 2 | 10      | 10 < 100 → count++         | 4     |
| 6    | 1 | 3 | 60      | 60 < 100 → count++         | 5     |
| 7    | 2 | 2 | 2       | 2 < 100 → count++          | 6     |
| 8    | 2 | 3 | 12      | 12 < 100 → count++         | 7     |
| 9    | 3 | 3 | 6       | 6 < 100 → count++          | 8     |

**Final Output:** `8`

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach uses a **sliding window** technique to maintain a window of elements whose product is less than `k`. The window expands by moving the right pointer and contracts by moving the left pointer when the product exceeds or equals `k`. This ensures we count all valid subarrays in linear time.

## 🔹 Why This Works
- The sliding window efficiently tracks the current product without recalculating it from scratch for every subarray.
- By moving the left pointer, we ensure that the window always contains the maximum valid subarray ending at the right pointer.
- The number of valid subarrays ending at `right` is `right - left + 1`.

## 🔹 Algorithm
1. Initialize `count = 0`, `product = 1`, and `left = 0`.
2. Iterate over `right` from `0` to `n-1`:
   - Multiply `product` by `nums[right]`.
   - While `product >= k` and `left <= right`, divide `product` by `nums[left]` and increment `left`.
   - If `product < k`, add `right - left + 1` to `count`.
3. Return `count`.

## 🔹 Code

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // Since all nums are positive, product >= 1

        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }
            if (product < k) {
                count += right - left + 1;
            }
        }

        return count;
    }
}
```

## 🔹 Detailed Dry Run

**Input:** `nums = [10, 5, 2, 6]`, `k = 100`

| Step | right | left | product | Action                     | count |
|------|-------|------|---------|----------------------------|-------|
| 1    | 0     | 0    | 10      | 10 < 100 → count += 1      | 1     |
| 2    | 1     | 0    | 50      | 50 < 100 → count += 2      | 3     |
| 3    | 2     | 0    | 100     | 100 >= 100 → left++        | 3     |
| 4    | 2     | 1    | 50      | 50 < 100 → count += 2      | 5     |
| 5    | 3     | 1    | 300     | 300 >= 100 → left++        | 5     |
| 6    | 3     | 2    | 60      | 60 < 100 → count += 2      | 7     |
| 7    | 3     | 3    | 6       | 6 < 100 → count += 1       | 8     |

**Final Output:** `8`

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `nums = []`, `k = 100`        | 0               | Empty array has no subarrays.                                               |
| `nums = [1]`, `k = 1`         | 0               | Product is 1, which is not **strictly** less than 1.                        |
| `nums = [1, 1, 1]`, `k = 2`   | 6               | All subarrays have product 1, which is < 2.                                 |
| `nums = [10, 5, 2, 6]`, `k=0` | 0               | All products are positive, so no subarray has product < 0.                  |
| `nums = [100, 200]`, `k=100`  | 0               | No subarray has product < 100.                                              |
| `nums = [10, 2, 2, 5]`, `k=100` | 7             | Valid subarrays: [10], [2], [2], [5], [10,2], [2,2], [2,5].                 |

---

# 📚 Key Takeaways

1. **Sliding Window is Optimal**: For problems involving contiguous subarrays with a condition on the product/sum, sliding window is often the best approach.
2. **Edge Cases Matter**: Always handle cases where `k <= 1` (since all elements are positive, no subarray will satisfy `product < k`).
3. **Efficiency**: The optimal solution reduces time complexity from O(n²) to O(n), making it suitable for large inputs.
4. **Counting Subarrays**: The formula `right - left + 1` efficiently counts all valid subarrays ending at `right`.

---

# 🚀 Interview Tips

1. **Follow-Up Questions**:
   - How would you handle negative numbers in the array?
   - Can you modify the solution to work with `k = 0` (assuming zeros are allowed in the array)?
   - How would you optimize further if `k` is very large?

2. **Common Pitfalls**:
   - Forgetting to handle `k <= 1` (leads to incorrect results).
   - Not breaking early in the brute-force approach when `product >= k`.
   - Off-by-one errors in counting subarrays (`right - left + 1`).

3. **Alternative Approaches**:
   - **Prefix Product + Binary Search**: Precompute prefix products and use binary search to find valid subarrays. This approach is O(n log n) and useful when the array contains zeros or negatives.

4. **Optimization Discussion**:
   - The sliding window approach is optimal for this problem because it processes each element exactly once, achieving O(n) time complexity.

---

# ✅ Conclusion

The **sliding window** approach is the most efficient solution for this problem, reducing the time complexity from O(n²) to O(n) while maintaining constant space complexity. The key insight is to dynamically adjust the window to ensure the product remains less than `k`, allowing us to count all valid subarrays in a single pass. This approach is not only optimal but also elegant and easy to understand, making it a strong candidate for technical interviews.