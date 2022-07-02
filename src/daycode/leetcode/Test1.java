package daycode.leetcode;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
//        String[] strs = {"aba", "cdc", "eae"};
//        System.out.println(findLUSlength(strs));
//
//        strs = new String[]{"aaa", "aaa", "aa"};
//        System.out.println(findLUSlength(strs));
//
//        strs = new String[]{"aabbcc", "aabbcc", "abc", "aa"};
//        System.out.println(findLUSlength(strs));

//        System.out.println(diffWaysToCompute("2-1-1"));
//        System.out.println(diffWaysToCompute("2*3-4*5"));

//        System.out.println(grayCode(2));
//        System.out.println(grayCode(3));
//
//        System.out.println(grayCode1(2));
//        System.out.println(grayCode1(3));

//        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
//        System.out.println(minRefuelStops(100, 10, stations));
//
//        stations = new int[][]{{10, 100}};
//        System.out.println(minRefuelStops(10, 1, stations));

        int[] nums = {3,5,4,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[] {3,2,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 522. 最长特殊序列 II
     * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）
     * s的子序列可以通过删去字符串s中的某些字符实现。
     *
     * @param strs 字符串列表
     * @return 返回其中 最长的特殊序列 的长度
     */
    public static int findLUSlength(String[] strs) {
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        for (int i = 0; i < strs.length; i++) {
            if (!isSubSeqOfAnother(strs, i)) {
                return strs[i].length();
            }
        }
        return -1;
    }

    /**
     * 判断strs[idx]是否是strs中其它字符串的子序列
     */
    private static boolean isSubSeqOfAnother(String[] strs, int idx) {
        for (int i = 0; i < strs.length; i++) {
            if (i == idx) {
                continue;
            }
            if (strs[i].length() < strs[idx].length()) {
                break;
            }
            if (isSubSeq(strs[idx], strs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * s1.length() >= s2.length()
     * 判断 s1(strs[idx])是否为 s2(strs[i])的子序列
     */
    private static boolean isSubSeq(String s1, String s2) {
        int p1 = 0, p2 = 0;
        while (p1 < s1.length() && p2 < s2.length()) {
            while (p2 < s2.length() && s2.charAt(p2) != s1.charAt(p1)) {
                p2++;
            }
            if (p2 < s2.length()) {
                p1++;
            }
            p2++;
        }
        return p1 == s1.length();
    }

    /**
     * 18. 四数之和
     *
     * @param nums   由 n 个整数组成的数组
     * @param target 目标值
     * @return 满足 nums[a] + nums[b] + nums[c] + nums[d] == target 的不重复四元组
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);// [-2,-1,0,0,1,2] [-3,-1,0,2,4,5]
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        right--;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 241. 为运算表达式设计优先级
     * 一个由数字和运算符组成的字符串 input, 按不同优先级组合数字和运算符，
     * 计算并返回所有可能组合的结果。可以按 任意顺序 返回答案。
     *
     * @param input 由数字和运算符(+, -, *)组成的字符串
     * @return 返回所有可能组合的结果
     */
    public static List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>();
        return dfs(input, map);
    }

    /**
     * 记忆化处理，分治处理
     *
     * @param input
     * @param map
     * @return
     */
    private static List<Integer> dfs(String input, Map<String, List<Integer>> map) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> list = new ArrayList<>();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = dfs(input.substring(0, i), map);
                List<Integer> right = dfs(input.substring(i + 1), map);
                for (int l : left) {
                    for (int r : right) {
                        System.out.println(l + " " + r);
                        switch (c) {
                            case '+':
                                list.add(l + r);
                                break;
                            case '-':
                                list.add(l - r);
                                break;
                            case '*':
                                list.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (list.isEmpty()) {
            list.add(Integer.valueOf(input));
        }
        map.put(input, list);
        return list;
    }

    /**
     * 89. 格雷编码
     * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
     * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
     * 第一个整数是 0
     * 一个整数在序列中出现 不超过一次
     * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
     * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
     *
     * @param n
     * @return 返回任一有效的 n 位格雷码序列
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        dfs(n, new StringBuilder(), new int[]{0, 1}, res);
        return res;
    }

    /**
     * 回溯生成格雷编码
     */
    private static void dfs(int n, StringBuilder sb, int[] bit, List<Integer> res) {
        if (sb.length() == n) {
            res.add(Integer.valueOf(sb.toString(), 2));
            return;
        }
        sb.append(bit[0]);
        dfs(n, sb, new int[]{0, 1}, res);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(bit[1]);
        dfs(n, sb, new int[]{1, 0}, res);
        sb.deleteCharAt(sb.length() - 1);
    }

    /**
     * 89. 格雷编码
     *
     * @param n
     * @return 返回任一有效的 n 位格雷码序列
     */
    public static List<Integer> grayCode1(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }

    /**
     * 871. 最低加油次数
     *
     * @param target    汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     * @param startFuel 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
     * @param stations  每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
     *                  如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     * @return 汽车所必要的最低加油次数，如果无法到达目的地，则返回 -1。
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int res = 0, n = stations.length;
        for (int i = 0; startFuel < target; res++) {
            while (i < n && startFuel >= stations[i][0]) {
                queue.offer(stations[i++][1]);
            }
            if (queue.isEmpty()) {
                return -1;
            }
            startFuel += queue.poll();
        }
        return res;
    }

    /**
     * 31. 下一个排列
     * 给定一个整数数组 nums ，找出 nums 的下一个排列。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
     * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，按元素升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;// 3 5 4 1 -> 4 1 3 5
        int a = n - 2, b = n - 1;
        while (a < b && a >= 0) {
            while (a >= 0 && nums[a] >= nums[a + 1]) {
                a--;
            }
            if (a < 0) {
                a = 0;
                while (a < b) {
                    swap(nums, a++, b--);
                }
                break;
            }
            while (a < b && nums[b] <= nums[a]) {
                b--;
            }
            swap(nums, a, b);
            a++;
            b = n - 1;
            while (a < b) {
                swap(nums, a++, b--);
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}

