package com.xxh.arithmetic

import com.xxh.datastructure.MyStack_Array

/**
 * @Author Xue_Xionghui
 * @Create_Time 2021-07-11 20:46
 *
 */
object Calculator {
	
	def main(args: Array[String]): Unit = {
		val expressionString = "4+6*5-2"
		val stackNum = new MyStack_Array(10)
		val stackOpr = new MyStack_Array(10)
		val index = 0 //用于扫描
		val num1 = 0
		val num2 = 0
		val oper = 0
		val res = 0
		var ch = ' '
		while(true){
			//依次得到expression的每个字符
			ch = expressionString.substring(index, index + 1).charAt(0)
			//判断ch是什么做相应处理
			if(stackOpr.isOper(ch)){
				if(stackOpr.isEmpty){
					stackOpr.push(ch)
				}else{
					if(stackOpr.priority(ch) <= stackOpr.priority(stackOpr.peek)){}
				}
			}
		}
		
		
		

	}
	
}
