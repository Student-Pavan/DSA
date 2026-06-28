# 📌 189. Rotate Array

---

# 📝 Problem Statement

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

**Objective**: Perform the rotation in-place with O(1) extra space.

**Constraints**:
- 1 <= nums.length <= 10^5
- -2^31 <= nums[i] <= 2^31 - 1
- 0 <= k <= 10^5

---

# 💡 Intuition

The key insight is that rotating an array by `k` steps can be achieved by reversing portions of the array. This approach leverages the fact that a sequence of reversals can achieve the same effect as a rotation, but with better time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves rotating the array one step at a time, `k` times. For each rotation, we move the last element to the front.

---

## 🔹 Algorithm

1. For each step from 1 to k:
   a. Store the last element of the array
   b. Shift all elements one position to the right
   c. Place the stored element at the first position

---

## 🔹 Code

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k > n

        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with `nums = [1, 2, 3, 4, 5]` and `k = 2`:

| Step | Action | Array State |
|------|--------|-------------|
| 1    | Store last element (5) | [1, 2, 3, 4, 5] |
| 2    | Shift elements right | [1, 1, 2, 3, 4] |
| 3    | Place stored element at front | [5, 1, 2, 3, 4] |
| 4    | Store last element (4) | [5, 1, 2, 3, 4] |
| 5    | Shift elements right | [5, 5, 1, 2, 3] |
| 6    | Place stored element at front | [4, 5, 1, 2, 3] |

After 2 rotations, the final array is `[4, 5, 1, 2, 3]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n*k) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using a series of reversals to achieve the rotation in O(n) time with O(1) space complexity. The steps are:

1. Reverse the entire array
2. Reverse the first `k` elements
3. Reverse the remaining `n-k` elements

---

## 🔹 Why This Works

This approach works because:
- Reversing the entire array brings the last `k` elements to the front, but in reverse order
- Reversing the first `k` elements corrects their order
- Reversing the remaining `n-k` elements corrects their order
- The overall effect is a right rotation by `k` steps

---

## 🔹 Algorithm

1. Reverse the entire array
2. Reverse the first `k` elements
3. Reverse the remaining `n-k` elements

---

## 🔹 Code

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `nums = [1, 2, 3, 4, 5]` and `k = 2`:

| Step | Action | Array State |
|------|--------|-------------|
| 1    | Reverse entire array | [5, 4, 3, 2, 1] |
| 2    | Reverse first 2 elements | [4, 5, 3, 2, 1] |
| 3    | Reverse remaining 3 elements | [4, 5, 1, 2, 3] |

After these steps, the array is rotated right by 2 steps.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty array
- Single element array
- k equals array length
- k larger than array length
- Negative k (handled by modulo operation)

---

# 📚 Key Takeaways

1. Array rotation can be achieved through a series of reversals
2. The optimal approach reduces time complexity from O(n*k) to O(n)
3. The modulo operation handles cases where k > n
4. In-place operations minimize space complexity

---

# 🚀 Interview Tips

1. **Follow-up questions**:
   - Can you solve it without using extra space?
   - How would you handle very large k values?

2. **Common pitfalls**:
   - Not handling the case where k > n
   - Incorrectly implementing the reversal logic

3. **Alternative approaches**:
   - Using extra space to store rotated elements
   - Using a queue to perform rotations

---

# ✅ Conclusion

The optimal solution using reversals is preferred because:
- It achieves O(n) time complexity
- It uses O(1) space complexity
- It demonstrates advanced array manipulation techniques

The key insight is recognizing that a sequence of reversals can achieve the same effect as a rotation, but with better performance characteristics.