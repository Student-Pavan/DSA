# Minimum Speed to Arrive on Time

---

# 📝 Problem Statement

You are given a floating-point number `hour`, representing the amount of time you have to reach the office. To commute to the office, you must take `n` trains in sequential order. You are also given an integer array `dist` of length `n`, where `dist[i]` describes the distance (in kilometers) of the `i-th` train ride.

Each train can only depart at an integer time, meaning you must wait in line to board the next train. More specifically, you can only take a new train at an integer hour. So, the `i-th` train ride takes:

- `ceil(dist[i] / speed)` time to complete, if you must wait from the arrival to its departure.

However, there is an important exception: **time waiting in line for a train ride at the departure does not count as part of the total time**. In other words, suppose at time `t` you arrive at the `i-th` train station, and the `i-th` train departs at time `j` where `j > t`. Then you can take the `i-th` train at any time `x` where `t <= x < j`. You do not have to wait for a train if you arrive on exact departure time.

Your task is to determine the **minimum positive integer speed** (in kilometers per hour) that all the trains must travel at for you to reach the office on time, or return `-1` if it is impossible to be on time.

**Constraints:**
- `n == dist.length`
- `1 <= n <= 10^5`
- `1 <= dist[i] <= 10^5`
- `1 <= hour <= 10^9`
- There will be at most two digits after the decimal point in `hour`.

---

# 💡 Intuition

The key insight here is recognizing that we need to find the minimum speed that allows us to arrive on time, considering the constraints of train departures. The problem can be approached using binary search because the speed is a continuous variable that can be searched efficiently within a range.

The main challenge is calculating the total time taken for a given speed, considering the ceiling function for all but the last segment and the exact division for the last segment. This requires careful handling of the time calculation to ensure accuracy.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking each possible speed from 1 to a maximum possible speed (like 10^7) to find the minimum speed that satisfies the time constraint. For each speed, we calculate the total time taken to travel all segments and check if it's within the given hour.

## 🔹 Algorithm

1. Initialize `min_speed` to a very large value.
2. Iterate through each possible speed from 1 to 10^7.
3. For each speed, calculate the total time taken to travel all segments.
4. If the total time is less than or equal to the given hour, update `min_speed` if the current speed is smaller.
5. After checking all speeds, return `min_speed` if found, otherwise return -1.

## 🔹 Code

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int minSpeed = Integer.MAX_VALUE;

        for (int speed = 1; speed <= 10000000; speed++) {
            double totalTime = 0;

            for (int i = 0; i < dist.length - 1; i++) {
                totalTime += Math.ceil((double) dist[i] / speed);
            }

            totalTime += (double) dist[dist.length - 1] / speed;

            if (totalTime <= hour) {
                minSpeed = Math.min(minSpeed, speed);
            }
        }

        return minSpeed == Integer.MAX_VALUE ? -1 : minSpeed;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `dist = [1, 3, 2]` and `hour = 6.0`.

| Speed | Total Time Calculation | Check | Action |
|---|---|---|---|
| 1 | ceil(1/1) + ceil(3/1) + 2/1 = 1 + 3 + 2 = 6 | 6 <= 6 | minSpeed = 1 |
| 2 | ceil(1/2) + ceil(3/2) + 2/2 = 1 + 2 + 1 = 4 | 4 <= 6 | minSpeed = 2 |
| 3 | ceil(1/3) + ceil(3/3) + 2/3 ≈ 1 + 1 + 0.666 ≈ 2.666 | 2.666 <= 6 | minSpeed = 3 |
| ... | ... | ... | ... |
| 6 | ceil(1/6) + ceil(3/6) + 2/6 ≈ 1 + 1 + 0.333 ≈ 2.333 | 2.333 <= 6 | minSpeed = 6 |

The minimum speed found is 1.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(10^7 * n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using binary search to efficiently find the minimum speed. The idea is to search for the minimum speed within a range (1 to 10^7) that satisfies the time constraint. For each mid value in the binary search, we check if it's possible to arrive on time with that speed. If it is, we search for a smaller speed; otherwise, we search for a larger speed.

## 🔹 Why This Works

Binary search is efficient for searching in a sorted range, and it reduces the time complexity from O(10^7 * n) to O(n log 10^7). The key insight is that the time taken to travel the segments is a monotonically decreasing function of speed, allowing us to use binary search to find the minimum speed.

## 🔹 Algorithm

1. Initialize `low` to 1 and `high` to 10^7.
2. Initialize `ans` to -1.
3. While `low` is less than or equal to `high`:
   - Calculate `mid` as the average of `low` and `high`.
   - Check if it's possible to arrive on time with speed `mid`.
   - If it is, update `ans` to `mid` and search for a smaller speed by setting `high` to `mid - 1`.
   - Otherwise, search for a larger speed by setting `low` to `mid + 1`.
4. Return `ans`.

## 🔹 Code

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int low = 1;
        int high = 10000000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canArrive(dist, hour, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canArrive(int[] dist, double hour, int speed) {
        double totalTime = 0;

        for (int i = 0; i < dist.length - 1; i++) {
            totalTime += Math.ceil((double) dist[i] / speed);
        }

        totalTime += (double) dist[dist.length - 1] / speed;

        return totalTime <= hour;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `dist = [1, 3, 2]` and `hour = 6.0`.

| Iteration | Low | High | Mid | Total Time Calculation | Check | Action |
|---|---|---|---|---|---|---|
| 1 | 1 | 10000000 | 5000000 | ceil(1/5000000) + ceil(3/5000000) + 2/5000000 ≈ 1 + 1 + 0.0000004 ≈ 2.0000004 | 2.0000004 <= 6 | ans = 5000000, high = 4999999 |
| 2 | 1 | 4999999 | 2500000 | ceil(1/2500000) + ceil(3/2500000) + 2/2500000 ≈ 1 + 1 + 0.0000008 ≈ 2.0000008 | 2.0000008 <= 6 | ans = 2500000, high = 2499999 |
| ... | ... | ... | ... | ... | ... | ... |
| 10 | 1 | 3 | 2 | ceil(1/2) + ceil(3/2) + 2/2 = 1 + 2 + 1 = 4 | 4 <= 6 | ans = 2, high = 1 |
| 11 | 1 | 1 | 1 | ceil(1/1) + ceil(3/1) + 2/1 = 1 + 3 + 2 = 6 | 6 <= 6 | ans = 1, high = 0 |

The minimum speed found is 1.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log 10^7) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Segment**: `dist = [5]`, `hour = 1.0` → speed = 5.
- **Multiple Segments**: `dist = [1, 3, 2]`, `hour = 6.0` → speed = 1.
- **Impossible to Arrive**: `dist = [1, 3, 2]`, `hour = 2.9` → speed = -1.
- **Large Constraints**: `dist = [10^5, 10^5, ..., 10^5]` (n = 10^5), `hour = 10^9` → efficient handling required.

---

# 📚 Key Takeaways

- **Binary Search**: Efficiently narrow down the search space for the minimum speed.
- **Time Calculation**: Carefully handle the ceiling function for all but the last segment and exact division for the last segment.
- **Edge Cases**: Consider scenarios where it's impossible to arrive on time.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle very large constraints efficiently.
- **Alternative Approaches**: Consider using ternary search for further optimization.
- **Optimization Discussions**: Explain why binary search is preferred over brute force.

---

# ✅ Conclusion

The optimal approach using binary search is significantly more efficient than the brute force approach, especially for large input sizes. The key insight is recognizing the monotonic nature of the time calculation with respect to speed, which allows for efficient searching. The solution efficiently handles the constraints and edge cases, providing an optimal and correct result.