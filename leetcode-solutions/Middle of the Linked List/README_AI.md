# 📌 Middle of the Linked List

---

# 📝 Problem Statement

Given the `head` of a singly linked list, return the **middle node** of the linked list.

If there are **two middle nodes**, return the **second middle node**.

### **Input:**
- `head`: The head node of a singly linked list.

### **Output:**
- The middle node of the linked list.

### **Constraints:**
- The number of nodes in the list is in the range `[1, 100]`.
- Node values are integers in the range `[-100, 100]`.

---

# 💡 Intuition

The key insight is that traversing a linked list requires **O(n)** time, and finding the middle node efficiently without multiple passes is crucial.

- **Brute Force:** Traverse the list once to count nodes, then traverse again to reach the middle.
- **Optimal Approach:** Use the **two-pointer technique (slow and fast pointers)** to find the middle in a single pass.

The two-pointer technique is optimal because it reduces time complexity while maintaining constant space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Count the total number of nodes** in the linked list.
2. **Traverse again** to the middle node using the count.

This approach requires **two full traversals** of the list.

---

## 🔹 Algorithm

1. Initialize a counter `count = 0`.
2. Traverse the list from `head` to `null`, incrementing `count` for each node.
3. Compute the middle index: `middle = count / 2`.
4. Traverse the list again from `head` to the `middle`-th node.
5. Return the node at the `middle` index.

---

## 🔹 Code

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode current = head;

        // Count the number of nodes
        while (current != null) {
            count++;
            current = current.next;
        }

        // Traverse to the middle node
        current = head;
        for (int i = 0; i < count / 2; i++) {
            current = current.next;
        }

        return current;
    }
}
```

---

## 🔹 Dry Run

**Example:** `1 → 2 → 3 → 4 → 5`

| Step | Action | Current Node | Count | Middle Index | Result |
|------|--------|--------------|-------|--------------|--------|
| 1    | Initialize `count = 0` | `1` | 0 | - | - |
| 2    | Traverse to `2` | `2` | 1 | - | - |
| 3    | Traverse to `3` | `3` | 2 | - | - |
| 4    | Traverse to `4` | `4` | 3 | - | - |
| 5    | Traverse to `5` | `5` | 4 | - | - |
| 6    | Traverse to `null` | `null` | 5 | `5 / 2 = 2` | - |
| 7    | Reset `current = head` | `1` | 5 | 2 | - |
| 8    | Move to `2` | `2` | 5 | 2 | - |
| 9    | Move to `3` | `3` | 5 | 2 | **Return `3`** |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) (two passes) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach (Two-Pointer Technique)

## 🔹 Approach

Use **slow and fast pointers**:
- **Slow pointer** moves **one node at a time**.
- **Fast pointer** moves **two nodes at a time**.
- When the fast pointer reaches the end, the slow pointer will be at the middle.

This approach finds the middle in **a single traversal**.

---

## 🔹 Why This Works

- The fast pointer moves **twice as fast** as the slow pointer.
- When the fast pointer reaches the end, the slow pointer will have traversed **half the distance**, landing at the middle.

---

## 🔹 Algorithm

1. Initialize `slow = head` and `fast = head`.
2. While `fast != null` and `fast.next != null`:
   - Move `slow` to `slow.next`.
   - Move `fast` to `fast.next.next`.
3. Return `slow`.

---

## 🔹 Code

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
```

---

## 🔹 Detailed Dry Run

**Example:** `1 → 2 → 3 → 4 → 5`

| Step | Slow | Fast | Action |
|------|------|------|--------|
| 1    | `1`  | `1`  | Initialize |
| 2    | `2`  | `3`  | Move slow to `2`, fast to `3` |
| 3    | `3`  | `5`  | Move slow to `3`, fast to `5` |
| 4    | `3`  | `null` | Fast reaches end, return `slow` (`3`) |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) (single pass) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

| Case | Input | Expected Output |
|------|-------|-----------------|
| Single Node | `1` | `1` |
| Two Nodes | `1 → 2` | `2` |
| Even Nodes | `1 → 2 → 3 → 4` | `3` |
| Odd Nodes | `1 → 2 → 3 → 4 → 5` | `3` |
| Large List | `1 → 2 → ... → 100` | `51` |

---

# 📚 Key Takeaways

- **Two-pointer technique** is optimal for linked list traversal problems.
- **Brute force** works but is inefficient due to multiple passes.
- **Optimal solution** reduces time complexity while maintaining constant space.
- **Edge cases** (single node, even/odd length) must be handled correctly.

---

# 🚀 Interview Tips

- **Follow-up:** Can you solve this in **one pass**? (Answer: Yes, using slow-fast pointers.)
- **Common Pitfall:** Forgetting to handle **even-length lists** (returning the second middle node).
- **Alternative Approach:** Using a **stack** (not optimal due to O(n) space).
- **Optimization Insight:** The two-pointer technique is a **fundamental pattern** for linked list problems.

---

# ✅ Conclusion

The **optimal solution** using the **two-pointer technique** is preferred because:
- It **reduces time complexity** to a single pass.
- It **maintains constant space**, making it memory-efficient.
- It **handles all edge cases** correctly.

**Key Insight:** The fast pointer moves twice as fast, ensuring the slow pointer lands at the middle when the fast pointer reaches the end. This is a **must-know pattern** for linked list problems.