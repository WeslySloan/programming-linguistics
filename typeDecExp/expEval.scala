trait Exp:
  def eval(): Int

class Num(number: Int) extends Exp:
  def eval(): Int = number

class Sum(e1: Exp, e2: Exp) extends Exp:
  def eval(): Int = e1.eval() + e2.eval()

class Mul(e1: Exp, e2: Exp) extends Exp:
  def eval(): Int = e1.eval() * e2.eval()

def main(args: Array[String]): Unit =
  val e: Exp = Sum(Num(1), Mul(Num(2), Num(3)))  // 1 + 2 * 3
  val result: Int = e.eval()
  println(result)

// Java로 정의된 코드(ExpEval.java)에 가까운 표현
// trait 는 Java의 Interface 에 해당되며,
// Num, Sum, Mul 은 Exp의 subclass.
// subclass에서 생성된 객체는 superclass의 타입을 가질 수 있음
// 각 subclass 마다 eval이 override 되었으며, 
// 각 객체에 해당되는 class의 eval 메소드가 선택되어 실행됨
