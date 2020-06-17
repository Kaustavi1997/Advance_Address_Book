package AddressBook;
import java.util.*;
public class AddressBookMain {
    List<Person> records = new ArrayList<Person> ();
    HashMap<String, Boolean> phoneNoDict = new HashMap<>();
    public void addPerson() {
        Scanner sc = new Scanner(System.in);
        String firstName = "", lastName = "", address = "", city = "", state = "", phoneNo = "";
        int zip = 0;

        System.out.println("Enter phone no:");
        phoneNo = sc.nextLine();
        if(phoneNoDict.containsKey(phoneNo)) {
            System.out.println("Entry already exists! Can't add!");
        }
        else{
            System.out.println("Enter first name:");
            firstName = sc.nextLine();
            System.out.println("Enter last name:");
            lastName = sc.nextLine();
            System.out.println("Enter address:");
            address = sc.nextLine();
            System.out.println("Enter city:");
            city = sc.nextLine();
            System.out.println("Enter state:");
            state = sc.nextLine();
            System.out.println("Enter zip:");
            zip = sc.nextInt();
            // Eat the new line
            sc.nextLine();

            Person p = new Person(firstName, lastName, address, city, state, zip, phoneNo);
            records.add(p);
            phoneNoDict.put(phoneNo, Boolean.TRUE);
            System.out.println("Added Successfully");
        }
    }

    public int findIndex(String phoneNo){
        String phoneNoTmp;
        int size = records.size();
        int index=-1;
        for (int i = 0; i < size; i++){
            phoneNoTmp = records.get(i).getPhoneNo();
            if(phoneNoTmp.equals(phoneNo)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void editPersonHelper(String value,String option,int index){
        Person obj = records.get(index);
        if(option=="a"){
            obj.setAddress(value);
        }
        else if(option=="c"){
            obj.setCity(value);
        }
        else if(option=="s"){
            obj.setState(value);
        }
        else if(option=="p"){
            obj.setPhoneNo(value);
        }

        records.set(index,obj);
    }

    public void editPersonHelper(int value,int index){
        Person obj = records.get(index);
        obj.setZip(value);
        records.set(index,obj);
    }

    public void editPerson() {
        String phoneNo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit");
        System.out.println("Enter phone number:");
        phoneNo= sc.nextLine();

        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }

        int ch = 0;
        while (ch != 6) {
            System.out.println("1 for editing address");
            System.out.println("2 for editing state");
            System.out.println("3 for editing city");
            System.out.println("4 for editing by zip");
            System.out.println("5 for editing by phone no");
            System.out.println("6 for exit");
            System.out.println("Enter choice");
            ch = sc.nextInt();
            // Eat the new line
            sc.nextLine();

            String address,state,city;
            int zip;
            switch (ch) {
                case 1:
                    System.out.println("Enter address:");
                    address = sc.nextLine();
                    editPersonHelper(address, "a", index);
                    break;
                case 2:
                    System.out.println("Enter state:");
                    state = sc.nextLine();
                    editPersonHelper(state, "s", index);
                    break;
                case 3:
                    System.out.println("Enter city:");
                    city = sc.nextLine();
                    editPersonHelper(city, "c", index);
                    break;
                case 4:
                    System.out.println("Enter zip:");
                    zip = sc.nextInt();
                    // Eat the new line
                    sc.nextLine();
                    editPersonHelper(zip, index);
                    break;
                case 5:
                    System.out.println("Enter phone no:");
                    phoneNo = sc.nextLine();
                    editPersonHelper(phoneNo, "p", index);
                    break;
                case 6:
                    System.out.println("Done!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;

            }
        }
    }

    public void deletePerson(){
        String phoneNo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete");
        System.out.println("Enter phone number:");
        phoneNo= sc.nextLine();

        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }
        records.remove(index);
    }

    public static void main(String[] args) {
        AddressBookMain obj = new AddressBookMain();
        obj.addPerson();
    }
}
