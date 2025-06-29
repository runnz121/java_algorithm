package algorithmKotlin.leetCode;

import java.util.*;

public class SameTree {

    public static class TreeNode {
        int val;
        int val2;
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 두 노드 모두 null이면 같은 트리
        if (p == null && q == null) return true;

        // 한 노드만 null이거나, 값이 다르면 다른 트리
        if (p == null || q == null || p.val != q.val) return false;

        // 왼쪽과 오른쪽 서브트리를 각각 재귀적으로 비교
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);


    }

    public static void main(String[] args) {
        int[] hi = new int[]{1,2,3};
        Integer[] reverse = Arrays.stream(hi).boxed().toArray(Integer[]::new);
        Arrays.sort(reverse, (a, b) -> b - a);
        System.out.println(Arrays.toString(reverse));

        List<TreeNode> trees = new ArrayList<>();
        trees.sort(Comparator.comparing((TreeNode t) -> t.val).reversed());

        Collections.sort(trees, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode a, TreeNode b) {
                int cmp = Integer.compare(a.val, a.val2);
                if (cmp != 0) {
                    return cmp;
                }
                return Integer.compare(a.val2, b.val2);
            }
        });
    }
}
