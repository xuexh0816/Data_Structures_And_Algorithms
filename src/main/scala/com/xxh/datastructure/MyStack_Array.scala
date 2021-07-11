package com.xxh.datastructure

import java.util.Scanner

/**
 * 用数组模拟栈
 */
object MyStack_Array{
	def main(args: Array[String]): Unit = {
		val stack: MyStack_Array = new MyStack_Array(4)
		var key = ""
		var loop = true
		val scanner = new Scanner(System.in)
		while(loop){
			println("show: 表示显示栈")
			println("exit: 表示退出栈")
			println("push: 表示添加数据到栈")
			println("pop: 表示从栈取出数据")
			println("请输入你的选择")
			
			key = scanner.next()
			key match {
				case "show" => stack.show()
				case "push" =>
					println("请输入一个数")
					val value = scanner.nextInt()
					stack.push(value)
				case "pop" =>
					try {
						val res = stack.pop
						printf("出栈的数据是%d\n",res)
					} catch {
						case e:Exception => println(e.getMessage)
					}
				case "exit" =>
					scanner.close()
					loop = false
				case _ => println("输入错误，请重新输入")
			}
		}
	}
}

class MyStack_Array(val maxSize:Int = 0) {
	var stack : Array[Int] = new Array[Int](maxSize)
	var top: Int = -1
	
	def isFull: Boolean = {top==maxSize -1}
	
	def isEmpty: Boolean = {top == -1}
	
	def push (value:Int): Unit ={
		if(isFull){
			println("栈满")
			return
		}
		top=top+1
		stack(top)=value
	}
	
	def pop: Int ={
		if (isEmpty) throw new RuntimeException("栈空，无数据")
		val value = stack(top)
		top = top - 1
		value
	}
	
	def show(): Unit = {
		if(isEmpty) {
			println("栈空，没有数据")
			return
		}
		for ( i <- top to 0 by(-1)){
			printf("stack[%d]=%d\n",i,stack(i))
		}
	}
	
	def priority(oper:Int): Int ={
		oper match {
			case '*' => 1
			case '/' => 1
			case '+' => 0
			case '-' => 0
			case _ => -1
		}
	}
	
	def isOper(oper : Char): Boolean ={
		oper == '+' || oper == '-' ||  oper == '*' || oper == '/'
	}
	
	def cal (num1:Int,num2:Int,oper:Int): Int ={
		oper match {
			case '*' => num1 * num2
			case '/' => num2 / num1
			case '+' => num1 + num2
			case '-' => num2 - num1
			case _ => throw new RuntimeException("运算符输入错误")
		}
	}
	
	def peek: Int ={
		stack(top)
	}
	
}
