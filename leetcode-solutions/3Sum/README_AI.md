```markdown
# 📌 3Sum

---

# 📝 Problem Statement

Given an integer array `nums`, return all the **unique triplets** `[nums[i], nums[j], nums[k]]` such that:
- `i != j`, `i != k`, and `j != k`
- `nums[i] + nums[j] + nums[k] == 0`

**Constraints:**
- `0 <= nums.length <= 3000`
- `-10^5 <= nums[i] <= 10^5`

**Note:** The solution set must not contain duplicate triplets.

---

# 💡 Intuition

The problem requires finding all unique triplets in an array that sum to zero. A brute-force approach would involve checking all possible triplets, but this is inefficient for large arrays. The key insight is to **sort the array** and use a **two-pointer technique** to reduce the time complexity from O(n³) to O(n²). Sorting helps in efficiently skipping duplicates and narrowing down the search space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute-force approach involves checking all possible triplets in the array using three nested loops. For each triplet, we check if the sum equals zero. To avoid duplicate triplets, we sort the triplet and store it in a set.

---

## 🔹 Algorithm

1. Initialize an empty set to store unique triplets.
2. Use three nested loops to iterate through all possible triplets:
   - Outer loop: `i` from `0` to `n-1`
   - Middle loop: `j` from `i+1` to `n-1`
   - Inner loop: `k` from `j+1` to `n-1`
3. For each triplet `[nums[i], nums[j], nums[k]]`, check if `nums[i] + nums[j] + nums[k] == 0`.
4. If the sum is zero, sort the triplet and add it to the set to avoid duplicates.
5. Convert the set to a list and return it.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [-1, 0, 1, 2, -1, -4]`

| Step | i | j | k | Triplet | Sum | Action |
|------|---|---|---|---------|-----|--------|
| 1    | 0 | 1 | 2 | [-1, 0, 1] | 0 | Add to result |
| 2    | 0 | 1 | 3 | [-1, 0, 2] | 1 | Skip |
| 3    | 0 | 1 | 4 | [-1, 0, -1] | -2 | Skip |
| 4    | 0 | 1 | 5 | [-1, 0, -4] | -5 | Skip |
| 5    | 0 | 2 | 3 | [-1, 1, 2] | 2 | Skip |
| 6    | 0 | 2 | 4 | [-1, 1, -1] | -1 | Skip |
| 7    | 0 | 2 | 5 | [-1, 1, -4] | -4 | Skip |
| 8    | 0 | 3 | 4 | [-1, 2, -1] | 0 | Add to result (duplicate) |
| 9    | 0 | 3 | 5 | [-1, 2, -4] | -3 | Skip |
| 10   | 0 | 4 | 5 | [-1, -1, -4] | -6 | Skip |
| ...  | ... | ... | ... | ... | ... | ... |

**Final Result:** `[[-1, 0, 1], [-1, -1, 2]]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n³) |
| Space Complexity | O(n) (for storing results) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves sorting the array and using a two-pointer technique to find triplets that sum to zero. This reduces the time complexity to O(n²). The key steps are:
1. Sort the array.
2. Iterate through the array with a fixed element `nums[i]`.
3. Use two pointers (`left` and `right`) to find pairs that sum to `-nums[i]`.
4. Skip duplicates to avoid redundant triplets.

---

## 🔹 Why This Works

Sorting the array allows us to use the two-pointer technique efficiently. By fixing one element and using two pointers to find the other two, we can reduce the problem to a two-sum problem for each element. Skipping duplicates ensures that the solution set contains only unique triplets.

---

## 🔹 Algorithm

1. Sort the array.
2. Initialize an empty list to store the result.
3. Iterate through the array with index `i` from `0` to `n-3`:
   - Skip duplicates for `nums[i]`.
   - Initialize two pointers: `left = i + 1` and `right = n - 1`.
   - While `left < right`:
     - Calculate the sum `nums[i] + nums[left] + nums[right]`.
     - If the sum is zero, add the triplet to the result and skip duplicates for `left` and `right`.
     - If the sum is less than zero, increment `left`.
     - If the sum is greater than zero, decrement `right`.
4. Return the result.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [-1, 0, 1, 2, -1, -4]`
**Sorted Array:** `[-4, -1, -1, 0, 1, 2]`

| Iteration | i | left | right | Sum | Action |
|-----------|---|------|-------|-----|--------|
| 1         | 0 | 1    | 5     | -4 + -1 + 2 = -3 | Sum < 0 → left++ |
| 2         | 0 | 2    | 5     | -4 + -1 + 2 = -3 | Sum < 0 → left++ |
| 3         | 0 | 3    | 5     | -4 + 0 + 2 = -2 | Sum < 0 → left++ |
| 4         | 0 | 4    | 5     | -4 + 1 + 2 = -1 | Sum < 0 → left++ |
| 5         | 1 | 2    | 5     | -1 + -1 + 2 = 0 | Add [-1, -1, 2] → Skip duplicates → left=3, right=4 |
| 6         | 1 | 3    | 4     | -1 + 0 + 1 = 0 | Add [-1, 0, 1] → Skip duplicates → left=4, right=3 (terminate) |

**Final Result:** `[[-1, -1, 2], [-1, 0, 1]]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) (ignoring space for result) |

---

# 🔍 Edge Cases

- **Empty Array:** `nums = []` → Return `[]`.
- **Single Element:** `nums = [0]` → Return `[]`.
- **All Zeros:** `nums = [0, 0, 0]` → Return `[[0, 0, 0]]`.
- **No Triplets:** `nums = [1, 2, 3]` → Return `[]`.
- **Duplicates:** `nums = [-1, -1, 0, 1]` → Return `[[-1, 0, 1]]`.
- **Negative Values:** `nums = [-2, -1, 0, 1, 2]` → Return `[[-2, 0, 2], [-1, 0, 1]]`.

---

# 📚 Key Takeaways

1. **Sorting is Key:** Sorting the array enables efficient two-pointer traversal.
2. **Skip Duplicates:** Always skip duplicate values to avoid redundant triplets.
3. **Two-Pointer Technique:** Reduces time complexity from O(n³) to O(n²).
4. **Early Termination:** The two-pointer approach allows early termination for unsolvable cases.

---

# 🚀 Interview Tips

- **Follow-Up:** How would you handle the problem if the input is a stream of numbers?
- **Pitfall:** Forgetting to skip duplicates can lead to incorrect results.
- **Alternative:** HashSet can be used to store seen values, but sorting is more efficient for this problem.
- **Optimization:** The optimal approach is preferred due to its lower time complexity.

---

# ✅ Conclusion

The optimal solution leverages sorting and the two-pointer technique to efficiently find all unique triplets that sum to zero. This approach significantly reduces the time complexity compared to the brute-force method, making it suitable for large input sizes. The key takeaway is the importance of sorting and careful handling of duplicates to ensure correctness and efficiency.
```