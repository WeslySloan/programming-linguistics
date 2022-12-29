// https://docs.scala-lang.org/scala3/book/types-adts-gadts.html

// Generalized Algebraic Datatypes (GADTs)

// 다음 Expr 타입은 Eq 기능을 가지므로 Expr Int와 Expr Bool의 두 가지 타입을 갖는다
// Phantom type

/* Haskell Expression
data Expr a where
    I   :: Int  -> Expr Int
    B   :: Bool -> Expr Bool
    Add :: Expr Int -> Expr Int -> Expr Int
    Mul :: Expr Int -> Expr Int -> Expr Int
    Eq  :: Eq a => Expr a -> Expr a -> Expr Bool
*/

enum Expr[T]:        // GADT: T는 Int와 Boolean이 될 수 있음
  case I(n: Int)                         extends Expr[Int]
  case B(b: Boolean)                     extends Expr[Boolean]
  case Add(x: Expr[Int], y: Expr[Int])   extends Expr[Int]
  case Mul(x: Expr[Int], y: Expr[Int])   extends Expr[Int]
  case Eq(x: Expr[T], y: Expr[T])        extends Expr[Boolean]

object Expr:
  def eval[T](e: Expr[T]): T = e match
    case I(n)      => n
    case B(b)      => b
    case Add(x,y)  => eval(x) + eval(y)
    case Mul(x,y)  => eval(x) * eval(y)
    case Eq(x,y)   => eval(x) == eval(y)

@main def myApp = 
  import Expr.*
  val exp1: Expr[Int] = I(10)
  println(exp1)                    // I(10)
  println(eval(exp1))              // 10
  val exp2: Expr[Int] = Add(I(5), I(6))  // 5 + 6
  println(eval(exp2))              // 11
  
  // 2 * 3 == 1 + 5`
  val exp3: Expr[Boolean] = Eq(Mul(I(2), I(3)), Add(I(1), I(5)))
  println(eval(exp3))              // true

