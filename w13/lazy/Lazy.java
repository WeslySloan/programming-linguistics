import java.util.List;
import java.util.Arrays;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class Lazy {
  public static void main(String[] args) {
	List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
	// List<Integer> twoEvenSquares = numbers.stream()
  IntStream intStrm = IntStream.iterate(1, i -> i + 1);  // [1..]
	List<Integer> twoEvenSquares = intStrm
    .filter(n -> {
        System.out.println("filtering " + n);
        return n % 2 == 0;
      })
    .map(n -> {
        System.out.println("mapping " + n);
        return n * n;
      })
    .limit(2)
    .boxed()       // convert IntStream to Stream<Integer>
    .collect(Collectors.toList());

  System.out.println(twoEvenSquares);
  }
}

// Haskell
// twoEvenSquares
//   = take 2 . map (\x -> x * x) . filter (\n -> n `mod` 2 == 0) $ [1..]


