import java.util.Optional;
import java.util.function.BiFunction;

public class SafeDiv {
  public static void main(String[] args) {

    BiFunction<Integer,Integer,Optional<Integer>> safeDiv 
    = (x, y) -> (y == 0) ? Optional.empty() : Optional.of(x/y);

    Optional<Integer> e1 = safeDiv.apply(10, 2);
    Optional<Integer> e2 = safeDiv.apply(10, 0);

    System.out.println( e1.isPresent() ? e1.get() : "divide by zero" );
    System.out.println( e2.isPresent() ? e1.get() : "divide by zero" );
  }
}