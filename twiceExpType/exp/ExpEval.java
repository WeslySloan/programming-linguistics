/*
 * Haskell Code
 * data Exp = Num Int | Plus Exp Exp | Mul Exp Exp
 *  eval :: Exp -> Int
 *  eval (Num x)     = x
 *  eval (Sum x y)   = eval x + eval y
 *  eval (Mul x y)   = eval x * eval y
 *
 *  e2 = Sum (Num 1) (Mul (Num 2) (Num 3))   // 1 + 2 * 3 AST
 *  main = print $ eval e2
 */

interface Exp {
    int eval();        // public
}

class Num implements Exp {
    int number;
    Num(int number)  { this.number = number; }
    @Override
    public int eval()  { return this.number; }
}

class Plus implements Exp {
    Exp left; Exp right;
    Plus(Exp left, Exp right) {this.left = left; this.right = right; }
    @Override
    public int eval() {return left.eval() + right.eval(); }
}

class Mul implements Exp {
    Exp left; Exp right;
    Mul(Exp left, Exp right) {this.left = left; this.right = right; }
    @Override
    public int eval() {return left.eval() * right.eval(); }
}

public class ExpEval {
    public static void main(String[] args) {
        // 1 + 2 * 3
        Exp e = new Plus ( new Num(1),
                           new Mul( new Num(2), new Num(3) ));

        int result = e.eval();
        System.out.println(result);
    }
}

