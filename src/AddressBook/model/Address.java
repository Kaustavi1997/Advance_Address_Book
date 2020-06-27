package AddressBook.model;

public class Address {
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

