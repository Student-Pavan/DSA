# 📌 Basic Calculator II

---

# 📝 Problem Statement

Given a string `s` representing a valid arithmetic expression containing non-negative integers, the operators `+`, `-`, `*`, `/`, and spaces, evaluate the expression and return the result.

**Constraints:**
- `1 <= s.length() <= 3 * 10^5`
- `s` consists of integers and the operators `+`, `-`, `*`, `/`.
- All integers are non-negative and within the 32-bit signed integer range.
- Division between integers should truncate toward zero (integer division).
- The expression is guaranteed to be valid and contain no division by zero.

**Example 1:**
```
Input: s = "3+2*2"
Output: 7
```

**Example 2:**
```
Input: s = " 3/2 "
Output: 1
```

**Example 3:**
```
Input: s = " 3+5 / 2 "
Output: 5
```

---

# 💡 Intuition

The key challenge in this problem is handling operator precedence: multiplication and division have higher precedence than addition and subtraction. A naive left-to-right evaluation would fail because it doesn't respect this precedence.

The optimal approach involves processing the string in a single pass while immediately evaluating multiplication and division as they appear, deferring addition and subtraction until the end. This can be efficiently managed using a stack to accumulate intermediate results.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves parsing the string into numbers and operators, then evaluating the expression using two passes:
1. First pass: Evaluate all multiplication and division operations.
2. Second pass: Evaluate all addition and subtraction operations.

This approach requires extra space to store intermediate results and multiple passes over the data, making it less efficient for large inputs.

---

## 🔹 Algorithm

1. Initialize two lists: one for numbers and one for operators.
2. Parse the string to extract numbers and operators, ignoring spaces.
3. First pass: Iterate through the operators. For every `*` or `/`, perform the operation on the corresponding numbers and update the numbers list.
4. Second pass: Iterate through the remaining operators (`+` and `-`) and perform the operations sequentially.
5. Return the final result.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int calculate(String s) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        // Parse the string into numbers and operators
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                numbers.add(num);
            } else {
                operators.add(s.charAt(i));
                i++;
            }
        }

        // First pass: Evaluate * and /
        for (int j = 0; j < operators.size(); j++) {
            char op = operators.get(j);
            if (op == '*' || op == '/') {
                int num1 = numbers.get(j);
                int num2 = numbers.get(j + 1);
                int res = op == '*' ? num1 * num2 : num1 / num2;
                numbers.set(j, res);
                numbers.remove(j + 1);
                operators.remove(j);
                j--; // Adjust index after removal
            }
        }

        // Second pass: Evaluate + and -
        int result = numbers.get(0);
        for (int j = 0; j < operators.size(); j++) {
            char op = operators.get(j);
            int num = numbers.get(j + 1);
            result = op == '+' ? result + num : result - num;
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

**Input:** `"3+2*2"`

### Parsing Phase:
| Step | Character | Action                     | Numbers List | Operators List |
|------|-----------|----------------------------|--------------|----------------|
| 1    | '3'       | Parse number 3             | [3]          | []             |
| 2    | '+'       | Add operator '+'           | [3]          | ['+']          |
| 3    | '2'       | Parse number 2             | [3, 2]       | ['+']          |
| 4    | '*'       | Add operator '*'           | [3, 2]       | ['+', '*']     |
| 5    | '2'       | Parse number 2             | [3, 2, 2]    | ['+', '*']     |

### First Pass (Multiplication/Division):
| Step | Operator | Action                     | Numbers List | Operators List |
|------|----------|----------------------------|--------------|----------------|
| 1    | '*'      | 2 * 2 = 4                  | [3, 4]       | ['+']          |

### Second Pass (Addition/Subtraction):
| Step | Operator | Action                     | Result |
|------|----------|----------------------------|--------|
| 1    | '+'      | 3 + 4 = 7                  | 7      |

**Final Result:** `7`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n)        |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach processes the string in a single pass while respecting operator precedence. It uses a stack to accumulate intermediate results for addition and subtraction, while immediately evaluating multiplication and division as they appear. This approach avoids multiple passes and reduces space complexity by reusing variables.

---

## 🔹 Why This Works

- **Operator Precedence:** Multiplication and division are evaluated immediately, while addition and subtraction are deferred by pushing values onto a stack.
- **Single Pass:** The entire string is processed in one iteration, making the algorithm efficient.
- **Space Optimization:** The stack only stores values that need to be summed later, minimizing space usage.

---

## 🔹 Algorithm

1. Initialize a stack to store numbers for addition/subtraction.
2. Initialize `currentNumber` to accumulate multi-digit numbers and `currentOperator` to track the last seen operator.
3. Iterate through the string:
   - If the character is a digit, update `currentNumber`.
   - If the character is an operator or the end of the string:
     - Apply the `currentOperator` to the previous number and `currentNumber`.
     - For `+` or `-`, push the result (or its negative) onto the stack.
     - For `*` or `/`, immediately evaluate and update the last stack value.
     - Update `currentOperator` to the new operator.
4. Sum all values in the stack to get the final result.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char currentOperator = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (currentOperator == '+') {
                    stack.push(currentNumber);
                } else if (currentOperator == '-') {
                    stack.push(-currentNumber);
                } else if (currentOperator == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (currentOperator == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                currentOperator = c;
                currentNumber = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `"3+2*2"`

| Step | Character | Action                     | currentNumber | currentOperator | Stack       |
|------|-----------|----------------------------|---------------|-----------------|-------------|
| 1    | '3'       | Parse number 3             | 3             | '+'             | []          |
| 2    | '+'       | Push 3 to stack            | 0             | '+'             | [3]         |
| 3    | '2'       | Parse number 2             | 2             | '+'             | [3]         |
| 4    | '*'       | Push 2 to stack (deferred) | 0             | '*'             | [3, 2]      |
| 5    | '2'       | Parse number 2             | 2             | '*'             | [3, 2]      |
| 6    | (end)     | 2 * 2 = 4, update stack    | 0             | '*'             | [3, 4]      |

**Sum Stack:** `3 + 4 = 7`

**Final Result:** `7`

---

## 🔹 Complexity Analysis

| Complexity      | Value       |
|-----------------|-------------|
| Time Complexity | O(n)        |
| Space Complexity| O(n)        |

---

# 🔍 Edge Cases

- **Single Number:** `"42"` → `42`
- **Division Truncation:** `"14/3"` → `4`
- **Spaces:** `" 3+ 5 / 2 "` → `5`
- **Large Input:** `"1+2+3+...+1000"` (ensure no stack overflow)
- **Consecutive Operators:** `"1*2*3"` → `6`
- **Mixed Operators:** `"1+2*3-4/2"` → `5`

---

# 📚 Key Takeaways

1. **Operator Precedence:** Always handle `*` and `/` before `+` and `-`.
2. **Stack Utilization:** Use a stack to defer addition/subtraction operations.
3. **Single Pass Efficiency:** Process the string in one iteration for optimal performance.
4. **Edge Cases:** Handle spaces, multi-digit numbers, and division truncation carefully.

---

# 🚀 Interview Tips

- **Follow-Up:** How would you extend this to handle parentheses (e.g., `"3*(2+2)"`)?
- **Common Pitfall:** Forgetting to reset `currentNumber` after processing an operator.
- **Alternative Approach:** Using a recursive descent parser for more complex expressions.
- **Optimization:** The optimal solution avoids multiple passes and minimizes space usage.

---

# ✅ Conclusion

The optimal approach efficiently evaluates the expression in a single pass while respecting operator precedence. By using a stack to defer addition and subtraction, it ensures correctness and minimizes computational overhead. This solution is both time and space efficient, making it ideal for large inputs and interview settings.