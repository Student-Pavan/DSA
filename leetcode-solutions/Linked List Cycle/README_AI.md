# 📌 Linked List Cycle

---

# 📝 Problem Statement

Given the `head` of a linked list, determine if the linked list contains a cycle.

A cycle exists in a linked list if some node in the list can be reached again by continuously following the `next` pointer.

**Objective:**
Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

**Constraints:**
- The number of nodes in the list is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a valid index in the linked list (used to denote the tail's `next` pointer in LeetCode's test cases).

---

# 💡 Intuition

The key insight is recognizing that a cycle in a linked list creates an infinite loop if traversed indefinitely. To detect a cycle efficiently, we need a method that doesn't rely on infinite traversal or excessive memory usage.

The optimal approach leverages the **Floyd's Tortoise and Hare algorithm**, which uses two pointers moving at different speeds. If there's a cycle, the faster pointer will eventually catch up to the slower one. This approach is both time and space efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A naive approach involves tracking visited nodes using a hash set. As we traverse the linked list, we check if the current node has been visited before. If it has, a cycle exists. This approach is straightforward but uses additional space proportional to the number of nodes.

---

## 🔹 Algorithm

1. Initialize an empty hash set to store visited nodes.
2. Traverse the linked list starting from the `head`.
3. For each node:
   - If the node is already in the set, return `true` (cycle detected).
   - Otherwise, add the node to the set.
4. If the traversal completes without finding a cycle, return `false`.

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }

        return false;
    }
}
```

---

## 🔹 Dry Run

Consider the following linked list with a cycle (nodes are labeled for clarity):
`1 -> 2 -> 3 -> 4 -> 5 -> 2` (5 points back to 2)

| Step | Current Node | Visited Set (Nodes) | Action                     | Result  |
|------|--------------|---------------------|----------------------------|---------|
| 1    | 1            | {}                  | Add 1 to set               |         |
| 2    | 2            | {1}                 | Add 2 to set               |         |
| 3    | 3            | {1, 2}              | Add 3 to set               |         |
| 4    | 4            | {1, 2, 3}           | Add 4 to set               |         |
| 5    | 5            | {1, 2, 3, 4}        | Add 5 to set               |         |
| 6    | 2            | {1, 2, 3, 4, 5}     | 2 is already in set        | **true**|

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses **Floyd's Tortoise and Hare algorithm**, which employs two pointers moving at different speeds:
- **Slow pointer (tortoise)**: Moves one node at a time.
- **Fast pointer (hare)**: Moves two nodes at a time.

If there is a cycle, the fast pointer will eventually meet the slow pointer inside the cycle. If there is no cycle, the fast pointer will reach the end of the list (`null`).

---

## 🔹 Why This Works

- **Cycle Detection**: In a cycle, the fast pointer will eventually "lap" the slow pointer, ensuring they meet.
- **Termination**: If there is no cycle, the fast pointer will reach the end of the list (`null`), terminating the loop.
- **Efficiency**: This approach uses constant space and linear time, making it highly efficient.

---

## 🔹 Algorithm

1. Initialize two pointers, `slow` and `fast`, both pointing to the `head` of the list.
2. Traverse the list:
   - Move `slow` one step forward.
   - Move `fast` two steps forward.
3. If `fast` or `fast.next` becomes `null`, return `false` (no cycle).
4. If `slow` and `fast` meet, return `true` (cycle detected).

---

## 🔹 Code

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
```

---

## 🔹 Detailed Dry Run

Consider the same linked list: `1 -> 2 -> 3 -> 4 -> 5 -> 2` (5 points back to 2)

| Step | Slow Pointer | Fast Pointer | Action                     | Result  |
|------|--------------|--------------|----------------------------|---------|
| 1    | 1            | 2            | Initialize pointers        |         |
| 2    | 2            | 4            | Move slow (1→2), fast (2→4)|         |
| 3    | 3            | 2            | Move slow (2→3), fast (4→2)|         |
| 4    | 4            | 4            | Move slow (3→4), fast (2→4)| **true**|

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(1)        |

---

# 🔍 Edge Cases

- **Empty List**: `head = null` → No cycle.
- **Single Node**: `1 -> null` → No cycle.
- **Single Node with Cycle**: `1 -> 1` → Cycle exists.
- **Large List**: Ensure the algorithm handles the upper constraint (10^4 nodes) efficiently.
- **No Cycle**: Linear list (e.g., `1 -> 2 -> 3 -> null`) → No cycle.

---

# 📚 Key Takeaways

1. **Cycle Detection**: Recognizing cycles in linked lists is a fundamental problem with applications in memory management and graph algorithms.
2. **Two-Pointer Technique**: Floyd's algorithm is a powerful example of using two pointers to achieve optimal performance.
3. **Space Optimization**: The optimal solution avoids extra space, making it suitable for large inputs.
4. **Interview Insight**: Always consider edge cases, such as empty lists or single-node lists, when designing algorithms.

---

# 🚀 Interview Tips

- **Follow-Up Questions**:
  - How would you find the start of the cycle if one exists?
  - Can you modify the algorithm to work with doubly linked lists?
- **Common Pitfalls**:
  - Forgetting to handle edge cases (e.g., `head = null`).
  - Incorrectly initializing the `fast` pointer (e.g., starting at `head` instead of `head.next`).
- **Alternative Approaches**:
  - Using a hash set (brute force) is simpler but less efficient in terms of space.
  - Marking nodes as visited by modifying the `val` field (not recommended for production code).

---

# ✅ Conclusion

The **Floyd's Tortoise and Hare algorithm** is the optimal solution for detecting cycles in a linked list. It efficiently balances time and space complexity, making it ideal for large inputs. The brute force approach, while straightforward, is less efficient due to its linear space usage. Understanding both approaches provides valuable insight into trade-offs in algorithm design.

**Key Insight**: The two-pointer technique is a versatile tool for solving problems involving sequences or linked structures. Always consider it when dealing with traversal problems.