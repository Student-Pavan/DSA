# 📌 Problem Name

50. Pow(x, n)

---

# 📝 Problem Statement

Implement pow(x, n), which calculates x raised to the power n (i.e., xⁿ).

**Constraints:**
- -100.0 < x < 100.0
- -2³¹ <= n <= 2³¹-1
- -10⁴ <= xⁿ <= 10⁴

---

# 💡 Intuition

The key insight is recognizing that we can use the mathematical property of exponents to break down the problem into smaller subproblems. Specifically, we can use the property that xⁿ can be broken down into (x²)ⁿ/², which allows us to reduce the problem size exponentially.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves multiplying x by itself n times. This is straightforward but inefficient, especially for large values of n.

---

## 🔹 Algorithm

1. Initialize the result to 1.
2. If n is negative, convert the problem to calculating (1/x)^(-n).
3. For each iteration from 1 to n, multiply the result by x.
4. Return the result.

---

## 🔹 Code

```java
class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }
        double result = 1;
        for (int i = 0; i < exp; i++) {
            result *= x;
        }
        return result;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with x = 2.0 and n = 10.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | x = 2.0 | result = 1 | result = 1 * 2.0 = 2.0 |
| 2 | x = 2.0 | result = 2.0 | result = 2.0 * 2.0 = 4.0 |
| 3 | x = 2.0 | result = 4.0 | result = 4.0 * 2.0 = 8.0 |
| 4 | x = 2.0 | result = 8.0 | result = 8.0 * 2.0 = 16.0 |
| 5 | x = 2.0 | result = 16.0 | result = 16.0 * 2.0 = 32.0 |
| 6 | x = 2.0 | result = 32.0 | result = 32.0 * 2.0 = 64.0 |
| 7 | x = 2.0 | result = 64.0 | result = 64.0 * 2.0 = 128.0 |
| 8 | x = 2.0 | result = 128.0 | result = 128.0 * 2.0 = 256.0 |
| 9 | x = 2.0 | result = 256.0 | result = 256.0 * 2.0 = 512.0 |
| 10 | x = 2.0 | result = 512.0 | result = 512.0 * 2.0 = 1024.0 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the mathematical property of exponents to break down the problem into smaller subproblems. Specifically, we can use the property that xⁿ can be broken down into (x²)ⁿ/², which allows us to reduce the problem size exponentially.

---

## 🔹 Why This Works

This approach works because it leverages the mathematical property of exponents to reduce the problem size exponentially. This results in a time complexity of O(log n), which is significantly faster than the brute force approach.

---

## 🔹 Algorithm

1. Initialize the result to 1.
2. If n is negative, convert the problem to calculating (1/x)^(-n).
3. While n is greater than 0:
   - If n is odd, multiply the result by x.
   - Square x.
   - Divide n by 2.
4. Return the result.

---

## 🔹 Code

```java
class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }
        double result = 1;
        while (exp > 0) {
            if (exp % 2 != 0) {
                result *= x;
            }
            x *= x;
            exp /= 2;
        }
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with x = 2.0 and n = 10.

| Step | exp | x | result | Action |
|---|---|---|---|---|
| 1 | 10 | 2.0 | 1 | exp is even, so we square x and divide exp by 2 |
| 2 | 5 | 4.0 | 1 | exp is odd, so we multiply result by x and then square x and divide exp by 2 |
| 3 | 2 | 16.0 | 4.0 | exp is even, so we square x and divide exp by 2 |
| 4 | 1 | 256.0 | 4.0 | exp is odd, so we multiply result by x and then square x and divide exp by 2 |
| 5 | 0 | 65536.0 | 1024.0 | exp is 0, so we exit the loop |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- x = 2.0, n = 0 (should return 1)
- x = 2.0, n = 1 (should return 2.0)
- x = 2.0, n = -1 (should return 0.5)
- x = 2.0, n = -2 (should return 0.25)
- x = 2.0, n = 2147483647 (should return 2.0^2147483647)
- x = 2.0, n = -2147483648 (should return 2.0^-2147483648)

---

# 📚 Key Takeaways

- The brute force approach is straightforward but inefficient.
- The optimal approach leverages the mathematical property of exponents to reduce the problem size exponentially.
- Understanding the mathematical properties of exponents is crucial for solving this problem efficiently.
- The optimal approach has a time complexity of O(log n), which is significantly faster than the brute force approach.

---

# 🚀 Interview Tips

- Be prepared to explain the mathematical properties of exponents.
- Be prepared to discuss the trade-offs between the brute force and optimal approaches.
- Be prepared to discuss the edge cases and how to handle them.
- Be prepared to discuss the time and space complexity of the optimal approach.

---

# ✅ Conclusion

The optimal approach is preferred because it leverages the mathematical property of exponents to reduce the problem size exponentially, resulting in a time complexity of O(log n). Understanding the mathematical properties of exponents is crucial for solving this problem efficiently.