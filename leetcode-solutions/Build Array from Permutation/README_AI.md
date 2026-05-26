# 📌 Build Array from Permutation

---

# 📝 Problem Statement

Given a **zero-based permutation** `nums` (0-indexed), build an array `ans` of the same length where:
- `ans[i] = nums[nums[i]]` for each `0 <= i < nums.length`
- Return the array `ans`

### **Objective**
Construct the result array by applying the permutation mapping defined by `nums` itself.

### **Constraints**
- `1 <= nums.length <= 1000`
- `0 <= nums[i] < nums.length`
- The elements in `nums` are **distinct**

### **Example**
**Input:** `nums = [0,2,1,5,3,4]`
**Output:** `[0,1,2,4,5,3]`

**Explanation:**
- `ans[0] = nums[nums[0]] = nums[0] = 0`
- `ans[1] = nums[nums[1]] = nums[2] = 1`
- `ans[2] = nums[nums[2]] = nums[1] = 2`
- `ans[3] = nums[nums[3]] = nums[5] = 4`
- `ans[4] = nums[nums[4]] = nums[3] = 5`
- `ans[5] = nums[nums[5]] = nums[4] = 3`

---

# 💡 Intuition

The problem requires applying a **permutation mapping** where each element in the result array is determined by using the current index as a pointer into the original array, then using that value as another pointer.

### **Key Insight**
- The permutation is **self-referential**: `nums[i]` acts as an index into `nums` itself.
- The solution involves **direct indexing** without any complex transformations.
- Since the constraints are small (`n <= 1000`), even a brute-force approach is efficient, but optimization can still be applied for better space usage.

---

# 🐌 Brute Force Approach

## 🔹 Approach
Create a new array `ans` and populate it by iterating through each index `i` and setting `ans[i] = nums[nums[i]]`. This is straightforward and leverages the problem's definition directly.

### **Why It Works**
- The problem explicitly defines `ans[i]` in terms of `nums[i]` and `nums[nums[i]]`.
- No additional logic is needed beyond following the given formula.

---

## 🔹 Algorithm
1. Initialize an empty array `ans` of the same length as `nums`.
2. Iterate through each index `i` from `0` to `nums.length - 1`.
3. Set `ans[i] = nums[nums[i]]`.
4. Return `ans`.

---

## 🔹 Code

```java
class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [0,2,1,5,3,4]`

| Iteration | `i` | `nums[i]` | `nums[nums[i]]` | `ans` (Current State) |
|-----------|-----|-----------|------------------|------------------------|
| 0         | 0   | 0         | `nums[0] = 0`    | `[0, _, _, _, _, _]`   |
| 1         | 1   | 2         | `nums[2] = 1`    | `[0, 1, _, _, _, _]`   |
| 2         | 2   | 1         | `nums[1] = 2`    | `[0, 1, 2, _, _, _]`   |
| 3         | 3   | 5         | `nums[5] = 4`    | `[0, 1, 2, 4, _, _]`   |
| 4         | 4   | 3         | `nums[3] = 5`    | `[0, 1, 2, 4, 5, _]`   |
| 5         | 5   | 4         | `nums[4] = 3`    | `[0, 1, 2, 4, 5, 3]`   |

**Final Output:** `[0, 1, 2, 4, 5, 3]`

---

## 🔹 Complexity Analysis

| Complexity      | Value  |
|-----------------|--------|
| Time Complexity | O(n)   |
| Space Complexity| O(n)   |

- **Time:** We iterate through the array once.
- **Space:** We allocate a new array of size `n`.

---

# ⚡ Optimal Approach

## 🔹 Approach
Instead of using extra space for the result array, we can **modify the input array in-place** to store both the original values and the result. This is achieved by encoding two values in a single integer using **mathematical operations**.

### **Key Insight**
- Use the formula `nums[i] = nums[i] + (nums[nums[i]] % n) * n` to store both the original and new value in the same position.
- The modulo operation (`% n`) extracts the original value, while division (`/ n`) extracts the new value.

### **Why It Works**
- The original value can be retrieved using `nums[i] % n`.
- The new value can be retrieved using `nums[i] / n`.
- This avoids extra space by leveraging the fact that `nums[i]` is always less than `n`.

---

## 🔹 Algorithm
1. Store the length of `nums` in `n`.
2. Iterate through each index `i` from `0` to `n - 1`.
3. Update `nums[i]` to encode both the original and new value using:
   `nums[i] = nums[i] + (nums[nums[i]] % n) * n`.
4. Iterate again to extract the new values by dividing each element by `n`.
5. Return the modified `nums`.

---

## 🔹 Code

```java
class Solution {
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] + (nums[nums[i]] % n) * n;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] / n;
        }
        return nums;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [0,2,1,5,3,4]` (`n = 6`)

### **First Pass (Encoding)**
| `i` | `nums[i]` (Original) | `nums[nums[i]] % n` | `nums[i] + (nums[nums[i]] % n) * n` | `nums` (After Update) |
|-----|----------------------|----------------------|--------------------------------------|------------------------|
| 0   | 0                    | `nums[0] % 6 = 0`    | `0 + 0 * 6 = 0`                      | `[0, 2, 1, 5, 3, 4]`   |
| 1   | 2                    | `nums[2] % 6 = 1`    | `2 + 1 * 6 = 8`                      | `[0, 8, 1, 5, 3, 4]`   |
| 2   | 1                    | `nums[1] % 6 = 2`    | `1 + 2 * 6 = 13`                     | `[0, 8, 13, 5, 3, 4]`  |
| 3   | 5                    | `nums[5] % 6 = 4`    | `5 + 4 * 6 = 29`                     | `[0, 8, 13, 29, 3, 4]` |
| 4   | 3                    | `nums[3] % 6 = 5`    | `3 + 5 * 6 = 33`                     | `[0, 8, 13, 29, 33, 4]`|
| 5   | 4                    | `nums[4] % 6 = 3`    | `4 + 3 * 6 = 22`                     | `[0, 8, 13, 29, 33, 22]`|

### **Second Pass (Decoding)**
| `i` | `nums[i]` (Encoded) | `nums[i] / n` | `nums` (After Update) |
|-----|---------------------|---------------|------------------------|
| 0   | 0                   | `0 / 6 = 0`   | `[0, 8, 13, 29, 33, 22]`|
| 1   | 8                   | `8 / 6 = 1`   | `[0, 1, 13, 29, 33, 22]`|
| 2   | 13                  | `13 / 6 = 2`  | `[0, 1, 2, 29, 33, 22]` |
| 3   | 29                  | `29 / 6 = 4`  | `[0, 1, 2, 4, 33, 22]`  |
| 4   | 33                  | `33 / 6 = 5`  | `[0, 1, 2, 4, 5, 22]`   |
| 5   | 22                  | `22 / 6 = 3`  | `[0, 1, 2, 4, 5, 3]`    |

**Final Output:** `[0, 1, 2, 4, 5, 3]`

---

## 🔹 Complexity Analysis

| Complexity      | Value  |
|-----------------|--------|
| Time Complexity | O(n)   |
| Space Complexity| O(1)   |

- **Time:** Two passes through the array.
- **Space:** No additional space is used beyond the input array.

---

# 🔍 Edge Cases

| Edge Case               | Input               | Output              | Explanation                          |
|-------------------------|---------------------|---------------------|--------------------------------------|
| Single element          | `[0]`               | `[0]`               | Only one possible mapping.           |
| All elements same       | `[0,0,0]`           | **Invalid**         | Violates distinctness constraint.    |
| Maximum length          | `nums = [0..999]`   | `[0, 1, 2, ..., 999]`| Each element maps to itself.         |
| Reverse permutation     | `[3,2,1,0]`         | `[1, 2, 3, 0]`      | Each element maps to its reverse.    |

---

# 📚 Key Takeaways

1. **Brute Force is Simple and Effective**
   - Directly following the problem statement yields an O(n) time and space solution.
   - Ideal for small constraints or when readability is prioritized.

2. **In-Place Optimization Saves Space**
   - Mathematical encoding allows O(1) space complexity.
   - Useful when input modification is allowed and space is constrained.

3. **Permutation Problems Often Involve Index Mapping**
   - Recognizing the pattern of using array values as indices is key.
   - Similar to problems like **"Shuffle an Array"** or **"Next Permutation"**.

4. **Modulo and Division for Encoding**
   - Storing two values in one integer is a powerful technique for in-place updates.

---

# 🚀 Interview Tips

### **Follow-Up Questions**
- **Can you solve this without extra space?**
  → Use the optimal in-place approach with mathematical encoding.
- **What if the array contains duplicates?**
  → The problem guarantees distinct elements, but if not, the solution would fail.
- **How would you handle larger constraints (e.g., `n = 10^6`)?**
  → The brute-force approach is still O(n) and efficient, but the in-place method avoids memory overhead.

### **Common Pitfalls**
- **Off-by-One Errors**
  → Ensure indices are within bounds (0 ≤ `nums[i]` < `nums.length`).
- **Modifying Input Prematurely**
  → In the optimal approach, ensure the original value is preserved during encoding.

### **Alternative Approaches**
- **Using a HashMap**
  → Overkill for this problem but useful if the permutation is not zero-based.
- **Recursive Approach**
  → Unnecessary for this problem but could be explored for learning recursion.

---

# ✅ Conclusion

The **brute-force approach** is the most intuitive and works efficiently for the given constraints. However, the **optimal in-place approach** demonstrates a clever space optimization technique that is valuable in interviews, especially when space complexity is a concern.

### **Key Insight**
- **Permutation problems often involve index manipulation.**
- **Mathematical encoding can save space when in-place modification is allowed.**

This problem is an excellent example of how **simple indexing** can solve seemingly complex problems efficiently. Mastering such patterns is crucial for technical interviews at top-tier companies.