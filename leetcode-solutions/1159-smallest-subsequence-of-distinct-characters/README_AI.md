# Smallest Subsequence of Distinct Characters

---

# 📝 Problem Statement

Given a string `s`, return the lexicographically smallest subsequence of `s` that contains all the distinct characters of `s` exactly once.

**Constraints:**
- `1 <= s.length <= 1000`
- `s` consists of lowercase English letters.

---

# 💡 Intuition

The key insight is that we need to find the smallest lexicographical order while ensuring all distinct characters are included. This requires:
1. Tracking the last occurrence of each character
2. Using a stack to build the result
3. Making greedy choices when possible

The optimal approach uses a stack to build the result, removing characters when a smaller character can appear later in the string.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Generate all possible subsequences
2. Filter to only those containing all distinct characters
3. Find the lexicographically smallest one

This is clearly inefficient but demonstrates the problem's requirements.

---

## 🔹 Algorithm

1. Generate all possible subsequences
2. For each subsequence:
   - Check if it contains all distinct characters
   - Compare with current smallest valid subsequence
3. Return the smallest valid subsequence

---

## 🔹 Code

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public String smallestSubsequence(String s) {
        Set<Character> distinctChars = new HashSet<>();
        for (char c : s.toCharArray()) {
            distinctChars.add(c);
        }

        String smallest = null;
        int n = s.length();
        int totalSubsequences = 1 << n;

        for (int mask = 1; mask < totalSubsequences; mask++) {
            StringBuilder current = new StringBuilder();
            Set<Character> currentChars = new HashSet<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    current.append(s.charAt(i));
                    currentChars.add(s.charAt(i));
                }
            }

            if (currentChars.size() == distinctChars.size()) {
                String candidate = current.toString();
                if (smallest == null || candidate.compareTo(smallest) < 0) {
                    smallest = candidate;
                }
            }
        }

        return smallest;
    }
}
```

---

## 🔹 Dry Run

Let's dry run with `s = "bcabc"`:

1. Distinct characters: {b, c, a}
2. Generate all subsequences:
   - "b" → missing a,c
   - "c" → missing a,b
   - "a" → missing b,c
   - "bc" → missing a
   - "ba" → missing c
   - "ca" → missing b
   - "bca" → valid
   - "abc" → valid
   - "bac" → valid
   - "cab" → valid
   - "cba" → valid
   - "acb" → valid
3. Valid subsequences: "bca", "abc", "bac", "cab", "cba", "acb"
4. Smallest is "abc"

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(2^n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use a stack to build the result
2. Track the last occurrence of each character
3. For each character:
   - If already in stack, skip
   - Otherwise, while stack is not empty and:
     - Top character is greater than current character
     - Top character appears later in the string
   - Remove from stack and mark as unvisited
4. Add current character to stack and mark as visited

---

## 🔹 Why This Works

This approach ensures we always have the smallest possible character at each position while guaranteeing all distinct characters are included. The greedy removal of larger characters when possible maintains the smallest lexicographical order.

---

## 🔹 Algorithm

1. Initialize a stack and a visited array
2. Record the last occurrence of each character
3. For each character in the string:
   - If already visited, skip
   - While stack is not empty and:
     - Top character is greater than current
     - Top character appears later
   - Pop from stack and mark as unvisited
   - Push current character and mark as visited
4. Convert stack to string and return

---

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        boolean[] visited = new boolean[26];
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (visited[c - 'a']) continue;

            while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run with `s = "bcabc"`:

| Iteration | Current Char | Stack | Visited | Action |
|-----------|--------------|-------|---------|--------|
| 0         | 'b'          | []    | [false, false, false, ...] | Push 'b' |
| 1         | 'c'          | ['b'] | [true, false, false, ...] | Push 'c' |
| 2         | 'a'          | ['b', 'c'] | [true, true, false, ...] | 'a' < 'c' and 'c' appears later → Pop 'c', Push 'a' |
| 3         | 'b'          | ['a'] | [true, false, true, ...] | 'b' > 'a' and 'a' doesn't appear later → Push 'b' |
| 4         | 'c'          | ['a', 'b'] | [true, true, true, ...] | 'c' > 'b' and 'b' appears later → Pop 'b', Push 'c' |

Result: "abc"

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Single character string: "a" → "a"
- All characters same: "bbbb" → "b"
- Already in order: "abc" → "abc"
- Reverse order: "cba" → "abc"
- With duplicates: "bcabc" → "abc"

---

# 📚 Key Takeaways

1. The optimal approach uses a greedy algorithm with a stack
2. Tracking last occurrences is crucial for making optimal choices
3. The time complexity is linear due to single pass with constant time operations
4. The space complexity is constant due to fixed-size arrays

---

# 🚀 Interview Tips

1. Clarify if the input can be empty or if all characters are distinct
2. Consider discussing alternative approaches like backtracking
3. Ask about handling case sensitivity if applicable
4. Discuss how to handle very large strings efficiently

---

# ✅ Conclusion

The optimal solution efficiently finds the smallest lexicographical subsequence by making greedy choices while ensuring all distinct characters are included. The key insight is using the stack to build the result while tracking character occurrences, allowing optimal character removal when beneficial.