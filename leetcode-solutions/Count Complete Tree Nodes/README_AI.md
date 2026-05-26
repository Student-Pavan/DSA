# 📌 Count Complete Tree Nodes

---

# 📝 Problem Statement

Given the `root` of a **complete binary tree**, return the number of nodes in the tree.

A **complete binary tree** is a binary tree in which every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.

### **Constraints:**
- The number of nodes in the tree is in the range `[0, 5 * 10^4]`.
- `0 <= Node.val <= 5 * 10^4`
- The tree is guaranteed to be **complete**.

### **Example:**
**Input:**
```
    1
   / \
  2   3
 / \  /
4  5 6
```
**Output:** `6`

---

# 💡 Intuition

The key insight lies in recognizing the structural properties of a **complete binary tree**. Unlike a general binary tree, a complete binary tree has predictable filling patterns, especially in the last level.

- **Brute Force:** Traverse every node recursively or iteratively (DFS/BFS) and count nodes. This works for any binary tree but doesn't leverage the "complete" property.
- **Optimal Approach:** Exploit the complete tree structure. By checking the height of the leftmost and rightmost paths, we can determine if the tree is **perfect** (fully filled) or not. If it is, we can compute the node count directly. If not, we recursively count nodes in the left and right subtrees.

This reduces the problem from O(n) to O(log n * log n) time.

---

# 🐌 Brute Force Approach

## 🔹 Approach

We use a standard **Depth-First Search (DFS)** traversal to visit every node in the tree. At each node, we increment a counter and recursively visit the left and right children.

This approach treats the tree as a general binary tree and does not take advantage of the "complete" property.

---

## 🔹 Algorithm

1. If the `root` is `null`, return 0.
2. Initialize a counter to 0.
3. Define a helper function that:
   - Increments the counter.
   - Recursively visits the left subtree.
   - Recursively visits the right subtree.
4. Call the helper on the root.
5. Return the counter.

---

## 🔹 Code

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int[] count = {0};
        dfs(root, count);
        return count[0];
    }

    private void dfs(TreeNode node, int[] count) {
        if (node == null) return;
        count[0]++;
        dfs(node.left, count);
        dfs(node.right, count);
    }
}
```

> **Note:** We use an array `int[] count` to simulate pass-by-reference since Java is pass-by-value.

---

## 🔹 Dry Run

**Tree:**
```
      1
     / \
    2   3
   / \  /
  4  5 6
```

| Step | Node Visited | Action | Count |
|------|--------------|--------|-------|
| 1    | 1            | Increment count | 1 |
| 2    | 2            | Increment count | 2 |
| 3    | 4            | Increment count | 3 |
| 4    | 4 → null     | Return | 3 |
| 5    | 4 → null     | Return | 3 |
| 6    | 2 → 5        | Increment count | 4 |
| 7    | 5 → null     | Return | 4 |
| 8    | 5 → null     | Return | 4 |
| 9    | 2 → null     | Return | 4 |
| 10   | 1 → 3        | Increment count | 5 |
| 11   | 3 → 6        | Increment count | 6 |
| 12   | 6 → null     | Return | 6 |
| 13   | 6 → null     | Return | 6 |
| 14   | 3 → null     | Return | 6 |

**Final Count:** `6`

---

## 🔹 Complexity Analysis

| Complexity       | Value       |
|------------------|-------------|
| Time Complexity  | O(n)        |
| Space Complexity | O(h) = O(n) in worst case (skewed tree), but O(log n) for balanced |

> **Note:** In a complete tree, height `h = log n`, so space is O(log n).

---

# ⚡ Optimal Approach

## 🔹 Approach

We leverage the **complete binary tree property** to avoid traversing every node.

1. Compute the **left height** (leftmost path) and **right height** (rightmost path).
2. If they are equal, the tree is **perfect**, and the number of nodes is `2^h - 1`.
3. If not, recursively count nodes in the left and right subtrees and add 1 for the root.

This reduces the problem size logarithmically at each step.

---

## 🔹 Why This Works

- A **perfect binary tree** of height `h` has `2^h - 1` nodes.
- In a complete tree, the last level may not be full, but the left and right subtrees are **complete**.
- By comparing left and right heights, we can determine if a subtree is perfect without traversing all nodes.

This leads to **O(log n * log n)** time because:
- Each recursive call takes O(log n) to compute heights.
- We make O(log n) recursive calls (depth of recursion).

---

## 🔹 Algorithm

1. If `root == null`, return 0.
2. Compute `leftHeight` by traversing left until `null`.
3. Compute `rightHeight` by traversing right until `null`.
4. If `leftHeight == rightHeight`, return `2^leftHeight - 1`.
5. Otherwise, return `1 + countNodes(root.left) + countNodes(root.right)`.

---

## 🔹 Code

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^h - 1
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}
```

---

## 🔹 Detailed Dry Run

**Tree:**
```
      1
     / \
    2   3
   / \  /
  4  5 6
```

| Step | Node | Left Height | Right Height | Action | Return Value |
|------|------|-------------|--------------|--------|--------------|
| 1    | 1    | 3           | 3            | `left == right` → perfect | `2^3 - 1 = 7` ❌ Wait! But tree has only 6 nodes. |

> **Wait!** This indicates a flaw in the dry run. Let's re-examine.

**Correction:** The tree is **not perfect** — node 3 has no right child.

Let’s recompute:

- `leftHeight(1)`: 1 → 2 → 4 → `null` → 3
- `rightHeight(1)`: 1 → 3 → 6 → `null` → 3
- `3 == 3` → seems perfect, but it's not.

**Issue:** The `rightHeight` is misleading. We need to check **only the leftmost and rightmost paths from root**, not full height.

But in a complete tree, if left and right heights are equal, **the tree is perfect**.

But in our example, the tree is **not perfect**, yet `leftHeight == rightHeight`.

**Why?** Because the **last level is not full**, but the **rightmost path still has 3 nodes**.

This is a **limitation of the height comparison** — it only works if the **last level is filled from left to right**, which it is. But the tree is still **not perfect** if the last level is incomplete.

Wait — **the algorithm is correct**. The condition `leftHeight == rightHeight` **does** imply the tree is perfect.

But in our example, the tree is **not perfect**, yet `leftHeight == rightHeight`.

**Resolution:** The tree **is not perfect**, but `leftHeight == rightHeight` only when the **entire tree is perfect**.

In our example:
- `leftHeight = 3` (1→2→4)
- `rightHeight = 3` (1→3→6)

But the tree is **not perfect** because node 3 has no right child.

**Conclusion:** The algorithm **is correct**. The condition `leftHeight == rightHeight` **does** mean the tree is perfect.

But in our example, the tree **is complete but not perfect**, yet `leftHeight == rightHeight`.

**This is impossible** unless the tree is **perfect**.

**Re-examining the tree:**

```
      1
     / \
    2   3
   / \  /
  4  5 6
```

- Level 0: 1 → 1 node
- Level 1: 2, 3 → 2 nodes
- Level 2: 4,5,6 → 3 nodes

Total: 6 nodes.

Is it perfect? No — level 2 should have 4 nodes (2^2), but has only 3.

But `leftHeight = 3`, `rightHeight = 3`.

**Why?** Because the **rightmost path** is 1→3→6, which is length 3.

But the **last level is not full**, so the tree is **not perfect**.

**Contradiction?**

No — the algorithm is correct. The condition `leftHeight == rightHeight` **only holds when the tree is perfect**.

In our example, `leftHeight == rightHeight` **should not hold** if the tree is not perfect.

But it does.

**Root Cause:** The tree **is complete**, but **not perfect**, yet `leftHeight == rightHeight`.

This implies the algorithm **would incorrectly return 7**, but the correct answer is 6.

**This suggests a bug in the algorithm?**

No — the algorithm is correct. The **error is in the example interpretation**.

Let’s compute `rightHeight` again:

- Start at 1: height = 1
- Go right to 3: height = 2
- Go right to `null` (3 has no right child): height = 2

So `rightHeight = 2`, not 3.

**Correction:**

- `leftHeight(1)`: 1 → 2 → 4 → 3
- `rightHeight(1)`: 1 → 3 → `null` → 2

Now `3 != 2`, so we recurse.

**Correct Dry Run:**

| Step | Node | Left Height | Right Height | Action | Return Value |
|------|------|-------------|--------------|--------|--------------|
| 1    | 1    | 3           | 2            | `3 != 2` → recurse | `1 + countNodes(2) + countNodes(3)` |
| 2    | 2    | 2 (2→4)     | 2 (2→5)      | `2 == 2` → perfect | `2^2 - 1 = 3` |
| 3    | 3    | 2 (3→6)     | 1 (3→null)   | `2 != 1` → recurse | `1 + countNodes(6) + countNodes(null)` |
| 4    | 6    | 1 (6→null)  | 1 (6→null)   | `1 == 1` → perfect | `2^1 - 1 = 1` |
| 5    | null | -           | -            | base case | 0 |

Now compute:
- `countNodes(3) = 1 + 1 + 0 = 2`
- `countNodes(2) = 3`
- `countNodes(1) = 1 + 3 + 2 = 6`

**Final Count:** `6` ✅

---

## 🔹 Complexity Analysis

| Complexity       | Value               |
|------------------|---------------------|
| Time Complexity  | O(log² n)           |
| Space Complexity | O(log n) (recursion stack) |

> **Explanation:**
> - Each recursive call does O(log n) work (computing heights).
> - There are O(log n) recursive calls (depth of recursion).
> - Total: O(log n * log n) = O(log² n).

---

# 🔍 Edge Cases

| Case | Description | Expected Output |
|------|-------------|-----------------|
| Empty tree | `root = null` | 0 |
| Single node | `root = [1]` | 1 |
| Perfect tree | All levels full | `2^h - 1` |
| Complete but not perfect | Last level not full | `n` (actual count) |
| Left-heavy | Only left children | `h` (height) |
| Large tree | 50,000 nodes | 50,000 |

---

# 📚 Key Takeaways

- **Complete binary trees** have predictable structure — exploit it.
- **Height comparison** is a powerful tool to avoid full traversal.
- **Perfect trees** allow O(1) node count via `2^h - 1`.
- **Recursive decomposition** reduces problem size exponentially.
- Time complexity improves from O(n) → O(log² n) by leveraging structure.

---

# 🚀 Interview Tips

- **Clarify:** Confirm the tree is **complete** — this is critical.
- **Follow-up:** What if the tree is not complete? → Brute force is needed.
- **Optimization Insight:** Always ask: "Can we use structure to avoid traversal?"
- **Common Pitfall:** Miscomputing height — ensure you traverse only left or only right.
- **Alternative:** BFS/DFS are acceptable if constraints are small, but optimal is expected for large trees.

---

# ✅ Conclusion

The **optimal solution** leverages the **complete binary tree property** to avoid traversing every node, achieving **O(log² n)** time. This is a classic example of **algorithmic optimization through structural insight**.

While the brute force approach is simple and correct, the optimal solution demonstrates **advanced problem-solving** and is preferred in interviews, especially for large input sizes.

**Key Insight:** In complete trees, **height comparison** can determine perfection without full traversal — a powerful pattern in tree problems.