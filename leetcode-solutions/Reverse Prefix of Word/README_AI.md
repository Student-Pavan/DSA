# 📌 Reverse Prefix of Word

**LeetCode Problem #2000 | Easy**

---

# 📝 Problem Statement

Given a **0-indexed** string `word` and a character `ch`, reverse the segment of `word` that starts at index `0` and ends at the **first occurrence** of `ch` (inclusive). If the character `ch` does not exist in `word`, return `word` unchanged.

### **Objective**
Reverse the prefix of `word` up to and including the first occurrence of `ch`.

### **Input**
- `word`: A string of length `1 <= word.length <= 250`
- `ch`: A lowercase English letter

### **Output**
- The modified string after reversing the specified prefix (or the original string if `ch` is not found)

### **Constraints**
- `word` consists of lowercase English letters only
- `ch` is a lowercase English letter

---

# 💡 Intuition

The problem requires reversing a prefix of a string up to the first occurrence of a given character. The key insight is:
- **Locate the first occurrence** of `ch` in `word`.
- **Reverse the substring** from index `0` to that position (inclusive).
- If `ch` is not found, return the original string.

This can be approached naively by converting the string to a character array and manually reversing the prefix, or optimally by using two-pointer technique for in-place reversal.

---

# 🐌 Brute Force Approach

## 🔹 Approach
1. **Find the index** of the first occurrence of `ch` in `word`.
2. If `ch` is not found, return `word` as-is.
3. Convert `word` to a character array for mutability.
4. **Reverse the prefix** from index `0` to the found index using a loop.
5. Convert the character array back to a string and return it.

This approach is straightforward but involves multiple conversions and manual reversal.

---

## 🔹 Algorithm
1. Find the index of `ch` in `word` using `indexOf()`.
2. If index is `-1`, return `word`.
3. Convert `word` to a character array.
4. Initialize two pointers: `left = 0`, `right = index`.
5. Swap characters at `left` and `right`, then move `left++` and `right--` until `left >= right`.
6. Convert the array back to a string and return.

---

## 🔹 Code

```java
class Solution {
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        }

        char[] chars = word.toCharArray();
        int left = 0;
        int right = index;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }
}
```

---

## 🔹 Dry Run

**Input:** `word = "abcdefd"`, `ch = 'd'`

| Step | Left | Right | Action | Array State |
|------|------|-------|--------|-------------|
| 0    | 0    | 3     | Initial | `['a','b','c','d','e','f','d']` |
| 1    | 0    | 3     | Swap 'a' and 'd' | `['d','b','c','a','e','f','d']` |
| 2    | 1    | 2     | Swap 'b' and 'c' | `['d','c','b','a','e','f','d']` |
| 3    | 2    | 1     | Terminate (left >= right) | `['d','c','b','a','e','f','d']` |

**Output:** `"dcbaefd"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

> **Note:** The space complexity is O(n) due to the character array conversion.

---

# ⚡ Optimal Approach

## 🔹 Approach
The optimal approach avoids unnecessary conversions by:
1. **Finding the index** of `ch` in one pass.
2. **Reversing the prefix in-place** using two pointers directly on the string (via `StringBuilder` for mutability).
3. **Constructing the result** efficiently.

This reduces overhead and improves clarity.

---

## 🔹 Why This Works
- **Single Pass for Index:** `indexOf()` is O(n) and efficient.
- **In-Place Reversal:** Using `StringBuilder` allows O(1) character access and mutation.
- **Efficiency:** Avoids multiple conversions; constructs result in one go.

---

## 🔹 Algorithm
1. Find the index of `ch` in `word`.
2. If not found, return `word`.
3. Use `StringBuilder` to build the result:
   - Append the reversed prefix (from `index` down to `0`).
   - Append the remaining suffix (from `index+1` to end).
4. Return the constructed string.

---

## 🔹 Code

```java
class Solution {
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = index; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        for (int i = index + 1; i < word.length(); i++) {
            sb.append(word.charAt(i));
        }

        return sb.toString();
    }
}
```

---

## 🔹 Detailed Dry Run

**Input:** `word = "abcdefd"`, `ch = 'd'`

| Step | Action | StringBuilder State |
|------|--------|---------------------|
| 0    | Append 'd' (index 3) | `"d"` |
| 1    | Append 'c' (index 2) | `"dc"` |
| 2    | Append 'b' (index 1) | `"dcb"` |
| 3    | Append 'a' (index 0) | `"dcba"` |
| 4    | Append 'e' (index 4) | `"dcbae"` |
| 5    | Append 'f' (index 5) | `"dcbaef"` |
| 6    | Append 'd' (index 6) | `"dcbaefd"` |

**Output:** `"dcbaefd"`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

> **Note:** Space is O(n) due to `StringBuilder`, but avoids multiple conversions.

---

# 🔍 Edge Cases

| Case | Input | Expected Output | Reason |
|------|-------|-----------------|--------|
| `ch` not in `word` | `"abc"`, `'z'` | `"abc"` | No reversal needed |
| `ch` at index 0 | `"abc"`, `'a'` | `"abc"` | Reversing single char changes nothing |
| `ch` at last index | `"abcd"`, `'d'` | `"dcba"` | Entire string reversed |
| Single character | `"a"`, `'a'` | `"a"` | No change |
| All same characters | `"aaaa"`, `'a'` | `"aaaa"` | Reversing prefix doesn't change order |

---

# 📚 Key Takeaways

- **Prefix Reversal:** Focus on the segment from start to first occurrence of `ch`.
- **Two-Pointer Technique:** Efficient for in-place reversal.
- **String Immutability:** Use `StringBuilder` or `char[]` for mutation.
- **Edge Cases:** Always check for `ch` absence and single-character inputs.

---

# 🚀 Interview Tips

- **Follow-up:** What if `ch` appears multiple times? (Problem specifies first occurrence.)
- **Pitfall:** Forgetting to check if `ch` exists leads to incorrect reversal.
- **Alternative:** Use `StringBuilder.reverse()` on the prefix substring.
- **Optimization:** The optimal solution avoids unnecessary conversions and is more readable.

---

# ✅ Conclusion

The **optimal approach** using `StringBuilder` is preferred for its **clarity** and **efficiency**. It constructs the result in a single pass after locating the target character, making it both **interview-friendly** and **production-ready**.

**Key Insight:** Reversing a prefix is a classic two-pointer problem—locate the boundary, then reverse in-place. Always validate the existence of the target character first.