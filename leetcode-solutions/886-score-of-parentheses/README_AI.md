# 📌 886. Score of Parentheses

---

# 📝 Problem Statement

Given a balanced parentheses string `s`, compute the score of the string based on the following rule:

- `()` has score 1.
- `AB` has score `A + B`, where A and B are balanced parentheses strings.
- `(A)` has score `2 * A`, where A is a balanced parentheses string.

**Constraints:**
- `2 <= s.length <= 50`
- `s` consists of only `'('` and `')'`.
- `s` is guaranteed to be balanced.

---

# 💡 Intuition

The problem requires calculating the score of a balanced parentheses string. The key insight is recognizing that the score can be computed using a stack-based approach where we track the current depth of nested parentheses. When we encounter a closing parenthesis, we calculate the score based on whether it's a single pair or a nested structure.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using recursion to calculate the score. For each segment of the string, we recursively compute the score of the inner segments and combine them according to the rules.

---

## 🔹 Algorithm

1. Initialize a pointer `i` to traverse the string.
2. For each opening parenthesis `(`, recursively compute the score of the inner segment.
3. For each closing parenthesis `)`, if the previous character was an opening parenthesis `(`, add 1 to the score. Otherwise, multiply the score of the inner segment by 2 and add it to the current score.
4. Return the total score.

---

## 🔹 Code

```java
class Solution {
    public int scoreOfParentheses(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int start, int end) {
        if (start == end) {
            return 0;
        }

        int score = 0;
        int i = start;
        while (i < end) {
            if (s.charAt(i) == '(') {
                int j = findMatchingParenthesis(s, i);
                if (i + 1 == j) {
                    score += 1;
                } else {
                    score += 2 * helper(s, i + 1, j - 1);
                }
                i = j + 1;
            } else {
                i++;
            }
        }
        return score;
    }

    private int findMatchingParenthesis(String s, int start) {
        int balance = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input string `"(()(()))"`.

| Step | i | Current Character | Action | Score | Balance |
|---|---|---|---|---|---|
| 1 | 0 | '(' | Start of segment | 0 | 1 |
| 2 | 1 | '(' | Start of inner segment | 0 | 2 |
| 3 | 2 | ')' | End of inner segment, i + 1 == j | 1 | 1 |
| 4 | 3 | '(' | Start of inner segment | 1 | 2 |
| 5 | 4 | '(' | Start of inner segment | 1 | 3 |
| 6 | 5 | ')' | End of inner segment, i + 1 == j | 2 | 2 |
| 7 | 6 | ')' | End of segment, multiply inner score by 2 | 6 | 1 |
| 8 | 7 | ')' | End of segment, multiply inner score by 2 | 6 | 0 |

Final score: 6

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a stack to keep track of the scores at different levels of nesting. For each opening parenthesis `(`, we push a new score onto the stack. For each closing parenthesis `)`, we pop the top score and add it to the next top score, doubling it if the previous character was an opening parenthesis `(`.

---

## 🔹 Why This Works

This approach efficiently computes the score by leveraging the stack to manage the nested structures. The stack ensures that we correctly handle the nested parentheses and compute the scores according to the given rules.

---

## 🔹 Algorithm

1. Initialize a stack with a base score of 0.
2. Traverse the string:
   - For each opening parenthesis `(`, push a new score of 0 onto the stack.
   - For each closing parenthesis `)`, pop the top score from the stack. If the previous character was an opening parenthesis `(`, add 1 to the new top score. Otherwise, add twice the popped score to the new top score.
3. Return the final score from the stack.

---

## 🔹 Code

```java
class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else {
                int val = stack.pop();
                int score = Math.max(2 * val, 1);
                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input string `"(()(()))"`.

| Step | Current Character | Stack | Action |
|---|---|---|---|
| 1 | '(' | [0] | Push 0 |
| 2 | '(' | [0, 0] | Push 0 |
| 3 | ')' | [0, 0] | Pop 0, val = 0, score = 1, push 0 + 1 = 1 |
| 4 | '(' | [0, 1] | Push 0 |
| 5 | '(' | [0, 1, 0] | Push 0 |
| 6 | ')' | [0, 1, 0] | Pop 0, val = 0, score = 1, push 1 + 1 = 2 |
| 7 | ')' | [0, 2] | Pop 2, val = 2, score = 4, push 0 + 4 = 4 |
| 8 | ')' | [4] | Pop 4, val = 4, score = 8, push 0 + 8 = 8 |

Final score: 8

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Single Pair**: `"()"` should return 1.
- **Nested Pairs**: `"(())"` should return 2.
- **Multiple Nested Pairs**: `"(()())"` should return 3.
- **Deeply Nested Pairs**: `"((()))"` should return 4.
- **Complex Nested Pairs**: `"(()(()))"` should return 6.

---

# 📚 Key Takeaways

- **Stack Usage**: The stack is crucial for managing nested structures and computing scores efficiently.
- **Recursive vs Iterative**: The recursive approach is simpler but less efficient, while the stack-based approach is more efficient and scalable.
- **Pattern Recognition**: Recognizing the pattern of nested structures and applying the appropriate rules is key to solving this problem.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle unbalanced parentheses or other variations of the problem.
- **Common Pitfalls**: Ensure that the stack is initialized correctly and that the scores are computed accurately.
- **Alternative Approaches**: Consider using a counter to track the depth of nesting and compute the score based on the depth.

---

# ✅ Conclusion

The optimal approach using a stack is efficient and scalable, making it suitable for larger input sizes. The key insight is leveraging the stack to manage nested structures and compute scores according to the given rules. This approach ensures that the solution is both correct and efficient.