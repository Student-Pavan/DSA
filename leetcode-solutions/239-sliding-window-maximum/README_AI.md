# Sliding Window Maximum

---

# 📝 Problem Statement

You are given an array of integers `nums` and an integer `k`. Your task is to find the maximum value in each sliding window of size `k` as it moves from the beginning to the end of the array. The window slides one element at a time.

**Objective**: Return an array containing the maximum values for each sliding window.

**Constraints**:
- `1 <= nums.length <= 10^5`
- `-10^4 <= nums[i] <= 10^4`
- `1 <= k <= nums.length`

---

# 💡 Intuition

The key insight is that we need an efficient way to track the maximum in each window without scanning the entire window for each position. A brute force approach would be to scan each window individually, resulting in O(n*k) time complexity. The optimal approach uses a deque (double-ended queue) to maintain potential maximum candidates, achieving O(n) time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves examining each window individually by scanning through the array and for each position, finding the maximum in the current window.

## 🔹 Algorithm

1. Initialize an empty result array.
2. For each index `i` from `0` to `n - k`:
   - Initialize `max_val` to the smallest possible integer.
   - For each index `j` from `i` to `i + k - 1`:
     - Update `max_val` with the maximum of `max_val` and `nums[j]`.
   - Append `max_val` to the result array.
3. Return the result array.

## 🔹 Code

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max_val = Math.max(max_val, nums[j]);
            }
            result[i] = max_val;
        }
        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with `nums = [1, 3, -1, -3, 5, 3, 6, 7]` and `k = 3`.

| Iteration | Window | Max Value | Result |
|-----------|--------|-----------|--------|
| 0         | [1, 3, -1] | 3         | [3]    |
| 1         | [3, -1, -3] | 3         | [3, 3] |
| 2         | [-1, -3, 5] | 5         | [3, 3, 5] |
| 3         | [-3, 5, 3] | 5         | [3, 3, 5, 5] |
| 4         | [5, 3, 6] | 6         | [3, 3, 5, 5, 6] |
| 5         | [3, 6, 7] | 7         | [3, 3, 5, 5, 6, 7] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n*k) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a deque to maintain indices of elements in the current window in such a way that the deque always contains the maximum element at the front. This allows us to access the maximum in O(1) time for each window.

## 🔹 Why This Works

By maintaining the deque in decreasing order of values, we ensure that the front of the deque always contains the maximum value in the current window. This approach efficiently tracks the maximum for each window as the window slides.

## 🔹 Algorithm

1. Initialize an empty deque and an empty result array.
2. For each index `right` from `0` to `n - 1`:
   - Remove indices from the front of the deque that are outside the current window.
   - Remove indices from the back of the deque where the corresponding elements are smaller than the current element.
   - Add the current index to the back of the deque.
   - If the window size is reached (i.e., `right >= k - 1`), add the element at the front of the deque to the result array.
3. Return the result array.

## 🔹 Code

```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int right = 0; right < n; right++) {
            while (!deque.isEmpty() && deque.peekFirst() <= right - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }
            deque.addLast(right);
            if (right >= k - 1) {
                result[right - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `nums = [1, 3, -1, -3, 5, 3, 6, 7]` and `k = 3`.

| Iteration | Right | Deque | Action | Result |
|-----------|-------|-------|--------|--------|
| 0         | 0     | [0]   | Add 0  | []     |
| 1         | 1     | [1]   | Remove 0, Add 1 | []     |
| 2         | 2     | [1, 2] | Add 2 | [3]    |
| 3         | 3     | [1, 2, 3] | Remove 1, Add 3 | [3, 3] |
| 4         | 4     | [4]   | Remove 2, Remove 3, Add 4 | [3, 3, 5] |
| 5         | 5     | [4, 5] | Add 5 | [3, 3, 5, 5] |
| 6         | 6     | [6]   | Remove 4, Remove 5, Add 6 | [3, 3, 5, 5, 6] |
| 7         | 7     | [7]   | Remove 6, Add 7 | [3, 3, 5, 5, 6, 7] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(k) |

---

# 🔍 Edge Cases

- **Empty Input**: `nums = []` should return `[]`.
- **Single Element**: `nums = [5], k = 1` should return `[5]`.
- **All Elements Same**: `nums = [2, 2, 2], k = 2` should return `[2, 2]`.
- **Negative Values**: `nums = [-1, -2, -3], k = 2` should return `[-1, -2]`.
- **Large Constraints**: `nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3` should return `[3, 3, 5, 5, 6, 7]`.

---

# 📚 Key Takeaways

- **Sliding Window Technique**: Understanding how to efficiently track elements in a sliding window is crucial.
- **Deque Usage**: The deque helps maintain the order of elements, ensuring optimal performance.
- **Efficiency**: The optimal approach reduces the time complexity from O(n*k) to O(n), making it suitable for large input sizes.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle edge cases and alternative approaches.
- **Common Pitfalls**: Be cautious about index management and ensuring the deque always contains valid indices.
- **Alternative Approaches**: Consider using a priority queue, but it may not be as efficient for this problem.

---

# ✅ Conclusion

The optimal approach using a deque is significantly more efficient than the brute force method, especially for large input sizes. Understanding the sliding window maximum problem helps in recognizing patterns that can be applied to other similar problems. The key takeaway is to leverage a deque to maintain the order of elements, ensuring optimal performance.