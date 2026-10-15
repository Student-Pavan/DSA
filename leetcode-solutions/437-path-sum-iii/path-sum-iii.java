class Solution {
    int pathcounts = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        countFromNode(root, targetSum);

        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);

        return pathcounts;
    }

    private void countFromNode(TreeNode root, long target) {
        if (root == null) return;

        target -= root.val;

       
        if (target == 0) {
            pathcounts++;
        }

     
        countFromNode(root.left, target);
        countFromNode(root.right, target);
    }
}