package design.SegTree.MyCalendar2;

import java.util.ArrayList;
import java.util.List;

/**
 * 731. 我的日程安排表 II
 * 实现一个 MyCalendar 类来存放日程安排。
 * 如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar() : 初始化
 * book(int start, int end) : 如果可以将日程 [start, end)（半开区间）
 * 成功安排添加到日历中而不会导致三重预订，返回true。否则，返回false，并且不要将该日程安排添加到日历中。
 */
public class MyCalendar2List {
    List<int[]> booked;// 安排表
    List<int[]> overlaps;// 交集

    MyCalendar2List() {
        booked = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for(int[] arr : overlaps) {
            int L = arr[0], R = arr[1];
            if(L < end && R > start) {
                return false;
            }
        }

        for (int[] arr : booked) {
            int L = arr[0], R = arr[1];
            if(L < end && start < R) {
                overlaps.add(new int[]{Math.max(L, start), Math.min(R, end)});
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }

}
