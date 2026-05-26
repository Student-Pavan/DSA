# 📌 Container With Most Water

---

# 📝 Problem Statement

You are given an integer array `height` of length `n`, where each element represents the height of a vertical line at position `i`. Your task is to find two lines that, together with the x-axis, form a container that can hold the most water.

**Objective**: Return the maximum amount of water the container can store.

**Key Points**:
- The container is formed by two lines and the x-axis.
- The width of the container is the distance between the two lines (indices).
- The height of the container is the minimum of the two line heights.
- The area is calculated as: `area = min(height[left], height[right]) * (right - left)`.

**Constraints**:
- `n == height.length`
- `2 <= n <= 10^5`
- `0 <= height[i] <= 10^4`

**Example**:
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The maximum area is formed by the lines at indices 1 and 8 (0-based), with heights 8 and 7 respectively. The area is min(8, 7) * (8 - 1) = 7 * 7 = 49.
```

---

# 💡 Intuition

The problem requires finding the maximum area formed by two vertical lines. The area depends on two factors:
1. The **height** of the shorter line (since water can only be held up to the shorter line).
2. The **width** between the two lines (distance between indices).

The naive approach would check all possible pairs of lines, but this leads to a time complexity of O(n²), which is inefficient for large inputs. The optimal approach leverages the **two-pointer technique** to reduce the time complexity to O(n). The key insight is that moving the pointer pointing to the shorter line might lead to a larger area, while moving the pointer pointing to the taller line will never increase the area (since the width decreases and the height is limited by the shorter line).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible pairs of lines to compute the area and keeping track of the maximum area encountered. For each line at index `i`, we iterate through all lines at indices `j > i` and compute the area formed by the pair `(i, j)`. The maximum area across all pairs is the answer.

---

## 🔹 Algorithm

1. Initialize `maxArea = 0`.
2. Iterate through each line at index `i` from `0` to `n-1`.
3. For each `i`, iterate through each line at index `j` from `i+1` to `n-1`.
4. Compute the area for the pair `(i, j)` as `min(height[i], height[j]) * (j - i)`.
5. Update `maxArea` if the computed area is greater than the current `maxArea`.
6. Return `maxArea` after all iterations.

---

## 🔹 Code

```java
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int n = height.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int currentArea = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, currentArea);
            }
        }

        return maxArea;
    }
}
```

---

## 🔹 Dry Run

**Input**: `height = [1, 8, 6, 2, 5, 4, 8, 3, 7]`

| Iteration (i, j) | height[i] | height[j] | Width (j - i) | Current Area | maxArea |
|------------------|-----------|-----------|---------------|--------------|---------|
| (0, 1)           | 1         | 8         | 1             | min(1,8)*1 = 1 | 1       |
| (0, 2)           | 1         | 6         | 2             | min(1,6)*2 = 2 | 2       |
| (0, 3)           | 1         | 2         | 3             | min(1,2)*3 = 3 | 3       |
| (0, 4)           | 1         | 5         | 4             | min(1,5)*4 = 4 | 4       |
| (0, 5)           | 1         | 4         | 5             | min(1,4)*5 = 5 | 5       |
| (0, 6)           | 1         | 8         | 6             | min(1,8)*6 = 6 | 6       |
| (0, 7)           | 1         | 3         | 7             | min(1,3)*7 = 7 | 7       |
| (0, 8)           | 1         | 7         | 8             | min(1,7)*8 = 8 | 8       |
| (1, 2)           | 8         | 6         | 1             | min(8,6)*1 = 6 | 8       |
| (1, 3)           | 8         | 2         | 2             | min(8,2)*2 = 4 | 8       |
| (1, 4)           | 8         | 5         | 3             | min(8,5)*3 = 15 | 15      |
| (1, 5)           | 8         | 4         | 4             | min(8,4)*4 = 16 | 16      |
| (1, 6)           | 8         | 8         | 5             | min(8,8)*5 = 40 | 40      |
| (1, 7)           | 8         | 3         | 6             | min(8,3)*6 = 18 | 40      |
| (1, 8)           | 8         | 7         | 7             | min(8,7)*7 = 49 | 49      |
| (2, 3)           | 6         | 2         | 1             | min(6,2)*1 = 2 | 49      |
| (2, 4)           | 6         | 5         | 2             | min(6,5)*2 = 10 | 49      |
| (2, 5)           | 6         | 4         | 3             | min(6,4)*3 = 12 | 49      |
| (2, 6)           | 6         | 8         | 4             | min(6,8)*4 = 24 | 49      |
| (2, 7)           | 6         | 3         | 5             | min(6,3)*5 = 15 | 49      |
| (2, 8)           | 6         | 7         | 6             | min(6,7)*6 = 36 | 49      |
| ...              | ...       | ...       | ...           | ...          | 49      |

**Final Output**: `49`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n²)     |
| Space Complexity | O(1)      |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the **two-pointer technique** to efficiently find the maximum area in O(n) time. The idea is to start with the widest possible container (pointers at the start and end of the array) and then move the pointer pointing to the shorter line inward. This is because moving the shorter line might lead to a larger area, while moving the taller line will never increase the area (since the width decreases and the height is limited by the shorter line).

---

## 🔹 Why This Works

- **Initialization**: Start with the widest container (left = 0, right = n-1). This ensures the maximum possible width.
- **Pointer Movement**: Move the pointer pointing to the shorter line inward. This is because the area is limited by the shorter line, and moving the taller line inward will only decrease the width without increasing the height.
- **Termination**: The loop terminates when the pointers meet, ensuring all possible containers are considered.

This approach guarantees that we explore all potential maximum areas without redundant checks, leading to an optimal solution.

---

## 🔹 Algorithm

1. Initialize `left = 0` and `right = n-1`.
2. Initialize `maxArea = 0`.
3. While `left < right`:
   - Compute the current area: `currentArea = min(height[left], height[right]) * (right - left)`.
   - Update `maxArea` if `currentArea` is greater.
   - If `height[left] < height[right]`, increment `left`.
   - Else, decrement `right`.
4. Return `maxArea`.

---

## 🔹 Code

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input**: `height = [1, 8, 6, 2, 5, 4, 8, 3, 7]`

| Step | Left | Right | height[Left] | height[Right] | Width | Current Area | maxArea | Action       |
|------|------|-------|--------------|---------------|-------|--------------|---------|--------------|
| 1    | 0    | 8     | 1            | 7             | 8     | min(1,7)*8 = 8 | 8       | left++       |
| 2    | 1    | 8     | 8            | 7             | 7     | min(8,7)*7 = 49 | 49      | right--      |
| 3    | 1    | 7     | 8            | 3             | 6     | min(8,3)*6 = 18 | 49      | right--      |
| 4    | 1    | 6     | 8            | 8             | 5     | min(8,8)*5 = 40 | 49      | right--      |
| 5    | 1    | 5     | 8            | 4             | 4     | min(8,4)*4 = 16 | 49      | right--      |
| 6    | 1    | 4     | 8            | 5             | 3     | min(8,5)*3 = 15 | 49      | right--      |
| 7    | 1    | 3     | 8            | 2             | 2     | min(8,2)*2 = 4  | 49      | right--      |
| 8    | 1    | 2     | 8            | 6             | 1     | min(8,6)*1 = 6  | 49      | right--      |

**Final Output**: `49`

---

## 🔹 Complexity Analysis

| Complexity       | Value     |
|------------------|-----------|
| Time Complexity  | O(n)      |
| Space Complexity | O(1)      |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `height = [1, 1]`             | 1               | Only one possible container with area 1.                                    |
| `height = [4, 3, 2, 1, 4]`    | 16              | Maximum area is formed by the first and last lines.                         |
| `height = [1, 2, 1]`          | 2               | Maximum area is formed by the first and last lines.                         |
| `height = [1, 2, 3, 4, 5]`    | 6               | Maximum area is formed by the first and last lines (min(1,5)*4 = 4, but min(2,5)*3 = 6). |
| `height = [5, 4, 3, 2, 1]`    | 6               | Similar to the above, but in reverse order.                                 |
| `height = [1, 2, 4, 3]`       | 4               | Maximum area is 4 (min(2,3)*2 = 4).                                         |
| `height = []`                 | 0               | Empty input (though constraints say n >= 2).                                |
| `height = [1]`                | 0               | Single element (though constraints say n >= 2).                             |

---

# 📚 Key Takeaways

1. **Two-Pointer Technique**: This problem is a classic example of the two-pointer technique, which is useful for optimizing problems involving arrays or sequences.
2. **Greedy Insight**: The optimal solution leverages the greedy insight that moving the shorter line might lead to a larger area, while moving the taller line will not.
3. **Efficiency**: The optimal solution reduces the time complexity from O(n²) to O(n), making it suitable for large inputs.
4. **Area Calculation**: The area is always limited by the shorter of the two lines, so the taller line can be "sacrificed" to explore potentially larger areas.

---

# 🚀 Interview Tips

1. **Follow-Up Questions**:
   - How would you handle the problem if the input array is extremely large (e.g., 10^6 elements)? (Answer: The optimal O(n) solution is already suitable.)
   - Can you solve this problem using a stack or other data structures? (Answer: Not necessary, as the two-pointer approach is optimal.)
   - What if the lines are not vertical but at arbitrary angles? (Answer: The problem becomes more complex and may require geometric algorithms.)

2. **Common Pitfalls**:
   - **Brute Force**: Avoid the brute force approach in interviews, as it demonstrates a lack of optimization awareness.
   - **Pointer Movement**: Ensure you move the correct pointer (the one pointing to the shorter line). Moving the taller line will never increase the area.
   - **Edge Cases**: Always consider edge cases like small arrays or arrays with duplicate values.

3. **Alternative Approaches**:
   - **Divide and Conquer**: This problem is not well-suited for divide and conquer, as the maximum area can span the entire array.
   - **Dynamic Programming**: Not applicable here, as there are no overlapping subproblems.

4. **Optimization Discussion**:
   - The two-pointer approach is optimal because it explores all potential maximum areas without redundant checks.
   - The key insight is that the area is limited by the shorter line, so we can safely discard the shorter line in each step.

---

# ✅ Conclusion

The **Container With Most Water** problem is a great example of how the two-pointer technique can optimize a brute force solution from O(n²) to O(n). The optimal solution efficiently narrows down the search space by leveraging the insight that the area is limited by the shorter line. This problem is a must-know for technical interviews, as it tests both algorithmic thinking and optimization skills.

**Key Takeaway**: Always look for opportunities to use the two-pointer technique when dealing with array-based problems that involve finding pairs or ranges. The technique can significantly reduce time complexity and demonstrate your ability to optimize solutions.