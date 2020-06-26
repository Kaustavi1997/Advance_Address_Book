package AddressBook;

class Address{
    String address;
    String city;
    String state;
    int zip;

    public Address(String address,String city,String state,int zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public int getZip(){
        return zip;
    }
    public void setZip(int zip){
        this.zip = zip;
    }
}
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
