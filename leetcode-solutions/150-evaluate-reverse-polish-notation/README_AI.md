# 150. Evaluate Reverse Polish Notation

---

# 📝 Problem Statement

Evaluate the value of an arithmetic expression in Reverse Polish Notation (RPN).

Valid operators are `+`, `-`, `*`, and `/`. Each operand may be an integer or another expression.

**Constraints:**
- `1 <= tokens.length <= 10^4`
- `tokens[i]` is either an operator or an integer in the range `[-200, 200]`

---

# 💡 Intuition

The key insight is that RPN expressions are evaluated using a stack. When we encounter an operator, we pop the top two elements from the stack, apply the operation, and push the result back. This approach efficiently handles operator precedence without needing parentheses.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Initialize an empty stack to store operands.
2. Iterate through each token in the input array.
3. If the token is an operand, push it onto the stack.
4. If the token is an operator, pop the top two elements from the stack.
5. Apply the operation to these elements and push the result back onto the stack.
6. After processing all tokens, the remaining element in the stack is the result.

## 🔹 Algorithm

1. Create an empty stack.
2. For each token in tokens:
   - If token is an operand, push to stack.
   - Else, pop two elements, apply operation, push result.
3. Return the remaining element in stack.

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") &&
                !token.equals("*") && !token.equals("/")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
```

## 🔹 Dry Run

Let's evaluate `["2","1","+","3","*"]`:

| Step | Token | Stack | Action |
|------|-------|-------|--------|
| 1    | "2"   | [2]   | Push 2 |
| 2    | "1"   | [2,1] | Push 1 |
| 3    | "+"   | [3]   | Pop 1, pop 2, push 2+1=3 |
| 4    | "3"   | [3,3] | Push 3 |
| 5    | "*"   | [9]   | Pop 3, pop 3, push 3*3=9 |

Final result: 9

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

The brute force approach is already optimal for this problem as it processes each token exactly once and uses a stack that grows linearly with the input size.

## 🔹 Why This Works

This approach efficiently handles operator precedence by processing operations as they appear in the RPN expression, which inherently respects the order of operations. The stack ensures that operands are always available when needed for operations.

## 🔹 Algorithm

1. Initialize an empty stack.
2. For each token:
   - If operand, push to stack.
   - If operator, pop two operands, apply operation, push result.
3. Return the final stack value.

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!"+-*/".contains(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }
}
```

## 🔹 Detailed Dry Run

Let's evaluate `["4","13","5","/","+"]`:

| Step | Token | Stack | Action |
|------|-------|-------|--------|
| 1    | "4"   | [4]   | Push 4 |
| 2    | "13"  | [4,13]| Push 13 |
| 3    | "5"   | [4,13,5] | Push 5 |
| 4    | "/"   | [4,2] | Pop 5, pop 13, push 13/5=2 |
| 5    | "+"   | [6]   | Pop 2, pop 4, push 4+2=6 |

Final result: 6

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Single operand: `["5"]` → 5
- Division with negative numbers: `["-5","-2","/"]` → 2
- Large numbers: `["1000000000","1000000000","*"]` → 1000000000000000000
- Mixed operations: `["10","6","9","3","+","-11","*","/","*","17","+","5","+"]` → 22

---

# 📚 Key Takeaways

- RPN evaluation is naturally handled by a stack.
- Operator precedence is implicitly handled by the order of operations.
- The stack approach ensures correct evaluation with minimal space overhead.

---

# 🚀 Interview Tips

- Be prepared to explain why a stack is the optimal data structure for this problem.
- Practice handling edge cases, especially division operations.
- Consider discussing time and space complexity trade-offs.

---

# ✅ Conclusion

The optimal solution using a stack provides an efficient and straightforward way to evaluate RPN expressions. The key insight is recognizing that the stack naturally handles the order of operations required for correct evaluation.