package demo.LinkedList;


public class ListNode {
    public int val;
    public ListNode next;

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
        System.out.println(sb);
    }

    /**
     * 获取链表的长度
     * @param head
     * @return
     */
    public static int getLength(ListNode head) {
       int len = 0;
       while(head != null) {
           len++;
           head = head.next;
       }
       return len;
    }
}
