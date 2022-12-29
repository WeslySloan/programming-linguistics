

twice1 f x = f (f x)
twice2 f x = (f . f) x
twice3 f = \x -> (f . f) x
twice4 f = f . f              -- from twice3, eta conversion
twice5 = \f -> \x -> f (f x)  -- from twice1, point-free
twice6 = \f -> f . f          -- from twice4, point-free

square x = x * x

