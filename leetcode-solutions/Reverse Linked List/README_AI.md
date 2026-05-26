```markdown
# 🔁 Reverse Linked List

---

# 📝 Problem Statement

Given the head of a singly linked list, reverse the list and return the new head.

**Objective:**
Reverse the direction of the linked list so that the first node becomes the last, and the last node becomes the first.

**Input:**
- `head`: The head node of a singly linked list (may be `null` for empty list)

**Output:**
- The head node of the reversed linked list

**Constraints:**
- The number of nodes in the list is in the range `[0, 5000]`
- `-5000 <= Node.val <= 5000`

**Example:**
```
Input: 1 -> 2 -> 3 -> 4 -> 5
Output: 5 -> 4 -> 3 -> 2 -> 1
```

---

# 💡 Intuition

Reversing a linked list requires changing the direction of the `next` pointers. Instead of each node pointing to the next node, it should point to the previous node.

The key insight is that we can reverse the links **in-place** by maintaining three pointers:
- `prev`: to keep track of the previous node (which will become the next node after reversal)
- `current`: the current node being processed
- `next`: to temporarily store the next node before we change the `current.next` pointer

This approach allows us to reverse the list in a single pass without using extra space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach would involve:
1. Traverse the original list and store all node values in an array.
2. Traverse the list again from the end of the array to the beginning, updating node values.
3. Return the head.

However, this approach modifies node **values** rather than **pointers**, which is not ideal for linked list reversal. It also uses extra space for the array.

We present a **pointer-based brute force** that still uses extra space: create a new reversed list by iterating through the original and prepending nodes.

---

## 🔹 Algorithm

1. Initialize an empty list `reversed` to store the reversed nodes.
2. Traverse the original list from `head` to `null`.
3. For each node, create a new node with the same value and insert it at the **beginning** of `reversed`.
4. Return the head of `reversed`.

> ⚠️ This creates new nodes and uses O(n) extra space.

---

## 🔹 Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode reversed = null;
        ListNode current = head;

        while (current != null) {
            // Create a new node with current value
            ListNode newNode = new ListNode(current.val);
            // Insert at the beginning of reversed list
            newNode.next = reversed;
            reversed = newNode;
            // Move to next node
            current = current.next;
        }

        return reversed;
    }
}
```

---

## 🔹 Dry Run

**Input:** `1 -> 2 -> 3 -> null`

| Step | Current Node | Action | Reversed List State |
|------|--------------|--------|---------------------|
| 1    | 1            | Create node(1), prepend | `1 -> null` |
| 2    | 2            | Create node(2), prepend | `2 -> 1 -> null` |
| 3    | 3            | Create node(3), prepend | `3 -> 2 -> 1 -> null` |
| 4    | null         | Exit loop | `3 -> 2 -> 1 -> null` |

**Output:** `3 -> 2 -> 1 -> null`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

> ❗ Uses extra space for new nodes.

---

# ⚡ Optimal Approach

## 🔹 Approach

Reverse the linked list **in-place** using three pointers:
- `prev`: tracks the previous node (initially `null`)
- `current`: the current node being processed (initially `head`)
- `next`: temporarily stores the next node before we modify `current.next`

We iterate through the list, reversing the `next` pointer of each node to point to `prev`, then move the pointers forward.

This approach reverses the list in a single pass with **O(1)** extra space.

---

## 🔹 Why This Works

- We are not creating new nodes; we are reusing existing ones.
- By reversing the `next` pointers, we effectively change the direction of traversal.
- The `prev` pointer becomes the new head at the end.
- No extra space is used beyond a few variables.

---

## 🔹 Algorithm

1. Initialize `prev = null`, `current = head`.
2. While `current != null`:
   - Store `next = current.next`
   - Reverse the link: `current.next = prev`
   - Move `prev = current`
   - Move `current = next`
3. Return `prev` (new head)

---

## 🔹 Code

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode next = current.next; // Store next node
            current.next = prev;          // Reverse the link
            prev = current;               // Move prev forward
            current = next;               // Move current forward
        }

        return prev; // prev is the new head
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `1 -> 2 -> 3 -> null`

| Step | Current | Next (temp) | Action | prev | current | List State |
|------|---------|-------------|--------|------|---------|------------|
| 1    | 1       | 2           | `1.next = null` | 1 | 2 | `1 -> null` |
| 2    | 2       | 3           | `2.next = 1` | 2 | 3 | `2 -> 1 -> null` |
| 3    | 3       | null        | `3.next = 2` | 3 | null | `3 -> 2 -> 1 -> null` |
| 4    | null    | -           | Exit loop | 3 | null | `3 -> 2 -> 1 -> null` |

**Output:** `3 -> 2 -> 1 -> null`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|-----------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

> ✅ Optimal in both time and space.

---

# 🔍 Edge Cases

| Edge Case | Expected Output |
|---------|-----------------|
| Empty list (`null`) | `null` |
| Single node (`1 -> null`) | `1 -> null` |
| Two nodes (`1 -> 2 -> null`) | `2 -> 1 -> null` |
| All nodes same value (`5 -> 5 -> 5`) | `5 -> 5 -> 5` |
| Large list (5000 nodes) | Reversed list |

---

# 📚 Key Takeaways

- **Pointer manipulation** is key in linked list problems.
- **In-place reversal** avoids extra space and is preferred.
- The three-pointer technique (`prev`, `current`, `next`) is a classic pattern for linked list reversal.
- Always handle `null` cases (empty list, single node).
- Reversing a linked list is a fundamental operation used in many advanced problems (e.g., palindrome check, cycle detection).

---

# 🚀 Interview Tips

- **Follow-up:** Can you reverse the list recursively? (Yes, but uses O(n) stack space)
- **Common pitfall:** Forgetting to store `next` before modifying `current.next` → leads to lost nodes.
- **Alternative:** Recursive approach is elegant but less efficient due to stack space.
- **Optimization discussion:** Iterative in-place reversal is optimal for space and time.
- **Test with:** Empty list, single node, even/odd length lists.

---

# ✅ Conclusion

The **optimal solution** uses an **iterative in-place reversal** with three pointers, achieving **O(n) time and O(1) space**. This is the preferred method in interviews due to its efficiency and clarity.

The key insight is that we can reverse the direction of the links by carefully updating `next` pointers while traversing the list, without needing extra memory.

Mastering this pattern will help you tackle more complex linked list problems with confidence.
```