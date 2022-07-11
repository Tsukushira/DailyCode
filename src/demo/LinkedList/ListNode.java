package demo.LinkedList;


public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 通过数组创建链表，头插法
     */
    public static ListNode createListByArray(int[] arr) {
        ListNode node = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            node = new ListNode(arr[i], node);
        }
        return node;
    }

    /**
     * 遍历打印输出链表，迭代
     */
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val).append(", ");
            head = head.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        if (sb.length() > 1) {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append("]");
        }
        System.out.println(sb.toString());
    }
}
