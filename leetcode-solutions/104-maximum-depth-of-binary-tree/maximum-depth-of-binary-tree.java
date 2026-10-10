/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return maxdepth(root);
    }

    private int maxdepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftheight = maxdepth(root.left);
        int rightheight = maxdepth(root.right);

        return Math.max(leftheight, rightheight) + 1;
    }

}