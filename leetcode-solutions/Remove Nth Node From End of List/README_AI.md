# 📌 Remove Nth Node From End of List

---

# 📝 Problem Statement

Given the `head` of a linked list, remove the `n-th` node from the end of the list and return its head.

**Constraints:**
- The number of nodes in the list is `sz`.
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`

**Example:**
```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Explanation: The 2nd node from the end is 4, which is removed.
```

**Note:**
- You must solve this problem in **one pass** for optimal performance.

---

# 💡 Intuition

The key insight is that removing the `n-th` node from the end requires knowing the length of the list to identify the target node's position from the start. However, calculating the length first requires a full traversal, and then a second traversal to reach the node before the target.

A more efficient approach uses **two pointers** with a fixed gap of `n` nodes. By moving both pointers until the faster one reaches the end, the slower pointer will be positioned just before the node to remove. This allows removal in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Calculate the length** of the linked list by traversing it completely.
2. **Determine the position** of the node to remove from the start: `length - n`.
3. **Traverse again** to the node before the target and adjust pointers to skip the target node.
4. Handle edge cases (e.g., removing the head node).

This approach requires **two full traversals** of the list.

---

## 🔹 Algorithm

1. Initialize a counter to calculate the length of the list.
2. Traverse the list to compute its length.
3. Compute the position of the node to remove: `pos = length - n`.
4. If `pos == 0`, remove the head node.
5. Otherwise, traverse to the `(pos - 1)`-th node and adjust its `next` pointer to skip the target node.
6. Return the head of the modified list.

---

## 🔹 Code

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Step 1: Calculate the length of the list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Step 2: Find the position of the node to remove
        int pos = length - n;

        // Edge case: Remove the head node
        if (pos == 0) {
            return head.next;
        }

        // Step 3: Traverse to the node before the target
        current = head;
        for (int i = 0; i < pos - 1; i++) {
            current = current.next;
        }

        // Step 4: Remove the target node
        current.next = current.next.next;

        return head;
    }
}
```

---

## 🔹 Dry Run

**Input:** `head = [1,2,3,4,5]`, `n = 2`

| Step | Action | Current Node | Length | Position (pos) | State |
|------|--------|--------------|--------|----------------|-------|
| 1    | Calculate length | 1 → 2 → 3 → 4 → 5 | 5 | - | - |
| 2    | Compute `pos = length - n` | - | - | 3 | - |
| 3    | Traverse to `pos - 1 = 2` | 1 → 2 → 3 | - | - | - |
| 4    | Remove node at `pos` (4) | 1 → 2 → 3 → 5 | - | - | Output: [1,2,3,5] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(L) (two passes) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use **two pointers** (`fast` and `slow`) with a gap of `n` nodes:
1. Move `fast` `n` nodes ahead of `slow`.
2. Move both pointers until `fast` reaches the last node.
3. `slow` will now be just before the node to remove.
4. Adjust `slow.next` to skip the target node.

This approach requires **only one pass** through the list.

---

## 🔹 Why This Works

- The gap of `n` nodes ensures that when `fast` reaches the end, `slow` is `n` nodes behind.
- This positions `slow` just before the node to remove, allowing O(1) pointer adjustment.
- A **dummy node** is used to handle edge cases (e.g., removing the head).

---

## 🔹 Algorithm

1. Create a dummy node pointing to `head`.
2. Initialize `fast` and `slow` at the dummy node.
3. Move `fast` `n` nodes ahead.
4. Move both pointers until `fast.next` is `null`.
5. Adjust `slow.next` to skip the target node.
6. Return `dummy.next`.

---

## 🔹 Code

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n nodes ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the target node
        slow.next = slow.next.next;

        return dummy.next;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `head = [1,2,3,4,5]`, `n = 2`

| Step | Action | Fast | Slow | State |
|------|--------|------|------|-------|
| 1    | Initialize | dummy → 1 | dummy → 1 | - |
| 2    | Move `fast` 2 nodes ahead | 3 | dummy → 1 | - |
| 3    | Move both until `fast.next` is `null` | 5 | 3 | - |
| 4    | Remove node after `slow` (4) | - | - | Output: [1,2,3,5] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(L) (one pass) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

| Case | Input | Expected Output | Explanation |
|------|-------|-----------------|-------------|
| Remove head | `[1,2,3]`, `n = 3` | `[2,3]` | Target is the first node. |
| Remove tail | `[1,2,3]`, `n = 1` | `[1,2]` | Target is the last node. |
| Single node | `[1]`, `n = 1` | `[]` | Only node is removed. |
| All nodes same | `[1,1,1]`, `n = 2` | `[1,1]` | Remove middle duplicate. |

---

# 📚 Key Takeaways

1. **Two-pointer technique** is powerful for linked list problems requiring positional access.
2. **Dummy nodes** simplify edge cases (e.g., removing the head).
3. **Single-pass solutions** are preferred for efficiency.
4. **Fixed-gap pointers** can replace length calculations.

---

# 🚀 Interview Tips

- **Follow-up:** Can you solve this without a dummy node? (Hint: Handle head removal separately.)
- **Common Pitfall:** Forgetting to handle the case where `n == length` (removing the head).
- **Alternative:** Recursive approach (less efficient due to stack space).
- **Optimization:** The two-pointer method is optimal for one-pass constraints.

---

# ✅ Conclusion

The **optimal two-pointer approach** efficiently removes the `n-th` node from the end in **one pass**, making it ideal for interviews. The brute force method, while intuitive, requires two passes and is less efficient. Mastering the two-pointer technique is crucial for solving linked list problems under time constraints.