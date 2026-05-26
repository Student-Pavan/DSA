# 📌 Design Linked List

---

# 📝 Problem Statement

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.

A node in a singly linked list should have two attributes: `val` and `next`. `val` is the value of the current node, and `next` is a pointer/reference to the next node.

If you want to use the doubly linked list, you will need one more attribute `prev` to indicate the previous node in the linked list. Assume all nodes in the linked list are **0-indexed**.

Implement the `MyLinkedList` class:

- `MyLinkedList()` Initializes the `MyLinkedList` object.
- `int get(int index)` Get the value of the `index`-th node in the linked list. If the index is invalid, return `-1`.
- `void addAtHead(int val)` Add a node of value `val` before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
- `void addAtTail(int val)` Append a node of value `val` to the last element of the linked list.
- `void addAtIndex(int index, int val)` Add a node of value `val` before the `index`-th node in the linked list. If `index` equals the length of the linked list, the node will be appended to the end of the linked list. If `index` is greater than the length, the node **will not be inserted**.
- `void deleteAtIndex(int index)` Delete the `index`-th node in the linked list, if the index is valid.

### Constraints:
- `0 <= index, val <= 1000`
- Please do not use the built-in `LinkedList` library.
- At most `2000` calls will be made to `get`, `addAtHead`, `addAtTail`, `addAtIndex`, and `deleteAtIndex`.

---

# 💡 Intuition

The key insight is to understand how linked lists work under the hood. Unlike arrays, linked lists do not allow random access, so we must traverse the list to reach a specific index. This makes operations like `get`, `addAtIndex`, and `deleteAtIndex` potentially O(n) in time complexity.

We can optimize certain operations (like `addAtHead` and `addAtTail`) to O(1) by maintaining head and tail pointers. However, for simplicity and consistency, we'll explore both a **singly linked list** (brute force) and a **doubly linked list** (optimal) approach.

The doubly linked list allows us to traverse backward, which can be useful in certain scenarios, though it increases space complexity slightly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

We'll implement a **singly linked list** with only a `head` pointer. This is the simplest form of a linked list but requires traversal from the head for most operations.

- **`get(index)`**: Traverse from the head until we reach the desired index.
- **`addAtHead(val)`**: Insert a new node at the head.
- **`addAtTail(val)`**: Traverse to the end of the list and append the new node.
- **`addAtIndex(index, val)`**: Traverse to the node before the desired index and insert the new node.
- **`deleteAtIndex(index)`**: Traverse to the node before the desired index and update the `next` pointer to skip the node to be deleted.

---

## 🔹 Algorithm

1. **Initialization**: Set `head = null` and `size = 0`.
2. **`get(index)`**:
   - If `index` is invalid, return `-1`.
   - Traverse from `head` to the `index`-th node.
   - Return the node's value.
3. **`addAtHead(val)`**:
   - Create a new node with `val`.
   - Set `newNode.next = head`.
   - Update `head = newNode`.
   - Increment `size`.
4. **`addAtTail(val)`**:
   - If the list is empty, call `addAtHead(val)`.
   - Otherwise, traverse to the last node and set `lastNode.next = newNode`.
   - Increment `size`.
5. **`addAtIndex(index, val)`**:
   - If `index == 0`, call `addAtHead(val)`.
   - If `index == size`, call `addAtTail(val)`.
   - Otherwise, traverse to the `(index-1)`-th node and insert the new node.
   - Increment `size`.
6. **`deleteAtIndex(index)`**:
   - If `index` is invalid, do nothing.
   - If `index == 0`, update `head = head.next`.
   - Otherwise, traverse to the `(index-1)`-th node and update `prevNode.next` to skip the `index`-th node.
   - Decrement `size`.

---

## 🔹 Code

```java
class MyLinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addAtTail(int val) {
        if (size == 0) {
            addAtHead(val);
            return;
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(val);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        ListNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }
}
```

---

## 🔹 Dry Run

Let’s simulate the following operations:

1. `addAtHead(1)`
2. `addAtTail(3)`
3. `addAtIndex(1, 2)`
4. `get(1)`
5. `deleteAtIndex(1)`
6. `get(1)`

### Initial State:
- `head = null`, `size = 0`

| Step | Operation | Head | List State | Size | Output |
|------|-----------|------|------------|------|--------|
| 1    | `addAtHead(1)` | `1` | `1 -> null` | 1 | - |
| 2    | `addAtTail(3)` | `1` | `1 -> 3 -> null` | 2 | - |
| 3    | `addAtIndex(1, 2)` | `1` | `1 -> 2 -> 3 -> null` | 3 | - |
| 4    | `get(1)` | `1` | `1 -> 2 -> 3 -> null` | 3 | `2` |
| 5    | `deleteAtIndex(1)` | `1` | `1 -> 3 -> null` | 2 | - |
| 6    | `get(1)` | `1` | `1 -> 3 -> null` | 2 | `3` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) for `get`, `addAtIndex`, `deleteAtIndex`; O(1) for `addAtHead` |
| Space Complexity | O(n) (for storing the list) |

---

# ⚡ Optimal Approach

## 🔹 Approach

We'll implement a **doubly linked list** with both `head` and `tail` pointers. This allows us to optimize `addAtTail` to O(1) time complexity.

- **`get(index)`**: Traverse from the head (or tail if `index` is closer to the end) to the desired node.
- **`addAtHead(val)`**: Insert a new node at the head in O(1) time.
- **`addAtTail(val)`**: Insert a new node at the tail in O(1) time.
- **`addAtIndex(index, val)`**: Traverse from the nearest end (head or tail) to the desired index.
- **`deleteAtIndex(index)`**: Traverse from the nearest end to the desired index and update both `prev` and `next` pointers.

---

## 🔹 Why This Works

The doubly linked list allows bidirectional traversal, which can reduce the average time complexity for operations like `get` and `deleteAtIndex` when the index is closer to the tail. However, in the worst case, it remains O(n).

The `tail` pointer ensures that `addAtTail` is O(1), which is a significant improvement over the singly linked list approach.

---

## 🔹 Algorithm

1. **Initialization**: Set `head = null`, `tail = null`, and `size = 0`.
2. **`get(index)`**:
   - If `index` is invalid, return `-1`.
   - Traverse from the head (or tail if `index > size/2`) to the `index`-th node.
   - Return the node's value.
3. **`addAtHead(val)`**:
   - Create a new node with `val`.
   - If the list is empty, set `head = tail = newNode`.
   - Otherwise, set `newNode.next = head`, `head.prev = newNode`, and `head = newNode`.
   - Increment `size`.
4. **`addAtTail(val)`**:
   - Create a new node with `val`.
   - If the list is empty, set `head = tail = newNode`.
   - Otherwise, set `tail.next = newNode`, `newNode.prev = tail`, and `tail = newNode`.
   - Increment `size`.
5. **`addAtIndex(index, val)`**:
   - If `index == 0`, call `addAtHead(val)`.
   - If `index == size`, call `addAtTail(val)`.
   - Otherwise, traverse from the nearest end to the `(index-1)`-th node and insert the new node.
   - Increment `size`.
6. **`deleteAtIndex(index)`**:
   - If `index` is invalid, do nothing.
   - If `index == 0`, update `head = head.next` and set `head.prev = null` if `head` exists.
   - If `index == size-1`, update `tail = tail.prev` and set `tail.next = null` if `tail` exists.
   - Otherwise, traverse to the `index`-th node and update `prevNode.next` and `nextNode.prev`.
   - Decrement `size`.

---

## 🔹 Code

```java
class MyLinkedList {
    private static class ListNode {
        int val;
        ListNode prev;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode newNode = new ListNode(val);
        ListNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return;
        }
        if (index == size - 1) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
            return;
        }
        ListNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }
}
```

---

## 🔹 Detailed Dry Run

Let’s simulate the same operations as before:

1. `addAtHead(1)`
2. `addAtTail(3)`
3. `addAtIndex(1, 2)`
4. `get(1)`
5. `deleteAtIndex(1)`
6. `get(1)`

### Initial State:
- `head = null`, `tail = null`, `size = 0`

| Step | Operation | Head | Tail | List State | Size | Output |
|------|-----------|------|------|------------|------|--------|
| 1    | `addAtHead(1)` | `1` | `1` | `1 <-> null` | 1 | - |
| 2    | `addAtTail(3)` | `1` | `3` | `1 <-> 3 <-> null` | 2 | - |
| 3    | `addAtIndex(1, 2)` | `1` | `3` | `1 <-> 2 <-> 3 <-> null` | 3 | - |
| 4    | `get(1)` | `1` | `3` | `1 <-> 2 <-> 3 <-> null` | 3 | `2` |
| 5    | `deleteAtIndex(1)` | `1` | `3` | `1 <-> 3 <-> null` | 2 | - |
| 6    | `get(1)` | `1` | `3` | `1 <-> 3 <-> null` | 2 | `3` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) for `get`, `addAtIndex`, `deleteAtIndex` (worst case); O(1) for `addAtHead`, `addAtTail` |
| Space Complexity | O(n) (for storing the list) |

---

# 🔍 Edge Cases

- **Empty List**: Operations like `get(0)` or `deleteAtIndex(0)` should handle gracefully.
- **Single Node**: Deleting the only node should reset `head` and `tail` to `null`.
- **Invalid Index**: `get`, `addAtIndex`, and `deleteAtIndex` should handle negative or out-of-bounds indices.
- **Large Inputs**: Ensure the implementation handles the upper constraint of `2000` calls efficiently.
- **Consecutive Operations**: Ensure `addAtHead` followed by `addAtTail` maintains correct pointers.

---

# 📚 Key Takeaways

1. **Linked List Basics**: Understand the difference between singly and doubly linked lists.
2. **Pointer Management**: Carefully update `next` and `prev` pointers to avoid breaking the list.
3. **Edge Cases**: Always handle empty lists, single-node lists, and invalid indices.
4. **Optimization**: Use `head` and `tail` pointers to optimize `addAtHead` and `addAtTail` to O(1).
5. **Bidirectional Traversal**: Doubly linked lists allow traversal from both ends, which can improve average-case performance.

---

# 🚀 Interview Tips

- **Clarify Requirements**: Ask whether the interviewer expects a singly or doubly linked list.
- **Discuss Trade-offs**: Mention the space-time trade-off between singly and doubly linked lists.
- **Follow-up Questions**:
  - How would you implement a circular linked list?
  - Can you reverse the linked list in-place?
  - How would you detect a cycle in the linked list?
- **Common Pitfalls**:
  - Forgetting to update `size` after insertions/deletions.
  - Not handling `null` pointers correctly (e.g., when deleting the head or tail).
  - Off-by-one errors in index calculations.

---

# ✅ Conclusion

The **doubly linked list** is the optimal approach for this problem due to its O(1) time complexity for `addAtHead` and `addAtTail`, and the ability to traverse from both ends. While the worst-case time complexity for other operations remains O(n), the average case can be improved by choosing the nearest traversal direction.

This problem is a great exercise in understanding linked list fundamentals, pointer manipulation, and edge case handling—key skills for technical interviews at top-tier companies.