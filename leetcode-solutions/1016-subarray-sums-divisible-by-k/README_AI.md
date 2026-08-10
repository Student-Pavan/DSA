# 📌 Problem Name
Subarray Sums Divisible by K

---

# 📝 Problem Statement
Given an integer array `nums` and an integer `k`, return the number of non-empty subarrays that have a sum divisible by `k`.

A subarray is a contiguous part of an array.

**Example 1:**
```
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3], [-3]
```

**Example 2:**
```
Input: nums = [5], k = 9
Output: 0
```

**Constraints:**
- `1 <= nums.length <= 3 * 10^4`
- `-10^4 <= nums[i] <= 10^4`
- `2 <= k <= 10^4`

---

# 💡 Intuition
The key insight here is recognizing that if the difference between two prefix sums is divisible by `k`, then the subarray between those indices has a sum divisible by `k`. This allows us to use a hash map to store the frequency of prefix sum remainders, enabling an efficient O(n) solution.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach involves checking all possible subarrays and counting those whose sum is divisible by `k`. For each starting index, we calculate the sum of all subarrays starting at that index and ending at every possible index after it.

---

## 🔹 Algorithm
1. Initialize a counter `count` to 0.
2. Use nested loops:
   - Outer loop runs from the start of the array to the end.
   - Inner loop runs from the current outer loop index to the end of the array.
3. For each subarray defined by the outer and inner loop indices, calculate the sum.
4. If the sum is divisible by `k`, increment `count`.
5. Return `count`.

---

## 🔹 Code
```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
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

Let's dry run the brute force approach with `nums = [4,5,0,-2,-3,1]` and `k = 5`.

| Iteration | i | j | Current Subarray | Sum | Divisible by 5? | Count |
|-----------|---|---|------------------|-----|-----------------|-------|
| 1         | 0 | 0 | [4]              | 4   | No              | 0     |
| 2         | 0 | 1 | [4,5]            | 9   | No              | 0     |
| 3         | 0 | 2 | [4,5,0]          | 9   | No              | 0     |
| 4         | 0 | 3 | [4,5,0,-2]       | 7   | No              | 0     |
| 5         | 0 | 4 | [4,5,0,-2,-3]    | 4   | No              | 0     |
| 6         | 0 | 5 | [4,5,0,-2,-3,1]  | 5   | Yes             | 1     |
| 7         | 1 | 1 | [5]              | 5   | Yes             | 2     |
| 8         | 1 | 2 | [5,0]            | 5   | Yes             | 3     |
| 9         | 1 | 3 | [5,0,-2]         | 3   | No              | 3     |
| 10        | 1 | 4 | [5,0,-2,-3]      | 0   | Yes             | 4     |
| 11        | 1 | 5 | [5,0,-2,-3,1]    | 1   | No              | 4     |
| 12        | 2 | 2 | [0]              | 0   | Yes             | 5     |
| 13        | 2 | 3 | [0,-2]           | -2  | No              | 5     |
| 14        | 2 | 4 | [0,-2,-3]        | -5  | Yes             | 6     |
| 15        | 2 | 5 | [0,-2,-3,1]      | -4  | No              | 6     |
| 16        | 3 | 3 | [-2]             | -2  | No              | 6     |
| 17        | 3 | 4 | [-2,-3]          | -5  | Yes             | 7     |
| 18        | 3 | 5 | [-2,-3,1]        | -4  | No              | 7     |
| 19        | 4 | 4 | [-3]             | -3  | No              | 7     |
| 20        | 4 | 5 | [-3,1]           | -2  | No              | 7     |
| 21        | 5 | 5 | [1]              | 1   | No              | 7     |

Final count: 7

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach uses the concept of prefix sums and modular arithmetic. We maintain a running prefix sum and use a hash map to store the frequency of remainders when the prefix sum is divided by `k`. This allows us to efficiently count the number of subarrays with sums divisible by `k` in O(n) time.

---

## 🔹 Why This Works
When the remainder of the prefix sum at two different indices is the same, the subarray between those indices has a sum divisible by `k`. By storing the frequency of each remainder, we can quickly determine how many such subarrays exist for each remainder encountered.

---

## 🔹 Algorithm
1. Initialize a hash map `map` with a key `0` and value `1` to account for the sum of the empty subarray.
2. Initialize `prefixsum` and `count` to `0`.
3. Iterate through the array:
   - Update `prefixsum` with the current element.
   - Calculate the remainder `rem` of `prefixsum` divided by `k`.
   - Adjust `rem` to be positive if it is negative.
   - If `rem` exists in the map, increment `count` by the value associated with `rem`.
   - Update the map with the current `rem`.
4. Return `count`.

---

## 🔹 Code
```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixsum = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixsum += nums[i];

            int rem = prefixsum % k;

            if (rem < 0) {
                rem += k;
            }
            if (map.containsKey(rem)) {
                count += map.get(rem);

            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums = [4,5,0,-2,-3,1]` and `k = 5`.

| Iteration | Current Value | Prefix Sum | Remainder | Adjusted Remainder | Map State | Count |
|-----------|---------------|------------|-----------|---------------------|-----------|-------|
| 0         | 4             | 4          | 4         | 4                   | {0:1, 4:1} | 0     |
| 1         | 5             | 9          | 4         | 4                   | {0:1, 4:2} | 1     |
| 2         | 0             | 9          | 4         | 4                   | {0:1, 4:3} | 2     |
| 3         | -2            | 7          | 2         | 2                   | {0:1, 4:3, 2:1} | 2     |
| 4         | -3            | 4          | 4         | 4                   | {0:1, 4:4, 2:1} | 5     |
| 5         | 1             | 5          | 0         | 0                   | {0:2, 4:4, 2:1} | 7     |

Final count: 7

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input:** Not applicable as per constraints.
- **Single Element:** If the single element is divisible by `k`, the count is 1.
- **All Elements Zero:** The count is `n*(n+1)/2` since all subarrays have sum zero.
- **Negative Values:** The algorithm handles negative values correctly by adjusting the remainder.
- **Large Constraints:** The optimal approach efficiently handles large input sizes.

---

# 📚 Key Takeaways

- **Prefix Sum Technique:** Understanding how prefix sums can be used to solve subarray problems efficiently.
- **Modular Arithmetic:** Leveraging properties of modular arithmetic to simplify the problem.
- **Hash Map Usage:** Efficiently tracking and utilizing remainders to count valid subarrays.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve this problem in O(n) time and O(1) space?
  - How would you handle very large values of `k`?
- **Common Pitfalls:**
  - Forgetting to handle negative remainders.
  - Not initializing the hash map with the key `0`.
- **Alternative Approaches:**
  - Using a brute force approach for small input sizes.
  - Using a sliding window approach for specific constraints.

---

# ✅ Conclusion

The optimal approach using prefix sums and modular arithmetic provides an efficient solution to the problem with O(n) time complexity and O(n) space complexity. This approach is crucial for handling large input sizes efficiently, making it suitable for interview scenarios and real-world applications.