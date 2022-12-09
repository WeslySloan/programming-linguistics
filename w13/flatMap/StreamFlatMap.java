import java.util.stream.*;
import java.util.List;

// Functor keeps the structure of the stream.
// flattening can extend and change the structure of the stream.
// Stream<R> stream<T>.flatMap(Function<T, Stream<R> mapper)

public class StreamFlatMap {
  public static void main(String[] args) {
    List<Integer> source = List.of(10,20,30);
    Stream<Integer> xs = source.stream();
    List<Integer> list = xs
      .flatMap(x -> Stream.of(x, x+1)) // flat [ [10,11], [20,21], [30,31] ]
      .collect(Collectors.toList());
    System.out.println(list);          // [10, 11, 20, 21, 30, 31]
    // [10, 20, 30] >>= \x -> [x, x+1]
    // join [ [10,11], [20,21], [30,31] ]

    Stream<Integer> ys = source.stream();
    List<Integer> list2 = ys
      .flatMap(x -> Stream.of(x, x+1))
      .flatMap(y -> (y>=20) ? Stream.empty() : Stream.of(y,y))
      .collect(Collectors.toList());
    System.out.println(list2);          // [10, 10, 11, 11]
    // [10, 20, 30] >>= \x -> [x, x+1] >>= \y -> if (y>=20) then [] else [y,y]

    // flatMap for filter
    List<Integer> source2 = List.of(5,4,9,6,3,1,8,2,7);
    List<Integer> list3 = source2.stream()
      .flatMap(x -> (x%2==0) ? Stream.of(x) : Stream.empty())
      .collect(Collectors.toList());
    System.out.println(list3);          // [4, 6, 8, 2]
    // 5,4,9,6,3,1,8,2,7] >>= \x -> if (x `mod` 2 == 0) then [x] else []

    // flatMap for map
    List<Integer> list4 = source2.stream()
      .flatMap(x -> Stream.of(x*10))
      .collect(Collectors.toList());
    System.out.println(list4);      //[50, 40, 90, 60, 30, 10, 80, 20, 70]
    // [5,4,9,6,3,1,8,2,7] >>= \x -> [x * 10]
  }
}

