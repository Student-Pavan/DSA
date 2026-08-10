# 📌 Find First and Last Position of Element in Sorted Array

---

# 📝 Problem Statement

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value.

If `target` is not found in the array, return `[-1, -1]`.

You must write an algorithm with `O(log n)` runtime complexity.

**Constraints:**
- `0 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`
- `nums` is a non-decreasing array
- `-10^9 <= target <= 10^9`

---

# 💡 Intuition

The problem requires finding the first and last occurrence of a target value in a sorted array. The key insight is that the array is sorted, which allows us to use binary search for efficient O(log n) time complexity.

The approach involves:
1. Performing a modified binary search to find the first occurrence of the target
2. Performing another modified binary search to find the last occurrence of the target
3. Combining these results to get the final answer

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves a linear scan through the array to find the first and last occurrences of the target. This approach is straightforward but inefficient for large arrays.

## 🔹 Algorithm

1. Initialize `first` and `last` to `-1`
2. Iterate through the array:
   - If `nums[i] == target` and `first == -1`, set `first = i`
   - If `nums[i] == target`, update `last = i`
3. Return `[first, last]`

## 🔹 Code

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }

        return new int[]{first, last};
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with `nums = [5,7,7,8,8,10]` and `target = 8`:

| Iteration | Current Value | First | Last |
|-----------|---------------|-------|------|
| 0         | 5             | -1    | -1   |
| 1         | 7             | -1    | -1   |
| 2         | 7             | -1    | -1   |
| 3         | 8             | 3     | 3    |
| 4         | 8             | 3     | 4    |
| 5         | 10            | 3     | 4    |

Final result: `[3, 4]`

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to find the first and last occurrences of the target in O(log n) time. This is achieved by performing two separate binary searches:

1. First binary search to find the first occurrence of the target
2. Second binary search to find the last occurrence of the target

## 🔹 Why This Works

The binary search algorithm is modified to continue searching even after finding a match, allowing it to find the first or last occurrence based on the direction of the search.

## 🔹 Algorithm

1. Initialize `first` and `last` to `-1`
2. Perform binary search to find the first occurrence:
   - If `nums[mid] == target`, record the position and continue searching left
   - Otherwise, adjust the search range as in standard binary search
3. Perform binary search to find the last occurrence:
   - If `nums[mid] == target`, record the position and continue searching right
   - Otherwise, adjust the search range as in standard binary search
4. Return `[first, last]`

## 🔹 Code

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirstOccurrence(nums, target);
        int last = findLastOccurrence(nums, target);

        return new int[]{first, last};
    }

    private int findFirstOccurrence(int[] nums, int target) {
        int first = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                first = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return first;
    }

    private int findLastOccurrence(int[] nums, int target) {
        int last = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                last = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return last;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal solution with `nums = [5,7,7,8,8,10]` and `target = 8`:

### First Occurrence Search

| Step | Left | Right | Mid | Action | First |
|------|------|-------|-----|--------|-------|
| 1    | 0    | 5     | 2   | nums[2] == 7 < 8 | left = 3 |
| 2    | 3    | 5     | 4   | nums[4] == 8 | first = 4, right = 3 |
| 3    | 3    | 3     | 3   | nums[3] == 8 | first = 3, right = 2 |
| 4    | 3    | 2     | -   | left > right | - |

### Last Occurrence Search

| Step | Left | Right | Mid | Action | Last |
|------|------|-------|-----|--------|------|
| 1    | 0    | 5     | 2   | nums[2] == 7 < 8 | left = 3 |
| 2    | 3    | 5     | 4   | nums[4] == 8 | last = 4, left = 5 |
| 3    | 5    | 5     | 5   | nums[5] == 10 > 8 | right = 4 |
| 4    | 5    | 4     | -   | left > right | - |

Final result: `[3, 4]`

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty array: `nums = []`, `target = 0` → `[-1, -1]`
- Single element array: `nums = [1]`, `target = 1` → `[0, 0]`
- Target not in array: `nums = [1,2,3]`, `target = 4` → `[-1, -1]`
- All elements same as target: `nums = [5,5,5]`, `target = 5` → `[0, 2]`
- Large array: `nums = [1,2,...,10^5]`, `target = 50000` → `[49999, 49999]`

---

# 📚 Key Takeaways

1. Binary search is efficient for sorted arrays
2. Modified binary search can find first/last occurrences
3. Two separate binary searches are needed for this problem
4. Time complexity improves from O(n) to O(log n) with binary search
5. Edge cases must be handled properly

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - What if the array is not sorted?
   - How would you handle duplicates differently?
   - Can you solve it with a single pass?

2. **Common Pitfalls:**
   - Forgetting to handle empty array case
   - Incorrectly updating search range during binary search
   - Not considering edge cases properly

3. **Alternative Approaches:**
   - Using linear scan (brute force)
   - Using hash map to store indices (O(n) space)

4. **Optimization Discussions:**
   - Why binary search is better than linear scan
   - Trade-offs between time and space complexity

---

# ✅ Conclusion

The optimal solution using binary search is significantly more efficient than the brute force approach, especially for large arrays. The key insight is recognizing that binary search can be modified to find the first and last occurrences of a target value in a sorted array. This approach demonstrates the power of binary search and its applications in solving range-related problems efficiently.