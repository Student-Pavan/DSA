/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    HashMap<TreeNode, TreeNode> parent = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildParent(root,null);

        Queue<TreeNode> q = new LinkedList<>();
        HashSet<TreeNode> vis = new HashSet<>();

        q.offer(target);
        vis.add(target);
        int level = 0;
        while(!q.isEmpty()){
            if(level == k) break;

            int size = q.size();

            while(size-- > 0){
                TreeNode curr = q.poll();

                if(curr.left!= null && vis.add(curr.left))
                    q.offer(curr.left);
                if(curr.right!= null && vis.add(curr.right))
                    q.offer(curr.right);
                if(parent.get(curr) != null && vis.add(parent.get(curr)))
                    q.offer(parent.get(curr));
                
            }
            level++;
        }

        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty())
            ans.add(q.poll().val);

        return ans;
    }

    private void buildParent(TreeNode root, TreeNode par) {
        if (root == null)
            return;

        parent.put(root, par);
        buildParent(root.left, root);
        buildParent(root.right, root);
    }
}