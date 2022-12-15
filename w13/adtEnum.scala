/*  Haskell Code
 *  data Exp = Num Int | Sum Exp Exp | Mul Exp Exp
 *
 *  eval :: Exp -> Int
 *  eval (Num x)     = x
 *  eval (Sum x y)   = eval x + eval y
 *  eval (Mul x y)   = eval x * eval y
 *
 *  e1 = Sum (Num 1) (Mul (Num 2) (Num 3))   // 1 + 2 * 3 AST
 *  main = print(eval e1)
 */

enum Exp:
  case Num(n: Int)           extends Exp
  case Sum(e1: Exp, e2: Exp) extends Exp
  case Mul(e1: Exp, e2: Exp) extends Exp

object Exp:
  def eval(e: Exp): Int = e match
    case Num(n)       => n
    case Sum(e1, e2)  => eval(e1) + eval(e2)
    case Mul(e1, e2)  => eval(e1) * eval(e2)

def main(args: Array[String]): Unit =
  import Exp.*
  val e1: Exp = Sum(Num(1), Mul(Num(2), Num(3)))  // 1 + 2 * 3
  println(eval(e1))  // 7, calling a staice method

// enum
// 1. enum = 고정된 값들을 표현하는 타입 정의  
// 2. enum은 sealed class로서 subclass (data constructor)들을 내포하고
//    class 키워드를 사용하지 않음
// 3. companion 객체 내에 함수 정의 (singletone, staic method)
// 4. naming 때문에 main에서 import 가 요구됨.
// 5. Java의 enum에서는 subclass 정의에 인자를 가질 수 있음

// Scala 3의 enum은 sealed case hierachy의 간결한 표현(sugaring)
// enum = sealed class + case subclass + companion obj (singletone)
// Haskell의 ADT: 고정된 수의 case 들로 구성된 타입
// 각각의 case는 data constructor로서 표현됨

// interface는 enum과는 달리 고정된 수의 subclass를 가정하지 않으며,
// companion class 또한 요구하지 않음


