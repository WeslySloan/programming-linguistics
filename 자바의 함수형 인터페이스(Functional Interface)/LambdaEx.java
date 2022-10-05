import java.util.function.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class LambdaEx {
  public static void main(String[] args) {

    // Unary function
    Function<Integer,Integer> inc = x -> x + 2;
    System.out.println(inc.apply(10));

    // Binary function with tuple
    BiFunction<Integer,Integer,Integer> add = (x,y) -> x + y;
    System.out.println(add.apply(10,20));

    // Binary function with single type
    BinaryOperator<Integer> add1 = (x,y) -> x + y;
    System.out.println(add1.apply(5,4));

    // Binary curried-function
    Function<Integer, Function<Integer,Integer>> add2 = x -> y -> x+y;
    Integer result = add2.apply(100).apply(200);
    System.out.println(result);

    // void function
    Consumer<Integer> printInt = x -> System.out.println(x);
    Consumer<Integer> printInt1 = (x) -> {
      int y = x * x;
      System.out.println(y);
    };
    Consumer<String> strPrint = System.out::println;  // String type
    printInt.accept(99);
    printInt1.accept(8);
    strPrint.accept("Method Referece");

    // void Binary Function
    BiConsumer<String,Integer> printTwo
      = (x,y) -> System.out.println("*** " + x + " *** : " + y);
    printTwo.accept("Apple", 10);

    // *** Functions are saved *** //
    List<BinaryOperator<Integer>> binOps =
        new ArrayList<BinaryOperator<Integer>> (
              Arrays.asList((x, y) -> y, (x, y) -> ++x));
    binOps.add((x, y) -> --x);
    binOps.add((x, y) -> x + y);
    binOps.add((x, y) -> x - y);

    System.out.println("\nApplying Iterator ");
    for(BinaryOperator<Integer> binOp : binOps)     // iterator
        System.out.println(binOp.apply(333, 88));

    System.out.println();
    Iterator<BinaryOperator<Integer>> itr = binOps.iterator();
    while (itr.hasNext()) {
        BinaryOperator<Integer> op = (BinaryOperator<Integer>) itr.next();
        System.out.println(op.apply(333, 88));
    }

    // *** Functions are returned *** //
    Function<Integer, Function<Integer,Integer>> f =  add2;
    Function<Integer, Function<Integer,Integer>> f1 = y -> f.apply(y);
    Function<Integer, Function<Integer,Integer>> f2 = x -> f1.apply(x);
    System.out.println(f2.apply(1).apply(3));

    // Function Definition with Function Names: Using jshell
    // import java.util.function.*;
    // Function<Integer, Function<Integer,Integer>> add2 = x -> y -> x+y;
    // Function<Integer, Function<Integer,Integer>> f() { return add2; }
    // Function<Integer,Integer> f1 (Integer x) { return f().apply(x); }
    // Integer f2 (Integer x, Integer y) { return f1(x).apply(y); }
    // add2.apply(10).apply(20)  // apply-apply
    // f().apply(10).apply(20)   // call-apply-apply
    // f1(10).apply(20)          // call-apply
    // f2(10,20)                 // call

    /* Functions Definition with Function Name: Compile and Run
      import java.util.function.*;
      public class Test {
        Function<Integer, Function<Integer,Integer>> add2 = x -> y -> x+y;
        Function<Integer, Function<Integer,Integer>> f() { return add2; }
        Function<Integer,Integer> f1 (Integer x) { return f().apply(x); }
        Integer f2 (Integer x, Integer y) { return f1(x).apply(y); }
        public static void main(String[] args) {
          Test x = new Test();
          System.out.println( x.add2.apply(10).apply(20));
          System.out.println(x.f().apply(10).apply(20));
          System.out.println(x.f1(10).apply(20));
          System.out.println(x.f2(10,20));
        }
      }
    */

  }

}
