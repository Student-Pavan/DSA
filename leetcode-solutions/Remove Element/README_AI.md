# 📌 Remove Element

---

# 📝 Problem Statement

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` **in-place**. The order of the elements may be changed. Then return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`. To get accepted, you need to do the following things:

1. Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`. The remaining elements of `nums` are not important as well as the size of `nums`.
2. Return `k`.

### Example 1:
**Input:** `nums = [3,2,2,3]`, `val = 3`
**Output:** `2`, `nums = [2,2,_,_]`
**Explanation:** Your function should return `k = 2`, with the first two elements of `nums` being `2`. It does not matter what you leave beyond the returned `k` (hence they are underscores).

### Example 2:
**Input:** `nums = [0,1,2,2,3,0,4,2]`, `val = 2`
**Output:** `5`, `nums = [0,1,4,0,3,_,_,_]`
**Explanation:** Your function should return `k = 5`, with the first five elements of `nums` containing `0, 1, 4, 0, 3`. Note that the five elements can be returned in any order. It does not matter what you leave beyond the returned `k` (hence they are underscores).

### Constraints:
- `0 <= nums.length <= 100`
- `0 <= nums[i] <= 50`
- `0 <= val <= 100`

---

# 💡 Intuition

The problem requires removing all instances of a given value from an array **in-place**, meaning we cannot use extra space for another array. The key insight is to maintain a pointer that tracks the position where the next valid (non-`val`) element should be placed.

This is a classic **two-pointer** problem:
- One pointer (`i`) iterates through the array.
- Another pointer (`k`) keeps track of the position where the next valid element should be placed.

The optimal solution efficiently filters out unwanted elements in a single pass, making it both time and space efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves creating a new array and copying only the elements that are not equal to `val`. However, this violates the **in-place** constraint and uses extra space.

Since the problem explicitly requires in-place modification, we instead simulate this by shifting elements to the front of the array. For each element, if it is not equal to `val`, we shift it to the front. This approach requires nested loops:
- Outer loop iterates through each element.
- Inner loop shifts elements to the left whenever a `val` is encountered.

This approach is inefficient due to repeated shifting.

---

## 🔹 Algorithm

1. Initialize a pointer `k = 0` to track the position of the next valid element.
2. Iterate through each element in `nums` using pointer `i`.
3. If `nums[i] != val`, copy `nums[i]` to `nums[k]` and increment `k`.
4. After processing all elements, return `k`.

*(Note: This is actually the optimal approach. For the sake of this exercise, we'll consider a less efficient "brute force" version that uses extra space or unnecessary operations.)*

**True Brute Force (Inefficient In-Place):**
1. Iterate through the array.
2. For each element equal to `val`, shift all subsequent elements left by one.
3. Decrement the array length after each shift.
4. Return the new length.

---

## 🔹 Code

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            if (nums[i] == val) {
                // Shift all elements after i to the left by 1
                for (int j = i; j < n - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                n--; // Reduce the effective length
            } else {
                i++;
            }
        }
        return n;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [3, 2, 2, 3]`, `val = 3`

| Step | i | n | Current Array (before shift) | Action | Array After Shift | i | n |
|------|---|---|-------------------------------|--------|-------------------|---|---|
| 1    | 0 | 4 | [3, 2, 2, 3]                  | nums[0] == 3 → shift left | [2, 2, 3, 3] | 0 | 3 |
| 2    | 0 | 3 | [2, 2, 3, 3]                  | nums[0] != 3 → move i | [2, 2, 3, 3] | 1 | 3 |
| 3    | 1 | 3 | [2, 2, 3, 3]                  | nums[1] != 3 → move i | [2, 2, 3, 3] | 2 | 3 |
| 4    | 2 | 3 | [2, 2, 3, 3]                  | nums[2] == 3 → shift left | [2, 2, 3, 3] → [2, 2, 3] | 2 | 2 |
| 5    | 2 | 2 | [2, 2, 3]                     | nums[2] == 3 → shift left | [2, 2, 3] → [2, 2] | 2 | 1 |
| 6    | 2 | 1 | [2, 2]                        | i >= n → exit | [2, 2] | - | 2 |

**Final Array:** `[2, 2]` (first 2 elements)
**Return:** `2`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n²)       |
| Space Complexity | O(1)        |

> **Explanation:**
> - In the worst case (all elements are `val`), we perform `n` shifts, each taking `O(n)` time → `O(n²)`.
> - Only constant extra space is used.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal solution uses the **two-pointer technique** to achieve the goal in a single pass:
- Use one pointer (`i`) to iterate through the array.
- Use another pointer (`k`) to track the position where the next valid element should be placed.
- If `nums[i]` is not equal to `val`, copy it to `nums[k]` and increment `k`.
- Finally, `k` will be the count of valid elements.

This approach ensures that all valid elements are moved to the front in **O(n)** time with **O(1)** space.

---

## 🔹 Why This Works

- **In-Place:** No extra array is used; we overwrite elements in the original array.
- **Order Preservation (Relative):** The relative order of non-`val` elements is preserved.
- **Efficiency:** Each element is checked exactly once → linear time.
- **Correctness:** The first `k` elements are guaranteed to be non-`val`, and `k` is the correct count.

---

## 🔹 Algorithm

1. Initialize `k = 0`.
2. Loop through `nums` with pointer `i` from `0` to `n-1`.
3. If `nums[i] != val`, set `nums[k] = nums[i]` and increment `k`.
4. Return `k`.

---

## 🔹 Code

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [3, 2, 2, 3]`, `val = 3`

| i | nums[i] | nums (before) | Action | nums (after) | k |
|---|---------|---------------|--------|--------------|---|
| 0 | 3       | [3,2,2,3]     | skip   | [3,2,2,3]    | 0 |
| 1 | 2       | [3,2,2,3]     | nums[0] = 2 → k++ | [2,2,2,3] | 1 |
| 2 | 2       | [2,2,2,3]     | nums[1] = 2 → k++ | [2,2,2,3] | 2 |
| 3 | 3       | [2,2,2,3]     | skip   | [2,2,2,3]    | 2 |

**Final Array:** `[2, 2, 2, 3]` → First 2 elements: `[2, 2]`
**Return:** `2`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(1)        |

> **Explanation:**
> - We traverse the array once → `O(n)`.
> - Only two pointers used → `O(1)` extra space.

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output | Explanation |
|---------|-------|-----------------|-----------|
| Empty array | `nums = []`, `val = 0` | `0` | No elements to process. |
| All elements are `val` | `nums = [1,1,1]`, `val = 1` | `0` | No valid elements. |
| No elements equal to `val` | `nums = [1,2,3]`, `val = 4` | `3` | All elements valid. |
| Single element, not `val` | `nums = [5]`, `val = 2` | `1` | Valid element. |
| Single element, equal to `val` | `nums = [2]`, `val = 2` | `0` | No valid elements. |
| Duplicates not equal to `val` | `nums = [2,2,2]`, `val = 3` | `3` | All valid. |
| Large array (within constraints) | `nums = [0,1,2,...,99]`, `val = 50` | `99` | All but one valid. |

---

# 📚 Key Takeaways

- **Two-Pointer Technique** is powerful for in-place array manipulation.
- **In-Place Modification** can be achieved without extra space.
- **Order of Operations** matters: always ensure valid elements are preserved.
- **Edge Cases** are critical in array problems — always test boundaries.
- **Time Complexity** can be reduced from O(n²) to O(n) with smart pointer usage.

---

# 🚀 Interview Tips

- **Clarify Constraints:** Confirm if in-place modification is required.
- **Discuss Trade-offs:** Mention brute force vs. optimal and why optimal is better.
- **Follow-Up:** What if the array is sorted? Can we use binary search? (Not applicable here, but good to think about.)
- **Alternative Approaches:** Could we use a stack or queue? (Not efficient for in-place.)
- **Optimization Insight:** The two-pointer method is optimal for this class of problems.
- **Common Pitfall:** Forgetting to increment `k` after copying a valid element.

---

# ✅ Conclusion

The **optimal solution** using the **two-pointer technique** is the most efficient and elegant approach for this problem. It achieves **O(n) time** and **O(1) space**, meeting all constraints while maintaining clarity and correctness.

The key insight is to **filter valid elements in a single pass**, avoiding unnecessary shifts or extra memory. This approach is widely applicable in array manipulation problems and is a must-know for technical interviews.

> **Final Takeaway:** When asked to modify an array in-place, consider using pointers to track positions — it often leads to optimal solutions.