# 📌 162. Find Peak Element

---

# 📝 Problem Statement

A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that `nums[-1] = nums[n] = -∞`. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

**Constraints:**

- `1 <= nums.length <= 1000`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `nums[i] != nums[i + 1]` for all valid i.

---

# 💡 Intuition

The problem requires finding a peak element in an array. A peak element is defined as an element that is greater than its neighbors. The challenge is to find this peak efficiently, specifically in O(log n) time, which suggests using a binary search approach.

The key insight is that in a given array, there must be at least one peak element. We can leverage this property to perform a binary search. By comparing the middle element with its right neighbor, we can determine which half of the array contains a peak. If the middle element is less than its right neighbor, the peak must be in the right half; otherwise, it must be in the left half (including the middle element).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the array and checking each element to see if it is greater than its neighbors. If an element is found that satisfies this condition, its index is returned immediately.

---

## 🔹 Algorithm

1. Iterate through the array from the first element to the second-to-last element.
2. For each element, check if it is greater than its next neighbor.
3. If such an element is found, return its index immediately.
4. If the loop completes without finding a peak, return the last index (since the last element is considered to have a neighbor of -∞).

---

## 🔹 Code

```java
class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the input array `[1, 2, 3, 1]`.

| Iteration | Current Index | Current Value | Next Value | Condition (Current > Next) | Action |
|-----------|----------------|---------------|------------|----------------------------|--------|
| 1         | 0              | 1             | 2          | False                      | Continue |
| 2         | 1              | 2             | 3          | False                      | Continue |
| 3         | 2              | 3             | 1          | True                       | Return 2 |

In this example, the peak element is found at index 2, and the algorithm returns 2.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to find a peak element in O(log n) time. The idea is to compare the middle element with its right neighbor. If the middle element is less than the right neighbor, the peak must be in the right half; otherwise, it must be in the left half (including the middle element).

---

## 🔹 Why This Works

The algorithm works because there must be at least one peak in the array. By comparing the middle element with its right neighbor, we can determine which half of the array contains the peak. This approach efficiently narrows down the search space by half in each iteration, leading to logarithmic time complexity.

---

## 🔹 Algorithm

1. Initialize two pointers, `left` and `right`, to the start and end of the array, respectively.
2. While `left` is less than `right`:
   - Calculate the middle index `mid`.
   - If the element at `mid` is greater than the element at `mid + 1`, set `right` to `mid`.
   - Otherwise, set `left` to `mid + 1`.
3. Return `left` as the index of the peak element.

---

## 🔹 Code

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input array `[1, 2, 3, 1]`.

| Step | Left | Right | Mid | Condition (nums[mid] > nums[mid + 1]) | Action |
|------|------|-------|-----|---------------------------------------|--------|
| 1    | 0    | 3     | 1   | False (2 > 3? No)                     | left = mid + 1 (2) |
| 2    | 2    | 3     | 2   | True (3 > 1? Yes)                     | right = mid (2) |
| 3    | 2    | 2     | -    | -                                     | Loop ends |

The loop ends when `left` equals `right`, which is 2. The peak element is at index 2.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Element Array**: `[1]` - The only element is the peak.
- **Two Elements**: `[1, 2]` - The second element is the peak.
- **Peak at the End**: `[1, 2, 3]` - The last element is the peak.
- **Peak at the Beginning**: `[3, 2, 1]` - The first element is the peak.
- **All Elements Equal**: `[1, 1, 1]` - Not possible as per constraints.

---

# 📚 Key Takeaways

- **Binary Search**: The optimal solution leverages binary search to achieve O(log n) time complexity.
- **Peak Guarantee**: There is always at least one peak in the array, which allows the binary search to work.
- **Efficiency**: The optimal approach is significantly more efficient than the brute force approach, especially for large arrays.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how the solution would change if the array is not guaranteed to have a peak or if there are duplicate elements.
- **Common Pitfalls**: Ensure that the binary search correctly handles the edge cases, such as the peak being at the beginning or end of the array.
- **Alternative Approaches**: Consider how other search algorithms, such as linear search, could be used, but note their inefficiency.

---

# ✅ Conclusion

The optimal solution using binary search is the preferred approach for this problem due to its O(log n) time complexity. The key insight is recognizing that a peak must exist in the array and using binary search to efficiently locate it. This approach is both efficient and easy to understand, making it ideal for interview scenarios.