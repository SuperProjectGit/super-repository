package com.common.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BFS(广度优先遍历输出二叉树每一层)
 *
 * @author super
 * @create 2019-11-17 10:11
 **/
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; i++) {
                TreeNode temp = queue.remove();
                list.add(temp.val);
                if (null != temp.left) {
                    queue.add(temp.left);
                }
                if (null != temp.right) {
                    queue.add(temp.right);
                }
            }
            result.add(list);
        }
        return result;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
