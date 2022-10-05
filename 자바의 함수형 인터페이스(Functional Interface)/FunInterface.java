/*
- FI(Functional Interface) 란 오직 한 개의 abstract 메소드를 포함하는 interface.
- 람다 식의 타입에 FI를 사용하고, 이 람다 식에 인수를 전달할 때 추상 메소드를 이용함.
람다 식을 사용하지 않는 경우, 무명(Anonymous) 클래스를 사용하여 구현할 수 있지만,
코드가 복잡해 짐.
- default 메소드나 static 메소드는 여러 개 사용 가능

<calculate 구현의 세 가지 방법>
1. Square를 implements 하는 방법 (전통적 방법) : result1 로 계산
   main 메소드 밖에서 calculate 메소드를 구현함.
2. Anonymous 클래스에서 구현: main 메소드 내에서 지역 변수 객체를 생성함
   따라서 이 변수 앞에 obj를 붙일 필요가 없음 : result2
3. 람다 식으로 함수를 정의하고, Square의 추상 메소드 calculate를 통하여,
   람다 함수에 인수를 전달함 : result3
   이 방법은 위의 2번 Anonymous 클래스 방식을 간략히 한 sugaring.
   즉, 모든 Anonymous 방식은 람다 식으로 간력히 표현할 수 있음.
   아래에서 s2와 s3의 타입과 이들이 사용되는 형태가 동일함을 유의할 것.
*/

@FunctionalInterface
interface Square {
  int calculate(int x);  // abstract
  default void print(String s) {System.out.println(s);}
  static void printStatic(String s) {System.out.println(s);}
}

class FunInterface implements Square {

  @Override
  // 아래 1번을 위한 구현
  public int calculate(int x) { return x*x + x;}

  public static void main(String args[]) {
    int a = 5;

    // 1. 전통적인 방법 : calculate 메소드를 구현하고, 객체를 생성하여 호출
    var obj = new FunInterface();
    int result1 = obj.calculate(a);
    System.out.println("result1: " + result1);

    // 2. Anonymous 클래스를 이용.
    // calculate를 구현하는 Square 타입의 객체 s2을 생성함.
    // s2는 main 메소드 안에서 생성된 Local 변수임 (객체 생성 불필요).
    Square s2 = new Square() {
      @Override
      public int calculate(int x) {return x*x + x;}
    };

    int result2 = s2.calculate(a);
    System.out.println("result2: " + result2);

    // 3. 람다 식을 정의하고, FI의 추상 메소드 calculate를 통하여 인수 전달
    Square s3 = x -> x*x + a;   // a must be final
    int result3 = s3.calculate(a);
    System.out.println("result3: " + result3);

    obj.print("Hello ");  // calling a default method

    Square.printStatic("World!"); // calling a static method

    // a = a + 2;     // error: a must be final or effectively final
  }
}

