# 🔍 Binary Search

---

# 📝 Problem Statement

Given a **sorted** array of integers `nums` and an integer `target`, return the **index** of `target` if it exists in the array. If the target does not exist, return `-1`.

### **Objective**
Find the position of `target` in `nums` efficiently using binary search.

### **Input**
- `nums`: A **sorted** array of integers (ascending order).
- `target`: The integer to search for.

### **Output**
- The index of `target` in `nums`, or `-1` if not found.

### **Constraints**
- `1 <= nums.length <= 10^4`
- `-10^4 <= nums[i], target <= 10^4`
- All integers in `nums` are **unique**.
- `nums` is sorted in **ascending order**.

---

# 💡 Intuition

Binary search is a **divide-and-conquer** algorithm that efficiently locates a target in a **sorted** array by repeatedly halving the search space. The key insight is that since the array is sorted, we can eliminate half of the remaining elements in each step by comparing the middle element with the target.

This reduces the time complexity from **O(n)** (linear search) to **O(log n)**, making it optimal for large datasets.

---

# 🐌 Brute Force Approach

## 🔹 Approach
A brute-force solution would involve iterating through the entire array sequentially until the target is found. This approach does not leverage the sorted property of the array and checks every element in the worst case.

---

## 🔹 Algorithm
1. Iterate through each element in `nums`.
2. Compare the current element with `target`.
3. If a match is found, return the current index.
4. If the loop completes without finding `target`, return `-1`.

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

**Example:** `nums = [-1, 0, 3, 5, 9, 12]`, `target = 9`

| Step | Current Index | Current Value | Comparison | Result |
|------|---------------|---------------|------------|--------|
| 1    | 0             | -1            | -1 != 9    | Continue |
| 2    | 1             | 0             | 0 != 9     | Continue |
| 3    | 2             | 3             | 3 != 9     | Continue |
| 4    | 3             | 5             | 5 != 9     | Continue |
| 5    | 4             | 9             | 9 == 9     | **Return 4** |

**Final Output:** `4`

---

## 🔹 Complexity Analysis

| Complexity       | Value  |
|------------------|--------|
| Time Complexity  | O(n)   |
| Space Complexity | O(1)   |

---

# ⚡ Optimal Approach

## 🔹 Approach
Binary search leverages the sorted property of the array to eliminate half of the remaining elements in each step. The algorithm maintains two pointers, `left` and `right`, representing the current search range. The middle element is compared with the target to determine whether to search the left or right half.

---

## 🔹 Why This Works
- **Efficiency:** Each comparison reduces the search space by half, leading to logarithmic time complexity.
- **Correctness:** The sorted property ensures that if the target is not in the current half, it cannot be in that half, allowing us to discard it safely.

---

## 🔹 Algorithm
1. Initialize `left = 0` and `right = nums.length - 1`.
2. While `left <= right`:
   - Calculate `mid = left + (right - left) / 2` (to avoid overflow).
   - If `nums[mid] == target`, return `mid`.
   - If `nums[mid] < target`, set `left = mid + 1`.
   - If `nums[mid] > target`, set `right = mid - 1`.
3. If the loop ends without finding `target`, return `-1`.

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
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `nums = [-1, 0, 3, 5, 9, 12]`, `target = 9`

| Step | Left | Right | Mid | nums[mid] | Comparison | Action          |
|------|------|-------|-----|-----------|------------|-----------------|
| 1    | 0    | 5     | 2   | 3         | 3 < 9      | `left = mid + 1` |
| 2    | 3    | 5     | 4   | 9         | 9 == 9     | **Return 4**    |

**Final Output:** `4`

---

## 🔹 Complexity Analysis

| Complexity       | Value  |
|------------------|--------|
| Time Complexity  | O(log n) |
| Space Complexity | O(1)   |

---

# 🔍 Edge Cases

| Case                     | Expected Output | Explanation                          |
|--------------------------|-----------------|--------------------------------------|
| Empty array              | -1              | No elements to search.               |
| Single element (match)   | 0               | Only one element, and it matches.    |
| Single element (no match)| -1              | Only one element, but it doesn’t match. |
| Target at first index    | 0               | Target is the first element.         |
| Target at last index     | `nums.length - 1` | Target is the last element.        |
| Target not in array      | -1              | Target does not exist in the array.  |
| Large array (10^4 elements) | Correct index | Binary search handles large inputs efficiently. |

---

# 📚 Key Takeaways

1. **Binary Search Requires Sorted Input:** The array must be sorted for binary search to work.
2. **Logarithmic Time Complexity:** Binary search reduces the problem size by half in each step, leading to **O(log n)** time.
3. **Avoid Overflow:** Use `mid = left + (right - left) / 2` instead of `(left + right) / 2` to prevent integer overflow.
4. **Pointer Management:** The `left` and `right` pointers must be updated carefully to avoid infinite loops.
5. **Termination Condition:** The loop must run while `left <= right` to ensure all possible cases are covered.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Always confirm if the input is sorted and if duplicates are allowed.
2. **Edge Cases:** Discuss edge cases like empty arrays, single-element arrays, and targets at boundaries.
3. **Follow-Up Questions:**
   - How would you modify the solution if duplicates were allowed?
   - Can you implement binary search recursively?
   - How would you handle a descending-order sorted array?
4. **Common Pitfalls:**
   - Forgetting to update `left` or `right` correctly.
   - Using `(left + right) / 2` instead of `left + (right - left) / 2`.
   - Off-by-one errors in loop conditions (`left < right` vs `left <= right`).
5. **Alternative Approaches:**
   - Recursive binary search (though it uses **O(log n)** space due to call stack).
   - Ternary search (though it is less efficient than binary search in practice).

---

# ✅ Conclusion

Binary search is a **fundamental** algorithm for efficiently locating elements in sorted arrays. The optimal approach achieves **O(log n)** time complexity by leveraging the sorted property to eliminate half of the search space in each iteration. This makes it significantly faster than brute-force methods for large datasets.

**Key Insight:** The power of binary search lies in its ability to discard irrelevant portions of the array early, making it a go-to solution for search problems in sorted data.

**Final Takeaway:** Mastering binary search is essential for technical interviews, as it frequently appears in problems involving sorted data, rotated arrays, and even in more advanced algorithms like binary lifting or segment trees.