package com.xxh.leetcode

/**
 * @Author Xue_Xionghui
 * @Create_Time 2021-06-30 22:13
 * @Description $END
 *
 */
object ListSolution {
	//1. 链表删除非末尾元素
	def deleteNode(node: ListNode1): Unit = {
		node.next=node.next.next
		node.x=node.next._x
	}
	
	def removeNthFromEnd(head: ListNode2, n: Int): ListNode2 = {
		var fast = head
		var slow = head
		for(i <- 0 until n){
			fast=fast.next
		}
		
		if(fast == null){
			return head.next
		}
		
		while(fast.next != null){
			slow = slow.next
			fast = fast.next
		}
		
		slow.next=slow.next.next
		head
	}
	
	def reverseList(head: ListNode2)= {
		var oldhead: ListNode2 = head
		var newHead:ListNode2 = null
		while (oldhead != null) {
			var temp: ListNode2  = oldhead.next;
			oldhead.next = newHead;
			//更新新链表
			newHead = oldhead;
			//重新赋值，继续访问
			oldhead = temp;
		}
		//返回新链表
		newHead;
	}
	
	def mergeTwoLists(l1: ListNode2, l2: ListNode2): ListNode2 = {
		var l1a = l1
		var l2a = l2
		if(l1a == null ) return l2a
		if(l2a == null ) return l1a
		var dummy = new ListNode2()
		var curr: ListNode2 = dummy
		while (l1a != null && l2a != null){
			if (l1a.x <= l2a.x){
				curr.next = l1a
				l1a = l1a.next
			} else{
				curr.next = l2a
				l2a = l2a.next
			}
			curr = curr.next
		}
		if (l1a == null) curr.next = l2a else curr.next = l2a
		dummy.next
	}
	
	def isPalindrome(_head: ListNode2): Boolean = {
		var head: ListNode2 = _head
		//1. 将后半部分反转
		var right = reverseList(getRightRegionHead(head))
		//2. 双指针判断相等 不相等结束，return false
		var left: ListNode2 = head
		while (right != null){
			if(left.x != right.x) return false
			right = right.next
			left = left.next
		}
		true
	}
	//偶数时 为右边的那个
	def getMiddleNode (_head:ListNode2)={
		//用来承接入参，
		var head: ListNode2 = _head
		//将head的地址赋值给slow
		var slow: ListNode2 = head
		var fast: ListNode2 = head
		while (slow != null && fast.next !=null){
			slow = slow.next
			fast = fast.next.next
		}
		slow
	}
	
	def getRightRegionHead (_head :ListNode2): ListNode2 ={
		val head: ListNode2 = _head
		var slow: ListNode2 = head
		var fast: ListNode2 = head
		while (fast != null && fast.next !=null){
			slow = slow.next
			fast = fast.next.next
		}
		if (fast != null) slow = slow.next
		slow
	}
	
	def getSize (head:ListNode2): Int ={
		var heada = head
		var size :Int = 0
		while(heada != null){
			size = size + 1
			heada = heada.next
		}
		size
	}
	def hasCycle(head: ListNode1): Boolean = {
		if (head == null) return false
		var slow = head
		var fast = head
		while (fast != null && fast.next != null){
			slow=slow.next
			fast = fast.next.next
			if(fast == slow) return true
		}
		false
	}
	
	def main(args: Array[String]): Unit = {
		val node: ListNode2 = new ListNode2(1,new ListNode2(2))
		val node1: ListNode1 = new ListNode1(3)
		println(node.toString)
		println(reverseList(node).toString)

		mergeTwoLists(node,node)
		isPalindrome(node)
		hasCycle(node1)
	}
}



