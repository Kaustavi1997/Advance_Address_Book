package AddressBook;
import java.util.*;
public class AddressBookMain {
    List<Person> records = new ArrayList<Person> ();
    HashMap<String, Boolean> phoneNoDict = new HashMap<>();
    HashMap<String, List<Person>> personByCityDict = new HashMap<>();
    HashMap<String, List<Person>> personByStateDict = new HashMap<>();

    public void addPerson() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter phone no:");
        String phoneNo = sc.nextLine();
        if(phoneNoDict.containsKey(phoneNo)) {
            System.out.println("Entry already exists! Can't add!");
        }
        else{
            System.out.println("Enter first name:");
            String firstName = sc.nextLine();
            System.out.println("Enter last name:");
            String lastName = sc.nextLine();
            System.out.println("Enter address:");
            String address = sc.nextLine();
            System.out.println("Enter city:");
            String city = sc.nextLine();
            System.out.println("Enter state:");
            String state = sc.nextLine();
            System.out.println("Enter zip:");
            int zip = sc.nextInt();
            // Eat the new line
            sc.nextLine();

            Person person = new Person(firstName, lastName, address, city, state, zip, phoneNo);
            records.add(person);
            if (personByCityDict.containsKey(city)) {
                List<Person> existingList;
                existingList = personByCityDict.get(city);
                existingList.add(person);
                personByCityDict.put(city,existingList);
            }
            else{
                List<Person> emptyList = new ArrayList<Person> ();
                emptyList.add(person);
                personByCityDict.put(city,emptyList);
            }

            if (personByStateDict.containsKey(state)) {
                List<Person> existingList;
                existingList = personByStateDict.get(state);
                existingList.add(person);
                personByStateDict.put(state,existingList);
            }
            else{
                List<Person> emptyList = new ArrayList<Person> ();
                emptyList.add(person);
                personByStateDict.put(state,emptyList);
            }

            phoneNoDict.put(phoneNo, Boolean.TRUE);
            System.out.println("Added Successfully");
        }
    }

    public int findIndex(String phoneNo){
        int index=-1;
        for (int i = 0; i < records.size(); i++){
            String phoneNoTemporary = records.get(i).getPhoneNo();
            if(phoneNoTemporary.equals(phoneNo)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void editPersonHelper(String value,String option,int index){
        Person person = records.get(index);
        if(option=="a"){
            person.setAddress(value);
        }
        else if(option=="c"){
            person.setCity(value);
        }
        else if(option=="s"){
            person.setState(value);
        }
        else if(option=="p"){
            person.setPhoneNo(value);
        }

        records.set(index,person);
    }

    public void editPersonHelper(int value,int index){
        Person person = records.get(index);
        person.setZip(value);
        records.set(index,person);
    }

    public void editPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit");
        System.out.println("Enter phone number:");
        String phoneNo= sc.nextLine();

        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }

        int choice = 0;
        while (choice != 6) {
            System.out.println("1 for editing address");
            System.out.println("2 for editing state");
            System.out.println("3 for editing city");
            System.out.println("4 for editing by zip");
            System.out.println("5 for editing by phone no");
            System.out.println("6 for exit");
            System.out.println("Enter choice");
            choice = sc.nextInt();
            // Eat the new line
            sc.nextLine();

            String address,state,city;
            int zip;
            // Eat the new line
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter address:");
                    address = sc.nextLine();
                    editPersonHelper(address, "a", index);
                }
                case 2 -> {
                    System.out.println("Enter state:");
                    state = sc.nextLine();
                    editPersonHelper(state, "s", index);
                }
                case 3 -> {
                    System.out.println("Enter city:");
                    city = sc.nextLine();
                    editPersonHelper(city, "c", index);
                }
                case 4 -> {
                    System.out.println("Enter zip:");
                    zip = sc.nextInt();
                    sc.nextLine();
                    editPersonHelper(zip, index);
                }
                case 5 -> {
                    System.out.println("Enter phone no:");
                    phoneNo = sc.nextLine();
                    editPersonHelper(phoneNo, "p", index);
                }
                case 6 -> System.out.println("Done!");
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    public void deletePerson(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete");
        System.out.println("Enter phone number:");
        String phoneNo= sc.nextLine();

        int index = findIndex(phoneNo);
        if (index == -1) {
            System.out.println("Entry not found!");
            return;
        }
        records.remove(index);
    }

    static class sortByNameHelper implements Comparator<Person>
    {
        public int compare(Person a, Person b)
        {
            String aFirstName = a.getFirstName();
            String bFirstName = b.getFirstName();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aLastName.compareTo(bLastName) == 0){
                return aFirstName.compareTo(bFirstName);
            }
            else{
                return aLastName.compareTo(bLastName);
            }
        }
    }
    static class sortByCityHelper implements Comparator<Person> {
        public int compare(Person a, Person b) {
            String aCity = a.getCity();
            String bCity = b.getCity();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aCity.compareTo(bCity) == 0) {
                return aLastName.compareTo(bLastName);
            }
            else{
                return aCity.compareTo(bCity);
            }
        }
    }
    static class sortByStateHelper implements Comparator<Person> {
        public int compare(Person a, Person b) {
            String aState = a.getState();
            String bState = b.getState();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aState.compareTo(bState) == 0) {
                return aLastName.compareTo(bLastName);
            }
            else{
                return aState.compareTo(bState);
            }
        }
    }
    static class sortByZipHelper implements Comparator<Person>
    {
        public int compare(Person a, Person b)
        {
            int aZip = a.getZip();
            int bZip = b.getZip();

            String aLastName = a.getLastName();
            String bLastName = b.getLastName();

            if (aZip - bZip == 0){
                return aLastName.compareTo(bLastName);
            }
            else{
                return aZip - bZip;
            }
        }
    }
    public void sortByName(){
        records.sort(new sortByNameHelper());
        PrintMailinglabelformat(records);
    }

    public void sortByCity(){
        records.sort(new sortByCityHelper());
        PrintMailinglabelformat(records);
    }
    public void sortByState(){
        records.sort(new sortByStateHelper());
        PrintMailinglabelformat(records);
    }
    public void sortByZip(){
        records.sort(new sortByZipHelper());
        PrintMailinglabelformat(records);
    }

    public void searchInCityOrState(String firstName,String value,int choice){
        boolean found=false;
        HashMap<String, List<Person>> commonDict;
        if(choice==0){
            commonDict=personByCityDict;
        }
        else{
            commonDict=personByStateDict;
        }
        for (String k : commonDict.keySet()) {
            if(k.compareTo(value) == 0) {
                List<Person> v = commonDict.get(k);
                for (int i = 0; i < v.size(); i++) {
                    if(v.get(i).getFirstName().compareTo(firstName) == 0) {
                        found = true;
                        System.out.println(v.get(i).getFirstName());
                        System.out.println(v.get(i).getLastName());
                        System.out.println(v.get(i).getAddress());
                        System.out.println(v.get(i).getPhoneNo());
                    }
                }
            }
        }
        if(!found){ //found != true)
            if(choice==0) {
                System.out.println("Person not found in given city");
            }
            else{
                System.out.println("Person not found in given state");
            }
        }
    }

    public void PrintMailinglabelformat(List<Person> recordList){
        for (int i = 0; i < recordList.size(); i++){
            System.out.println(recordList.get(i).getLastName() + " " + recordList.get(i).getFirstName());
            System.out.println(recordList.get(i).getAddress());
            System.out.println(recordList.get(i).getCity());
            System.out.println(recordList.get(i).getState());
            System.out.println(recordList.get(i).getZip());
            System.out.println(recordList.get(i).getPhoneNo());
            System.out.println("");
        }
    }

    public void viewByCityOrState(int choice){
        HashMap<String, List<Person>> commonDict;
        if(choice==0){
            commonDict = personByCityDict;
        }
        else{
            commonDict = personByStateDict;
        }
        for (String k : commonDict.keySet()) {
            List<Person> v = commonDict.get(k);
                System.out.println(k);
                PrintMailinglabelformat(v);
                System.out.println("\n");
        }
    }

}
