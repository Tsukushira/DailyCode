package daycode.leetcode;

import java.util.*;
import java.util.stream.Collectors;

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

//        int[] nums = {3, 5, 4, 1};
//        nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));
//
//        nums = new int[]{3, 2, 1};
//        nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));

//        System.out.println(multiply("123", "0"));
//        System.out.println(multiply("10", "10"));
//        System.out.println(multiply("123", "456"));
//        System.out.println(multiply("123456789", "999999999999"));

//        int[][] intervals = {{1, 2}, {3, 5}, {8, 9}};
//        System.out.println(Arrays.deepToString(insert(intervals, new int[]{5, 6})));
//        System.out.println(Arrays.deepToString(insert(intervals, new int[]{5, 8})));
//        System.out.println(Arrays.deepToString(insert(intervals, new int[]{6, 7})));
//        System.out.println(Arrays.deepToString(insert(intervals, new int[]{5, 10})));


//        System.out.println(addBinary("111111", "101010"));
//        System.out.println(addBinary("111111", "111111"));

//        int[] nums = {1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6};
//        int n = removeDuplicates(nums);
//        for (int i = 0; i < n; i++) {
//            System.out.print(nums[i] + " ");
//        }

//        int[] nums = {3, 4, 0, 1, 1, 2, 2, 3};
//        System.out.println(search(nums, 0));

//        System.out.println(getPermutation(6, 666));
//        System.out.println(getPermutation(7, 777));

//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        System.out.println(fullJustify(words, 16));
//        System.out.println(fullJustify(words, 20));
//        System.out.println(fullJustify(words, 26));


        int[][] isInfected = {{0, 1, 0, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(containVirus(isInfected));
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

    /**
     * 交换数组中a，b两个位置的值
     *
     * @param nums
     * @param a
     * @param b
     */
    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 43. 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * num1 和 num2 只能由数字组成。
     * num1 和 num2 都不包含任何前导零，除了数字0本身。
     * 1 <= num1.length, num2.length <= 200。
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] cn1 = num1.toCharArray();
        char[] cn2 = num2.toCharArray();
        int n1 = cn1.length - 1;
        int n2 = cn2.length - 1;
        int[] res = new int[n1 + n2 + 2];

        for (int i = n1; i >= 0; i--) {
            int x = cn1[i] - '0';
            for (int j = n2; j >= 0; j--) {
                int y = cn2[j] - '0';
                res[i + j + 1] += x * y;
            }
        }
        for (int i = res.length - 1; i > 0; i--) {
            res[i - 1] += res[i] / 10;
            res[i] = res[i] % 10;
        }
        StringBuilder sb = new StringBuilder();
        int i = res[0] == 0 ? 1 : 0;
        while (i < res.length) {
            sb.append(res[i++]);
        }
        return sb.toString();
    }

    /**
     * 57. 插入区间
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * @param intervals   一个无重叠的 ，按照区间起始端点排序的区间列表。
     * @param newInterval 需要插入列表中的一个新的区间
     * @return 插入新区间后的列表
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        while (i < n && newInterval[0] > intervals[i][1]) {
            res.add(intervals[i++]);
        }
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[1] = Math.max(newInterval[1], intervals[i++][1]);
        }
        res.add(newInterval);
        while (i < n) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[res.size()][]);
    }


    /**
     * 67. 二进制求和
     * 1 <= a.length, b.length <= 10^4
     *
     * @param a 二进制字符串
     * @param b 二进制字符串
     * @return 返回它们的和（用二进制表示）
     */
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int n1 = a.length() - 1;
        int n2 = b.length() - 1;
        while (n1 >= 0 || n2 >= 0) {
            if (n1 >= 0) carry += a.charAt(n1--) - '0';
            if (n2 >= 0) carry += b.charAt(n2--) - '0';
            sb.insert(0, carry % 2);
            carry >>= 1;
        }
        return carry == 0 ? sb.toString() : "1" + sb;
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * 原地删除重复出现的元素，使得出现次数超过两次的元素只出现两次。
     *
     * @param nums 一个升序数组nums
     * @return 返回删除后数组的新长度
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || nums[i - 2] < n) {
                nums[i++] = n;
            }
        }
        return i;
    }

    /**
     * 81. 搜索旋转排序数组 II
     * 给定一个旋转后的数组 nums 和一个整数 target ，判断给定的目标值是否存在于数组中。
     * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
     * 你必须尽可能减少整个操作步骤。
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 60. 排列序列, (解题方法：康拓展开)
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     * 当 n = 3 时，按大小顺序的排列情况："123", "132", "213", "231", "312", "321"。
     * 给定 n 和 k，返回第 k 个排列。
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        int[] digit = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> dList = Arrays.stream(digit).boxed().collect(Collectors.toList());
        int[] factor = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        StringBuilder sb = new StringBuilder();
        k--;
        while (n > 0) {
            int idx = k / factor[n - 1];
            sb.append(dList.remove(idx));
            k %= factor[--n];
        }
        return sb.toString();
    }

    /**
     * 68. 文本左右对齐
     * 重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     * 要求尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     * <p>
     * 注意：
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于 maxWidth。
     * 输入单词数组 words 至少包含一个单词。
     * <p>
     * 示例：
     * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
     * 输出:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     *
     * @param words    单词数组
     * @param maxWidth 重新排版words中的单词，使其成为每行恰好有 maxWidth 个字符
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length, right = 0;
        while (true) {
            int left = right;
            int sumLen = 0;
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }
            // 当前行是最后一行
            if (right == n) {
                StringBuilder sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));// 插入右边剩余的空格
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词
            if (numWords == 1) {
                ans.add(words[left] + blank(numSpaces));
                continue;
            }
            // 当前行不止一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            String sb = join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1)) +// 拼接额外加一个空格的单词
                    blank(avgSpaces) +
                    join(words, left + extraSpaces + 1, right, blank(avgSpaces));// 拼接其余单词
            ans.add(sb);
        }
    }

    /**
     * @param n
     * @return 返回长度为 n 的由空格组成的字符串
     */
    private static String blank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * @param words
     * @param left
     * @param right
     * @param sep
     * @return 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
     */
    private static StringBuilder join(String[] words, int left, int right, String sep) {
        StringBuilder sb = new StringBuilder(words[left]);
        for (int i = left + 1; i < right; i++) {
            sb.append(sep).append(words[i]);
        }
        return sb;
    }

    /**
     * 749. 隔离病毒
     * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。
     * 可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙。
     * 每天只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），
     * 且该感染区域对未感染区域的威胁最大且 保证唯一。
     * 如果可以成功使得最后有部分区域不被病毒感染，返回需要使用的防火墙个数;
     * 否则，返回在矩阵被病毒全部感染时已安装的防火墙个数。
     *
     * @param isInfected m x n 的二维矩阵，isInfected[i][j] == 0表示该区域未感染病毒，isInfected[i][j] == 1表示该区域已感染病毒。
     * @return 安装的防火墙个数
     */
    public static int containVirus(int[][] isInfected) {
        int ans = 0;
        int m = isInfected.length, n = isInfected[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (true) {
            List<Set<Integer>> neighbors = new ArrayList<>();
            List<Integer> firewalls = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1) {
                        Queue<int[]> queue = new ArrayDeque<>();
                        queue.offer(new int[]{i, j});
                        Set<Integer> neighbor = new HashSet<>();
                        int firewall = 0, idx = neighbors.size() + 1;
                        isInfected[i][j] = -idx;

                        // 从[i, j]开始BFS，将 1 变为-idx，将 1 相邻的 0 记录在Set里，记录需要的防火墙数量。
                        while (!queue.isEmpty()) {
                            int[] arr = queue.poll();
                            int x = arr[0], y = arr[1];
                            for (int[] dir : dirs) {
                                int nx = x + dir[0], ny = y + dir[1];
                                if(nx >=0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1) {
                                        queue.offer(new int[]{nx, ny});
                                        isInfected[nx][ny] = -idx;
                                    } else if (isInfected[nx][ny] == 0) {
                                        neighbor.add((nx << 6) ^ ny);
                                        firewall++;
                                    }
                                }
                            }
                        }
                        neighbors.add(neighbor);
                        firewalls.add(firewall);
                    }
                }
            }
            if (neighbors.isEmpty()) {
                break;
            }
            if (neighbors.size() == 1) {
                return ans + firewalls.get(0);
            }

            int idx = 0;
            for (int i = 1; i < neighbors.size(); i++) {// 找到感染威胁最大的区域
                if (neighbors.get(i).size() > neighbors.get(idx).size()) {
                    idx = i;
                }
            }
            ans += firewalls.get(idx);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] < 0) {
                        if (isInfected[i][j] == -idx - 1) {
                            isInfected[i][j] = 2;// 将防火墙围起来的病毒区域标记为2
                        } else {
                            isInfected[i][j] = 1;// 其它病毒区域恢复为1
                        }
                    }
                }
            }
            // 将病毒感染区域扩散
            for (int i = 0; i < neighbors.size(); i++) {
                if (i != idx) {
                    for (int val : neighbors.get(i)) {
                        int x = val >> 6, y = val & ((1 << 6) - 1);
                        isInfected[x][y] = 1;
                    }
                }
            }
        }
        return ans;
    }
}