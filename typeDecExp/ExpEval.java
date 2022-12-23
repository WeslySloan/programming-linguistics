/* Haskell Code
 * 
 * data Exp = Num Int | Sum Exp Exp | Mul Exp Exp
 *  eval :: Exp -> Int
 *  eval (Num n)     = n
 *  eval (Sum e1 e2)   = eval e1 + eval e2
 *  eval (Mul e2 e2)   = eval e1 * eval e2
 *
 *  e = Sum (Num 1) (Mul (Num 2) (Num 3))   // 1 + 2 * 3, AST
 *  main = print(eval e)
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

class Sum implements Exp {
  Exp e1; Exp e2;
  Sum(Exp e1, Exp e2) {this.e1 = e1; this.e2 = e2; }
  @Override
  public int eval() {return e1.eval() + e2.eval(); }
}

class Mul implements Exp {
  Exp e1; Exp e2;
  Mul(Exp e1, Exp e2) {this.e1 = e1; this.e2 = e2; }
  @Override
  public int eval() {return e1.eval() * e2.eval(); }
}

public class ExpEval {
  public static void main(String[] args) {
    // 1 + 2 * 3
    Exp e = new Sum(new Num(1),
                    new Mul(new Num(2), new Num(3) ));
    int result = e.eval();
    System.out.println(result);
  }
}
