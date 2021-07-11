package com.xxh.leetcode

/**
 * @Author Xue_Xionghui
 * @Create_Time 2021-07-01 21:40
 * @Description $END
 *
 */
class ListNode2(_x: Int = 0, _next: ListNode2 = null) {
	var next: ListNode2 = _next
	var x: Int = _x
	
	override def toString: String = this.x.toString + " "+this.next.x + " "
}