# 📌 Search in Rotated Sorted Array

---

# 📝 Problem Statement

Given a sorted and rotated array `nums` of unique elements and a target value, return the index of the target if it exists in the array, otherwise return `-1`.

**Constraints:**
- `1 <= nums.length <= 5000`
- `-10^4 <= nums[i] <= 10^4`
- All values in `nums` are unique
- `nums` is guaranteed to be rotated at some pivot point

---

# 💡 Intuition

The key insight is recognizing that a rotated sorted array consists of two sorted subarrays. We can leverage binary search but need to determine which half of the array is sorted and whether the target lies within that half.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A brute force approach would simply iterate through the array linearly, checking each element for the target. This is inefficient but straightforward.

---

## 🔹 Algorithm

1. Iterate through each element in the array.
2. If the current element matches the target, return its index.
3. If the loop completes without finding the target, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `nums = [4,5,6,7,0,1,2]` and `target = 0`.

| Iteration | Current Value | Current Index | Result |
|-----------|---------------|---------------|--------|
| 1         | 4             | 0             | Not found |
| 2         | 5             | 1             | Not found |
| 3         | 6             | 2             | Not found |
| 4         | 7             | 3             | Not found |
| 5         | 0             | 4             | Found at index 4 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use a modified binary search to efficiently locate the target in O(log n) time. The key is determining which half of the array is sorted and whether the target lies within that half.

---

## 🔹 Why This Works

The algorithm works by first finding the midpoint and comparing it with the leftmost element to determine which half is sorted. Then it checks if the target lies within the sorted half, adjusting the search range accordingly.

---

## 🔹 Algorithm

1. Initialize `left` to 0 and `right` to `nums.length - 1`.
2. While `left <= right`:
   - Calculate `mid` as `left + (right - left) / 2`.
   - If `nums[mid] == target`, return `mid`.
   - If the left half is sorted (`nums[left] <= nums[mid]`):
     - If `target` is in the left half, search left by setting `right = mid - 1`.
     - Else, search right by setting `left = mid + 1`.
   - Else, the right half is sorted:
     - If `target` is in the right half, search right by setting `left = mid + 1`.
     - Else, search left by setting `right = mid - 1`.
3. If the loop ends without finding the target, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums = [4,5,6,7,0,1,2]` and `target = 0`.

| Step | Left | Right | Mid | Action | State |
|------|------|-------|-----|--------|-------|
| 1    | 0    | 6     | 3   | nums[3] >= nums[0] | Left half sorted |
| 2    | 0    | 6     | 3   | target not in left half | Search right |
| 3    | 4    | 6     | 5   | nums[5] >= nums[4] | Left half sorted |
| 4    | 4    | 6     | 5   | target not in left half | Search right |
| 5    | 6    | 6     | 6   | nums[6] == target | Found at index 6 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty array
- Single element array
- Target is the first element
- Target is the last element
- Target is in the first sorted subarray
- Target is in the second sorted subarray
- Array is not rotated (fully sorted)
- Target not present in the array

---

# 📚 Key Takeaways

- Binary search can be adapted for rotated sorted arrays by determining which half is sorted.
- The optimal solution leverages the properties of sorted subarrays to achieve O(log n) time complexity.
- Understanding the pivot point is crucial for solving rotated array problems efficiently.

---

# 🚀 Interview Tips

- Ask clarifying questions about array properties (e.g., uniqueness, rotation).
- Consider edge cases like empty arrays or single-element arrays.
- Practice visualizing the array to understand the rotation and sorted subarrays.
- Be prepared to discuss alternative approaches like linear search.

---

# ✅ Conclusion

The optimal solution using modified binary search is significantly more efficient than the brute force approach, especially for large input sizes. Understanding the underlying structure of rotated sorted arrays is key to solving such problems effectively.