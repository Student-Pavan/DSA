# 📌 283. Move Zeroes

---

# 📝 Problem Statement

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

**Constraints:**
- `1 <= nums.length <= 10^4`
- `-2^31 <= nums[i] <= 2^31 - 1`

**Example:**
```java
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

---

# 💡 Intuition

The key insight is that we need to maintain the relative order of non-zero elements while moving all zeros to the end. The optimal approach involves using two pointers to efficiently rearrange the array in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Count the number of zeros in the array.
2. Create a new array without zeros.
3. Append zeros to the end of the new array.
4. Copy the new array back to the original array.

---

## 🔹 Algorithm

1. Initialize a counter `zeroCount` to 0.
2. Iterate through the array and count all zeros.
3. Create a new array `nonZeroArray` with size `nums.length - zeroCount`.
4. Iterate through the original array and copy non-zero elements to `nonZeroArray`.
5. Append zeros to the end of `nonZeroArray`.
6. Copy `nonZeroArray` back to `nums`.

---

## 🔹 Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
        }

        int[] nonZeroArray = new int[nums.length - zeroCount];
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nonZeroArray[index++] = num;
            }
        }

        index = 0;
        for (int i = 0; i < nonZeroArray.length; i++) {
            nums[index++] = nonZeroArray[i];
        }

        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input `[0,1,0,3,12]`.

| Step | Action | State |
|---|---|---|
| 1 | Count zeros | zeroCount = 2 |
| 2 | Create nonZeroArray of size 3 | nonZeroArray = [0, 0, 0] |
| 3 | Copy non-zero elements | nonZeroArray = [1, 3, 12] |
| 4 | Copy nonZeroArray back to nums | nums = [1, 3, 12, 0, 0] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use two pointers to efficiently rearrange the array in a single pass. The left pointer keeps track of the position where the next non-zero element should be placed, and the right pointer iterates through the array to find non-zero elements.

---

## 🔹 Why This Works

This approach ensures that we maintain the relative order of non-zero elements while moving all zeros to the end in a single pass. The left pointer acts as a marker for the next position to place a non-zero element, and the right pointer scans the array to find such elements.

---

## 🔹 Algorithm

1. Initialize two pointers, `left` and `right`, both starting at 0.
2. Iterate through the array with the `right` pointer.
3. If the element at `right` is non-zero, swap it with the element at `left` and increment `left`.
4. Increment `right` in each iteration.

---

## 🔹 Code

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                right++;
            }
            else {
                if (nums[left] == 0) {
                    int temp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = temp;
                }
                left++;
                right++;
            }
        }
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[0,1,0,3,12]`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 0 | nums[0] == 0 | nums = [0,1,0,3,12] |
| 2 | 0 | 1 | nums[1] != 0, swap | nums = [1,0,0,3,12] |
| 3 | 1 | 2 | nums[2] == 0 | nums = [1,0,0,3,12] |
| 4 | 1 | 3 | nums[3] != 0, swap | nums = [1,3,0,0,12] |
| 5 | 2 | 4 | nums[4] != 0, swap | nums = [1,3,12,0,0] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty array
- Array with all zeros
- Array with all non-zero elements
- Array with negative numbers
- Large array size (close to 10^4)

---

# 📚 Key Takeaways

- The optimal approach uses two pointers to efficiently rearrange the array in a single pass.
- Maintaining the relative order of non-zero elements is crucial.
- The left pointer acts as a marker for the next position to place a non-zero element.
- The right pointer scans the array to find non-zero elements.

---

# 🚀 Interview Tips

- Discuss the importance of maintaining the relative order of non-zero elements.
- Mention the trade-off between time and space complexity in the brute force approach.
- Highlight the efficiency of the optimal approach with O(n) time and O(1) space complexity.
- Be prepared to discuss follow-up questions, such as handling duplicates or other constraints.

---

# ✅ Conclusion

The optimal approach using two pointers is the most efficient solution for this problem. It ensures that we maintain the relative order of non-zero elements while moving all zeros to the end in a single pass, resulting in O(n) time complexity and O(1) space complexity. This approach is both time and space efficient, making it the preferred solution for this problem.