package algorithmKotlin.leetCode;

import java.util.ArrayList;
import java.util.List;

public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 두 노드 모두 null이면 같은 트리
        if (p == null && q == null) return true;

        // 한 노드만 null이거나, 값이 다르면 다른 트리
        if (p == null || q == null || p.val != q.val) return false;

        // 왼쪽과 오른쪽 서브트리를 각각 재귀적으로 비교
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
