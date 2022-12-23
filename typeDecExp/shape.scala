/* Haskell
  data Shape = Circle Float | Rect Float Float
  
  square :: Float -> Shape 
  square n = Rect n n
  
  area :: Shape -> Float 
  area (Circle r) = pi * r^2 area (Rect x y) = x * y

  rect1, rect2, circle1 :: Shape
  rect1 = Rect 10.0 20.0
  rect2 = square 5.0
  circle1 = Circle 10.0
*/

trait Shape 
case class Circle(c: Float) extends Shape
case class Rect(width: Float, height: Float) extends Shape

// def square(r: Shape): Float = r match {
//   case Rect(width, height)   => width * height
// }
def square(r: Float): Shape = Rect(r, r)

def area(s: Shape): Float = s match {
  case Circle(r: Float)  => (3.14 * r * r).toFloat
  case Rect(width, height)   => width * height
}

def main(args: Array[String]): Unit =
  val rect1: Shape = Rect(10.0, 20.0)
  val rect2: Shape = square(5.0)
  val circle1: Shape = Circle(10.0)

  println(area(circle1)) // 314.0
  println(area(rect1))   // 200.0
  println(area(rect2))   // 25.0

// Haskell의 타입을 trait - subclass 구조로서 표현하기
// Haskell애서 Shape 타입을 갖는 수식은 Shape 타입을 갖는 객체로서 표현됨
// subclass는 생성자로서 표현되며, new 를 생략할 수 있음
