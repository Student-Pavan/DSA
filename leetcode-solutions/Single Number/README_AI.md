# 📌 Single Number

---

# 📝 Problem Statement

Given a **non-empty** array of integers `nums`, every element appears **twice** except for one. Find that single one.

**Objective:**
Return the integer that appears only once in the array.

**Constraints:**
- `1 <= nums.length <= 3 * 10^4`
- `-3 * 10^4 <= nums[i] <= 3 * 10^4`
- Each element in the array appears exactly **twice**, except for one element which appears **once**.

**Example:**
```
Input: nums = [2, 2, 1]
Output: 1
```

```
Input: nums = [4, 1, 2, 1, 2]
Output: 4
```

---

# 💡 Intuition

The key observation here is that **XOR (exclusive OR)** operation has two important properties:
1. **Commutative & Associative:** The order of operations does not matter.
2. **Self-Inverse:** Any number XORed with itself results in 0 (`a ^ a = 0`).
3. **Identity:** Any number XORed with 0 remains unchanged (`a ^ 0 = a`).

Since all numbers except one appear exactly twice, XORing all numbers will cancel out the duplicates, leaving only the single number.

This insight leads to an **O(n) time and O(1) space** solution, which is optimal.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a **hash map** to count the frequency of each number. After building the frequency map, we iterate through it to find the number with a frequency of 1.

This approach is straightforward but uses **O(n) extra space** for storing frequencies.

---

## 🔹 Algorithm

1. Initialize a `HashMap` to store frequency counts.
2. Iterate through the array and update the frequency count for each number.
3. Iterate through the `HashMap` to find the number with a frequency of 1.
4. Return that number.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Build frequency map
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Find the number with frequency 1
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        // This line is theoretically unreachable due to problem constraints
        return -1;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [4, 1, 2, 1, 2]`

| Step | Current Number | Frequency Map State |
|------|----------------|---------------------|
| 1    | 4              | `{4: 1}`            |
| 2    | 1              | `{4: 1, 1: 1}`      |
| 3    | 2              | `{4: 1, 1: 1, 2: 1}`|
| 4    | 1              | `{4: 1, 1: 2, 2: 1}`|
| 5    | 2              | `{4: 1, 1: 2, 2: 2}`|

Now, iterate through the frequency map:
- `4: 1` → **return 4**

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal solution leverages the **XOR operation** to find the single number in **O(n) time and O(1) space**.

Since XORing a number with itself results in 0, and XORing a number with 0 results in the number itself, we can XOR all elements in the array. The duplicates will cancel out, leaving only the single number.

---

## 🔹 Why This Works

- **XOR is commutative and associative:** The order of operations does not matter.
- **Self-cancellation:** `a ^ a = 0`
- **Identity:** `a ^ 0 = a`

Thus, for an array like `[4, 1, 2, 1, 2]`:
```
4 ^ 1 ^ 2 ^ 1 ^ 2 = 4 ^ (1 ^ 1) ^ (2 ^ 2) = 4 ^ 0 ^ 0 = 4
```

---

## 🔹 Algorithm

1. Initialize `result = 0`.
2. Iterate through each number in the array.
3. XOR the current number with `result`.
4. Return `result`.

---

## 🔹 Code

```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [4, 1, 2, 1, 2]`

| Iteration | Current Number | XOR Operation | Result |
|-----------|----------------|---------------|--------|
| 1         | 4              | `0 ^ 4`       | 4      |
| 2         | 1              | `4 ^ 1`       | 5      |
| 3         | 2              | `5 ^ 2`       | 7      |
| 4         | 1              | `7 ^ 1`       | 6      |
| 5         | 2              | `6 ^ 2`       | 4      |

**Final Result:** `4`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

---

# 🔍 Edge Cases

| Edge Case                     | Expected Output | Explanation |
|-------------------------------|-----------------|-------------|
| `[1]`                         | `1`             | Single element |
| `[-1, -1, -2]`                | `-2`            | Negative numbers |
| `[0, 1, 0]`                   | `1`             | Zero included |
| `[30000, 30000, 20000]`       | `20000`         | Large values |
| `[1, 1, 2, 2, 3, 3, 4]`       | `4`             | Last element is single |

---

# 📚 Key Takeaways

- **XOR is a powerful bitwise operation** for problems involving duplicates and uniqueness.
- **Optimal space usage** is achieved by leveraging bitwise properties.
- **Brute force is intuitive** but often suboptimal in space.
- **Problem constraints** can guide optimization (e.g., all duplicates appear twice).

---

# 🚀 Interview Tips

- **Follow-up:** What if every element appears **three times** except one? (Hint: Use bit manipulation with counters.)
- **Common Pitfall:** Forgetting that XOR is commutative and associative.
- **Alternative Approach:** Sorting the array and comparing adjacent elements (O(n log n) time, O(1) space).
- **Optimization Insight:** Always consider **bitwise operations** for problems involving duplicates and uniqueness.

---

# ✅ Conclusion

The **optimal solution** using XOR is **preferred** due to its **O(n) time and O(1) space** complexity. It efficiently cancels out duplicates, leaving only the single number.

**Key Insight:** XOR’s self-inverse property makes it ideal for problems where duplicates cancel out.

This problem is a **classic example** of how **bitwise operations** can lead to elegant and efficient solutions.