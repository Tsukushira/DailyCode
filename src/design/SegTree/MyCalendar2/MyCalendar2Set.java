package design.SegTree.MyCalendar2;

import java.util.Map;
import java.util.TreeMap;

/**
 * 731. 我的日程安排表 II
 * 实现一个 MyCalendar 类来存放日程安排。
 * 如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar() : 初始化
 * book(int start, int end) : 如果可以将日程 [start, end)（半开区间）
 * 成功安排添加到日历中而不会导致三重预订，返回true。否则，返回false，并且不要将该日程安排添加到日历中。
 */
public class MyCalendar2Set {
    Map<Integer, Integer> map;

    MyCalendar2Set() {
        map = new TreeMap<>();
    }

    /**
     * 利用差分数组的思想，每当我们预定一个新的日程安排 [start,end)，
     * 在start 计数 cnt[start] 加 1，表示从start 预定的数目加 1；
     * 从 end 计数 cnt[end] 减 1，表示从 end 开始预定的数目减 1。
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int cnt = 0;
        for(int val : map.values()) {// 按从小到大的时间点扫描
            cnt += val;
            if(cnt >= 3) {// 三重预订
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
