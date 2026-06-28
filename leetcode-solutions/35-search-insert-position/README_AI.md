# Search Insert Position

---

# 📝 Problem Statement

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

**Constraints:**
- 1 <= nums.length <= 10⁴
- -10⁴ <= nums[i] <= 10⁴
- nums contains distinct values sorted in ascending order
- -10⁴ <= target <= 10⁴

---

# 💡 Intuition

The problem requires finding the insertion position of a target in a sorted array. The key insight is that since the array is sorted, we can use binary search to achieve O(log n) time complexity. The binary search approach efficiently narrows down the search space by comparing the middle element with the target value.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the array from the beginning to find the correct insertion position. If the target is found, return its index. If not, iterate until we find the first element greater than the target or reach the end of the array.

---

## 🔹 Algorithm

1. Initialize a variable `index` to 0.
2. Iterate through the array:
   - If the current element is greater than or equal to the target, return the current index.
   - Otherwise, increment the index.
3. If the loop completes without finding a suitable position, return the length of the array.

---

## 🔹 Code

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] >= target) {
                return index;
            }
            index++;
        }
        return nums.length;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `nums = [1, 3, 5, 6]` and `target = 5`.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | 1 | 1 < 5 | Continue |
| 2 | 3 | 3 < 5 | Continue |
| 3 | 5 | 5 == 5 | Return 2 |

The algorithm returns 2, which is the correct index of the target value.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to achieve O(log n) time complexity. We maintain two pointers, `left` and `right`, which represent the current search range. We calculate the middle index and compare the middle element with the target value to narrow down the search range.

---

## 🔹 Why This Works

Binary search works because the array is sorted. By comparing the middle element with the target, we can determine whether the target lies in the left or right half of the array, effectively halving the search space each time. This approach ensures that we find the insertion position in logarithmic time.

---

## 🔹 Algorithm

1. Initialize `left` to 0 and `right` to the last index of the array.
2. While `left` is less than or equal to `right`:
   - Calculate the middle index `mid` as `left + (right - left) / 2`.
   - If the middle element is equal to the target, return `mid`.
   - If the middle element is less than the target, set `left` to `mid + 1`.
   - Otherwise, set `right` to `mid - 1`.
3. If the loop exits without finding the target, return `left`, which is the insertion position.

---

## 🔹 Code

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums = [1, 3, 5, 6]` and `target = 5`.

| Step | Left | Right | Mid | Action | State |
|---|---|---|---|---|---|
| 1 | 0 | 3 | 1 | nums[1] < 5 | left = 2 |
| 2 | 2 | 3 | 2 | nums[2] == 5 | Return 2 |

The algorithm returns 2, which is the correct index of the target value.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** If the array is empty, the target should be inserted at index 0.
- **Single Element:** If the array has one element, check if the target matches or should be inserted at index 0 or 1.
- **Target at Beginning:** If the target is smaller than the first element, it should be inserted at index 0.
- **Target at End:** If the target is larger than the last element, it should be inserted at the end of the array.
- **Duplicate Values:** Although the problem states the array contains distinct values, it's good to consider how duplicates might affect the insertion position.

---

# 📚 Key Takeaways

- Binary search is an efficient algorithm for searching in sorted arrays.
- The optimal approach leverages the sorted nature of the array to achieve logarithmic time complexity.
- Understanding the binary search algorithm is crucial for solving problems that involve searching in sorted data structures.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss how the solution would change if the array had duplicates.
- **Common Pitfalls:** Ensure that the binary search loop terminates correctly and handles edge cases properly.
- **Alternative Approaches:** Consider using linear search for small arrays, but binary search is preferred for large datasets due to its efficiency.

---

# ✅ Conclusion

The optimal solution using binary search is preferred because it efficiently finds the insertion position in logarithmic time. Understanding the binary search algorithm is essential for solving various problems involving sorted data structures.