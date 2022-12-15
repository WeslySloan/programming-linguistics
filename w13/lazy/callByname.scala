// https://stackoverflow.com/questions/9508051/function-parameter-types-and

object ByName:
  // def answer = { println("answer"); 40 }
  def answer =
    println("answer")
    40

  def eagerEval(x: Int)   = { println("eager"); x; }
  // def lazyEval(x: => Int) = { println("lazy");  x; }
  def lazyEval(x: ()=>Int) = { println("lazy");  x; }

  @main def myApp = 
    val r1 = eagerEval(answer + 2)  // answer \n lazy
    val r2 = lazyEval(answer + 2)   // lazy \n answer
    println(r1)
    println(r2)

// eagerEval의 파라메터 전송은 call-by-value로 실행
// 먼저 answer의 값 구하기가 실행됨. "eager" 를 프린트한 후, 값 40을 리턴 함

// lazyEval의 파라메터 전송은 call-by-name 으로 진행
// answer + 2 의  값 구하기를 하지 않은 상태로 lazyEval로 흐름 제어가 이동함
// lazyEval 에서는 순서대로 "lazy" 를 프린트 한 다음, x의 값 구하기를 실행함
// 이를 위해서 파라메터로 주어진 answer 함수를 실행하고 ("answer" 프린트) 40의 값을 리턴함
