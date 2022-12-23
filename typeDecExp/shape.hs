  data Shape = Circle Float | Rect Float Float
  
  square :: Float -> Shape 
  square n = Rect n n
  
  area :: Shape -> Float 
  area (Circle r) = pi * r^2 
  area (Rect x y) = x * y

  rect1, rect2, circle1 :: Shape
  rect1 = Rect 10.0 20.0
  rect2 = square 5.0
  circle1 = Circle 10.0
