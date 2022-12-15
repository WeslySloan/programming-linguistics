import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

// page 165 : Modern Java in Action

public class StringFlatMap {
  public static void main(String[] args) {
    List<String> words = Arrays.asList ("Hello", "World");
    List<String> uniqueChars = words.stream()  // Stream<String> : 2 elements
      .map(word -> word.split(""))      // Stream<String[]> : 2 elements
      // ( { "H", "e", "l", "l", "o" }, { "W", "o", "r", "l", "d" } )
      .flatMap(Arrays::stream)                 // Stream<String> : 1 elements
      // Arrays::stream  :: String[] => Stream<String>
      // flattening: Stream<Stream<String>> => Stream<String>
      // ( { "H", "e", "l", "l", "o", "W", "o", "r", "l", "d" } )
      .distinct()                              // Stream<String> : 7 elements
      // remove duplicated elements
      .collect(Collectors.toList());

    System.out.println(uniqueChars);

    // Using reduce
    List<String> uniqueChars2 = words.stream()  // Stream<String> : 2 elements
      .reduce("", (acc, x) -> acc + x) // Stream<String> : 1 elements
      .chars()                                  // Stream<Integer>
      .distinct()                               // remove duplicated
      .mapToObj(c -> String.valueOf((char)c))
      .collect(Collectors.toList());

    System.out.println(uniqueChars2);
  }
}

/*
  jshell> words.get(0).split("")
  $ ==> String[5] { "H", "e", "l", "l", "o" }

  jshell> words.get(0).split("")[1]
  $ ==> "e"

  jshell> Arrays.stream(words.get(0).split("")).distinct().forEach(System.out::print)
  Helo

  jshell> String[] arrStrs=  { "H", "e", "l", "l", "o", "W", "o", "r", "l", "d" }
  jshell> Arrays.stream(arrStrs).distinct().forEach(System.out::print)
  HeloWrd

 */

 // Haskell
 // init (split ["Hello", "World"])
 // = [["H","e","l","l","o"], ["W","o","r","l","d"]] :: [[String]]
 // join it
 // = ["H","e","l","l","o","W","o","r","l","d"] :: [String]
 // nub it 
 // = ["H","e","l","o","W","r","d"]

 // nub is similar to distinct() in Java