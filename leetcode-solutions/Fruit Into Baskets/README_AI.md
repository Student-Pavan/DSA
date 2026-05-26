```markdown
# 🍎 Fruit Into Baskets

---

# 📝 Problem Statement

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array `fruits` where `fruits[i]` is the type of fruit the `i-th` tree produces.

You want to collect as much fruit as possible. However, the farm has the following rules:

1. You only have **two baskets**, and each basket can only hold a **single type of fruit**. There is no limit on the amount of fruit each basket can hold.
2. Starting from any tree of your choice, you must pick **exactly one fruit from every tree** (including the start tree) while moving to the right. You cannot skip any trees.
3. Once you reach a tree with fruit that cannot fit into your baskets, you must stop.

Given the integer array `fruits`, return the **maximum number of fruits you can pick**.

### Example 1:
**Input:** `fruits = [1,2,1]`
**Output:** `3`
**Explanation:** We can pick from all 3 trees.

### Example 2:
**Input:** `fruits = [0,1,2,2]`
**Output:** `3`
**Explanation:** We can pick from trees [1,2,2]. If we start at the first tree, we only collect [0,1].

### Example 3:
**Input:** `fruits = [1,2,3,2,2]`
**Output:** `4`
**Explanation:** We can pick from trees [2,3,2,2]. If we start at the second tree, we collect [1,2,3,2].

### Constraints:
- `1 <= fruits.length <= 10^5`
- `0 <= fruits[i] < fruits.length`

---

# 💡 Intuition

This problem is a classic **sliding window** variation where we need to find the longest contiguous subarray containing **at most two distinct elements**. The key insight is recognizing that the problem reduces to maintaining a window of fruits where we only have two types of fruits at any time.

The optimal approach involves using a sliding window with a hash map (or frequency array) to track the count of each fruit type within the current window. As we expand the window to the right, we adjust the left boundary whenever the number of distinct fruits exceeds two.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every possible subarray and counting the number of fruits collected while ensuring that no more than two distinct types are present. For each starting index, we expand the window to the right until we encounter a third distinct fruit type, then record the length of the valid window.

This approach is straightforward but inefficient due to its O(n²) time complexity, making it unsuitable for large inputs.

---

## 🔹 Algorithm

1. Initialize `maxFruits = 0`.
2. For each starting index `i` from `0` to `n-1`:
   - Initialize a set to track distinct fruit types.
   - Initialize `currentFruits = 0`.
   - For each ending index `j` from `i` to `n-1`:
     - Add `fruits[j]` to the set.
     - If the set size exceeds 2, break.
     - Increment `currentFruits`.
     - Update `maxFruits = max(maxFruits, currentFruits)`.
3. Return `maxFruits`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalFruit(int[] fruits) {
        int maxFruits = 0;
        int n = fruits.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> basket = new HashSet<>();
            int currentFruits = 0;

            for (int j = i; j < n; j++) {
                basket.add(fruits[j]);
                if (basket.size() > 2) {
                    break;
                }
                currentFruits++;
                maxFruits = Math.max(maxFruits, currentFruits);
            }
        }

        return maxFruits;
    }
}
```

---

## 🔹 Dry Run

**Input:** `fruits = [1, 2, 1]`

| Step | Start (i) | End (j) | Current Window | Distinct Fruits | Current Count | Max Fruits |
|------|-----------|---------|----------------|------------------|---------------|------------|
| 1    | 0         | 0       | [1]            | {1}              | 1             | 1          |
| 2    | 0         | 1       | [1, 2]         | {1, 2}           | 2             | 2          |
| 3    | 0         | 2       | [1, 2, 1]      | {1, 2}           | 3             | 3          |
| 4    | 1         | 1       | [2]            | {2}              | 1             | 3          |
| 5    | 1         | 2       | [2, 1]         | {1, 2}           | 2             | 3          |
| 6    | 2         | 2       | [1]            | {1}              | 1             | 3          |

**Output:** `3`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n²)       |
| Space Complexity| O(1)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a **sliding window** technique with a hash map to track the frequency of each fruit type in the current window. We maintain two pointers, `left` and `right`, representing the current window boundaries. As we move the `right` pointer to expand the window, we adjust the `left` pointer whenever the number of distinct fruits exceeds two. This ensures that we always have a valid window with at most two distinct fruit types.

---

## 🔹 Why This Works

- **Efficiency:** The sliding window approach processes each element exactly once, resulting in O(n) time complexity.
- **Correctness:** By tracking the frequency of each fruit type, we can efficiently determine when to shrink the window from the left.
- **Optimality:** This approach ensures that we explore all possible valid windows without redundant computations.

---

## 🔹 Algorithm

1. Initialize `left = 0`, `maxFruits = 0`, and a hash map `frequency` to track fruit counts.
2. Iterate over the array with `right` from `0` to `n-1`:
   - Increment the count of `fruits[right]` in `frequency`.
   - While the size of `frequency` exceeds 2:
     - Decrement the count of `fruits[left]` in `frequency`.
     - If the count becomes zero, remove `fruits[left]` from `frequency`.
     - Increment `left`.
   - Update `maxFruits = max(maxFruits, right - left + 1)`.
3. Return `maxFruits`.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalFruit(int[] fruits) {
        int left = 0;
        int maxFruits = 0;
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            frequency.put(fruits[right], frequency.getOrDefault(fruits[right], 0) + 1);

            while (frequency.size() > 2) {
                frequency.put(fruits[left], frequency.get(fruits[left]) - 1);
                if (frequency.get(fruits[left]) == 0) {
                    frequency.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `fruits = [1, 2, 1, 2, 2]`

| Step | Right | Left | Current Window | Frequency Map | Action                     | Max Fruits |
|------|-------|------|----------------|---------------|----------------------------|------------|
| 1    | 0     | 0    | [1]            | {1: 1}        | Expand window              | 1          |
| 2    | 1     | 0    | [1, 2]         | {1: 1, 2: 1}  | Expand window              | 2          |
| 3    | 2     | 0    | [1, 2, 1]      | {1: 2, 2: 1}  | Expand window              | 3          |
| 4    | 3     | 1    | [2, 1, 2]      | {1: 1, 2: 2}  | Shrink left (remove 1)     | 3          |
| 5    | 4     | 1    | [2, 1, 2, 2]   | {1: 1, 2: 3}  | Expand window              | 4          |

**Output:** `4`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

- **Empty Input:** `fruits = []` → Return `0`.
- **Single Fruit Type:** `fruits = [1, 1, 1]` → Return `3`.
- **All Unique Fruits:** `fruits = [1, 2, 3]` → Return `2`.
- **Large Input:** `fruits = [1, 1, 2, 2, 3, 3, ...]` (10^5 elements) → Ensure O(n) solution handles it efficiently.
- **Alternating Fruits:** `fruits = [1, 2, 1, 2, 1]` → Return `5`.

---

# 📚 Key Takeaways

- **Sliding Window Pattern:** This problem is a classic example of the sliding window technique for finding the longest subarray with at most `k` distinct elements.
- **Hash Map for Tracking:** Using a hash map to track frequencies allows efficient window adjustments.
- **Optimization Insight:** The optimal solution reduces the problem to a single pass through the array, making it highly efficient for large inputs.

---

# 🚀 Interview Tips

- **Follow-Up:** What if the number of baskets changes? Extend the solution to handle `k` distinct elements.
- **Common Pitfall:** Forgetting to remove the fruit type from the map when its count drops to zero, leading to incorrect window adjustments.
- **Alternative Approach:** Using a frequency array instead of a hash map for better performance in constrained environments.
- **Optimization Discussion:** Highlight the trade-off between time and space complexity in brute force vs. optimal solutions.

---

# ✅ Conclusion

The **optimal sliding window approach** efficiently solves the problem in O(n) time with O(1) space, making it suitable for large inputs. The key insight is recognizing that the problem reduces to finding the longest subarray with at most two distinct elements, a common pattern in array and string problems. This approach is both intuitive and highly performant, making it ideal for technical interviews.
```