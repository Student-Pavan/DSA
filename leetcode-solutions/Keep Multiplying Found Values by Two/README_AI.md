# 📌 Keep Multiplying Found Values by Two

**LeetCode Problem #2154**

---

# 📝 Problem Statement

You are given an array of integers `nums`. You are also given an integer `original` which is the first number that needs to be searched for in `nums`.

You then do the following steps:
1. If `original` is found in `nums`, multiply it by two (i.e., set `original = 2 * original`).
2. Otherwise, stop the process.
3. Repeat this process with the new number as long as you keep finding the number.

Return the **final value** of `original`.

### Constraints:
- `1 <= nums.length <= 1000`
- `1 <= nums[i], original <= 1000`

---

# 💡 Intuition

The problem requires repeatedly searching for a value in an array and doubling it if found. The key observation is that we need to check for the presence of `original` in `nums` repeatedly until it's no longer found.

The brute force approach would involve repeatedly scanning the array for `original` and doubling it each time. However, this can be optimized by pre-processing the array into a set for O(1) lookups, reducing the time complexity significantly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Starting with the given `original` value.
2. Repeatedly scanning the entire array to check if `original` exists.
3. If found, double `original` and repeat the scan.
4. If not found, return the current value of `original`.

This approach is straightforward but inefficient due to repeated linear scans of the array.

---

## 🔹 Algorithm

1. Initialize `current = original`.
2. While `true`:
   - Search for `current` in `nums`.
   - If found, set `current = current * 2`.
   - If not found, return `current`.

---

## 🔹 Code

```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        int current = original;
        while (true) {
            boolean found = false;
            for (int num : nums) {
                if (num == current) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return current;
            }
            current *= 2;
        }
    }
}
```

---

## 🔹 Dry Run

**Example:** `nums = [5, 3, 6, 1, 12]`, `original = 3`

| Step | Current Value | Found in Array? | Action          | Result |
|------|---------------|------------------|-----------------|--------|
| 1    | 3             | Yes              | Double current  | 6      |
| 2    | 6             | Yes              | Double current  | 12     |
| 3    | 12            | Yes              | Double current  | 24     |
| 4    | 24            | No               | Return 24       | 24     |

---

## 🔹 Complexity Analysis

| Complexity       | Value          |
|------------------|----------------|
| Time Complexity  | O(n * k)       |
| Space Complexity | O(1)           |

Where:
- `n` = length of `nums`
- `k` = number of times `original` is doubled (worst case: log(max(nums)))

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages a **hash set** for O(1) lookups:
1. Convert the array `nums` into a set for constant-time membership checks.
2. Start with `original` and repeatedly check if it exists in the set.
3. If found, double `original` and repeat.
4. If not found, return the current value.

This reduces the time complexity from O(n * k) to O(n + k), where `k` is the number of doublings.

---

## 🔹 Why This Works

- **Hash Set Lookup:** Checking for the presence of a value in a set is O(1) on average.
- **Efficiency:** Pre-processing the array into a set takes O(n) time, but subsequent lookups are O(1).
- **Correctness:** The logic remains the same as the brute force approach, but the lookup is optimized.

---

## 🔹 Algorithm

1. Convert `nums` into a hash set.
2. Initialize `current = original`.
3. While `current` exists in the set:
   - Double `current`.
4. Return `current`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int current = original;
        while (numSet.contains(current)) {
            current *= 2;
        }
        return current;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `nums = [5, 3, 6, 1, 12]`, `original = 3`

| Step | Current Value | Exists in Set? | Action          | Result |
|------|---------------|-----------------|-----------------|--------|
| 1    | 3             | Yes             | Double current  | 6      |
| 2    | 6             | Yes             | Double current  | 12     |
| 3    | 12            | Yes             | Double current  | 24     |
| 4    | 24            | No              | Return 24       | 24     |

---

## 🔹 Complexity Analysis

| Complexity       | Value          |
|------------------|----------------|
| Time Complexity  | O(n + k)       |
| Space Complexity | O(n)           |

Where:
- `n` = length of `nums`
- `k` = number of times `original` is doubled

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation                                                                 |
|-------------------------------|-----------------|-----------------------------------------------------------------------------|
| `nums = [2, 4, 8]`, `original = 2` | 16              | `original` is doubled until it exceeds the largest value in `nums`.         |
| `nums = [1]`, `original = 1`  | 2               | Single element array where `original` is found once.                        |
| `nums = [1, 3, 5]`, `original = 2` | 2               | `original` is not found in the array.                                       |
| `nums = []`, `original = 1`   | 1               | Empty array (though constraints say `nums.length >= 1`).                    |
| `nums = [1000]`, `original = 1000` | 2000            | Maximum constraint values.                                                  |

---

# 📚 Key Takeaways

1. **Brute Force vs. Optimized:** Always consider pre-processing data for faster lookups (e.g., using a set).
2. **Hash Set Efficiency:** Converting an array to a set allows O(1) membership checks, drastically improving performance.
3. **Problem Patterns:** This problem is a variation of "repeated search and update," where optimization hinges on reducing lookup time.
4. **Edge Cases:** Always test for cases where `original` is not in the array or is the only element.

---

# 🚀 Interview Tips

1. **Follow-Up Questions:**
   - What if the array is sorted? Can we use binary search for lookups?
   - How would you handle very large arrays (e.g., `nums.length = 10^6`)?
   - What if `original` could be negative?

2. **Common Pitfalls:**
   - Forgetting to handle the case where `original` is not in the array.
   - Using brute force without considering optimization opportunities.
   - Not accounting for integer overflow (though constraints limit this here).

3. **Alternative Approaches:**
   - Sorting the array and using binary search (O(n log n) pre-processing, O(log n) per lookup).
   - Using a frequency array (if constraints allow for small value ranges).

4. **Optimization Discussion:**
   - Highlight the trade-off between pre-processing time and lookup efficiency.
   - Discuss how the optimal solution scales with input size.

---

# ✅ Conclusion

The optimal solution leverages a hash set to reduce the time complexity from O(n * k) to O(n + k). This is a classic example of trading space for time, where pre-processing the array into a set allows for constant-time lookups. The brute force approach, while simple, is inefficient for larger inputs and serves as a good starting point for understanding the problem before optimizing.

**Key Insight:** Always consider pre-processing data structures (like sets or hash maps) when repeated lookups are required. This is a fundamental optimization technique in algorithm design.