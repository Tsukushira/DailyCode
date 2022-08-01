package demo.BinaryTree;

public class Node {
    int val;
    Node left;
    Node right;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    /**
     * 从层次遍历中递归建树
     * 通过String重建二叉树，节点的数据不可大于9，null节点使用 '#' 表示
     *
     * @param n 调用时n赋值为0
     */
    public Node buildTree(String str, int n) {
        if (str == null || n < 0 || n >= str.length() || str.charAt(n) == '#') {
            return null;
        }
        Node root = new Node(str.charAt(n) - '0');
        root.left = buildTree(str, 2 * n + 1);
        root.right = buildTree(str, 2 * n + 2);
        return root;
    }

    /**
     * 从层次遍历建树
     * 使用字符串数组，null节点使用 "#" 表示
     *
     * @param n 调用时n赋值为0
     */
    public Node buildTree(String[] str, int n) {
        if (str == null || n < 0 || n > str.length || str[n].equals("#")) {
            return null;
        }
        int l = 2 * n + 1;
        int r = 2 * n + 2;
        return new Node(Integer.parseInt(str[n]), buildTree(str, l), buildTree(str, r), null);
    }

    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * <p>
     * 填充节点的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 null。
     * 初始状态下，所有next 指针都被设置为 null。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node dummy = new Node();
        dummy.next = root;
        while (dummy.next != null) {
            Node cur = dummy.next;
            Node pre = dummy;
            dummy.next = null;
            while (cur != null) {
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
        }
        return root;
    }
}
