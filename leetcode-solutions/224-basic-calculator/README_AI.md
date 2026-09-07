# 📌 224. Basic Calculator

---

# 📝 Problem Statement

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open `(` and closing parentheses `)`, the plus `+` or minus sign `-`, **non-negative** integers and empty spaces ` `.

**Objective**: Calculate the result of the expression.

**Constraints**:
- `1 <= s.length <= 3 * 10^5`
- `s` consists of digits, `'+'`, `'-'`, `'('`, `')'`, and `' '`.
- `s` represents a valid expression.
- `'+'` is not used as a unary operation (i.e., `"+1"` and `"+(2 + 3)"` is invalid).
- `'-'` could be used as a unary operation (i.e., `"-1"` and `"-(2 + 3)"` is valid).
- There will be no two consecutive operators in the input.
- Every number and running calculation will fit in a signed 32-bit integer.

---

# 💡 Intuition

The key insight is handling parentheses correctly. When we encounter an opening parenthesis `(`, we need to save the current result and sign, then reset them to compute the value inside the parentheses. When we encounter a closing parenthesis `)`, we use the saved values to compute the final result.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using recursion to handle parentheses. For each opening parenthesis `(`, we recursively evaluate the expression inside the parentheses and return the result.

## 🔹 Algorithm

1. Initialize `result` and `sign` to 0 and 1 respectively.
2. Iterate through each character in the string:
   - If the character is a digit, build the number.
   - If the character is `+` or `-`, add the current number to the result with the current sign, then update the sign.
   - If the character is `(`, recursively evaluate the expression inside the parentheses.
   - If the character is `)`, return the result.
3. After the loop, add the last number to the result.
4. Return the result.

## 🔹 Code

```java
class Solution {
    private int index = 0;

    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;

        while (index < s.length()) {
            char ch = s.charAt(index);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '+' || ch == '-') {
                result += sign * num;
                num = 0;
                sign = (ch == '+') ? 1 : -1;
            } else if (ch == '(') {
                index++;
                num = calculate(s);
            } else if (ch == ')') {
                result += sign * num;
                return result;
            }

            index++;
        }

        result += sign * num;
        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the input `"1 + (2 - 3)"`.

| Step | Index | Character | Result | Num | Sign | Action |
|------|-------|-----------|--------|-----|------|--------|
| 1    | 0     | '1'       | 0      | 1   | 1    | Build num |
| 2    | 2     | '+'       | 1      | 0   | 1    | Update result and sign |
| 3    | 4     | '('       | 1      | 0   | 1    | Recursive call |
| 4    | 5     | '2'       | 0      | 2   | 1    | Build num |
| 5    | 7     | '-'       | 2      | 0   | -1   | Update result and sign |
| 6    | 9     | '3'       | 2      | 3   | -1   | Build num |
| 7    | 10    | ')'       | -1     | 0   | -1   | Return result |
| 8    | 11    | End       | 1      | 0   | 1    | Update result |

Final result: `1 + (-1) = 0`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the length of the string |
| Space Complexity | O(n) due to recursion stack |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a stack to handle parentheses. We push the current result and sign onto the stack when we encounter an opening parenthesis `(`, and pop them off when we encounter a closing parenthesis `)`. This allows us to handle nested parentheses efficiently.

## 🔹 Why This Works

This approach efficiently handles nested parentheses by saving the current state (result and sign) on the stack before processing the inner expression. When we encounter a closing parenthesis, we use the saved state to compute the final result, ensuring correct evaluation of nested expressions.

## 🔹 Algorithm

1. Initialize `result` and `sign` to 0 and 1 respectively.
2. Use a stack to store intermediate results and signs.
3. Iterate through each character in the string:
   - If the character is a digit, build the number.
   - If the character is `+` or `-`, add the current number to the result with the current sign, then update the sign.
   - If the character is `(`, push the current result and sign onto the stack, then reset them.
   - If the character is `)`, add the current number to the result, then use the saved values to compute the final result.
4. After the loop, add the last number to the result.
5. Return the result.

## 🔹 Code

```java
class Solution {
    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '+' || ch == '-') {
                result += sign * num;
                num = 0;
                sign = (ch == '+') ? 1 : -1;
            } else if (ch == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                result += sign * num;
                num = 0;
                int prevSign = stack.pop();
                int prevResult = stack.pop();
                result = prevResult + prevSign * result;
            }
        }

        result += sign * num;

        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the input `"1 + (2 - 3)"`.

| Step | Character | Result | Num | Sign | Stack | Action |
|------|-----------|--------|-----|------|-------|--------|
| 1    | '1'       | 0      | 1   | 1    | []    | Build num |
| 2    | ' '       | 0      | 1   | 1    | []    | Skip space |
| 3    | '+'       | 1      | 0   | 1    | []    | Update result and sign |
| 4    | ' '       | 1      | 0   | 1    | []    | Skip space |
| 5    | '('       | 1      | 0   | 1    | [1, 1] | Push result and sign, reset |
| 6    | '2'       | 0      | 2   | 1    | [1, 1] | Build num |
| 7    | ' '       | 0      | 2   | 1    | [1, 1] | Skip space |
| 8    | '-'       | 2      | 0   | -1   | [1, 1] | Update result and sign |
| 9    | ' '       | 2      | 0   | -1   | [1, 1] | Skip space |
| 10   | '3'       | 2      | 3   | -1   | [1, 1] | Build num |
| 11   | ')'       | -1     | 0   | -1   | []    | Compute result |
| 12   | End       | 1      | 0   | 1    | []    | Update result |

Final result: `1 + (-1) = 0`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the length of the string |
| Space Complexity | O(n) due to stack usage |

---

# 🔍 Edge Cases

- Empty string (though constraints say `1 <= s.length <= 3 * 10^5`)
- Single number
- Single operator
- Nested parentheses
- Multiple consecutive spaces
- Large numbers
- Unary minus

---

# 📚 Key Takeaways

- Recursion can be used to handle parentheses, but it may lead to stack overflow for very deep recursion.
- Using a stack to handle parentheses is more efficient and avoids recursion stack issues.
- Always handle spaces in the input string.
- Unary minus can be handled by checking the previous character.

---

# 🚀 Interview Tips

- Discuss the trade-offs between recursion and stack-based approaches.
- Ask if the input can contain unary plus or other edge cases.
- Consider using a stack to handle more complex expressions.

---

# ✅ Conclusion

The optimal approach using a stack is more efficient and avoids recursion stack issues, making it suitable for large input sizes. The key insight is saving the current state (result and sign) on the stack before processing nested expressions.

---

# 🎨 Formatting Rules

- Use proper markdown headings and separators.
- Use syntax-highlighted code blocks.
- Use markdown tables for dry runs and complexity analysis.
- Ensure GitHub readability.