# Minimum String Length After Removing Substrings

---

# 📝 Problem Statement

You are given a string `s` consisting only of uppercase English letters.

You can apply some operations to this string where, in one operation, you can remove any occurrence of the substring "AB" or "CD" from `s`.

Return the minimum possible length of the resulting string that you can obtain.

Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.

**Constraints:**

- 1 <= s.length <= 100
- s consists only of uppercase English letters.

---

# 💡 Intuition

The key insight here is recognizing that we need to repeatedly remove all occurrences of "AB" and "CD" substrings until no more can be removed. The optimal approach involves using a stack to efficiently track and remove these substrings in a single pass through the string.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves repeatedly scanning the string to find and remove all occurrences of "AB" and "CD" substrings until no more can be found. This requires multiple passes through the string, making it inefficient.

---

## 🔹 Algorithm

1. Initialize a flag to track if any substrings were removed in the current pass.
2. While the flag indicates that substrings were removed:
   - Reset the flag to false.
   - Iterate through the string to find "AB" or "CD" substrings.
   - For each found substring, remove it and set the flag to true.
3. Return the length of the modified string.

---

## 🔹 Code

```java
class Solution {
    public int minLength(String s) {
        boolean removed;
        do {
            removed = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i + 1 < s.length() && s.charAt(i) == 'A' && s.charAt(i + 1) == 'B') {
                    i++;
                    removed = true;
                } else if (i + 1 < s.length() && s.charAt(i) == 'C' && s.charAt(i + 1) == 'D') {
                    i++;
                    removed = true;
                } else {
                    sb.append(s.charAt(i));
                }
            }
            s = sb.toString();
        } while (removed);
        return s.length();
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the string "ABFCACDB".

| Iteration | Current String | Action | Result |
|---|---|---|---|
| 1 | "ABFCACDB" | Remove "AB" | "FCACDB" |
| 2 | "FCACDB" | Remove "CD" | "FACB" |
| 3 | "FACB" | Remove "AB" | "FCB" |
| 4 | "FCB" | No more substrings to remove | "FCB" |

The final length is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using a stack to efficiently track and remove "AB" and "CD" substrings in a single pass through the string. This approach ensures that we only need to traverse the string once, making it much more efficient.

---

## 🔹 Why This Works

Using a stack allows us to keep track of the characters as we process them. Whenever we encounter a character that can form a valid substring with the top of the stack, we remove the top of the stack. This ensures that we efficiently remove all possible substrings in a single pass.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the string:
   - If the current character is 'B' and the top of the stack is 'A', remove the top of the stack.
   - Else if the current character is 'D' and the top of the stack is 'C', remove the top of the stack.
   - Otherwise, push the current character onto the stack.
3. The length of the stack is the minimum possible length of the string after removing all possible substrings.

---

## 🔹 Code

```java
class Solution {
    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && ((stack.peek() == 'A' && c == 'B') || (stack.peek() == 'C' && c == 'D'))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the string "ABFCACDB".

| Iteration | Current Character | Stack | Action |
|---|---|---|---|
| 1 | 'A' | ['A'] | Push |
| 2 | 'B' | [] | Pop 'A' |
| 3 | 'F' | ['F'] | Push |
| 4 | 'C' | ['F', 'C'] | Push |
| 5 | 'A' | ['F', 'C', 'A'] | Push |
| 6 | 'C' | ['F', 'C', 'A', 'C'] | Push |
| 7 | 'D' | ['F', 'C', 'A'] | Pop 'C' |
| 8 | 'B' | ['F', 'C', 'A'] | Push |

The final stack is ['F', 'C', 'A'], so the minimum length is 3.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty string: Input "". Output 0.
- No substrings to remove: Input "ABC". Output 3.
- Multiple substrings to remove: Input "ABFCACDB". Output 3.
- All characters are part of substrings: Input "ABABAB". Output 0.

---

# 📚 Key Takeaways

- Using a stack to track characters allows for efficient removal of substrings in a single pass.
- The optimal approach reduces the time complexity from O(n^2) to O(n).
- Understanding the pattern of substrings to remove is crucial for solving such problems efficiently.

---

# 🚀 Interview Tips

- Discuss the brute force approach and its inefficiency to understand the need for optimization.
- Explain why the stack approach is more efficient and how it ensures all possible substrings are removed.
- Consider follow-up questions about handling different types of substrings or larger input sizes.

---

# ✅ Conclusion

The optimal approach using a stack is significantly more efficient than the brute force method. It ensures that all possible "AB" and "CD" substrings are removed in a single pass through the string, making it the preferred solution for this problem. The key takeaway is the importance of recognizing patterns and applying the right data structure to solve the problem efficiently.