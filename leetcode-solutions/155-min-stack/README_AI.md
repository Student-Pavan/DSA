```markdown
# 📌 Problem Name
155-min-stack

---

# 📝 Problem Statement
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

**Constraints:**
- `-2^31 <= val <= 2^31 - 1`
- Methods `pop`, `top` and `getMin` operations will always be called on non-empty stacks.
- At most `3 * 10^4` calls will be made to `push`, `pop`, `top`, and `getMin`.

---

# 💡 Intuition
The key insight is to maintain a separate stack that tracks the minimum value at each state of the main stack. This allows us to retrieve the minimum value in constant time by simply peeking at the top of the min stack.

---

# 🐌 Brute Force Approach

## 🔹 Approach
The brute force approach would be to scan the entire stack whenever `getMin` is called to find the minimum value. This would be inefficient for large stacks.

---

## 🔹 Algorithm
1. Initialize a main stack to store all elements.
2. For `push` operation:
   - Push the value onto the main stack.
3. For `pop` operation:
   - Remove the top element from the main stack.
4. For `top` operation:
   - Return the top element of the main stack.
5. For `getMin` operation:
   - Iterate through the main stack to find the minimum value.
   - Return the minimum value.

---

## 🔹 Code
```java
class MinStack {
    private Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        int min = stack.peek();
        for (int num : stack) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
```

---

## 🔹 Dry Run

| Iteration | Operation | Stack State | Min Value |
|-----------|-----------|-------------|-----------|
| 1         | push(3)   | [3]         | 3         |
| 2         | push(5)   | [3, 5]      | 3         |
| 3         | push(2)   | [3, 5, 2]   | 2         |
| 4         | push(1)   | [3, 5, 2, 1]| 1         |
| 5         | getMin    | [3, 5, 2, 1]| 1         |
| 6         | pop       | [3, 5, 2]   | 2         |
| 7         | getMin    | [3, 5, 2]   | 2         |
| 8         | pop       | [3, 5]      | 3         |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) for `getMin`, O(1) for other operations |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach
Use two stacks: one to store all elements and another to store the minimum values. The min stack will always have the current minimum at its top.

---

## 🔹 Why This Works
By maintaining a separate min stack, we can always access the current minimum in constant time. The min stack ensures that we only store the minimum values at each state of the main stack.

---

## 🔹 Algorithm
1. Initialize two stacks: one for the main stack and one for the min stack.
2. For `push` operation:
   - Push the value onto the main stack.
   - If the min stack is empty or the value is less than or equal to the current min, push the value onto the min stack.
3. For `pop` operation:
   - Remove the top element from the main stack.
   - If the popped value is equal to the current min, remove the top element from the min stack.
4. For `top` operation:
   - Return the top element of the main stack.
5. For `getMin` operation:
   - Return the top element of the min stack.

---

## 🔹 Code
```java
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

---

## 🔹 Detailed Dry Run

| Iteration | Operation | Main Stack | Min Stack | Min Value |
|-----------|-----------|------------|-----------|-----------|
| 1         | push(3)   | [3]        | [3]       | 3         |
| 2         | push(5)   | [3, 5]     | [3]       | 3         |
| 3         | push(2)   | [3, 5, 2]  | [3, 2]    | 2         |
| 4         | push(1)   | [3, 5, 2, 1]| [3, 2, 1] | 1         |
| 5         | getMin    | [3, 5, 2, 1]| [3, 2, 1] | 1         |
| 6         | pop       | [3, 5, 2]  | [3, 2]    | 2         |
| 7         | getMin    | [3, 5, 2]  | [3, 2]    | 2         |
| 8         | pop       | [3, 5]     | [3]       | 3         |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(1) for all operations |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases
- Pushing negative numbers.
- Pushing duplicate minimum values.
- Popping the minimum value.
- Getting the minimum value from a single-element stack.
- Handling a large number of operations.

---

# 📚 Key Takeaways
- Maintaining a separate stack for minimum values allows constant time retrieval.
- The min stack only stores values that are less than or equal to the current minimum.
- This approach ensures efficient operations for all stack methods.

---

# 🚀 Interview Tips
- Discuss the trade-offs between time and space complexity.
- Mention that the optimal approach uses extra space to achieve constant time operations.
- Be prepared to explain how the min stack works in detail.

---

# ✅ Conclusion
The optimal approach using two stacks provides constant time operations for all methods, making it highly efficient for large numbers of operations. The key insight is maintaining a separate stack to track minimum values, ensuring quick access to the minimum element at any point in the stack's history.
```