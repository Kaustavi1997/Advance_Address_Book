package AddressBook;

public class Person {
    private final String firstName; // cant be edited so private and final
    private final String lastName;
    String address;
    String city;
    String state;
    int zip;
    String phoneNo;
    public Person(String firstName,String lastName,String address,String city,String state,int zip,String phoneNo){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNo=phoneNo;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip=zip;
    }
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCity(){
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

    public String getPhoneNo(){
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

}
