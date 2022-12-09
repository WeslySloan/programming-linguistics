
{-# LANGUAGE DeriveFunctor #-}

-- module Test where

import Control.Monad
-- import Data.List.Split
import Data.List

-- data Maybe   a  = Just a | Nothing
data BinTree a  = Leaf a | Node (BinTree a) (BinTree a)
                  deriving (Show, Eq, Functor)

oddM :: Maybe Integer -> Maybe Bool
oddM (Just x) = Just (odd x)
oddM Nothing = Nothing

inc :: Integer -> Integer
inc x = x + 1

-- incM :: Maybe Int -> Maybe Int
incM (Just x) = Just (inc x)
incM Nothing = Nothing

-- funM :: (a -> b) -> Maybe a -> Maybe b
funM f (Just x) = Just (f x)
funM f Nothing   = Nothing

-- incBT :: BinTree Int -> BinTree Int
incBT (Leaf x)     = Leaf (inc x)
incBT (Node t1 t2) = Node (incBT t1) (incBT t2)

-- tree1 :: BinTree Int
tree1 = Node (Leaf 1) (Node (Leaf 2) (Leaf 3))

-- incL :: [Integer] -> [Integer]
incL [] = []
incL (x:xs) = (inc x) : incL xs

data List a = Empty | Cons a (List a)

mapM1 :: (a->b) -> Maybe a -> Maybe b
mapM1 f (Just x) = Just (f x)
mapM1 f Nothing = Nothing

mapBT :: (a -> b) -> BinTree a -> BinTree b
mapBT f (Leaf x) = Leaf (f x)
mapBT f (Node t1 t2) = Node (mapBT f t1) (mapBT f t2)

class Functor1 f where
  fmap1 :: (a -> b) -> f a -> f b

instance Functor1 [] where
  fmap1 = map

instance Functor1 BinTree where
  fmap1 = mapBT

instance Functor1 Maybe where
  fmap1 = mapM1


-- test
t1 = mapM1 odd (Just 3) 
t2 = fmap1 odd (Just 3)

-- Functor Test
functorIdTest x   = fmap id x == id x
functorCompTest f x = fmap (inc . inc) x == ((fmap inc) . (fmap inc)) x
f2 = fmap id tree1 == id tree1

f3 :: Maybe (Maybe Bool)
f3 = fmap Just (Just True)
f4 :: Maybe Bool
f4 = join f3

-- Applicative
a1 = Just inc <*> (Just 4)

-- join
jm1 = join (Just Nothing)                 -- Nothing
jm2 = join (Just (Just (Just 'a')))       -- Just (Just 'a')

jl1 = join [[1], [2,3], [], [4]]          -- [1,2,3,4]
jl2 = join [[1,2,3,4]]                    -- [1,2,3,4]
jl3 = join [[], [1], [2,3,4]]             -- [1,2,3,4]

-- Monad
l11 =  do x <- [10, 20, 30]
          [x, x+1]                        -- [10, 11, 20, 21, 30, 31]
l12 = [10,20,30] >>= \x -> [x, x+1]
l13 = (>>=) [10,20,30] (\x -> [x, x+1])

l21 =  do x <- [10, 20, 30]
          return [x, x+1]                  -- [[10,11],[20,21],[30,31]]
l22 = [ [x]++[x+1] | x <- [10, 20, 30] ]
l23 = [ [x, x+1] | x <- [10, 20, 30] ]

lFlat = join l21                         -- [10, 11, 20, 21, 30, 31]

lt3 = [ [] | x <- [10, 20, 30] ]

lt4 = [ [x, x+1, x+2] | x <- [10, 20, 30] ]

xs :: [Integer]
xs = [10 ..]

exL1 =
  do  x <- [1,2,3]
      y <- [4,5]
      z <- [0,0]
      return (x+y+z) -- return (x+10, y)

addM :: Maybe Int -> Maybe Int -> Maybe Int
addM x y =
  do  a <- x
      b <- y
      return (a+b)

-- FlatMap in java
strs1 = ["Hello", "World"]
strs2 = ["Modern", "Java", "In", "Action"]

str1 = strs1 >>= \word -> word         -- just flattening  = join
str1' = strs1 >>= id                   -- just flattening  = join
str2 = strs1 >>= \word -> [word]
    -- flattening after lifting : no change ["Modern","Java","In","Action"]
    -- (join . return) [1,2,3] = [1,2,3]
str3 = strs1 >>= \word -> [word, word]

-- split each element of list, and flattening
-- splitted :: [String]  -> [String]                -- type String = [Char]
-- splitted strs = join $ map (tail . splitOn "") strs
--  ["Hello", "World"]
-- => [ [ "H", "e", "l", "l", "o" ], [ "W", "o", "r", "l", "d" ] ]
-- => [ "H", "e", "l", "l", "o" , "W", "o", "r", "l", "d" ]

splitted2 :: [String] -> [String]
splitted2 = map (\x -> [x]) . foldr (++) ""

uniqueChars :: [String] -> [String]
uniqueChars = nub . splitted2    -- nub acts like distinct() in Java

