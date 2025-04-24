package algorithm.leetCode;

public class MaximumDepthOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int answer = 0;
    public int maxDepth(TreeNode root) {

        recursvie(root, 1);
        return answer;
    }

    void recursvie(TreeNode node, int depth) {

        if (node == null) {
            return;
        }
        answer = Math.max(answer, depth);
        recursvie(node.left, depth + 1);
        recursvie(node.right, depth + 1);
    }
}
