/* Haskell: Fibonacci Numbers: infinite list
  fib = 0 : 1 : map tupleAdd listTuple 
          where
            tupleAdd (a, b) = a + b
            listTuple = zip fib (tail fib)
*/

import scala.math.BigInt
import math.Numeric.Implicits.infixNumericOps

// https://www.scala-lang.org/api/3.0.2/scala/collection/immutable/LazyList.html

object Lazy:
  val fibs: LazyList[BigInt] = 
    BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(n => n._1 + n._2)
      // fibs.zip(fibs.tail).map{ n => println(s"Adding ${n._1} and ${n._2}"); n._1 + n._2 }

  def fib: LazyList[Int] = {
    // tuples
    // def tupleAdd(tpl: (Int,Int)): Int = tpl._1 + tpl._2
    def tupleAdd = {(x: (Int,Int)) => val (a,b) = x ;  a + b}  // labmda & pattern matching
    // def tupleAdd(tpl: (Int,Int)): Int = { val (a, b) = tpl; a + b; }  // pattern matching
    def listTuple = fib.zip(fib.tail)

    0 #:: 1 #:: listTuple.map(tupleAdd)
  }

  def myTake(n:Int, lst: =>LazyList[Int]): List[Int] =       // call-by-name
    // if (n == 0) then List.empty else lst.head :: myTake(n-1, lst.tail)
    if (n == 0) then Nil else lst.head :: myTake(n-1, lst.tail)
  
  def listPrint[A](lst: List[A]) = { lst.foreach(x => print(x.toString + " ")) ; println() }

  @main def myApp = 
    var lst: List[Int] = fib.take(10).toList
    listPrint(lst)

    val infRange = 3 to Int.MaxValue
    lst = infRange.take(10).toList  // haskell: take 10 [3 ..]
    listPrint(lst)

    // The Stream is a lazy list, different from Stream in Java where single use is applied
    // <deprecated>
    val strm = 1 #:: 2 #:: 3 #:: Stream.empty
    val lst2: List[Int] = strm.take(2).toList
    val lst3: List[Int] = strm.take(3).toList

    val infStrm = Stream.from(3)
    lst = infStrm.take(10).toList
    listPrint(lst)



// fib는 무한 개의 피보나치 수들을 생성함. 
// 이 무한 개의 수들은 LazyList[Int] 에 lazy 형태로 generate 됨
// 이를 take 함수로서 정해진 수의 원소들로 구성된 List를 만든다.
// 이때, take의 두 번째 파라메터 리스트는 call-by-name 형태로 처리된다.