# 📌 1034-subarrays-with-k-different-integers

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return the number of good subarrays of `nums`.

A good array is an array where the number of different integers in that array is exactly `k`.

For example, `[1,2,3,1,2]` has `3` different integers: `1`, `2`, and `3`.

---

# 💡 Intuition

The key insight is recognizing that the problem can be solved by finding the difference between two sliding window problems:

1. The number of subarrays with at most `k` distinct elements
2. The number of subarrays with at most `k-1` distinct elements

The difference between these two values gives us the exact count of subarrays with exactly `k` distinct elements.

This approach leverages the sliding window technique to efficiently count subarrays while maintaining optimal time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible subarrays and checking each one for the number of distinct elements.

1. Generate all possible subarrays
2. For each subarray, count the distinct elements
3. If the count equals `k`, increment the result

---

## 🔹 Algorithm

1. Initialize a counter to 0
2. Iterate through all possible starting indices of subarrays
3. For each starting index, iterate through all possible ending indices
4. For each subarray, count the distinct elements using a HashSet
5. If the count equals `k`, increment the counter
6. Return the counter

---

## 🔹 Code

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinct.add(nums[j]);
                if (distinct.size() == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
```

---

## 🔹 Dry Run

Let's dry run with `nums = [1,2,1,2,3]` and `k = 2`:

| Iteration | Subarray | Distinct Elements | Count |
|-----------|----------|-------------------|-------|
| 1         | [1]      | {1}               | 0     |
| 2         | [1,2]    | {1,2}             | 1     |
| 3         | [1,2,1]  | {1,2}             | 1     |
| 4         | [1,2,1,2]| {1,2}             | 1     |
| 5         | [1,2,1,2,3]| {1,2,3}       | 0     |
| 6         | [2]      | {2}               | 0     |
| 7         | [2,1]    | {2,1}             | 1     |
| 8         | [2,1,2]  | {2,1}             | 1     |
| 9         | [2,1,2,3]| {2,1,3}         | 0     |
| 10        | [1]      | {1}               | 0     |
| 11        | [1,2]    | {1,2}             | 1     |
| 12        | [1,2,3]  | {1,2,3}           | 0     |
| 13        | [2]      | {2}               | 0     |
| 14        | [2,3]    | {2,3}             | 1     |
| 15        | [3]      | {3}               | 0     |

Total count: 5

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the sliding window technique to efficiently count subarrays with exactly `k` distinct elements by calculating the difference between two sliding window counts:

1. Count subarrays with at most `k` distinct elements
2. Count subarrays with at most `k-1` distinct elements
3. Subtract the second count from the first to get the exact count

---

## 🔹 Why This Works

This approach works because:
- The difference between the counts gives us exactly the subarrays with exactly `k` distinct elements
- The sliding window technique allows us to count these efficiently in O(n) time
- We avoid the O(n²) time complexity of the brute force approach

---

## 🔹 Algorithm

1. Implement a helper function `atMost` that counts subarrays with at most `k` distinct elements
2. Call `atMost(nums, k)` to get the count of subarrays with at most `k` distinct elements
3. Call `atMost(nums, k-1)` to get the count of subarrays with at most `k-1` distinct elements
4. Return the difference between these two counts

---

## 🔹 Code

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.size() > k) {
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the `atMost` function with `nums = [1,2,1,2,3]` and `k = 2`:

| Iteration | Left | Right | Current Element | Map State | Action | Count |
|-----------|------|-------|-----------------|-----------|--------|-------|
| 1         | 0    | 0     | 1               | {1:1}     | -      | 1     |
| 2         | 0    | 1     | 2               | {1:1,2:1} | -      | 3     |
| 3         | 0    | 2     | 1               | {1:2,2:1} | -      | 6     |
| 4         | 0    | 3     | 2               | {1:2,2:2} | -      | 10    |
| 5         | 1    | 4     | 3               | {2:2,3:1} | Remove 1 | 13    |

Now dry run with `k = 1`:

| Iteration | Left | Right | Current Element | Map State | Action | Count |
|-----------|------|-------|-----------------|-----------|--------|-------|
| 1         | 0    | 0     | 1               | {1:1}     | -      | 1     |
| 2         | 1    | 1     | 2               | {2:1}     | Remove 1 | 2     |
| 3         | 2    | 2     | 1               | {1:1}     | Remove 2 | 3     |
| 4         | 3    | 3     | 2               | {2:1}     | Remove 1 | 4     |
| 5         | 4    | 4     | 3               | {3:1}     | Remove 2 | 5     |

Final result: 13 - 5 = 8

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(k) |

---

# 🔍 Edge Cases

- Empty array: `nums = []`, `k = 0` → returns 0
- Single element array: `nums = [1]`, `k = 1` → returns 1
- All identical elements: `nums = [1,1,1]`, `k = 1` → returns 3
- Large input size: `nums` with 10,000 elements → should handle efficiently
- `k` equals array length: `nums = [1,2,3]`, `k = 3` → returns 1
- `k` equals 1: `nums = [1,2,1,2]`, `k = 1` → returns 4

---

# 📚 Key Takeaways

1. The sliding window technique is powerful for counting subarrays with specific properties
2. The difference between two sliding window counts can solve problems that would otherwise require more complex logic
3. Understanding the relationship between "at most" and "exactly" counts is crucial for optimization
4. Time complexity can be significantly improved by leveraging mathematical insights

---

# 🚀 Interview Tips

- Be prepared to explain the intuition behind using the difference of two sliding window counts
- Practice implementing the sliding window technique efficiently
- Consider edge cases where `k` is 1 or equals the array length
- Be ready to discuss alternative approaches and their trade-offs

---

# ✅ Conclusion

The optimal solution efficiently counts subarrays with exactly `k` distinct elements by leveraging the sliding window technique and calculating the difference between two counts. This approach achieves O(n) time complexity, making it suitable for large input sizes, which is crucial for interview scenarios. The key insight is recognizing that the difference between "at most k" and "at most k-1" gives the exact count, demonstrating how mathematical optimization can lead to elegant solutions.