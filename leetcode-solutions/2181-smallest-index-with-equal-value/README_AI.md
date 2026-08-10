# 📌 Smallest Index With Equal Value

---

# 📝 Problem Statement

Given a **0-indexed** integer array `nums`, return the **smallest** index `i` of `nums` such that `i mod 10 == nums[i]`, or `-1` if such index does not exist.

**Constraints:**
- `1 <= nums.length <= 100`
- `0 <= nums[i] < 10`

---

# 💡 Intuition

The problem requires finding the smallest index where the index modulo 10 equals the value at that index. The key insight is that we need to check each element in order and return the first valid index we encounter. If no such index exists, we return -1.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through each element of the array and checking if the condition `i % 10 == nums[i]` is satisfied. If it is, we immediately return the current index. If we finish the loop without finding any valid index, we return -1.

---

## 🔹 Algorithm

1. Iterate through each element in the array from index 0 to the end.
2. For each index `i`, check if `i % 10 == nums[i]`.
3. If the condition is satisfied, return `i`.
4. If the loop completes without finding any valid index, return -1.

---

## 🔹 Code

```java
class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input `nums = [0,1,2]`.

| Iteration | Index (i) | nums[i] | Condition (i % 10 == nums[i]) | Action |
|-----------|-----------|---------|--------------------------------|--------|
| 1         | 0         | 0       | 0 % 10 == 0 → True             | Return 0 |

The algorithm returns `0` as the smallest index where the condition is satisfied.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because we need to check each element in the worst case. However, we can optimize the condition check by directly comparing `i % 10` with `nums[i]` without any additional operations.

---

## 🔹 Why This Works

This approach works because we are checking each element in order, and the first valid index we find is the smallest possible. The condition `i % 10 == nums[i]` is straightforward and can be checked in constant time for each element.

---

## 🔹 Algorithm

1. Iterate through each element in the array from index 0 to the end.
2. For each index `i`, check if `i % 10 == nums[i]`.
3. If the condition is satisfied, return `i`.
4. If the loop completes without finding any valid index, return -1.

---

## 🔹 Code

```java
class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `nums = [4,3,2,1]`.

| Iteration | Index (i) | nums[i] | Condition (i % 10 == nums[i]) | Action |
|-----------|-----------|---------|--------------------------------|--------|
| 1         | 0         | 4       | 0 % 10 == 4 → False            | Continue |
| 2         | 1         | 3       | 1 % 10 == 3 → False            | Continue |
| 3         | 2         | 2       | 2 % 10 == 2 → True             | Return 2 |

The algorithm returns `2` as the smallest index where the condition is satisfied.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty array:** Return -1.
- **Single element array:** Check if the element satisfies the condition.
- **No valid index:** Return -1.
- **All elements satisfy the condition:** Return the smallest index.

---

# 📚 Key Takeaways

- The problem can be solved by iterating through the array and checking the condition for each element.
- The optimal approach is the same as the brute force approach because we need to check each element in the worst case.
- The condition `i % 10 == nums[i]` is straightforward and can be checked in constant time.

---

# 🚀 Interview Tips

- **Follow-up questions:** What if the array is very large? Can we optimize further?
- **Common pitfalls:** Forgetting to return -1 if no valid index is found.
- **Alternative approaches:** Using binary search if the array is sorted, but the problem does not specify that the array is sorted.

---

# ✅ Conclusion

The optimal solution involves iterating through the array and checking the condition for each element. The first valid index found is the answer. If no valid index is found, return -1. This approach ensures that we find the smallest index efficiently.