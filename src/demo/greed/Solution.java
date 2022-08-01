package demo.greed;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println(intersectionSizeTwo(intervals));

        intervals = new int[][]{{1, 3}, {1, 4}, {5, 9}, {4, 6}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    /**
     * 757. 设置交集大小至少为2
     * 找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。输出这个最小集合S的大小。
     *
     * @param intervals 一组整数区间，一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
     * @return 最小集合S的大小。
     */
    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 2, n = intervals.length;
        int cur = intervals[n - 1][0];
        int next = cur + 1;
        for (int i = n - 2; i >= 0; i--) {
            if (intervals[i][1] < cur) {// 区间不重合
                cur = intervals[i][0];
                next = cur + 1;
                ans += 2;
            } else if (intervals[i][1] < next) {// 有重合，但不是不是完全包含[cur, next]
                next = cur;
                cur = intervals[i][0];
                ans++;
            }
        }
        return ans;
    }
}
