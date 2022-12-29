/*  Haskell
  square :: Integer -> Integer
  square = \x -> x * x

  twice :: (t -> t) -> t -> t
  twice1 f x = f (f x)
  twice2 f x = (f . f) x            -- function composition
  twice3 f = \x -> (f . f) x
  twice4 f = f . f                  -- from twice3, eta conversion
  twice5 = \f -> \x -> f (f x)
  twice6 = \f -> f . f              -- from twice4, point-free form

  g :: a -> (b -> c) -> b -> c
  g :: a -> ((b -> c) -> (b -> c))
  g = \x y z -> y z
*/

// Haskell에서는 오직 applicative (lambda) 함수로 표현되지만,
// Java 에서는 기존 함수와 lambda 함수가 섞여서 표현됨

import java.util.function.Function;

public class TwiceGen <T,U,V> {
  
  static Function<Integer,Integer> square = x -> x * x;
  
  Function<T, Function<Function<U,V>, Function<U,V>>> g
    = (T x) -> (Function<U,V> y) -> (U z) -> y.apply(z);
  
  T twice1 (Function<T,T> f, T x) {return f.apply(f.apply(x));}
  T twice2 (Function<T,T> f, T x) {return (f.andThen(f)).apply(x);}
  Function<T,T> twice3(Function<T,T> f) {return x -> (f.andThen (f)).apply(x);}
  Function<T,T> twice4(Function<T,T> f) { return f.andThen(f) ; }
  Function<Function<T,T>, Function<T,T>> twice5
      = (Function<T,T> f) -> (T x) -> f.apply(f.apply(x));
  Function<Function<T,T>, Function<T,T>> twice6 = (Function<T,T> f) -> f.andThen(f);

  public static void main(String[] args) {

    TwiceGen<Integer,Integer,Integer> obj = new TwiceGen<Integer,Integer,Integer>();

    Integer rg = obj.g
      .apply(10)
      .apply(square)
      .apply(20);

    Integer r1 = obj.twice1(square, 3);
    Integer r2 = obj.twice2(square, 3);
    Integer r3 = obj.twice3(square).apply(3);
    Integer r4 = obj.twice4(square).apply(3);
    Integer r5 = obj.twice5.apply(square).apply(3);
    Integer r6 = obj.twice6.apply(square).apply(3);

    System.out.println("rg: " + rg);                // 400
    System.out.println("r1: "+r1 + "\tr2: "+r2 + "\tr3: "+ r3 
                        + "\tr4: "+r4 + "\tr5: "+r5 + "\tr6: "+r6);
  }
}
