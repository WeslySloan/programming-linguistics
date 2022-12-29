// https://docs.scala-lang.org/tour/polymorphic-methods.html

object Gen:

  // generic id functions
  val id = [T] => (x: T) => x: T

  /* <Haskell>
  twice1 f x = f (f x)
  twice2 f x = (f . f) x
  twice3 f = \x -> (f . f) x
  twice4 f = f . f              -- from twice3, eta conversion
  twice5 = \f -> \x -> f (f x)  -- from twice1, point-free
  twice6 = \f -> f . f          -- from twice4, point-free
  */
  
  def twice1[T] (f: T=>T, x: T): T  = f.apply(f.apply(x))      // f(f(x))
  def twice2[T] (f: T=>T, x: T): T  = (f andThen f) (x)        // composition
  def twice3[T] (f: T=>T): T=>T     = x => (f andThen f) (x) 
  def twice4[T] (f: T=>T): T=>T     = f andThen f              // from twice3, eta conversion
  def twice5[T]: (T=>T)=>(T=>T)     = f => x => f (f (x))      // from twice1, point-free
  def twice6[T]: (T=>T)=>(T=>T)     = f => f andThen f         // from twice4, point-free


@main def myApp = 
  println(Gen.id[Int](3: Int)) 
  println(Gen.id(3: Int))
  println(Gen.id(3))

  println(Gen.twice1((x: Int) => x * x, 3))
  println(Gen.twice2((x: Int) => x * x, 3))
  println(Gen.twice3((x: Int) => x * x).apply(3))
  println(Gen.twice4((x: Int) => x * x).apply(3))
  println(Gen.twice5.apply((x: Int) => x * x).apply(3))
  println(Gen.twice6.apply((x: Int) => x * x).apply(3))