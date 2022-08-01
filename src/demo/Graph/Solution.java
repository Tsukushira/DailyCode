package demo.Graph;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[][] sequences = {{1, 2}, {1, 3}};
        System.out.println(sequenceReconstruction(nums, sequences));

        sequences = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println(sequenceReconstruction(nums, sequences));
    }

    /**
     * 剑指 Offer II 115. 重建序列
     * 检查 nums 是否是唯一的最短超序列。并且所有序列sequences[i]都是它的子序列。
     * 子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
     * 对于给定的数组sequences，可能存在多个有效的超序列:
     * 例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
     * 对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。
     *
     * @param nums      长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。
     * @param sequences sequences[i] 是 nums 的子序列。
     * @return 检查 nums 是否是唯一的最短超序列。
     */
    public static boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        List<Set<Integer>> graph = new ArrayList<>();
        int[] indegrees = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] seq : sequences) {
            for (int i = 1; i < seq.length; i++) {
                int from = seq[i - 1], to = seq[i];
                if (graph.get(from).add(to)) {
                    indegrees[to]++;
                }
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            for (int val : graph.get(queue.poll())) {
                if (--indegrees[val] == 0) {
                    queue.offer(val);
                }
            }
        }
        return true;
    }
}
