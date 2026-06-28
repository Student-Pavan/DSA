# 📌 Minimum Number of Days to Make m Bouquets

---

# 📝 Problem Statement

You are given an integer array `bloomDay`, an integer `m` and an integer `k`.

You want to make `m` bouquets. To make one bouquet, you need to use `k` adjacent flowers from the garden.

The garden consists of `n` flowers, the ith flower will bloom in the `bloomDay[i]` and then can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make `m` bouquets from the garden. If it is impossible to make `m` bouquets return `-1`.

**Constraints:**
- `bloomDay.length == n`
- `1 <= n <= 10^5`
- `1 <= bloomDay[i] <= 10^9`
- `1 <= m, k <= 10^6`

---

# 💡 Intuition

The problem requires finding the minimum number of days needed to make `m` bouquets, where each bouquet requires `k` adjacent flowers that have bloomed. The key insight is that we can use binary search to efficiently determine the minimum number of days required. The approach involves checking if it's possible to make `m` bouquets on a given day by counting the number of adjacent flowers that have bloomed by that day.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Check Feasibility**: First, check if it's possible to make `m` bouquets given the constraints. If `m * k` is greater than the number of flowers, return `-1` immediately.
2. **Iterate Through Days**: For each day from the minimum bloom day to the maximum bloom day, check if it's possible to make `m` bouquets by that day.
3. **Count Bouquets**: For each day, iterate through the `bloomDay` array and count the number of adjacent flowers that have bloomed by that day. If the count of adjacent flowers reaches `k`, increment the bouquet count.
4. **Return Result**: The first day where the bouquet count reaches `m` is the answer.

---

## 🔹 Algorithm

1. **Check Feasibility**: If `m * k > bloomDay.length`, return `-1`.
2. **Initialize Variables**: Set `minDay` to the minimum value in `bloomDay` and `maxDay` to the maximum value in `bloomDay`.
3. **Iterate Through Days**: For each day from `minDay` to `maxDay`:
   - Initialize `bouquets` and `count` to `0`.
   - For each flower in `bloomDay`:
     - If the flower has bloomed by the current day, increment `count`.
     - If `count` equals `k`, increment `bouquets` and reset `count` to `0`.
     - If the flower hasn't bloomed, reset `count` to `0`.
   - If `bouquets` is greater than or equal to `m`, return the current day.
4. **Return -1**: If no day is found, return `-1`.

---

## 🔹 Code

```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }

        for (int day = minDay; day <= maxDay; day++) {
            int bouquets = 0;
            int count = 0;

            for (int bloom : bloomDay) {
                if (bloom <= day) {
                    count++;
                    if (count == k) {
                        bouquets++;
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }

            if (bouquets >= m) {
                return day;
            }
        }

        return -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `bloomDay = [1, 10, 3, 10, 2]`, `m = 3`, and `k = 1`.

| Day | Bouquets | Count | Action |
|-----|----------|-------|--------|
| 1   | 0        | 0     | Increment count to 1 (bloomDay[0] = 1 <= 1) |
| 1   | 1        | 0     | Increment bouquets to 1 (count == 1) |
| 1   | 1        | 1     | Increment count to 1 (bloomDay[1] = 10 > 1) |
| 1   | 1        | 0     | Reset count to 0 (bloomDay[1] = 10 > 1) |
| 1   | 1        | 1     | Increment count to 1 (bloomDay[2] = 3 > 1) |
| 1   | 1        | 0     | Reset count to 0 (bloomDay[2] = 3 > 1) |
| 1   | 1        | 1     | Increment count to 1 (bloomDay[3] = 10 > 1) |
| 1   | 1        | 0     | Reset count to 0 (bloomDay[3] = 10 > 1) |
| 1   | 2        | 0     | Increment bouquets to 2 (count == 1) |
| 1   | 2        | 0     | No action (bouquets >= m not met) |

Since bouquets is not greater than or equal to `m`, we continue to the next day.

| Day | Bouquets | Count | Action |
|-----|----------|-------|--------|
| 2   | 0        | 0     | Increment count to 1 (bloomDay[0] = 1 <= 2) |
| 2   | 1        | 0     | Increment bouquets to 1 (count == 1) |
| 2   | 1        | 1     | Increment count to 1 (bloomDay[1] = 10 > 2) |
| 2   | 1        | 0     | Reset count to 0 (bloomDay[1] = 10 > 2) |
| 2   | 1        | 1     | Increment count to 1 (bloomDay[2] = 3 <= 2) |
| 2   | 2        | 0     | Increment bouquets to 2 (count == 1) |
| 2   | 2        | 1     | Increment count to 1 (bloomDay[3] = 10 > 2) |
| 2   | 2        | 0     | Reset count to 0 (bloomDay[3] = 10 > 2) |
| 2   | 3        | 0     | Increment bouquets to 3 (count == 1) |
| 2   | 3        | 0     | Return day 2 (bouquets >= m) |

The minimum number of days required is `2`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n * (maxDay - minDay)) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. **Check Feasibility**: First, check if it's possible to make `m` bouquets given the constraints. If `m * k` is greater than the number of flowers, return `-1` immediately.
2. **Binary Search**: Use binary search to find the minimum number of days required. The search space is between the minimum and maximum bloom days.
3. **Check Feasibility for Mid Day**: For each mid day during the binary search, check if it's possible to make `m` bouquets by that day.
4. **Adjust Search Space**: If it's possible to make `m` bouquets by the mid day, adjust the search space to the left. Otherwise, adjust the search space to the right.
5. **Return Result**: The minimum day found during the binary search is the answer.

---

## 🔹 Why This Works

The binary search approach efficiently narrows down the search space by checking the feasibility of making `m` bouquets at the mid day. This reduces the time complexity from O(n * (maxDay - minDay)) to O(n log(maxDay - minDay)).

---

## 🔹 Algorithm

1. **Check Feasibility**: If `m * k > bloomDay.length`, return `-1`.
2. **Initialize Variables**: Set `minDay` to the minimum value in `bloomDay` and `maxDay` to the maximum value in `bloomDay`.
3. **Binary Search**: While `minDay <= maxDay`:
   - Calculate `midDay` as `minDay + (maxDay - minDay) / 2`.
   - Check if it's possible to make `m` bouquets by `midDay` using the `canMake` function.
   - If it's possible, update the answer to `midDay` and set `maxDay` to `midDay - 1`.
   - If it's not possible, set `minDay` to `midDay + 1`.
4. **Return Answer**: Return the answer.

---

## 🔹 Code

```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }

        int answer = -1;

        while (minDay <= maxDay) {
            int midDay = minDay + (maxDay - minDay) / 2;

            if (canMake(bloomDay, m, k, midDay)) {
                answer = midDay;
                maxDay = midDay - 1;
            } else {
                minDay = midDay + 1;
            }
        }

        return answer;
    }

    private boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int count = 0;

        for (int bloom : bloomDay) {
            if (bloom <= day) {
                count++;
                if (count == k) {
                    bouquets++;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        return bouquets >= m;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `bloomDay = [1, 10, 3, 10, 2]`, `m = 3`, and `k = 1`.

| Iteration | minDay | maxDay | midDay | canMake(midDay) | Action |
|-----------|--------|--------|--------|------------------|--------|
| 1         | 1      | 10     | 5      | true             | answer = 5, maxDay = 4 |
| 2         | 1      | 4      | 2      | true             | answer = 2, maxDay = 1 |
| 3         | 1      | 1      | 1      | true             | answer = 1, maxDay = 0 |

The minimum number of days required is `1`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log(maxDay - minDay)) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Impossible to Make Bouquets**: `m * k > bloomDay.length` should return `-1`.
- **Single Flower**: `bloomDay = [1]`, `m = 1`, `k = 1` should return `1`.
- **All Flowers Bloom on the Same Day**: `bloomDay = [1, 1, 1, 1, 1]`, `m = 3`, `k = 1` should return `1`.
- **Large Input**: `bloomDay` with `10^5` elements, `m = 10^6`, `k = 10^6` should handle efficiently.

---

# 📚 Key Takeaways

- **Binary Search**: Binary search is an efficient way to solve problems with a large search space.
- **Feasibility Check**: Always check the feasibility of the problem constraints before proceeding.
- **Efficiency**: The optimal approach reduces the time complexity significantly compared to the brute force approach.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss the time and space complexity of the solution.
- **Alternative Approaches**: Mention other approaches like using a sliding window or dynamic programming.
- **Optimization**: Highlight the importance of binary search for large input sizes.

---

# ✅ Conclusion

The optimal approach using binary search is the most efficient way to solve this problem. It reduces the time complexity from O(n * (maxDay - minDay)) to O(n log(maxDay - minDay)), making it suitable for large input sizes. The key insight is to use binary search to efficiently determine the minimum number of days required to make `m` bouquets.