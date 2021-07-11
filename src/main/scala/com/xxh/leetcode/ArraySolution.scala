package com.xxh.leetcode

import scala.collection.mutable.ArrayBuffer



/**
 * @Author Xue_Xionghui
 * @Create_Time 2021-06-14 12:19
 * @Description $END
 *
 */
object ArraySolution {
	def removeDuplicates(nums: Array[Int]): Int = {
		if (nums.length < 2) {
			nums.length
		} else {
			var left: Int = 0
			for (right: Int <- 1 until nums.length) {
				if (nums(left) != nums(right)) {
					left = left + 1
					nums(left) = nums(right)
				}
			}
			left + 1
		}
	}
	
	def maxProfit(prices: Array[Int]): Int = {
		if (prices.length < 2) {
			0
		} else {
			var result: Int = 0
			for (index <- 1 until prices.length) {
				if (prices(index - 1) < prices(index)) {
					result = prices(index) - prices(index - 1) + result
				}
			}
			result
		}
	}
	
	def rotate(nums: Array[Int], k: Int): Unit = {
		if (nums.length > 1) {
			var moveNum: Int = k % nums.length
			reverse(nums, 0, nums.length - 1)
			reverse(nums, 0, moveNum - 1)
			reverse(nums, moveNum, nums.length - 1)
		}
	}
	
	def reverse(nums: Array[Int], start: Int, end: Int): Unit = {
		var left: Int = start
		var right: Int = end
		while (left < right) {
			var temp: Int = nums(left)
			nums(left) = nums(right)
			nums(right) = temp
			left = left + 1
			right = right - 1
		}
	}
	
	def containsDuplicate(nums: Array[Int]): Boolean = {
		nums.toSet.size == nums.length
	}
	
	def singleNumber(nums: Array[Int]): Int = {
		nums.toSet.sum * 2 - nums.sum
	}
	
	def intersect(nums1: Array[Int], nums2: Array[Int]) = {
		val sumSet: Set[Int] = nums1.toSet ++ nums2
		val otherSet1 = sumSet -- nums2.toSet
		val singleAll = (nums1.toSet -- otherSet1)
		val result = new ArrayBuffer[Int]()
		for (elem <- singleAll) {
			val num1 = elemNum(nums1, elem)
			val num2 = elemNum(nums2, elem)
			if (num1 >= num2) {
				for (i <- 1 to num2) {
					result += elem
				}
			} else {
				for (i <- 1 to num1) {
					result += elem
				}
			}
		}
		result.toArray
	}
	
	def elemNum(nums: Array[Int], elem: Int): Int = {
		var num: Int = 0
		for (element <- nums) {
			if (elem == element) num = num + 1
		}
		num
	}
	
	def plusOne(digits: Array[Int]): Array[Int] = {
		for (i <- digits.length - 1 to 0 by -1) {
			if (digits(i) != 9) {
				digits(i) += 1
				return digits
			} else {
				digits(i) = 0
			}
			if (i == 0 && digits(i) == 0) {
				val ints = new Array[Int](digits.size + 1)
				ints.update(0, 1)
				return ints
			}
		}
		digits
	}
	
	def moveZeroes(nums: Array[Int]): Array[Int] = {
		var zeroNum: Int = 0
		if (nums.length == 1) return nums
		
		for (i <- 0 until nums.length - 1) {
			if (nums(i) == 0 & nums(i + 1) == 0) zeroNum = zeroNum + 1
			if (nums(i) == 0 & nums(i + 1) != 0) {
				var temp = nums(i - zeroNum)
				nums(i - zeroNum) = nums(i + 1)
				nums(i + 1) = temp
			}
		}
		if (zeroNum != 0) {
			for (i <- nums.length - 1 to nums.length - zeroNum by -1) {
				nums(i) = 0
			}
			nums
		} else nums
	}
	
	def twoSum(nums: Array[Int], target: Int): Array[Int] = {
		var map: Map[Int, Int] = Map()
		for (i <- nums.indices) {
			if (map.contains(target - nums(i))) {
				return Array[Int](map(target - nums(i)), i)
			}
			map += (nums(i) -> i)
		}
		Array(0, 0)
	}
	
	def isValidSudoku(board: Array[Array[Char]]): Boolean = {
		val line: Array[Array[Int]] = Array.ofDim[Int](9,9)
		val column: Array[Array[Int]] = Array.ofDim[Int](9,9)
		val cell = Array.ofDim[Int](9,9)
		for (i <- 0 until 9) {
			import scala.util.control.Breaks.{breakable,break}
			for (j <- 0 until 9) {
				breakable{
					if (board(i)(j).equals('.'))
							break()
					val num: Int = board(i)(j)-'0' - 1
					val k: Int = (i / 3) * 3 + j / 3
					if(line(i)(num) != 0 || column(j)(num) != 0 || cell(k)(num) != 0){
						return false
					}
					line(i)(num) = 1
					column(j)(num) = 1
					cell(k)(num) = 1
				}
			}
		}
		true
	}
	def rotate(matrix: Array[Array[Int]]): Array[Array[Int]] = {
		val size = matrix.length
		//1. 左右交换(i)(j) => (i)(length - j-1)
		for(row<- matrix.indices){
			for (column<- 0 to (size-1)/2){
				val temp = matrix(row)(column)
				matrix(row)(column)=matrix(row)(size-column-1)
				matrix(row)(size-column-1)=temp
			}
		}
		
		for (elem <- matrix) {
			for (elem <- elem) {
				print(elem+"\t")
			}
			println("")
		}
		println("_________________________________")
//		2. 副对角线交换(i)(j) => (length-j-1)(length-i+1)
		for(row<- matrix.indices){
			for (column<- 0 until (size - row)){
				val temp = matrix(row)(column)
				matrix(row)(column)=matrix(size-column-1)(size-row-1)
				matrix(size-column-1)(size-row-1)=temp
			}
		}
		matrix
	}
	
	def main(args: Array[String]): Unit = {
		
		println(removeDuplicates(Array(1, 2, 3)))
		
		println(maxProfit(Array(1, 3, 4, 2, 1, 5)))
		
		val nums = Array(1, 2)
		rotate(nums, 1)
		for (elem <- nums) println(elem)
		
		for (elem <- intersect(Array(1, 2, 2, 1), Array(2, 2))) {
			println(elem)
		}
		
		for (elem <- plusOne(Array(9, 9, 9))) {
			println(elem)
		}
		
		for (elem <- moveZeroes(Array(0, 1, 0, 3, 12))) {
			println(elem)
		}
		
		for (elem <- twoSum(Array(1, 2, 3, 4, 5, 6, 7), 8)) {
			println(elem)
		}
		
		val board: Array[Array[Char]] =Array[Array[Char]](Array[Char]('5','3','.','.','7','.','.','.','.'),Array[Char]('6','.','.','1','9','5','.','.','.'),Array[Char]('.','9','8','.','.','.','.','6','.'),Array[Char]('8','.','.','.','6','.','.','.','3'),Array[Char]('4','.','.','8','.','3','.','.','1'),Array[Char]('7','.','.','.','2','.','.','.','6'),Array[Char]('.','6','.','.','.','.','2','8','.'),Array[Char]('.','.','.','4','1','9','.','.','5'),Array[Char]('.','.','.','.','8','.','.','7','9'))
		println(isValidSudoku(board))
		
		val matrix: Array[Array[Int]] = Array[Array[Int]](Array[Int](5, 1, 9, 11), Array[Int](2, 4, 8, 10), Array[Int](13, 3, 6, 7), Array[Int](15, 14, 12, 16))
		for (elem <- matrix) {
			for (elem <- elem) {
				print(elem+"\t")
			}
			println("")
		}
		println("_________________________________")
		for (elem <- rotate(matrix)) {
			for (elem <- elem) {
				print(elem+"\t")
			}
			println("")
		}
	}
}
