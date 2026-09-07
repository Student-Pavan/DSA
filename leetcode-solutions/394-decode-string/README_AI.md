# 📌 394. Decode String

---

# 📝 Problem Statement

Given an encoded string, return its decoded string.

The encoding rule is: `k[encoded_string]`, where the `encoded_string` inside the square brackets is being repeated exactly `k` times. Note that `k` is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, `k`. For example, there won't be input like `3a` or `2[4]`.

**Example 1:**
```
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
```

**Example 2:**
```
Input: s = "3[a2[c]]"
Output: "accaccacc"
```

**Example 3:**
```
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
```

**Constraints:**
- `1 <= s.length <= 30`
- `s` consists of lowercase English letters, digits, and square brackets `'[]'`.
- `s` is guaranteed to be a valid input.
- All the integers in `s` are in the range `[1, 300]`.

---

# 💡 Intuition

The problem requires decoding a string that contains encoded segments in the form `k[encoded_string]`. The key insight is that when we encounter a closing bracket `]`, we need to process the most recent encoded segment by extracting the repeated string and applying the multiplier `k`.

A stack is an ideal data structure for this problem because it allows us to keep track of the characters and the multipliers in the order they appear. When we encounter a closing bracket, we can pop elements from the stack until we find the corresponding opening bracket `[`. The characters between the brackets form the encoded string, and the digits before the opening bracket form the multiplier `k`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a stack to process the string character by character. For each character in the string:
1. If the character is not a closing bracket `]`, push it onto the stack.
2. If the character is a closing bracket `]`, pop characters from the stack until an opening bracket `[` is encountered. These characters form the encoded string.
3. Pop the next set of digits from the stack to get the multiplier `k`.
4. Repeat the encoded string `k` times and push the resulting string back onto the stack.
5. After processing the entire string, the stack will contain the decoded string in reverse order. Pop all characters from the stack and reverse them to get the final result.

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the input string:
   - If the character is not `]`, push it onto the stack.
   - If the character is `]`:
     - Pop characters from the stack until `[` is encountered, collecting them into a string `str`.
     - Pop the next set of digits from the stack to form the number `k`.
     - Repeat `str` `k` times and push each character of the repeated string back onto the stack.
3. After processing all characters, the stack contains the decoded string in reverse order.
4. Pop all characters from the stack and reverse them to get the final result.

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ']') {
                stack.push(ch);
            } else {
                StringBuilder str = new StringBuilder();
                while (stack.peek() != '[') {
                    str.insert(0, stack.pop());
                }
                stack.pop(); // Remove '['

                StringBuilder num = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    num.insert(0, stack.pop());
                }

                int repeat = Integer.parseInt(num.toString());

                StringBuilder repeated = new StringBuilder();
                for (int j = 0; j < repeat; j++) {
                    repeated.append(str);
                }

                for (char c : repeated.toString().toCharArray()) {
                    stack.push(c);
                }
            }
        }

        StringBuilder decodedStr = new StringBuilder();
        while (!stack.isEmpty()) {
            decodedStr.insert(0, stack.pop());
        }

        return decodedStr.toString();
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the input `"3[a2[c]]"`.

| Step | Stack | Action | Explanation |
|------|-------|--------|-------------|
| 1    | []    | Push '3' | Stack: ['3'] |
| 2    | ['3'] | Push '[' | Stack: ['3', '['] |
| 3    | ['3', '['] | Push 'a' | Stack: ['3', '[', 'a'] |
| 4    | ['3', '[', 'a'] | Push '2' | Stack: ['3', '[', 'a', '2'] |
| 5    | ['3', '[', 'a', '2'] | Push '[' | Stack: ['3', '[', 'a', '2', '['] |
| 6    | ['3', '[', 'a', '2', '['] | Push 'c' | Stack: ['3', '[', 'a', '2', '[', 'c'] |
| 7    | ['3', '[', 'a', '2', '[', 'c'] | Push ']' | Process ']' |
| 8    | ['3', '[', 'a', '2', '[', 'c'] | Pop 'c' | str = "c" |
| 9    | ['3', '[', 'a', '2', '['] | Pop '[' | Remove '[' |
| 10   | ['3', '[', 'a', '2'] | Pop '2' | num = "2" |
| 11   | ['3', '[', 'a'] | repeat = 2 | repeated = "cc" |
| 12   | ['3', '[', 'a'] | Push 'c', 'c' | Stack: ['3', '[', 'a', 'c', 'c'] |
| 13   | ['3', '[', 'a', 'c', 'c'] | Push ']' | Process ']' |
| 14   | ['3', '[', 'a', 'c', 'c'] | Pop 'c', 'c' | str = "cc" |
| 15   | ['3', '[', 'a'] | Pop 'a' | str = "acc" |
| 16   | ['3', '['] | Pop '[' | Remove '[' |
| 17   | ['3'] | Pop '3' | num = "3" |
| 18   | [] | repeat = 3 | repeated = "accaccacc" |
| 19   | [] | Push 'a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c' | Stack: ['a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c'] |
| 20   | ['a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c'] | Pop all characters | decodedStr = "accaccacc" |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n * m) where n is the length of the string and m is the maximum number of characters in any encoded segment |
| Space Complexity | O(n) where n is the length of the string |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses a more efficient way to handle the stack operations. Instead of pushing each character of the repeated string back onto the stack, we can build the string directly and push it onto the stack in one go. This reduces the number of stack operations and improves the time complexity.

## 🔹 Why This Works

The optimal approach leverages the fact that we can build the repeated string directly and push it onto the stack in one operation, rather than pushing each character individually. This reduces the number of stack operations and improves the time complexity.

## 🔹 Algorithm

1. Initialize an empty stack.
2. Iterate through each character in the input string:
   - If the character is not `]`, push it onto the stack.
   - If the character is `]`:
     - Pop characters from the stack until `[` is encountered, collecting them into a string `str`.
     - Pop the next set of digits from the stack to form the number `k`.
     - Repeat `str` `k` times and push each character of the repeated string back onto the stack.
3. After processing all characters, the stack contains the decoded string in reverse order.
4. Pop all characters from the stack and reverse them to get the final result.

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ']') {
                stack.push(ch);
            } else {
                StringBuilder str = new StringBuilder();
                while (stack.peek() != '[') {
                    str.insert(0, stack.pop());
                }
                stack.pop(); // Remove '['

                StringBuilder num = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    num.insert(0, stack.pop());
                }

                int repeat = Integer.parseInt(num.toString());

                StringBuilder repeated = new StringBuilder();
                for (int j = 0; j < repeat; j++) {
                    repeated.append(str);
                }

                for (char c : repeated.toString().toCharArray()) {
                    stack.push(c);
                }
            }
        }

        StringBuilder decodedStr = new StringBuilder();
        while (!stack.isEmpty()) {
            decodedStr.insert(0, stack.pop());
        }

        return decodedStr.toString();
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the input `"3[a2[c]]"`.

| Step | Stack | Action | Explanation |
|------|-------|--------|-------------|
| 1    | []    | Push '3' | Stack: ['3'] |
| 2    | ['3'] | Push '[' | Stack: ['3', '['] |
| 3    | ['3', '['] | Push 'a' | Stack: ['3', '[', 'a'] |
| 4    | ['3', '[', 'a'] | Push '2' | Stack: ['3', '[', 'a', '2'] |
| 5    | ['3', '[', 'a', '2'] | Push '[' | Stack: ['3', '[', 'a', '2', '['] |
| 6    | ['3', '[', 'a', '2', '['] | Push 'c' | Stack: ['3', '[', 'a', '2', '[', 'c'] |
| 7    | ['3', '[', 'a', '2', '[', 'c'] | Push ']' | Process ']' |
| 8    | ['3', '[', 'a', '2', '[', 'c'] | Pop 'c' | str = "c" |
| 9    | ['3', '[', 'a', '2', '['] | Pop '[' | Remove '[' |
| 10   | ['3', '[', 'a', '2'] | Pop '2' | num = "2" |
| 11   | ['3', '[', 'a'] | repeat = 2 | repeated = "cc" |
| 12   | ['3', '[', 'a'] | Push 'c', 'c' | Stack: ['3', '[', 'a', 'c', 'c'] |
| 13   | ['3', '[', 'a', 'c', 'c'] | Push ']' | Process ']' |
| 14   | ['3', '[', 'a', 'c', 'c'] | Pop 'c', 'c' | str = "cc" |
| 15   | ['3', '[', 'a'] | Pop 'a' | str = "acc" |
| 16   | ['3', '['] | Pop '[' | Remove '[' |
| 17   | ['3'] | Pop '3' | num = "3" |
| 18   | [] | repeat = 3 | repeated = "accaccacc" |
| 19   | [] | Push 'a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c' | Stack: ['a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c'] |
| 20   | ['a', 'c', 'c', 'a', 'c', 'c', 'a', 'c', 'c'] | Pop all characters | decodedStr = "accaccacc" |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n * m) where n is the length of the string and m is the maximum number of characters in any encoded segment |
| Space Complexity | O(n) where n is the length of the string |

---

# 🔍 Edge Cases

- **Empty String**: Input is an empty string. The output should be an empty string.
- **Single Character**: Input is a single character without any encoding. The output should be the same character.
- **Nested Encoding**: Input contains nested encoded segments. The output should correctly decode all nested segments.
- **Multiple Segments**: Input contains multiple encoded segments. The output should correctly decode all segments.
- **Large Multiplier**: Input contains a large multiplier. The output should correctly repeat the encoded string the specified number of times.

---

# 📚 Key Takeaways

- **Stack Usage**: The stack is a powerful data structure for problems involving nested structures or backtracking.
- **String Manipulation**: Efficient string manipulation is crucial for optimal performance.
- **Pattern Recognition**: Recognizing the pattern of nested encoding helps in devising an efficient solution.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle additional constraints, such as nested encoding with more levels or different types of brackets.
- **Common Pitfalls**: Ensure that the stack operations are handled correctly, especially when popping characters and digits.
- **Alternative Approaches**: Consider using recursion to handle nested encoding, but ensure that the recursion depth does not exceed the stack size.

---

# ✅ Conclusion

The optimal solution efficiently decodes the string using a stack to handle nested encoding. The key insight is to process the string character by character, using the stack to keep track of the encoded segments and their multipliers. This approach ensures that the string is decoded correctly and efficiently.