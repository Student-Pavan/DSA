# 📌 Problem Name

Continuous Subarray Sum

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return `true` if `nums` has a continuous subarray of size at least two whose elements sum to a multiple of `k`, or `false` otherwise.

An integer `x` is a multiple of `k` if there exists an integer `n` such that `x = n * k`. `0` is considered a multiple of every integer.

**Constraints:**
- `1 <= nums.length <= 10^5`
- `0 <= nums[i] <= 10^9`
- `0 <= k <= 10^5`

---

# 💡 Intuition

The key insight is recognizing that if the difference between two prefix sums modulo `k` is the same, then the subarray between those indices sums to a multiple of `k`. This allows us to use a hash map to store prefix sums modulo `k` and their indices, enabling efficient checking for valid subarrays.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays of size at least two and calculating their sums to see if they are divisible by `k`.

## 🔹 Algorithm

1. Iterate through all possible starting indices of subarrays.
2. For each starting index, iterate through all possible ending indices.
3. Calculate the sum of the current subarray.
4. Check if the sum is divisible by `k`.
5. If a valid subarray is found, return `true`.
6. If no valid subarray is found after all iterations, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `nums = [23, 2, 4, 6, 7]` and `k = 6`.

| Iteration | Subarray | Sum | Sum % 6 | Result |
|---|---|---|---|---|
| 1 | [23] | 23 | 5 | Continue |
| 2 | [23, 2] | 25 | 1 | Continue |
| 3 | [23, 2, 4] | 29 | 5 | Continue |
| 4 | [23, 2, 4, 6] | 35 | 5 | Continue |
| 5 | [23, 2, 4, 6, 7] | 42 | 0 | Return true |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a hash map to store prefix sums modulo `k` and their indices. This allows us to check for valid subarrays in constant time.

## 🔹 Why This Works

If the difference between two prefix sums modulo `k` is the same, then the subarray between those indices sums to a multiple of `k`. This is because the difference in prefix sums represents the sum of the subarray, and if the difference modulo `k` is the same, the sum is divisible by `k`.

## 🔹 Algorithm

1. Initialize a hash map to store prefix sums modulo `k` and their indices.
2. Initialize a variable to store the prefix sum.
3. Iterate through the array, updating the prefix sum and its modulo `k`.
4. If the modulo `k` is already in the hash map and the difference in indices is at least two, return `true`.
5. If the modulo `k` is not in the hash map, add it to the hash map with its index.
6. If no valid subarray is found after all iterations, return `false`.

## 🔹 Code

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixsum = 0;

        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixsum += nums[i];
            int rem = prefixsum % k;

            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }

        return false;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums = [23, 2, 4, 6, 7]` and `k = 6`.

| Iteration | Current Value | Prefix Sum | Prefix Sum % 6 | Map State | Result |
|---|---|---|---|---|---|
| 0 | 23 | 23 | 5 | {0: -1, 5: 0} | Continue |
| 1 | 2 | 25 | 1 | {0: -1, 5: 0, 1: 1} | Continue |
| 2 | 4 | 29 | 5 | {0: -1, 5: 0, 1: 1} | Return true |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(min(n, k)) |

---

# 🔍 Edge Cases

- Empty array
- Single element array
- All elements are zero
- Large values of `k`
- Small values of `k`
- Negative values in the array

---

# 📚 Key Takeaways

- The brute force approach is simple but inefficient for large arrays.
- The optimal approach uses the concept of prefix sums and modulo arithmetic to efficiently check for valid subarrays.
- The optimal approach is suitable for large input sizes due to its linear time complexity.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Mention the importance of handling edge cases, especially when `k` is zero.
- Be prepared to explain the reasoning behind using modulo arithmetic in the optimal approach.

---

# ✅ Conclusion

The optimal approach is preferred due to its efficiency, especially for large input sizes. The key insight is recognizing the pattern in prefix sums modulo `k` to efficiently check for valid subarrays. This approach demonstrates a deep understanding of algorithmic optimization and is suitable for FAANG interviews.