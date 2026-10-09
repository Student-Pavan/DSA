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
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            int leftheight = height(curr.left);
            int rightheight = height(curr.right);

            if(Math.abs(leftheight - rightheight) > 1)
                return false;

            if(curr.left != null){
                q.offer(curr.left);
            }
            if(curr.right != null ){
                q.offer(curr.right);
            }
            
        }
        return true;

    }


    private int height(TreeNode root){
        if(root == null){
            return 0;
        }


        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int height = 0;

        while(!q.isEmpty()){
            
            int size = q.size();

            while(size-- > 0){
                TreeNode node  = q.poll();

                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            height++;

        }

        return height;
    }
}