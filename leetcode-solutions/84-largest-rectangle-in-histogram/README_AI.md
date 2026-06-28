# 📌 Largest Rectangle in Histogram

---

# 📝 Problem Statement

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

**Constraints:**
- `1 <= heights.length <= 10^5`
- `0 <= heights[i] <= 10^4`

---

# 💡 Intuition

The key insight is that for each bar, we need to find the largest rectangle that can be formed with that bar as the smallest bar in the rectangle. We can efficiently solve this using a stack to keep track of indices of bars in increasing order of their heights. This allows us to calculate the width of the rectangle that can be formed with each bar as the smallest bar.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every possible rectangle in the histogram. For each bar, we consider it as the smallest bar in the rectangle and expand to the left and right to find the maximum width that can form a rectangle with this bar as the smallest bar.

---

## 🔹 Algorithm

1. Initialize `max_area` to 0.
2. For each bar at index `i`:
   - Initialize `left` to `i` and `right` to `i`.
   - Expand `left` to the left as long as the bar height is greater than or equal to the current bar's height.
   - Expand `right` to the right as long as the bar height is greater than or equal to the current bar's height.
   - Calculate the area of the rectangle formed by the current bar and the expanded width.
   - Update `max_area` if the current area is larger.
3. Return `max_area`.

---

## 🔹 Code

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max_area = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;

            while (left > 0 && heights[left - 1] >= heights[i]) {
                left--;
            }

            while (right < n - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }

            int width = right - left + 1;
            int area = heights[i] * width;
            max_area = Math.max(max_area, area);
        }

        return max_area;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the input `[2, 1, 5, 6, 2, 3]`.

| Iteration | Current Bar | Left | Right | Width | Area | Max Area |
|-----------|-------------|------|-------|-------|------|----------|
| 1         | 2           | 0    | 0     | 1     | 2    | 2        |
| 2         | 1           | 1    | 1     | 1     | 1    | 2        |
| 3         | 5           | 1    | 3     | 3     | 15   | 15       |
| 4         | 6           | 2    | 3     | 2     | 12   | 15       |
| 5         | 2           | 4    | 4     | 1     | 2    | 15       |
| 6         | 3           | 4    | 5     | 2     | 6    | 15       |

The final `max_area` is 15.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a stack to keep track of indices of bars in increasing order of their heights. This allows us to efficiently calculate the width of the rectangle that can be formed with each bar as the smallest bar.

---

## 🔹 Why This Works

By maintaining a stack of indices, we ensure that each bar is processed in a way that allows us to calculate the maximum area rectangle that can be formed with it as the smallest bar. The stack helps us keep track of the indices of bars that are greater than or equal to the current bar's height, allowing us to calculate the width of the rectangle efficiently.

---

## 🔹 Algorithm

1. Initialize `max_area` to 0 and a stack.
2. Iterate through each bar in the histogram:
   - While the stack is not empty and the current bar's height is less than the height of the bar at the top of the stack:
     - Pop the top of the stack to get the height of the rectangle.
     - Calculate the width of the rectangle by subtracting the current index from the index stored in the stack (or the current index if the stack is empty).
     - Calculate the area of the rectangle and update `max_area` if the current area is larger.
   - Push the current index onto the stack.
3. After processing all bars, process any remaining bars in the stack in the same manner.
4. Return `max_area`.

---

## 🔹 Code

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxarea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = height * width;
                maxarea = Math.max(area, maxarea);
            }
            stack.push(i);
        }
        return maxarea;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input `[2, 1, 5, 6, 2, 3]`.

| Iteration | Current Bar | Stack | Action | Area | Max Area |
|-----------|-------------|-------|--------|------|----------|
| 1         | 2           | [0]   | Push 0 | -    | 0        |
| 2         | 1           | [0, 1]| Push 1 | -    | 0        |
| 3         | 5           | [0, 1, 2]| Push 2 | -    | 0        |
| 4         | 6           | [0, 1, 2, 3]| Push 3 | -    | 0        |
| 5         | 2           | [0, 1, 2, 4]| Pop 3, calculate area 6*1=6 | 6    | 6        |
| 6         | 3           | [0, 1, 2, 4, 5]| Push 5 | -    | 6        |
| 7         | 0           | [0, 1, 2, 4, 5]| Pop 5, calculate area 3*1=3 | 3    | 6        |
| 8         | -           | [0, 1, 2, 4]| Pop 4, calculate area 2*2=4 | 4    | 6        |
| 9         | -           | [0, 1, 2]| Pop 2, calculate area 5*3=15 | 15   | 15       |
| 10        | -           | [0, 1]| Pop 1, calculate area 1*4=4 | 4    | 15       |
| 11        | -           | [0]| Pop 0, calculate area 2*5=10 | 10   | 15       |

The final `max_area` is 15.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty input array.
- Single bar in the histogram.
- All bars have the same height.
- Bars are in strictly increasing order.
- Bars are in strictly decreasing order.
- Bars with duplicate heights.

---

# 📚 Key Takeaways

- The brute force approach is straightforward but inefficient for large inputs.
- The optimal approach uses a stack to efficiently calculate the maximum area rectangle.
- Understanding the use of a stack to keep track of indices is crucial for solving this problem efficiently.
- The optimal approach ensures that each bar is processed in linear time, making it suitable for large inputs.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Explain how the stack helps in efficiently calculating the maximum area rectangle.
- Be prepared to discuss the time and space complexity of both approaches.
- Practice dry runs with different inputs to ensure a clear understanding of the algorithm.

---

# ✅ Conclusion

The optimal approach using a stack is the most efficient solution for the Largest Rectangle in Histogram problem. It ensures that each bar is processed in linear time, making it suitable for large inputs. Understanding the use of a stack to keep track of indices is crucial for solving this problem efficiently.