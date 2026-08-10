# Implement Stack using Queues

---

# 📝 Problem Statement

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (`push`, `pop`, `top`, and `empty`).

**Constraints:**
- You must use only standard operations of a queue.
- You may assume that all operations are valid (for example, no `pop` or `top` operations will be called on an empty stack).

---

# 💡 Intuition

The key insight is that a stack requires LIFO behavior, while a queue provides FIFO behavior. To simulate stack operations using queues, we need to ensure that the most recently pushed element is always at the front of the queue, so it can be accessed or removed first. This requires transferring elements between queues during push operations.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Use two queues: `queue1` and `queue2`.
2. For `push` operation:
   - Add the new element to `queue1`.
   - Move all existing elements from `queue2` to `queue1`.
   - Swap the names of `queue1` and `queue2` so that `queue2` always contains the elements in stack order.
3. For `pop` operation:
   - Simply remove and return the front element of `queue2`.
4. For `top` operation:
   - Return the front element of `queue2` without removing it.
5. For `empty` operation:
   - Check if `queue2` is empty.

## 🔹 Algorithm

1. Initialize two queues: `queue1` and `queue2`.
2. **Push(x):**
   - Add `x` to `queue1`.
   - While `queue2` is not empty, remove the front element of `queue2` and add it to `queue1`.
   - Swap `queue1` and `queue2`.
3. **Pop():**
   - Remove and return the front element of `queue2`.
4. **Top():**
   - Return the front element of `queue2`.
5. **Empty():**
   - Return `true` if `queue2` is empty, otherwise `false`.

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue2.remove();
    }

    public int top() {
        return queue2.peek();
    }

    public boolean empty() {
        return queue2.isEmpty();
    }
}
```

## 🔹 Dry Run

Let's dry run the `push` operation with the sequence: `push(1)`, `push(2)`, `push(3)`.

| Step | Action | queue1 | queue2 | Explanation |
|------|--------|---------|---------|--------------|
| 1    | push(1)| [1]     | []      | Add 1 to queue1, swap queues |
| 2    | push(2)| [2]     | [1]     | Add 2 to queue1, move 1 from queue2 to queue1, swap queues |
| 3    | push(3)| [3]     | [2, 1]  | Add 3 to queue1, move 2 and 1 from queue2 to queue1, swap queues |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) for `push`, O(1) for `pop`, `top`, and `empty` |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but with a slight optimization in the `push` operation. Instead of swapping the queues after each push, we can keep `queue2` as the main queue and `queue1` as the auxiliary queue. This way, we avoid the swap operation and directly use `queue2` for stack operations.

## 🔹 Why This Works

By maintaining `queue2` as the main queue, we ensure that the most recently pushed element is always at the front of `queue2`. This allows us to perform stack operations efficiently without the need for a swap operation after each push.

## 🔹 Algorithm

1. Initialize two queues: `queue1` and `queue2`.
2. **Push(x):**
   - Add `x` to `queue1`.
   - While `queue2` is not empty, remove the front element of `queue2` and add it to `queue1`.
   - Move all elements from `queue1` to `queue2`.
3. **Pop():**
   - Remove and return the front element of `queue2`.
4. **Top():**
   - Return the front element of `queue2`.
5. **Empty():**
   - Return `true` if `queue2` is empty, otherwise `false`.

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }
        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }
    }

    public int pop() {
        return queue2.remove();
    }

    public int top() {
        return queue2.peek();
    }

    public boolean empty() {
        return queue2.isEmpty();
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the `push` operation with the sequence: `push(1)`, `push(2)`, `push(3)`.

| Step | Action | queue1 | queue2 | Explanation |
|------|--------|---------|---------|--------------|
| 1    | push(1)| []      | [1]     | Add 1 to queue1, move all elements from queue1 to queue2 |
| 2    | push(2)| [2]     | [1]     | Add 2 to queue1, move 1 from queue2 to queue1, move all elements from queue1 to queue2 |
| 3    | push(3)| [3]     | [2, 1]  | Add 3 to queue1, move 2 and 1 from queue2 to queue1, move all elements from queue1 to queue2 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) for `push`, O(1) for `pop`, `top`, and `empty` |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Stack:** Ensure that `pop`, `top`, and `empty` operations handle an empty stack correctly.
- **Single Element:** Verify that the stack behaves correctly when it contains only one element.
- **Multiple Pushes:** Test the stack with multiple consecutive `push` operations to ensure the elements are ordered correctly.
- **Mixed Operations:** Test a sequence of `push`, `pop`, and `top` operations to ensure the stack maintains the correct order.

---

# 📚 Key Takeaways

- **Queue Operations:** Understanding how to use queue operations to simulate stack behavior is crucial.
- **Efficiency:** The optimal approach ensures that `push` operations are efficient by minimizing the number of transfers between queues.
- **Edge Cases:** Handling edge cases such as an empty stack is essential for a robust implementation.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss how to optimize the solution further or explore alternative approaches using a single queue.
- **Common Pitfalls:** Be aware of the inefficiency of the brute force approach and focus on optimizing the `push` operation.
- **Alternative Approaches:** Consider using a single queue and rotating elements to simulate stack behavior.

---

# ✅ Conclusion

The optimal approach ensures that the stack operations are performed efficiently by maintaining the most recently pushed element at the front of the queue. This approach minimizes the number of transfers between queues and ensures that stack operations are performed in constant time. Understanding the underlying principles of queue operations is essential for implementing stack behavior effectively.