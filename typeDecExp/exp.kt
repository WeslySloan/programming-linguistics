/*  Haskell Code
 *  data Exp = Num Int | Sum Exp Exp | Mul Exp Exp
 *
 *  eval :: Exp -> Int
 *  eval (Num x)     = x
 *  eval (Sum x y)   = eval x + eval y
 *  eval (Mul x y)   = eval x * eval y
 *
 *  e2 = Sum (Num 1) (Mul (Num 2) (Num 3))   // 1 + 2 * 3 AST
 *  main = print $ eval e2
 */

interface Exp
class Num(val number: Int) : Exp
class Sum(val e1: Exp, val e2: Exp) : Exp
class Mul(val e1: Exp, val e2: Exp) : Exp

fun eval(e: Exp): Int =
  when (e) {
    is Num -> e.number
    is Sum -> eval(e.e2) + eval(e.e1)
    is Mul -> eval(e.e2) * eval(e.e1)
    else -> throw IllegalArgumentException("Unknown Expression")
  }

fun main(args: Array<String>) {
  val exp1 = Sum(Sum(Num(1), Num(2)), Num(3))     // 1+2+3
  val exp2 = Sum(Num(1), Mul(Num(2), Num(3)))     // 1+2*3
  println(eval(exp1))
  println(eval(exp2))
}

// Kotlin의 when에 의한 case 구문이 적용됨.
// 이는 Scala의 case class와 유사함. 
// Scala에서는 Constructor 를 구성하는 field 값을 추출하지만, 
// Kotlin에서는 이 기능은 포함되어 있지 않음
