import java.util.List;
import java.util.Arrays;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

// Haskell
// take 2 [1..] = [1,2]

public class Take {
  public static void main(String[] args) {
    IntStream intStrm = IntStream.iterate(1, i -> i + 1);  // [1..]
	  List<Integer> result = intStrm
      .limit(3)
      .boxed()       // convert IntStream to Stream<Integer>
      .collect(Collectors.toList());

    System.out.println(result);
  }
}



