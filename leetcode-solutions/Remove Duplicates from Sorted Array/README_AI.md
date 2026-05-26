# 📌 Remove Duplicates from Sorted Array

---

# 📝 Problem Statement

Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates **in-place** such that each unique element appears only once. The **relative order** of the elements should be kept the same.

Return the number of unique elements in `nums`.

**Constraints:**
- `1 <= nums.length <= 3 * 10^4`
- `-100 <= nums[i] <= 100`
- `nums` is sorted in **non-decreasing order**

**Key Requirements:**
- Modify the array **in-place** (no extra space for another array)
- Return the count of unique elements
- The first `k` elements of `nums` should contain the unique elements in order, where `k` is the return value

---

# 💡 Intuition

Since the array is **sorted**, all duplicate elements will be adjacent. This allows us to efficiently identify and skip duplicates using a **two-pointer technique**.

The key insight is:
- Use one pointer (`uniquePtr`) to track the position of the last unique element
- Use another pointer (`i`) to scan through the array
- Whenever a new unique element is found, place it at `uniquePtr + 1` and increment `uniquePtr`

This approach ensures we modify the array **in-place** with **O(1)** extra space and **O(n)** time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach would involve:
1. Creating a new array to store unique elements
2. Iterating through the original array and copying only unique elements
3. Copying the result back to the original array

However, this violates the **in-place** requirement and uses **O(n)** extra space.

We'll implement a brute-force version that uses a `Set` to track seen elements, but this still uses extra space and doesn't fully satisfy the in-place constraint.

---

## 🔹 Algorithm

1. Initialize a `Set` to track seen elements
2. Initialize a pointer `uniquePtr` at index 0
3. Iterate through the array:
   - If the current element is not in the set:
     - Add it to the set
     - Place it at `uniquePtr`
     - Increment `uniquePtr`
4. Return `uniquePtr`

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int uniquePtr = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!seen.contains(nums[i])) {
                seen.add(nums[i]);
                nums[uniquePtr] = nums[i];
                uniquePtr++;
            }
        }

        return uniquePtr;
    }
}
```

---

## 🔹 Dry Run

**Input:** `nums = [1, 1, 2]`

| Step | i | nums[i] | seen Set | Action | nums Array | uniquePtr |
|------|---|---------|----------|--------|------------|-----------|
| 1    | 0 | 1       | {}       | Add 1 to set, place at 0 | [1, 1, 2] | 1 |
| 2    | 1 | 1       | {1}      | Skip (duplicate) | [1, 1, 2] | 1 |
| 3    | 2 | 2       | {1}      | Add 2 to set, place at 1 | [1, 2, 2] | 2 |

**Output:** `2`, `nums = [1, 2, _]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

**Note:** This is not optimal due to the extra space used by the `Set`.

---

# ⚡ Optimal Approach

## 🔹 Approach

We leverage the fact that the array is **sorted**. Since duplicates are adjacent, we can use a **two-pointer technique** to solve this in **O(1)** space:

- `uniquePtr`: Tracks the position of the last unique element
- `i`: Scans through the array

Whenever `nums[i] != nums[uniquePtr]`, we've found a new unique element. We increment `uniquePtr` and place the new element at `uniquePtr`.

---

## 🔹 Why This Works

- **Sorted Property:** Duplicates are adjacent, so we only need to compare with the last unique element
- **In-Place:** We overwrite duplicates without extra space
- **Efficiency:** Single pass through the array with constant space

---

## 🔹 Algorithm

1. If the array is empty, return 0
2. Initialize `uniquePtr = 0`
3. Iterate through the array with `i` from 1 to n-1:
   - If `nums[i] != nums[uniquePtr]`:
     - Increment `uniquePtr`
     - Set `nums[uniquePtr] = nums[i]`
4. Return `uniquePtr + 1`

---

## 🔹 Code

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int uniquePtr = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniquePtr]) {
                uniquePtr++;
                nums[uniquePtr] = nums[i];
            }
        }

        return uniquePtr + 1;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`

| Step | i | nums[i] | nums[uniquePtr] | Action | nums Array | uniquePtr |
|------|---|---------|-----------------|--------|------------|-----------|
| 1    | 1 | 0       | 0               | Skip   | [0,0,1,1,1,2,2,3,3,4] | 0 |
| 2    | 2 | 1       | 0               | Place at 1 | [0,1,1,1,1,2,2,3,3,4] | 1 |
| 3    | 3 | 1       | 1               | Skip   | [0,1,1,1,1,2,2,3,3,4] | 1 |
| 4    | 4 | 1       | 1               | Skip   | [0,1,1,1,1,2,2,3,3,4] | 1 |
| 5    | 5 | 2       | 1               | Place at 2 | [0,1,2,1,1,2,2,3,3,4] | 2 |
| 6    | 6 | 2       | 2               | Skip   | [0,1,2,1,1,2,2,3,3,4] | 2 |
| 7    | 7 | 3       | 2               | Place at 3 | [0,1,2,3,1,2,2,3,3,4] | 3 |
| 8    | 8 | 3       | 3               | Skip   | [0,1,2,3,1,2,2,3,3,4] | 3 |
| 9    | 9 | 4       | 3               | Place at 4 | [0,1,2,3,4,2,2,3,3,4] | 4 |

**Output:** `5`, `nums = [0,1,2,3,4, ...]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

| Edge Case | Input | Expected Output |
|-----------|-------|-----------------|
| Empty array | `[]` | `0` |
| Single element | `[1]` | `1` |
| All duplicates | `[2, 2, 2]` | `1` |
| No duplicates | `[1, 2, 3]` | `3` |
| Negative numbers | `[-3, -3, -1, 0]` | `3` |
| Large input | Array of size 30,000 | Correct count |
| All same except last | `[1, 1, 1, 2]` | `2` |

---

# 📚 Key Takeaways

- **Two-pointer technique** is powerful for in-place array modifications
- **Sorted arrays** allow efficient duplicate removal by comparing adjacent elements
- **In-place modification** is crucial for space optimization
- Always handle **edge cases** (empty array, single element)
- The optimal solution runs in **O(n) time and O(1) space**

---

# 🚀 Interview Tips

✅ **Follow-up Questions:**
- Can you solve it without extra space? (Answer: Yes, using two pointers)
- What if the array is not sorted? (Answer: Sort first or use a set, but sorting changes relative order)
- How would you modify this for k duplicates allowed?

⚠️ **Common Pitfalls:**
- Forgetting to handle empty array
- Using extra space unnecessarily
- Not incrementing `uniquePtr` before assignment
- Off-by-one errors in return value

🔄 **Alternative Approaches:**
- Using a `Set` (brute force) - not optimal for space
- Using a stack (unnecessary for this problem)
- Binary search (not applicable here)

💡 **Optimization Insight:**
- The two-pointer approach is optimal because it leverages the **sorted property** to avoid extra space

---

# ✅ Conclusion

The **optimal solution** uses a **two-pointer technique** to remove duplicates **in-place** with **O(n) time and O(1) space**. This approach is efficient, elegant, and interview-ready.

**Key Insight:** In a sorted array, duplicates are adjacent, so we only need to compare each element with the last unique element to determine if it's new.

This problem is a classic example of how **algorithmic patterns** (like two pointers) can optimize solutions by leveraging input properties.