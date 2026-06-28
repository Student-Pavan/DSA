# 📌 Online Stock Span

---

# 📝 Problem Statement

Design a class `StockSpanner` that collects daily price quotes for a stock and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were `[100, 80, 60, 70, 60, 75, 85]`, then the stock spans would be `[1, 1, 1, 2, 1, 4, 6]`.

**Constraints:**
- `1 <= price <= 10^5`
- At most `10^4` calls will be made to `next`.

---

# 💡 Intuition

The key insight is to efficiently track the previous higher prices and their spans. The brute force approach checks each previous day to find the span, which is inefficient. The optimal approach uses a stack to keep track of the indices of the prices and their spans, allowing us to compute the span in constant time for each call to `next`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

For each new price, iterate backward through the list of previous prices to count how many consecutive days the price was less than or equal to the current price. This approach is straightforward but inefficient, especially for large input sizes.

---

## 🔹 Algorithm

1. Initialize an empty list to store the prices.
2. For each new price:
   - Add the price to the list.
   - Initialize a counter to 1.
   - Iterate backward through the list starting from the previous day.
   - For each day, if the price is less than or equal to the current price, increment the counter.
   - If the price is greater than the current price, break the loop.
   - Return the counter as the span.

---

## 🔹 Code

```java
import java.util.ArrayList;

class StockSpanner {
    private ArrayList<Integer> prices;

    public StockSpanner() {
        prices = new ArrayList<>();
    }

    public int next(int price) {
        prices.add(price);
        int span = 1;
        int index = prices.size() - 2;

        while (index >= 0 && prices.get(index) <= price) {
            span++;
            index--;
        }

        return span;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the example `[100, 80, 60, 70, 60, 75, 85]`.

| Iteration | Price | Span Calculation | Result |
|---|---|---|---|
| 1 | 100 | No previous prices | 1 |
| 2 | 80 | 80 <= 100? No | 1 |
| 3 | 60 | 60 <= 80? No | 1 |
| 4 | 70 | 70 <= 60? No, 70 <= 80? Yes | 2 |
| 5 | 60 | 60 <= 70? No | 1 |
| 6 | 75 | 75 <= 60? No, 75 <= 70? No, 75 <= 80? Yes | 4 |
| 7 | 85 | 85 <= 75? No, 85 <= 60? No, 85 <= 70? No, 85 <= 80? Yes, 85 <= 100? Yes | 6 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) for each call to `next` in the worst case |
| Space Complexity | O(n) to store the prices |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use a stack to keep track of the indices of the prices and their spans. For each new price, pop elements from the stack while the current price is greater than or equal to the price at the top of the stack. The span is then calculated as the difference between the current index and the index at the top of the stack (or the current index plus one if the stack is empty).

---

## 🔹 Why This Works

This approach efficiently tracks the previous higher prices and their spans using a stack. By popping elements from the stack, we ensure that we only consider relevant previous prices, reducing the number of operations needed to compute the span.

---

## 🔹 Algorithm

1. Initialize a stack to store indices of the prices and a list to store the prices.
2. For each new price:
   - Add the price to the list.
   - While the stack is not empty and the price at the top of the stack is less than or equal to the current price, pop the stack.
   - If the stack is empty, the span is the current index plus one.
   - Otherwise, the span is the difference between the current index and the index at the top of the stack.
   - Push the current index onto the stack.
   - Return the span.

---

## 🔹 Code

```java
import java.util.Stack;
import java.util.ArrayList;

class StockSpanner {
    private Stack<Integer> stack;
    private ArrayList<Integer> prices;

    public StockSpanner() {
        stack = new Stack<>();
        prices = new ArrayList<>();
    }

    public int next(int price) {
        prices.add(price);
        int index = prices.size() - 1;

        while (!stack.isEmpty() && prices.get(stack.peek()) <= price) {
            stack.pop();
        }

        int span;
        if (stack.isEmpty()) {
            span = index + 1;
        } else {
            span = index - stack.peek();
        }

        stack.push(index);
        return span;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the example `[100, 80, 60, 70, 60, 75, 85]`.

| Iteration | Price | Stack Operations | Span Calculation | Result |
|---|---|---|---|---|
| 1 | 100 | Push 0 | Stack is empty | 1 |
| 2 | 80 | Push 1 | 1 - 0 = 1 | 1 |
| 3 | 60 | Push 2 | 2 - 1 = 1 | 1 |
| 4 | 70 | Pop 2, Push 3 | 3 - 1 = 2 | 2 |
| 5 | 60 | Push 4 | 4 - 3 = 1 | 1 |
| 6 | 75 | Pop 4, Pop 3, Push 5 | 5 - 1 = 4 | 4 |
| 7 | 85 | Pop 5, Pop 1, Push 6 | 6 - 0 = 6 | 6 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(1) amortized for each call to `next` |
| Space Complexity | O(n) to store the stack and prices |

---

# 🔍 Edge Cases

- **Single Price:** The span should be 1.
- **Increasing Prices:** The span for each day should be 1.
- **Decreasing Prices:** The span for each day should be the number of days up to that point.
- **Equal Prices:** The span should include all consecutive equal prices.

---

# 📚 Key Takeaways

- The brute force approach is simple but inefficient for large input sizes.
- The optimal approach uses a stack to efficiently track previous higher prices and their spans.
- Understanding the use of a stack to optimize span calculation is crucial for solving similar problems.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Emphasize the importance of using a stack to optimize the solution.
- Consider follow-up questions about handling multiple stocks or different span definitions.

---

# ✅ Conclusion

The optimal approach using a stack is significantly more efficient than the brute force approach, especially for large input sizes. Understanding the use of a stack to track previous higher prices and their spans is a valuable skill for solving similar problems in interviews.