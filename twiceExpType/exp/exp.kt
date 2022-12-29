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
class Num(val value: Int) : Exp
class Sum(val left: Exp, val right: Exp) : Exp
class Mul(val left: Exp, val right: Exp) : Exp

fun eval(e: Exp): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        is Mul -> eval(e.right) * eval(e.left)
        else -> throw IllegalArgumentException("Unknown Expression")
    }

fun main(args: Array<String>) {
    val e1 = Sum(Sum(Num(1), Num(2)), Num(3))     // 1+2+3
    val e2 = Sum(Num(1), Mul(Num(2), Num(3)))     // 1+2*3
    println(eval(e1))
    println(eval(e2))
}
