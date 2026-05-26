# 📌 Product of Array Except Self

---

# 📝 Problem Statement

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

**Constraints:**
- The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.
- You must write an algorithm that runs in **O(n)** time and **without using the division operation**.

**Example 1:**
```
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
```

**Example 2:**
```
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
```

---

# 💡 Intuition

The key insight is to recognize that the product of all elements except the current one can be broken down into the product of all elements to the left of the current element and the product of all elements to the right.

Instead of calculating these products separately for each element (which would be O(n²)), we can compute them in two passes:
1. **Left Pass:** Compute the product of all elements to the left of each element.
2. **Right Pass:** Compute the product of all elements to the right of each element and multiply it with the left product.

This approach allows us to achieve O(n) time complexity with O(1) extra space (excluding the output array).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves calculating the product of all elements except the current one by iterating through the array for each element and computing the product of all other elements.

**Key Steps:**
1. For each element in the array, initialize a product variable to 1.
2. Iterate through the entire array again, multiplying all elements except the current one.
3. Store the result in the output array.

This approach is straightforward but inefficient due to its O(n²) time complexity.

---

## 🔹 Algorithm

1. Initialize an empty result array of the same length as `nums`.
2. For each index `i` in `nums`:
   - Initialize `product = 1`.
   - For each index `j` in `nums`:
     - If `i != j`, multiply `product` by `nums[j]`.
   - Store `product` in `result[i]`.
3. Return the `result` array.

---

## 🔹 Code

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 2, 3, 4]`

| Step | i | j | nums[j] | product (before) | product (after) | result[i] |
|------|---|---|---------|------------------|-----------------|-----------|
| 1    | 0 | 0 | 1       | 1                | 1               | -         |
| 2    | 0 | 1 | 2       | 1                | 2               | -         |
| 3    | 0 | 2 | 3       | 2                | 6               | -         |
| 4    | 0 | 3 | 4       | 6                | 24              | 24        |
| 5    | 1 | 0 | 1       | 1                | 1               | -         |
| 6    | 1 | 1 | 2       | 1                | 1               | -         |
| 7    | 1 | 2 | 3       | 1                | 3               | -         |
| 8    | 1 | 3 | 4       | 3                | 12              | 12        |
| 9    | 2 | 0 | 1       | 1                | 1               | -         |
| 10   | 2 | 1 | 2       | 1                | 2               | -         |
| 11   | 2 | 2 | 3       | 2                | 2               | -         |
| 12   | 2 | 3 | 4       | 2                | 8               | 8         |
| 13   | 3 | 0 | 1       | 1                | 1               | -         |
| 14   | 3 | 1 | 2       | 1                | 2               | -         |
| 15   | 3 | 2 | 3       | 2                | 6               | -         |
| 16   | 3 | 3 | 4       | 6                | 6               | 6         |

**Output:** `[24, 12, 8, 6]`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n²)     |
| Space Complexity | O(1)*     |

*Excluding the output array.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages prefix and suffix products to compute the result in O(n) time with O(1) extra space (excluding the output array).

**Key Steps:**
1. **Left Pass:** Traverse the array from left to right, storing the product of all elements to the left of the current element in the result array.
2. **Right Pass:** Traverse the array from right to left, multiplying the result array with the product of all elements to the right of the current element.

This approach avoids the division operation and efficiently computes the result in two linear passes.

---

## 🔹 Why This Works

- The product of all elements except `nums[i]` is the product of all elements to the left of `i` multiplied by the product of all elements to the right of `i`.
- By computing these products in two separate passes, we avoid recalculating the product for each element repeatedly.
- The result array is reused to store intermediate products, ensuring O(1) extra space.

---

## 🔹 Algorithm

1. Initialize the result array with all 1s.
2. **Left Pass:**
   - Initialize `leftProduct = 1`.
   - For each index `i` from `0` to `n-1`:
     - Set `result[i] = leftProduct`.
     - Update `leftProduct *= nums[i]`.
3. **Right Pass:**
   - Initialize `rightProduct = 1`.
   - For each index `i` from `n-1` to `0`:
     - Multiply `result[i] *= rightProduct`.
     - Update `rightProduct *= nums[i]`.
4. Return the result array.

---

## 🔹 Code

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Left pass: compute product of all elements to the left of i
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] = leftProduct;
            leftProduct *= nums[i];
        }

        // Right pass: compute product of all elements to the right of i and multiply with left product
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [1, 2, 3, 4]`

### Left Pass

| i | nums[i] | leftProduct (before) | result[i] | leftProduct (after) |
|---|---------|----------------------|-----------|---------------------|
| 0 | 1       | 1                    | 1         | 1                   |
| 1 | 2       | 1                    | 1         | 2                   |
| 2 | 3       | 2                    | 2         | 6                   |
| 3 | 4       | 6                    | 6         | 24                  |

**Result after Left Pass:** `[1, 1, 2, 6]`

### Right Pass

| i | nums[i] | rightProduct (before) | result[i] (before) | result[i] (after) | rightProduct (after) |
|---|---------|-----------------------|--------------------|-------------------|----------------------|
| 3 | 4       | 1                     | 6                  | 6                 | 4                    |
| 2 | 3       | 4                     | 2                  | 8                 | 12                   |
| 1 | 2       | 12                    | 1                  | 12                | 24                   |
| 0 | 1       | 24                    | 1                  | 24                | 24                   |

**Final Result:** `[24, 12, 8, 6]`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n)      |
| Space Complexity | O(1)*     |

*Excluding the output array.

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output                     | Explanation                                                                 |
|-------------------------------|-------------------------------------|-----------------------------------------------------------------------------|
| `nums = []`                   | `[]`                                | Empty input returns empty output.                                          |
| `nums = [5]`                  | `[1]`                               | Single element returns `[1]` since there are no other elements to multiply. |
| `nums = [0, 1, 2, 3]`         | `[6, 0, 0, 0]`                      | Zero in the array affects the product for other elements.                   |
| `nums = [-1, -2, -3, -4]`     | `[-24, -12, -8, -6]`                | Negative values are handled correctly.                                     |
| `nums = [1, 0, 3, 0]`         | `[0, 0, 0, 0]`                      | Multiple zeros result in all products being zero.                          |

---

# 📚 Key Takeaways

1. **Prefix-Suffix Product Pattern:** The optimal solution leverages the prefix and suffix product pattern, a common technique in array problems.
2. **Space Optimization:** By reusing the result array for intermediate products, we achieve O(1) extra space.
3. **Two-Pass Technique:** The two-pass approach (left and right) is a powerful optimization for problems involving cumulative products or sums.
4. **Avoiding Division:** The problem explicitly forbids division, making the prefix-suffix approach essential.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Always confirm whether division is allowed and whether the input can contain zeros.
2. **Discuss Trade-offs:** Compare the brute force and optimal approaches in terms of time and space complexity.
3. **Follow-up Questions:**
   - How would you handle the problem if division were allowed?
   - Can you solve it in O(n) time with O(n) space?
   - How would you modify the solution if the input array is immutable?
4. **Common Pitfalls:**
   - Forgetting to handle zeros in the input.
   - Miscalculating the product for edge cases like single-element arrays.
   - Overcomplicating the solution with unnecessary data structures.

---

# ✅ Conclusion

The **Product of Array Except Self** problem is a classic example of leveraging prefix and suffix products to achieve an optimal solution. The brute force approach, while intuitive, is inefficient for large inputs. The optimal solution, using two passes to compute left and right products, achieves O(n) time complexity with O(1) extra space, making it both efficient and elegant.

**Key Insight:** The product of all elements except the current one can be decomposed into the product of all elements to the left and the product of all elements to the right, computed in linear time. This approach is a powerful pattern applicable to many array problems.