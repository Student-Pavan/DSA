# 📌 Two Sum II - Input Array Is Sorted

---

# 📝 Problem Statement

Given a **1-indexed** array of integers `numbers` that is **already sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number. Return the indices of the two numbers, `index1` and `index2`, **as an integer array** `[index1, index2]` of length 2.

### Key Constraints:
- The solution must use **only constant extra space** (O(1) space).
- You **may not use the same element twice**.
- The input array is **1-indexed** — the first element is at position 1, not 0.
- There is **exactly one solution** for each input.

### Example:
```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2.
```

---

# 💡 Intuition

The problem is a variation of the classic **Two Sum**, but with a crucial advantage: **the input array is sorted**. This allows us to use **two pointers** (one at the start, one at the end) to efficiently find the pair without extra space.

The key insight is:
- If the sum of two numbers is **less than the target**, we need a **larger sum**, so we move the **left pointer right** (to a larger value).
- If the sum is **greater than the target**, we need a **smaller sum**, so we move the **right pointer left** (to a smaller value).
- Since the array is sorted, this approach guarantees we either find the solution or exhaust the search space in linear time.

This avoids the O(n²) brute-force approach and the O(n) space of a hash map, achieving **O(n) time and O(1) space**.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The naive approach checks every possible pair in the array using nested loops. For each element at index `i`, we scan all subsequent elements to see if they sum to the target.

While simple, this approach has **O(n²) time complexity** and violates the constraint of using only constant space (though it technically does, it's inefficient).

---

## 🔹 Algorithm

1. Iterate over each element in the array using index `i` (from 0 to n-1).
2. For each `i`, iterate over each element using index `j` (from `i+1` to n-1).
3. If `numbers[i] + numbers[j] == target`, return `[i+1, j+1]` (converting to 1-based indexing).
4. If no pair is found (though problem guarantees one), return an empty array.

---

## 🔹 Code

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1}; // 1-based indexing
                }
            }
        }
        return new int[0]; // should not reach here per problem statement
    }
}
```

---

## 🔹 Dry Run

**Input:** `numbers = [2, 7, 11, 15]`, `target = 9`

| Step | i | j | numbers[i] | numbers[j] | Sum | Action |
|------|---|---|------------|------------|-----|--------|
| 1    | 0 | 1 | 2          | 7          | 9   | **Match found** → return `[1, 2]` |

Only one iteration is needed since the first pair sums to 9.

✅ **Output:** `[1, 2]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **two pointers** (`left` and `right`) starting at the beginning and end of the array.

- While `left < right`:
  - Compute `currentSum = numbers[left] + numbers[right]`
  - If `currentSum == target`, return `[left+1, right+1]`
  - If `currentSum < target`, move `left++` to increase sum
  - If `currentSum > target`, move `right--` to decrease sum

This leverages the **sorted property** of the array to eliminate half the search space in each step.

---

## 🔹 Why This Works

- The array is sorted, so moving `left` right increases the sum, and moving `right` left decreases it.
- We start with the smallest and largest values — if their sum is too small, no smaller pair can work, so we must increase the left value.
- Similarly, if the sum is too large, no larger pair can work, so we decrease the right value.
- This guarantees we find the solution in **O(n)** time with **O(1)** space.

---

## 🔹 Algorithm

1. Initialize `left = 0`, `right = numbers.length - 1`.
2. While `left < right`:
   - Calculate `sum = numbers[left] + numbers[right]`.
   - If `sum == target`, return `[left+1, right+1]`.
   - If `sum < target`, increment `left`.
   - If `sum > target`, decrement `right`.
3. Return `[-1, -1]` (should not reach here).

---

## 🔹 Code

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1}; // should not reach here
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `numbers = [2, 7, 11, 15]`, `target = 9`

| Step | Left | Right | numbers[left] | numbers[right] | Sum | Action |
|------|------|-------|---------------|----------------|-----|--------|
| 1    | 0    | 3     | 2             | 15             | 17  | > 9 → right-- |
| 2    | 0    | 2     | 2             | 11             | 13  | > 9 → right-- |
| 3    | 0    | 1     | 2             | 7              | 9   | == 9 → return `[1, 2]` |

✅ **Output:** `[1, 2]`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case | Input Example | Expected Output | Notes |
|---------|----------------|------------------|-------|
| Smallest array | `[3, 24]`, 27 | `[1, 2]` | Only two elements |
| Negative numbers | `[-3, -2, 0, 4]`, 2 | `[2, 4]` | Handles negatives |
| Large values | `[1000000000, 1000000000]`, 2000000000 | `[1, 2]` | Avoid overflow (use `long` if needed) |
| Duplicates | `[2, 2, 3, 4]`, 4 | `[1, 2]` | First valid pair returned |
| Target at ends | `[1, 2, 3, 4]`, 5 | `[1, 4]` or `[2, 3]` | Multiple valid pairs; return first |

---

# 📚 Key Takeaways

- **Sorted input enables two-pointer optimization** — a powerful pattern in DSA.
- **Two-pointer technique** reduces time from O(n²) → O(n) and space from O(n) → O(1).
- Always consider **input constraints** (e.g., sorted, unique solution) to guide optimization.
- **1-based indexing** is a common source of off-by-one errors — be careful.

---

# 🚀 Interview Tips

- **Mention the brute-force approach first**, then optimize — shows problem-solving depth.
- **Explain why two pointers work** — interviewers love reasoning about correctness.
- **Discuss edge cases** like duplicates, negatives, and large values.
- **Follow-up question**: What if the array is not sorted? → Use a hash map (O(n) time, O(n) space).
- **Time-space tradeoff**: Two-pointer is optimal here due to sorted input.

---

# ✅ Conclusion

The **optimal solution** using **two pointers** is the clear winner — it’s **efficient, elegant, and leverages the sorted property** of the input. It achieves **O(n) time and O(1) space**, making it ideal for large inputs.

**Key insight**: When the input is sorted, **two pointers can often replace nested loops or hash maps**, offering optimal performance with minimal space.

This problem is a **classic example** of how understanding input constraints leads to powerful optimizations — a skill every software engineer should master.