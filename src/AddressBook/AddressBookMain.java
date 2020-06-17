package AddressBook;
import java.util.*;
public class AddressBookMain {
    List<Person> records = new ArrayList<Person> ();
    public void addPerson(){Scanner sc = new Scanner(System.in);
        String firstName="",lastName="",address="",city="",state="",phoneNo="";
        int zip=0;
        System.out.println("Enter first name:");
        firstName= sc.nextLine();
        System.out.println("Enter last name:");
        lastName= sc.nextLine();
        System.out.println("Enter address:");
        address= sc.nextLine();
        System.out.println("Enter city:");
        city= sc.nextLine();
        System.out.println("Enter state:");
        state= sc.nextLine();
        System.out.println("Enter zip:");
        zip= sc.nextInt();
        // Eat the new line
        sc.nextLine();
        System.out.println("Enter phone no:");
        phoneNo= sc.nextLine();
        Person p=new Person(firstName,lastName,address,city,state,zip,phoneNo);
        records.add(p);
        System.out.println("Added Successfully");

    }
    public static void main(String[] args) {
        AddressBookMain obj = new AddressBookMain();
        obj.addPerson();
    }

}
