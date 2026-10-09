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
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        countDepth(root);
        return diameter;
    }

    private int countDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = countDepth(root.left);
        int rightHeight = countDepth(root.right);
        diameter = Math.max(leftHeight + rightHeight, diameter);

        return Math.max(leftHeight,rightHeight) + 1;
    }
}