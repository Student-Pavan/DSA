# Minimum Remove to Make Valid Parentheses

---

# 📝 Problem Statement

Given a string `s` consisting of lowercase English letters and parentheses, remove the minimum number of parentheses to make the string valid.

A string is considered valid if:
- Every opening parenthesis `'('` has a corresponding closing parenthesis `')'`.
- Every closing parenthesis `')'` has a corresponding opening parenthesis `'('`.
- The parentheses are correctly nested.

**Constraints:**
- `1 <= s.length <= 10^5`
- `s[i]` is either lowercase English letter or `'('` or `')'`.

---

# 💡 Intuition

The key insight is that we need to track the indices of unmatched parentheses. We can use a stack to keep track of the indices of opening parentheses `'('`. When we encounter a closing parenthesis `')'`, we check if there's a matching opening parenthesis on the stack. If there is, we pop it from the stack. If not, we push the index of the closing parenthesis onto the stack. After processing the entire string, the stack will contain the indices of all unmatched parentheses. We then construct the result string by excluding these indices.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Initialize an empty stack to keep track of indices of unmatched parentheses.
2. Iterate through each character in the string:
   - If the character is `'('`, push its index onto the stack.
   - If the character is `')'`, check if the stack is not empty and the top of the stack contains an index of `'('`. If so, pop the stack. Otherwise, push the index of `')'` onto the stack.
3. After processing the entire string, the stack contains indices of all unmatched parentheses.
4. Construct the result string by excluding the characters at these indices.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the string:
   - If the character is `'('`, push its index onto the stack.
   - If the character is `')'`, check if the stack is not empty and the top of the stack contains an index of `'('`. If so, pop the stack. Otherwise, push the index of `')'` onto the stack.
3. Initialize an empty string builder.
4. Iterate through each character in the string:
   - If the current index is not in the stack, append the character to the string builder.
5. Return the string from the string builder.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the string `s = "lee(t(c)o)de)"`.

| Iteration | Character | Stack | Action |
|-----------|-----------|-------|--------|
| 0         | 'l'       | []    | Do nothing |
| 1         | 'e'       | []    | Do nothing |
| 2         | 'e'       | []    | Do nothing |
| 3         | '('       | [3]   | Push index 3 |
| 4         | 't'       | [3]   | Do nothing |
| 5         | '('       | [3,5] | Push index 5 |
| 6         | 'c'       | [3,5] | Do nothing |
| 7         | ')'       | [3]   | Pop index 5 |
| 8         | 'o'       | [3]   | Do nothing |
| 9         | ')'       | []    | Pop index 3 |
| 10        | 'd'       | []    | Do nothing |
| 11        | 'e'       | []    | Do nothing |
| 12        | ')'       | [12]  | Push index 12 |

After processing the string, the stack contains indices `[12]`.

Now, construct the result string by excluding the characters at these indices.

| Iteration | Character | Index | Action |
|-----------|-----------|-------|--------|
| 0         | 'l'       | 0     | Append |
| 1         | 'e'       | 1     | Append |
| 2         | 'e'       | 2     | Append |
| 3         | '('       | 3     | Skip |
| 4         | 't'       | 4     | Append |
| 5         | '('       | 5     | Skip |
| 6         | 'c'       | 6     | Append |
| 7         | ')'       | 7     | Append |
| 8         | 'o'       | 8     | Append |
| 9         | ')'       | 9     | Append |
| 10        | 'd'       | 10    | Append |
| 11        | 'e'       | 11    | Append |
| 12        | ')'       | 12    | Skip |

The result string is `"lee(t(c)o)de"`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Initialize an empty stack to keep track of indices of unmatched parentheses.
2. Iterate through each character in the string:
   - If the character is `'('`, push its index onto the stack.
   - If the character is `')'`, check if the stack is not empty and the top of the stack contains an index of `'('`. If so, pop the stack. Otherwise, push the index of `')'` onto the stack.
3. Construct the result string by excluding the characters at the indices in the stack.

---

## 🔹 Why This Works

The algorithm works by tracking the indices of unmatched parentheses using a stack. By iterating through the string and using the stack to keep track of the indices of opening parentheses, we can efficiently identify the indices of unmatched parentheses. The result string is constructed by excluding the characters at these indices, ensuring the string is valid.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the string:
   - If the character is `'('`, push its index onto the stack.
   - If the character is `')'`, check if the stack is not empty and the top of the stack contains an index of `'('`. If so, pop the stack. Otherwise, push the index of `')'` onto the stack.
3. Initialize an empty string builder.
4. Iterate through each character in the string:
   - If the current index is not in the stack, append the character to the string builder.
5. Return the string from the string builder.

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the string `s = "lee(t(c)o)de)"`.

| Iteration | Character | Stack | Action |
|-----------|-----------|-------|--------|
| 0         | 'l'       | []    | Do nothing |
| 1         | 'e'       | []    | Do nothing |
| 2         | 'e'       | []    | Do nothing |
| 3         | '('       | [3]   | Push index 3 |
| 4         | 't'       | [3]   | Do nothing |
| 5         | '('       | [3,5] | Push index 5 |
| 6         | 'c'       | [3,5] | Do nothing |
| 7         | ')'       | [3]   | Pop index 5 |
| 8         | 'o'       | [3]   | Do nothing |
| 9         | ')'       | []    | Pop index 3 |
| 10        | 'd'       | []    | Do nothing |
| 11        | 'e'       | []    | Do nothing |
| 12        | ')'       | [12]  | Push index 12 |

After processing the string, the stack contains indices `[12]`.

Now, construct the result string by excluding the characters at these indices.

| Iteration | Character | Index | Action |
|-----------|-----------|-------|--------|
| 0         | 'l'       | 0     | Append |
| 1         | 'e'       | 1     | Append |
| 2         | 'e'       | 2     | Append |
| 3         | '('       | 3     | Skip |
| 4         | 't'       | 4     | Append |
| 5         | '('       | 5     | Skip |
| 6         | 'c'       | 6     | Append |
| 7         | ')'       | 7     | Append |
| 8         | 'o'       | 8     | Append |
| 9         | ')'       | 9     | Append |
| 10        | 'd'       | 10    | Append |
| 11        | 'e'       | 11    | Append |
| 12        | ')'       | 12    | Skip |

The result string is `"lee(t(c)o)de"`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty string: `""` → `""`
- No parentheses: `"leetcode"` → `"leetcode"`
- All opening parentheses: `"((("` → `""`
- All closing parentheses: `")))"` → `""`
- Balanced parentheses: `"(a(b)c)"` → `"(a(b)c)"`
- Nested parentheses: `"a(b(c)d)"` → `"a(b(c)d)"`
- Unbalanced parentheses: `"a(b(c)d)"` → `"a(b(c)d)"`

---

# 📚 Key Takeaways

- Use a stack to track the indices of unmatched parentheses.
- Iterate through the string and use the stack to identify the indices of unmatched parentheses.
- Construct the result string by excluding the characters at these indices.
- The optimal approach efficiently identifies and removes the minimum number of parentheses to make the string valid.

---

# 🚀 Interview Tips

- Discuss the time and space complexity of the solution.
- Mention the use of a stack to track the indices of unmatched parentheses.
- Explain the approach of iterating through the string and using the stack to identify the indices of unmatched parentheses.
- Discuss the approach of constructing the result string by excluding the characters at these indices.

---

# ✅ Conclusion

The optimal solution efficiently identifies and removes the minimum number of parentheses to make the string valid by using a stack to track the indices of unmatched parentheses. The solution ensures the string is valid and minimizes the number of removals. The key insight is the use of a stack to track the indices of unmatched parentheses, allowing efficient identification and removal of unmatched parentheses.