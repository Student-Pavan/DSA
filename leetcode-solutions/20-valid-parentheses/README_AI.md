# 20. Valid Parentheses

---

# 📝 Problem Statement

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

**Constraints:**

- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

---

# 💡 Intuition

The problem requires checking if the parentheses in the string are balanced and properly nested. The key insight is that the last opening bracket must match the first closing bracket. This suggests using a stack data structure, which naturally follows the Last-In-First-Out (LIFO) principle.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible pairs of parentheses to see if they match and are properly nested. This approach is inefficient and has a high time complexity.

---

## 🔹 Algorithm

1. Iterate through each character in the string.
2. For each opening bracket, find the corresponding closing bracket.
3. Check if the closing bracket matches the opening bracket.
4. Ensure that all brackets are properly nested.

---

## 🔹 Code

```java
class Solution {
    public boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int j = s.indexOf(')', i);
                if (j == -1 || !isValid(s.substring(i + 1, j))) {
                    return false;
                }
                i = j;
            } else if (s.charAt(i) == '{') {
                int j = s.indexOf('}', i);
                if (j == -1 || !isValid(s.substring(i + 1, j))) {
                    return false;
                }
                i = j;
            } else if (s.charAt(i) == '[') {
                int j = s.indexOf(']', i);
                if (j == -1 || !isValid(s.substring(i + 1, j))) {
                    return false;
                }
                i = j;
            } else {
                return false;
            }
        }
        return true;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input string `"({})"`.

| Step | Character | Action | State |
|---|---|---|---|
| 1 | `(` | Start checking for `)` | `i = 0` |
| 2 | `(` | Start checking for `)` | `i = 1` |
| 3 | `{` | Start checking for `}` | `i = 2` |
| 4 | `}` | Found matching `}` | `i = 3` |
| 5 | `)` | Found matching `)` | `i = 4` |
| 6 | End of string | All brackets matched | Return `true` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using a stack to keep track of the opening brackets. When a closing bracket is encountered, the top of the stack is checked to see if it matches the closing bracket. If it matches, the opening bracket is popped from the stack. If it does not match, the string is invalid.

---

## 🔹 Why This Works

This approach works because the stack ensures that the last opening bracket encountered is the first one to be closed, which is the correct order for balanced parentheses. The stack operations are efficient and ensure that the solution is optimal.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the string.
3. If the character is an opening bracket, push it onto the stack.
4. If the character is a closing bracket, check if the stack is empty or if the top of the stack does not match the closing bracket. If either condition is true, return false.
5. Pop the top of the stack.
6. After iterating through the string, check if the stack is empty. If it is, return true; otherwise, return false.

---

## 🔹 Code

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[')
                st.push(']');
            else if (c == '{')
                st.push('}');
            else if (c == '(')
                st.push(')');
            else if (st.isEmpty() || st.pop() != c)
                return false;
        }
        return st.isEmpty();
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input string `"({})"`.

| Step | Character | Stack | Action |
|---|---|---|---|
| 1 | `(` | `(` | Push `(` onto the stack |
| 2 | `{` | `(`, `{` | Push `{` onto the stack |
| 3 | `}` | `(`, `{` | Pop `{` from the stack |
| 4 | `)` | `(` | Pop `(` from the stack |
| 5 | End of string | Empty | Check if stack is empty |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty string: `""` → `true`
- Single opening bracket: `"("` → `false`
- Single closing bracket: `")"` → `false`
- Mismatched brackets: `"({)}"` → `false`
- Nested brackets: `"({[]})"` → `true`
- Large input: `"((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((