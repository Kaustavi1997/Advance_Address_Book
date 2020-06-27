package AddressBook.model;

public class Person {
    String firstName;
    String lastName;
    Address address;
    String phoneNo;
    public Person(String firstName,String lastName,String phoneNo,Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public Address getAddress(){
        return this.address;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

}
