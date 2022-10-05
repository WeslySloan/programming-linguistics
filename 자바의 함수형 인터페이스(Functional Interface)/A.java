// Method Reference Programming

import java.util.List;
import java.util.stream.*;

public class A {
  String name;   Integer age;
  A(String name, Integer age) { this.name = name; this.age = age; }
  String getName() { return this.name; }
  Integer getAge() { return this.age; }
  @Override
  public String toString() {
    return "A{" + "name=" + name + ", age=" + age + '}';
  }

  public static void main(String[] args) {
    List<A> list = List.of(new A("John",10), new A("Ted", 14));
    System.out.println(list);

    // Method Reference and print
    Stream<A> strm = list.stream();
    strm.map(A::getAge).forEach(System.out::println);
    Stream<A> strm2 = list.stream();
    strm2.map(A::getName).forEach(System.out::println);

    // Medthod reference and toList()
    List<String> names
      = list.stream().map(A::getName).collect(Collectors.toList());
    System.out.println(names);

    // Lambda expressin and toList()
    List<String> names2
      = list.stream().map(x -> x.getName()).collect(Collectors.toList());
    System.out.println(names2);
  }
}
