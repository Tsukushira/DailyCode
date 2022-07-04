package demo.BinaryTree;

import java.util.*;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 利用TreeNode[] nodes，从层次遍历中重建二叉树
     */
    public static TreeNode[] nodes;

    /**
     * 通过String重建二叉树时，节点的数据不可大于9，null节点使用 '#' 表示
     * <br>一次读取一个字符，节点的数据大于9，将出错，
     * <br>当有节点的数据大于9时，使用String[]作为参数的createTree()或buildTree()
     */
    public static TreeNode createTree(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int n = str.length();
        nodes = new TreeNode[n]; // 静态成员变量nodes
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) != '#') {
                if (nodes[i] == null) {
                    nodes[i] = new TreeNode(str.charAt(i) - '0');
                }
                int idx = 2 * i + 1;
                if (idx < n && str.charAt(idx) != '#') {
                    nodes[idx] = new TreeNode(str.charAt(idx) - '0');
                    nodes[i].left = nodes[idx];
                }
                idx++;
                if (idx < n && str.charAt(idx) != '#') {
                    nodes[idx] = new TreeNode(str.charAt(idx) - '0');
                    nodes[i].right = nodes[idx];
                }
            }
        }
        return nodes[0];
    }

    /**
     * 从层次遍历中重建二叉树
     * 使用字符串数组，null节点使用 "#" 表示
     */
    public static TreeNode createTree(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        int n = strs.length;
        TreeNode[] tNode = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            if (!strs[i].equals("#")) {
                if (tNode[i] == null) {
                    tNode[i] = new TreeNode(Integer.parseInt(strs[i]));
                }
                int idx = 2 * i + 1;
                if (idx < n && !strs[idx].equals("#")) {
                    tNode[idx] = new TreeNode(Integer.parseInt(strs[idx]));
                    tNode[i].left = tNode[idx];
                }
                idx++;
                if (idx < n && !strs[idx].equals("#")) {
                    tNode[idx] = new TreeNode(Integer.parseInt(strs[idx]));
                    tNode[i].right = tNode[idx];
                }
            }
        }
        return tNode[0];
    }

    /**
     * 从层次遍历中递归建树
     * 通过String重建二叉树，节点的数据不可大于9，null节点使用 '#' 表示
     */
    public static TreeNode createTree(String str, int n) {
        if (str == null || str.length() == 0 || n >= str.length() || str.charAt(n) == '#') {
            return null;
        }
        int l = 2 * n + 1;
        int m = 2 * n + 2;
        TreeNode tNode = new TreeNode(str.charAt(n) - '0');
        tNode.left = createTree(str, l);
        tNode.right = createTree(str, m);
        return tNode;
    }

    /**
     * 从层次遍历(String[])中重建二叉树
     * 使用字符串数组，null节点使用 "#" 表示
     */
    public static TreeNode createTree(String[] strs, int n) {
        if (strs == null || strs.length == 0 || n >= strs.length || strs[n].equals("#")) {
            return null;
        }
        int idxl = 2 * n + 1;
        int idxr = 2 * n + 2;
        TreeNode tNode = new TreeNode(Integer.parseInt(strs[n]));
        tNode.left = createTree(strs, idxl);
        tNode.right = createTree(strs, idxr);
        return tNode;
    }

    /**
     * 从层次遍历中递归建树
     * 通过String重建二叉树，节点的数据不可大于9，null节点使用 '#' 表示
     */
    public static TreeNode buildTree(String str, int n) {
        if (str == null || str.length() == 0 || n >= str.length() || str.charAt(n) == '#') {
            return null;
        }
        int l = 2 * n + 1;
        int m = 2 * n + 2;
        return new TreeNode(str.charAt(n) - '0', buildTree(str, l), buildTree(str, m));
    }

    /**
     * 从层次遍历建树
     * 使用字符串数组，null节点使用 "#" 表示
     */
    public static TreeNode buildTree(String[] strs, int n) {
        if (strs == null || strs.length == 0 || n >= strs.length || strs[n].equals("#")) {
            return null;
        }
        int idxl = 2 * n + 1;
        int idxr = 2 * n + 2;
        return new TreeNode(Integer.parseInt(strs[n]), buildTree(strs, idxl), buildTree(strs, idxr));
    }

    /**
     * 利用队列从层次遍历中创建二叉树
     * 通过String重建二叉树，节点的数据不可大于9，null节点使用 '#' 表示
     */
    public static TreeNode buildTree(String str) {
        if (str == null || str.length() == 0 || str.charAt(0) == '#') {
            return null;
        }
        TreeNode tNode = new TreeNode(str.charAt(0) - '0');
        TreeNode root = tNode;
        Queue<TreeNode> queue = new LinkedList<>();
        int n = 0;
        while (tNode != null && n < str.length()) {
            int l = 2 * n + 1;
            int m = 2 * n + 2;
            if (l < str.length() && str.charAt(l) != '#') {
                tNode.left = new TreeNode(str.charAt(l) - '0');
                queue.add(tNode.left);
            }
            if (m < str.length() && str.charAt(m) != '#') {
                tNode.right = new TreeNode(str.charAt(m) - '0');
                queue.add(tNode.right);
            }
            tNode = queue.poll();
            n++;
        }
        return root;
    }

    /**
     * 从层次遍历建树
     * 使用字符串数组，null节点使用 "#" 表示
     */
    public static TreeNode buildTree(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].equals("#")) {
            return null;
        }
        TreeNode tNode = new TreeNode(Integer.parseInt(strs[0]));
        TreeNode root = tNode;
        Queue<TreeNode> queue = new LinkedList<>();
        int n = 0;
        while (tNode != null && n < strs.length) {
            int idxl = 2 * n + 1;
            int idxr = 2 * n + 2;
            if (idxl < strs.length && !strs[idxl].equals("#")) {
                tNode.left = new TreeNode(Integer.parseInt(strs[idxl]));
                queue.add(tNode.left);
            }
            if (idxr < strs.length && !strs[idxr].equals("#")) {
                tNode.right = new TreeNode(Integer.parseInt(strs[idxr]));
                queue.add(tNode.right);
            }
            tNode = queue.poll();
            n++;
        }
        return root;

    }


    /**
     * 层次遍历
     */
    public static String levelOrderToString(TreeNode root) {
        if (root == null) {
            System.out.println("Empty Tree");
        }
        ArrayList<String> aList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode tNode = root;
        while (tNode != null) {
            aList.add(String.valueOf(tNode.val));
            if (tNode.left != null) {
                queue.add(tNode.left);
            }
            if (tNode.right != null) {
                queue.add(tNode.right);
            }
            tNode = queue.poll();
        }
        return aList.toString();
    }

    /**
     * 层次遍历二叉树至List<List<Integer>>
     */
    public static List<List<Integer>> levelOrderToList(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 序列化二叉树为String[]，层次遍历，BFS
     */
    public static String[] serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayList<String> aList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node == null) {
                aList.add("#");
                continue;
            }
            aList.add(String.valueOf(node.val));
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return aList.toArray(new String[0]);
    }

    /**
     * 反序列化，将String[]为转换为一颗二叉树，使用队列
     */
    public static TreeNode deserialize(String[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        queue.add(root);
        for (int i = 1; i < data.length; i++) {
            TreeNode parent = queue.poll();
            if (!data[i].equals("#") && parent != null) {
                parent.left = new TreeNode(Integer.parseInt(data[i]));
                queue.add(parent.left);
            }
            if (!data[++i].equals("#") && parent != null) {
                parent.right = new TreeNode(Integer.parseInt(data[i]));
                queue.add(parent.right);
            }
        }
        return root;
    }

    /**
     * 序列化二叉树为String，前序遍历， dfs
     */
    public static String serializeToString(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("[");
        serializeHelp(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    private static void serializeHelp(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#, ");
            return;
        }
        sb.append(root.val).append(", ");
        serializeHelp(root.left, sb);
        serializeHelp(root.right, sb);
    }

    /**
     * String反序列化为二叉树，前序遍历，dfs
     */
    public static TreeNode deserialize(String data) {
        String[] datas = data.substring(1, data.length() - 1).split(",");
        List<String> nodes = new LinkedList<>();
        Collections.addAll(nodes, datas);// 将String[] datas添加至 List<String> nodes
        return deserializeHelp(nodes);
    }

    private static TreeNode deserializeHelp(List<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String val = nodes.remove(0);// 删除头，返回值
        if (val.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelp(nodes);
        root.right = deserializeHelp(nodes);
        return root;
    }
}
