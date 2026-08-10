# 📌 153. Find Minimum in Rotated Sorted Array

---

# 📝 Problem Statement

Given the sorted rotated array `nums` of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

**Example 1:**
```
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
```

**Example 2:**
```
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
```

**Example 3:**
```
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
```

**Constraints:**
- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- All the integers of `nums` are unique.
- `nums` is sorted and rotated between 1 and n times.

---

# 💡 Intuition

The key insight is recognizing that in a rotated sorted array, there's always one half of the array that is completely sorted. By comparing the middle element with the rightmost element, we can determine which half contains the minimum element and eliminate the other half.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves scanning through the entire array to find the minimum element. This approach is straightforward but inefficient for large arrays.

---

## 🔹 Algorithm

1. Initialize `min_val` with the first element of the array.
2. Iterate through the array starting from the second element.
3. For each element, compare it with `min_val`.
4. If the current element is smaller than `min_val`, update `min_val`.
5. After the loop, return `min_val`.

---

## 🔹 Code

```java
class Solution {
    public int findMin(int[] nums) {
        int minVal = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }
        return minVal;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with `nums = [4,5,6,7,0,1,2]`:

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | 4 | minVal = 4 | minVal = 4 |
| 2 | 5 | minVal = 4 | minVal = 4 |
| 3 | 6 | minVal = 4 | minVal = 4 |
| 4 | 7 | minVal = 4 | minVal = 4 |
| 5 | 0 | minVal = 0 | minVal = 0 |
| 6 | 1 | minVal = 0 | minVal = 0 |
| 7 | 2 | minVal = 0 | minVal = 0 |

Final result: `0`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to achieve O(log n) time complexity. The idea is to leverage the properties of the rotated sorted array to determine which half of the array to search in.

---

## 🔹 Why This Works

In a rotated sorted array, there's always one half that is completely sorted. By comparing the middle element with the rightmost element, we can determine if the minimum is in the left or right half. This allows us to eliminate half of the array in each iteration.

---

## 🔹 Algorithm

1. Initialize `left` to 0 and `right` to the last index of the array.
2. While `left` is less than `right`:
   - Calculate the middle index `mid`.
   - If `nums[mid]` is less than or equal to `nums[right]`, the minimum is in the left half, so set `right` to `mid`.
   - Otherwise, the minimum is in the right half, so set `left` to `mid + 1`.
3. When the loop ends, `left` will be pointing to the minimum element, so return `nums[left]`.

---

## 🔹 Code

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= nums[right])
                right = mid;

            else
                left = mid + 1;

        }

        return nums[left];
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `nums = [4,5,6,7,0,1,2]`:

| Step | Left | Right | Mid | Action | State |
|---|---|---|---|---|---|
| 1 | 0 | 6 | 3 | nums[3] <= nums[6] (7 <= 2? No) | left = mid + 1 = 4 |
| 2 | 4 | 6 | 5 | nums[5] <= nums[6] (1 <= 2? Yes) | right = mid = 5 |
| 3 | 4 | 5 | 4 | nums[4] <= nums[5] (0 <= 1? Yes) | right = mid = 4 |
| 4 | 4 | 4 | - | left < right? No | Exit loop |

Final result: `nums[4] = 0`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Single element array: `[5]` → `5`
- Already sorted array: `[1,2,3,4,5]` → `1`
- Fully rotated array: `[5,1,2,3,4]` → `1`
- Minimum at the end: `[4,5,6,7,0,1,2]` → `0`
- Minimum at the beginning: `[1,2,3,4,5]` → `1`

---

# 📚 Key Takeaways

- The optimal solution leverages binary search to achieve O(log n) time complexity.
- The key insight is recognizing that one half of the array is always sorted in a rotated sorted array.
- The brute force approach is simple but inefficient for large arrays.
- Understanding the properties of rotated sorted arrays is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- Be prepared to explain the intuition behind the binary search approach.
- Practice dry runs with different edge cases to ensure understanding.
- Be ready to discuss the time and space complexity of both approaches.
- Consider asking follow-up questions about handling duplicates or other variations of the problem.

---

# ✅ Conclusion

The optimal solution using binary search is the preferred approach due to its O(log n) time complexity, making it significantly more efficient than the brute force O(n) solution. The key insight is recognizing that one half of the array is always sorted, allowing us to eliminate half of the search space in each iteration. This approach is essential for solving rotated sorted array problems efficiently in technical interviews.