import java.util.function.*;

public class Lambda {
  public static void main(String[] args) {

    BinaryOperator<Integer> add1 = (x,y) -> x + y;
    System.out.println(add1.apply(10,20));

    Function<Integer, Function<Integer,Integer>> add2 = x -> (y -> x + y);
    System.out.println(add2.apply(10).apply(20));

    Function<Integer,Function<Integer, Function<Integer,Integer>>>
      add3 = x -> y -> z -> x+y+z;
    System.out.println(add3.apply(10).apply(20).apply(30));

    Function<Integer,Function<Integer, Function<Integer,Integer>>>
      add3Lam = x -> (y -> (z -> x+y+z));
    System.out.println(add3Lam.apply(10).apply(20).apply(30));

    BinaryOperator<Integer> max = (x,y) -> x > y ? x : y;
    System.out.println(max.apply(10,20));
  }

}
