import Data.List
import Control.Monad


split :: [String] -> [[String]]
split []  = []
split (str: strs) = [[x] | x <- str] : split strs

listListStrings :: [[String]]
listListStrings = split ["Hello", "World"] -- equals to lls
lls = [ ["H","e","l","l","o"], ["W","o","r","l","d"] ]

-- flattenning of obj: obj.flatMap(id) 
-- = join = concat
test = join lls == (lls >>= id)  -- True

flattenedList :: [String]
flattenedList = (>>=) lls id  -- lls.flatMap(\x -> x) 
flattened = ["H","e","l","l","o","W","o","r","l","d"]
-- = [[x]| str <- ["Hello", "World"], x <- str]

result :: [String]
result = nub flattened -- ["H","e","l","o","W","r","d"]
