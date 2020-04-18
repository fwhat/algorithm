package golang

/**
 * 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	var l1Nodes []int
	var l2Nodes []int

	l1Nodes = append(l1Nodes, l1.Val)
	for l1.Next != nil {
		l1Nodes = append(l1Nodes, l1.Next.Val)
		l1 = l1.Next
	}

	l2Nodes = append(l2Nodes, l2.Val)
	for l2.Next != nil {
		l2Nodes = append(l2Nodes, l2.Next.Val)
		l2 = l2.Next
	}

	var lastNode *ListNode
	var maxGroup []int
	var minGroup []int
	if len(l1Nodes) > len(l2Nodes) {
		maxGroup = l1Nodes
		minGroup = l2Nodes
	} else {
		maxGroup = l2Nodes
		minGroup = l1Nodes
	}

	for i := 0; i < len(maxGroup); i++ {
		lastAI := len(maxGroup) - 1 - i
		lastBI := len(minGroup) - 1 - i

		newNode := &ListNode{}
		newNode.Val = maxGroup[lastAI]

		if lastBI >= 0 {
			newNode.Val += minGroup[lastBI]
		}

		if lastNode != nil {
			if lastNode.Val >= 10 {
				newNode.Val += lastNode.Val / 10
				lastNode.Val = lastNode.Val % 10
			}
			newNode.Next = lastNode
		}

		lastNode = newNode
	}
	if lastNode != nil && lastNode.Val >= 10 {
		newNode := &ListNode{}
		newNode.Val += lastNode.Val / 10
		lastNode.Val = lastNode.Val % 10
		newNode.Next = lastNode

		return newNode
	}

	return lastNode
}

type ListNode struct {
	Val  int
	Next *ListNode
}
