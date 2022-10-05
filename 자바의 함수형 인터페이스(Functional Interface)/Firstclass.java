import java.util.function.*;
import java.util.stream.*;
import java.util.List;

public class Firstclass {

  // Functions are used as return values
  // Haskell :: greaterThan n = \x -> n > x
  static Function<Integer,Boolean> greaterThan(Integer n) {
    return x -> n > x;
  }

  public static void main(String[] args) {
    Function<Integer,Integer> square = x -> x * x;

    Stream<Integer> strm = Stream.of(2,3,4);
    strm
      // .map(x -> square.apply(x))  // functions as argument
      .map(square)                   // eta conversion
      .forEach(System.out::println);

    // Function<Integer,Integer>
    UnaryOperator<Integer> f1 = x -> x+2;
    UnaryOperator<Integer> f2 = x -> x*x;

    // Functions are stored
    List<UnaryOperator<Integer>> funs = List.of(f1, f2);
    List<Function<Integer,Integer>> funs2
      = List.of(x -> x+2, x -> x*x, x -> x*5);

    System.out.println("\nFunctions are stored");
    for(Function<Integer,Integer> f : funs2)
      System.out.println(f.apply(3));

    Boolean b = Firstclass.greaterThan(18).apply(12);
    System.out.println(b);

    List<Integer> nums = List.of(9, 10, 11, 12);
    nums.stream()
      // .map(x -> 10 > x)
      .map(Firstclass.greaterThan(10))
      .forEach(System.out::println);
  }
}
