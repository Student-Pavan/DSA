# Remove K Digits

---

# 📝 Problem Statement

Given a non-negative integer represented as a string `num` and an integer `k`, remove `k` digits from the number to form the smallest possible number. The number should not contain leading zeros, and if all digits are removed, return "0".

**Constraints:**
- `1 <= k <= num.length <= 10^5`
- `num` consists only of digits.

---

# 💡 Intuition

The key insight is to use a greedy approach with a stack to remove digits in a way that maximizes the reduction in the number's value. We want to remove digits that are larger than the subsequent digits, as this will result in a smaller number when removed.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible combinations of removing `k` digits and selecting the smallest number from them. This is computationally expensive and not feasible for large inputs.

## 🔹 Algorithm

1. Generate all possible combinations of removing `k` digits from the number.
2. For each combination, convert the remaining digits to a number.
3. Track the smallest number encountered.
4. Return the smallest number as a string, ensuring no leading zeros.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public String removeKdigits(String num, int k) {
        List<String> combinations = new ArrayList<>();
        generateCombinations(num, k, 0, new StringBuilder(), combinations);

        long minNum = Long.MAX_VALUE;
        for (String combination : combinations) {
            long currentNum = Long.parseLong(combination);
            if (currentNum < minNum) {
                minNum = currentNum;
            }
        }

        String result = String.valueOf(minNum);
        if (result.length() == 0) {
            return "0";
        }
        return result;
    }

    private void generateCombinations(String num, int k, int index, StringBuilder current, List<String> combinations) {
        if (k == 0) {
            combinations.add(current.toString());
            return;
        }
        if (index == num.length()) {
            return;
        }

        // Include the current digit
        current.append(num.charAt(index));
        generateCombinations(num, k, index + 1, current, combinations);
        current.deleteCharAt(current.length() - 1);

        // Exclude the current digit
        generateCombinations(num, k - 1, index + 1, current, combinations);
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `num = "1432219"` and `k = 3`.

1. **Initial Call:** `generateCombinations("1432219", 3, 0, "", combinations)`
   - **Include '1':** `current = "1"`
     - **Next Call:** `generateCombinations("1432219", 3, 1, "1", combinations)`
       - **Include '4':** `current = "14"`
         - **Next Call:** `generateCombinations("1432219", 3, 2, "14", combinations)`
           - **Include '3':** `current = "143"`
             - **Next Call:** `generateCombinations("1432219", 3, 3, "143", combinations)`
               - **Include '2':** `current = "1432"`
                 - **Next Call:** `generateCombinations("1432219", 3, 4, "1432", combinations)`
                   - **Include '2':** `current = "14322"`
                     - **Next Call:** `generateCombinations("1432219", 3, 5, "14322", combinations)`
                       - **Include '1':** `current = "143221"`
                         - **Next Call:** `generateCombinations("1432219", 3, 6, "143221", combinations)`
                           - **Include '9':** `current = "1432219"`
                             - **Next Call:** `generateCombinations("1432219", 3, 7, "1432219", combinations)`
                               - **k = 0:** Add "1432219" to combinations.
                       - **Exclude '1':** `generateCombinations("1432219", 2, 6, "14322", combinations)`
                         - Continue similarly for other combinations.
   - **Exclude '1':** `generateCombinations("1432219", 2, 1, "", combinations)`
     - Continue similarly for other combinations.

2. **Final Combinations:** After generating all combinations, the smallest number is found to be "1219".

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(2^n) where n is the length of num |
| Space Complexity | O(n) for recursion stack |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a stack to greedily remove digits. We iterate through each digit, and for each digit, we remove digits from the stack that are larger than the current digit and we still have removals left. This ensures that the smallest number is formed.

## 🔹 Why This Works

By removing larger digits earlier, we maximize the reduction in the number's value. The stack helps in maintaining the order of digits and allows us to efficiently remove digits in a single pass.

## 🔹 Algorithm

1. Initialize an empty stack to store digits.
2. Iterate through each digit in the input string.
3. For each digit, while the stack is not empty, the current digit is less than the top of the stack, and we still have removals left, pop the top of the stack and decrement `k`.
4. Push the current digit onto the stack.
5. If there are remaining removals after the iteration, remove the remaining `k` digits from the end of the stack.
6. Construct the result string from the stack, skipping any leading zeros.
7. If the result string is empty, return "0"; otherwise, return the result string.

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        for (char ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }

        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `num = "1432219"` and `k = 3`.

| Step | Stack | Action | k |
|---|---|---|---|
| 1 | [] | Push '1' | 3 |
| 2 | ['1'] | Push '4' | 3 |
| 3 | ['1', '4'] | Push '3' | 3 |
| 4 | ['1', '3'] | Pop '4' (since '3' < '4') | 2 |
| 5 | ['1'] | Push '3' | 2 |
| 6 | ['1', '3'] | Push '2' | 2 |
| 7 | ['1', '2'] | Pop '3' (since '2' < '3') | 1 |
| 8 | ['1'] | Push '2' | 1 |
| 9 | ['1', '2'] | Push '2' | 1 |
| 10 | ['1', '2', '2'] | Push '1' | 1 |
| 11 | ['1', '2', '2', '1'] | Push '9' | 1 |
| 12 | ['1', '2', '2', '1', '9'] | Pop '1' (since '9' > '1') | 0 |
| 13 | ['1', '2', '2', '1'] | No more pops | 0 |
| 14 | ['1', '2', '2', '1'] | Construct result | - |
| 15 | ['1', '2', '2', '1'] | Remove leading zeros | - |
| 16 | ['1', '2', '2', '1'] | Final result | "1219" |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) where n is the length of num |
| Space Complexity | O(n) for the stack |

---

# 🔍 Edge Cases

- **Empty Input:** `num = ""`, `k = 0` → Return "0".
- **Single Digit:** `num = "5"`, `k = 1` → Return "0".
- **All Digits Removed:** `num = "10"`, `k = 2` → Return "0".
- **Leading Zeros:** `num = "10200"`, `k = 1` → Return "200".
- **Large Input:** `num = "1234567890"`, `k = 9` → Return "0".

---

# 📚 Key Takeaways

- **Greedy Approach:** The optimal solution uses a greedy approach to remove digits in a way that maximizes the reduction in the number's value.
- **Stack Utilization:** The stack helps in efficiently maintaining the order of digits and allows for easy removal of digits.
- **Edge Cases:** Handling edge cases like leading zeros and all digits removed is crucial for a complete solution.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss how the solution can be optimized further or how it can be adapted for different constraints.
- **Common Pitfalls:** Ensure that leading zeros are handled correctly and that the solution works for edge cases.
- **Alternative Approaches:** Consider using a deque or a different data structure to optimize the solution further.

---

# ✅ Conclusion

The optimal solution efficiently removes `k` digits to form the smallest possible number using a greedy approach with a stack. This approach ensures that the solution is both time and space efficient, making it suitable for large inputs. Understanding the greedy approach and stack utilization is crucial for solving such problems effectively.