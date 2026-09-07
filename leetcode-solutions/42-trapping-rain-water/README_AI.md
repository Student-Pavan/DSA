# 📌 Problem Name
42. Trapping Rain Water

---

# 📝 Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Objective**: Calculate the total amount of rainwater trapped between the bars.

**Input**: An integer array `height` representing the elevation of bars.

**Output**: An integer representing the total units of water trapped.

**Constraints**:
- `n == height.length`
- `1 <= n <= 2 * 10^4`
- `0 <= height[i] <= 10^5`

---

# 💡 Intuition
The key insight is that water trapped at any point depends on the tallest bars to its left and right. The amount of water trapped at a given index is determined by the minimum of these two maximum heights minus the height of the current bar.

The optimal approach efficiently tracks these maximum heights using two pointers, moving from both ends towards the center, which allows us to compute the trapped water in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach
For each bar, find the maximum height to its left and the maximum height to its right. The amount of water trapped at that bar is the minimum of these two maximum heights minus the height of the current bar. Sum this value for all bars to get the total trapped water.

---

## 🔹 Algorithm
1. Initialize total water trapped to 0.
2. For each bar at index `i`:
   a. Find the maximum height to the left of `i`.
   b. Find the maximum height to the right of `i`.
   c. The water trapped at `i` is `min(left_max, right_max) - height[i]`.
   d. Add this value to the total water.
3. Return the total water trapped.

---

## 🔹 Code
```java
class Solution {
    public int trap(int[] height) {
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            water += Math.min(leftMax, rightMax) - height[i];
        }
        return water;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the input `[0,1,0,2,1,0,1,3,2,1,2,1]`.

| Iteration | Current Bar | Left Max | Right Max | Water Trapped | Total Water |
|-----------|-------------|----------|-----------|---------------|--------------|
| 0         | 0           | 0        | 3         | 0 - 0 = 0     | 0            |
| 1         | 1           | 1        | 3         | 1 - 1 = 0     | 0            |
| 2         | 0           | 1        | 3         | 1 - 0 = 1     | 1            |
| 3         | 2           | 2        | 3         | 2 - 2 = 0     | 1            |
| 4         | 1           | 2        | 3         | 2 - 1 = 1     | 2            |
| 5         | 0           | 2        | 3         | 2 - 0 = 2     | 4            |
| 6         | 1           | 3        | 3         | 3 - 1 = 2     | 6            |
| 7         | 3           | 3        | 3         | 3 - 3 = 0     | 6            |
| 8         | 2           | 3        | 3         | 3 - 2 = 1     | 7            |
| 9         | 1           | 3        | 3         | 3 - 1 = 2     | 9            |
| 10        | 2           | 3        | 2         | 2 - 2 = 0     | 9            |
| 11        | 1           | 3        | 2         | 2 - 1 = 1     | 10           |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach
Use two pointers, one starting at the beginning (`left`) and one at the end (`right`) of the array. Track the maximum heights encountered from both ends. For each step, move the pointer pointing to the smaller maximum height, compute the water trapped, and update the maximum height if necessary.

---

## 🔹 Why This Works
This approach efficiently computes the trapped water in a single pass by leveraging the fact that the water trapped at any point is determined by the minimum of the maximum heights to its left and right. By moving the pointer with the smaller maximum height, we ensure that we are always working with the limiting factor, which allows us to compute the trapped water accurately.

---

## 🔹 Algorithm
1. Initialize `left` to 0 and `right` to `height.length - 1`.
2. Initialize `leftMax` to `height[left]` and `rightMax` to `height[right]`.
3. Initialize `water` to 0.
4. While `left < right`:
   a. If `height[left] < height[right]`:
      i. Move `left` to the right.
      ii. Update `leftMax` to the maximum of `leftMax` and `height[left]`.
      iii. Add `leftMax - height[left]` to `water`.
   b. Else:
      i. Move `right` to the left.
      ii. Update `rightMax` to the maximum of `rightMax` and `height[right]`.
      iii. Add `rightMax - height[right]` to `water`.
5. Return `water`.

---

## 🔹 Code
```java
class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
            }
        }
        return water;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input `[0,1,0,2,1,0,1,3,2,1,2,1]`.

| Step | Left | Right | Left Max | Right Max | Action | Water Added | Total Water |
|------|------|-------|----------|-----------|--------|-------------|--------------|
| 1    | 0    | 11    | 0        | 1         | Move left | 0 - 0 = 0   | 0            |
| 2    | 1    | 11    | 1        | 1         | Move left | 1 - 1 = 0   | 0            |
| 3    | 2    | 11    | 1        | 1         | Move left | 1 - 0 = 1   | 1            |
| 4    | 3    | 11    | 2        | 1         | Move left | 2 - 2 = 0   | 1            |
| 5    | 4    | 11    | 2        | 1         | Move right | 2 - 1 = 1   | 2            |
| 6    | 4    | 10    | 2        | 2         | Move right | 2 - 0 = 2   | 4            |
| 7    | 4    | 9     | 2        | 2         | Move right | 2 - 1 = 1   | 5            |
| 8    | 4    | 8     | 2        | 3         | Move right | 3 - 3 = 0   | 5            |
| 9    | 4    | 7     | 2        | 3         | Move right | 3 - 2 = 1   | 6            |
| 10   | 4    | 6     | 2        | 3         | Move right | 3 - 1 = 2   | 8            |
| 11   | 5    | 6     | 2        | 3         | Move left  | 2 - 0 = 2   | 10           |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Input**: `[]` → Output: `0`
- **Single Bar**: `[5]` → Output: `0`
- **All Bars Same Height**: `[2,2,2,2]` → Output: `0`
- **Increasing Heights**: `[1,2,3,4,5]` → Output: `0`
- **Decreasing Heights**: `[5,4,3,2,1]` → Output: `0`
- **Alternating Heights**: `[1,0,1,0,1]` → Output: `2`
- **Large Input**: `[100000, 0, 100000]` → Output: `100000`

---

# 📚 Key Takeaways

- The brute force approach has a time complexity of O(n^2) due to nested loops, making it inefficient for large inputs.
- The optimal approach uses a two-pointer technique to achieve O(n) time complexity with O(1) space complexity.
- The key insight is that the water trapped at any point depends on the minimum of the maximum heights to its left and right.
- The two-pointer technique efficiently tracks these maximum heights and computes the trapped water in a single pass.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem with O(n) time and O(n) space complexity?
  - How would you handle very large inputs efficiently?
- **Common Pitfalls**:
  - Forgetting to update the maximum heights.
  - Incorrectly calculating the trapped water.
  - Not considering edge cases.
- **Alternative Approaches**:
  - Using a stack to keep track of the indices of the bars.
  - Using dynamic programming to store the maximum heights to the left and right of each bar.
- **Optimization Discussions**:
  - The two-pointer technique is optimal for this problem.
  - The stack approach can be useful for other problems but is less efficient here.

---

# ✅ Conclusion

The optimal approach using two pointers is the most efficient solution for the "Trapping Rain Water" problem. It efficiently computes the trapped water in a single pass with O(n) time complexity and O(1) space complexity. Understanding the key insight that water trapped at any point depends on the minimum of the maximum heights to its left and right is crucial for solving this problem optimally.