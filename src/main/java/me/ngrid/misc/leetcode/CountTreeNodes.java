package me.ngrid.misc.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly
 * the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1
 * and 2h nodes inclusive at the last level h.
 *
 * @see <a href="https://leetcode.com/problems/count-complete-tree-nodes/">l33tc0d3</a>
 */
public class CountTreeNodes {
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;

        TreeNode deepest = root.left;
        int count = 0;
        while(deepest != null) {
            deepest = deepest.left;
            count++;
        }

        return (int)Math.pow(2, count) - 1 + dfs(root, count);

    }
    private class Node {
        int level;
        TreeNode node;
        Node(int level, TreeNode node){
            this.node = node;
            this.level = level;
        }
        public int level() {
            return level;
        }
        public TreeNode get() {
            return node;
        }
    }

    private int dfs(TreeNode root, int deepest) {
        Deque<Node> buf = new ArrayDeque<>();
        if(root == null)
            return 0;

        int count = 0;
        buf.push(new Node(0, root));
        Node tmp;

        while(!buf.isEmpty()) {
            tmp = buf.pop();
            if(tmp.get().right != null)
                buf.push(new Node(tmp.level() + 1, tmp.get().right));

            if(tmp.get().left != null) {
                buf.push(new Node(tmp.level() + 1, tmp.get().left));
            }
            //congrats we reached a leaf.
            // count up and level down
            else {
                if(tmp.level() == deepest) {
                    count ++;
                }
                // if the level is not deepest and we got no more lefties
                // its over
                else {
                    break;
                }
            }
        }
        return count;
    }
}
