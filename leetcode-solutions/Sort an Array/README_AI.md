```markdown
# 📌 Sort an Array

---

# 📝 Problem Statement

Given an array of integers `nums`, sort the array in ascending order and return it.

**Objective:**
- Return a new sorted array (or sort in-place if allowed).
- Handle all edge cases including empty arrays, single elements, duplicates, and negative numbers.

**Constraints:**
- `1 <= nums.length <= 5 * 10^4`
- `-5 * 10^4 <= nums[i] <= 5 * 10^4`

**Input/Output Example:**
```
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
```

---

# 💡 Intuition

Sorting an array is a fundamental problem with multiple approaches. The key insight is recognizing the trade-offs between time and space complexity. While a brute-force approach like Bubble Sort is simple, it is inefficient for large inputs. Optimal approaches like Merge Sort or Quick Sort leverage divide-and-conquer strategies to achieve better performance.

For this problem, we explore:
1. **Brute Force:** Bubble Sort (easy to understand but inefficient).
2. **Optimal:** Merge Sort (stable, efficient, and predictable).

---

# 🐌 Brute Force Approach

## 🔹 Approach
**Bubble Sort** repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process is repeated until the list is sorted.

**Key Characteristics:**
- Simple to implement.
- Inefficient for large datasets (O(n²) time complexity).
- In-place sorting (O(1) space complexity).

---

## 🔹 Algorithm
1. Iterate through the array from the first element to the second-to-last element.
2. For each element, compare it with the next element.
3. If the current element is greater than the next, swap them.
4. Repeat this process for `n` passes, where `n` is the length of the array.
5. After each pass, the largest unsorted element "bubbles up" to its correct position.

---

## 🔹 Code

```java
class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Swap adjacent elements
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [5, 2, 3, 1]`

| Pass | Comparison (j) | Swap? | Array State After Pass |
|------|----------------|-------|------------------------|
| 1    | 5 > 2          | Yes   | [2, 5, 3, 1]           |
|      | 5 > 3          | Yes   | [2, 3, 5, 1]           |
|      | 5 > 1          | Yes   | [2, 3, 1, 5]           |
| 2    | 2 > 3          | No    | [2, 3, 1, 5]           |
|      | 3 > 1          | Yes   | [2, 1, 3, 5]           |
| 3    | 2 > 1          | Yes   | [1, 2, 3, 5]           |

**Final Output:** `[1, 2, 3, 5]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach
**Merge Sort** is a divide-and-conquer algorithm that recursively splits the array into halves, sorts them, and merges the sorted halves. It guarantees O(n log n) time complexity and is stable.

**Key Characteristics:**
- Efficient for large datasets.
- Stable sort (preserves the order of equal elements).
- Requires O(n) auxiliary space.

---

## 🔹 Why This Works
Merge Sort works by breaking the problem into smaller subproblems (divide), solving them recursively (conquer), and combining the results (merge). The merge step ensures that the combined array is sorted by comparing elements from both halves and placing them in order.

---

## 🔹 Algorithm
1. **Divide:** Split the array into two halves.
2. **Conquer:** Recursively sort each half.
3. **Merge:** Combine the two sorted halves into a single sorted array.

---

## 🔹 Code

```java
class Solution {
    public int[] sortArray(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = new int[mid];
        int[] right = new int[nums.length - mid];

        System.arraycopy(nums, 0, left, 0, mid);
        System.arraycopy(nums, mid, right, 0, nums.length - mid);

        sortArray(left);
        sortArray(right);

        merge(nums, left, right);
        return nums;
    }

    private void merge(int[] nums, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }
        while (i < left.length) {
            nums[k++] = left[i++];
        }
        while (j < right.length) {
            nums[k++] = right[j++];
        }
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [5, 2, 3, 1]`

1. **Divide:**
   - Split into `left = [5, 2]` and `right = [3, 1]`.
   - Recursively split `left` into `[5]` and `[2]`.
   - Recursively split `right` into `[3]` and `[1]`.

2. **Conquer:**
   - `[5]` and `[2]` are already sorted.
   - `[3]` and `[1]` are already sorted.

3. **Merge:**
   - Merge `[5]` and `[2]` → `[2, 5]`.
   - Merge `[3]` and `[1]` → `[1, 3]`.
   - Merge `[2, 5]` and `[1, 3]` → `[1, 2, 3, 5]`.

**Final Output:** `[1, 2, 3, 5]`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n log n)  |
| Space Complexity| O(n)        |

---

# 🔍 Edge Cases

| Edge Case               | Expected Output          |
|-------------------------|--------------------------|
| Empty array             | `[]`                     |
| Single element          | `[1]`                    |
| All duplicates          | `[2, 2, 2]`              |
| Negative numbers        | `[-3, -1, 0, 2]`         |
| Already sorted          | `[1, 2, 3]`              |
| Reverse sorted          | `[3, 2, 1]` → `[1, 2, 3]`|
| Large input (5 * 10⁴)   | Sorted array             |

---

# 📚 Key Takeaways

1. **Brute Force vs. Optimal:**
   - Brute force (Bubble Sort) is simple but inefficient for large inputs.
   - Optimal (Merge Sort) is efficient and scalable.

2. **Divide and Conquer:**
   - Merge Sort demonstrates the power of breaking problems into smaller subproblems.

3. **Stability:**
   - Merge Sort is stable, which is important for sorting objects with multiple keys.

4. **Space Trade-off:**
   - Merge Sort uses O(n) space, while in-place sorts like Quick Sort use O(log n) space.

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - Can you implement an in-place Merge Sort?
   - How would you optimize Merge Sort for nearly sorted arrays?
   - Compare Merge Sort with Quick Sort.

2. **Common Pitfalls:**
   - Forgetting base cases in recursive implementations.
   - Incorrect merging logic (e.g., not handling remaining elements).
   - Off-by-one errors in array splitting.

3. **Alternative Approaches:**
   - Quick Sort (average O(n log n), worst-case O(n²)).
   - Heap Sort (O(n log n) time, O(1) space).
   - Counting Sort (O(n + k) time, but limited to small integer ranges).

4. **Optimization Discussions:**
   - Hybrid approaches (e.g., Timsort, which combines Merge Sort and Insertion Sort).
   - Parallelizing Merge Sort for large datasets.

---

# ✅ Conclusion

The optimal solution for sorting an array is **Merge Sort** due to its predictable O(n log n) time complexity and stability. While it requires O(n) auxiliary space, its efficiency and reliability make it a preferred choice for large datasets. Understanding the trade-offs between brute-force and optimal approaches is crucial for mastering sorting algorithms and acing technical interviews.

---
```