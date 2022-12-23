trait Exp
case class Num(n: Int) extends Exp
case class Sum(e1: Exp, e2: Exp) extends Exp
case class Mul(e1: Exp, e2: Exp) extends Exp

def eval(e: Exp): Int = e match {
  case Num(n)       => n
  case Sum(e1, e2)  => eval(e1) + eval(e2)
  case Mul(e1, e2)  => eval(e1) * eval(e2)
}

def main(args: Array[String]): Unit =
  val e: Exp = Sum(Num(1), Mul(Num(2), Num(3)))  // 1 + 2 * 3
  val result: Int = eval(e)
  println(result)

// case class는 pattern matching를 적용할 수 있음
// 객체 e 가 생성된 클래스 패턴 및 그 객체를 생성할 때 사용된 field 값을 추출 함. 
// 예: Sum(e1, e2) 형태로 생성된 객체로부터 e1과 e2를 extract 함
// Exp는 Tree (재귀적) 구조를 갖고 있으며, Num 이 base case에 해당된다.
