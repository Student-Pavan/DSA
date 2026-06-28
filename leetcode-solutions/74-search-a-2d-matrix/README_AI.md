# Search a 2D Matrix

---

# 📝 Problem Statement

You are given an `m x n` integer matrix `matrix` with the following two properties:

1. Each row is sorted in non-decreasing order.
2. The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in `matrix` or `false` otherwise.

You must write a solution in O(log(m * n)) time complexity.

---

# 💡 Intuition

The key observation here is that the matrix is sorted both row-wise and column-wise. This allows us to use a binary search approach that treats the 2D matrix as a 1D sorted array. By converting the 2D indices to 1D and vice versa, we can perform a standard binary search to find the target element efficiently.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through each element in the matrix and checking if it matches the target. This approach is straightforward but inefficient for large matrices.

---

## 🔹 Algorithm

1. Iterate through each row of the matrix.
2. For each row, iterate through each element.
3. If the current element matches the target, return `true`.
4. If the loop completes without finding the target, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

---

## 🔹 Dry Run

Let's consider the following matrix and target:

```
matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3
```

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | matrix[0][0] = 1 | Not equal | Continue |
| 2 | matrix[0][1] = 3 | Equal | Return true |

In this example, the target is found in the first row, second column.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(m * n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves treating the 2D matrix as a 1D sorted array and performing a binary search. This approach leverages the sorted properties of the matrix to achieve O(log(m * n)) time complexity.

---

## 🔹 Why This Works

By converting the 2D indices to 1D and vice versa, we can perform a standard binary search. The key insight is that the matrix is sorted in such a way that the elements are in a non-decreasing order when treated as a 1D array. This allows us to efficiently narrow down the search space by comparing the middle element with the target.

---

## 🔹 Algorithm

1. Calculate the total number of elements in the matrix.
2. Initialize the left and right pointers to the start and end of the 1D array.
3. While the left pointer is less than or equal to the right pointer:
   - Calculate the middle index.
   - Convert the middle index to 2D indices.
   - If the middle element is equal to the target, return `true`.
   - If the middle element is less than the target, move the left pointer to the right of the middle index.
   - If the middle element is greater than the target, move the right pointer to the left of the middle index.
4. If the loop completes without finding the target, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's consider the following matrix and target:

```
matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3
```

| Step | Left | Right | Mid | Mid Value | Action |
|---|---|---|---|---|---|
| 1 | 0 | 11 | 5 | matrix[1][1] = 11 | right = mid - 1 |
| 2 | 0 | 4 | 2 | matrix[0][2] = 5 | right = mid - 1 |
| 3 | 0 | 1 | 0 | matrix[0][0] = 1 | left = mid + 1 |
| 4 | 1 | 1 | 1 | matrix[0][1] = 3 | midValue == target |

In this example, the target is found at the second step.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(log(m * n)) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty matrix
- Single row matrix
- Single column matrix
- Target not present in the matrix
- Target is the first or last element
- Matrix with all identical elements

---

# 📚 Key Takeaways

- The matrix is sorted in a way that allows binary search to be applied.
- Converting 2D indices to 1D and vice versa is a common technique for binary search in 2D arrays.
- The optimal approach leverages the sorted properties of the matrix to achieve logarithmic time complexity.

---

# 🚀 Interview Tips

- Discuss the importance of the matrix being sorted in both dimensions.
- Mention the trade-off between time and space complexity in the brute force and optimal approaches.
- Be prepared to explain the conversion between 1D and 2D indices.
- Consider follow-up questions about handling matrices with varying row lengths.

---

# ✅ Conclusion

The optimal approach is preferred because it leverages the sorted properties of the matrix to achieve logarithmic time complexity, making it significantly more efficient for large matrices. The key insight is recognizing that the matrix can be treated as a 1D sorted array, allowing the application of binary search. This approach is both time and space efficient, making it ideal for interview scenarios.