import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Intuition:
 * <p>
 * Traverse Root -> Right --> Left,
 * <p>
 * Consider the first node per level as the Right view node for the current level.
 * <p>
 * Check before inserting to the result list.
 * <p>
 * Can be done by both DFS and BFS
 * <p>
 * NOTE: Can be done via Root -> Left -> Right
 * Just keep on updating the value in the list at level == index
 */

// LC 199
public class RightViewBinaryTree {

    /**
     * Root - Right - Left
     * <p>
     * TC: O(n)
     * SC; O(H), in the worst case == skewed tree, h == n
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView_dfs_rootRL(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs_rootRL(root, res, 0);
        return res;
    }

    private void dfs_rootRL(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(root.val);
        }
        dfs_rootRL(root.right, res, level + 1);
        dfs_rootRL(root.left, res, level + 1);
    }

    /**
     * Root - Right - Left
     * <p>
     * TC: O(n)
     * SC: O(leaf nodes)
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView_bfs_rootRL(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (res.size() == level) {
                    res.add(curr.val);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
            }
            level++;
        }
        return res;
    }

    /**
     * Root - Left - Right
     * <p>
     * TC: O(n)
     * SC; O(H), in the worst case == skewed tree, h == n
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView_dfs_rootLR(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs_rootLR(root, res, 0);
        return res;
    }

    private void dfs_rootLR(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(root.val);
        } else {
            res.set(level, root.val);
        }
        dfs_rootLR(root.left, res, level + 1);
        dfs_rootLR(root.right, res, level + 1);
    }

    /**
     * Root - Left - Right
     * <p>
     * TC: O(n)
     * SC: O(leaf nodes)
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView_bfs_rootLR(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (res.size() == level) {
                    res.add(curr.val);
                } else {
                    res.set(level, curr.val);
                }
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            level++;
        }
        return res;
    }
}

class TreeNode {
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
