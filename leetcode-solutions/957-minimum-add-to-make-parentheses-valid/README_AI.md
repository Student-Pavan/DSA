# Minimum Add to Make Parentheses Valid

---

# 📝 Problem Statement

Given a string `s` of `'('` , `')'` and lowercase English characters.

Your task is to find the minimum number of parentheses we must add to make the resulting string valid.

A valid string is defined as one where every opening parenthesis `'('` has a corresponding closing parenthesis `')'`.

**Constraints:**
- `1 <= s.length <= 1000`
- `s[i]` is either `'('` , `')'` or lowercase English letter.

---

# 💡 Intuition

The key insight is that we need to track the balance of parentheses as we iterate through the string. When we encounter an opening parenthesis `'('`, we increase the balance, and when we encounter a closing parenthesis `')'`, we decrease the balance. The minimum number of additions required is the maximum between the number of unmatched opening parentheses and the number of unmatched closing parentheses at the end of the iteration.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the string and counting the number of unmatched opening and closing parentheses. For each closing parenthesis, if there are no unmatched opening parentheses, we increment the count of additions required. At the end, the total additions required is the sum of unmatched opening and closing parentheses.

---

## 🔹 Algorithm

1. Initialize `open` and `close` counters to 0.
2. Iterate through each character in the string:
   - If the character is `'('`, increment `open`.
   - If the character is `')'`:
     - If `open` is greater than 0, decrement `open`.
     - Otherwise, increment `close`.
3. Return the sum of `open` and `close`.

---

## 🔹 Code

```java
class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }

        return open + close;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the string `"())"`.

| Iteration | Character | Open | Close | Action |
|-----------|-----------|------|-------|--------|
| 1         | '('       | 1    | 0     | Increment open |
| 2         | ')'       | 0    | 0     | Decrement open |
| 3         | ')'       | 0    | 1     | Increment close |

Final result: `open + close = 0 + 1 = 1`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a stack to keep track of the opening parentheses. For each closing parenthesis, if there is a matching opening parenthesis on the stack, we pop it. Otherwise, we increment the count of additions required. At the end, the size of the stack gives the number of unmatched opening parentheses, and the count of additions gives the number of unmatched closing parentheses.

---

## 🔹 Why This Works

This approach efficiently tracks the balance of parentheses using a stack, ensuring that each closing parenthesis matches the most recent unmatched opening parenthesis. The stack size at the end gives the number of unmatched opening parentheses, and the count of additions gives the number of unmatched closing parentheses.

---

## 🔹 Algorithm

1. Initialize an empty stack and `additions` counter to 0.
2. Iterate through each character in the string:
   - If the character is `'('`, push it onto the stack.
   - If the character is `')'`:
     - If the stack is not empty and the top of the stack is `'('`, pop from the stack.
     - Otherwise, increment `additions`.
3. Return the sum of the stack size and `additions`.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        int additions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    additions++;
                }
            }
        }

        return stack.size() + additions;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the string `"())"`.

| Iteration | Character | Stack | Additions | Action |
|-----------|-----------|-------|-----------|--------|
| 1         | '('       | ['('] | 0         | Push '(' |
| 2         | ')'       | []    | 0         | Pop '(' |
| 3         | ')'       | []    | 1         | Increment additions |

Final result: `stack.size() + additions = 0 + 1 = 1`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty string: `""` → 0
- All opening parentheses: `"((("` → 3
- All closing parentheses: `")))"` → 3
- Balanced parentheses: `"()"` → 0
- Mixed characters: `"a(b)c)"` → 1

---

# 📚 Key Takeaways

- The problem can be solved using a stack to track the balance of parentheses.
- The optimal approach efficiently tracks the balance using a stack, ensuring each closing parenthesis matches the most recent unmatched opening parenthesis.
- The brute force approach counts the number of unmatched opening and closing parentheses, providing a straightforward solution.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Consider follow-up questions about handling other types of parentheses (e.g., `[]`, `{}`).
- Emphasize the importance of tracking the balance of parentheses efficiently.

---

# ✅ Conclusion

The optimal approach using a stack provides an efficient solution to the problem, ensuring that each closing parenthesis matches the most recent unmatched opening parenthesis. The key insight is to track the balance of parentheses as we iterate through the string, ensuring the resulting string is valid with the minimum number of additions.

---

# 🎨 Formatting Rules

- Use proper markdown headings and separators.
- Use syntax-highlighted code blocks for Java code.
- Use markdown tables for dry runs and complexity analysis.
- Ensure GitHub readability with clean spacing and formatting.