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
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        int left = leftheight(root);
        int right = rightheight(root);

        if(left == right)
            return (1 << left )-1;

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    private int leftheight(TreeNode root){
        int height = 0;

        while(root != null){
            height++;
            root = root.left;
        }
        return height;
    }
    private int rightheight(TreeNode root){
        int height = 0;

        while(root != null){
            height++;
            root = root.right;
        }
        return height;
    }
}