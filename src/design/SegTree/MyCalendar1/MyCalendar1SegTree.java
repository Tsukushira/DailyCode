package design.SegTree.MyCalendar1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 729. 我的日程安排表 I
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数x 的范围为， start <= x < end。
 * <p>
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 */
public class MyCalendar1SegTree {
    private static final int N = (int) 1e2; //日程安排的最大值
    private final SegTree root = new SegTree();

    MyCalendar1SegTree() {
    }

    public boolean book(int start, int end) {
        int q = query(root, 0, N, start, end - 1);
        if (q != 0) {
            System.out.println(q);
            return false;
        }
        update(root, 0, N, start, end - 1, 1);
        return true;
    }

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
     * 线段树查询
     *
     * @param node      当前节点
     * @param start,end 当前节点管辖的区间范围
     * @param L,R       查找的区间范围
     * @return
     */
    private int query(SegTree node, int start, int end, int L, int R) {
        if (L <= start && end <= R) {
            return node.val;
        }
        pushDown(node);
        int mid = (end + start) >> 1;
        int res1 = 0, res2 = 0;
        if (L <= mid) {
            res1 = query(node.left, start, mid, L, R);
        }
        if (R > mid) {
            res2 = query(node.right, mid + 1, end, L, R);
        }
        return res1 + res2;
        //return Math.max(res1, res2);
    }

    /**
     * 更新线段树
     *
     * @param node      当前节点
     * @param start,end 当前节点管辖的区间范围
     * @param L,R       需要更新的区间范围
     * @param val       更新的值
     * @return
     */
    private void update(SegTree node, int start, int end, int L, int R, int val) {
        if (L <= start && end <= R) { // 当前节点范围在更新范围内
            node.val += val;
            node.add += val;
            return;
        }
        pushDown(node);
        int mid = (end + start) >> 1;
        if (L <= mid) {
            update(node.left, start, mid, L, R, val);
        }
        if (R > mid) {
            update(node.right, mid + 1, end, L, R, val);
        }
        pushUp(node);
    }

    /**
     * 懒标记下移
     * 1.创建子节点
     * 2.将标记推给子节点，更新子节点值
     * 3.消除当前节点懒标记
     *
     * @param node 当前节点
     */
    private void pushDown(SegTree node) {
        if (node.left == null) {
            node.left = new SegTree();
        }
        if (node.right == null) {
            node.right = new SegTree();
        }
        if (node.add == 0) {
            return;
        }
        node.left.val += node.add;
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }

    /**
     * 将子节点懒标记更新结果上推
     *
     * @param node
     */
    private void pushUp(SegTree node) {
        node.val = node.left.val + node.right.val;
        //node.val = Math.max(node.left.val, node.right.val);
    }
}

/**
 * 线段树，动态开点
 */
class SegTree {
    SegTree left, right;
    int val;// 区间内已book的日程数量
    int add;// 懒标记
}