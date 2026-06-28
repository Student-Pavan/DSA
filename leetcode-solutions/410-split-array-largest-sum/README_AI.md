# 410. Split Array Largest Sum

---

# 📝 Problem Statement

Given an array `nums` which consists of non-negative integers and an integer `k`, the task is to split the array into `k` or fewer subarrays such that the largest sum among these subarrays is minimized.

**Constraints:**
- `1 <= nums.length <= 1000`
- `0 <= nums[i] <= 10^6`
- `1 <= k <= min(50, nums.length)`

---

# 💡 Intuition

The key insight is recognizing that this problem can be efficiently solved using binary search. The solution involves determining the minimum possible maximum sum by checking if a given sum can be achieved with `k` or fewer subarrays.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible ways to split the array into `k` subarrays and calculating the maximum sum for each split. The minimum of these maximum sums is the answer.

## 🔹 Algorithm

1. Generate all possible splits of the array into `k` subarrays.
2. For each split, calculate the sum of each subarray.
3. Find the maximum sum for each split.
4. Track the minimum of these maximum sums.

## 🔹 Code

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[i][1] = prefixSum[i];
        }

        for (int j = 2; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x < i; x++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[x][j - 1], prefixSum[i] - prefixSum[x]));
                }
            }
        }

        return dp[n][k];
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `nums = [7,2,5,10,8]` and `k = 2`.

| Step | Subarrays | Max Sum | Min Max Sum |
|---|---|---|---|
| 1 | [7,2,5,10,8] | 32 | 32 |
| 2 | [7,2,5,10], [8] | 24 | 24 |
| 3 | [7,2,5], [10,8] | 22 | 22 |
| 4 | [7,2], [5,10,8] | 25 | 22 |
| 5 | [7], [2,5,10,8] | 25 | 22 |

The minimum maximum sum is 22.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2 * k) |
| Space Complexity | O(n * k) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to efficiently determine the minimum possible maximum sum. The idea is to perform a binary search on the possible values of the maximum sum, checking if a given sum can be achieved with `k` or fewer subarrays.

## 🔹 Why This Works

Binary search reduces the time complexity from O(n^2 * k) to O(n log(sum(nums))), where `sum(nums)` is the sum of all elements in the array. This is because the binary search operates on the range of possible sums, which is from the maximum element in the array to the sum of all elements.

## 🔹 Algorithm

1. Initialize `low` to the maximum element in the array and `high` to the sum of all elements.
2. Perform binary search on the range `[low, high]`.
3. For each midpoint `mid`, check if it's possible to split the array into `k` or fewer subarrays such that the sum of each subarray is less than or equal to `mid`.
4. Adjust `low` and `high` based on the result of the check.
5. The answer is the smallest `mid` for which the check passes.

## 🔹 Code

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;
        for (int page : nums) {
            low = Math.max(low, page);
            high += page;
        }
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean canSplit(int[] nums, int k, int max) {
        int sum = 0;
        int group = 1;
        for (int page : nums) {
            if (sum + page <= max) {
                sum += page;
            } else {
                group++;
                sum = page;
            }

        }

        return group <= k;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums = [7,2,5,10,8]` and `k = 2`.

| Step | Low | High | Mid | Can Split? | Action |
|---|---|---|---|---|---|
| 1 | 10 | 32 | 21 | No | low = 22 |
| 2 | 22 | 32 | 27 | No | low = 28 |
| 3 | 28 | 32 | 30 | No | low = 31 |
| 4 | 31 | 32 | 31 | No | low = 32 |
| 5 | 32 | 32 | 32 | Yes | ans = 32, high = 31 |
| 6 | 32 | 31 | - | - | Terminate |

The minimum maximum sum is 32.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log(sum(nums))) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Element:** `nums = [5]`, `k = 1` → Output: 5
- **All Elements Equal:** `nums = [2,2,2,2]`, `k = 2` → Output: 4
- **k = 1:** `nums = [1,2,3,4,5]`, `k = 1` → Output: 15
- **k = n:** `nums = [1,2,3,4,5]`, `k = 5` → Output: 5
- **Large Values:** `nums = [1000000, 1000000]`, `k = 2` → Output: 1000000

---

# 📚 Key Takeaways

- **Binary Search:** Binary search is a powerful technique for optimizing problems involving finding a minimum or maximum value within a range.
- **Prefix Sum:** Prefix sums can be used to efficiently calculate the sum of subarrays.
- **Dynamic Programming:** Dynamic programming can be used to solve the problem by breaking it down into smaller subproblems.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the array contains negative numbers?
  - How would you handle very large arrays?
- **Common Pitfalls:**
  - Not considering the edge cases.
  - Overcomplicating the solution with unnecessary steps.
- **Alternative Approaches:**
  - Using dynamic programming to solve the problem.
  - Using greedy algorithms to find a suboptimal solution.

---

# ✅ Conclusion

The optimal approach using binary search is preferred because it efficiently narrows down the search space, leading to a time complexity of O(n log(sum(nums))), which is significantly better than the brute force approach's O(n^2 * k). The key insight is recognizing that the problem can be framed as a binary search problem, allowing for an efficient solution.