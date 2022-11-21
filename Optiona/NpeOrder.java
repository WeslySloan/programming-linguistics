import java.util.Optional;

class Order {
  private int id; private int date; private Member member;

  Order (int id, int date, Member member) {
      this.id = id;  this.date = date;  this.member = member;
  }

  int getId() { return this.id; }
  int getDate() { return this.date; }
  Member getMember() { return this.member; }
}

class Member {
  private int id;  private String name;	private Address address;

  Member(int id, String name, Address address) {
    this.id = id;  this.name = name;  this.address = address;
  }

  int getId() { return this.id; }
  String getName() { return this.name; }
  Address getAddress() { return this.address; }
}

class Address {
  private String street;	private String city; private int zipcode;

  Address(String street, String city, int zipcode)  {
      this.street = street;  this.city = city;  this.zipcode = zipcode;
  }

  String getStreet() { return this.street; }
  String getCity() { return this.city; }
  int getZipcode() { return this.zipcode; }
}

public class NpeOrder {
  public static void main(String[] args) {
	  Order order; Member memb;  Address addr;

	  addr = new Address("Daeyeong-Dong", "Busan", 48434);
    memb = new Member(100, "HongGilDong", addr);
	  // order = new Order(200, 20170530, null);  // NPE
	  order = new Order(200, 20170530, memb);

	  System.out.println(getCityOfMemberFromOrder(order));
	  System.out.println(getCityOfMemberFromOrder2(null));
  }

  public static String getCityOfMemberFromOrder(Order order) {
	// return order.getMember().getAddress().getCity(); // no protection
    if (order != null) {
    	Member member = order.getMember();
     	if (member != null) {
     		Address address = member.getAddress();
     		if (address != null) {
     			String city = address.getCity();
     			if (city != null) {
     				return city;
     			}
     		}
     	}
     }
    return "Seoul"; // default
  }

  public static String getCityOfMemberFromOrder2(Order order) {
	  return Optional.ofNullable(order)
			.map(Order::getMember)
			.map(Member::getAddress)
			.map(Address::getCity)
			.orElse("Seoul");
  }
}
