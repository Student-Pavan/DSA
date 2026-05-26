# 📌 Longest Common Prefix

---

# 📝 Problem Statement

Write a function to find the longest common prefix string amongst an array of strings.

**Objective:**
Return the longest prefix that is common to all strings in the array. If there is no common prefix, return an empty string `""`.

**Input:**
- An array of strings `strs` where `1 <= strs.length <= 200` and `0 <= strs[i].length <= 200`.

**Output:**
- A string representing the longest common prefix among all strings in the array.

**Constraints:**
- All strings consist of only lowercase English letters.
- The input array is non-empty.
- The solution must efficiently handle edge cases (e.g., empty strings, single string).

---

# 💡 Intuition

The problem requires identifying the longest prefix shared by all strings in the array. A prefix is a substring that appears at the beginning of a string.

**Key Insight:**
- The longest common prefix (LCP) cannot be longer than the shortest string in the array.
- We can iteratively compare characters at the same position across all strings until a mismatch is found.

**Thought Process:**
1. Start with the first string as the initial candidate for the LCP.
2. For each subsequent string, reduce the candidate LCP until it matches the prefix of the current string.
3. If the candidate LCP becomes empty, return early.
4. The optimal approach avoids unnecessary comparisons by leveraging the above insight.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves comparing each string in the array with the current candidate LCP, character by character, and updating the LCP accordingly.

**Steps:**
1. Initialize the LCP as the first string in the array.
2. For each subsequent string, compare it with the current LCP.
3. For each character position, check if the characters match. If they don't, truncate the LCP up to the previous position.
4. If the LCP becomes empty at any point, return `""`.
5. Return the final LCP after processing all strings.

---

## 🔹 Algorithm

1. If the input array is empty, return `""`.
2. Initialize `prefix` as the first string in the array.
3. For each string `s` in the array starting from the second string:
   - While `s` does not start with `prefix`:
     - Remove the last character from `prefix`.
     - If `prefix` becomes empty, return `""`.
4. Return `prefix`.

---

## 🔹 Code

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
```

---

## 🔹 Dry Run

**Input:** `["flower", "flow", "flight"]`

| Step | Current String | Current Prefix | Action | Updated Prefix |
|------|----------------|----------------|--------|----------------|
| 1    | "flower"       | "flower"       | Initialize prefix | "flower" |
| 2    | "flow"         | "flower"       | "flow" does not start with "flower" | Truncate to "flowe" |
| 3    | "flow"         | "flowe"        | "flow" does not start with "flowe" | Truncate to "flow" |
| 4    | "flow"         | "flow"         | "flow" starts with "flow" | No change |
| 5    | "flight"       | "flow"         | "flight" does not start with "flow" | Truncate to "flo" |
| 6    | "flight"       | "flo"          | "flight" does not start with "flo" | Truncate to "fl" |
| 7    | "flight"       | "fl"           | "flight" starts with "fl" | No change |

**Output:** `"fl"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(S), where S is the sum of all characters in all strings. In the worst case, we compare all characters of all strings. |
| Space Complexity | O(1), as we only use a constant amount of extra space. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the fact that the LCP cannot be longer than the shortest string in the array. We can iterate through each character position of the shortest string and check if all other strings have the same character at that position.

**Steps:**
1. Find the shortest string in the array to minimize the number of comparisons.
2. For each character position in the shortest string, check if all other strings have the same character at that position.
3. If a mismatch is found, return the LCP up to the previous position.
4. If all characters match, return the entire shortest string.

---

## 🔹 Why This Works

- By focusing on the shortest string, we minimize the number of comparisons.
- The approach ensures that we only compare characters up to the length of the shortest string, avoiding unnecessary checks.
- Early termination is possible if a mismatch is found, improving efficiency.

---

## 🔹 Algorithm

1. If the input array is empty, return `""`.
2. Find the shortest string in the array.
3. For each character position `i` in the shortest string:
   - For each string in the array, check if the character at position `i` matches the character in the shortest string.
   - If any string does not match, return the substring of the shortest string up to position `i`.
4. Return the shortest string as the LCP.

---

## 🔹 Code

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Find the shortest string to minimize comparisons
        String shortest = strs[0];
        for (String s : strs) {
            if (s.length() < shortest.length()) {
                shortest = s;
            }
        }

        for (int i = 0; i < shortest.length(); i++) {
            char c = shortest.charAt(i);
            for (String s : strs) {
                if (s.charAt(i) != c) {
                    return shortest.substring(0, i);
                }
            }
        }
        return shortest;
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `["flower", "flow", "flight"]`

| Iteration | Character Position | Character | All Match? | Action |
|-----------|---------------------|-----------|------------|--------|
| 1         | 0                   | 'f'       | Yes        | Continue |
| 2         | 1                   | 'l'       | Yes        | Continue |
| 3         | 2                   | 'o'       | No ("flight" has 'i') | Return "fl" |

**Output:** `"fl"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(S), where S is the sum of all characters in all strings. In the worst case, we compare all characters of the shortest string with all other strings. |
| Space Complexity | O(1), as we only use a constant amount of extra space. |

---

# 🔍 Edge Cases

| Edge Case | Expected Output | Explanation |
|-----------|-----------------|-------------|
| `[""]` | `""` | Empty string in the array. |
| `["a"]` | `"a"` | Single string in the array. |
| `["", "b"]` | `""` | One empty string in the array. |
| `["flower", "flow", "flight"]` | `"fl"` | Standard case with a common prefix. |
| `["dog", "racecar", "car"]` | `""` | No common prefix. |
| `["interspecies", "interstellar", "interstate"]` | `"inters"` | Long common prefix. |

---

# 📚 Key Takeaways

1. **Prefix Identification:** The problem revolves around identifying the longest shared prefix among strings, which is a common pattern in string manipulation problems.
2. **Early Termination:** Both approaches allow for early termination if a mismatch is found, improving efficiency.
3. **Optimal Shortest String:** The optimal approach minimizes comparisons by focusing on the shortest string in the array.
4. **Edge Case Handling:** Always consider edge cases like empty strings, single strings, and no common prefix.

---

# 🚀 Interview Tips

1. **Clarify Constraints:** Ask if the input array can contain empty strings or if all strings are non-empty.
2. **Discuss Trade-offs:** Compare the brute force and optimal approaches in terms of time and space complexity.
3. **Follow-up Questions:**
   - How would you handle Unicode characters?
   - Can you optimize further if the input is sorted?
   - How would you modify the solution for case-insensitive comparison?
4. **Common Pitfalls:**
   - Forgetting to handle empty input arrays.
   - Not considering the shortest string in the optimal approach.
   - Off-by-one errors in substring operations.

---

# ✅ Conclusion

The **optimal approach** is preferred due to its efficiency and clarity. By focusing on the shortest string in the array, we minimize the number of comparisons and ensure early termination upon finding a mismatch. This approach is both intuitive and efficient, making it ideal for interviews and production use.

**Key Insight:** The longest common prefix cannot exceed the length of the shortest string in the array, which is the foundation of the optimal solution.

**Learning Outcome:** Mastering string manipulation and prefix identification is crucial for solving a wide range of problems in technical interviews and real-world applications.