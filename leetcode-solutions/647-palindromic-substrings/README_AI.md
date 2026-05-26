```markdown
# 647. Palindromic Substrings

## Problem Statement

Given a string `s`, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

### Example 1:
```
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
```
### Example 2:
```
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
```

### Constraints:
- `1 <= s.length <= 1000`
- `s` consists of lowercase English letters.

## Intuition

The key insight is that a palindrome can be centered around either a single character (for odd-length palindromes) or between two characters (for even-length palindromes). By expanding around each possible center, we can efficiently count all palindromic substrings.

## Brute Force Approach

### Approach
1. Generate all possible substrings of the string.
2. For each substring, check if it is a palindrome.
3. Count all such palindromic substrings.

### Algorithm
1. Initialize a counter to zero.
2. Use nested loops to generate all possible substrings:
   - Outer loop runs from the start of the string to the end.
   - Inner loop runs from the current outer loop index to the end of the string.
3. For each substring, check if it is a palindrome by comparing characters from both ends moving towards the center.
4. If it is a palindrome, increment the counter.
5. Return the counter.

### Code
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

### Dry Run
Let's dry run the algorithm with `s = "aaa"`:

1. i = 0:
   - j = 0: substring "a" → palindrome → count = 1
   - j = 1: substring "aa" → palindrome → count = 2
   - j = 2: substring "aaa" → palindrome → count = 3
2. i = 1:
   - j = 1: substring "a" → palindrome → count = 4
   - j = 2: substring "aa" → palindrome → count = 5
3. i = 2:
   - j = 2: substring "a" → palindrome → count = 6

Final count: 6

### Complexity
- Time Complexity: O(n^3) - We have O(n^2) substrings, and each substring check takes O(n) time.
- Space Complexity: O(1) - We use constant extra space.

## Optimal Approach

### Approach
1. Expand around each possible center to find all palindromic substrings.
2. For each character in the string, treat it as the center of a potential palindrome and expand outwards to find all palindromes.
3. Also treat each pair of adjacent characters as the center for even-length palindromes and expand outwards.

### Why This Works
- This approach efficiently finds all palindromic substrings by leveraging the fact that every palindrome has a center.
- By expanding around each possible center, we can find all palindromes in O(n^2) time, which is optimal for this problem.

### Algorithm
1. Initialize a counter to zero.
2. Iterate through each character in the string:
   - Treat the current character as the center of a potential odd-length palindrome and expand outwards.
   - Treat the current character and the next character as the center of a potential even-length palindrome and expand outwards.
3. For each expansion, if the characters at the current left and right pointers are equal, increment the counter and continue expanding.
4. Return the counter.

### Code
```java
class Solution {
    public int countSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += expandAroundCenter(s, i, i); // Odd length
            count += expandAroundCenter(s, i, i + 1); // Even length
        }

        return count;
    }

    private int expandAroundCenter(String s, int left, int right) {
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

### Dry Run
Let's dry run the algorithm with `s = "aaa"`:

1. i = 0:
   - Odd length: expandAroundCenter(s, 0, 0) → "a", "aaa" → count = 2
   - Even length: expandAroundCenter(s, 0, 1) → "aa" → count = 1
   - Total count = 3
2. i = 1:
   - Odd length: expandAroundCenter(s, 1, 1) → "a" → count = 1
   - Even length: expandAroundCenter(s, 1, 2) → "aa" → count = 1
   - Total count = 4
3. i = 2:
   - Odd length: expandAroundCenter(s, 2, 2) → "a" → count = 1
   - Even length: expandAroundCenter(s, 2, 3) → out of bounds → count = 0
   - Total count = 5

Final count: 6

### Complexity
- Time Complexity: O(n^2) - We expand around each of the O(n) centers, and each expansion takes O(n) time in the worst case.
- Space Complexity: O(1) - We use constant extra space.

## Edge Cases
- Single character string: "a" → 1 palindromic substring.
- All characters same: "aaa" → 6 palindromic substrings.
- No palindromic substrings: "abc" → 3 palindromic substrings (each character is a palindrome).
- Even-length palindromes: "abba" → 6 palindromic substrings.

## Key Takeaways
- The optimal approach is more efficient than the brute force approach.
- The optimal approach leverages the properties of palindromes to reduce the time complexity.
- Understanding the center of a palindrome is crucial for solving this problem efficiently.

## Interview Tips
- Start by understanding the problem and the examples.
- Consider the brute force approach first to ensure you understand the problem.
- Optimize the brute force approach by recognizing patterns or properties of the problem.
- Practice dry runs to ensure your understanding of the algorithm.
- Be prepared to explain your thought process and the reasoning behind your approach.

## Conclusion
The optimal approach of expanding around centers is more efficient and elegant than the brute force approach. It leverages the properties of palindromes to count all possible palindromic substrings in O(n^2) time, which is optimal for this problem. Understanding this approach is crucial for solving similar problems efficiently in interviews.
```