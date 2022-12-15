
take' :: Int -> [a] -> [a]
take' 0 xs = []
take' n (x:xs) = x : take' (n-1) xs

test = take' 3 [1..]

{-
  take 2 [1..]
  = 1 : take 1 [2..]
  = 1 : 2 : take 0 [3..]
  = 1 : 2 : [] = [1,2]
-}
