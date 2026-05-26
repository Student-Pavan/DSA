# 📌 647. Palindromic Substrings

---

# 📝 Problem Statement

Given a string `s`, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

**Constraints:**
- `1 <= s.length <= 1000`
- `s` consists of lowercase English letters.

---

# 💡 Intuition

The key insight is recognizing that palindromes can be of even or odd length. For each character in the string, we can expand around its center to find all possible palindromic substrings. This approach efficiently captures both odd-length and even-length palindromes in a single pass through the string.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of the string to determine if they are palindromes. This is done by:
1. Iterating through all possible starting indices of substrings.
2. For each starting index, iterating through all possible ending indices.
3. Checking if the substring from the starting to ending index is a palindrome.

## 🔹 Algorithm

1. Initialize a counter to zero.
2. Iterate through each character in the string using an outer loop.
3. For each character, iterate through all possible substrings starting at that character using an inner loop.
4. For each substring, check if it is a palindrome by comparing characters from both ends moving towards the center.
5. If the substring is a palindrome, increment the counter.
6. Return the counter after all substrings have been checked.

## 🔹 Code

```java
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the string "aaa".

| Iteration | i | j | Substring | isPalindrome | Count |
|-----------|---|---|-----------|--------------|-------|
| 1         | 0 | 0 | "a"       | true         | 1     |
| 2         | 0 | 1 | "aa"      | true         | 2     |
| 3         | 0 | 2 | "aaa"     | true         | 3     |
| 4         | 1 | 1 | "a"       | true         | 4     |
| 5         | 1 | 2 | "aa"      | true         | 5     |
| 6         | 2 | 2 | "a"       | true         | 6     |

Final count: 6

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves expanding around the center for each character in the string. This approach efficiently captures all possible palindromic substrings by considering each character as the center of a potential palindrome and expanding outwards to find all valid palindromes.

## 🔹 Why This Works

This approach works because it leverages the fact that every palindrome has a center. For odd-length palindromes, the center is a single character, and for even-length palindromes, the center is between two characters. By expanding around each character, we can find all possible palindromes in linear time relative to the length of the string.

## 🔹 Algorithm

1. Initialize a counter to zero.
2. Iterate through each character in the string.
3. For each character, expand around the center to find all possible palindromes.
4. Expand around the center for both odd-length and even-length palindromes.
5. Increment the counter for each palindrome found.
6. Return the counter after all characters have been processed.

## 🔹 Code

```java
class Solution {
    public int countSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += expand(i, i, s); // Odd-length palindromes
            count += expand(i, i + 1, s); // Even-length palindromes
        }

        return count;
    }

    private int expand(int left, int right, String s) {
        int count = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the string "aaa".

| Iteration | i | Left | Right | Action | Count |
|-----------|---|------|-------|--------|-------|
| 1         | 0 | 0    | 0     | Expand | 1     |
|           |   | -1   | 1     | Exit   |       |
| 2         | 0 | 0    | 1     | Expand | 1     |
|           |   | -1   | 2     | Exit   |       |
| 3         | 1 | 1    | 1     | Expand | 1     |
|           |   | 0    | 2     | Expand | 2     |
|           |   | -1   | 3     | Exit   |       |
| 4         | 1 | 1    | 2     | Expand | 1     |
|           |   | 0    | 3     | Exit   |       |
| 5         | 2 | 2    | 2     | Expand | 1     |
|           |   | 1    | 3     | Exit   |       |
| 6         | 2 | 2    | 3     | Exit   |       |

Final count: 6

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty String**: The function should return 0.
- **Single Character**: The function should return 1.
- **All Characters Same**: The function should return the sum of the first n natural numbers where n is the length of the string.
- **No Palindromes**: The function should return the length of the string.
- **Palindromes of Different Lengths**: The function should correctly count all palindromic substrings, including those of different lengths.

---

# 📚 Key Takeaways

- **Center Expansion**: This technique is efficient for finding palindromic substrings by leveraging the symmetry property of palindromes.
- **Time Complexity**: The optimal approach reduces the time complexity from O(n³) to O(n²) by avoiding redundant checks.
- **Space Complexity**: Both approaches use constant space, making them space-efficient.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to optimize further if the string is very large or if the solution needs to be adapted for different constraints.
- **Common Pitfalls**: Ensure that the center expansion correctly handles both odd and even-length palindromes.
- **Alternative Approaches**: Consider using dynamic programming to solve the problem, but note that the center expansion method is more efficient for this specific problem.

---

# ✅ Conclusion

The optimal approach using center expansion is preferred because it efficiently captures all possible palindromic substrings in O(n²) time with O(1) space complexity. This method is both time and space efficient, making it ideal for interview scenarios where performance is critical. The key insight is recognizing the symmetry of palindromes and leveraging it to avoid redundant checks.