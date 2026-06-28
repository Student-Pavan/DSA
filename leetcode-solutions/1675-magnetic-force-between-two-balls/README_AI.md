# 📌 1675. Magnetic Force Between Two Balls

---

# 📝 Problem Statement

In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has `n` empty baskets, the `i-th` basket is at `position[i]`, Morty has `m` balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.

Rick stated that magnetic force between two different balls at positions `x` and `y` is `|x - y|`.

Given the integer array `position` and the integer `m`. Return the required force.

**Constraints:**
- `n == position.length`
- `2 <= n <= 10^5`
- `1 <= position[i] <= 10^9`
- All integers in `position` are distinct.
- `2 <= m <= position.length`

---

# 💡 Intuition

The problem requires finding the maximum minimum distance between `m` balls placed in `n` baskets. This is a classic binary search problem where we need to find the largest minimum distance that can be maintained between the balls.

The key insight is that the positions can be sorted, and we can use binary search to determine the maximum minimum distance. The binary search helps us efficiently check whether a given distance is feasible for placing all `m` balls.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible distances from the smallest possible distance (1) to the largest possible distance (difference between the first and last position). For each distance, we check if it's possible to place all `m` balls with at least that distance between them. The largest distance for which this is possible is our answer.

## 🔹 Algorithm

1. Sort the positions array.
2. Initialize the answer to 0.
3. Iterate from the smallest possible distance (1) to the largest possible distance (positions[n-1] - positions[0]).
4. For each distance, check if it's possible to place all `m` balls with at least that distance between them.
5. If it's possible, update the answer to the current distance.
6. Return the answer.

## 🔹 Code

```java
import java.util.Arrays;

class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int ans = 0;

        for (int d = 1; d <= position[n - 1] - position[0]; d++) {
            if (canPlace(position, m, d)) {
                ans = d;
            }
        }
        return ans;
    }

    private boolean canPlace(int[] position, int m, int d) {
        int count = 1;
        int last = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= d) {
                count++;
                last = position[i];
            }
        }
        return count >= m;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `position = [1, 2, 3, 4, 7]` and `m = 3`.

| Step | Distance (d) | Can Place? | Action | Answer |
|------|--------------|------------|--------|--------|
| 1    | 1            | Yes        | Update answer to 1 | 1 |
| 2    | 2            | Yes        | Update answer to 2 | 2 |
| 3    | 3            | Yes        | Update answer to 3 | 3 |
| 4    | 4            | Yes        | Update answer to 4 | 4 |
| 5    | 5            | No         | Do nothing | 4 |
| 6    | 6            | No         | Do nothing | 4 |

The maximum minimum distance is 4.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log n + n * D) where D is the maximum distance between the first and last position. |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to efficiently determine the maximum minimum distance. We sort the positions and then perform a binary search between the smallest possible distance (1) and the largest possible distance (difference between the first and last position). For each midpoint distance, we check if it's possible to place all `m` balls with at least that distance between them. If it's possible, we try a larger distance; otherwise, we try a smaller distance.

## 🔹 Why This Works

Binary search reduces the time complexity from O(n * D) to O(n log D), making it much more efficient, especially for large values of D.

## 🔹 Algorithm

1. Sort the positions array.
2. Initialize the answer to 0.
3. Set the low to 1 and high to the difference between the first and last position.
4. While low is less than or equal to high:
   - Calculate the mid distance.
   - If it's possible to place all `m` balls with at least mid distance, update the answer to mid and set low to mid + 1.
   - Otherwise, set high to mid - 1.
5. Return the answer.

## 🔹 Code

```java
import java.util.Arrays;

class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length - 1;
        int low = 1;
        int high = position[n] - position[0];
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(position, m, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean canPlace(int[] position, int m, int mid) {
        int last = position[0];
        int count = 1;

        for (int i = 1; i < position.length; i++) {
            if (position[i] - last >= mid) {
                count++;
                last = position[i];
            }
        }
        return count >= m;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `position = [1, 2, 3, 4, 7]` and `m = 3`.

| Step | Low | High | Mid | Can Place? | Action | Answer |
|------|-----|------|-----|------------|--------|--------|
| 1    | 1   | 6    | 3   | Yes        | Update answer to 3, set low to 4 | 3 |
| 2    | 4   | 6    | 5   | No         | Set high to 4 | 3 |
| 3    | 4   | 4    | 4   | Yes        | Update answer to 4, set low to 5 | 4 |
| 4    | 5   | 4    | -   | -          | Exit loop | 4 |

The maximum minimum distance is 4.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log n + n log D) where D is the maximum distance between the first and last position. |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Ball:** If `m = 1`, the answer is 0 since there are no pairs.
- **All Balls in One Position:** If all positions are the same, the answer is 0.
- **Large Positions:** The algorithm should handle large position values efficiently.
- **Minimum Distance:** If the minimum distance is required, the answer is 1.

---

# 📚 Key Takeaways

- Binary search is an efficient way to solve problems involving finding a maximum or minimum value with a certain condition.
- Sorting the positions array is crucial for efficiently checking the feasibility of placing the balls.
- The `canPlace` function helps in checking if a given distance is feasible for placing all `m` balls.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss the time and space complexity of the solution. Ask about handling edge cases.
- **Common Pitfalls:** Ensure the positions array is sorted before performing binary search. Be careful with the boundary conditions in the binary search.
- **Alternative Approaches:** Discuss the brute force approach and its limitations. Explain why binary search is more efficient.

---

# ✅ Conclusion

The optimal approach using binary search is more efficient and scalable, especially for large input sizes. The key insight is to use binary search to efficiently determine the maximum minimum distance, reducing the time complexity significantly.