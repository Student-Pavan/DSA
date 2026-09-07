# 📌 496. Next Greater Element I

---

# 📝 Problem Statement

You are given two integer arrays `nums1` and `nums2`. For each element in `nums1`, find the next greater element in `nums2`. The next greater element is the first element in `nums2` that is larger than the current element in `nums1`. If no such element exists, return `-1` for that element.

**Objective**: Implement a function to find the next greater elements for all elements in `nums1` based on their order in `nums2`.

**Input**:
- `nums1`: An array of integers (1 ≤ nums1.length ≤ 1000)
- `nums2`: An array of integers (nums2.length ≥ nums1.length)

**Output**:
- An array where each element is the next greater element for the corresponding element in `nums1`

**Constraints**:
- All integers in `nums1` and `nums2` are unique
- All integers are within the range [-10⁴, 10⁴]

---

# 💡 Intuition

The key insight is to efficiently find the next greater element for each element in `nums1` by leveraging the order of elements in `nums2`. The optimal approach uses a stack to keep track of potential candidates for the next greater element, allowing us to process each element in `nums2` exactly once.

---

# 🐌 Brute Force Approach

## 🔹 Approach

For each element in `nums1`, we search through `nums2` to find the first element that is larger than the current element. This involves nested loops, resulting in a time complexity of O(n*m), where n is the length of `nums1` and m is the length of `nums2`.

---

## 🔹 Algorithm

1. Initialize an empty result array.
2. For each element in `nums1`:
   - Search through `nums2` to find the first element larger than the current element.
   - If found, add it to the result array.
   - If not found, add `-1` to the result array.
3. Return the result array.

---

## 🔹 Code

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int current = nums1[i];
            boolean found = false;

            for (int num : nums2) {
                if (num == current) {
                    found = true;
                } else if (found && num > current) {
                    result[i] = num;
                    break;
                }
            }

            if (!found || result[i] == 0) {
                result[i] = -1;
            }
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `nums1 = [4,1,2]` and `nums2 = [1,3,4,2]`.

| Iteration | Current Element | Found in nums2 | Next Greater Element | Result |
|-----------|------------------|----------------|-----------------------|--------|
| 1         | 4                | Yes            | 4 is not greater than itself | Search continues |
|           |                  |                | 2 is not greater than 4 | -1 |
| 2         | 1                | Yes            | 3 is greater than 1 | 3 |
| 3         | 2                | Yes            | No element greater than 2 after it | -1 |

Final result: `[3, -1, -1]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n*m) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

We use a stack to keep track of elements for which we haven't found the next greater element yet. As we iterate through `nums2`, we compare each element with the elements in the stack. If the current element is greater than the top of the stack, it becomes the next greater element for the top of the stack. We continue this process until the stack is empty or the current element is no longer greater than the top of the stack. This approach ensures that each element is pushed and popped from the stack exactly once, resulting in an O(n) time complexity.

---

## 🔹 Why This Works

The stack helps us efficiently find the next greater element by maintaining a list of elements that are waiting for their next greater element. By processing each element in `nums2` once and using the stack to keep track of potential candidates, we ensure optimal performance.

---

## 🔹 Algorithm

1. Initialize a stack and a hash map.
2. Iterate through each element in `nums2`:
   - While the stack is not empty and the current element is greater than the top of the stack:
     - Pop the top element from the stack and add it to the hash map with the current element as its next greater element.
   - Push the current element onto the stack.
3. After processing all elements, any remaining elements in the stack do not have a next greater element, so we add them to the hash map with `-1`.
4. Iterate through `nums1` and use the hash map to find the next greater elements.

---

## 🔹 Code

```java
import java.util.HashMap;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `nums1 = [4,1,2]` and `nums2 = [1,3,4,2]`.

| Iteration | Current Element | Stack State | Map State |
|-----------|------------------|-------------|-----------|
| 1         | 1                | [1]         | {}        |
| 2         | 3                | [1, 3]      | {1: 3}    |
| 3         | 4                | [1, 3, 4]   | {1: 3}    |
| 4         | 2                | [1, 2]      | {1: 3, 3: 4} |

After processing all elements, the stack contains `[1, 2]`, which are mapped to `-1`.

| Element | Next Greater Element |
|---------|-----------------------|
| 4       | 4 is not greater than itself | -1 |
| 1       | 3                       | 3 |
| 2       | No element greater than 2 after it | -1 |

Final result: `[3, -1, -1]`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n + m) |
| Space Complexity | O(n + m) |

---

# 🔍 Edge Cases

- `nums1` is empty: Return an empty array.
- `nums1` contains elements not in `nums2`: This is not possible according to the problem constraints.
- `nums2` is in descending order: All elements in `nums1` will have `-1` as their next greater element.
- `nums2` is in ascending order: Each element in `nums1` will have the next element in `nums2` as its next greater element.
- `nums1` contains duplicates: This is not possible according to the problem constraints.

---

# 📚 Key Takeaways

- The brute force approach is straightforward but inefficient for large inputs.
- The optimal approach leverages a stack to efficiently find the next greater element, reducing the time complexity significantly.
- Understanding the problem constraints is crucial for choosing the right approach.
- The optimal approach demonstrates the importance of using auxiliary data structures to optimize performance.

---

# 🚀 Interview Tips

- Discuss the time and space complexity of both approaches.
- Ask if the elements in `nums1` are guaranteed to be in `nums2`.
- Consider follow-up questions about handling duplicates or larger input sizes.
- Be prepared to explain why the stack-based approach is more efficient.

---

# ✅ Conclusion

The optimal approach using a stack is significantly more efficient than the brute force approach, especially for larger input sizes. By understanding the problem constraints and leveraging auxiliary data structures, we can achieve an optimal solution that is both time and space efficient.