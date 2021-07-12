package com.xxh.arithmetic

import com.xxh.datastructure.MyStack_Array

/**
 * @Author Xue_Xionghui
 * @Create_Time 2021-07-11 20:46
 *
 */
object Calculator {
	
	def main(args: Array[String]): Unit = {
		val expressionString = "40+6*5-80"
		val stackNum = new MyStack_Array(10)
		val stackOpr = new MyStack_Array(10)
		var index = 0 //用于扫描
		var num1 = 0
		var num2 = 0
		var oper = 0
		var res = 0
		var ch = ' '
		var kepnum = new StringBuilder
		while(index < expressionString.length) {
			//依次得到expression的每个字符
			ch = expressionString.substring(index, index + 1).charAt(0)
			//判断ch是什么做相应处理
			
			if (stackOpr.isOper(ch)) {
				if (stackOpr.isEmpty) {
					stackOpr.push(ch)
				} else {
					if (stackOpr.priority(ch) <= stackOpr.priority(stackOpr.peek)) {
						num1 = stackNum.pop
						num2 = stackNum.pop
						oper = stackOpr.pop
						stackNum.push(stackNum.cal(num1, num2, oper))
						stackOpr.push(ch)
					} else {
						stackOpr.push(ch)
					}
				}
			} else {
				kepnum = kepnum+ch
				if(index == expressionString.length -1){
					stackNum.push(kepnum.toInt)
				}else {
					if (stackNum.isOper(expressionString.substring(index + 1, index + 2).charAt(0))) {
						stackNum.push(kepnum.toInt)
						kepnum.clear()
					}
				}
			}
			index = index + 1
		}
		
		while(!stackOpr.isEmpty){
			num1 = stackNum.pop
			num2 = stackNum.pop
			oper = stackOpr.pop
			stackNum.push(stackNum.cal(num1, num2, oper))
		}
		res = stackNum.pop
		println(res)
	}
	
}

/**
 * 数字入数字栈 符号入符号栈
 *   如果符号栈为空 直接入栈
 *   如果不为空
 *        判断如果当前操作符优先级 <= 栈中 操作符优先级 从数栈中取出两个数 从符号栈中取出一个运算符 将得到的结果入数栈 将当前操作符入符号栈
 *        如果当前操作符优先级 >= 栈中操作符优先级 直接入栈
 * 扫描完毕 依次取出栈中元素计算 最后栈中数字即为结果
 */
