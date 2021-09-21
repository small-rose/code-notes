//<p>请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 <strong>要被删除的节点</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p>现有一个链表 --&nbsp;head =&nbsp;[4,5,1,9]，它可以表示为:</p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/237_example.png" style="height: 49px; width: 300px;"></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>head = [4,5,1,9], node = 5
//<strong>输出：</strong>[4,1,9]
//<strong>解释：</strong>给定你链表中值为&nbsp;5&nbsp;的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 1 -&gt; 9.
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>head = [4,5,1,9], node = 1
//<strong>输出：</strong>[4,5,9]
//<strong>解释：</strong>给定你链表中值为&nbsp;1&nbsp;的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 5 -&gt; 9.
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul>
//	<li>链表至少包含两个节点。</li>
//	<li>链表中所有节点的值都是唯一的。</li>
//	<li>给定的节点为非末尾节点并且一定是链表中的一个有效节点。</li>
//	<li>不要从你的函数中返回任何结果。</li>
//</ul>
//<div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 955</li><li>👎 0</li></div>

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
