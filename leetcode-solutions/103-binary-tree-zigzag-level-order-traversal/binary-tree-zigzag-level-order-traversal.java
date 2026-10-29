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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);

        boolean lefttoRight = true;

        while (!dq.isEmpty()) {
            int size = dq.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (lefttoRight) {
                    TreeNode curr = dq.pollFirst();
                    level.add(curr.val);

                    if (curr.left != null)
                        dq.offerLast(curr.left);
                    if (curr.right != null)
                        dq.offerLast(curr.right);

                } else {
                    TreeNode curr = dq.pollLast();
                    level.add(curr.val);

                    if (curr.right != null)
                        dq.offerFirst(curr.right);
                    if (curr.left != null)
                        dq.offerFirst(curr.left);
                }
            }

            ans.add(level);
            lefttoRight = !lefttoRight;
        }

        return ans;
    }
}
