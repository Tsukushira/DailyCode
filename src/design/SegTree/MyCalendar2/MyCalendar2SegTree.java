package design.SegTree.MyCalendar2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 731. 我的日程安排表 II
 * 实现一个 MyCalendar 类来存放日程安排。
 * 如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar() : 初始化
 * book(int start, int end) : 如果可以将日程 [start, end)（半开区间）
 * 成功安排添加到日历中而不会导致三重预订，返回true。否则，返回false，并且不要将该日程安排添加到日历中。
 */
public class MyCalendar2SegTree {
    MyCalendar2SegTree() {
    }

    public boolean book(int start, int end) {
        if (query(root, 0, N, start, end - 1) >= 2) {
            return false;
        }
        update(root, 0, N, start, end - 1, 1);
        return true;
    }

    static class SegTree {
        int val, add;
        SegTree left, right;
    }

    private static final int N = (int) 1e2;
    private final SegTree root = new SegTree();

    public List<List<Integer>> traverse() {
        List<List<Integer>> res = new ArrayList<>();
        Queue<SegTree> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                SegTree node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * @param root      当前节点
     * @param satrt,end 当前节点root管辖范围
     * @param L,R       L ~ R 范围都+val
     * @param val
     */
    private void update(SegTree root, int satrt, int end, int L, int R, int val) {
        if (L <= satrt && end <= R) {// 当前节点范围在更新范围内
            root.val += val;
            root.add += val;
            System.out.println(satrt + "~" + end + " " + root.add);
            return;
        }
        pushDown(root);
        int mid = (satrt + end) >> 1;
        if (L <= mid) {
            update(root.left, satrt, mid, L, R, val);
        }
        if (R > mid) {
            update(root.right, mid + 1, end, L, R, val);
        }
        pushUp(root);
    }

    private void pushDown(SegTree root) {
        if (root.left == null) {
            root.left = new SegTree();
        }
        if (root.right == null) {
            root.right = new SegTree();
        }
        if (root.add == 0) {
            return;
        }
        root.left.val += root.add;
        root.right.val += root.add;
        root.left.add += root.add;
        root.right.add += root.add;
        root.add = 0;
    }

    private void pushUp(SegTree root) {
        root.val = Math.max(root.left.val, root.right.val);
    }

    /**
     * @param root
     * @param satrt,end 当前节点 root 的管辖范围
     * @param L,R       查询 L ~ R 范围上的最大值
     * @return
     */
    private int query(SegTree root, int satrt, int end, int L, int R) {
        if (L <= satrt && end <= R) {
            return root.val;
        }
        pushDown(root);
        int mid = (satrt + end) >> 1;
        int res1 = 0, res2 = 0;
        if (L <= mid) {
            res1 = query(root.left, satrt, mid, L, R);
        }
        if (R > mid) {
            res2 = query(root.right, mid + 1, end, L, R);
        }
        return Math.max(res1, res2);
    }
}

