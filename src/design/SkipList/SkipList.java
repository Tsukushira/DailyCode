package design.SkipList;

import java.util.Arrays;
import java.util.Random;

/**
 * 1206. 设计跳表
 * <p>
 * 设计应该要包含这些函数：
 * boolean search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入元素num到跳表。
 * boolean erase(int num): 在跳表中删除一个值，如果num不存在，返回false，如果存在多个num，删除其中任意一个即可。
 */
public class SkipList {
    static class Node {
        int val;
        Node[] next;

        Node(int val, int size) {
            this.val = val;
            next = new Node[size];
        }
    }

    private final int maxLevel = 32;// 最大32层数
    private final double p;// 第 i 层的以 p 的概率出现在第 i+1 层
    private final Node head;
    private int level;// 当前的层数
    private final Random random;

    SkipList() {
        p = 0.25;
        head = new Node(-1, maxLevel);
        level = 1;
        random = new Random();
    }

    /**
     * 随机生成level
     *
     * @return 返回随机生成的level。
     */
    private int randomLevel() {
        int level = 1;
        while (random.nextDouble() < p && level < maxLevel) {
            level++;
        }
        return level;
    }

    /**
     * 搜索
     *
     * @param target
     * @return 返回target是否存在于跳表中。
     */
    public boolean search(int target) {
        Node curr = head;
        for (int i = level - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].val < target) {
                curr = curr.next[i];
            }
        }
        curr = curr.next[0];
        return curr != null && curr.val == target;
    }

    /**
     * 插入
     *
     * @param num 插入num到跳表中。
     */
    public void add(int num) {
        Node[] update = new Node[maxLevel];
        Arrays.fill(update, head);
        Node curr = head;
        for (int i = level - 1; i >= 0; i--) {
            // 找到第 i 层最接近 num 的节点
            while (curr.next[i] != null && curr.next[i].val < num) {
                curr = curr.next[i];
            }
            update[i] = curr;
        }
        int lv = randomLevel();
        level = Math.max(level, lv);
        Node node = new Node(num, level);
        for (int i = 0; i < lv; i++) {// 插入元素
            node.next[i] = update[i].next[i];
            update[i].next[i] = node;
        }

    }

    /**
     * 删除
     *
     * @param num
     * @return 如果num不存在，返回false；如果存在多个num，删除其中任意一个即可，返回true。
     */
    public boolean erase(int num) {
        Node[] update = new Node[level];
        Node curr = head;
        for (int i = level - 1; i >= 0; i--) {
            // 找到第 i 层最接近 num 的节点
            while (curr.next[i] != null && curr.next[i].val < num) {
                curr = curr.next[i];
            }
            update[i] = curr;
        }
        curr = curr.next[0];
        if (curr == null || curr.val != num) {// 值不存在
            return false;
        }
        for (int i = 0; i < level; i++) {
            if (update[i].next[i] != curr) {
                break;
            }
            update[i].next[i] = curr.next[i];// 删除节点
        }
        // 更新level
        while (level > 1 && head.next[level - 1] == null) {
            level--;
        }
        return true;
    }

}
