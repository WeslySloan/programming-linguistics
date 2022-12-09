import java.util.Optional;

/* Haskell
  fmap :: (a -> b) -> Maybe a -> Maybe b
  fmap :: (a -> Maybe b) -> Maybe a -> Maybe (Maybe b)
  bind :: Maybe a -> (a -> Maybe b) -> Maybe b
  return :: a -> Maybe a
  addM :: Maybe Int -> Maybe Int -> Maybe Int
  addM x y = x >>= \a -> (y >>= \b -> return(a+b))
  addM x y = { a <- x; b <- y; return (a + b) }

  test1 = addM (Just 10) (Just 20)  -- (Just 30)
  test2 = addM Nothing (Just 20)    -- Nothing
 */

/* Java 8
  <U> Optional<U> optVal<T>.map(Function<T,U> mapper)
  static <T> Optional<T> of(T val)
  <U> Optional<U> optVal<T>.flatMap(Function<T, Optional<U>> mapper)
  return x  Optional.of(x)
  Nothing   Optional.empty()
 */
public class AddOpt {
  static Optional<Integer> addOpt(Optional<Integer> x, Optional<Integer> y) {
    return
      x.flatMap(a ->               // x -> a
      y.flatMap(b ->               // y -> b
        Optional.of(a+b)));        // return (a + b)
  }

  public static void main(String[] args) {
    Optional<Integer> x = Optional.empty();
    Optional<Integer> result = addOpt(Optional.of(10), Optional.of(20));
    System.out.println(result);    // Optional[30]
    result = addOpt(Optional.empty(), Optional.of(20));
    System.out.println(result);    // Optional.empty
  }
}
